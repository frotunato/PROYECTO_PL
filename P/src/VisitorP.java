import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Map;

public class VisitorP extends AnasintBaseVisitor<Object> {
    Map<Integer, AuxVariables> scopes = new OrderedHashMap<>();
    AuxVariables subprogramas = new AuxVariables();
    AuxVariables scopeGlobal = new AuxVariables();

    @Override
    public Object visitBloque_programa(Anasint.Bloque_programaContext ctx) {
        System.out.println("hello world");
        //AuxVariables scope = new AuxVariables();
        //this.visit(ctx.bloque_variables(), new AuxVariables());
        scopeGlobal = visitBloque_variables(ctx.bloque_variables());
        visitBloque_subprogramas(ctx.bloque_subprogramas());
        //this.visit(ctx.bloque_subprogramas(), scope);
        return 0;
        //return super.visitBloque_programa(ctx);
    }

    public AuxVariables visitBloque_variables(Anasint.Bloque_variablesContext ctx) {
        System.out.println("visitBloque_variables " + ctx.getText());
        AuxVariables scope = new AuxVariables();
        //scopes.add(scope);
        for (Anasint.Declaracion_variableContext variable: ctx.declaracion_variable()) {
             scope = visitDeclaracion_variable(variable, scope);
            //visitBloque_funcion(subprograma);
            //System.out.println(subprograma.getText());
        }
        //scopes.add(scope);
        return scope;
        //return super.visitBloque_variables(ctx);
    }

    public AuxVariables visitDeclaracion_variable(Anasint.Declaracion_variableContext ctx, AuxVariables scope) {
        //System.out.println("visitDeclaracion_variable " + ctx.getText());
        //AuxVariables scope = new AuxVariables();
        for (TerminalNode variable: ctx.IDENT())
            scope.declara(variable.getText(), ctx.tipo().getText());
        //scopes.add(scope);
        return scope;
        //return super.visitBloque_variables(ctx);
    }

    public Object visitBloque_subprogramas (Anasint.Bloque_subprogramasContext ctx) {
        //System.out.println("heheheheu " + scope.getVariablesDeclaradas());
        for (Anasint.Bloque_funcionContext funcion: ctx.bloque_funcion())
            visitBloque_funcion(funcion);

        /*for (Anasint.Bloque_funcionContext subprograma: ctx.bloque_funcion()) {
            visitBloque_funcion(subprograma);
            //System.out.println(subprograma.getText());
        }*/
        //return super.visitBloque_subprogramas(ctx);

        //System.out.println("visitBloque_subprogramas");
        return 1;
    }

    public Object visitBloque_funcion(Anasint.Bloque_funcionContext ctx) {
        AuxVariables scope = visitBloque_variables(ctx.bloque_variables());
        System.out.println("visitBloque_funcion " + scope.getVariablesDeclaradas());
        for (Anasint.Args_funcion_procedimientoContext args: ctx.args_funcion_procedimiento())
            scope = visitArgs_funcion_procedimiento(args, scope);
        scopes.put(scope.hashCode(), scope);
        for (Integer scope2: scopes.keySet())
            System.out.println("scope " + scope2 + " " + scopes.get(scope2).getVariablesDeclaradas());
        return 0;
        //return super.visitBloque_funcion(ctx);
    }

    public AuxVariables visitArgs_funcion_procedimiento (Anasint.Args_funcion_procedimientoContext ctx, AuxVariables scope) {
        //System.out.println("first " + ctx.lista_variables_tipadas());
        for (Anasint.Variable_tipadaContext varTipada: ctx.lista_variables_tipadas().variable_tipada())
            scope.declara(varTipada.IDENT().getText(), varTipada.tipo().getText());
        return scope;
    }
}
