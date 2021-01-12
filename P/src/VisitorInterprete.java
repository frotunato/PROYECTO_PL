import Valor.Valor;
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
    Scope scopeGlobal = new Scope("global");

    //ParseTreeProperty<List<Valor>> valorOperaciones = new ParseTreeProperty<>();
    ParseTreeProperty<Map<String, Valor>> memoria = new ParseTreeProperty<>();
    ParseTreeProperty<Valor> retornosFuncion = new ParseTreeProperty<>();
    ParseTreeProperty<Boolean> rupturaBloque = new ParseTreeProperty<>();

    /*
    private Scope getGlobalScope () {
        return scopeTree.get(raiz).get("global");
    }
    */

    private ParserRuleContext closestInstruccionBlock (ParserRuleContext ctx) {
        if (ctx.getParent().getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx.getParent();
        else
            return closestInstruccionBlock(ctx.getParent());
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
        //List<Variable> variables = visitBloque_variables(ctx.bloque_variables());
        //Scope scopeGlobal = new Scope("global");
        //scopeGlobal.declaraVariables(variables);

        scopeGlobal.declaraSubprogramaNativo("vacia", "Funcion");
        scopeGlobal.declaraSubprogramaNativo("ultima_posicion", "Funcion");
        scopeGlobal.declaraSubprogramaNativo("mostrar", "Procedimieneto");

        //raiz = ctx;
        //Map<String, Scope> scopes = new OrderedHashMap<>();
        //scopes.put(scopeGlobal.getNombre(), scopeGlobal);
        //scopeTree.get("global").put(ctx, scopeGlobal);
        //scopeTree.put(raiz, scopes);
        return super.visitBloque_programa(ctx);
    }

    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        if (memoria.get(ctx) == null)
            memoria.put(ctx, new HashMap<>());
        for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
            if (instruccion.instruccion_ruptura() != null)
                break;
            visit(instruccion);
        }
        //return super.visitBloque_instrucciones(ctx);
        return 1;
    }

    public Object visitBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        String nombreFuncion = ctx.IDENT().getText();

        List<Anasint.Lista_variables_tipadasContext> variablesEntradaSalida =  ctx.lista_variables_tipadas();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> salida = new ArrayList<>();

        if (variablesEntradaSalida.size() == 2) {
            entrada.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(1)));
        } else
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));

        scopeGlobal.declaraSubprograma(nombreFuncion, "Funcion", entrada, salida);
        scopeGlobal.getSubprograma(nombreFuncion).setPuntero(ctx.bloque_instrucciones());

        return 1;
    }

    public List<Variable> visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        List<Variable> variables = new ArrayList<>();

        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable()) {
            variables.addAll(visitDeclaracion_variable(variable));
        }
        return variables;
        //return super.visitBloque_variables(ctx);
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

    public Object visitInstruccion (Anasint.InstruccionContext ctx) {
        //esto hace que ignoremos todas las intrucciones detras de ruptura
        //a nivel de bloque instruccion mas externo
        //if (rupturaBloque.get(ctx.getParent()) != null && rupturaBloque.get(ctx.getParent())) {
        //    System.out.println("[INTERPRETE] visitBloque_instrucciones RUPTURA " + ctx.getText());
        //rupturaBloque.removeFrom(ctx);
        //    return 1;
        //}

        if (retornosFuncion.get(closestInstruccionBlock(ctx)) != null) {
            System.out.println("[INTERPRETE] visitBloque_instrucciones RETORNO memoria de " + ctx.getText() + " " + retornosFuncion.get(closestInstruccionBlock(ctx)).toString());
            System.out.println("[INTERPRETE] visitBloque_instrucciones RETORNO La instruccion no deberia visitarse, un retorno se ha visitado ya " + ctx.getText());
            return 1;
        }

        //if (ctx.instruccion_retorno() != null)
        //    return visitInstruccion_retorno(ctx.instruccion_retorno());
        return super.visitInstruccion(ctx);
    }

    public Object visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        ParserRuleContext closest = closestBreakBlock(ctx);
        System.out.println("[INTERPRETE] Hemos puesto ruptura en " + closest.getClass());
        //rupturaBloque.put(closestBreakBlock(ctx), true);
        return 1;
    }

    // esto no deberia hacerse aqui, solo funciona para bucles!
    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        //System.out.println("[INTERPRETE] visitInstruccion_bucle " + ctx.predicado().getText());
        boolean ruptura = false;
        while (!ruptura && visitPredicado(ctx.predicado()).getValorBooleano()) {
            System.out.println("[INTERPRETE] visitInstruccion_bucle predicado (" +
                    ctx.predicado().getText() + ") = " +
                    visitPredicado(ctx.predicado()).getValorBooleano());
            for (Anasint.InstruccionContext instruccion: ctx.instruccion())
                if (instruccion.instruccion_retorno() != null)
                    return 1;
                else if (instruccion.instruccion_ruptura() != null) {
                    ruptura = true;
                    break;
                }
                else
                    visitInstruccion(instruccion);
        }
        return 1;
    }

    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        List<Anasint.InstruccionContext> instruccionesSi = new ArrayList<>();
        List<Anasint.InstruccionContext> instruccionesSino = new ArrayList<>();
        boolean encontradoSino = false;

        //dividimos las instrucciones en dos partes, si/sino
        for (ParseTree hijo: ctx.children) {
            if (hijo.equals(ctx.SINO()))
                encontradoSino = true;
            if (hijo.getClass().equals(Anasint.InstruccionContext.class)) {
                if (!encontradoSino)
                    instruccionesSi.add((Anasint.InstruccionContext) hijo);
                else
                    instruccionesSino.add((Anasint.InstruccionContext) hijo);
            }
        }
        //si el predicado es cierto (condicion), entramos a las instrucciones
        boolean valorPredicado = visitPredicado(ctx.predicado()).getValorBooleano();
        System.out.println("[INTERPRETE] visitInstruccion valor predicado " + ctx.predicado().getText() + " = " + valorPredicado);
        if (valorPredicado)
            //se visitan instrucciones si
            for (Anasint.InstruccionContext instruccion: instruccionesSi) {
                if (instruccion.instruccion_ruptura() != null) {
                    System.out.println("[INTERPRETE] visitInstruccion_control NO visitamos la instruccion porque tiene ruptura");
                    break;
                }

                visitInstruccion(instruccion);
                System.out.println("[INTERPRETE] visitInstruccion_control instr si " + instruccion.getText());
            }
            //se visitan las instrucciones sino
        else
            for (Anasint.InstruccionContext instruccion: instruccionesSino) {
                if (instruccion.instruccion_ruptura() != null) {
                    System.out.println("[INTERPRETE] visitInstruccion_control NO visitamos la instruccion porque tiene ruptura");
                    break;
                }
                visitInstruccion(instruccion);
                System.out.println("[INTERPRETE] visitInstruccion_control instr sino " + instruccion.getText());
            }
        return 1;
    }

    public Valor visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        Valor res;

        List<Valor> valoresRetorno = new ArrayList<>();

        for (Anasint.Evaluacion_variableContext evalVariable: ctx.evaluaciones_variables().evaluacion_variable()) {
            valoresRetorno.add(visitEvaluacion_variable(evalVariable));
        }

        res = valoresRetorno.get(0);
        res.setValores(valoresRetorno);

        retornosFuncion.put(closestInstruccionBlock(ctx), res);

        System.out.println("[INTERPRETE] visitInstruccion_retorno " + res);
        return res;
    }

    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        List<Valor> valores = new ArrayList<>();
        //List<Variable> variables = new ArrayList<>();

        //lo enchufamos al bloque padre (instruccion)
        int i = 0;

        for (Anasint.Evaluacion_variableContext evalr: ctx.evaluaciones_variables().evaluacion_variable()) {
            if (evalr.subprograma() != null)
                valores.addAll(visitEvaluacion_variable(evalr).getValores());
            else
                valores.add(visitEvaluacion_variable(evalr));
            //valores.addAll(valorOperaciones.get(evalr));
        }

        //acceder caso especial funcion
        for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
            String nombreVariable = variable.getText();
            memoria.get(closestInstruccionBlock(variable)).put(nombreVariable, valores.get(i));
            i++;
        }

        System.out.println("[INTERPRETE] Datos de la memoria " + ctx.getParent().getClass() + " , " + memoria.get(closestInstruccionBlock(ctx)));
        return 1;
    }

    public Valor visitSubprograma (Anasint.SubprogramaContext ctx) {

        String nombre = ctx.IDENT().getText();
        Subprograma subprograma = scopeGlobal.getSubprograma(nombre);
        System.out.println("Visitando programa " + ctx.getText() + " tiene memoria? " + memoria.get(subprograma.getPuntero()));

        //Cada vez que entramos en la funcion, limpiamos los datos del nodo
        retornosFuncion.removeFrom(subprograma.getPuntero());
        memoria.removeFrom(subprograma.getPuntero());
        //rupturaBloque.removeFrom
        ////Valor res;
        // hay que hacer una memoria especial con los parametros de entrada
        // cargados con los valores de parametros


        List<Variable> argumentosDeclarados = subprograma.getEntrada();
        Map<String, Valor> memoriaSubprograma = new OrderedHashMap<>();
        List<Valor> valoresInvocacionSubprograma = new ArrayList<>();

        for (Anasint.Evaluacion_variableContext valorArgumento :ctx.evaluacion_variable()) {
            valoresInvocacionSubprograma.add(visitEvaluacion_variable(valorArgumento));
        }

        int i = 0;

        for (Variable argumentoDeclarado: argumentosDeclarados) {
            memoriaSubprograma.put(argumentoDeclarado.getNombre(), valoresInvocacionSubprograma.get(i));
            i++;
        }

        memoria.put(subprograma.getPuntero(), memoriaSubprograma);
        visitBloque_instrucciones((Anasint.Bloque_instruccionesContext) subprograma.getPuntero());

        //visitBloque_instrucciones((Anasint.Bloque_instruccionesContext) scopeTree.get(raiz).get(ctx.IDENT().getText()).getPuntero());
        //List<Valor> valoresRetorno = new ArrayList<Valor>(memoria.get(subprograma.getPuntero()).values());
        //res = valoresRetorno.get(0);
        //res.setValores(valoresRetorno);

        return retornosFuncion.get(subprograma.getPuntero());
    }

    public Valor visitOperando (Anasint.OperandoContext ctx) {
        return (Valor) super.visit(ctx);
    }
    public Valor visitValor_booleano (Anasint.Valor_booleanoContext ctx) {
        return (Valor) super.visit(ctx);
    }

    public Valor visitValor_booleano_true (Anasint.Valor_booleano_trueContext ctx) {
        return new Valor(true);
    }
    public Valor visitValor_booleano_false (Anasint.Valor_booleano_falseContext ctx) {
        return new Valor(false);
    }

    /*
    public Valor visitOperando_booleano (Anasint.Operando_booleanoContext ctx) {
        if (ctx.TRUE() != null)
            return new Valor(true);
        else
            return new Valor(false);
    }
*/
    public Valor visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return new Valor(Integer.valueOf(ctx.NUMERO().getText()));
    }

    public Valor visitCondicion (Anasint.CondicionContext ctx) {
        return (Valor) super.visit(ctx);
    }

    public Valor visitCondicion_base (Anasint.Condicion_baseContext ctx) {
        return visitOperacion(ctx.operacion());
    }

    public Valor visitCondicion_envuelta (Anasint.Condicion_envueltaContext ctx) {
        return (Valor) visit(ctx.condicion());
    }

    public Valor visitCondicion_rec (Anasint.Condicion_recContext ctx) {
        Valor condicionA = (Valor) visit(ctx.condicion(0));
        Valor condicionB = (Valor) visit(ctx.condicion(1));

        //System.out.println("visitDigualdad_caso_base de " + ctx.getText() + " res = " + predicadoA +" vs " + predicadoB +" computed " + predicadoA.equals(predicadoB));

        /////////arregaaaa
        //return new Valor(false);
        return resuelveOperadorLogico(condicionA, condicionB, ctx.operador_logico_2_ario());
    }


    public Valor visitPredicado (Anasint.PredicadoContext ctx) {
        return (Valor) super.visit(ctx);
    }

    public Valor visitPredicado_cierto (Anasint.Predicado_ciertoContext ctx) {
        return new Valor(true);
    }
    
    public Valor visitPredicado_falso (Anasint.Predicado_falsoContext ctx) {
        return new Valor(false);
    }
    
    public Valor visitPredicado_negado (Anasint.Predicado_negadoContext ctx) {
        Valor valorPredicado = visitPredicado(ctx.predicado());
        valorPredicado.setValorBooleano(!valorPredicado.getValorBooleano());
        return valorPredicado;
    }

    public Valor visitPredicado_envuelto (Anasint.Predicado_envueltoContext ctx) {
        return visitPredicado(ctx.predicado());
    }

    public Valor visitPredicado_base (Anasint.Predicado_baseContext ctx) {
        return visitCondicion(ctx.condicion());
    }


    public Valor visitPredicado_rec(Anasint.Predicado_recContext ctx) {
        Valor predicadoA = (Valor) visitPredicado(ctx.predicado(0));
        Valor predicadoB = (Valor) visitPredicado(ctx.predicado(1));
        Valor condicionActual = null;
        System.out.println("predicado rec me llega " + predicadoA  + " " + predicadoB + " " + ctx.getText());
        //List<Anasint.Operador_condicion_2_arioContext> condiciones = ctx.operador_condicion_2_ario();
        int i = 0;


        /*
        for (Anasint.CondicionContext condicion: ctx.condicion()) {
            if (condicionActual == null)
                condicionActual = (Valor) visit(condicion);
            else
                condicionActual = resuelveOperadorCondicion(condicionActual, (Valor) visit(condicion), condiciones.get(i));
            i++;
        }
        */

        //System.out.println("visitPredicado_doble de " + ctx.getText() + " res = " + predicadoA +" vs " + predicadoB +" computed " + predicadoA.equals(predicadoB));
        return resuelveOperadorCondicion(predicadoA,
                predicadoB, ctx.operador_condicion_2_ario());
    }

    public Valor visitVariable (Anasint.VariableContext ctx) {
        return memoria.get(closestInstruccionBlock(ctx)).get(ctx.getText());
    }

    public Valor visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        return (Valor) super.visitEvaluacion_variable(ctx);
    }

    private Valor resuelveOperadorLogico (Valor a, Valor b, Anasint.Operador_logico_2_arioContext ctx) {
        String operador = ctx.getText();
        boolean res = false;

        if (a.getEsNulo() || b.getEsNulo()) {
            System.out.println("resuelveOperadorLogico Evaluando " + a + " vs " + b + "a nulo...");

            return new Valor(false);
        } else {
            switch (operador) {
                case "==" -> res = a.equals(b);
                case "!=" -> res = !a.equals(b);
                case ">=" -> res = a.getValorNumerico() >= b.getValorNumerico();
                case ">" -> res = a.getValorNumerico() > b.getValorNumerico();
                case "<=" -> res = a.getValorNumerico() <= b.getValorNumerico();
                case "<" -> res = a.getValorNumerico() < b.getValorNumerico();
            }
            System.out.println("resuelveOperadorLogico Evaluando " + a + " vs " + b + "(" + res + ")");

            return new Valor(res);
        }
    }

    private Valor resuelveOperadorAritmetico (Valor a, Valor b, String operador) {
        int res = 0;
        //if (a == null || b == null)
        //    return new Valor(new Numero(0));
        switch (operador) {
            case "+" -> res = a.getValorNumerico() + b.getValorNumerico();
            case "-" -> res = a.getValorNumerico() - b.getValorNumerico();
            case "*" -> res = a.getValorNumerico() * b.getValorNumerico();
        }
        return new Valor(res);
    }

    private Valor resuelveOperadorCondicion (Valor a, Valor b, Anasint.Operador_condicion_2_arioContext ctx) {
        String operador = ctx.getText();
        boolean res = false;
        System.out.println("resuelveOperadorCondicion Evaluando " + a + " vs " + b);

        if (a.getValorBooleano() == null || b.getValorBooleano() == null) {
            System.out.println("Resolucion condicion fallida");
            return new Valor(false);
        } else {
            switch (operador) {
                case "&&" -> res = a.getValorBooleano() && b.getValorBooleano();
                case "||" -> res = a.getValorBooleano() || b.getValorBooleano();
            }
            return new Valor(res);
        }
    }

    public Valor visitOperacion (Anasint.OperacionContext ctx) {
        return (Valor) super.visit(ctx);
    }
    /*
        public Valor visitOperando_secuencia (Anasint.Operando_secuenciaContext ctx) {
            if (ctx.evaluacion_variable() != null)
        }
    */
