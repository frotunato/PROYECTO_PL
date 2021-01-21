import Valor.Valor;
import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VisitorInterprete extends AnasintBaseVisitor<Object> {
    Scope scopeGlobal = new Scope("global");

    ParseTreeProperty<Map<String, Valor>> memoria = new ParseTreeProperty<>();
    ParseTreeProperty<Valor> retornosFuncion = new ParseTreeProperty<>();

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
        memoria.put(ctx.bloque_instrucciones(), new OrderedHashMap<>());
        scopeGlobal.declaraSubprogramaNativo("vacia", "Funcion");
        scopeGlobal.declaraSubprogramaNativo("ultima_posicion", "Funcion");
        scopeGlobal.declaraSubprogramaNativo("mostrar", "Procedimieneto");

        return super.visitBloque_programa(ctx);
    }
    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        //si es el bloque instrucciones de programa, inicializamos
        //en la memoria las variables; en el resto de caso, es decir,
        //en una invocacion de subprograma, se hace antes de invocar
        //no aqui
        if (ctx.getParent().getClass().equals(Anasint.Bloque_programaContext.class))
            for (Variable variable: scopeGlobal.getVariables())
                memoria.get(ctx).put(variable.getNombre(), new Valor());
        for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
            //System.out.println("Ruptura? " + !instruccion.getTokens(Anasint.RUPTURA).isEmpty());
            if (!instruccion.getTokens(Anasint.RUPTURA).isEmpty())
            //if (instruccion.instruccion_ruptura() != null)
                break;
            visit(instruccion);
        }
        //return super.visitBloque_instrucciones(ctx);
        return 1;
    }
    public Object visitBloque_procedimiento (Anasint.Bloque_procedimientoContext ctx) {
        String nombreProcedimiento = ctx.IDENT().getText();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> declaradas = visitBloque_variables(ctx.bloque_variables());
        if (ctx.lista_variables_tipadas() != null)
            entrada.addAll(visitLista_variables_tipadas(ctx.lista_variables_tipadas()));
        Subprograma procedimiento = new Subprograma(nombreProcedimiento, "Procedimiento", entrada, new ArrayList<>(), declaradas);
        procedimiento.setPuntero(ctx.bloque_instrucciones());
        if (!scopeGlobal.existeSubprograma(nombreProcedimiento)) {
            scopeGlobal.declaraSubprograma(procedimiento);
        }

        //Map<String, Valor> valoresIniciales = new OrderedHashMap<>();

        //memoria.get(ctx.bloque_instrucciones()).putAll(valoresIniciales);
        //memoria.put(ctx, valoresIniciales);
        return 1;
    }
    public Object visitBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        String nombreFuncion = ctx.IDENT().getText();

        List<Anasint.Lista_variables_tipadasContext> variablesEntradaSalida =  ctx.lista_variables_tipadas();
        List<Variable> entrada = new ArrayList<>();
        List<Variable> salida = new ArrayList<>();
        List<Variable> declaradas = visitBloque_variables(ctx.bloque_variables());


        if (variablesEntradaSalida.size() == 2) {
            entrada.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(1)));
        } else
            salida.addAll(visitLista_variables_tipadas(variablesEntradaSalida.get(0)));

        Subprograma funcion = new Subprograma(nombreFuncion, "Funcion", entrada, salida, declaradas);
        funcion.setPuntero(ctx.bloque_instrucciones());
        scopeGlobal.declaraSubprograma(funcion);
        scopeGlobal.getSubprograma(nombreFuncion).setPuntero(ctx.bloque_instrucciones());

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

        /*
        for (Variable variable: variables)
            memoria.get(ctx.getParent()).put(variable.getNombre(), new Valor());
        */

        if (ctx.getParent().getClass().equals(Anasint.Bloque_programaContext.class)) {
            scopeGlobal.declaraVariables(variables);
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
        int i = 0;
        String nombre = "";
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
        return super.visit(ctx);
    }
    public Object visitInstruccion_llamada_subprograma (Anasint.Instruccion_llamada_subprogramaContext ctx) {
        visit(ctx.subprograma());
        return 0;
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
            for (Anasint.InstruccionContext instruccion: ctx.instruccion()) {
                System.out.println("[INTERPRETE] visitInstruccion_bucle: " + instruccion.getText());
                if (!instruccion.getTokens(Anasint.RETORNO).isEmpty())
                    //if (instruccion.instruccion_retorno() != null)
                    return visitInstruccion(instruccion);
                else if (!instruccion.getTokens(Anasint.RUPTURA).isEmpty()) {
                    //else if (instruccion.instruccion_ruptura() != null) {
                    ruptura = true;
                    break;
                } else
                    visitInstruccion(instruccion);
            }
        }
        return 1;
    }
    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        List<Anasint.InstruccionContext> instruccionesSi = new ArrayList<>();
        List<Anasint.InstruccionContext> instruccionesSino = new ArrayList<>();
        boolean encontradoSino = false;
        Map<String, Valor> mm = memoria.get(closestInstruccionBlock(ctx));
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
        boolean valorPredicado = visitPredicado(ctx.predicado()).getValorBooleano();
        System.out.println("[INTERPRETE] visitInstruccion: valor predicado " + ctx.predicado().getText() + " = " + valorPredicado);
        if (valorPredicado)
            //se visitan instrucciones si
            for (Anasint.InstruccionContext instruccion: instruccionesSi) {
                System.out.println("[INTERPRETE] visitInstruccion: instr si entonces ");
                if (!instruccion.getTokens(Anasint.RUPTURA).isEmpty()) {
                //if (instruccion.instruccion_ruptura() != null) {
                    System.out.println("[INTERPRETE] visitInstruccion_control NO visitamos la instruccion porque tiene ruptura");
                    break;
                }
                visitInstruccion(instruccion);
                System.out.println("[INTERPRETE] visitInstruccion_control instr si " + instruccion.getText());
            }
            //se visitan las instrucciones sino
        else
            for (Anasint.InstruccionContext instruccion: instruccionesSino) {
               if (!instruccion.getTokens(Anasint.RUPTURA).isEmpty()) {
                // if (instruccion.instruccion_ruptura() != null) {
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

        //lo enchufamos al bloque padre (instruccion)
        int i = 0;

        for (Anasint.Evaluacion_variableContext evalr: ctx.evaluaciones_variables().evaluacion_variable()) {
            if (evalr.subprograma() != null && evalr.subprograma().getClass().equals(Anasint.Subprograma_declaradoContext.class))
                valores.addAll(visitEvaluacion_variable(evalr).getValores());
            else
                valores.add(visitEvaluacion_variable(evalr));
        }

        //acceder caso especial funcion ??
        for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
            String nombreVariable = variable.getText();
            memoria.get(closestInstruccionBlock(variable)).put(nombreVariable, valores.get(i));
            i++;
        }

        System.out.println("[INTERPRETE] Datos de la memoria " + ctx.getParent().getClass() + " , " + memoria.get(closestInstruccionBlock(ctx)));
        return 1;
    }

    public Valor visitPredicado (Anasint.PredicadoContext ctx) {
        return (Valor) super.visit(ctx);
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
        Valor predicadoA = visitPredicado(ctx.predicado(0));
        Valor predicadoB = visitPredicado(ctx.predicado(1));
        System.out.println("predicado rec me llega " + predicadoA  + " " + predicadoB + " " + ctx.getText());
        return resuelveOperadorCondicion(predicadoA,
                predicadoB, ctx.operador_condicion_2_ario());
    }

    public Valor visitCondicion (Anasint.CondicionContext ctx) {
        return (Valor) super.visit(ctx);
    }
    public Valor visitCondicion_base (Anasint.Condicion_baseContext ctx) {
        return (Valor) visit(ctx.getChild(0));
    }
    public Valor visitCondicion_envuelta (Anasint.Condicion_envueltaContext ctx) {
        return (Valor) visit(ctx.condicion());
    }
    public Valor visitCondicion_rec (Anasint.Condicion_recContext ctx) {
        Valor condicionA = (Valor) visit(ctx.condicion(0));
        Valor condicionB = (Valor) visit(ctx.condicion(1));
        return resuelveOperadorLogico(condicionA, condicionB, ctx.operador_logico_2_ario());
    }
    public Valor visitCondicion_cierto (Anasint.Condicion_ciertoContext ctx) {
        return new Valor(true);
    }
    public Valor visitCondicion_falso (Anasint.Condicion_falsoContext ctx) {
        return new Valor(false);
    }

    public Valor visitValor_booleano_true (Anasint.Valor_booleano_trueContext ctx) {
        return new Valor(true);
    }
    public Valor visitValor_booleano_false (Anasint.Valor_booleano_falseContext ctx) {
        return new Valor(false);
    }
    public Valor visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return new Valor(Integer.valueOf(ctx.NUMERO().getText()));
    }
    public Valor visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        Valor variable = (Valor) visit(ctx.variable());
        Valor indice = (Valor) visit(ctx.operacion());
        return variable.getValorSecuencia(indice.getValorNumerico());
        //return new Valor(memoria.get(ctx.variable().getText()))
    }

    public Valor visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        return (Valor) super.visitEvaluacion_variable(ctx);
    }
    public Valor visitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String nombre = ctx.IDENT().getText();
        //String nombre = ctx.getChild(0).getText();
        Subprograma subprograma = scopeGlobal.getSubprograma(nombre);
        System.out.println("Visitando programa " + ctx.getText() + " tiene memoria? " + memoria.get(subprograma.getPuntero()));

        // Cada vez que entramos en la funcion, limpiamos los datos del nodo
        retornosFuncion.removeFrom(subprograma.getPuntero());
        memoria.removeFrom(subprograma.getPuntero());
        Map<String, Valor> memoriaGlobal = memoria.get(closestInstruccionBlock(ctx));
        List<Variable> argumentosDeclarados = subprograma.getEntrada();
        Map<String, Valor> memoriaSubprograma = new OrderedHashMap<>();
        List<Valor> valoresInvocacionSubprograma = new ArrayList<>();

        //estos son los valores con los que se ha invocado al subprograma
        for (Anasint.Evaluacion_variableContext valorArgumento :ctx.evaluacion_variable())
            valoresInvocacionSubprograma.add(visitEvaluacion_variable(valorArgumento));

        int i = 0;

        //estas son las variables definidas del programa en scope
        for (Variable variableDeclarada: scopeGlobal.getSubprograma(nombre).getDeclaradas())
            memoriaSubprograma.put(variableDeclarada.getNombre(), new Valor());

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
        visit(subprograma.getPuntero());

        //actualizamos las variables globales, prevalecen privadas
        //si tienen mismo nombre que globales
        for (String nombreVariable: memoriaSubprograma.keySet())
            //la variable privada prevalece a la global
            if (!scopeGlobal.getSubprograma(nombre).existeVariable(nombreVariable) &&
                scopeGlobal.existeVariable(nombreVariable))
                memoriaGlobal.put(nombreVariable, memoriaSubprograma.get(nombreVariable));
        return retornosFuncion.get(subprograma.getPuntero());
    }
    public Valor visitVariable (Anasint.VariableContext ctx) {
        return memoria.get(closestInstruccionBlock(ctx)).get(ctx.getText());
    }



    public Valor visitSubprograma_ultima_posicion (Anasint.Subprograma_ultima_posicionContext ctx) {
        Valor ultimoValor = (Valor) visit(ctx.evaluacion_variable());
        /*if (ctx.variable() != null)
            ultimoValor = visitVariable(ctx.variable()).getUltimoValorSecuencia();
        else
            ultimoValor = visitOperando_secuencia(ctx.operando_secuencia()).getUltimoValorSecuencia();
        */
        System.out.println("visitUltima_posicion es " + ctx.getText() + " = " + ultimoValor);
        return ultimoValor.getUltimoValorSecuencia();
    }
    public Valor visitSubprograma_vacia (Anasint.Subprograma_vaciaContext ctx) {
        Valor res = visitEvaluacion_variable(ctx.evaluacion_variable());
        boolean vaciaNum = res.getSecuenciaBooleana() == null || res.getSecuenciaBooleana().isEmpty();
        boolean vaciaBool = res.getSecuenciaNumerica() == null || res.getSecuenciaNumerica().isEmpty();
        return new Valor(vaciaNum && vaciaBool);
    }

    public Valor visitSubprograma_mostrar (Anasint.Subprograma_mostrarContext ctx) {
        Valor secuencia = (Valor) visit(ctx.evaluacion_variable());
        System.out.println("[INTERPRETE] visitSubprograma_mostrar: " + secuencia);
        return null;
    }

    public Valor visitOperando_subprograma (Anasint.Operando_subprogramaContext ctx) {
        return (Valor) visit(ctx.subprograma());
    }
    public Valor visitOperando_secuencia_numerica (Anasint.Operando_secuencia_numericaContext ctx) {
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
    public Valor visitOperando_secuencia_vacia (Anasint.Operando_secuencia_vaciaContext ctx) {
        return new Valor(new ArrayList<>());
    }

    public Valor visitOperacion (Anasint.OperacionContext ctx) {
        return (Valor) super.visit(ctx);
    }
    public Valor visitOp_aritmetica_envuelta (Anasint.Op_aritmetica_envueltaContext ctx) {
        return visitOperacion(ctx.operacion());
    }
    public Valor visitOp_aritmetica_negacion (Anasint.Op_aritmetica_negacionContext ctx) {
        return new Valor(visitOperacion(ctx.operacion()).getValorNumerico()*-1);
    }
    public Valor visitOp_aritmetica_mult (Anasint.Op_aritmetica_multContext ctx) {
        return resuelveOperadorAritmetico(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                "*");
    }
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

}