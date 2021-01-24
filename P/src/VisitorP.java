import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitorP extends AnasintBaseVisitor<Object> {
    InformadorErrores a = new InformadorErrores ("[ERROR SEMANTICO]", ConsoleColors.GREEN_BOLD);
    Programa programa = new Programa("Main", "Main");
    String scope = null;
    ParseTreeProperty<List<Boolean>> retornoTree = new ParseTreeProperty<>();
    ParseTreeProperty<Boolean> rupturaTree = new ParseTreeProperty<>();
    boolean compruebaVariableInicializada = false;
    boolean retornoMultiplePermitido = false;
    boolean instrPosteriorARetorno = false;

    private String getTipoOperacionIgualdad (String a, String b, String operador) {
        String res = "Indefinido";
        if (!a.equals(b) || (a.equals("Indefinido") || b.equals("Indefinido")))
            return res;

        switch (operador) {
            case "==":
            case "!=":
                res = "Boolean";
                break;
            case ">=":
            case ">" :
            case "<=":
            case "<" :
                if (a.equals("Integer"))
                    res = "Boolean";
                else
                    res = "Indefinido";
                break;
            case "&&":
            case "||":
                if (a.equals("Boolean"))
                    res = "Boolean";
                else
                    res = "Indefinido";
                break;
            default:
                res = "Indefinido";
        }
        //System.out.println("[ANALISIS SEMANTICO] getTipoOperacionIgualdad: " + a + " vs " + b+ " res=" + res + " op " + operador);

        return res;
    }

    private boolean anyParentIsAFunction (ParserRuleContext ctx) {
        if (ctx.getParent() != null)
            if (ctx.getParent().getClass().equals(Anasint.Bloque_funcionContext.class))
                return true;
            else
                return anyParentIsAFunction(ctx.getParent());
        else
            return false;
    }
    private ParserRuleContext closestReturnBlock (ParserRuleContext ctx) {
        if (ctx.getClass().equals(Anasint.Bloque_funcionContext.class) ||
                ctx.getClass().equals(Anasint.Instruccion_controlContext.class) ||
                ctx.getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx;
        else
            return closestReturnBlock(ctx.getParent());
    }
    private ParserRuleContext closestBreakBlock (ParserRuleContext ctx) {
        if (//ctx.getParent().getClass().equals(Anasint.Bloque_funcionContext.class) ||
                ctx.getClass().equals(Anasint.Instruccion_controlContext.class) ||
                ctx.getClass().equals(Anasint.Instruccion_bucleContext.class) ||
                ctx.getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx.getParent();
        else
            return closestBreakBlock(ctx.getParent());
    }

    public Object visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        List<Variable> variables = visitBloque_variables(ctx.bloque_variables());

        //todo cambiado
        //Scope scopeGlobal = new Scope("global");
        programa.declaraVariables(variables);

        programa.declaraSubprogramaNativo("vacia", "Funcion");
        programa.declaraSubprogramaNativo("ultima_posicion", "Funcion");
        programa.declaraSubprogramaNativo("mostrar", "Procedimiento");

        //raiz = ctx;
        //scopeTree.put(ctx, scopeGlobal);

        return super.visitBloque_programa(ctx);
    }
    public List<Variable> visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        List<Variable> variables = new ArrayList<>();
        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable())
            variables.addAll(visitDeclaracion_variable(variable));
        return variables;
    }
    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        return super.visitBloque_instrucciones(ctx);
    }
    public Object visitBloque_subprogramas (Anasint.Bloque_subprogramasContext ctx) {
        return super.visitBloque_subprogramas(ctx);
    }
    public Object visitBloque_procedimiento(Anasint.Bloque_procedimientoContext ctx) {
        /*
            En este bloque de instrucciones, añadimos el procedimiento al scope global. Las
            variables de entrada, salida, y las definidas en su bloque VARIABLES son
            añadidas al scope de la función.
         */
        //Scope scope = new Scope(getGlobalScope(), ctx.IDENT().getText());
        //deberiamos declarar las variables del padre tambien
        String nombreProcedimiento = ctx.IDENT().getText();
        scope = nombreProcedimiento;
        Programa subprograma = new Programa(nombreProcedimiento, "Procedimiento");
        //Scope scope = new Scope(ctx.IDENT().getText());
        List<Variable> variablesProcedimiento = visitBloque_variables(ctx.bloque_variables());

        List<Variable> variablesEntrada = visitLista_variables_tipadas(ctx.lista_variables_tipadas());

        for (Variable variableEntrada: variablesEntrada)
            if (variablesProcedimiento.contains(variableEntrada))
                a.informa(" La variable " + variableEntrada.getNombre() +
                        " ya esta definida en el procedimiento " + nombreProcedimiento);
            //else
            //    variableEntrada.setSoloLectura();


        subprograma.declaraVarsInternas(variablesProcedimiento);
        subprograma.declaraVarsEntrada(variablesEntrada);
        //subprograma.inicializaVariables(variablesEntrada);

        //scope.inicializaVariables(salida);

        programa.declaraSubprograma(subprograma);
        //scopeGlobal.declaraFuncion(ctx.IDENT().getText(), entrada, salida);
        //scopeTree.put(ctx, scope);

        /*
            Aquí entramos en el bloque de instrucciones de la función, y comenzamos a
            realizar las diferentes comprobaciones de tipo y existencia en scope.
         */

        visitBloque_variables(ctx.bloque_variables());
        visitBloque_instrucciones(ctx.bloque_instrucciones());


        //comprobamos si algun retorno disponible es válido
        if (retornoTree.get(ctx.bloque_instrucciones()) != null)
            a.informa("El procedimiento no puede tener ningún retorno");
        scope = null;
        return 0;
    }
    public Object visitBloque_funcion(Anasint.Bloque_funcionContext ctx) {
        /*
            En este bloque de instrucciones, añadimos la función al scope global. Las
            variables de entrada, salida, y las definidas en su bloque VARIABLES son
            añadidas al scope de la función.
         */
        String nombreFuncion = ctx.IDENT().getText();
        scope = nombreFuncion;

        Programa subprograma = new Programa(nombreFuncion, "Funcion");
        List<Variable> variablesInternas = visitBloque_variables(ctx.bloque_variables());

        subprograma.declaraVarsInternas(variablesInternas);

        List<Anasint.Lista_variables_tipadasContext> variablesEntradaSalida =  ctx.lista_variables_tipadas();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> salida = new ArrayList<>();

        if (variablesEntradaSalida.size() == 2) {
            entrada.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(1)));
        } else
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));

        if (salida.size() == 0)
            a.informa("Un subprograma debe tener argumentos de salida");

        //las variables de entrada son de solo lectura
        //for (Variable variableEntrada: entrada)
        //    variableEntrada.setSoloLectura();

        for (Variable variable: variablesInternas)
           if (salida.contains(variable) || entrada.contains(variable))
               a.informa( "La variable " + variable.getNombre() +
                       " ya esta definida en la función " + nombreFuncion);

        subprograma.declaraVarsEntrada(entrada);
        subprograma.declaraVarsSalida(salida);


        programa.declaraSubprograma(subprograma);

        //Colocamos un contenedor de retorno a nivel subprograma
        //retornoTree.put(ctx.bloque_instrucciones(), new boolean[2]);

        visitBloque_variables(ctx.bloque_variables());
        visitBloque_instrucciones(ctx.bloque_instrucciones());

        // Añadimos retornoTree
        //comprobamos si algun retorno disponible es válido
        //System.out.println("La salida del arr bool a nivel funcion es " + retornoTree.get(ctx.bloque_instrucciones()).toString());

        if (!programa.existeRetornoValido(scope))
            a.informa("La función no tiene ningún retorno alcanzable");
        scope = null;
        return 0;
    }

    public List<Variable> visitDeclaracion_variable(Anasint.Declaracion_variableContext ctx) {
        List<Variable> res = new ArrayList<>();
        for (TerminalNode variable: ctx.IDENT())
            res.add(new Variable(variable.getText(), ctx.tipo().getText()));
        return res;
    }
    public List<Variable> visitLista_variables_tipadas (Anasint.Lista_variables_tipadasContext ctx) {
        List<Variable> res = new ArrayList<>();
        int i;
        String nombre;
        for (Anasint.TipoContext tipo: ctx.tipo()) {
            i = tipo.getParent().children.indexOf(tipo);
            nombre = tipo.parent.getChild(i + 1).getText();
            res.add(new Variable(nombre, tipo.getText()));
        }
        return res;
    }


    public String visitOperando_secuencia_vacia(Anasint.Operando_secuencia_vaciaContext ctx) {
        return "ArrayList<>";
    }/*
    public String visitOperando_secuencia_llena (Anasint.Operando_secuencia_llenaContext ctx) {
        for (Anasint.OperacionContext operacion: ctx.operacion()) {
            if (!visit(operacion).equals("Integer"))
                return "Indefinido";
        }
        return "ArrayList<Integer>";
    }*/
    public String visitOperando_secuencia_llena (Anasint.Operando_secuencia_llenaContext ctx) {
        List<String> tipos = new ArrayList<>();
        String tipo;
        for (Anasint.OperacionContext elemento: ctx.operacion()) {
            tipo = (String) visit(elemento);
            if (tipo.equals("Indefinido"))
                return "ArrayList<>";
            else
                tipos.add(tipo);
        }
        if (tipos.stream().allMatch(x -> x.equals("Integer")) ||
                tipos.stream().allMatch(x -> x.equals("Boolean")))
            return "ArrayList<" + tipos.get(0) + ">";
        else
            return "Indefinido";
    }

    public Object visitInstruccion (Anasint.InstruccionContext ctx) {
        //System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion " + ctx.getText());

        //comprobar si estamos en funcion
        //si lo estamos y si estamos en bloque
        //instrucciones con ruptura definida, no
        //sin existir un retorno tirar error

        boolean algunPadreFuncion = anyParentIsAFunction(ctx);
        //funcion sin retorno alcanzable debido a ruptura

        if (    algunPadreFuncion &&
                ctx.getParent().getClass().equals(Anasint.Bloque_instruccionesContext.class) &&
                !ctx.getTokens(Anasint.RUPTURA).isEmpty() &&
                //ctx.instruccion_ruptura() != null &&
                !Arrays.asList(retornoTree.get(ctx.getParent())).contains(true))
            a.informa("Funcion con ruptura sin retorno alcanzable");

        //instrucciones despues de ruptura
        if (rupturaTree.get(ctx.getParent()) != null)
            a.informa("No se admiten instrucciones después de una ruptura");

            //throw new IllegalStateException("No se admiten instrucciones después de una ruptura");
        //if (retornoTree.get(ctx) != null && retornoTree.get(ctx).size() > 0) {
        if (instrPosteriorARetorno) {
            a.informa("No se admiten instrucciones después de un retorno (" + ctx.getText() + ")");
            return null;
        }

        return super.visit(ctx);
    }
    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        //acceder a global
        //Scope scope = getUpperScope(ctx);
        List<Anasint.VariableContext> expresionIzquierda = ctx.lista_variables().variable();
        Anasint.Evaluaciones_variablesContext expresionDerecha = ctx.evaluaciones_variables();
        String nombreSubprograma;
        List<String> arrTiposIzq = new ArrayList<>();
        List<String> arrTiposDer = new ArrayList<>();
        // cargamos en arrTiposIzq los tipos de las variables a asignar
        compruebaVariableInicializada = false;
        for (Anasint.VariableContext variableIzquierda: expresionIzquierda) {
            String tipoVariable = (String) visit(variableIzquierda);
            if (programa.existeVariable(variableIzquierda.getChild(0).getText()) && programa.getVariable(scope, variableIzquierda.getChild(0).getText()).isSoloLectura())
                a.informa("Se ha intentado asignar una variable de solo lectura (" + ctx.getText() + ")");
            arrTiposIzq.add(tipoVariable);
        }

        //comprobamos que las posibles variables usadas en expresiones
        //de asignacion esten inicializadas previamente
        compruebaVariableInicializada = true;
        // cargamos en arrTiposDer los tipos de las expresiones
        for (Anasint.Evaluacion_variableContext varDerecha: expresionDerecha.evaluacion_variable()) {
            //.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_asig: ESTAMOS ASIGNANDO " + varDerecha.getText() + " clase " + varDerecha.getClass());
            if (varDerecha.subprograma() != null && varDerecha.subprograma().getClass().equals(Anasint.Subprograma_declaradoContext.class)) {
                retornoMultiplePermitido = true;
                visitEvaluacion_variable(varDerecha);
                retornoMultiplePermitido = false;
                nombreSubprograma = varDerecha.subprograma().getChild(0).getText();
                if (!programa.existeSubprograma(nombreSubprograma))
                    a.informa("Subprograma no definido! " + nombreSubprograma);
                if (programa.existeProcedimiento(nombreSubprograma))
                    a.informa("Los procedimientos no pueden ser usados en asignaciones");
                else if (programa.existeFuncion(nombreSubprograma))
                    arrTiposDer.addAll(programa.getSubprograma(nombreSubprograma).getTiposSalida());
            } else {
                arrTiposDer.add(visitEvaluacion_variable(varDerecha));
            }
        }

        if (arrTiposIzq.size() != arrTiposDer.size())
            a.informa("Las asignaciones no tienen el mismo numero de elementos");

        //Comprobación del tipo de la expresión y su variable asociada
        for (int i = 0; i < arrTiposIzq.size(); i++) {
            //System.out.println(arrTiposIzq.size() + " " + arrTiposIzq.get(i));
            if (arrTiposIzq.get(i).contains("ArrayList") && arrTiposDer.get(i).equals("ArrayList<>")) {
                a.informa("Asignando secuencia vacia (" + ctx.getText() + ")");
            } else if (!arrTiposIzq.get(i).equals(arrTiposDer.get(i))) {
                a.informa("Asignación no valida... Izq: " + arrTiposIzq.toString() +
                        " = Der: " + arrTiposDer.toString() + " (" + ctx.getText() + ")");
            }
        }

        //Asignacion paralela
        compruebaVariableInicializada = false;
        for (Anasint.VariableContext variableIzquierda: expresionIzquierda)
            programa.inicializaVariable(scope, variableIzquierda.getChild(0).getText());
        compruebaVariableInicializada = true;
        return 1;
    }
    public String visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        boolean algunPadreFuncion = programa.esFuncion(scope);
        ParserRuleContext bloquePadreAsociadoARetorno = closestReturnBlock(ctx);

        if (!algunPadreFuncion) {
            a.informa("Retorno solo válido dentro de función");
            return null;
        }

        //Scope scope = getUpperScope(ctx);
        List<String> tiposSalidaDefinidos = programa.getSubprograma(scope).getTiposSalida();
        List<String> tiposSalidaPrograma = new ArrayList<>();
        //tenemos que comprobar en el retorno que devolvemos lo declarado
        for (Anasint.Evaluacion_variableContext variablesRetorno: ctx.evaluaciones_variables().evaluacion_variable()) {
            tiposSalidaPrograma.add(visitEvaluacion_variable(variablesRetorno));
        }

        if (!tiposSalidaDefinidos.equals(tiposSalidaPrograma))
            a.informa("Instruccion retorno inválida (no devuelve los tipos especificados)");

        //System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: Estamos en la funcion" + scope +
        //        " obtenidos " + tiposSalidaPrograma);

        ParserRuleContext bloqueAsociadoARetorno = closestReturnBlock(ctx);

        if (bloquePadreAsociadoARetorno.getClass().equals(Anasint.Bloque_instruccionesContext.class)) //retorno valido solo si 2 cond
            programa.addRetorno(scope, true);
        else
            if (retornoTree.get(bloqueAsociadoARetorno).size() == 2)
                a.informa("Instruccion de retorno posterior a una anterior.");
            else
                retornoTree.get(bloquePadreAsociadoARetorno).add(true);
        //System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: El arr bool para "+  ctx.getParent().getText() + " es " + retornoTree.get(bloqueAsociadoARetorno).toString());
        instrPosteriorARetorno = true;
        return null;
    }
    public Object visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        ParserRuleContext closest = closestBreakBlock(ctx);
        //System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: Hemos puesto ruptura en " + closest.getClass());
        rupturaTree.put(closestBreakBlock(ctx), true);
        return super.visitInstruccion_ruptura(ctx);
    }
    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        String resultado = (String) visit(ctx.predicado());
        //boolean algunPadreFuncion = anyParentIsAFunction(ctx);
        boolean algunPadreFuncion = programa.esFuncion(scope);

        //Comprobamos que algun nodo padre es una funcion. Evaluamos retorno si lo tiene.
        if (algunPadreFuncion)
            retornoTree.put(ctx, new ArrayList<>());

        //predicado cierto, falso o indefinido, no numero
        if (resultado.equals("Integer"))
            a.informa("Predicado no booleano/indefinido " + ctx.predicado().getText());
            //System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_control: PREDICADO NO BOOLEANO/INDEFINIDO " + ctx.predicado().getText());
            //throw new IllegalStateException("PREDICADO NO BOOLEANO " + ctx.predicado().getText());

        //actualizamos el indice del array de retorno
        for (ParseTree hijo: ctx.children) {

            if (hijo.equals(ctx.SINO())) {
                instrPosteriorARetorno = false;
            }
            if (Anasint.InstruccionContext.class.isAssignableFrom(hijo.getClass())) {
                visitInstruccion((Anasint.InstruccionContext) hijo);
            }
        }
        /*
        for (Anasint.InstruccionContext instruccion: ctx.instruccion())
            visit(instruccion);
        */
        //System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_control: " + ctx.getText() + " lista de retornos: "+  retornoTree.get(ctx));

        //aqui ya se han procesado todas las instrucciones, analizamos retornos
        //solo si se trata de una funcion, claro, y de si existen retornos dentro
        ParserRuleContext bloquePadreAsociadoARetorno = closestReturnBlock(ctx.getParent());
        //añadimos si los retornos de esta instr. control son validos al padre
        //si no validos, false. si validos, true

        List<Boolean> retornosNodo = retornoTree.get(ctx);
        if (retornosNodo == null) return 0;
        boolean esRetornoValido = retornosNodo.stream().allMatch(i -> i != null && i == true) && retornosNodo.size() == 2;
        if (bloquePadreAsociadoARetorno.getClass().equals(Anasint.Bloque_instruccionesContext.class)) //retorno valido solo si 2 cond
            programa.addRetorno(scope, esRetornoValido);
        else
            retornoTree.get(bloquePadreAsociadoARetorno).add(esRetornoValido);
            /*
        if (algunPadreFuncion && retornoTree.get(ctx) != null) {
            if (!Arrays.asList(retornoTree.get(ctx)).contains(false) && retornoTree.get(ctx).length() == 2)
                retornoTree.get(bloquePadreAsociadoARetorno)[indiceRetorno] = true;
            else
                retornoTree.get(bloquePadreAsociadoARetorno)[indiceRetorno] = false;
        }*/
        instrPosteriorARetorno = false;

        return 0;
    }
    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        String resultado = (String) visit(ctx.predicado());
        if (resultado.equals("Integer"))
            a.informa("Pr " + ctx.predicado().getText());
        if (ctx.subprograma() != null) {
            //Scope scope = getUpperScope(ctx);
            //Subprograma subprograma = scope.getSubprograma(ctx.subprograma().getChild(0).getText());
            Programa subprograma = programa.getSubprograma(scope);
            if (subprograma == null)
                a.informa("la función de avance no existe");
            else {
                if (!subprograma.esArgumento())
                    a.informa("la función de avance no es válida como argumento (devuelve más de 1 parámetro)");
                if (!subprograma.getTiposSalida().get(0).equals("Integer"))
                    a.informa("la función de avance no es válida, no devuelve un número");
            }
        }
        return super.visitInstruccion_bucle(ctx);
    }
    public Object visitInstruccion_aserto_simple (Anasint.Instruccion_aserto_simpleContext ctx) {
        String resultado = (String) visit(ctx.predicado());
        if (resultado.equals("Integer"))
            a.informa("Predicado no booleano/indefinido " + ctx.predicado().getText());
        return null;
    }
    public Object visitInstruccion_aserto_cuantificado (Anasint.Instruccion_aserto_cuantificadoContext ctx) {
        //se declara un tipo de variable??
        Variable varTmpAserto = new Variable(ctx.variable().getText(), "NUM");
        programa.declaraVariable(scope, varTmpAserto);
        String resultado = (String) visit(ctx.predicado());

        if (resultado.equals("Integer"))
            a.informa("Predicado " + ctx.predicado().getText());
        String operacionA = (String) visit(ctx.operacion(0));
        String operacionB = (String) visit(ctx.operacion(1));

        if (!operacionA.equals("Integer") || !operacionB.equals("Integer"))
            a.informa("Predicado no booleano ni indefinido " + ctx.predicado().getText());
        programa.eliminaVariable(scope, varTmpAserto);
        return null;
    }
    public Object visitInstruccion_llamada_subprograma (Anasint.Instruccion_llamada_subprogramaContext ctx) {
        retornoMultiplePermitido = true;
        visit(ctx.subprograma());
        retornoMultiplePermitido = false;
        return 0;
    }
    public String visitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String tipoArgumento;
        int indexArgumento = 0;
        String tipoRes = "Indefinido";
        String nombreSubprograma = ctx.IDENT().getText();
        //System.out.println("[ANALIZADOR SEMANTICO] visitSubprograma_declarado: " + ctx.IDENT().getText());
        //comprobamos la existencia del subprograma

        if (!programa.existeSubprograma(nombreSubprograma)) {
            a.informa("El subprograma no está definido");
            return tipoRes;
        }

        Programa subprograma = programa.getSubprograma(nombreSubprograma);
        boolean esArgumento = subprograma.esArgumento();
        if (subprograma.getVarsEntrada().size() != ctx.evaluacion_variable().size())
            a.informa("El número de argumentos del subprograma " + nombreSubprograma + " no es correcto, esperando " + subprograma.getVarsEntrada().size() + ", obtenemos " +  ctx.evaluacion_variable().size() + " " + ctx.getText());
        else if (!subprograma.esArgumento() && !retornoMultiplePermitido)
            a.informa("No se permiten funciones de retorno multiple fuera de una asignacion (" + nombreSubprograma + ")");
        //comprobamos si los tipos de entrada son los adecuados
        retornoMultiplePermitido = false;
        for (Anasint.Evaluacion_variableContext variable: ctx.evaluacion_variable()) {
            tipoArgumento = visitEvaluacion_variable(variable);
            //comprobamos los tipos de argumentos recibidos con los del programa declarado
            if (!tipoArgumento.equals(subprograma.getTiposEntrada().get(indexArgumento)))
                a.informa("Tipo arg inválido orig: " +
                        subprograma.getTiposEntrada().get(indexArgumento) + " , recib: " + tipoArgumento + " (" + ctx.getText() + ")");

            System.out.println("[ANALIZADOR SEMANTICO] visitSubprograma_declarado:" + variable.getText());
            indexArgumento++;
        }

        if (subprograma.esArgumento())
            tipoRes = subprograma.getVarsSalida().get(0).getTipo();

      return tipoRes;
    }

    public String visitPredicado_base (Anasint.Predicado_baseContext ctx) {
        return (String) visit(ctx.condicion());
    }
    public String visitPredicado_negado (Anasint.Predicado_negadoContext ctx) {
        return (String) visit(ctx.predicado());
    }
    public String visitPredicado_envuelto (Anasint.Predicado_envueltoContext ctx) {
        return (String) visit(ctx.predicado());
    }
    public String visitPredicado_rec(Anasint.Predicado_recContext ctx) {
        String tipoCondicionA = (String) visit(ctx.predicado(0));
        String tipoCondicionB = (String) visit(ctx.predicado(1));
        return getTipoOperacionIgualdad(
                tipoCondicionA,
                tipoCondicionB,
                ctx.operador_condicion_2_ario().getText());
    }

    public String visitCondicion_base (Anasint.Condicion_baseContext ctx) {
        return (String) visit(ctx.getChild(0));
    }
    public String visitCondicion_envuelta (Anasint.Condicion_envueltaContext ctx) {
        return (String) visit(ctx.condicion());
    }
    public String visitCondicion_cierto (Anasint.Condicion_ciertoContext ctx) {
        return "Boolean";
    }
    public String visitCondicion_falso (Anasint.Condicion_falsoContext ctx) {
        return "Boolean";
    }
    public String visitCondicion_rec (Anasint.Condicion_recContext ctx) {
        return getTipoOperacionIgualdad(
                (String) visit(ctx.condicion(0)),
                (String) visit(ctx.condicion(1)),
                ctx.operador_logico_2_ario().getText());
    }

    public String visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        //System.out.println("[ANALIZADOR SEMANTICO] visitEvaluacion_variable: " + ctx.getText());
        return (String) super.visitEvaluacion_variable(ctx);
    }

    public String visitOp_aritmetica_envuelta (Anasint.Op_aritmetica_envueltaContext ctx) {
        return (String) visit(ctx.operacion());
    }

    public String visitOp_aritmetica_mult (Anasint.Op_aritmetica_multContext ctx) {
        String operacionA = (String) visit(ctx.operacion(0));
        String operacionB = (String) visit(ctx.operacion(1));
        if (operacionA.equals(operacionB) && !operacionA.equals("Indefinido"))
            return "Integer";
        else
            return "Indefinido";
    }

    public String visitOp_aritmetica_sr (Anasint.Op_aritmetica_srContext ctx) {
        String operacionA = (String) visit(ctx.operacion(0));
        String operacionB = (String) visit(ctx.operacion(1));
        if (operacionA.equals(operacionB) && !operacionA.equals("Indefinido"))
            return "Integer";
        else
            return "Indefinido";
    }

    public String visitValor_booleano_true (Anasint.Valor_booleano_trueContext ctx) {
        return "Boolean";
    }
    public String visitValor_booleano_false (Anasint.Valor_booleano_falseContext ctx) {
        return "Boolean";
    }
    public String visitCuantificador_universal (Anasint.Cuantificador_universalContext ctx) {
        return "Boolean";
    }
    public String visitCuantificador_existencial (Anasint.Cuantificador_existencialContext ctx) {
        return "Boolean";
    }
    public String visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return "Integer";
    }
    /*public String visitOperando_booleano (Anasint.Operando_booleanoContext ctx) {
        return "Boolean";
    }*/

    public String visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        if (!programa.existeVariable(scope, ctx.IDENT().getText())) {
            a.informa("Variable " + ctx.getText() + " no declarada");
            return "Indefinido";
        }
        String tipoVariable = programa.getVariable(scope, ctx.IDENT().getText()).getTipo();
        String tipoAcceso = (String) visit(ctx.operacion());

        if (!programa.getVariable(scope, ctx.IDENT().getText()).isInicializada() &&
                compruebaVariableInicializada)
            a.informa("Variable " + ctx.IDENT().getText() + " no inicializada, no se puede usar en una expresión");
        else if (!tipoAcceso.equals("Integer")) {
            a.informa("Indice de acceso a secuencia no numérico");
        }
        else if (!tipoVariable.contains("ArrayList")) {
            a.informa("La variable " + ctx.IDENT().getText() + " no es de tipo secuencia");
        }

        return tipoAcceso.replace("ArrayList<", "").replace(">", "");
    }

    public String visitVariable_simple (Anasint.Variable_simpleContext ctx) {
        String res = "Indefinido";
        //System.out.println("[ANALIZADOR SEMANTICO] visitVariable: " + ctx.getText() + " on scope ");
        //System.out.println("PARENT=?=?" + ctx.getParent().getClass() + " to " + Anasint.Operando_universalContext.class);
        String txt = ctx.getText();

        if (!programa.existeVariable(scope, ctx.IDENT().getText()))
            a.informa("Variable " + ctx.getText() + " no declarada");

        //if (variablesAInicializar.contains(ctx.getText()))
        //    throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitVariable Variable " + ctx.getText() + " no inicializada, no se puede asignar a si misma");
        else if (!programa.getVariable(scope, ctx.getText()).isInicializada() &&
                compruebaVariableInicializada)
            a.informa("Variable " + ctx.getText() + " no inicializada, no se puede usar en una expresión");
        else
            res = programa.getVariable(scope, ctx.getText()).getTipo();
        return res;
    }

    public String visitSubprograma_ultima_posicion(Anasint.Subprograma_ultima_posicionContext ctx) {
        String tipoArgumento = (String) visit(ctx.evaluacion_variable());
        if (tipoArgumento.equals("Indefinido") ||
                (!tipoArgumento.contains("Boolean") &&
                !tipoArgumento.contains("Integer")))
            a.informa("Ultima_posicion_variable no se admiten secuencias sin tipo (" + tipoArgumento + ")");

        if (!tipoArgumento.contains("ArrayList"))
            a.informa("Ultima_posicion_variable  ultima_posicion solo válido en secuencias");

        return "Integer";
    }

    public String visitSubprograma_vacia (Anasint.Subprograma_vaciaContext ctx) {
        //System.out.println("visitVacia " + ctx.getText());
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (!tipoArgumento.contains("ArrayList"))
            a.informa("Operador vacia solo válido en secuencias");
        return "Boolean";
    }

    public String visitSubprograma_mostrar (Anasint.Subprograma_mostrarContext ctx) {
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (ctx.getParent().getClass().equals(Anasint.Evaluacion_variableContext.class))
            a.informa("Procedimiento mostrar no válido como evaluación");
        if (!tipoArgumento.contains("ArrayList"))
            a.informa("Operador mostrar solo válido en secuencias");
        return null;
    }

}