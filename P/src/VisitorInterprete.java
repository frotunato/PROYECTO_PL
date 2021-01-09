import Valor.Booleano;
import Valor.Numero;
import Valor.Valor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.*;

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

    public Valor visitPredicado (Anasint.PredicadoContext ctx) {
        return (Valor) super.visitPredicado(ctx);
    }

    public Object visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        System.out.println("[INTERPRETE] visitInstruccion_bucle " + ctx.predicado().getText());
        while (visitPredicado(ctx.predicado()).getValorBooleano()) {
            System.out.println("[INTERPRETE] visitInstruccion_bucle predicado " + visitPredicado(ctx.predicado()).getValorBooleano());
            for (Anasint.InstruccionContext instruccion: ctx.instruccion())
                visitInstruccion(instruccion);
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
/*
    @Override
    public Object visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
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
 */

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

    public Valor visitOperacion_logica (Anasint.Operacion_logicaContext ctx) {
        System.out.println("[INTERPRETE] visitOperacion_logica " + ctx.getText());
        Valor ladoIzquierdo;
        Valor ladoDerecho;
        Valor res = null;

        if (ctx.operador_logico_2_ario() != null && ctx.operando_logico() == null && ctx.operacion_logica().size() == 1) {
            //System.out.println("CASO NESTED " + ctx.getText());
            return visitOperacion_logica(ctx.operacion_logica().get(0));
        } else if (ctx.operador_logico_2_ario() == null && ctx.operando_logico() != null) {
            return visitOperando_logico(ctx.operando_logico());
        } else if (ctx.operador_logico_2_ario() != null && ctx.operacion_logica().size() == 2) {
            //System.out.println("DOSSS " + ctx.getText());
            ladoIzquierdo = visitOperacion_logica(ctx.operacion_logica().get(0));
            ladoDerecho = visitOperacion_logica(ctx.operacion_logica().get(1));
            //System.out.println("LOSLADOS SON " + ladoIzquierdo + " y" + ladoDerecho);
            res = resuelveOperadorLogico(ladoIzquierdo, ladoDerecho, ctx.operador_logico_2_ario());
        } else if (ctx.operando_logico() != null && ctx.operacion_logica() != null) {
            //System.out.println("ENTRAMOS EN DECIDIDOR, SOLO UN OPERACION " + ctx.getText());
            ladoIzquierdo = visitOperando_logico(ctx.operando_logico());
            ladoDerecho = visitOperacion_logica(ctx.operacion_logica().get(0));
            res = resuelveOperadorLogico(ladoIzquierdo, ladoDerecho, ctx.operador_logico_2_ario());
        }
        System.out.println("[INTERPRETE] visitOperacion_logica resultado " + ctx.getText() + " as " + res.getValorBooleano());
        //if (tipoLadoDerecho.equals(tipoLadoIzquierdo))
        //    tipoLadoIzquierdo = "Boolean";
        if (ctx.getParent().getClass().equals(Anasint.Evaluacion_variableContext.class))
            valorOperaciones.put(ctx.getParent(), Arrays.asList(res));
        return res;
    }

    public Valor visitOperacion_aritmetica (Anasint.Operacion_aritmeticaContext ctx) {
        //System.out.println("[INTERPRETE] visitOperacion_aritmetica " + ctx.getText());
        Valor ladoIzquierdo;
        Valor ladoDerecho;
        Valor res = null;
        //caso base
        if (ctx.operador_aritmetico_2_ario() != null && ctx.operando_aritmetico() == null) {
            return visitOperacion_aritmetica(ctx.operacion_aritmetica().get(0));
        } else if (ctx.operador_aritmetico_2_ario() == null && ctx.operando_aritmetico() != null)
            return visitOperando_aritmetico(ctx.operando_aritmetico());
        else if (ctx.operador_aritmetico_2_ario() != null && ctx.operacion_aritmetica().size() == 2) {
            //System.out.println("DOSSS " + ctx.getText());
            ladoIzquierdo = visitOperacion_aritmetica(ctx.operacion_aritmetica().get(0));
            ladoDerecho = visitOperacion_aritmetica(ctx.operacion_aritmetica().get(1));
            //System.out.println("LOSLADOS SON " + ladoIzquierdo + " y" + ladoDerecho);
            res = resuelveOperadorAritmetico(ladoIzquierdo, ladoDerecho, ctx.operador_aritmetico_2_ario());
        } else if (ctx.operando_aritmetico() != null && ctx.operacion_aritmetica() != null) {
            //System.out.println("ENTRAMOS EN DECIDIDOR, SOLO UN OPERACION " + ctx.getText());
            ladoIzquierdo = visitOperando_aritmetico(ctx.operando_aritmetico());
            ladoDerecho = visitOperacion_aritmetica(ctx.operacion_aritmetica().get(0));
            res = resuelveOperadorAritmetico(ladoIzquierdo, ladoDerecho, ctx.operador_aritmetico_2_ario());
        }

        System.out.println("[INTERPRETE] visitOperacion_aritmetica RESULTADO ARITMETICO DE " + ctx.getText() + " = " + res.getValorNumerico());

        if (ctx.getParent().getClass().equals(Anasint.Evaluacion_variableContext.class))
            valorOperaciones.put(ctx.getParent(), Arrays.asList(res));

        return res;
    }

    public Valor visitOperando_aritmetico(Anasint.Operando_aritmeticoContext ctx) {
        //System.out.println(ctx.getText());
        if (ctx.NUMERO() != null)
            return new Valor(new Numero(Integer.valueOf(ctx.NUMERO().getText())));
        else
            return (Valor) super.visitOperando_aritmetico(ctx);
    }

    public Valor visitOperando_logico(Anasint.Operando_logicoContext ctx) {
        //System.out.println(ctx.getText());
        Valor res;
        if (ctx.TRUE() != null)
            return new Valor(new Booleano(true));
        else if (ctx.FALSE() != null)
            return new Valor(new Booleano(false));
        else
            return (Valor) super.visitOperando_logico(ctx);
    }

}
