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
    /*
    private String convierteClaseATipo (Object expresion) {
        String res = "";
        System.out.println(expresion.getClass());
        switch (expresion.getClass()) {
            //case Anasint.Operando_logicoContext -> res = "Boolean";
            case Anasint.Operando_aritmeticoContext -> res = "Integer";
        }
        return res;
    }
    */

    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        Scope scope = getUpperScope(ctx);
        //System.out.println("IM GETTING ASSIGNED " + ctx.getText());
        List<Anasint.VariableContext> expresionIzquierda = ctx.lista_variables().variable();
        Anasint.Evaluaciones_variablesContext expresionDerecha = ctx.evaluaciones_variables();
        //List<RuleContext> expresionDerecha = ctx.getRuleContexts();

        //String tipoExpresionDerecha = convierteClaseATipo(visitEvaluacion_variable(expresionDerecha));
        //String tipoExpresionIzquierda = scope.getVariable(expresionIzquierda.get(0).getText()).getTipo();

        for (Anasint.VariableContext variableIzquierda: expresionIzquierda) {
            Object dunno = visit(variableIzquierda);
            System.out.println(dunno);
        }

        //Comprobación de tipo variable con homonimo
        for (int i = 0; i < expresionDerecha.evaluacion_variable().size(); i++) {
            Anasint.Evaluacion_variableContext a = expresionDerecha.evaluacion_variable().get(i);
            String tipoVarDerecha = visitEvaluacion_variable(a);
            Variable variableIzqAsociada = scope.getVariable(expresionIzquierda.get(i).getText());
            if (variableIzqAsociada.getTipo() != tipoVarDerecha)
                throw new IllegalStateException("ASIGNACION DE TIPOS INVALIDOS ENTRE VAR " +
                        variableIzqAsociada.getNombre() + " TIPO " + variableIzqAsociada.getTipo() +
                        "CON TIPO " + tipoVarDerecha);
            //variableIzqAsociada.inicializaVariable();
            System.out.println("IZK " + variableIzqAsociada.getTipo() + " vs DER " + tipoVarDerecha);

        }
        //Asignacion paralela
        for (Anasint.VariableContext variableIzquierda: expresionIzquierda) {
            scope.inicializaVariable(variableIzquierda.getText());
        }



