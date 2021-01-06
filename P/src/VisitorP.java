import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class VisitorP extends AnasintBaseVisitor<Object> {
    //AuxVariables subprogramas = new AuxVariables();
    Scope scopeGlobal = new Scope("global");
    ParseTreeProperty<Scope> scopeTree = new ParseTreeProperty<>();
    ParseTreeProperty<List<Boolean>> retornoTree = new ParseTreeProperty<>();
    ParseTreeProperty<String> rupturaTree = new ParseTreeProperty<>();


    @Override
    public Object visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        ////System.out.println("hello world");
        //AuxVariables scope = new AuxVariables();
        //this.visit(ctx.bloque_variables(), new AuxVariables());
        List<Variable> variables = visitBloque_variables(ctx.bloque_variables());
        scopeGlobal = new Scope("scopeGlobal");
        scopeGlobal.declaraVariables(variables);

        scopeTree.put(ctx, scopeGlobal);
        //visitBloque_subprogramas(ctx.bloque_subprogramas());
        //visitBloque_instrucciones(ctx.bloque_instrucciones());

        //this.visit(ctx.bloque_subprogramas(), scope);
        //return 0;
        return super.visitBloque_programa(ctx);
    }

    public List<Variable> visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        List<Variable> variables = new ArrayList<>();
        ////System.out.println("visitBloque_variables " + ctx.getText());
        /////Scope scope = new Scope();
        //scopes.add(scope);
        /*
        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable()) {
             scope = visitDeclaracion_variable(variable, scope);
            //visitBloque_funcion(subprograma);
            //System.out.println(subprograma.getText());
        }
        */
        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable()) {
            variables.addAll(visitDeclaracion_variable(variable));
        }
        //scopes.add(scope);
        return variables;
        //return super.visitBloque_variables(ctx);
    }

    public List<Variable> visitDeclaracion_variable(Anasint.Declaracion_variableContext ctx) {
        //System.out.println("visitDeclaracion_variable " + ctx.getText());
        //AuxVariables scope = new AuxVariables();

        /*
        for (TerminalNode variable: ctx.IDENT())
            scope.declaraVariable(variable.getText(), ctx.tipo().getText());

        //scopes.add(scope);
        return scope;
        */
        //return super.visitBloque_variables(ctx);
        List<Variable> res = new ArrayList<>();
        for (TerminalNode variable: ctx.IDENT())
             res.add(new Variable(variable.getText(), ctx.tipo().getText()));
        return res;
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
        //Scope scope = visitBloque_variables(ctx.bloque_variables());
        Scope scope = new Scope(ctx.IDENT().getText());
        List<Variable> variables = visitBloque_variables(ctx.bloque_variables());

        scope.declaraVariables(variables);

        List<Anasint.Lista_variables_tipadasContext> variablesEntradaSalida =  ctx.lista_variables_tipadas();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> salida = new ArrayList<>();

        //List<Variable> entrada =  visitArgs_funcion_procedimiento(variables.get(0));
        //List<Variable> salida = visitArgs_funcion_procedimiento(variables.get(1));


        if (variablesEntradaSalida.size() > 1) {
            entrada.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(1)));
        } else
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));


        scope.declaraVariables(entrada);
        scope.declaraVariables(salida);
        scopeGlobal.declaraFuncion(ctx.IDENT().getText(), entrada, salida);
        scopeTree.put(ctx, scope);

        /*
            Aquí entramos en el bloque de instrucciones de la función, y comenzamos a
            realizar las diferentes comprobaciones de tipo y existencia en scope.
         */
        visitBloque_variables(ctx.bloque_variables());
        visitBloque_instrucciones(ctx.bloque_instrucciones());

        // Añadimos retornoTree
        System.out.println("ANALIZANDO RETORNO FUNCION " + retornoTree.get(ctx.bloque_instrucciones()));
        if (!retornoTree.get(ctx.bloque_instrucciones()).contains(true))
            throw new IllegalArgumentException("La función no tiene ningún retorno alcanzable");
        //return super.visitBloque_funcion(ctx);
        return 0;
    }

    public List<Variable> visitLista_variables_tipadas (Anasint.Lista_variables_tipadasContext ctx) {
        //System.out.println("first " + ctx.lista_variables_tipadas())
        List<Variable> res = new ArrayList<>();
        for (Anasint.Variable_tipadaContext varTipada: ctx.variable_tipada())
            res.add(new Variable(varTipada.IDENT().getText(), varTipada.tipo().getText()));
        //scope.declaraVariable(varTipada.IDENT().getText(), varTipada.tipo().getText());
        return res;
    }

    private Scope getUpperScope (ParserRuleContext ctx) {
        if (scopeTree.get((ParseTree) ctx) != null)
            return scopeTree.get(ctx);
        else
            return getUpperScope(ctx.getParent());
    }

    private boolean anyParentIsAFunction (ParserRuleContext ctx) {
        if (ctx.getParent() != null)
            if (ctx.getParent().getClass() == Anasint.Bloque_funcionContext.class)
                return true;
            else
                return anyParentIsAFunction(ctx.getParent());
        else
            return false;
    }


    private ParserRuleContext closestReturnBlock (ParserRuleContext ctx) {
        System.out.println(ctx.getParent().getClass());
        if (ctx.getParent().getClass() == Anasint.Bloque_funcionContext.class ||
        ctx.getParent().getClass() == Anasint.Instruccion_controlContext.class ||
        ctx.getParent().getClass() == Anasint.Bloque_instruccionesContext.class)
            return ctx.getParent();
        else
            return closestReturnBlock(ctx.getParent());
    }

    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        //System.out.println("AUTOmagically? " + scopeTree.get(ctx.getParent()));
        Scope pscope = getUpperScope(ctx);
        //for (Anasint.InstruccionContext instruccion: ctx.instruccion()){
            //System.out.println("Instruccion " + instruccion.getText());
            //visitInstruccion(instruccion);
        //}
        retornoTree.put(ctx, new ArrayList<Boolean>());

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
        String tipoVarDerecha = "";
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

    public String visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        boolean algunPadreFuncion = anyParentIsAFunction(ctx);
        if (!algunPadreFuncion)
            throw new IllegalStateException("Retorno solo válido dentro de función");

        Scope scope = getUpperScope(ctx);
        List<String> tiposSalidaDefinidos = scopeGlobal.getFuncion(scope.getNombre()).getTiposSalida();
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
        //System.out.println("RESULTADO PREDICADO " + resultado);

        for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
            visitInstruccion(instruccion);
        }
        //aqui ya se han procesado todas las instrucciones, analizamos retornos
        System.out.println("RETORNO MAESTRO " + ctx.getText() + " ret "+  retornoTree.get(ctx) + "algun padre funcion " + algunPadreFuncion);

        if (algunPadreFuncion) {
            if (!retornoTree.get(ctx).contains(false) && retornoTree.get(ctx).size() == 2)
                retornoTree.get(bloqueAsociadoARetorno).add(true);
            else
                retornoTree.get(bloqueAsociadoARetorno).add(false);
        }


        return 0;
        //return super.visitInstruccion_control(ctx);
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