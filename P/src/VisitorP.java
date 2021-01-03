import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VisitorP extends AnasintBaseVisitor<Object> {
    Map<Integer, Scope> scopes = new OrderedHashMap<>();
    //AuxVariables subprogramas = new AuxVariables();
    Scope scopeGlobal = new Scope();
    ParseTreeProperty<Scope> scopeTree = new ParseTreeProperty<Scope>();

    @Override
    public Object visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        ////System.out.println("hello world");
        //AuxVariables scope = new AuxVariables();
        //this.visit(ctx.bloque_variables(), new AuxVariables());
        scopeGlobal = visitBloque_variables(ctx.bloque_variables());
        scopeTree.put(ctx, scopeGlobal);
        //visitBloque_subprogramas(ctx.bloque_subprogramas());
        //visitBloque_instrucciones(ctx.bloque_instrucciones());

        //this.visit(ctx.bloque_subprogramas(), scope);
        //return 0;
        return super.visitBloque_programa(ctx);
    }

    public Scope visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        ////System.out.println("visitBloque_variables " + ctx.getText());
        Scope scope = new Scope();
        //scopes.add(scope);
        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable()) {
             scope = visitDeclaracion_variable(variable, scope);
            //visitBloque_funcion(subprograma);
            //System.out.println(subprograma.getText());
        }
        //scopes.add(scope);
        return scope;
        //return super.visitBloque_variables(ctx);
    }

    public Scope visitDeclaracion_variable(Anasint.Declaracion_variableContext ctx, Scope scope) {
        //System.out.println("visitDeclaracion_variable " + ctx.getText());
        //AuxVariables scope = new AuxVariables();
        for (TerminalNode variable: ctx.IDENT())
            scope.declaraVariable(variable.getText(), ctx.tipo().getText());
        //scopes.add(scope);
        return scope;
        //return super.visitBloque_variables(ctx);
    }

    public Object visitBloque_subprogramas (Anasint.Bloque_subprogramasContext ctx) {
        //System.out.println("heheheheu " + scope.getVariablesDeclaradas());

        /*for (Anasint.Bloque_funcionContext funcion: ctx.bloque_funcion())
            visitBloque_funcion(funcion);
        */
        /*for (Anasint.Bloque_funcionContext subprograma: ctx.bloque_funcion()) {
            visitBloque_funcion(subprograma);
            //System.out.println(subprograma.getText());
        }*/
        //return super.visitBloque_subprogramas(ctx);

        //System.out.println("visitBloque_subprogramas");
        return super.visitBloque_subprogramas(ctx);
    }

    public Object visitBloque_funcion(Anasint.Bloque_funcionContext ctx) {
        /*
            En este bloque de instrucciones, añadimos la función al scope global. Las
            variables de entrada, salida, y las definidas en su bloque VARIABLES son
            añadidas al scope de la función.
         */
        Scope scope = visitBloque_variables(ctx.bloque_variables());
        List<Anasint.Args_funcion_procedimientoContext> variables =  ctx.args_funcion_procedimiento();
        List<Variable> entrada =  visitArgs_funcion_procedimiento(variables.get(0));
        List<Variable> salida = visitArgs_funcion_procedimiento(variables.get(1));
        scope.declaraVariables(entrada);
        scope.declaraVariables(salida);
        scopeGlobal.declaraFuncion(ctx.IDENT().getText(), entrada, salida);
        scopeTree.put(ctx, scope);
        /*
            Aquí entramos en el bloque de instrucciones de la función, y comenzamos a
            realizar las diferentes comprobaciones de tipo y existencia en scope.
         */

        //visitBloque_instrucciones(ctx.bloque_instrucciones());

        //List<Variable> entrada = visit(ctx.args_funcion_procedimiento());
        //List<Variable> salida = ctx.args_funcion_procedimiento().get(1);
        /*
        for (Anasint.Args_funcion_procedimientoContext args: ctx.args_funcion_procedimiento())
            visitArgs_funcion_procedimiento(args, scope);
        scopes.put(scope.hashCode(), scope);
        for (Integer scope2: scopes.keySet())
            System.out.println("scope " + scope2 + " " + scopes.get(scope2).getVariablesDeclaradas());
        */
        //return 0;

        return super.visitBloque_funcion(ctx);
    }

    private Scope getUpperScope (ParserRuleContext ctx) {
        if (scopeTree.get((ParseTree) ctx) != null)
            return scopeTree.get(ctx);
        else
            return getUpperScope(ctx.getParent());
    }

    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        //System.out.println("AUTOmagically? " + scopeTree.get(ctx.getParent()));
        Scope pscope = getUpperScope(ctx);
        for (Anasint.InstruccionContext instruccion: ctx.instruccion()){
            //System.out.println("Instruccion " + instruccion.getText());
            //visitInstruccion(instruccion);
        }
        return super.visitBloque_instrucciones(ctx);
    }

    public List<Variable> visitArgs_funcion_procedimiento (Anasint.Args_funcion_procedimientoContext ctx) {
        //System.out.println("first " + ctx.lista_variables_tipadas())
        List<Variable> res = new ArrayList<>();
        for (Anasint.Variable_tipadaContext varTipada: ctx.lista_variables_tipadas().variable_tipada())
            res.add(new Variable(varTipada.getText(), varTipada.tipo().getText()));
            //scope.declaraVariable(varTipada.IDENT().getText(), varTipada.tipo().getText());
        return res;
    }
    /*
    public Object visitInstruccion(Anasint.InstruccionContext ctx) {
        Scope pscope = getUpperScope(ctx);
        System.out.println("JUST AN INSTRUCTION " + ctx.getText());
        //ctx.;
        //return 0;
        return super.visitInstruccion(ctx);
    }
    */
    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        Scope scope = getUpperScope(ctx);
        System.out.println("IM GETTING ASSIGNED " + ctx.getText());
        Anasint.Evaluacion_variableContext rightSide = ctx.evaluacion_variable();
        List<Anasint.VariableContext> leftSide = ctx.lista_variables().variable();

        Object who = visitEvaluacion_variable(rightSide);

        System.out.println("WHO " + who + " " + who);

        if (who.getClass().equals(Anasint.Operando_aritmeticoContext.class)) {
            Anasint.Operando_aritmeticoContext cnumero = (Anasint.Operando_aritmeticoContext) who;
            System.out.println("ESPUNTONUMEROOO " + cnumero.getText());

            if (scope.getVariable(leftSide.get(0).getText()).getTipo() == "Integer") {
                scope.getVariable(leftSide.get(0).getText()).inicializaVariable();
            }
        } else if (who.getClass().equals(Anasint.Operando_logicoContext.class)) {
            Anasint.Operando_logicoContext cnumero = (Anasint.Operando_logicoContext) who;
            System.out.println("ES PUTO LOGICOo " + cnumero.getText());

            if (scope.getVariable(leftSide.get(0).getText()).getTipo() == "Boolean") {
                scope.getVariable(leftSide.get(0).getText()).inicializaVariable();
            }
        }



        //las del lado izq, no pueden aparecer en el derecho
        //System.out.println("STOP is " + ctx.evaluacion_variable().getStop().getText());
        /*for (ParserRuleContext valAsig: rightSide) {
            System.out.println("Testing the shit " + valAsig.getText());
        }*/
        return 1;
        //return super.visitInstruccion_asig(ctx);
    }/*
    public Object visitEvalSecuencia (Anasint.EvalSecuenciaContext ctx) {
        System.out.println("im sequence");
        return super.visitEvalSecuencia(ctx);
    }*/

    public Object visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        return super.visitEvaluacion_variable(ctx);
    }

    public Anasint.Operando_logicoContext visitOperacion_logica (Anasint.Operacion_logicaContext ctx) {
        //Anasint.Operando_aritmeticoContext operando = visitOperando_aritmetico(ctx.operando_aritmetico());
        Scope scope = getUpperScope(ctx);
        Anasint.Operando_logicoContext operandoIzquierda = ctx.operando_logico();
        //System.out.println("opIzq " + operandoIzquierda.getText());
        if (operandoIzquierda == null)
            return visitOperacion_logica(ctx.operacion_logica().get(0));
        //return visitOperacion_aritmetica((Anasint.Operacion_aritmeticaContext) ctx.operacion_aritmetica());

        //operandos.add(ctx.operando_aritmetico());
        System.out.println("LOGICO Operando " + ctx.operando_logico().getText());
        System.out.println("LOGICO Operacion " + ctx.operacion_logica().toString());
        //operandos.add(visitOperacion_aritmetica(ctx));

        for (Anasint.Operacion_logicaContext nOperando: ctx.operacion_logica()) {
            Anasint.Operando_logicoContext operandoDerecha = visitOperacion_logica(nOperando);
            visitOperando_logico(operandoIzquierda);
            visitOperando_logico(operandoDerecha);

            //System.out.println(nValor.getText() + " vs " + operandoIzquierda.getText());
            //System.out.println("[LOGICO] OperandoIzquierda " + operandoIzquierda.getStop().getText());
            //System.out.println("[LOGICO] OperandoDerecha " + operandoDerecha.getStop().getText());
            String tipoOperandoDerecha = "";
            String tipoOperandoIzquierda = "";

            if (operandoDerecha.operando_universal() != null) {
                if (operandoDerecha.operando_universal().variable() != null)
                    tipoOperandoDerecha = scope.getVariable(operandoDerecha.getText()).getTipo();
                else if (operandoDerecha.operando_universal().variable_acceso() != null)
                    tipoOperandoDerecha = scope.getVariable(operandoDerecha.operando_universal().variable_acceso().variable().getText()).getTipo();
                else if (operandoDerecha.operando_universal().ultima_posicion().variable() != null)
                    tipoOperandoDerecha = scope.getVariable(operandoDerecha.operando_universal().ultima_posicion().variable().getText()).getTipo();
            } else if (operandoDerecha.TRUE() != null || operandoDerecha.FALSO() != null)
                tipoOperandoDerecha = "Boolean";

            if (operandoIzquierda.operando_universal() != null) {
                if (operandoIzquierda.operando_universal().variable() != null)
                    tipoOperandoIzquierda = scope.getVariable(operandoIzquierda.getText()).getTipo();
                else if (operandoIzquierda.operando_universal().variable_acceso() != null)
                    tipoOperandoIzquierda = scope.getVariable(operandoIzquierda.operando_universal().variable_acceso().variable().getText()).getTipo();
                else if (operandoIzquierda.operando_universal().ultima_posicion().variable() != null)
                    tipoOperandoIzquierda = scope.getVariable(operandoIzquierda.operando_universal().ultima_posicion().variable().getText()).getTipo();
            } else if (operandoDerecha.TRUE() != null || operandoDerecha.FALSO() != null)
                tipoOperandoIzquierda = "Boolean";

            if (tipoOperandoDerecha != tipoOperandoIzquierda)
                throw new IllegalStateException("DOS VARIABLES NO DEL MISMO TIPO");
        }
        return operandoIzquierda;
    }


    public Anasint.Operando_aritmeticoContext visitOperacion_aritmetica (Anasint.Operacion_aritmeticaContext ctx) {
        //Anasint.Operando_aritmeticoContext operando = visitOperando_aritmetico(ctx.operando_aritmetico());
        Scope scope = getUpperScope(ctx);
        Anasint.Operando_aritmeticoContext operandoIzquierda = ctx.operando_aritmetico();
        //System.out.println("opIzq " + operandoIzquierda.getText());
        if (operandoIzquierda == null)
            return visitOperacion_aritmetica(ctx.operacion_aritmetica().get(0));
            //return visitOperacion_aritmetica((Anasint.Operacion_aritmeticaContext) ctx.operacion_aritmetica());

        //operandos.add(ctx.operando_aritmetico());
        //System.out.println("Operando " + ctx.operando_aritmetico().getText());
        //System.out.println("Operacion " + ctx.operacion_aritmetica().toString());
        //operandos.add(visitOperacion_aritmetica(ctx));

        for (Anasint.Operacion_aritmeticaContext nOperando: ctx.operacion_aritmetica()) {
            Anasint.Operando_aritmeticoContext operandoDerecha = visitOperacion_aritmetica(nOperando);
            visitOperando_aritmetico(operandoIzquierda);
            visitOperando_aritmetico(operandoDerecha);

            //System.out.println(nValor.getText() + " vs " + operandoIzquierda.getText());
            //System.out.println("OperandoIzquierda " + operandoIzquierda.getStop().getTokenSource());
            //System.out.println("OperandoDerecha " + operandoDerecha.getStop().getType());
            String tipoOperandoDerecha = "";
            String tipoOperandoIzquierda = "";

            if (operandoDerecha.operando_universal() != null) {
                if (operandoDerecha.operando_universal().variable() != null)
                    tipoOperandoDerecha = scope.getVariable(operandoDerecha.getText()).getTipo();
                else if (operandoDerecha.operando_universal().variable_acceso() != null)
                    tipoOperandoDerecha = scope.getVariable(operandoDerecha.operando_universal().variable_acceso().variable().getText()).getTipo();
                else if (operandoDerecha.operando_universal().ultima_posicion().variable() != null)
                    tipoOperandoDerecha = scope.getVariable(operandoDerecha.operando_universal().ultima_posicion().variable().getText()).getTipo();
            } else if (operandoDerecha.NUMERO() != null)
                tipoOperandoDerecha = "Integer";

            if (operandoIzquierda.operando_universal() != null) {
                if (operandoIzquierda.operando_universal().variable() != null)
                    tipoOperandoIzquierda = scope.getVariable(operandoIzquierda.getText()).getTipo();
                else if (operandoIzquierda.operando_universal().variable_acceso() != null)
                    tipoOperandoIzquierda = scope.getVariable(operandoIzquierda.operando_universal().variable_acceso().variable().getText()).getTipo();
                else if (operandoIzquierda.operando_universal().ultima_posicion().variable() != null)
                    tipoOperandoIzquierda = scope.getVariable(operandoIzquierda.operando_universal().ultima_posicion().variable().getText()).getTipo();
            } else if (operandoIzquierda.NUMERO() != null)
                tipoOperandoIzquierda = "Integer";

            if (tipoOperandoDerecha != tipoOperandoIzquierda)
                throw new IllegalStateException("DOS VARIABLES NO DEL MISMO TIPO");

            /*
            //caso suma 2 operadores_universales
            if (operandoDerecha.operando_universal() != null &&
                    operandoIzquierda.operando_universal() != null) {
                //if (operandoDerecha.operando_universal().variable() != null)
                //caso variable + variable
                if (operandoDerecha.operando_universal().variable() != null &&
                         operandoIzquierda.operando_universal().variable() != null) {
                    Variable varIzq = scope.getVariable(operandoIzquierda.getText());
                    Variable varDer = scope.getVariable(operandoDerecha.getText());
                    if (varIzq.getTipo() != "Integer" || varDer.getTipo() != "Integer")
                        throw new IllegalStateException("tipos variable distintos");
                    System.out.println("variable + variable");
                //caso variable + variable_acceso
                } else if (operandoIzquierda.operando_universal().variable() != null &&
                            operandoDerecha.operando_universal().variable_acceso() != null) {
                    Variable varIzq = scope.getVariable(operandoIzquierda.getText());
                    Variable varDer = scope.getVariable(operandoDerecha.getText());
                    if (varIzq.getTipo() != "Integer" || varDer.getTipo() != "ArrayList<Integer>")
                        throw new IllegalStateException("tipos variable distintos");
                //caso variable_acceso + variable
                } else if (operandoIzquierda.operando_universal().variable_acceso() != null &&
                            operandoDerecha.operando_universal().variable() != null) {
                    Variable varIzq = scope.getVariable(operandoIzquierda.getText());
                    Variable varDer = scope.getVariable(operandoDerecha.getText());
                    if (varIzq.getTipo() != "ArrayList<Integer>" || varDer.getTipo() != "Integer")
                        throw new IllegalStateException("tipos variable distintos");
                }


                if (varIzq.getTipo() == varDer.getTipo())
                    System.out.println("SON MISMO TIPO, AMBAS VARIABLES");
                else
                    throw new IllegalStateException("DOS VARIABLES NO DEL MISMO TIPO");
                //ambas variables, cmprobar tipo
                87z
            } else if (operandoDerecha.operando_universal() != null) {

                Variable varDer = scope.getVariable(operandoDerecha.getText());

                System.out.println("DERECHA VARIABLE");
            } else if (operandoIzquierda.operando_universal() != null) {
                Variable varIzq = scope.getVariable(operandoIzquierda.getText());
                System.out.println("IZQUIERDA VARIABLE");
            }*/

            //String tipoOperandoIzquierda = scope.getVariable(operando.getText()).getTipo();
            //String tipoOperandoDerecha = nValor.NUMERO();
            /*
            if (operandoDerecha.getStop().getType() == (operandoDerecha.getStop().getType()))
                System.out.println("operacion valida");
            else
                throw new IllegalStateException("Variables de tipos diferentes");
            */
            //operandos.add(visitOperacion_aritmetica(operando));

            //System.out.println("cuantos operaciones " + operando.getText());
        }


        //operandos.add(ctx.operando_aritmetico());
        return operandoIzquierda;
        //return super.visitOperacion_aritmetica(ctx.);
    }

    public Object visitOperando_aritmetico (Anasint.Operando_aritmeticoContext ctx) {
        //Scope scope = getUpperScope(ctx);
        //System.out.println("OPERANDO UNIVERSAL? Q CLASE LOCO " + ctx.);
        //return ctx.getText();
        return super.visitOperando_aritmetico(ctx);
    }

    public Object visitOperando_universal (Anasint.Operando_universalContext ctx) {
        //System.out.println("hoing depp");
        return super.visitOperando_universal(ctx);
    }
    /*
        public Object visitOperando_logico (Anasint.Operando_logicoContext ctx) {
            Scope scope = getUpperScope(ctx);
            //System.out.println("OPERANDO UNIVERSAL? Q CLASE LOCO " + ctx.);
            return ctx.getTe();
        }
        /*
        public Object visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
            System.out.println(ctx.)
        }
        */
    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        System.out.println("IM GETTING CONTROLLED " + ctx.getText());
        return super.visitInstruccion_control(ctx);
    }

    public Object visitVariable (Anasint.VariableContext ctx) {
        Scope scope = getUpperScope(ctx);
        System.out.println("Checking variable " + ctx.getText() + " on scope " + scope);
        System.out.println("PARENT=?=?" + ctx.getParent().getClass() + " to " + Anasint.Operando_universalContext.class);

        if (!scope.existeVariable(ctx.IDENT().getText())) {
            System.out.println("SE HUNDE EL BARCO HIJODEPUTA " + ctx.getText());
            throw new IllegalStateException("Variable " + ctx.getText() + " no declarada");
        }

        if (ctx.getParent().getClass() == Anasint.Operando_universalContext.class &&
                !scope.getVariable(ctx.getText()).isInicializada())
            throw new IllegalStateException("Variable " + ctx.getText() + " no inicializada");

        return super.visitVariable(ctx);
    }

}
