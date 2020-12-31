import java.util.HashSet;
import java.util.Set;

public class ExtraerVarsExpr extends AnasintBaseVisitor<Object> {

    public Set<String> visitTipo (Anasint.TipoContext ctx) {
        Set<String> s = new HashSet<String>();
        s.add(ctx.getText());
        System.out.println("hello from visitor" + ctx.getText());
        return s;
    }
}
