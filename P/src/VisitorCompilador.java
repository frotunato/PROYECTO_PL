import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.*;

public class VisitorCompilador extends AnasintBaseVisitor<Object>{
    String programa = "";
    boolean variablesPrivadas = false;
    boolean variablesBajoCondicion = false;
    String variablesCondicion = "";
    ParseTreeProperty<Map<String, String>> scope = new ParseTreeProperty<>();
    Map<String, List<String>> funciones = new OrderedHashMap<>();

    private ParserRuleContext closestBreakBlock (ParserRuleContext ctx) {
        if (//ctx.getParent().getClass().equals(Anasint.Bloque_funcionContext.class) ||
                ctx.getParent().getClass().equals(Anasint.Instruccion_controlContext.class) ||
                        ctx.getParent().getClass().equals(Anasint.Instruccion_bucleContext.class) ||
                        ctx.getParent().getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return ctx.getParent();
        else
            return closestBreakBlock(ctx.getParent());
    }
    private String convierteTipo (String tipoP) {
        String res = "";
        switch (tipoP) {
            case "NUM" -> res = "Integer";
            case "LOG" -> res = "Boolean";
            case "SEQ(NUM)" -> res = "Integer[]";
            case "SEQ(LOG)" -> res = "Boolean[]";
        }
        return res;
    }
    private String quitaUltimaComa (String str) {
        int lastIndex = str.lastIndexOf(",");
        if (lastIndex >= 0)
            return str.substring(0 ,str.lastIndexOf(","));
        else return str;
    }
    private Map<String, String> getUpperScope (ParserRuleContext ctx) {
        if (scope.get(ctx) != null)
            return scope.get(ctx);
        else
        if (ctx.getParent() == null)
            return null;
        return getUpperScope(ctx.getParent());
    }
    private String ultimaPosicion () {
        return
                "private static Integer ultima_posicion (Integer[] entrada) {\n" +
                "   return entrada[entrada.length-1];\n" +
                "}\n" +
                "private static Boolean ultima_posicion (Boolean[] entrada) {\n" +
                "   return entrada[entrada.length-1];\n" +
                "}\n"
                ;
    }
    private String vacia () {
        return
                "private static Boolean vacia (Boolean[] entrada) {\n" +
                "   return entrada.length == 0;\n" +
                "}\n" +
                "private static Boolean vacia (Integer[] entrada) {\n" +
                "   return entrada.length == 0;\n" +
                "}\n";
    }
    private String mostrar () {
        return
                "private static void mostrar (Boolean[] entrada) {\n" +
                "   System.out.println(entrada);\n" +
                "}\n" +
                "private static void mostrar (Integer[] entrada) {\n" +
                "   System.out.println(entrada);\n" +
                "}\n";
    }

    public String visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        scope.put(ctx, new OrderedHashMap<>());
        variablesPrivadas = false;
        programa += "import java.util.Arrays;\n public class Test {\n";
        programa += ultimaPosicion();
        programa += vacia();
        programa += mostrar();
        programa += visitBloque_variables(ctx.bloque_variables()) + "\n";
        programa += visitBloque_subprogramas(ctx.bloque_subprogramas()) + "\n";
        programa += "public static void main(String[] args) throws Exception {\n" + visitBloque_instrucciones(ctx.bloque_instrucciones());
        programa += "}\n}";
        return "";
    }

