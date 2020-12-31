import org.antlr.v4.runtime.tree.TerminalNode;

public class Anasem extends AnasintBaseListener {
    ExtraerVarsExpr extractor = new ExtraerVarsExpr();
    AuxVariables auxVariables = new AuxVariables();

    public void exitVariable (Anasint.VariableContext ctx) {
        if (!auxVariables.estaDeclarada(ctx.getText()))
            throw new IllegalStateException("La variable " + ctx.getText() + " no está declarada");
        //System.out.println(ctx.getText() + " declarada: " + auxVariables.estaDeclarada(ctx.getText()));
    }

    //registramos la declaración y tipado de variables
    public void exitBloque_variables (Anasint.Bloque_variablesContext ctx) {
        System.out.println("Hello from listener exitBloque_variables");
        for (Anasint.Declaracion_variableContext declaracionVariable: ctx.declaracion_variable())
            for (TerminalNode variable: declaracionVariable.lista_variables().IDENT())
                auxVariables.declara(variable.getText(), declaracionVariable.tipo().getText());
            //for (Anasint.VariableContext variable: declaracionVariable.lista_variables().variable())

        System.out.println(auxVariables.getVariablesDeclaradas());
    }
    public void enterArgs_funcion_procedimiento (Anasint.Args_funcion_procedimientoContext ctx) {
        System.out.println("Hello from listener exitArgs_funcion_procedimiento " + ctx.getText());
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