/*
    public Valor visitOperando_booleano (Anasint.Operando_booleanoContext ctx) {
        return (Valor) visit(ctx.valor_booleano());
    }
*/
    public Valor visitOperando_secuencia_vacia (Anasint.Operando_secuencia_vaciaContext ctx) {
        return new Valor(new ArrayList<>());
    }
/*
    public Valor visitOperando_secuencia_logica (Anasint.Operando_secuencia_logicaContext ctx) {
        Valor res;
        List<Object> secuencia = new ArrayList<>();

        for (ParseTree valorBooleano: ctx.children) {
            if (valorBooleano.getText().equals("T"))
                secuencia.add(true);
            else if (valorBooleano.getText().equals("F"))
                secuencia.add(false);
            //if (valorBooleano.equals(ctx.TRUE))

        }
        //for Anasint.Operando_booleanoContext valorBooleano: ctx.
        //for (Anasint.Operando_booleanoContext valorBooleano: ctx.operando_booleano())
        //    secuencia.add(valorBooleano);
        return new Valor(secuencia);
    }
    */
/*
    public Valor visitUltima_posicion(Anasint.Ultima_posicionContext ctx) {
        Valor selector = visitEvaluacion_variable(ctx.evaluacion_variable());
        return new Valor(selector.getSecuenciaGenerica().get(selector.getSecuenciaGenerica().size() - 1));

    }
*/

    public Valor visitUltima_posicion (Anasint.Ultima_posicionContext ctx) {
        Valor ultimoValor;
        if (ctx.variable() != null)
            ultimoValor = visitVariable(ctx.variable()).getUltimoValorSecuencia();
        else
            ultimoValor = visitOperando_secuencia(ctx.operando_secuencia()).getUltimoValorSecuencia();
        System.out.println("visitUltima_posicion es " + ctx.getText() + " = " + ultimoValor);
        return ultimoValor;
    }

    public Valor visitVacia (Anasint.VaciaContext ctx) {
        Valor res = visitEvaluacion_variable(ctx.evaluacion_variable());
        boolean vaciaNum = res.getSecuenciaBooleana().size() == 0;
        boolean vaciaBool = res.getSecuenciaNumerica().size() == 0;
        /*
        if (ctx.variable() != null)
            ultimoValor = visitVariable(ctx.variable()).getUltimoValorSecuencia();
        else
            ultimoValor = visitOperando_secuencia(ctx.operando_secuencia()).getUltimoValorSecuencia();

        System.out.println("visitUltima_posicion es " + ctx.getText() + " = " + ultimoValor);
         */
        return new Valor(vaciaNum && vaciaBool);
    }



