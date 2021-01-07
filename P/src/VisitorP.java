import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class VisitorP extends AnasintBaseVisitor<Object> {
    //AuxVariables subprogramas = new AuxVariables();
    //Scope scopeGlobal = new Scope("global");
    Anasint.Bloque_programaContext raiz;
    ParseTreeProperty<Scope> scopeTree = new ParseTreeProperty<>();
    ParseTreeProperty<List<Boolean>> retornoTree = new ParseTreeProperty<>();
    ParseTreeProperty<Boolean> rupturaTree = new ParseTreeProperty<>();


    private Scope getGlobalScope () {
        return scopeTree.get(raiz);
    }

    private Scope getUpperScope (ParserRuleContext ctx) {
        if (scopeTree.get((ParseTree) ctx) != null)
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
        if (ctx.getParent().getClass().equals(Anasint.Bloque_funcionContext.class) ||
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

        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable()) {
            variables.addAll(visitDeclaracion_variable(variable));
        }
        return variables;
        //return super.visitBloque_variables(ctx);
    }

    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        //igual hay que descomentar esto??
        retornoTree.put(ctx, new ArrayList<Boolean>());
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
        Scope scope = new Scope(getGlobalScope(), ctx.IDENT().getText());
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
        if (retornoTree.get(ctx.bloque_instrucciones()).contains(true))
            throw new IllegalArgumentException("El procedimiento no puede tener ningún retorno");

        return 0;
    }

    public Object visitBloque_funcion(Anasint.Bloque_funcionContext ctx) {
        /*
            En este bloque de instrucciones, añadimos la función al scope global. Las
            variables de entrada, salida, y las definidas en su bloque VARIABLES son
            añadidas al scope de la función.
         */
        Scope scope = new Scope(getGlobalScope(), ctx.IDENT().getText());
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

        visitBloque_variables(ctx.bloque_variables());
        visitBloque_instrucciones(ctx.bloque_instrucciones());

        // Añadimos retornoTree

        //comprobamos si algun retorno disponible es válido
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
        //System.out.println("first " + ctx.lista_variables_tipadas())
        List<Variable> res = new ArrayList<>();
        for (Anasint.Variable_tipadaContext varTipada: ctx.variable_tipada())
            res.add(new Variable(varTipada.IDENT().getText(), varTipada.tipo().getText()));
        //scope.declaraVariable(varTipada.IDENT().getText(), varTipada.tipo().getText());
        return res;
    }



    public String visitOperando_secuencia (Anasint.Operando_secuenciaContext ctx) {
        List<String> tiposSecuencia = new ArrayList<>();
        for (Anasint.Evaluacion_variableContext elemento: ctx.evaluacion_variable()) {
            tiposSecuencia.add(visitEvaluacion_variable(elemento));
        }
        if (!tiposSecuencia.stream().allMatch(s -> s.equals(tiposSecuencia.get(0))))
            throw new IllegalStateException("Secuencia con tipos mixtos " + tiposSecuencia.toString());
        if (tiposSecuencia.isEmpty())
            return "ArrayList<>";
        else
            return "ArrayList<" + tiposSecuencia.get(0) + ">";
    }

    public Object visitInstruccion (Anasint.InstruccionContext ctx) {
        if (rupturaTree.get(ctx.getParent()) != null)
            throw new IllegalStateException("No se admiten instrucciones después de una ruptura");
        if (retornoTree.get(ctx.getParent()) != null && !retornoTree.get(ctx.getParent()).isEmpty())
            throw new IllegalStateException("No se admiten instrucciones después de un retorno");
        return super.visitInstruccion(ctx);
    }

    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        Scope scope = getUpperScope(ctx);
        List<Anasint.VariableContext> expresionIzquierda = ctx.lista_variables().variable();
        Anasint.Evaluaciones_variablesContext expresionDerecha = ctx.evaluaciones_variables();
        String tipoVarDerecha = "";
        String nombreSubprograma;
        List<String> arrTiposIzq = new ArrayList<String>();
        List<String> arrTiposDer = new ArrayList<String>();

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
            if (varDerecha.subprograma() != null) {
                nombreSubprograma = varDerecha.subprograma().IDENT().getText();
                if (getGlobalScope().existeProcedimiento(nombreSubprograma))
                    throw new IllegalArgumentException("Los procedimientos no pueden ser usados en asignaciones");
                else if (getGlobalScope().existeFuncion(nombreSubprograma))
                    arrTiposDer.addAll(getGlobalScope().getSubprograma(nombreSubprograma).getTiposSalida());
            } else
                arrTiposDer.add(visitEvaluacion_variable(varDerecha));
        }

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
            retornoTree.put(bloqueAsociadoARetorno, new ArrayList<Boolean>());
            System.out.println("El padre no tiene un arr bool para guardar ret " + bloqueAsociadoARetorno.getClass());
        }
        retornoTree.get(bloqueAsociadoARetorno).add(true);

        return "True";
    }

    public Object visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        ParserRuleContext closest = closestBreakBlock(ctx);
        System.out.println("Hemos puesto ruptura en " + closest.getClass());
        rupturaTree.put(closestBreakBlock(ctx), true);
        return super.visitInstruccion_ruptura(ctx);
    }

    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        String resultado = visitPredicado(ctx.predicado());
        boolean algunPadreFuncion = anyParentIsAFunction(ctx);
        ParserRuleContext bloqueAsociadoARetorno = closestReturnBlock(ctx);

        //Comprobamos que algun nodo padre es una funcion. Evaluamos retorno si lo tiene.
        if (algunPadreFuncion) {
            retornoTree.put(bloqueAsociadoARetorno, new ArrayList<Boolean>());
        }

        System.out.println("visitInstruccion_control " + ctx.getText() + " Is any parent a function?" + algunPadreFuncion);

        if (resultado != "Boolean")
            throw new IllegalStateException("PREDICADO NO BOOLEANO " + ctx.predicado().getText());

        for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
            visitInstruccion(instruccion);
        }

        //aqui ya se han procesado todas las instrucciones, analizamos retornos
        System.out.println("RETORNO MAESTRO " + ctx.getText() + " ret "+  retornoTree.get(ctx) + "algun padre funcion " + algunPadreFuncion);

        if (algunPadreFuncion && retornoTree.get(ctx) != null) {
            if (!retornoTree.get(ctx).contains(false) && retornoTree.get(ctx).size() == 2)
                retornoTree.get(bloqueAsociadoARetorno).add(true);
            else
                retornoTree.get(bloqueAsociadoARetorno).add(false);
        }
        return 0;
        //return super.visitInstruccion_control(ctx);
    }

    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        if (!getGlobalScope().existeFuncion(ctx.subprograma().IDENT().getText()))
            throw new IllegalStateException("El subprograma de avance debe ser una función");

        return super.visitInstruccion_bucle(ctx);
    }

    public Object visitInstruccionAserto (Anasint.Instruccion_asertoContext ctx) {
        return super.visitInstruccion_aserto(ctx);
    }

    public String visitSubprograma (Anasint.SubprogramaContext ctx) {
        String tipoArgumento;
        int indexArgumento = 0;
        String tipoRes = "Indefinido";
        String nombreSubprograma = ctx.IDENT().getText();
        //Scope scope = scopeGlobal;
        String tipoSubprograma;
/*
        if (scopeGlobal.existeFuncion(nombreSubprograma)) {
            tipoSubprograma = "Funcion";
        }
        else if(scopeGlobal.existeProcedimiento(nombreSubprograma)) {
            tipoSubprograma = "Procedimiento";
        }
        else {
            throw new IllegalArgumentException("Subprograma no definido");
        }
*/

        //comprobamos la existencia del subprograma
        if (!getGlobalScope().existeSubprograma(nombreSubprograma))
            throw new IllegalArgumentException("El subprograma no está definido");

        Subprograma subprograma = getGlobalScope().getSubprograma(nombreSubprograma);

        if (subprograma.getEntrada().size() != ctx.evaluacion_variable().size())
            throw new IllegalArgumentException("El número de argumentos del subprograma " + ctx.IDENT().getText() + " no es correcto");

        //tipoSubprograma = scopeGlobal.getSubprograma(nombreSubprograma).getTipo();
        //Subprograma subprograma = scopeGlobal.getSubprograma(nombreSubprograma);

        //comprobamos si los tipos de entrada son los adecuados
        for (Anasint.Evaluacion_variableContext variable: ctx.evaluacion_variable()) {
            tipoArgumento = visitEvaluacion_variable(variable);

            if (!tipoArgumento.equals(subprograma.getEntrada().get(indexArgumento).getTipo()))
                throw new IllegalStateException("visitSubprograma tipo arg inválido orig: " +
                        subprograma.getEntrada().get(indexArgumento).getTipo() + " , recib: " + tipoArgumento);


            //if (variable.subprograma() != null)
            //    System.out.println("ES UN SUBPROGRAMA PARAMETER " + scopeGlobal.getFuncion(nombreSubprograma).getSalida().size());
            if (variable.subprograma() != null &&
                    //scopeGlobal.getSubprograma(variable.subprograma().IDENT().getText()).esFuncion() &&
                    !getGlobalScope().getSubprograma(variable.subprograma().IDENT().getText()).esArgumento())
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




        //Funcion funcion = scopeGlobal.getFuncion(nombreSubprograma);
        //Procedimiento
        // comprobamos si ha sido invocada con el mismo numero de parametros
        //Subprograma.Tipo tipoSubprograma = subprograma.getTipoSubprograma();
/*
        if (tipoSubprograma == "Funcion") {
            Subprograma funcion = scopeGlobal.getSubprograma(nombreSubprograma);

            if (funcion.getEntrada().size() != ctx.evaluacion_variable().size()) {
                throw new IllegalStateException("visitSubprograma nº argumentos inválidos orig: " +
                        funcion.getEntrada().size() + " , recib: " + ctx.evaluacion_variable().size());
            }

            if (ctx.getParent().getClass() == Anasint.Operando_universalContext.class &&
                    funcion.getSalida().size() > 1)
                throw new IllegalStateException("La función devuelve más de un parámetro fuera de una asignación");

        } else {
            Subprograma procedimiento = scopeGlobal.getSubprograma(nombreSubprograma);

            if (ctx.getParent().getClass() == Anasint.Operando_universalContext.class) {
                throw new IllegalStateException("Los procedimientos no pueden ser argumentos");
            }
        }
        System.out.println(tipoSubprograma + "  " + ctx.IDENT().getText() + " CALLED RETURNED " + tipoRes);
*/
        return tipoRes;



        //return super.visitFuncion(ctx).toString();
    }

    public String visitPredicado(Anasint.PredicadoContext ctx) {
        return super.visitPredicado(ctx).toString();
    }

    public String visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        if (ctx.subprograma() != null &&
                getGlobalScope().existeFuncion(ctx.subprograma().IDENT().getText()) &&
                !getGlobalScope().getSubprograma(ctx.subprograma().IDENT().getText()).esArgumento() && //piede fallar, quitar !
                !ctx.getParent().getClass().equals(Anasint.Evaluaciones_variablesContext.class))
                throw new IllegalStateException("Variables retorno multiple solo en definicion");

        return super.visitEvaluacion_variable(ctx).toString();
    }

    public String visitOperacion_aritmetica (Anasint.Operacion_aritmeticaContext ctx) {
        System.out.println("visitOperacion_aritmetica " + ctx.getText());
        String tipoLadoIzquierdo = "";
        String tipoLadoDerecho = "";
        //caso base
        if (ctx.operador_aritmetico_2_ario() != null && ctx.operando_aritmetico() == null) {
            return visitOperacion_aritmetica(ctx.operacion_aritmetica().get(0));
        } else if (ctx.operador_aritmetico_2_ario() == null && ctx.operando_aritmetico() != null)
            return visitOperando_aritmetico(ctx.operando_aritmetico());
        else {
            tipoLadoIzquierdo = visitOperando_aritmetico(ctx.operando_aritmetico());
            for (Anasint.Operacion_aritmeticaContext operacion: ctx.operacion_aritmetica()) {
                tipoLadoDerecho = visitOperacion_aritmetica(operacion);
                System.out.println(tipoLadoDerecho + " vs " + tipoLadoIzquierdo);
                if (tipoLadoDerecho != tipoLadoIzquierdo)
                    throw new IllegalStateException("TIPOS DE OPERANDOS NO VALIDOS");

            }
        }
        System.out.println("padre op aritmetica es " + ctx.getParent().getClass());
        return tipoLadoIzquierdo;
    }

    public String visitOperacion_logica (Anasint.Operacion_logicaContext ctx) {
        System.out.println("visitOperacion_logica " + ctx.getText());
        String tipoLadoIzquierdo = "Indefinido";
        String tipoLadoDerecho = "";
        String res;

        if (ctx.operador_logico_2_ario() != null && ctx.operando_logico() == null && ctx.operacion_logica().size() == 1) {
            System.out.println("CASO NESTED " + ctx.getText());
            res =  visitOperacion_logica(ctx.operacion_logica().get(0));
            return res;
        } else if (ctx.operador_logico_2_ario() == null && ctx.operando_logico() != null) {
            res = visitOperando_logico(ctx.operando_logico());
            System.out.println("CASO BASE para " + ctx.getText() + " returned " + res);
            return res;
        } else if (ctx.operador_logico_2_ario() != null && ctx.operacion_logica().size() == 2) {
            System.out.println("DOSSS " + ctx.getText());
            tipoLadoIzquierdo = visitOperacion_logica(ctx.operacion_logica().get(0));
            tipoLadoDerecho = visitOperacion_logica(ctx.operacion_logica().get(1));
            System.out.println("LOSLADOS SON " + tipoLadoIzquierdo + " y" + tipoLadoDerecho);
        } else if (ctx.operando_logico() != null && ctx.operacion_logica() != null) {
            System.out.println("ENTRAMOS EN DECIDIDOR, SOLO UN OPERACION " + ctx.getText());
            tipoLadoIzquierdo = visitOperando_logico(ctx.operando_logico());
            tipoLadoDerecho = visitOperacion_logica(ctx.operacion_logica().get(0));
        }

        if (tipoLadoDerecho.equals(tipoLadoIzquierdo))
            tipoLadoIzquierdo = "Boolean";

        return tipoLadoIzquierdo;
    }

    public String visitOperando_aritmetico (Anasint.Operando_aritmeticoContext ctx) {
        System.out.println("visitOperando_aritmetico " + ctx.getText());
        if (ctx.operando_universal() != null)
            return visitOperando_universal(ctx.operando_universal());
        else
            return "Integer";
    }

    public String visitOperando_logico (Anasint.Operando_logicoContext ctx) {
        System.out.println("visitOperando_logico " + ctx.getText());
        if (ctx.operacion_aritmetica() != null) {
            return visitOperacion_aritmetica(ctx.operacion_aritmetica());
        } else
            return "Boolean";
    }

    public String visitOperando_universal (Anasint.Operando_universalContext ctx) {
        return super.visitOperando_universal(ctx).toString();
    }

    public String visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        String tipoVariable = visitVariable(ctx.variable());
        String tipoAcceso = visitOperacion_aritmetica(ctx.operacion_aritmetica());
        if (tipoAcceso != "Integer") {
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
        System.out.println("Checking variable " + ctx.getText() + " on scope " + scope);
        //System.out.println("PARENT=?=?" + ctx.getParent().getClass() + " to " + Anasint.Operando_universalContext.class);

        if (!scope.existeVariable(ctx.IDENT().getText()))
            throw new IllegalStateException("Variable " + ctx.getText() + " no declarada");

        /*
        if (ctx.getParent().getClass() == Anasint.Operando_universalContext.class &&
                !scope.getVariable(ctx.getText()).isInicializada())
            throw new IllegalStateException("Variable " + ctx.getText() + " no inicializada");
        */
        if (!scope.getVariable(ctx.getText()).isInicializada())
            return "Undefined";

        return scope.getVariable(ctx.getText()).getTipo();
        //return super.visitVariable(ctx);
    }

    public String visitUltima_posicion (Anasint.Ultima_posicionContext ctx) {
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (!tipoArgumento.contains("ArrayList"))
            throw new IllegalStateException("operador ultima_posicion solo válido en secuencias");
        return tipoArgumento.replace("ArrayList<", "").replace(">", "");
    }

    public String visitVacia (Anasint.VaciaContext ctx) {
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (!tipoArgumento.contains("ArrayList"))
            throw new IllegalStateException("operador vacia solo válido en secuencias");
        return "Boolean";
    }

    public String visitMostrar (Anasint.MostrarContext ctx) {
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (!tipoArgumento.contains("ArrayList"))
            throw new IllegalStateException("operador mostrar solo válido en secuencias");
        return tipoArgumento;
    }

}