/*
        for (Anasint.Evaluacion_variableContext variableDerecha: expresionDerecha.evaluacion_variable()) {
            System.out.println(variableDerecha.getText() + " TIPO IS " + visitEvaluacion_variable(variableDerecha));
            //System.out.println(variableDerecha.getChild(0).getClass());
        }
*/
        //System.out.println(tipo);
        /*
        for (Anasint.Evaluacion_variableContext evaluacion: expresionDerecha) {
            System.out.println("huey " + evaluacion);
        }
*/

        //System.out.println("TipoIzq " + tipoExpresionIzquierda + " TipoDer" + tipoExpresionDerecha);
        /*
        //if (who.getClass().equals(Anasint.Operando_aritmeticoContext.class)) {
        if (tipoExpresionDerecha.equals(scope.getVariable(expresionIzquierda.get(0).getText()).getTipo()))
            Anasint.Operando_aritmeticoContext cnumero = (Anasint.Operando_aritmeticoContext) who;
            System.out.println("ESPUNTONUMEROOO " + cnumero.getText());

            if (scope.getVariable(expresionIzquierda.get(0).getText()).getTipo() == "Integer") {
                scope.getVariable(expresionIzquierda.get(0).getText()).inicializaVariable();
            } else
                throw new IllegalStateException("ASIGNACION DE TIPOS INVALIDOS " + scope.getVariable(expresionIzquierda.get(0).getText()).getTipo() + " vs " + cnumero.getText());
        } else if (who.getClass().equals(Anasint.Operando_logicoContext.class)) {
            Anasint.Operando_logicoContext cnumero = (Anasint.Operando_logicoContext) who;
            System.out.println("ES PUTO LOGICOo " + cnumero.getText());

            if (scope.getVariable(expresionIzquierda.get(0).getText()).getTipo() == "Boolean") {
                scope.getVariable(expresionIzquierda.get(0).getText()).inicializaVariable();
            }
        }
    /*


        //las del lado izq, no pueden aparecer en el derecho
        //System.out.println("STOP is " + ctx.evaluacion_variable().getStop().getText());
        /*for (ParserRuleContext valAsig: rightSide) {
            System.out.println("Testing the shit " + valAsig.getText());
        }*/
        return 1;
        //return super.visitInstruccion_asig(ctx);
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
        String tipoLadoIzquierdo = "";
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
            //System.out.println("LOSLADOS SON " + tipoLadoIzquierdo + " y" + tipoLadoDerecho);
        } else if (ctx.operando_logico() != null && ctx.operacion_logica() != null){
            //System.out.println("ENTRAMOS EN DECIDIDOR, SOLO UN OPERACION " + ctx.getText());
            tipoLadoIzquierdo = visitOperando_logico(ctx.operando_logico());
            tipoLadoDerecho = visitOperacion_logica(ctx.operacion_logica().get(0));


        }
        //if (tipoLadoDerecho == tipoLadoIzquierdo)
        //    System.out.println("THIS IS ABOUT TO CRASH " + ctx.getText());
        if (tipoLadoDerecho != tipoLadoIzquierdo)
            throw new IllegalStateException("TIPOS DE OPERANDOS NO VALIDOS i " + tipoLadoIzquierdo + " d " + tipoLadoDerecho + " ctx " + ctx.getText());
        else
            tipoLadoIzquierdo = "Boolean";

        return tipoLadoIzquierdo;
    }



    public String visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        String resultado = visitPredicado(ctx.predicado());
        if (resultado != "Boolean")
            throw new IllegalStateException("PREDICADO NO BOOLEANO " + ctx.predicado().getText());
        //System.out.println("RESULTADO PREDICADO " + resultado);
        return resultado;
    }
    public String visitPredicado(Anasint.PredicadoContext ctx) {
        return super.visitPredicado(ctx).toString();
    }
    public String visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        return super.visitEvaluacion_variable(ctx).toString();
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

    /*

    public boolean evalOperacionLogica (Anasint.EvalOperacionLogicaContext ctx) {
        this.visit(ctx);
        return true;
    }
    public int evalOperacionAritmetica (Anasint.EvalOperacionAritmeticaContext ctx) {
        this.visit(ctx);
        return 0;
    }*/
    /*
    public Object visitEvalSecuencia (Anasint.EvalSecuenciaContext ctx) {
        System.out.println("im sequence");
        return super.visitEvalSecuencia(ctx);
    }

    }
    public String visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        String tipoEvaluacion = "";
        this.visit(ctx);
        return tipoEvaluacion;*/
        //return super.visitEvaluacion_variable(ctx);
    /*
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

*/
/*
    public Anasint.Operando_aritmeticoContext visitOperacion_aritmetica (Anasint.Operacion_aritmeticaContext ctx) {
        //Anasint.Operando_aritmeticoContext operando = visitOperando_aritmetico(ctx.operando_aritmetico());
        Scope scope = getUpperScope(ctx);
        Anasint.Operando_aritmeticoContext operandoIzquierda = ctx.operando_aritmetico();
        //System.out.println("opIzq " + operandoIzquierda.getText());
        if (operandoIzquierda == null) {
            return visitOperacion_aritmetica(ctx.operacion_aritmetica().get(0));
        }
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
            else
                System.out.println("EXPRESION VALIDA EN CUANTO A TIPOS " + tipoOperandoDerecha + " " + tipoOperandoIzquierda);

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
*/
    /*
    public Object visitOperando_universal (Anasint.Operando_universalContext ctx) {
        //System.out.println("hoing depp");
        return super.visitOperando_universal(ctx);
    }
    */

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
    /*
    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        System.out.println("IM GETTING CONTROLLED " + ctx.getText());
        return super.visitInstruccion_control(ctx);
    }
*/
    public Object visitVariable (Anasint.VariableContext ctx) {
        Scope scope = getUpperScope(ctx);
        System.out.println("Checking variable " + ctx.getText() + " on scope " + scope);
        //System.out.println("PARENT=?=?" + ctx.getParent().getClass() + " to " + Anasint.Operando_universalContext.class);

        if (!scope.existeVariable(ctx.IDENT().getText())) {
            System.out.println("SE HUNDE EL BARCO HIJODEPUTA " + ctx.getText());
            throw new IllegalStateException("Variable " + ctx.getText() + " no declarada");
        }

        if (ctx.getParent().getClass() == Anasint.Operando_universalContext.class &&
                !scope.getVariable(ctx.getText()).isInicializada())
            throw new IllegalStateException("Variable " + ctx.getText() + " no inicializada");
        return scope.getVariable(ctx.getText()).getTipo();
        //return super.visitVariable(ctx);
    }

}
