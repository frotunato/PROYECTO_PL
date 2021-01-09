import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class ListenerInterprete extends AnasintBaseListener{
    ParseTreeProperty<Scope> scopeTree = new ParseTreeProperty<>();

    private Scope getUpperScope (ParserRuleContext ctx) {
        if (scopeTree.get(ctx) != null)
            return scopeTree.get(ctx);
        else
            return getUpperScope(ctx.getParent());
    }

    @Override
    public void enterBloque_programa(Anasint.Bloque_programaContext ctx) {
        //scopeTree.put(ctx, "Hola");
        super.enterBloque_programa(ctx);
    }

    public void enterBloque_funcion (Anasint.Bloque_funcionContext ctx) {
        System.out.println("Declarando scope en funcion " + ctx.IDENT().getText());
        scopeTree.put(ctx, new Scope(ctx.IDENT().getText()));
    }

    public void exitInstruccion_asignacion (Anasint.Instruccion_asigContext ctx) {
        ctx.evaluaciones_variables();
    }

    //public void exitBloque_variables (Anasint.Bloque_variablesContext ctx) {
    //    Scope scope = getUpperScope(ctx);

    //}
/*
    public void exitInstruccion(Anasint.InstruccionContext ctx) {
        String frase = getUpperScope(ctx);
        System.out.println(frase + " " + ctx.getText());
    }
*/

}
