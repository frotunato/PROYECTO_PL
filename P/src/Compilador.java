import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.*;

public class Compilador extends AnasintBaseListener{
    //ParseTreeProperty<Scope> scopeTree = new ParseTreeProperty<>();
    String programa = "";
    boolean multiarg = false;
    boolean variablesPrivadas = false;
    boolean necesitaComa = false;
    boolean usaPrefijo = true;
    boolean nuevaLinea = false;
    boolean retornoFuncion = false;
    boolean pyc = false;
    Stack<String> prefijos = new Stack<String>();
    Stack<Boolean> funcionesAsignadas = new Stack<Boolean>();
    ParseTreeProperty<OrderedHashMap<String, String>> scope = new ParseTreeProperty<>();
    Map<String, List<String>> funciones = new OrderedHashMap<>();
    private String convierteTipo (String tipoP) {
        String res = "";
        switch (tipoP) {
            case "NUM" -> res = "int";
            case "LOG" -> res = "boolean";
            case "SEQ(NUM)" -> res = "int[]";
            case "SEQ(LOG)" -> res = "boolean[]";
        }
        return res;
    }

    private String quitaUltimaComa (String str) {
        return str.substring(0 ,str.lastIndexOf(","));
    }

    private OrderedHashMap getUpperScope (ParserRuleContext ctx) {
        if (scope.get(ctx) != null)
            return scope.get(ctx);
        else
            if (ctx.getParent() == null)
                return null;
            return getUpperScope(ctx.getParent());
    }

    @Override
    public void enterDeclaracion_variable (Anasint.Declaracion_variableContext ctx) {
        String tipo = ctx.tipo().getText();
        OrderedHashMap scope = getUpperScope(ctx);
        if (!variablesPrivadas)
            programa += "private static ";
        //programa += "private static " + convierteTipo(tipo);
        programa += convierteTipo(tipo) + " ";

        for (ParseTree nombreVariable: ctx.IDENT()) {
            programa += nombreVariable + ",";
            scope.put(nombreVariable.getText(), convierteTipo(tipo));
        }
        programa = quitaUltimaComa(programa);
        programa += ";\n";
    }

    public void enterBloque_variables(Anasint.Bloque_variablesContext ctx) {
        OrderedHashMap scopePadre = getUpperScope(ctx);
        if (!ctx.getParent().getClass().equals(Anasint.Bloque_programaContext.class)) {
            OrderedHashMap<String, String> scopeHijo = new OrderedHashMap();
            scopeHijo.putAll(scopePadre);
            scope.put(ctx, scopeHijo);
        }
    }



    @Override
    public void enterBloque_programa(Anasint.Bloque_programaContext ctx) {
        scope.put(ctx, new OrderedHashMap<String, String>());
        programa += "public class Test {\n";
        super.enterBloque_programa(ctx);
    }
    @Override
    public void exitBloque_subprogramas (Anasint.Bloque_subprogramasContext ctx) {
        programa += "public static void main(String[] args) throws Exception {\n";
    }

    public void exitValor_booleano_true (Anasint.Valor_booleano_trueContext ctx) {
        if (necesitaComa)
            programa += "true,";
        else
            programa += "true";
    }

    public void exitValor_booleano_false (Anasint.Valor_booleano_falseContext ctx) {
        if (necesitaComa)
            programa += "false,";
        else
            programa += "false";
    }

    @Override
    public void enterOperando_secuencia_vacia (Anasint.Operando_secuencia_vaciaContext ctx) {
        programa += "{}";
    }

    @Override
    public void enterOperando_secuencia_logica (Anasint.Operando_secuencia_logicaContext ctx) {
        programa += "new boolean[]{";
        necesitaComa = true;
    }

    @Override
    public void exitOperando_secuencia_logica (Anasint.Operando_secuencia_logicaContext ctx) {
        programa = quitaUltimaComa(programa) + "}";
        necesitaComa = false;
    }

