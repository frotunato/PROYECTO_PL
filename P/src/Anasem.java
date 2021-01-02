import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.Map;

public class Anasem extends AnasintBaseListener {
    ExtraerVarsExpr extractor = new ExtraerVarsExpr();
    AuxVariables auxVariables = new AuxVariables();
    Map<RuleContext, AuxVariables> almacen = new HashMap<>();

    public void exitVariable (Anasint.VariableContext ctx) {
        //System.out.println("exitVariable " + ctx.parent);
        if (!auxVariables.estaDeclarada(ctx.getText()))
            throw new IllegalStateException("La variable " + ctx.getText() + " no está declarada");
        //System.out.println(ctx.getText() + " declarada: " + auxVariables.estaDeclarada(ctx.getText()));
    }
    //public void enterBloque_variables (Anasint.Bloque_variablesContext ctx) {
        //System.out.println("Hello from listener enterBloque_variables " + ctx.parent);
    //}
    //registramos la declaración y tipado de variables
    public void exitBloque_variables (Anasint.Bloque_variablesContext ctx) {
        //System.out.println("Hello from listener exitBloque_variables " + ctx.getRuleContext().toString());
        AuxVariables NauxVariables = new AuxVariables();
        for (Anasint.Declaracion_variableContext declaracionVariable: ctx.declaracion_variable())
            for (TerminalNode variable: declaracionVariable.IDENT()) {
                System.out.println("plugging on ");
                NauxVariables.declara(variable.getText(), declaracionVariable.tipo().getText());
            }

            //for (Anasint.VariableContext variable: declaracionVariable.lista_variables().variable())
        almacen.put(ctx.parent, NauxVariables);
        System.out.println(NauxVariables.getVariablesDeclaradas());
    }
    public void enterArgs_funcion_procedimiento (Anasint.Args_funcion_procedimientoContext ctx) {
        //System.out.println("Hello from listener exitArgs_funcion_procedimiento " + ctx.getText());
        for (Anasint.Variable_tipadaContext variablesTipadas: ctx.lista_variables_tipadas().variable_tipada()) {
            auxVariables.declara(variablesTipadas.IDENT().getText(), variablesTipadas.tipo().getText());
        }
    }
    /*

    public void enterDeclaracion_variable(Anasint.Declaracion_variableContext ctx) {
        almacenar_variable_declarada(ctx.getText());
        extractor.visitTipo_elemental(ctx.);
        System.out.println(almacen_variables_declaradas.toString());
    }
    public void enterInstruccion(Anasint.InstruccionContext ctx) {
        //System.out.println("vuamos " + ctx.getText());
        //almacenar_variable_declarada(ctx.getText());
    }

    public void visitInstruccion(Anasint.IdVariableContext ctx) {
        System.out.println("asdasd");
    }*/
}
