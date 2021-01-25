import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

public class VisitorCompilador extends AnasintBaseVisitor<Object>{
    String codigoPrograma = "";
    boolean variablesPrivadas = false;
    boolean variablesBajoCondicion = false;
    boolean casteoVariables = true;
    Stack<String> tiposSecuencia = new Stack<>();
    Programa programa = new Programa("Main", "Main");
    String scope = null;
    final String _tmpGlobal = "_" + UUID.randomUUID().toString().replace("-", "");

    List<String> variablesCondicion = new ArrayList<>();
    int tmpIndex = 0;

    private String genPropagaIndefinicion (Anasint.PredicadoContext ctx, boolean niega, String elmVar) {
        variablesBajoCondicion = true;
        String valorPredicado = (String) visit(ctx);
        if (elmVar != null)
            variablesCondicion.remove(variablesCondicion.indexOf(elmVar));
        variablesBajoCondicion = false;
        String strCondicionNoIndef = "!Arrays.asList(" +
                String.join(",",variablesCondicion) +
                ").contains(null)" + " && \n" + (niega ? "!": "") + "(" + valorPredicado + ")";
        variablesCondicion.clear();
        return strCondicionNoIndef;
    }
    private ParserRuleContext closestBreakBlock (ParserRuleContext ctx) {
        if (ctx.getParent().getClass().equals(Anasint.Instruccion_controlContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Instruccion_bucleContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx.getParent();
        else
            return closestBreakBlock(ctx.getParent());
    }
    private String convierteTipo (String tipoP) {
        String res = "";
        switch (tipoP) {
            case "NUM" -> res = "Integer";
            case "LOG" -> res = "Boolean";
            //case "SEQ(NUM)", "SEQ(LOG)" -> res = "Object[]";

            case "SEQ(NUM)" -> res = "Integer[]";
            case "SEQ(LOG)" -> res = "Boolean[]";
        }
        return res;
    }
    private String quitaUltimaComa (String str) {
        int lastIndex = str.lastIndexOf(",");
        if (lastIndex >= 0)
            return str.substring(0 ,str.lastIndexOf(","));
        else return str;
    }
    private String getTipoVariable (Anasint.VariableContext ctx) {
        if (ctx.getClass().equals(Anasint.Variable_simpleContext.class))
            return programa.getVariable(ctx.getChild(0).getText()).getTipo();
        else
            return programa.getVariable(scope, ctx.getChild(0).getText()).getTipo().replace("[]", "");
    }
    /*
    private Map<String, String> getUpperScope (ParserRuleContext ctx) {
        if (scope.get(ctx) != null)
            return scope.get(ctx);
        else
        if (ctx.getParent() == null)
            return null;
        return getUpperScope(ctx.getParent());
    }*/
    private String ultimaPosicion () {
        return
                "private static Integer ultima_posicion (Object[] entrada) {\n" +
                "   return entrada.length - 1;\n" +
                "}\n";
    }
    private String vacia () {
        return
                "private static Boolean vacia (Object[] entrada) {\n" +
                "   return entrada.length == 0;\n" +
                "}\n";
    }
    private String mostrar () {
        return
                "private static void mostrar (Object[] entrada) {\n" +
                "   System.out.println(\"Mostrar: \" + Arrays.toString(entrada));\n" +
                "}\n";
    }

    public String visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        //scope.put(ctx, new OrderedHashMap<>());
        variablesPrivadas = false;
        programa.declaraSubprogramaNativo("ultima_posicion", "Funcion");
        programa.declaraSubprogramaNativo("vacia", "Funcion");
        programa.declaraSubprogramaNativo("mostrar", "Procedimiento");

        //funciones.put("ultima_posicion", Arrays.asList("Object[]"));
        //funciones.put("vacia", Arrays.asList("Object[]"));
        codigoPrograma += "import java.util.Arrays;\nimport java.util.stream.IntStream;\n public class Test {\n" + "private static Object[] " + _tmpGlobal + ";\n";
        codigoPrograma += ultimaPosicion();
        codigoPrograma += vacia();
        codigoPrograma += mostrar();
        codigoPrograma += visitBloque_variables(ctx.bloque_variables()) + "\n";
        codigoPrograma += visitBloque_subprogramas(ctx.bloque_subprogramas()) + "\n";
        codigoPrograma += "public static void main(String[] args) throws Exception {\n" + visitBloque_instrucciones(ctx.bloque_instrucciones());
        codigoPrograma += "}\n}";
        return "";
    }

    public String visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        String res = "";
        //Map<String, String> scopePadre = getUpperScope(ctx);
        /*
        if (!ctx.getParent().getClass().equals(Anasint.Bloque_programaContext.class)) {
            Map<String, String> scopeHijo = new OrderedHashMap<>();
            scopeHijo.putAll(scopePadre);
            //scope.put(ctx, scopeHijo);
        }*/
        for (Anasint.Declaracion_variableContext variable : ctx.declaracion_variable())
            res += (visitDeclaracion_variable(variable));
        return res;
    }

