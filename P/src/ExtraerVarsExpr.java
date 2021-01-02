import java.util.HashSet;
import java.util.Set;

public class ExtraerVarsExpr extends AnasintBaseVisitor<Object> {

    public Set<String> visitBloque_variables (Anasint.Bloque_variablesContext ctx) {
        Set<String> s = new HashSet<String>();
        s.add(ctx.getText());
        System.out.println("hello from visitor" + ctx.getText());
        return s;
    }


}
