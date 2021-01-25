import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitorInterprete extends AnasintBaseVisitor<Object> {
    Programa programa = new Programa("Main", "Main");
    String scope = null;
    ParseTreeProperty<Map<String, Object>> memoria = new ParseTreeProperty<>();
    ParseTreeProperty<Object> retornosFuncion = new ParseTreeProperty<>();
    boolean retorno = false;
    boolean ruptura = false;
    Map<String, Boolean> resultadosAsertos = new HashMap<>();

    private Boolean resuelveOperadorLogico (Object a, Object b, Anasint.Operador_logico_2_arioContext ctx) {
        String operador = ctx.getText();
        boolean res = false;

        if (a == null || b == null) {
            System.out.println("resuelveOperadorLogico Evaluando " + a + " vs " + b + "a nulo...");
            return false;
        } else {
            switch (operador) {
                case "==" -> res = a.equals(b);
                case "!=" -> res = !a.equals(b);
                case ">=" -> res = (Integer) a >= (Integer) b;
                case ">" -> res = (Integer) a > (Integer) b;
                case "<=" -> res = (Integer) a <= (Integer) b;
                case "<" -> res = (Integer) a < (Integer) b;
            }
            System.out.println("resuelveOperadorLogico Evaluando " + a + " vs " + b + "(" + res + ")");
            return res;
        }
    }
    private Integer resuelveOperadorAritmetico (Object a, Object b, String operador) {
        int res = 0;
        switch (operador) {
            case "+" -> res = (Integer) a + (Integer) b;
            case "-" -> res = (Integer) a - (Integer) b;
            case "*" -> res = (Integer) a * (Integer) b;
        }
        return res;
    }
    private Boolean resuelveOperadorCondicion (Object a, Object b, Anasint.Operador_condicion_2_arioContext ctx) {
        String operador = ctx.getText();
        boolean res = false;
        System.out.println("resuelveOperadorCondicion Evaluando " + a + " vs " + b);

        if (a == null || b == null) {
            System.out.println("Resolucion condicion fallida");
            return false;
        } else {
            switch (operador) {
                case "&&" -> res = (Boolean) a && (Boolean) b;
                case "||" -> res = (Boolean) a || (Boolean) b;
            }
            return res;
        }
    }

    private ParserRuleContext closestInstruccionBlock (ParserRuleContext ctx) {
        if (ctx.getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx;
        else
            return closestInstruccionBlock(ctx.getParent());
    }
    private ParserRuleContext closestBreakBlock (ParserRuleContext ctx) {
        if (//ctx.getParent().getClass().equals(Anasint.Bloque_funcionContext.class) ||
                ctx.getClass().equals(Anasint.Instruccion_controlContext.class) ||
                        ctx.getClass().equals(Anasint.Instruccion_bucleContext.class) ||
                        ctx.getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx;
        else
            return closestBreakBlock(ctx.getParent());
    }

    public Object visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        memoria.put(ctx.bloque_instrucciones(), new OrderedHashMap<>());
        programa.declaraSubprogramaNativo("vacia", "Funcion");
        programa.declaraSubprogramaNativo("ultima_posicion", "Funcion");
        programa.declaraSubprogramaNativo("mostrar", "Procedimieneto");

        return super.visitBloque_programa(ctx);
    }
    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        //si es el bloque instrucciones de programa, inicializamos
        //en la memoria las variables; en el resto de caso, es decir,
        //en una invocacion de subprograma, se hace antes de invocar
        //no aqui
        Object retornoInstruccion = null;
        List<Variable> variablesPrograma = programa.getVariables();
        if (ctx.getParent().getClass().equals(Anasint.Bloque_programaContext.class))
            for (Variable variable: variablesPrograma)
                memoria.get(ctx).put(variable.getNombre(), null);
        for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
            retornoInstruccion = visit(instruccion);
            if (retorno || ruptura) {
                break;
            }
        }
        retorno = false;
        ruptura = false;
        return retornoInstruccion;
    }
    public Object visitBloque_procedimiento (Anasint.Bloque_procedimientoContext ctx) {
        String nombreProcedimiento = ctx.IDENT().getText();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> internas = visitBloque_variables(ctx.bloque_variables());
        if (ctx.lista_variables_tipadas() != null)
            entrada.addAll(visitLista_variables_tipadas(ctx.lista_variables_tipadas()));
        Programa procedimiento = new Programa(nombreProcedimiento, "Procedimiento");
        procedimiento.declaraVarsEntrada(entrada);
        procedimiento.declaraVarsInternas(internas);
        procedimiento.setPuntero(ctx.bloque_instrucciones());
        if (!programa.existeSubprograma(nombreProcedimiento)) {
            programa.declaraSubprograma(procedimiento);
        }
        return null;
    }
    public Object visitBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        String nombreFuncion = ctx.IDENT().getText();

        List<Anasint.Lista_variables_tipadasContext> variablesEntradaSalida =  ctx.lista_variables_tipadas();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> salida = new ArrayList<>();
        List<Variable> internas = visitBloque_variables(ctx.bloque_variables());

        if (variablesEntradaSalida.size() == 2) {
            entrada.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(1)));
        } else
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));

        Programa funcion = new Programa(nombreFuncion, "Funcion");
        funcion.declaraVarsEntrada(entrada);
        funcion.declaraVarsSalida(salida);
        funcion.declaraVarsInternas(internas);
        funcion.setPuntero(ctx.bloque_instrucciones());
        programa.declaraSubprograma(funcion);
        programa.getSubprograma(nombreFuncion).setPuntero(ctx.bloque_instrucciones());

        return 1;
    }
    public List<Variable> visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        List<Variable> variables = new ArrayList<>();

        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable()) {
            variables.addAll(visitDeclaracion_variable(variable));
        }

        //esto significa que el bloque programa/subprograma
        // tiene bloque instrucciones. colocamos memoria en el
        // con las variables que hemos declarado inic. a null
            //memoria.put(bloque_instrucciones, new OrderedHashMap<>());

        if (ctx.getParent().getClass().equals(Anasint.Bloque_programaContext.class)) {
            programa.declaraVariables(variables);
        }
        return variables;
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

    public Object visitInstruccion (Anasint.InstruccionContext ctx) {
        if (retorno) {
            System.out.println("[INTERPRETE] visitInstruccion RETORNO memoria de " + ctx.getText() + " " + retornosFuncion.get(closestInstruccionBlock(ctx)).toString());
            System.out.println("[INTERPRETE] visitInstruccion RETORNO La instruccion no deberia visitarse, un retorno se ha visitado ya " + ctx.getText());
            return null;
        }
        return super.visit(ctx);
    }
    public Object visitInstruccion_llamada_subprograma (Anasint.Instruccion_llamada_subprogramaContext ctx) {
        return visit(ctx.subprograma());
    }
    public Object visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        ParserRuleContext closest = closestBreakBlock(ctx);
        ruptura = true;
        System.out.println("[INTERPRETE] Hemos puesto ruptura en " + closest.getClass());
        //rupturaBloque.put(closestBreakBlock(ctx), true);
        return 1;
    }
    // TODO esto no deberia hacerse aqui, solo funciona para bucles!
    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        //System.out.println("[INTERPRETE] visitInstruccion_bucle " + ctx.predicado().getText());
        Object retornoInstruccion = null;
        boolean tieneAvance = ctx.subprograma() != null;
        boolean avanceValido = true;
        Programa subprogramaAvance;
        Integer valorAvance;
        Integer nValorAvance = Integer.MAX_VALUE;
        //if (tieneAvance)
        //    subprogramaAvance = scopeGlobal.getSubprograma(ctx.subprograma().getChild(0).getText());
        while (!ruptura) {
            Boolean resultado = visitPredicado(ctx.predicado());
            if (resultado == null || !resultado)
                break;
            System.out.println("[INTERPRETE] visitInstruccion_bucle predicado (" +
                    ctx.predicado().getText() + ") = " +
                    visitPredicado(ctx.predicado()));

            //aqui vamos a ejecutar la funcion de avance
            if (tieneAvance && avanceValido) {
                valorAvance = nValorAvance;
                nValorAvance = (Integer) visit(ctx.subprograma());
                avanceValido = (nValorAvance < valorAvance) && nValorAvance >= 0;
                System.out.println("[INTERPRETE] visitInstruccion_bucle: función de avance (" + nValorAvance + " < " + valorAvance + ")");
                if (!avanceValido) {
                    System.out.println("[INTERPRETE] visitInstruccion_bucle: función de avance no válida! (" + nValorAvance + " >= " + valorAvance + ")");
                    break;
                }
            }

            for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
                System.out.println("[INTERPRETE] visitInstruccion_bucle: " + instruccion.getText());
                retornoInstruccion = visit(instruccion);
                if (retorno) {
                    retorno = false;
                    break;
                }
                else if (ruptura) {
                    ruptura = false;
                    break;
                }
            }
        }
        return retornoInstruccion;
    }
    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        List<Anasint.InstruccionContext> instruccionesSi = new ArrayList<>();
        List<Anasint.InstruccionContext> instruccionesSino = new ArrayList<>();
        boolean encontradoSino = false;
        Object retornoInstruccion = null;
        //dividimos las instrucciones en dos partes, si/sino
        for (ParseTree hijo: ctx.children) {
            if (hijo.equals(ctx.SINO()))
                encontradoSino = true;
            if (Anasint.InstruccionContext.class.isAssignableFrom(hijo.getClass())) {
                if (!encontradoSino)
                    instruccionesSi.add((Anasint.InstruccionContext) hijo);
                else
                    instruccionesSino.add((Anasint.InstruccionContext) hijo);
            }
        }
        //si el predicado es cierto (condicion), entramos a las instrucciones
        Boolean valorPredicado = visitPredicado(ctx.predicado());
        System.out.println("[INTERPRETE] visitInstruccion: " + ctx.getText() + " + valor predicado " + ctx.predicado().getText() + " = " + valorPredicado);
        if (valorPredicado != null && valorPredicado)
            //se visitan instrucciones si
            for (Anasint.InstruccionContext instruccion: instruccionesSi) {
                System.out.println("[INTERPRETE] visitInstruccion: instr si entonces ");
                retornoInstruccion = visitInstruccion(instruccion);
                if (retorno) {
                    retorno = false;
                    System.out.println("[INTERPRETE] visitInstruccion_control NO visitamos la instruccion porque tiene retorno");
                    break;
                }
                else if (ruptura) {
                    ruptura = false;
                    System.out.println("[INTERPRETE] visitInstruccion_control NO visitamos la instruccion porque tiene ruptura");
                    break;
                }
            }
            //se visitan las instrucciones sino
        else
            for (Anasint.InstruccionContext instruccion: instruccionesSino) {
                retornoInstruccion = visitInstruccion(instruccion);
                if (retorno) {
                    retorno = false;
                    System.out.println("[INTERPRETE] visitInstruccion_control NO visitamos la instruccion porque tiene retorno");
                    break;
                }
                else if (ruptura) {
                    ruptura = false;
                    System.out.println("[INTERPRETE] visitInstruccion_control NO visitamos la instruccion porque tiene ruptura");
                    break;
                }
                System.out.println("[INTERPRETE] visitInstruccion_control instr sino " + instruccion.getText());
            }
        return retornoInstruccion;
    }
    public Object visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        List<Object> valoresRetorno = new ArrayList<>();
        for (Anasint.Evaluacion_variableContext evalVariable: ctx.evaluaciones_variables().evaluacion_variable())
            valoresRetorno.add(visitEvaluacion_variable(evalVariable));

        retornosFuncion.put(closestInstruccionBlock(ctx), valoresRetorno);
        retorno = true;
        System.out.println("[INTERPRETE] visitInstruccion_retorno " + valoresRetorno.toString());
        return valoresRetorno;
    }
    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        List<Object> valores = new ArrayList<>();
        String nombre;
        //lo enchufamos al bloque padre (instruccion)
        int i = 0;

        //por cada eval. de variable, añadimos su valor al arr
        for (Anasint.Evaluacion_variableContext evalr: ctx.evaluaciones_variables().evaluacion_variable()) {
            if (evalr.subprograma() != null && evalr.subprograma().getClass().equals(Anasint.Subprograma_declaradoContext.class)) {
                nombre = evalr.subprograma().getChild(0).getText();
                Object retornoFuncion = visitEvaluacion_variable(evalr);
                if (programa.getSubprograma(nombre).getVarsSalida().size() > 1)
                    valores.addAll((List<Object>) retornoFuncion);
                else
                    valores.add(retornoFuncion);
            }
            else
                valores.add(visitEvaluacion_variable(evalr));
        }

        //acceder caso especial funcion ??
        //ahora vamos variable por variable y asignamos; si es un acceso
        //a lista tenemos que modificar solo el elemento indicado
        for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
            String nombreVariable = variable.getChild(0).getText();
            if (variable.getClass().equals(Anasint.Variable_accesoContext.class)) {
                Integer selectorSecuencia = (Integer) visit(variable.getChild(2));
                //memoria.get(closestInstruccionBlock(variable)).put(nombreVariable, valores.get(i));
                List<Object> secuencia = (List<Object>) memoria.get(closestInstruccionBlock(variable)).get(nombreVariable);
                secuencia.set(selectorSecuencia, valores.get(i));
                memoria.get(closestInstruccionBlock(variable)).put(nombreVariable, secuencia);
            } else
                memoria.get(closestInstruccionBlock(variable)).put(nombreVariable, valores.get(i));
            i++;
        }

        System.out.println("[INTERPRETE] Datos de la memoria " + ctx.getParent().getClass() + " , " + memoria.get(closestInstruccionBlock(ctx)));
        return 1;
    }
    public Boolean visitInstruccion_aserto_simple (Anasint.Instruccion_aserto_simpleContext ctx) {
        Boolean resultado = (Boolean) visit(ctx.predicado());
        if (resultado == null)
            throw new IllegalStateException("[INTERPRETE] visitInstruccion_aserto_simple: aserto no válido (ha devuelto indefinido) " + ctx.predicado().getText());
        //else
        //    resultadosAsertos.put( + "_" + ctx.getText(), resultado);
        return resultado;
    }
    public Object visitInstruccion_aserto_cuantificado (Anasint.Instruccion_aserto_cuantificadoContext ctx) {
        Map<String, Object> mem = memoria.get(closestInstruccionBlock(ctx));
        //cargamos valor memoria variable
        Integer operacionA = (Integer) visit(ctx.operacion(0));
        Integer operacionB = (Integer) visit(ctx.operacion(1));
        boolean paratodo = !ctx.cuantificador().getTokens(Anasint.PARATODO).isEmpty();
        //si paratodo, inicializamos true
        //caso contrario false
        Boolean asertoValido = paratodo;

        for (int i = operacionA; i < operacionB; i++) {
            mem.put(ctx.variable().getText(), i);
            asertoValido = (Boolean) visit(ctx.predicado());
            if (asertoValido == null)
                throw new IllegalStateException("[INTERPRETE] Aserto (" + ctx.getText() + ") con resultado indefinido, aserto no válido!!");
            else if ((!asertoValido && paratodo) || (asertoValido && !paratodo))
                break;
        }
        if (!asertoValido)
            throw new IllegalStateException("[INTERPRETE] Aserto (" + ctx.getText() + ") con resultado falso, programa no válido!!");
        //resultadosAsertos.put(currentTimeMillis() + "_" + ctx.getText(), asertoValido);
        mem.remove(ctx.variable().getText());
        return null;
    }

    public Boolean visitPredicado (Anasint.PredicadoContext ctx) {
        return (Boolean) super.visit(ctx);
    }
    public Boolean visitPredicado_negado (Anasint.Predicado_negadoContext ctx) {
        return !visitPredicado(ctx.predicado());
    }
    public Boolean visitPredicado_envuelto (Anasint.Predicado_envueltoContext ctx) {
        return visitPredicado(ctx.predicado());
    }
    public Boolean visitPredicado_base (Anasint.Predicado_baseContext ctx) {
        return visitCondicion(ctx.condicion());
    }
    public Boolean visitPredicado_rec(Anasint.Predicado_recContext ctx) {
        Boolean predicadoA = visitPredicado(ctx.predicado(0));
        Boolean predicadoB = visitPredicado(ctx.predicado(1));
        System.out.println("predicado rec me llega " + predicadoA  + " " + predicadoB + " " + ctx.getText());
        return resuelveOperadorCondicion(predicadoA,
                predicadoB, ctx.operador_condicion_2_ario());
    }

    public Boolean visitCondicion (Anasint.CondicionContext ctx) {
        return (Boolean) super.visit(ctx);
    }
    public Object visitCondicion_base (Anasint.Condicion_baseContext ctx) {
        return visit(ctx.getChild(0));
    }
    public Boolean visitCondicion_envuelta (Anasint.Condicion_envueltaContext ctx) {
        return (Boolean) visit(ctx.condicion());
    }
    public Boolean visitCondicion_rec (Anasint.Condicion_recContext ctx) {
        Object condicionA = visit(ctx.condicion(0));
        Object condicionB = visit(ctx.condicion(1));
        return resuelveOperadorLogico(condicionA, condicionB, ctx.operador_logico_2_ario());
    }
    public Boolean visitCondicion_cierto (Anasint.Condicion_ciertoContext ctx) {
        return true;
    }
    public Boolean visitCondicion_falso (Anasint.Condicion_falsoContext ctx) {
        return false;
    }

    public Boolean visitValor_booleano_true (Anasint.Valor_booleano_trueContext ctx) {
        return true;
    }
    public Boolean visitValor_booleano_false (Anasint.Valor_booleano_falseContext ctx) {
        return false;
    }
    public Integer visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return Integer.valueOf(ctx.NUMERO().getText());
    }
    public Object visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        System.out.println(ctx.getText());
        List<Object> variable = (List<Object>) memoria.get(closestInstruccionBlock(ctx)).get(ctx.getChild(0).getText());;
        Integer indice = (Integer) visit(ctx.operacion());
        Object res;
        try {
            res = variable.get(indice);
        } catch (Exception e) {
            System.out.println("[INTERPRETE] visitVariable_acceso: acceso a lista fuera de su rango");
            res = null;
        }
        return res;
    }

    public Object visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        return super.visitEvaluacion_variable(ctx);
    }
    public Object visitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String nombre = ctx.IDENT().getText();
        scope = nombre;
        //String nombre = ctx.getChild(0).getText();
        Programa subprograma = programa.getSubprograma(nombre);
        System.out.println("Visitando programa " + ctx.getText() + " tiene memoria? " + memoria.get(subprograma.getPuntero()));

        // Cada vez que entramos en la funcion, limpiamos los datos del nodo
        //retornosFuncion.removeFrom(subprograma.getPuntero());
        memoria.removeFrom(subprograma.getPuntero());
        Map<String, Object> memoriaGlobal = memoria.get(closestInstruccionBlock(ctx));
        List<Variable> argumentosDeclarados = subprograma.getVarsEntrada();
        Map<String, Object> memoriaSubprograma = new OrderedHashMap<>();
        List<Object> valoresInvocacionSubprograma = new ArrayList<>();

        //estos son los valores con los que se ha invocado al subprograma
        for (Anasint.Evaluacion_variableContext valorArgumento :ctx.evaluacion_variable())
            valoresInvocacionSubprograma.add(visitEvaluacion_variable(valorArgumento));

        int i = 0;

        //estas son las variables definidas del programa en scope
        for (Variable variableDeclarada: programa.getSubprograma(nombre).getVarsInternas())
            memoriaSubprograma.put(variableDeclarada.getNombre(), null);

        //hay que cargar tambien los valores de la memoria padre
        for (String variableGlobal: memoriaGlobal.keySet())
            memoriaSubprograma.put(variableGlobal, memoriaGlobal.get(variableGlobal));

        //estas son las variables de entrada del subprograma
        for (Variable argumentoDeclarado: argumentosDeclarados) {
            memoriaSubprograma.put(argumentoDeclarado.getNombre(), valoresInvocacionSubprograma.get(i));
            i++;
        }

        //aqui cargamos la memoria del subprograma precargada
        memoria.put(subprograma.getPuntero(), memoriaSubprograma);

        //ejecutamos el bloque de instrucciones del programa
        Object salidaSubprograma = visit(subprograma.getPuntero());

        //actualizamos las variables globales, prevalecen privadas
        //si tienen mismo nombre que globales
        for (String nombreVariable: memoriaSubprograma.keySet())
            //la variable privada prevalece a la global
            if (!programa.getSubprograma(nombre).existeVariable(nombreVariable) &&
                programa.existeVariable(nombreVariable))
                memoriaGlobal.put(nombreVariable, memoriaSubprograma.get(nombreVariable));

        //si es funcion, analizamos si retorno multiple o no
        scope = null;
        if (salidaSubprograma != null && subprograma.esFuncion(null))
            if (subprograma.getVarsSalida().size() > 1)
                return salidaSubprograma;
            else
                return ((List<Object>) salidaSubprograma).get(0);
        else
            return null;
    }
    public Object visitVariable_simple (Anasint.Variable_simpleContext ctx) {
        return memoria.get(closestInstruccionBlock(ctx)).get(ctx.getText());
    }

    public Integer visitSubprograma_ultima_posicion (Anasint.Subprograma_ultima_posicionContext ctx) {
        Integer pos = ((List<Object>) visit(ctx.evaluacion_variable())).size() - 1;
        System.out.println("visitUltima_posicion es " + ctx.getText() + " = " + pos);
        return pos;
    }
    public Boolean visitSubprograma_vacia (Anasint.Subprograma_vaciaContext ctx) {
        List<Object> res = (List<Object>) visitEvaluacion_variable(ctx.evaluacion_variable());
        return (res == null || res.isEmpty());
    }

    public Object visitSubprograma_mostrar (Anasint.Subprograma_mostrarContext ctx) {
        List<Object> secuencia = (List<Object>) visit(ctx.evaluacion_variable());
        System.out.println("[INTERPRETE] visitSubprograma_mostrar: " + secuencia);
        return null;
    }

    public Object visitOperando_subprograma (Anasint.Operando_subprogramaContext ctx) {
        return visit(ctx.subprograma());
    }
    public List<Object> visitOperando_secuencia_llena (Anasint.Operando_secuencia_llenaContext ctx) {
        List<Object> secuencia = new ArrayList<>();
        for (Anasint.OperacionContext operacion: ctx.operacion())
            secuencia.add(visit(operacion));
        return secuencia;
    }/*
    public List<Boolean> visitOperando_secuencia_logica (Anasint.Operando_secuencia_logicaContext ctx) {
        List<Boolean> secuencia = new ArrayList<>();
        for (Anasint.OperandoContext booleano: ctx.operando())
            secuencia.add(booleano.getText().equals("T"));
        return secuencia;
    }*/
    public Object visitOperando_secuencia_vacia (Anasint.Operando_secuencia_vaciaContext ctx) {
        return new ArrayList<>();
    }

    public Integer visitOperacion (Anasint.OperacionContext ctx) {
        return (Integer) super.visit(ctx);
    }
    public Integer visitOp_aritmetica_envuelta (Anasint.Op_aritmetica_envueltaContext ctx) {
        return visitOperacion(ctx.operacion());
    }
    public Integer visitOp_aritmetica_negacion (Anasint.Op_aritmetica_negacionContext ctx) {
        return visitOperacion(ctx.operacion()) * -1;
    }
    public Integer visitOp_aritmetica_mult (Anasint.Op_aritmetica_multContext ctx) {
        return resuelveOperadorAritmetico(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                ctx.getChild(1).getText());
    }
    public Integer visitOp_aritmetica_sr (Anasint.Op_aritmetica_srContext ctx) {
        return resuelveOperadorAritmetico(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                ctx.getChild(1).getText());
    }
}