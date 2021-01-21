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
    boolean compruebaVariableInicializada = false;
    boolean retornoMultiplePermitido = false;
    List<Boolean> retornosBloque = new ArrayList<>();

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
        Scope scope = new Scope(getGlobalScope(), ctx.IDENT().getText());
        String nombreProcedimiento = ctx.IDENT().getText();
        //Scope scope = new Scope(ctx.IDENT().getText());
        List<Variable> variablesProcedimiento = visitBloque_variables(ctx.bloque_variables());

        List<Variable> variablesEntrada = visitLista_variables_tipadas(ctx.lista_variables_tipadas());

        for (Variable variableEntrada: variablesEntrada)
            if (variablesProcedimiento.contains(variableEntrada))
                throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitBloque_procedimiento:" +
                        " la variable " + variableEntrada.getNombre() +
                        " ya esta definida en el procedimiento " + nombreProcedimiento);
            else
                variableEntrada.setSoloLectura();

        scope.declaraVariables(variablesProcedimiento);
        scope.declaraVariables(variablesEntrada);
        scope.inicializaVariables(variablesEntrada);
        //scope.inicializaVariables(salida);

        getGlobalScope().declaraSubprograma(nombreProcedimiento, "Procedimiento", variablesEntrada, new ArrayList<>());
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
        Scope scope = new Scope(getGlobalScope(), ctx.IDENT().getText());
        String nombreFuncion = ctx.IDENT().getText();
        //Scope scope = new Scope(ctx.IDENT().getText());
        List<Variable> variablesFuncion = visitBloque_variables(ctx.bloque_variables());

        scope.declaraVariables(variablesFuncion);

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

        for (Variable variable: variablesFuncion)
           if (salida.contains(variable) || entrada.contains(variable))
               throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitBloque_funcion:" +
                       " la variable " + variable.getNombre() +
                       " ya esta definida en la función " + nombreFuncion);

        scope.declaraVariables(entrada);
        scope.declaraVariables(salida);
        scope.inicializaVariables(entrada);
        //scope.inicializaVariables(salida);

        getGlobalScope().declaraSubprograma(nombreFuncion, "Funcion", entrada, salida);
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
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitInstruccion: Funcion con ruptura sin retorno alcanzable");

        //instrucciones despues de ruptura
        if (rupturaTree.get(ctx.getParent()) != null)
            System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion: No se admiten instrucciones después de una ruptura");

            //throw new IllegalStateException("No se admiten instrucciones después de una ruptura");
        if (retornoTree.get(ctx.getParent()) != null && retornoTree.get(ctx.getParent()).size() == 2)
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitInstruccion: No se admiten instrucciones después de un retorno");

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
            String tipoVariable = visitVariable(variableIzquierda);
            if (scope.getVariable(variableIzquierda.getText()).isSoloLectura())
                throw new IllegalArgumentException("Se ha intentado asignar una variable de solo lectura");
            arrTiposIzq.add(tipoVariable);
            //arrTiposIzq.add(scope.getVariable(variableIzquierda.getText()).getTipo());
        }
        //comprobamos que las posibles variables usadas en expresiones
        //de asignacion esten inicializadas previamente
        compruebaVariableInicializada = true;
        // cargamos en arrTiposDer los tipos de las expresiones
        for (Anasint.Evaluacion_variableContext varDerecha: expresionDerecha.evaluacion_variable()) {
            System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_asig: ESTAMOS ASIGNANDO " + varDerecha.getText() + " clase " + varDerecha.getClass());
            if (varDerecha.subprograma() != null && varDerecha.subprograma().getClass().equals(Anasint.Subprograma_declaradoContext.class)) {
                retornoMultiplePermitido = true;
                visitEvaluacion_variable(varDerecha);
                retornoMultiplePermitido = false;
                nombreSubprograma = varDerecha.subprograma().getChild(0).getText();
                if (!getGlobalScope().existeSubprograma(nombreSubprograma))
                    throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitInstruccion_asig: Subprograma no definido! " + nombreSubprograma);
                if (getGlobalScope().existeProcedimiento(nombreSubprograma))
                    throw new IllegalArgumentException("[ANALIZADOR SEMANTICO] visitInstruccion_asig: Los procedimientos no pueden ser usados en asignaciones");
                else if (getGlobalScope().existeFuncion(nombreSubprograma))
                    arrTiposDer.addAll(getGlobalScope().getSubprograma(nombreSubprograma).getTiposSalida());
            } else {
                arrTiposDer.add(visitEvaluacion_variable(varDerecha));
            }
        }

        if (arrTiposIzq.size() != arrTiposDer.size())
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitInstruccion_asig: Las asignaciones no tienen el mismo numero de elementos");

        //Comprobación del tipo de la expresión y su variable asociada
        for (int i = 0; i < arrTiposIzq.size(); i++) {
            System.out.println(arrTiposIzq.size() + " " + arrTiposIzq.get(i));
            if (arrTiposIzq.get(i).contains("ArrayList") && arrTiposDer.get(i).equals("ArrayList<>")) {
                System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_asig: Lista tipada vs generica ");
            } else if (!arrTiposIzq.get(i).equals(arrTiposDer.get(i))) {
                throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitInstruccion_asig: Asignación no valida... Izq: " + arrTiposIzq.toString() +
                        " vs Der: " + arrTiposDer.toString());
            }
        }
        compruebaVariableInicializada = false;
        //Asignacion paralela
        for (Anasint.VariableContext variableIzquierda: expresionIzquierda)
            scope.inicializaVariable(variableIzquierda.getText());


        return 1;
        //return super.visitInstruccion_asig(ctx);
    }
    public String visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        boolean algunPadreFuncion = anyParentIsAFunction(ctx);
        if (!algunPadreFuncion)
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: Retorno solo válido dentro de función");

        Scope scope = getUpperScope(ctx);
        List<String> tiposSalidaDefinidos = getGlobalScope().getSubprograma(scope.getNombre()).getTiposSalida();
        List<String> tiposSalidaPrograma = new ArrayList<>();
        //tenemos que comprobar en el retorno que devolvemos lo declarado
        for (Anasint.Evaluacion_variableContext variablesRetorno: ctx.evaluaciones_variables().evaluacion_variable()) {
            tiposSalidaPrograma.add(visitEvaluacion_variable(variablesRetorno));
        }

        if (!tiposSalidaDefinidos.equals(tiposSalidaPrograma))
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: Instruccion retorno inválida (no devuelve los tipos especificados)");

        System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: Estamos en la funcion" + scope.getNombre() +
                " obtenidos " + tiposSalidaPrograma);

        ParserRuleContext bloqueAsociadoARetorno = closestReturnBlock(ctx);

        retornoTree.get(bloqueAsociadoARetorno).add(true);
        System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: El arr bool para "+  ctx.getParent().getText() + " es " + retornoTree.get(bloqueAsociadoARetorno).toString());
        return null;
    }
    public Object visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        ParserRuleContext closest = closestBreakBlock(ctx);
        System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_retorno: Hemos puesto ruptura en " + closest.getClass());
        rupturaTree.put(closestBreakBlock(ctx), true);
        return super.visitInstruccion_ruptura(ctx);
    }
    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        String resultado = (String) visit(ctx.predicado());

        boolean algunPadreFuncion = anyParentIsAFunction(ctx);

        //Comprobamos que algun nodo padre es una funcion. Evaluamos retorno si lo tiene.
        //esto igual sobra
        if (algunPadreFuncion)
            retornoTree.put(ctx, new ArrayList<>());

        //predicado cierto, falso o indefinido, no numero
        if (resultado.equals("Integer"))
            System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_control: PREDICADO NO BOOLEANO/INDEFINIDO " + ctx.predicado().getText());
            //throw new IllegalStateException("PREDICADO NO BOOLEANO " + ctx.predicado().getText());

        for (Anasint.InstruccionContext instruccion: ctx.instruccion())
            visit(instruccion);

        System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_control: " + ctx.getText() + " lista de retornos: "+  retornoTree.get(ctx));


        //aqui ya se han procesado todas las instrucciones, analizamos retornos
        //solo si se trata de una funcion, claro, y de si existen retornos dentro
        ParserRuleContext bloquePadreAsociadoARetorno = closestReturnBlock(ctx.getParent());
        //añadimos si los retornos de esta instr. control son validos al padre
        //si no validos, false. si validos, true
        if (algunPadreFuncion && retornoTree.get(ctx) != null) {
            if (!retornoTree.get(ctx).contains(false) && retornoTree.get(ctx).size() == 2)
                retornoTree.get(bloquePadreAsociadoARetorno).add(true);
            else
                retornoTree.get(bloquePadreAsociadoARetorno).add(false);
        }
        return 0;
    }
    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        String resultado = (String) visit(ctx.predicado());
        if (resultado.equals("Integer"))
            System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_bucle: PREDICADO NO BOOLEANO/INDEFINIDO " + ctx.predicado().getText());
        return super.visitInstruccion_bucle(ctx);
    }
    public Object visitInstruccion_aserto_simple (Anasint.Instruccion_aserto_simpleContext ctx) {
        String resultado = (String) visit(ctx.predicado());
        if (resultado.equals("Integer"))
            System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_aserto_simple: PREDICADO NO BOOLEANO/INDEFINIDO " + ctx.predicado().getText());
        return null;
    }
    public Object visitInstruccion_aserto_cuantificado (Anasint.Instruccion_aserto_cuantificadoContext ctx) {
        getUpperScope(ctx).declaraVariable(new Variable(ctx.variable().getText(), "NUM"));
        String resultado = (String) visit(ctx.predicado());
        if (resultado.equals("Integer"))
            System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_aserto_paratodo: PREDICADO NO BOOLEANO/INDEFINIDO " + ctx.predicado().getText());
        String operacionA = (String) visit(ctx.operacion(0));
        String operacionB = (String) visit(ctx.operacion(1));
        if (!operacionA.equals("Integer") || !operacionB.equals("Integer"))
            System.out.println("[ANALIZADOR SEMANTICO] visitInstruccion_aserto_paratodo: PREDICADO NO BOOLEANO/INDEFINIDO " + ctx.predicado().getText());
        return null;
    }

    public String visitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String tipoArgumento;
        int indexArgumento = 0;
        String tipoRes = "Indefinido";
        String nombreSubprograma = ctx.IDENT().getText();
        System.out.println("[ANALIZADOR SEMANTICO] visitSubprograma_declarado: " + ctx.IDENT().getText());
        //comprobamos la existencia del subprograma

        if (!getGlobalScope().existeSubprograma(nombreSubprograma))
            throw new IllegalArgumentException("[ANALIZADOR SEMANTICO] visitSubprograma_declarado: El subprograma no está definido");

        Subprograma subprograma = getGlobalScope().getSubprograma(nombreSubprograma);

        if (subprograma.getEntrada().size() != ctx.evaluacion_variable().size())
            throw new IllegalArgumentException("[ANALIZADOR SEMANTICO] visitSubprograma_declarado: El número de argumentos del subprograma " + nombreSubprograma + " no es correcto, esperando " + subprograma.getEntrada().size() + ", obtenemos " +  ctx.evaluacion_variable().size() + " " + ctx.getText());
        else if (!subprograma.esArgumento() && !retornoMultiplePermitido)
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitSubprograma_declarado: No se permiten funciones de retorno multiple fuera de una asignacion (" + nombreSubprograma + ")");
        //comprobamos si los tipos de entrada son los adecuados
        retornoMultiplePermitido = false;
        for (Anasint.Evaluacion_variableContext variable: ctx.evaluacion_variable()) {
            tipoArgumento = visitEvaluacion_variable(variable);
            //comprobamos los tipos de argumentos recibidos con los del programa declarado
            if (!tipoArgumento.equals(subprograma.getEntrada().get(indexArgumento).getTipo()))
                throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitSubprograma_declarado: tipo arg inválido orig: " +
                        subprograma.getEntrada().get(indexArgumento).getTipo() + " , recib: " + tipoArgumento);

            /*
            if (variable.subprograma() != null &&
                    //scopeGlobal.getSubprograma(variable.subprograma().IDENT().getText()).esFuncion() &&
                    !getGlobalScope().getSubprograma(variable.subprograma().getChild(0).getText()).esArgumento())
                throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitSubprograma_declarado: argumento parametro funcion DEV > 1 como parametro");
            */
            System.out.println("[ANALIZADOR SEMANTICO] visitSubprograma_declarado:" + variable.getText());
            indexArgumento++;
        }

        if (subprograma.esArgumento())
            tipoRes = subprograma.getSalida().get(0).getTipo();

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
        System.out.println("[ANALIZADOR SEMANTICO] visitEvaluacion_variable: " + ctx.getText());
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

    public String visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        String tipoVariable = (String) visit(ctx.variable());
        String tipoAcceso = (String) visit(ctx.operacion());
        if (!tipoAcceso.equals("Integer")) {
            throw new IllegalArgumentException("[ANALIZADOR SEMANTICO] visitVariable_acceso: Indice de acceso a secuencia no numérico");
        }
        else if (!tipoVariable.contains("ArrayList")) {
            throw new IllegalStateException("La variable " + ctx.variable().IDENT() + " no es de tipo secuencia");
        }
        System.out.println("[ANALIZADOR SEMANTICO] visitVariable_acceso: " + ctx.getText() + " tipo " + tipoVariable.replace("ArrayList<", "").replace(">", ""));
        return tipoVariable.replace("ArrayList<", "").replace(">", "");
    }

    public String visitVariable (Anasint.VariableContext ctx) {
        Scope scope = getUpperScope(ctx);
        System.out.println("[ANALIZADOR SEMANTICO] visitVariable: " + ctx.getText() + " on scope ");
        //System.out.println("PARENT=?=?" + ctx.getParent().getClass() + " to " + Anasint.Operando_universalContext.class);

        if (!scope.existeVariable(ctx.IDENT().getText()))
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitVariable Variable " + ctx.getText() + " no declarada");

        //if (variablesAInicializar.contains(ctx.getText()))
        //    throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitVariable Variable " + ctx.getText() + " no inicializada, no se puede asignar a si misma");

        if (!scope.getVariable(ctx.getText()).isInicializada() &&
                compruebaVariableInicializada)
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitVariable Variable " + ctx.getText() + " no inicializada, no se puede asignar a si misma");

        //throw new IllegalStateException("Variable " + ctx.getText() + " no inicializada");

        //hay que distinguir si estamos en scope global o no
        //durante declaracion de subprogramas no mirar valores globales

        //ESTO ES MENTIRA, NO ES COSA DEL SEMANTICO
        //if (!scope.getVariable(ctx.getText()).isInicializada())
        //    return "Indefinido";

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
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitSubprograma_vacia: operador vacia solo válido en secuencias");
        return "Boolean";
    }

    public String visitSubprograma_mostrar (Anasint.Subprograma_mostrarContext ctx) {
        String tipoArgumento = visitEvaluacion_variable(ctx.evaluacion_variable());
        if (ctx.getParent().getClass().equals(Anasint.Evaluacion_variableContext.class))
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitSubprograma_mostrar: Procedimiento mostrar no válido como evaluación");
        if (!tipoArgumento.contains("ArrayList"))
            throw new IllegalStateException("[ANALIZADOR SEMANTICO] visitSubprograma_mostrar: operador mostrar solo válido en secuencias");
        //return tipoArgumento;
        return null;
    }

}