/*
    public Valor visitUltima_posicion_variable (Anasint.Ultima_posicion_variableContext ctx) {

    }

    public Valor visitUltima_posicion_variable (Anasint.Ultima_posicion_variableContext ctx) {

    }
*/
    public Valor visitOperando_secuencia (Anasint.Operando_secuenciaContext ctx) {
        return (Valor) visit(ctx);
    }

    public Valor visitOperando_secuencia_numerica (Anasint.Operando_secuencia_numericaContext ctx) {
        Valor res;
        List<Integer> secuencia = new ArrayList<>();
        for (TerminalNode numero: ctx.NUMERO())
            secuencia.add(Integer.valueOf(numero.getText()));
        return new Valor(secuencia, 0);
    }

    public Valor visitOperando_secuencia_logica (Anasint.Operando_secuencia_logicaContext ctx) {
        List<Boolean> secuencia = new ArrayList<>();
        for (Anasint.Valor_booleanoContext booleano: ctx.valor_booleano())
            secuencia.add(booleano.getText().equals("T"));
        return new Valor(secuencia, true);
    }

    /*
        public Valor visitOp_logica_simple(Anasint.Op_logica_simpleContext ctx) {
            return resuelveOperadorLogico(
                    visitOperando(ctx.operando(0)),
                    visitOperando(ctx.operando(1)),
                    ctx.operador_logico_2_ario());
        }

        public Valor visitOp_logica_compuesta(Anasint.Op_logica_compuestaContext ctx) {
            return resuelveOperadorLogico(
                    visitOperando(ctx.operando()),
                    visitOperacion(ctx.operacion()),
                    ctx.operador_logico_2_ario());
        }

        public Valor visitOp_logica_compuesta_doble(Anasint.Op_logica_compuesta_dobleContext ctx) {
            return resuelveOperadorLogico(
                    visitOperacion(ctx.operacion(0)),
                    visitOperacion(ctx.operacion(1)),
                    ctx.operador_logico_2_ario());
        }
    */


    public Valor visitOp_aritmetica_envuelta (Anasint.Op_aritmetica_envueltaContext ctx) {
        return visitOperacion(ctx.operacion());
    }

    public Valor visitOp_aritmetica_mult (Anasint.Op_aritmetica_multContext ctx) {
        return resuelveOperadorAritmetico(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                "*");    }

    public Valor visitOp_aritmetica_sr (Anasint.Op_aritmetica_srContext ctx) {
        String operador;
        if (ctx.MAS() != null)
            operador = "+";
        else
            operador = "-";
        return resuelveOperadorAritmetico(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                operador);
    }
                /*
    public Valor visitOp_aritmetica_doble(Anasint.Op_aritmetica_dobleContext ctx) {
        return resuelveOperadorAritmetico(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                ctx.operador_aritmetico_2_ario());
    }
*/

}

