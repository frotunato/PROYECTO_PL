import Valor.Booleano;
import Valor.Numero;
import Valor.Valor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitorInterprete extends AnasintBaseVisitor<Object> {
    ParseTreeProperty<Scope> scopeTree = new ParseTreeProperty<>();
    ParseTreeProperty<List<Valor>> valorOperaciones = new ParseTreeProperty<>();
    ParseTreeProperty<Map<String, Valor>> memoria = new ParseTreeProperty<>();
    /*public Valor visitEvaluacion_variable(Anasint.Evaluacion_variableContext ctx) {

    }*/

    public Object visitBloque_instrucciones (Anasint.Bloque_instruccionesContext ctx) {
        memoria.put(ctx, new HashMap<String, Valor>());
        //scopeTree.put(new Scope())
        return super.visitBloque_instrucciones(ctx);
    }

    /*
    public Valor visitPredicado (Anasint.PredicadoContext ctx) {
        return (Valor) super.visitPredicado(ctx);
    }
*/
    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        System.out.println("[INTERPRETE] visitInstruccion_bucle " + ctx.predicado().getText());
        while (visitPredicado(ctx.predicado()).getValorBooleano()) {
            System.out.println("[INTERPRETE] visitInstruccion_bucle predicado " + visitPredicado(ctx.predicado()).getValorBooleano());
            for (Anasint.InstruccionContext instruccion: ctx.instruccion())
                visitInstruccion(instruccion);
        }
        return 1;
    }

    public Object visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        List<Anasint.InstruccionContext> instruccionesSi = new ArrayList<>();
        List<Anasint.InstruccionContext> instruccionesSino = new ArrayList<>();
        boolean encontradoSino = false;

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

        if (visitPredicado(ctx.predicado()).getValorBooleano())
            for (Anasint.InstruccionContext instruccion: instruccionesSi) {
                visitInstruccion(instruccion);
                System.out.println("[INTERPRETE] visitInstruccion_control instr si " + instruccion.getText());
            }
        else
            for (Anasint.InstruccionContext instruccion: instruccionesSino) {
                visitInstruccion(instruccion);
                System.out.println("[INTERPRETE] visitInstruccion_control instr sino " + instruccion.getText());
            }
        return 1;
    }

    private ParserRuleContext closestInstruccionBlock (ParserRuleContext ctx) {
        if (ctx.getParent().getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx.getParent();
        else
            return closestInstruccionBlock(ctx.getParent());
    }

    public Object visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        List<Valor> valores = new ArrayList<Valor>();
        List<Variable> variables = new ArrayList<>();

        //lo enchufamos al bloque padre (instruccion)
        int i = 0;

        for (Anasint.Evaluacion_variableContext evalr: ctx.evaluaciones_variables().evaluacion_variable()) {
            valores.add(visitEvaluacion_variable(evalr));
            //valores.addAll(valorOperaciones.get(evalr));
        }

        //acceder caso especial funcion
        for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
            String nombreVariable = variable.getText();
            memoria.get(closestInstruccionBlock(variable)).put(nombreVariable, valores.get(i));
            i++;
        }

        System.out.println("Datos del nodo " + memoria.get(closestInstruccionBlock(ctx)));
        return 1;
    }

    public Valor visitVariable (Anasint.VariableContext ctx) {
        return memoria.get(closestInstruccionBlock(ctx)).get(ctx.getText());
    }

    public Valor visitEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        //Valor valor = visit(ctx);
        //valorOperaciones.put(ctx, Arrays.asList(valor));
        return (Valor) super.visitEvaluacion_variable(ctx);
    }

    private Valor resuelveOperadorLogico (Valor a, Valor b, Anasint.Operador_logico_2_arioContext ctx) {
        String operador = ctx.getText();
        boolean res = false;
        //if (a == null || b == null)
        //    return new Valor(new Booleano(true));
        switch (operador) {
            case "==" -> res = a.getValorBooleano().equals(b.getValorBooleano());
            case "!=" -> res = !a.getValorBooleano().equals(b.getValorBooleano());
            case ">=" -> res = a.getValorNumerico() >= b.getValorNumerico();
            case ">" -> res = a.getValorNumerico() > b.getValorNumerico();
            case "<=" -> res = a.getValorNumerico() <= b.getValorNumerico();
            case "<" -> res = a.getValorNumerico() < b.getValorNumerico();
        }
        return new Valor(new Booleano(res));
    }

    private Valor resuelveOperadorAritmetico (Valor a, Valor b, Anasint.Operador_aritmetico_2_arioContext ctx) {
        String operador = ctx.getText();
        Integer res = 0;
        //if (a == null || b == null)
        //    return new Valor(new Numero(0));
        switch (operador) {
            case "+" -> res = a.getValorNumerico() + b.getValorNumerico();
            case "-" -> res = a.getValorNumerico() - b.getValorNumerico();
            case "*" -> res = a.getValorNumerico() * b.getValorNumerico();
        }
        return new Valor(new Numero(res));
    }

    private Valor resuelveOperadorCondicion (Valor a, Valor b, Anasint.Operador_condicion_2_arioContext ctx) {
        String operador = ctx.getText();
        boolean res = false;
        switch (operador) {
            case "&&" -> res = a.getValorBooleano() && b.getValorBooleano();
            case "||" -> res = a.getValorBooleano() || b.getValorBooleano();
        }
        return new Valor(new Booleano(res));
    }


    public Valor visitOperacion (Anasint.OperacionContext ctx) {
        return (Valor) super.visit(ctx);
    }

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

    public Valor visitOp_aritmetica_simple(Anasint.Op_aritmetica_simpleContext ctx) {
        return resuelveOperadorAritmetico(
                visitOperando(ctx.operando(0)),
                visitOperando(ctx.operando(1)),
                ctx.operador_aritmetico_2_ario());
    }

    public Valor visitOp_aritmetica_compuesta(Anasint.Op_aritmetica_compuestaContext ctx) {
        return resuelveOperadorAritmetico(
               visitOperando(ctx.operando()),
               visitOperacion(ctx.operacion()),
               ctx.operador_aritmetico_2_ario());
    }

    public Valor visitOp_aritmetica_compuesta_doble(Anasint.Op_aritmetica_compuesta_dobleContext ctx) {
        return resuelveOperadorAritmetico(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                ctx.operador_aritmetico_2_ario());
    }

    public Valor visitOperando (Anasint.OperandoContext ctx) {
        return (Valor) super.visit(ctx);
    }

    public Valor visitOperando_booleano (Anasint.Operando_booleanoContext ctx) {
        if (ctx.TRUE() != null)
            return new Valor(new Booleano(true));
        else
            return new Valor(new Booleano(false));
    }

    public Valor visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return new Valor(new Numero(Integer.valueOf(ctx.NUMERO().getText())));
    }

    public Valor visitPredicado (Anasint.PredicadoContext ctx) {
        return (Valor) super.visit(ctx);
    }

    public Valor visitPredicado_simple(Anasint.Predicado_simpleContext ctx) {
        return resuelveOperadorCondicion(
                visitOperacion(ctx.operacion(0)),
                visitOperacion(ctx.operacion(1)),
                ctx.operador_condicion_2_ario());
    }

    public Valor visitPredicado_compuesto(Anasint.Predicado_compuestoContext ctx) {
        return resuelveOperadorCondicion(
                visitOperacion(ctx.operacion()),
                visitPredicado(ctx.predicado()),
                ctx.operador_condicion_2_ario());
    }

    public Valor visitPredicado_compuesto_doble(Anasint.Predicado_compuesto_dobleContext ctx) {
        return resuelveOperadorCondicion(
                visitPredicado(ctx.predicado(0)),
                visitPredicado(ctx.predicado(1)),
                ctx.operador_condicion_2_ario());
    }





}