    public String visitBloque_subprogramas (Anasint.Bloque_subprogramasContext ctx) {
        //String res = "public static void main(String[] args) throws Exception {\n";
        String res = "";
        variablesPrivadas = true;

        //TODO esto deberiamos separarlo y hacerlo secuencial..
        for (Anasint.Bloque_procedimientoContext procedimiento: ctx.bloque_procedimiento())
            res += visit(procedimiento);
        for (Anasint.Bloque_funcionContext funcion: ctx.bloque_funcion())
            res += visit(funcion);

        return res;
    }

    public String visitBloque_instrucciones(Anasint.Bloque_instruccionesContext ctx) {
        String res = "";
        for (Anasint.InstruccionContext instruccion: ctx.instruccion())
            res += visit(instruccion);
        return res;
    }

    public String visitBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        //Map<String, String> scopePadre = getUpperScope(ctx);
        String declaracionVariablesSalida = "";
        String nombrePrograma = ctx.IDENT().getText();
        scope = nombrePrograma;
        String tipoSalida, nombre;
        String res = "";
        res += "private static ";
        //funciones.put(nombrePrograma, new ArrayList<>());
        Programa subprograma = new Programa(nombrePrograma, "Funcion");

        int i = 0;
        int j;
        if (ctx.lista_variables_tipadas().size() > 1)
            i = 1;