    public String visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        String res = "";
        Map<String, String> scopePadre = getUpperScope(ctx);
        if (!ctx.getParent().getClass().equals(Anasint.Bloque_programaContext.class)) {
            Map<String, String> scopeHijo = new OrderedHashMap<>();
            scopeHijo.putAll(scopePadre);
            scope.put(ctx, scopeHijo);
        }
        for (Anasint.Declaracion_variableContext variable : ctx.declaracion_variable())
            res += (visitDeclaracion_variable(variable));
        return res;
    }

    public String visitBloque_subprogramas (Anasint.Bloque_subprogramasContext ctx) {
        //String res = "public static void main(String[] args) throws Exception {\n";
        String res = "";
        variablesPrivadas = true;

        for (Anasint.Bloque_procedimientoContext procedimiento: ctx.bloque_procedimiento())
            res += visit(procedimiento);
        for (Anasint.Bloque_funcionContext funcion: ctx.bloque_funcion())
            res += visit(funcion);

        //for (ParseTree subprograma: ctx.children)
        //    res+= (String) visit(subprograma);
            //return (String) super.visitBloque_subprogramas(ctx);
        //for (ctx.)
        return res;
    }

    public String visitBloque_instrucciones(Anasint.Bloque_instruccionesContext ctx) {
        String res = "";
        for (Anasint.InstruccionContext instruccion: ctx.instruccion())
            res += visit(instruccion);
        return res;
    }

    public String visitBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        Map<String, String> scopePadre = getUpperScope(ctx);
        String declaracionVariablesSalida = "";
        String nombrePrograma = ctx.IDENT().getText();
        String tipoSalida, nombre;
        String res = "";
        res += "private static ";
        funciones.put(nombrePrograma, new ArrayList<>());

        int i = 0;
        int j;
        if (ctx.lista_variables_tipadas().size() > 1)
            i = 1;

        //la funcion tiene varios args de salida
        if (ctx.lista_variables_tipadas(i).IDENT().size() > 1) {
            res += "Object[] ";
            tipoSalida = "Object[]";
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(1).tipo()) {
                j = tipo.getParent().children.indexOf(tipo);
                nombre = tipo.parent.getChild(j + 1).getText();
                declaracionVariablesSalida += (convierteTipo(tipo.getText()) + " " + nombre +";\n");
                scopePadre.put(nombre, convierteTipo(tipo.getText()));
            }
        }
        //funcion solo tiene un arg de salida
        else {
            Anasint.TipoContext tipo = ctx.lista_variables_tipadas(i).tipo(0);
            j = tipo.getParent().children.indexOf(tipo);
            nombre = tipo.parent.getChild(j + 1).getText();
            tipo.parent.getChild(j + 1).getText();
            tipoSalida = convierteTipo(tipo.getText());
            res += convierteTipo(tipo.getText()) + " ";
            declaracionVariablesSalida += convierteTipo(tipo.getText()) + " " + nombre +";\n";
            scopePadre.put(nombre, convierteTipo(tipo.getText()));
        }
        //entrada
        res += ctx.IDENT().getText() + " (";
        if (i > 0) {
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(0).tipo()) {
                j = tipo.getParent().children.indexOf(tipo);
                nombre = tipo.parent.getChild(j + 1).getText();
                res += (convierteTipo(tipo.getText()) + " " + nombre);
                scopePadre.put(nombre, convierteTipo(tipo.getText()));
                if (tipo.parent.getChild(j+2) != null)
                    res += tipo.parent.getChild(j+2);
            }
        }
        res += ") {\n" + declaracionVariablesSalida;

        for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(1).tipo()) {
            funciones.get(nombrePrograma).add(convierteTipo(tipo.getText()));
        }
        res += visitBloque_variables(ctx.bloque_variables());
        res += visitBloque_instrucciones(ctx.bloque_instrucciones());
        res += " }\n";
        if (funciones.get(nombrePrograma).size() > 1)
            res += "private static " + tipoSalida + " salida_" + nombrePrograma + ";\n";
        return res;
    }

    public String visitBloque_procedimiento (Anasint.Bloque_procedimientoContext ctx) {
        Map<String, String> scopePadre = getUpperScope(ctx);
        String nombrePrograma = ctx.IDENT().getText();
        String res, nombre;
        funciones.put(nombrePrograma, new ArrayList<>());
        int i = 0;
        int j;
        res = "private static void " + nombrePrograma + " (";
        //la funcion tiene varios args de salida
        /*
        if (ctx.lista_variables_tipadas(i).IDENT().size() > 1) {
            res += "Object[] ";
            tipoSalida = "Object[]";
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(1).tipo()) {
                j = tipo.getParent().children.indexOf(tipo);
                nombre = tipo.parent.getChild(j + 1).getText();
                declaracionVariablesSalida += (convierteTipo(tipo.getText()) + " " + nombre +";\n");
                scopePadre.put(nombre, convierteTipo(tipo.getText()));
            }
        }
        //funcion solo tiene un arg de salida
        else {
            Anasint.TipoContext tipo = ctx.lista_variables_tipadas(i).tipo(0);
            j = tipo.getParent().children.indexOf(tipo);
            nombre = tipo.parent.getChild(j + 1).getText();
            tipo.parent.getChild(j + 1).getText();
            tipoSalida = convierteTipo(tipo.getText());
            res += convierteTipo(tipo.getText()) + " ";
            declaracionVariablesSalida += convierteTipo(tipo.getText()) + " " + nombre +";\n";
            scopePadre.put(nombre, convierteTipo(tipo.getText()));
        }*/
        //entrada
        for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas().tipo()) {
            j = tipo.getParent().children.indexOf(tipo);
            nombre = tipo.parent.getChild(j + 1).getText();
            res += (convierteTipo(tipo.getText()) + " " + nombre);
            scopePadre.put(nombre, convierteTipo(tipo.getText()));
            if (tipo.parent.getChild(j+2) != null)
                res += tipo.parent.getChild(j+2);
        }
        res += ") {\n";

        for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas().tipo()) {
            funciones.get(nombrePrograma).add(convierteTipo(tipo.getText()));
        }
        res += visitBloque_variables(ctx.bloque_variables());
        res += visitBloque_instrucciones(ctx.bloque_instrucciones());
        res += " }\n";
        return res;
    }

    public String visitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        String res = "return ";
        if (ctx.evaluaciones_variables().evaluacion_variable().size() > 1) {
            res += "new Object[]{";
            for (Anasint.Evaluacion_variableContext variable: ctx.evaluaciones_variables().evaluacion_variable()) {
                res += (visitEvaluacion_variable(variable) + ",");// += visitEvaluacion_variable(variable) + ",";
            }
            res = quitaUltimaComa(res) + "}";
        } else
            res += ctx.evaluaciones_variables().evaluacion_variable(0).getText();
        return res + ";\n";
    }

    public String visitInstruccion_bucle (Anasint.Instruccion_bucleContext ctx) {
        variablesBajoCondicion = true;
        variablesCondicion = "!Arrays.asList(";
        String valorPredicado = (String) visit(ctx.predicado());
        variablesBajoCondicion = false;
        variablesCondicion = quitaUltimaComa(variablesCondicion) + ").contains(null) && (";

        String res = "while (" + variablesCondicion + valorPredicado + ")) {\n";
        //dividimos las instrucciones en dos partes, si/sino
       for (Anasint.InstruccionContext instruccion: ctx.instruccion())
           res += visit(instruccion);
        res += "}\n";
        //si el predicado es cierto (condicion), entramos a las instrucciones
        System.out.println("[COMPILADOR] visitInstruccion_bucle: valor predicado " + ctx.predicado().getText() + " = " + valorPredicado);
        return res;
    }

    public String visitInstruccion_control (Anasint.Instruccion_controlContext ctx) {
        variablesBajoCondicion = true;
        variablesCondicion = "!Arrays.asList(";
        String valorPredicado = (String) visit(ctx.predicado());
        variablesBajoCondicion = false;
        System.out.println("[COMPILADOR] visitInstruccion_control: " + variablesCondicion.toString());
        variablesCondicion = quitaUltimaComa(variablesCondicion) + ").contains(null) && (";
        //for (String variableCondicion: variablesCondicion)
            //precondicion += variablesCondicion.pop() + "!=null"
        String res = "if (" + variablesCondicion + valorPredicado + ")) {\n";
        //dividimos las instrucciones en dos partes, si/sino
        for (ParseTree hijo: ctx.children) {
            if (hijo.equals(ctx.SINO()))
                res += "} else {\n";
            else if (Anasint.InstruccionContext.class.isAssignableFrom(hijo.getClass()))
                res += visit(hijo);
        }
        res += "}\n";
        //si el predicado es cierto (condicion), entramos a las instrucciones
        System.out.println("[COMPILADOR] visitInstruccion: valor predicado " + ctx.predicado().getText() + " = " + valorPredicado);
        return res;
    }
    public String visitInstruccion_ruptura (Anasint.Instruccion_rupturaContext ctx) {
        if (closestBreakBlock(ctx).getClass().equals(Anasint.Bloque_instruccionesContext.class))
            return "return;\n";
        else
            return "break;\n";
    }
    public String visitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        String str;
        String res = "";
        Stack<String> pilaVariables = new Stack<>();
        for (Anasint.VariableContext variable: ctx.lista_variables().variable())
            pilaVariables.add(variable.getText());
        Collections.reverse(pilaVariables);
        for (Anasint.Evaluacion_variableContext valor: ctx.evaluaciones_variables().evaluacion_variable()) {
            if (valor.subprograma() != null) {
                str = valor.subprograma().getText();
                str = str.substring(0, str.indexOf("("));
                if (funciones.get(str).size() > 1) {
                    res += ("salida_" + str + "=" + visitEvaluacion_variable(valor) + ";\n");
                    for (int i = 0; i < funciones.get(str).size(); i++) {
                        res += (pilaVariables.pop() + "= (" + funciones.get(str).get(i) + ") salida_" + str + "[" + i + "];\n");
                    }
                }
                else
                    res += (pilaVariables.pop() + "=" + visitEvaluacion_variable(valor) + ";\n");
            } else
                res += (pilaVariables.pop() + "=" + visitEvaluacion_variable(valor) + ";\n");
        }
        return res;
    }
    public String visitInstruccion_llamada_subprograma (Anasint.Instruccion_llamada_subprogramaContext ctx) {
        return visit(ctx.subprograma()) + ";\n";
    }
    public String visitPredicado_negado (Anasint.Predicado_negadoContext ctx) {
        String valorPredicado = (String) visit(ctx.predicado());
        //valorPredicado.setValorBooleano(!valorPredicado.getValorBooleano());
        return "!" + valorPredicado;
    }
    public String visitPredicado_envuelto (Anasint.Predicado_envueltoContext ctx) {
        return (String) visit(ctx.predicado());
    }
    public String visitPredicado_base (Anasint.Predicado_baseContext ctx) {
        return (String) visit(ctx.condicion());
    }
    public String visitPredicado_rec(Anasint.Predicado_recContext ctx) {
        String predicadoA = (String) visit(ctx.predicado(0));
        String predicadoB = (String) visit (ctx.predicado(1));
        //System.out.println("predicado rec me llega " + predicadoA  + " " + predicadoB + " " + ctx.getText());
        return predicadoA + " " + ctx.operador_condicion_2_ario().getText() + " " + predicadoB;
        //return resuelveOperadorCondicion(predicadoA,
        //        predicadoB, ctx.operador_condicion_2_ario());
    }

    public String visitCondicion_base (Anasint.Condicion_baseContext ctx) {
        return (String) visit(ctx.getChild(0));
    }
    public String visitCondicion_envuelta (Anasint.Condicion_envueltaContext ctx) {
        return "(" + visit(ctx.condicion()) + ")";
    }
    public String visitCondicion_rec (Anasint.Condicion_recContext ctx) {
        String condicionA = (String) visit(ctx.condicion(0));
        String condicionB = (String) visit(ctx.condicion(1));
        return condicionA + " " + ctx.operador_logico_2_ario().getText() + " " + condicionB;
    }
    public String visitCondicion_cierto (Anasint.Condicion_ciertoContext ctx) {
        return "true";
    }
    public String visitCondicion_falso (Anasint.Condicion_falsoContext ctx) {
        return "false";
    }


    public String visitOp_aritmetica_envuelta (Anasint.Op_aritmetica_envueltaContext ctx) {
        return (String) visit(ctx.operacion());
    }
    public String visitOp_aritmetica_negacion (Anasint.Op_aritmetica_negacionContext ctx) {
        return "-" + visit(ctx.operacion());
    }
    public String visitOp_aritmetica_mult (Anasint.Op_aritmetica_multContext ctx) {
        return visit(ctx.operacion(0)) + "*" + visit(ctx.operacion(1));
    }
    public String visitOp_aritmetica_sr (Anasint.Op_aritmetica_srContext ctx) {
        String operador;
        if (ctx.MAS() != null)
            operador = "+";
        else
            operador = "-";
        return visit(ctx.operacion(0)) +
               operador +
               visit(ctx.operacion(1));
    }

    public String visitOperando_numerico (Anasint.Operando_numericoContext ctx) {
        return ctx.getText();
    }
    public String visitOperando_variable (Anasint.Operando_variableContext ctx) {
        return (String) visit(ctx.variable());
    }
    public String visitValor_booleano_true (Anasint.Valor_booleano_trueContext ctx) {
        return "true";
    }
    public String visitValor_booleano_false (Anasint.Valor_booleano_falseContext ctx) {
        return "false";
    }


    public String visitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String res = ctx.IDENT().getText() + "(";
        for (Anasint.Evaluacion_variableContext parametro: ctx.evaluacion_variable()) {
            res += visit(parametro) + ",";
        }
        return quitaUltimaComa(res) + ")";
    }
    public String visitSubprograma_ultima_posicion (Anasint.Subprograma_ultima_posicionContext ctx) {
        return ctx.UL_POS().getText() + "(" +
            visit(ctx.evaluacion_variable()) + ")";
    }
    public String visitSubprograma_mostrar (Anasint.Subprograma_mostrarContext ctx) {
        return ctx.MOSTRAR().getText() + "(" +
                visit(ctx.evaluacion_variable()) + ")";
    }
    public String visitSubprograma_vacia (Anasint.Subprograma_vaciaContext ctx) {
        return ctx.VACIA().getText() + "(" +
            visit(ctx.evaluacion_variable()) + ")";
    }

    public String visitVariable_acceso (Anasint.Variable_accesoContext ctx) {
        return visitVariable(ctx.variable()) + "[" + visit(ctx.operacion()) + "]";
    }

    public String visitVariable (Anasint.VariableContext ctx) {
        if (variablesBajoCondicion)
            variablesCondicion += ctx.getText() + ",";
        return ctx.getText();
    }

    public String visitEvaluacion_variable(Anasint.Evaluacion_variableContext ctx) {
        System.out.println("[COMPILADOR] visitEvaluacion_variable: " + ctx.getText() + "= " + ctx.getChild(0).getClass().toString());
        return (String) visit(ctx.getChild(0));
    }

    public String visitOperando_subprograma (Anasint.Operando_subprogramaContext ctx) {
        return (String) visit(ctx.subprograma());
    }

    public String visitOperando_secuencia_numerica (Anasint.Operando_secuencia_numericaContext ctx) {
        return "new Integer[]" + ctx.getText().replace("[","{").replace("]", "}");
    }

    public String visitOperando_secuencia_logica (Anasint.Operando_secuencia_logicaContext ctx) {
        String res = "new Boolean[]{";
            for (Anasint.Valor_booleanoContext bool: ctx.valor_booleano()) {
                res += visit(bool) + ",";
            }
        return quitaUltimaComa(res) + "}";
        //return "new boolean[]" + ctx.getText().replace("[","{").replace("]", "}");
    }

    public String visitDeclaracion_variable (Anasint.Declaracion_variableContext ctx) {
            String tipo = ctx.tipo().getText();
            String res = "";
            Map<String, String> scope = getUpperScope(ctx);
            if (!variablesPrivadas)
                res += "private static ";
            res += convierteTipo(tipo) + " ";
            for (ParseTree nombreVariable: ctx.IDENT()) {
                res += (nombreVariable + ",");
                scope.put(nombreVariable.getText(), convierteTipo(tipo));
            }
            res = quitaUltimaComa(res);
            res += ";\n";
        return res;
    }
}