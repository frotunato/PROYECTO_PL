import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class VisitorP extends AnasintBaseVisitor<Object> {
    Anasint.Bloque_programaContext raiz;
    ParseTreeProperty<Scope> scopeTree = new ParseTreeProperty<>();
    ParseTreeProperty<List<Boolean>> retornoTree = new ParseTreeProperty<>();
    ParseTreeProperty<Boolean> rupturaTree = new ParseTreeProperty<>();

    private Scope getGlobalScope () {
        return scopeTree.get(raiz);
    }
    private Scope getUpperScope (ParserRuleContext ctx) {
        if (scopeTree.get(ctx) != null)
            return scopeTree.get(ctx);
        else
            return getUpperScope(ctx.getParent());
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
        if (ctx.getParent().getClass().equals(Anasint.Bloque_funcionContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Instruccion_controlContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx.getParent();
        else
            return closestReturnBlock(ctx.getParent());
    }
    private ParserRuleContext closestBreakBlock (ParserRuleContext ctx) {
        if (//ctx.getParent().getClass().equals(Anasint.Bloque_funcionContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Instruccion_controlContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Instruccion_bucleContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx.getParent();
        else
            return closestBreakBlock(ctx.getParent());
    }

    public Object visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        List<Variable> variables = visitBloque_variables(ctx.bloque_variables());
        Scope scopeGlobal = new Scope("global");
        scopeGlobal.declaraVariables(variables);

        scopeGlobal.declaraSubprogramaNativo("vacia", "Funcion");
        scopeGlobal.declaraSubprogramaNativo("ultima_posicion", "Funcion");
        scopeGlobal.declaraSubprogramaNativo("mostrar", "Procedimieneto");

        raiz = ctx;
        scopeTree.put(ctx, scopeGlobal);

        return super.visitBloque_programa(ctx);
    }
    public List<Variable> visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        List<Variable> variables = new ArrayList<>();
        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable())
            variables.addAll(visitDeclaracion_variable(variable));
        return variables;
    }
    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        //igual hay que descomentar esto??
        //retornoTree.put(ctx, new ArrayList<>());
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
        Scope scope = new Scope(ctx.IDENT().getText());
        List<Variable> variablesProcedimiento = visitBloque_variables(ctx.bloque_variables());

        List<Variable> variablesEntrada = visitLista_variables_tipadas(ctx.lista_variables_tipadas());

        for (Variable variableEntrada: variablesEntrada)
            variableEntrada.setSoloLectura();

        scope.declaraVariables(variablesProcedimiento);
        scope.declaraVariables(variablesEntrada);
        scope.inicializaVariables(variablesEntrada);
        //scope.inicializaVariables(salida);

        getGlobalScope().declaraSubprograma(ctx.IDENT().getText(), "Procedimiento", variablesEntrada, new ArrayList<>());
        //scopeGlobal.declaraFuncion(ctx.IDENT().getText(), entrada, salida);
        scopeTree.put(ctx, scope);

        /*
            Aquí entramos en el bloque de instrucciones de la función, y comenzamos a
            realizar las diferentes comprobaciones de tipo y existencia en scope.
         */

        visitBloque_variables(ctx.bloque_variables());
        visitBloque_instrucciones(ctx.bloque_instrucciones());

        // Añadimos retornoTree

        //comprobamos si algun retorno disponible es válido
        if (retornoTree.get(ctx.bloque_instrucciones()) != null)
            throw new IllegalArgumentException("El procedimiento no puede tener ningún retorno");

        return 0;
    }
    public Object visitBloque_funcion(Anasint.Bloque_funcionContext ctx) {
        /*
            En este bloque de instrucciones, añadimos la función al scope global. Las
            variables de entrada, salida, y las definidas en su bloque VARIABLES son
            añadidas al scope de la función.
         */
        //Scope scope = new Scope(getGlobalScope(), ctx.IDENT().getText());
        Scope scope = new Scope(ctx.IDENT().getText());
        List<Variable> variables = visitBloque_variables(ctx.bloque_variables());

        scope.declaraVariables(variables);

        List<Anasint.Lista_variables_tipadasContext> variablesEntradaSalida =  ctx.lista_variables_tipadas();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> salida = new ArrayList<>();

        if (variablesEntradaSalida.size() == 2) {
            entrada.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(1)));
        } else
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));

        //las variables de entrada son de solo lectura
        for (Variable variableEntrada: entrada)
            variableEntrada.setSoloLectura();

        scope.declaraVariables(entrada);
        scope.declaraVariables(salida);
        scope.inicializaVariables(entrada);
        //scope.inicializaVariables(salida);

        getGlobalScope().declaraSubprograma(ctx.IDENT().getText(), "Funcion", entrada, salida);
        //scopeGlobal.declaraFuncion(ctx.IDENT().getText(), entrada, salida);
        scopeTree.put(ctx, scope);

        /*
            Aquí entramos en el bloque de instrucciones de la función, y comenzamos a
            realizar las diferentes comprobaciones de tipo y existencia en scope.
         */
        retornoTree.put(ctx.bloque_instrucciones(), new ArrayList<>());

        visitBloque_variables(ctx.bloque_variables());
        visitBloque_instrucciones(ctx.bloque_instrucciones());

        // Añadimos retornoTree
        //comprobamos si algun retorno disponible es válido
        System.out.println("La salida del arr bool a nivel funcion es " + retornoTree.get(ctx.bloque_instrucciones()).toString());

        if (!retornoTree.get(ctx.bloque_instrucciones()).contains(true))
            throw new IllegalArgumentException("La función no tiene ningún retorno alcanzable");

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
        /*
        for (Anasint.Variable_tipadaContext varTipada: ctx.variable_tipada())
            res.add(new Variable(varTipada.IDENT().getText(), varTipada.tipo().getText()));
        */
        //scope.declaraVariable(varTipada.IDENT().getText(), varTipada.tipo().getText());
        return res;
    }


    public String visitOperando_secuencia_vacia(Anasint.Operando_secuencia_vaciaContext ctx) {
        return "ArrayList<>";
    }
    public String visitOperando_secuencia_numerica (Anasint.Operando_secuencia_numericaContext ctx) {
        return "ArrayList<Integer>";
    }
    public String visitOperando_secuencia_logica (Anasint.Operando_secuencia_logicaContext ctx) {
        return "ArrayList<Boolean>";
    }

    public Object visitInstruccion (Anasint.InstruccionContext ctx) {
        System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion " + ctx.getText());

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
                !retornoTree.get(ctx.getParent()).contains(true))
            throw new IllegalStateException("Funcion con ruptura sin retorno alcanzable");

        //instrucciones despues de ruptura
        if (rupturaTree.get(ctx.getParent()) != null)
            System.out.println("No se admiten instrucciones después de una ruptura");

            //throw new IllegalStateException("No se admiten instrucciones después de una ruptura");
        if (retornoTree.get(ctx.getParent()) != null && retornoTree.get(ctx.getParent()).size() == 2)
            throw new IllegalStateException("No se admiten instrucciones después de un retorno");

        return super.visit(ctx);
    }
    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        Scope scope = getUpperScope(ctx);
        List<Anasint.VariableContext> expresionIzquierda = ctx.lista_variables().variable();
        Anasint.Evaluaciones_variablesContext expresionDerecha = ctx.evaluaciones_variables();
        String nombreSubprograma;
        List<String> arrTiposIzq = new ArrayList<>();
        List<String> arrTiposDer = new ArrayList<>();

        // cargamos en arrTiposIzq los tipos de las variables a asignar
        for (Anasint.VariableContext variableIzquierda: expresionIzquierda) {
            visitVariable(variableIzquierda);
            if (scope.getVariable(variableIzquierda.getText()).isSoloLectura()) {
                throw new IllegalArgumentException("Se ha intentado asignar una variable de solo lectura");
            }
            arrTiposIzq.add(scope.getVariable(variableIzquierda.getText()).getTipo());
        }

        // cargamos en arrTiposDer los tipos de las expresiones
        for (Anasint.Evaluacion_variableContext varDerecha: expresionDerecha.evaluacion_variable()) {
            System.out.println("ESTAMOS ASIGNANDO " + varDerecha.getText() + " clase " + varDerecha.getClass());
            if (varDerecha.subprograma() != null && varDerecha.subprograma().getClass().equals(Anasint.Subprograma_declaradoContext.class)) {
                nombreSubprograma = varDerecha.subprograma().getChild(0).getText();
                if (!getGlobalScope().existeSubprograma(nombreSubprograma))
                    throw new IllegalStateException("Subprograma no definido! " + nombreSubprograma);
                if (getGlobalScope().existeProcedimiento(nombreSubprograma))
                    throw new IllegalArgumentException("Los procedimientos no pueden ser usados en asignaciones");
                else if (getGlobalScope().existeFuncion(nombreSubprograma))
                    arrTiposDer.addAll(getGlobalScope().getSubprograma(nombreSubprograma).getTiposSalida());
                //System.out.println("ACABAMOS DE AÑADIR " + arrTiposDer.get(arrTiposDer.size() - 1));
            } else {
                arrTiposDer.add(visitEvaluacion_variable(varDerecha));
                //System.out.println("ACABAMOS DE AÑADIR " + arrTiposDer.get(arrTiposDer.size() - 1));
            }
        }

        if (arrTiposIzq.size() != arrTiposDer.size())
            throw new IllegalStateException("Las asignaciones no tienen el mismo numero de elementos");

        //Comprobación del tipo de la expresión y su variable asociada
        for (int i = 0; i < arrTiposIzq.size(); i++) {
            System.out.println(arrTiposIzq.size() + " " + arrTiposIzq.get(i));
            if (arrTiposIzq.get(i).contains("ArrayList") && arrTiposDer.get(i).equals("ArrayList<>")) {
                System.out.println("Lista tipada vs generica ");
            } else if (!arrTiposIzq.get(i).equals(arrTiposDer.get(i))) {
                throw new IllegalStateException("Asignación no valida... Izq: " + arrTiposIzq.toString() +
                        " vs Der: " + arrTiposDer.toString());
            }
        }

        //Asignacion paralela
        for (Anasint.VariableContext variableIzquierda: expresionIzquierda)
            scope.inicializaVariable(variableIzquierda.getText());


        return 1;
        //return super.visitInstruccion_asig(ctx);
    }
    public String visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        boolean algunPadreFuncion = anyParentIsAFunction(ctx);
        if (!algunPadreFuncion)
            throw new IllegalStateException("Retorno solo válido dentro de función");

        Scope scope = getUpperScope(ctx);
        List<String> tiposSalidaDefinidos = getGlobalScope().getSubprograma(scope.getNombre()).getTiposSalida();
        List<String> tiposSalidaPrograma = new ArrayList<>();

        for (Anasint.Evaluacion_variableContext variablesRetorno: ctx.evaluaciones_variables().evaluacion_variable()) {
            tiposSalidaPrograma.add(visitEvaluacion_variable(variablesRetorno));
        }

        if (!tiposSalidaDefinidos.equals(tiposSalidaPrograma))
            throw new IllegalStateException("Instruccion retorno inválida (no devuelve los tipos especificados)");

        System.out.println("Estamos en la funcion" + scope.getNombre() +
                " obtenidos " + tiposSalidaPrograma);

        ParserRuleContext bloqueAsociadoARetorno = closestReturnBlock(ctx);
        //List<String> tiposParametrosRetorno = new ArrayList<>();
        if (retornoTree.get(bloqueAsociadoARetorno) == null) {
            retornoTree.put(bloqueAsociadoARetorno, new ArrayList<>());
            System.out.println("El padre no tiene un arr bool para guardar ret " + bloqueAsociadoARetorno.getClass() + " instr " + ctx.getText());
        } else
            System.out.println("El padre tiene un arr bool para guardar retornos, " + retornoTree.get(bloqueAsociadoARetorno).toString());
        retornoTree.get(bloqueAsociadoARetorno).add(true);
        System.out.println("El arr bool para "+  ctx.getParent().getText() + " es " + retornoTree.get(bloqueAsociadoARetorno).toString());
        return "True";
    }
    public Object visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        ParserRuleContext closest = closestBreakBlock(ctx);
        System.out.println("[ANALIZADOR SEMANTICO] Hemos puesto ruptura en " + closest.getClass());
        rupturaTree.put(closestBreakBlock(ctx), true);
        return super.visitInstruccion_ruptura(ctx);
    }
    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        String resultado = (String) visit(ctx.predicado());
        boolean algunPadreFuncion = anyParentIsAFunction(ctx);
        ParserRuleContext bloqueAsociadoARetorno = closestReturnBlock(ctx);

        //Comprobamos que algun nodo padre es una funcion. Evaluamos retorno si lo tiene.
        if (algunPadreFuncion)
            retornoTree.put(bloqueAsociadoARetorno, new ArrayList<>());

        System.out.println("visitInstruccion_control " + ctx.getText() + " Is any parent a function?" + algunPadreFuncion);

        if (!resultado.equals("Boolean"))
            System.out.println("visitInstruccion_control PREDICADO NO BOOLEANO " + ctx.predicado().getText());
            //throw new IllegalStateException("PREDICADO NO BOOLEANO " + ctx.predicado().getText());

        // si comento esto, no se mete por donde deberia!
        // if, visitar to do y analizar. no al reves
        for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
            visitInstruccion(instruccion);
        } //he comentado esto y el retorno a 0, igual no es lo suyo

        //aqui ya se han procesado todas las instrucciones, analizamos retornos
        System.out.println("RETORNO MAESTRO " + ctx.getText() + " ret "+  retornoTree.get(ctx) + "algun padre funcion " + algunPadreFuncion);

        if (algunPadreFuncion && retornoTree.get(ctx) != null) {
            if (!retornoTree.get(ctx).contains(false) && retornoTree.get(ctx).size() == 2)
                retornoTree.get(bloqueAsociadoARetorno).add(true);
            else
                retornoTree.get(bloqueAsociadoARetorno).add(false);
        }
        //le pasa lo mismo, visitariamos 2 veces
        return 0;
        //return super.visitInstruccion_control(ctx);
    }
    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        String resultado = (String) visit(ctx.predicado());

        if (ctx.subprograma() != null && !getGlobalScope().existeFuncion(ctx.subprograma().getChild(0).getText()))
            throw new IllegalStateException("El subprograma de avance debe ser una función");
        if (!resultado.equals("Boolean"))
            throw new IllegalStateException("PREDICADO NO BOOLEANO " + ctx.predicado().getText());

        return super.visitInstruccion_bucle(ctx);
    }
    public Object visitInstruccionAserto (Anasint.Instruccion_asertoContext ctx) {
        return super.visitInstruccion_aserto(ctx);
    }


    public String visitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String tipoArgumento;
        int indexArgumento = 0;
        String tipoRes = "Indefinido";
        String nombreSubprograma = ctx.IDENT().getText();
        System.out.println("visitSubprograma " + ctx.IDENT().getText());
        //comprobamos la existencia del subprograma
        if (!getGlobalScope().existeSubprograma(nombreSubprograma))
            throw new IllegalArgumentException("El subprograma no está definido");

        Subprograma subprograma = getGlobalScope().getSubprograma(nombreSubprograma);

        if (subprograma.getEntrada().size() != ctx.evaluacion_variable().size())
            throw new IllegalArgumentException("El número de argumentos del subprograma " + ctx.IDENT().getText() + " no es correcto, esperando " + subprograma.getEntrada().size() + ", obtenemos " +  ctx.evaluacion_variable().size() + " " + ctx.getText());

        //comprobamos si los tipos de entrada son los adecuados
        for (Anasint.Evaluacion_variableContext variable: ctx.evaluacion_variable()) {
            tipoArgumento = visitEvaluacion_variable(variable);

            if (!tipoArgumento.equals(subprograma.getEntrada().get(indexArgumento).getTipo()))
                throw new IllegalStateException("visitSubprograma tipo arg inválido orig: " +
                        subprograma.getEntrada().get(indexArgumento).getTipo() + " , recib: " + tipoArgumento);

            if (variable.subprograma() != null &&
                    //scopeGlobal.getSubprograma(variable.subprograma().IDENT().getText()).esFuncion() &&
                    !getGlobalScope().getSubprograma(variable.subprograma().getChild(0).getText()).esArgumento())
                throw new IllegalStateException("visitSubprograma argumento parametro funcion DEV > 1 como parametro");
            System.out.println("visitSubprograma argumento " + variable.getText());
            indexArgumento++;
        }

        if (subprograma.getTipo().equals("Funcion")) {
            if (!subprograma.esArgumento() &&
                    ctx.getParent().getClass().equals(Anasint.Instruccion_bucleContext.class))
                throw new IllegalStateException("La función de avance solo puede tener un parámetro de retorno");
            tipoRes = subprograma.getSalida().get(0).getTipo();
        }

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

    private String getTipoOperacionIgualdad (String a, String b, String operador) {
        String res = "Indefinido";

        if (!a.equals(b) || a.equals("Indefinido") || b.equals("Indefinido"))
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
        System.out.println("[ANALISIS SEMANTICO] getTipoOperacionIgualdad: " + a + " vs " + b+ " res=" + res + " op " + operador);

        return res;
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
        if (ctx.subprograma() != null &&
                getGlobalScope().existeFuncion(ctx.subprograma().getChild(0).getText()) &&
                !getGlobalScope().getSubprograma(ctx.subprograma().getChild(0).getText()).esArgumento() && //piede fallar, quitar !
                !ctx.getParent().getClass().equals(Anasint.Evaluaciones_variablesContext.class))
                throw new IllegalStateException("Variables retorno multiple solo en definicion");

        return (String) visit(ctx.getChild(0));
        //return super.visitEvaluacion_variable(ctx).toString();
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
    public String visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return "Integer";
    }

    public String visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        String tipoVariable = (String) visit(ctx.variable());
        String tipoAcceso = (String) visit(ctx.operacion());
        if (!tipoAcceso.equals("Integer")) {
            throw new IllegalArgumentException("Indice de acceso a secuencia no numérico");
        }
        else if (!tipoVariable.contains("ArrayList")) {
            throw new IllegalStateException("La variable " + ctx.variable().IDENT() + " no es de tipo secuencia");
        }
        System.out.println("Visiting variable acceso " + ctx.getText() + " tipo " + tipoVariable.replace("ArrayList<", "").replace(">", ""));
        return tipoVariable.replace("ArrayList<", "").replace(">", "");
    }

    public String visitVariable (Anasint.VariableContext ctx) {
        Scope scope = getUpperScope(ctx);
        System.out.println("Checking variable " + ctx.getText() + " on scope ");
        //System.out.println("PARENT=?=?" + ctx.getParent().getClass() + " to " + Anasint.Operando_universalContext.class);

        if (!scope.existeVariable(ctx.IDENT().getText()))
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitVariable Variable " + ctx.getText() + " no declarada");

        if (!scope.getVariable(ctx.getText()).isInicializada())
            System.out.println("[ANALIZADOR SEMANTICO] visitVariable Variable " + ctx.getText() + " no inicializada");

        //throw new IllegalStateException("Variable " + ctx.getText() + " no inicializada");

        //hay que distinguir si estamos en scope global o no
        //durante declaracion de subprogramas no mirar valores globales
        if (!scope.getVariable(ctx.getText()).isInicializada())
            return "Indefinido";

        return scope.getVariable(ctx.getText()).getTipo();
        //return super.visitVariable(ctx);
    }

    public String visitSubprograma_ultima_posicion(Anasint.Subprograma_ultima_posicionContext ctx) {
        String tipoArgumento = (String) visit(ctx.evaluacion_variable());
        if (tipoArgumento.equals("Indefinido") ||
                (!tipoArgumento.contains("Boolean") &&
                !tipoArgumento.contains("Integer")))
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitUltima_posicion_variable no se admiten secuencias sin tipo (" + tipoArgumento + ")");

        if (!tipoArgumento.contains("ArrayList"))
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitUltima_posicion_variable  ultima_posicion solo válido en secuencias");

        return tipoArgumento.replace("ArrayList<", "").replace(">", "");
    }

    public String visitSubprograma_vacia (Anasint.Subprograma_vaciaContext ctx) {
        System.out.println("visitVacia " + ctx.getText());
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (!tipoArgumento.contains("ArrayList"))
            throw new IllegalStateException("operador vacia solo válido en secuencias");
        return "Boolean";
    }

    public String visitSubprograma_mostrar (Anasint.Subprograma_mostrarContext ctx) {
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (ctx.getParent().getClass().equals(Anasint.Evaluacion_variableContext.class))
            throw new IllegalStateException("Procedimiento mostrar no válido como evaluación");
        if (!tipoArgumento.contains("ArrayList"))
            throw new IllegalStateException("operador mostrar solo válido en secuencias");
        //return tipoArgumento;
        return null;
    }

}