    @Override
    public void enterInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        usaPrefijo = true;
        String str = "";
        pyc = true;
        Stack<String> pilaVariables = new Stack<>();
        for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
            pilaVariables.add(variable.getText() + "=");
        }
        Collections.reverse(pilaVariables);

        for (Anasint.Evaluacion_variableContext valor: ctx.evaluaciones_variables().evaluacion_variable()) {
            if (valor.subprograma() != null) {
                str = valor.subprograma().getText();
                str = str.substring(0, str.indexOf("("));
                prefijos.add("Object[] salida_" + str + "=");
                for (int i = 0; i < funciones.get(str).size(); i++) {
                    prefijos.add(pilaVariables.pop());
                }
                //programa += "\nObject[] salida_" + str + "=" + valor.getText() + ";\n";
                funcionesAsignadas.add(true);
                //retornoFuncion = true;
            } else
                prefijos.add(pilaVariables.pop());
        }

        Collections.reverse(prefijos);

    }

    public void enterInstruccion_asig2 (Anasint.Instruccion_asigContext ctx) {
        String str;
        List<String> asignaciones = new ArrayList<>();
        OrderedHashMap<String, String> scopePadre = getUpperScope(ctx);

        // se analizan todos los tipos de la derecha asignacion
        for (Anasint.Evaluacion_variableContext valor: ctx.evaluaciones_variables().evaluacion_variable()) {
            if (valor.subprograma() != null) {
                str = valor.subprograma().getText();
                str = str.substring(0, str.indexOf("("));
                programa += "\nObject[] salida_" + str + "=" + valor.getText() + ";\n";
                for (int i = 0; i < funciones.get(str).size(); i++) {
                    asignaciones.add("(" + funciones.get(str).get(i).trim() + ") salida_" + str + "[" + i + "]");
                }
            } else
                asignaciones.add(valor.getText());
        }

        //se mira si a la izq hay una o mas variables
        usaPrefijo = true;
        if (ctx.lista_variables().variable().size() == 1) {
            prefijos.push(ctx.lista_variables().variable(0).getText() + "=");
            //programa += ctx.lista_variables().variable(0).getText() + "=" + asignaciones.get(0) + ";\n";
        } else {
            programa += "\nObject[] t = new Object[]{";

            for (String asignacion: asignaciones) {
                //programa += asignacion + ",";
            }


            /*esto al final?=?
            programa = quitaUltimaComa(programa);
            programa += "};\n";

            int i = 0;
            //resuelve multiasignacion
            for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
                programa += variable.getText() + "= (" + scopePadre.get(variable.getText()) + ") t[" + i + "];\n";
                i++;
            }*/
        }

        /*
        //caso de asignacion multiple
        if (asignaciones.size() > 1) {
            int i = 0;
            for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
                programa += variable.getText() + "=" + asignaciones.get(i) + ";\n";
                i++;
            }
        } else {
            programa += ctx.lista_variables().variable(0).getText() + "=" + asignaciones.get(0) + ";\n";
        }
        */
    }

    public void exitInstruccion_asig (Anasint.Instruccion_asigContext ctx) {
        usaPrefijo = false;
        pyc = false;
        retornoFuncion = false;
        /*
            usaPrefijo = false;
            OrderedHashMap<String, String> scopePadre = getUpperScope(ctx);

            programa = quitaUltimaComa(programa);
            programa += "};\n";

            int i = 0;
            //resuelve multiasignacion
            for (Anasint.VariableContext variable: ctx.lista_variables().variable()) {
                programa += variable.getText() + "= (" + scopePadre.get(variable.getText()) + ") t[" + i + "];\n";
                i++;
            }
        */
    }

    @Override
    public void exitOperando_secuencia_numerica (Anasint.Operando_secuencia_numericaContext ctx) {
        String str = ctx.getText();
        if (usaPrefijo)
            programa += prefijos.pop();
            programa += "new int[]";
        str = str.replace("[", "{").replace("]", "}");
        //str = "new int[] " + str;
                //necesitaComa = false;
        if (necesitaComa)
            programa += str + ",";
        else
            programa += str;
        if (pyc)
            programa += ";";
    }

    @Override
    public void enterEvaluaciones_variables (Anasint.Evaluaciones_variablesContext ctx) {
        //programa += ctx.getText();
    }

    @Override
    public void enterSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        String nombre;
        nombre = ctx.IDENT().getText();

        if (!funcionesAsignadas.isEmpty()) {
            programa += prefijos.pop() + ctx.getText() + ";\n";
            for (int i = 0; i < funciones.get(nombre).size(); i++) {
                programa += prefijos.pop() + "salida_" + nombre + "[" + i + "];\n";
            }

            //programa += prefijos.pop();
        }
        //retornoFuncion = false;
    }

    public void exitSubprograma_declarado (Anasint.Subprograma_declaradoContext ctx) {
        funcionesAsignadas.pop();
    }

    @Override
    public void enterEvaluacion_variable (Anasint.Evaluacion_variableContext ctx) {
        System.out.println("[COMPILADOR] enterEvaluacion_variable: " + ctx.getText());
        //if (ctx.getParent().getClass().equals(Anasint.Evaluaciones_variablesContext.class))
        //    return;

        //if (ctx.operando() != null && prefijos.size() > 1)
        //    return;

        if (ctx.subprograma() == null && ctx.operando_secuencia() == null) { //&& funcionesAsignadas.isEmpty()) {
            if (usaPrefijo)
                programa += prefijos.pop();
            if (necesitaComa)
                programa += ctx.getText() + ",";
            else
                programa += ctx.getText();
            if (pyc)
                programa += ";\n";
        }

    }
    @Override
    public void enterInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        programa += "return ";
        if (ctx.evaluaciones_variables().evaluacion_variable().size() > 1) {
            necesitaComa = true;
            programa += "new Object[]{";
        } else
            programa += ctx.evaluaciones_variables().evaluacion_variable(0).getText();
    }
    @Override
    public void exitInstruccion_retorno (Anasint.Instruccion_retornoContext ctx) {
        if (ctx.evaluaciones_variables().evaluacion_variable().size() > 1) {
            necesitaComa = false;
            //programa += "};";
            programa = quitaUltimaComa(programa) + "};";
        } else
            programa += ";";
    }

    /*
    public void exitEvaluaciones_variables (Anasint.Evaluaciones_variablesContext ctx) {
        String str = "";

    }
  */

    @Override
    public void exitBloque_programa(Anasint.Bloque_programaContext ctx) {
        programa += "\n}";
        System.out.println(programa);
        //super.enterBloque_programa(ctx);
    }

    @Override
    public void enterBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        programa += "private static ";
        OrderedHashMap scopePadre = getUpperScope(ctx);
        variablesPrivadas = true;
        String declaracionVariablesSalida = "";
        String nombrePrograma = ctx.IDENT().getText();
        funciones.put(nombrePrograma, new ArrayList<String>());
        int i = 0;
        int j = 0;
        String nombre = "";

        //vemos si la funcion tiene args entrada y salida o solo salida
        if (ctx.lista_variables_tipadas().size() > 1)
            i = 1;

        //la funcion tiene varios args de salida
        if (ctx.lista_variables_tipadas(i).IDENT().size() > 1) {
            programa += "Object[] ";
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(1).tipo()) {
                j = tipo.getParent().children.indexOf(tipo);
                nombre = tipo.parent.getChild(j + 1).getText();
                declaracionVariablesSalida += convierteTipo(tipo.getText()) + " " + nombre +";\n";
                scopePadre.put(nombre, convierteTipo(tipo.getText()));
                //funciones.get(nombrePrograma).add((tipo.getText()));
            }
        }
        //funcion solo tiene un arg de salida
        else {
            Anasint.TipoContext tipo = ctx.lista_variables_tipadas(i).tipo(0);
            j = tipo.getParent().children.indexOf(tipo);
            nombre = tipo.parent.getChild(j + 1).getText();
            tipo.parent.getChild(j + 1).getText();
            programa += convierteTipo(tipo.getText()) + " ";
            declaracionVariablesSalida += convierteTipo(tipo.getText()) + " " + nombre +";\n";
            scopePadre.put(nombre, convierteTipo(tipo.getText()));

            /*
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(i).tipo()) {
                if (tipo.parent.getChild(j+2) != null)
                    programa += tipo.parent.getChild(j+2);
            }
            */
        }
        //entrada
        programa += ctx.IDENT().getText() + " (";
        if (i > 0) {
            for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(0).tipo()) {
                j = tipo.getParent().children.indexOf(tipo);
                nombre = tipo.parent.getChild(j + 1).getText();
                programa += convierteTipo(tipo.getText()) + " " + nombre;
                scopePadre.put(nombre, convierteTipo(tipo.getText()));
                if (tipo.parent.getChild(j+2) != null)
                    programa += tipo.parent.getChild(j+2);
                /*
                if (ctx.getToken(Anasint.COMA,j + 2) != null)
                    programa += ", ";*/
            }
        }
        programa += ") {\n" + declaracionVariablesSalida;

        for (Anasint.TipoContext tipo: ctx.lista_variables_tipadas(1).tipo()) {
            funciones.get(nombrePrograma).add(convierteTipo(tipo.getText()));
        }
    }

    @Override
    public void exitBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        variablesPrivadas = false;
        programa += "\n}\n";
    }
}