        //la funcion tiene varios valores de salida, los añadimos al scope
        if (ctx.lista_variables_tipadas(i).IDENT().size() > 1) {
            res += "Object[] ";
            tipoSalida = "Object[]";
            //aqui añadimos el tipo de variables de salida
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(1).tipo()) {
                j = tipo.getParent().children.indexOf(tipo);
                nombre = tipo.parent.getChild(j + 1).getText();
                declaracionVariablesSalida += (convierteTipo(tipo.getText()) + " " + nombre +";\n");
                subprograma.declaraVariable(null, new Variable(nombre, tipo.getText(), "Salida"));
                //scopePadre.put(nombre, convierteTipo(tipo.getText()));
            }
        }
        //funcion solo tiene un valor como salida
        else {
            Anasint.TipoContext tipo = ctx.lista_variables_tipadas(i).tipo(0);
            j = tipo.getParent().children.indexOf(tipo);
            nombre = tipo.parent.getChild(j + 1).getText();
            tipo.parent.getChild(j + 1).getText();
            tipoSalida = convierteTipo(tipo.getText());
            res += convierteTipo(tipo.getText()) + " ";
            declaracionVariablesSalida += convierteTipo(tipo.getText()) + " " + nombre +";\n";
            subprograma.declaraVariable(null, new Variable(nombre, tipo.getText(), "Salida"));
            //scopePadre.put(nombre, convierteTipo(tipo.getText()));
        }
        //analizamos las variables de entrada
        res += ctx.IDENT().getText() + " (";
        //si i > 0, tiene entrada/salida, y la entrada esta en indice 0
        if (i > 0) {
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(0).tipo()) {
                j = tipo.getParent().children.indexOf(tipo);
                nombre = tipo.parent.getChild(j + 1).getText();
                res += (convierteTipo(tipo.getText()) + " " + nombre);
                //scopePadre.put(nombre, convierteTipo(tipo.getText()));
                subprograma.declaraVariable(null, new Variable(nombre, tipo.getText(), "Entrada"));
                if (tipo.parent.getChild(j+2) != null)
                    res += tipo.parent.getChild(j+2);
            }
            /*
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(1).tipo()) {
                funciones.get(nombrePrograma).add(convierteTipo(tipo.getText()));
            }
             */
        }
        res += ") {\n" + declaracionVariablesSalida;


        res += visitBloque_variables(ctx.bloque_variables());
        res += visitBloque_instrucciones(ctx.bloque_instrucciones());
        res += " }\n";
        //si devuelve mas de una cosa, devuelve Object[]
        if (!subprograma.esArgumento())
        //if (funciones.get(nombrePrograma).size() > 1)
            res += "private static " + tipoSalida + " salida_" + nombrePrograma + ";\n";
        programa.declaraSubprograma(subprograma);
        scope = null;
        return res;
    }

    public String visitBloque_procedimiento (Anasint.Bloque_procedimientoContext ctx) {
        //Map<String, String> scopePadre = getUpperScope(ctx);

        String nombrePrograma = ctx.IDENT().getText();
        scope = nombrePrograma;
        String res, nombre;
        Programa subprograma = new Programa(nombrePrograma, "Procedimiento");
        //funciones.put(nombrePrograma, new ArrayList<>());
        int j;
        res = "private static void " + nombrePrograma + " (";

        //entrada
        for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas().tipo()) {
            j = tipo.getParent().children.indexOf(tipo);
            nombre = tipo.parent.getChild(j + 1).getText();
            res += (convierteTipo(tipo.getText()) + " " + nombre);
            subprograma.declaraVariable(null, new Variable(nombre, tipo.getText(), "Entrada"));
            //scopePadre.put(nombre, convierteTipo(tipo.getText()));
            if (tipo.parent.getChild(j+2) != null)
                res += tipo.parent.getChild(j+2);
        }
        res += ") {\n";
        /*
        for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas().tipo()) {
            funciones.get(nombrePrograma).add(convierteTipo(tipo.getText()));
        }
         */
        res += visitBloque_variables(ctx.bloque_variables());
        res += visitBloque_instrucciones(ctx.bloque_instrucciones());
        res += " }\n";
        programa.declaraSubprograma(subprograma);
        scope = null;
        return res;
    }

    public String visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        String res = "return ";
        if (ctx.evaluaciones_variables().evaluacion_variable().size() > 1) {
            res += "new Object[]{";
            for (Anasint.Evaluacion_variableContext variable: ctx.evaluaciones_variables().evaluacion_variable()) {
                res += (visitEvaluacion_variable(variable) + ",");// += visitEvaluacion_variable(variable) + ",";
            }
            res = quitaUltimaComa(res) + "}";
        } else
            res += ctx.evaluaciones_variables().evaluacion_variable(0).getText();
        return res + ";\n";
    }
    public String visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        String varTmp0, varTmp1, varTmp, subprogramaAvance, compruebaAvance;
        varTmp = compruebaAvance = "";
        if (ctx.subprograma() != null) {
            varTmp = "int[] _tmp" + tmpIndex + " = {Integer.MAX_VALUE, 0};\n";
            varTmp0 = "_tmp" + tmpIndex + "[0]";
            varTmp1 = "_tmp" + tmpIndex + "[1]";

            subprogramaAvance = (String) visit(ctx.subprograma());
            compruebaAvance =
                varTmp1 + " = " + varTmp0 + ";\n" +
                varTmp0 + " = " + subprogramaAvance + ";\n" +
                "if (!Arrays.asList(_tmp" + tmpIndex +
                        ").contains(null) && \n (" +
                        varTmp0 + " < 0 || " +
                        varTmp0 + " >= " + varTmp1 + "))\n" +
                    "throw new IllegalArgumentException(\"Función de avance inválida\");\n";
            tmpIndex++;
        }
        String strPropagaIndef = genPropagaIndefinicion(ctx.predicado(), false,null);
        String res = varTmp + "while (" + strPropagaIndef + ") {\n";

        res += compruebaAvance;

        for (Anasint.InstruccionContext instruccion: ctx.instruccion())
            res += visit(instruccion);
        return res + "}\n";
    }
    public String visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        String strPropagaIndef = genPropagaIndefinicion(ctx.predicado(), false,null);
        String res = "if (" + strPropagaIndef + ") {\n";
        //dividimos las instrucciones en dos partes, si/sino
        for (ParseTree hijo: ctx.children) {
            if (hijo.equals(ctx.SINO()))
                res += "} else {\n";
            else if (Anasint.InstruccionContext.class.isAssignableFrom(hijo.getClass()))
                res += visit(hijo);
        }
        return res + "}\n";
    }
    public String visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        if (closestBreakBlock(ctx).getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return "return;\n";
        else
            return "break;\n";
    }
    public String visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        String res = "";
        List<String> constr = new ArrayList<>();
        Programa subprograma;
        //llenamos la pila con las variables de la izq y la invertimos
        Stack<Anasint.VariableContext> variablesIzq = new Stack<>();
        variablesIzq.addAll(ctx.lista_variables().variable());
        Collections.reverse(variablesIzq);

        //asignación multiple
        if (ctx.lista_variables().variable().size() > 1) {
            String nombreSubprograma;
            int i = 0;
            int k;
            //reservamos la primera linea para la variable temporal
            constr.add(null);
            //vamos rellenando la variable temporal con las evaluaciones
            String _tmpVar = _tmpGlobal + " = new Object[] {";
            for (Anasint.Evaluacion_variableContext evalr: ctx.evaluaciones_variables().evaluacion_variable()) {
                nombreSubprograma = (evalr.subprograma() != null) ? evalr.subprograma().getChild(0).getText() : null;
                String tipoVariable = "(" + getTipoVariable(variablesIzq.peek()) + ")";

                //si encontramos un subprograma
                if (evalr.subprograma() != null) {
                    subprograma = programa.getSubprograma(nombreSubprograma);
                    //vemos si es un argumento o no (dev > 2)
                    if (!subprograma.esArgumento()) {
                        //si no es argumento, calculamos los subindices
                        //ej: i = _tmp[1][0];
                        //    j = _tmp[1][1];
                        //  ... = _tmp[i][k];
                        k = 0;
                        List<String> tiposSalida = subprograma.getTiposSalida();
                        for (int j = 0; j < tiposSalida.size(); j++) {
                            tipoVariable = "(" + getTipoVariable(variablesIzq.peek()) + ")";
                            constr.add(visit(variablesIzq.pop()) + " = " + tipoVariable +  " ((Object[]) " + _tmpGlobal + "[" + i + "])[" + k + "];\n");
                            k++;
                        }
                    } else {
                        if (evalr.operando_secuencia() != null)
                            tiposSecuencia.push(getTipoVariable(variablesIzq.peek()));
                        //si vale como argumento, se añade normalmente
                        constr.add(visit(variablesIzq.pop()) + " = " + tipoVariable + " " + _tmpGlobal + "[" + i + "];\n");
                    }
                } else {
                    if (evalr.operando_secuencia() != null)
                        tiposSecuencia.push(getTipoVariable(variablesIzq.peek()));
                    //si no es subprograma, se añade normal
                    constr.add(visit(variablesIzq.pop()) + " = " + tipoVariable + " " + _tmpGlobal + "[" + i + "];\n");
                }
                //seguimos construyendo el array temporal
                _tmpVar += visitEvaluacion_variable(evalr) + ",";
                i++;
            }
            //ponemos como primera asignación la del array temporal
            _tmpVar = quitaUltimaComa(_tmpVar) + "};\n";
            constr.set(0, _tmpVar);
            //cuando acabamos, juntamos el array 'constr' en un string
            res = String.join("", constr);
        } else {
            //asignación simple
            if (ctx.evaluaciones_variables().evaluacion_variable(0).operando_secuencia() != null)
                tiposSecuencia.push(getTipoVariable(variablesIzq.peek()));
            res += visit(variablesIzq.pop()) + " = " + visit(ctx.evaluaciones_variables().evaluacion_variable(0)) + ";\n";
            /*
            for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
                pilaVariables.add(variable.getText());
                //System.out.println(variable.getText() + " " + getUpperScope(ctx).get(variable.getChild(0).getText()));
                //si la variable es de asignacion, añadimos al stack la info
                if (variable.getClass().equals(Anasint.Variable_simpleContext.class) &&
                        programa.getVariable(scope, variable.getChild(0).getText()).getTipo().contains("[]"))
                    tiposSecuencia.push(programa.getVariable(scope, variable.getChild(0).getText()).getTipo());

                //scope.get(variable.getChild(0).getText()).contains("[]"))
                //tiposSecuencia.push(scope.get(variable.getChild(0).getText()));
            }
            Collections.reverse(pilaVariables);
        }

        //casteoVariables = true;
        for (Anasint.Evaluacion_variableContext valor: ctx.evaluaciones_variables().evaluacion_variable()) {
            //si es funcion, (y devuelve mas de 1) hay que usar el array temporal de la funcion
            if (valor.subprograma() != null) {
                str = valor.subprograma().getText();
                str = str.substring(0, str.indexOf("("));
                subprograma = programa.getSubprograma(str);
                //si tiene mas de un valor de salida
                if (!subprograma.esArgumento()) {
                //if (funciones.get(str).size() > 1) {
                    res += ("salida_" + str + "=" + visitEvaluacion_variable(valor) + ";\n");
                for (int i = 0; i < subprograma.getTiposSalida().size(); i++) {
                    res += (pilaVariables.pop() + "= (" + subprograma.getTiposSalida().get(i) + ") salida_" + str + "[" + i + "];\n");
                }
            }
                else
                    res += (pilaVariables.pop() + "=" + visitEvaluacion_variable(valor) + ";\n");
            } else
                res += (pilaVariables.pop() + "=" + visitEvaluacion_variable(valor) + ";\n");
        */
        }

        return res;
    }
    public String visitInstruccion_llamada_subprograma (Anasint.Instruccion_llamada_subprogramaContext ctx) {
        return visit(ctx.subprograma()) + ";\n";
    }
    public String visitInstruccion_aserto_simple (Anasint.Instruccion_aserto_simpleContext ctx) {
        String strPropagaIndef = genPropagaIndefinicion(ctx.predicado(), false, null);
        return "if (" + strPropagaIndef + ") {\n" +
                "throw new IllegalArgumentException(\"Aserto no válido\");\n}\n";
    }
    public String visitInstruccion_aserto_cuantificado (Anasint.Instruccion_aserto_cuantificadoContext ctx) {
        String operacionA = (String) visit(ctx.operacion(0));
        String operacionB = (String) visit(ctx.operacion(1));
        String variable = ctx.variable().getText();
        String[] strPropagaIndef = genPropagaIndefinicion(ctx.predicado(), false, variable).split("&&");
        String funcionCuantificador = (String) visit(ctx.cuantificador());
        return "if (" + strPropagaIndef[0] + " && !(IntStream.range(" + operacionA + ", " +
                operacionB + ")." + funcionCuantificador + "(" + variable + " -> (" + strPropagaIndef[1] + ")))) {\n" +
                "throw new IllegalArgumentException(\"Aserto no válido\");\n}\n";
    }

    public List<String> visitEvaluaciones_variables (Anasint.Evaluaciones_variablesContext ctx) {
        List<String> res = new ArrayList<>();
        for (Anasint.Evaluacion_variableContext evalVar: ctx.evaluacion_variable())
            res.add((String) visit(evalVar));
        return res;
    }

    public String visitPredicado_negado (Anasint.Predicado_negadoContext ctx) {
        return "!" + visit(ctx.predicado());
    }
    public String visitPredicado_envuelto (Anasint.Predicado_envueltoContext ctx) {
        return (String) visit(ctx.predicado());
    }
    public String visitPredicado_base (Anasint.Predicado_baseContext ctx) {
        return (String) visit(ctx.condicion());
    }
    public String visitPredicado_rec(Anasint.Predicado_recContext ctx) {
        String predicadoA = (String) visit(ctx.predicado(0));
        String predicadoB = (String) visit (ctx.predicado(1));
        return predicadoA + " " + ctx.operador_condicion_2_ario().getText() + " " + predicadoB;
    }

    public String visitCondicion_base (Anasint.Condicion_baseContext ctx) {
        return (String) visit(ctx.getChild(0));
    }
    public String visitCondicion_envuelta (Anasint.Condicion_envueltaContext ctx) {
        return "(" + visit(ctx.condicion()) + ")";
    }
    public String visitCondicion_rec (Anasint.Condicion_recContext ctx) {
        String condicionA = (String) visit(ctx.condicion(0));
        String condicionB = (String) visit(ctx.condicion(1));
        return condicionA + " " + ctx.operador_logico_2_ario().getText() + " " + condicionB;
    }
    public String visitCondicion_cierto (Anasint.Condicion_ciertoContext ctx) {
        return "true";
    }
    public String visitCondicion_falso (Anasint.Condicion_falsoContext ctx) {
        return "false";
    }

    public String visitOp_aritmetica_envuelta (Anasint.Op_aritmetica_envueltaContext ctx) {
        return (String) visit(ctx.operacion());
    }
    public String visitOp_aritmetica_negacion (Anasint.Op_aritmetica_negacionContext ctx) {
        return "-" + visit(ctx.operacion());
    }
    public String visitOp_aritmetica_mult (Anasint.Op_aritmetica_multContext ctx) {
        return visit(ctx.operacion(0)) + "*" + visit(ctx.operacion(1));
    }
    public String visitOp_aritmetica_sr (Anasint.Op_aritmetica_srContext ctx) {
        String operador;
        if (ctx.MAS() != null)
            operador = "+";
        else
            operador = "-";
        return visit(ctx.operacion(0)) +
               operador +
               visit(ctx.operacion(1));
    }

    public String visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return ctx.getText();
    }
    public String visitOperando_variable (Anasint.Operando_variableContext ctx) {
        return (String) visit(ctx.variable());
    }
    public String visitValor_booleano_true (Anasint.Valor_booleano_trueContext ctx) {
        return "true";
    }
    public String visitValor_booleano_false (Anasint.Valor_booleano_falseContext ctx) {
        return "false";
    }
    public String visitCuantificador_universal (Anasint.Cuantificador_universalContext ctx) {
        return "allMatch";
    }
    public String visitCuantificador_existencial (Anasint.Cuantificador_existencialContext ctx) {
        return "anyMatch";
    }

    public String visitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String res = ctx.IDENT().getText() + "(";
        Programa subprograma = programa.getSubprograma(ctx.IDENT().getText());
        int i = 0;
        for (Anasint.Evaluacion_variableContext parametro: ctx.evaluacion_variable()) {
            if (parametro.operando_secuencia() != null) {
                tiposSecuencia.push(subprograma.getTiposEntrada().get(i));
            }
            res += visit(parametro) + ",";
            i++;

        }
        return quitaUltimaComa(res) + ")";
    }
    public String visitSubprograma_ultima_posicion (Anasint.Subprograma_ultima_posicionContext ctx) {
        return ctx.UL_POS().getText() + "(" +
            visit(ctx.evaluacion_variable()) + ")";
    }
    public String visitSubprograma_mostrar (Anasint.Subprograma_mostrarContext ctx) {
        return ctx.MOSTRAR().getText() + "(" +
                visit(ctx.evaluacion_variable()) + ")";
    }
    public String visitSubprograma_vacia (Anasint.Subprograma_vaciaContext ctx) {
        return ctx.VACIA().getText() + "(" +
            visit(ctx.evaluacion_variable()) + ")";
    }

    public String visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        if (variablesBajoCondicion)
            variablesCondicion.add(ctx.getText());
        //if (casteoVariables) {
        //    System.out.println(getUpperScope(ctx).get(ctx.IDENT().getText()));
        //}

        return ctx.IDENT().getText() + "[" + visit(ctx.operacion()) + "]";
    }

    public String visitVariable_simple (Anasint.Variable_simpleContext ctx) {
        if (variablesBajoCondicion)
            variablesCondicion.add(ctx.getText());
        return ctx.getText();
    }

    public String visitEvaluacion_variable(Anasint.Evaluacion_variableContext ctx) {
        System.out.println("[COMPILADOR] visitEvaluacion_variable: " + ctx.getText() + "= " + ctx.getChild(0).getClass().toString());
        return (String) visit(ctx.getChild(0));
    }

    public String visitOperando_subprograma (Anasint.Operando_subprogramaContext ctx) {
        return (String) visit(ctx.subprograma());
    }
/*
    public String visitOperando_secuencia_numerica (Anasint.Operando_secuencia_numericaContext ctx) {
        return "new Integer[]" + ctx.getText().replace("[","{").replace("]", "}");
    }*/



    public String visitOperando_secuencia_llena (Anasint.Operando_secuencia_llenaContext ctx) {
        String res = "new " + tiposSecuencia.pop() + "{";
        //String res = "new Object[]{";
            for (Anasint.OperacionContext elto: ctx.operacion()) {
                res += visit(elto) + ",";
            }
        return quitaUltimaComa(res) + "}";
    }

    public String visitDeclaracion_variable (Anasint.Declaracion_variableContext ctx) {
            String tipo = convierteTipo(ctx.tipo().getText());
            String res = "";
            //Map<String, String> scope = getUpperScope(ctx);
            if (!variablesPrivadas)
                res += "private static ";
            res += tipo + " ";
            for (ParseTree nombreVariable: ctx.IDENT()) {
                res += (nombreVariable + ",");
                programa.declaraVariable(scope, new Variable(nombreVariable.getText(), ctx.tipo().getText()));
                //scope.put(nombreVariable.getText(), convierteTipo(tipo));
            }
            res = quitaUltimaComa(res);
            res += ";\n";
        return res;
    }
}