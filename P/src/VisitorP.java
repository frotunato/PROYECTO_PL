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

    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        Scope scope = getUpperScope(ctx);
        //System.out.println("IM GETTING ASSIGNED " + ctx.getText());
        List<Anasint.VariableContext> expresionIzquierda = ctx.lista_variables().variable();
        Anasint.Evaluaciones_variablesContext expresionDerecha = ctx.evaluaciones_variables();
        //List<RuleContext> expresionDerecha = ctx.getRuleContexts();

        //String tipoExpresionDerecha = convierteClaseATipo(visitEvaluacion_variable(expresionDerecha));
        //String tipoExpresionIzquierda = scope.getVariable(expresionIzquierda.get(0).getText()).getTipo();



        List<String> arrTiposIzq = new ArrayList<String>();
        List<String> arrTiposDer = new ArrayList<String>();

        for (Anasint.VariableContext variableIzquierda: expresionIzquierda) {
            visit(variableIzquierda);
            arrTiposIzq.add(scope.getVariable(variableIzquierda.getText()).getTipo());
        }

        //Comprobación de tipo variable con homonimo

        for (Anasint.Evaluacion_variableContext varDerecha: expresionDerecha.evaluacion_variable()) {
            if (varDerecha.funcion() != null) {
                List<Variable> varSalidaFuncion = scopeGlobal.getFuncion(varDerecha.funcion().IDENT().getText()).getSalida();
                for (Variable vSalida : varSalidaFuncion)
                    arrTiposDer.add(vSalida.getTipo());

            } else
                arrTiposDer.add(visitEvaluacion_variable(varDerecha));
        }
        /*
        for (int i = 0; i < expresionDerecha.evaluacion_variable().size(); i++) {
            Anasint.Evaluacion_variableContext a = expresionDerecha.evaluacion_variable().get(i);
            if (a.funcion() != null) {
                List<Variable> varSalidaFuncion = scopeGlobal.getFuncion(a.funcion().IDENT().getText()).getSalida();
                for (Variable vSalida : varSalidaFuncion) {
                    System.out.println("Salida " + vSalida.getTipo());
                    arrTiposDer.add(vSalida.getTipo());
                }
            } else {
                String tipoVarDerecha = visitEvaluacion_variable(a);
                arrTiposDer.add(tipoVarDerecha);
                /*if (variableIzqAsociada.getTipo() != tipoVarDerecha)
                    throw new IllegalStateException("ASIGNACION DE TIPOS INVALIDOS ENTRE VAR " +
                            variableIzqAsociada.getNombre() + " TIPO " + variableIzqAsociada.getTipo() +
                            "CON TIPO " + tipoVarDerecha);

                ///System.out.println("IZK " + variableIzqAsociada.getTipo() + " vs DER " + tipoVarDerecha);

            }
            //ariable variableIzqAsociada = scope.getVariable(expresionIzquierda.get(i).getText());
            //arrTiposIzq.add(variableIzqAsociada.getTipo());
            }
         */

       if (!arrTiposDer.equals(arrTiposIzq))
           throw new IllegalStateException("Asignación no valida... Izq: " + arrTiposIzq.toString() +
                   " vs Der: " + arrTiposDer.toString());

            //Asignacion paralela
        for (Anasint.VariableContext variableIzquierda: expresionIzquierda)
            scope.inicializaVariable(variableIzquierda.getText());


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
        if (ctx.funcion() != null &&
            scopeGlobal.getFuncion(ctx.funcion().IDENT().getText()).getSalida().size() > 1 &&
            ctx.getParent().getClass() != Anasint.Evaluaciones_variablesContext.class)
                throw new IllegalStateException("Variables retorno multiple solo en definicion");

        return super.visitEvaluacion_variable(ctx).toString();
    }

    /*public String visitEvaluaciones_variables (Anasint.Evaluaciones_variablesContext ctx) {
        if
    }*/

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

    public String visitFuncion (Anasint.FuncionContext ctx) {
        System.out.println("FUNCTION CALLED WITH PARAMS " + ctx.IDENT().getText());
        String tipoArgumento;
        int indexArgumento = 0;
        //Scope scope = scopeGlobal;
        Funcion funcion = scopeGlobal.getFuncion(ctx.IDENT().getText());
        // comprobamos si ha sido invocada con el mismo numero de parametros
        if (funcion.getEntrada().size() != ctx.evaluacion_variable().size()) {
            throw new IllegalStateException("visitFuncion nº argumentos inválidos orig: " +
                    funcion.getEntrada().size() + " , recib: " + ctx.evaluacion_variable().size());
        }

        //comprobamos si los tipos de entrada son los adecuados
        for (Anasint.Evaluacion_variableContext variable: ctx.evaluacion_variable()) {
            tipoArgumento = visitEvaluacion_variable(variable);
            if (variable.funcion() != null)
                System.out.println("ES UNA FUNCION PARAMETER " + scopeGlobal.getFuncion(variable.funcion().IDENT().getText()).getSalida().size());
            if (variable.funcion() != null &&
                    scopeGlobal.getFuncion(variable.funcion().IDENT().getText()).getSalida().size() > 1)
                throw new IllegalStateException("visitFuncion funcion DEV > 1 como parametro");
            if (tipoArgumento != funcion.getEntrada().get(indexArgumento).getTipo())
                throw new IllegalStateException("visitFuncion tipo arg inválido orig: " +
                        funcion.getEntrada().get(indexArgumento).getTipo() + " , recib: " + tipoArgumento);
            System.out.println("visitFuncion argumento " + variable.getText());
            indexArgumento++;
        }
        return funcion.getSalida().get(0).getTipo();
        //return super.visitFuncion(ctx).toString();
    }

    public String visitVariable (Anasint.VariableContext ctx) {
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