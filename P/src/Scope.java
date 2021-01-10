import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Scope {
    private final Map<String, Variable> variables = new OrderedHashMap<>();
    private final Map<String, Subprograma> subprogramas = new OrderedHashMap<>();
    private final String nombre;
    private ParserRuleContext puntero = null;

    public Collection<Variable> getVariablesDeclaradas () {
        return variables.values();
    }

    public void declaraVariable (String nombre, String tipo) {
        if (variables.containsKey(nombre))
            throw new IllegalStateException("La variable " + nombre + " ya ha sido declarada con anterioridad");
        variables.put(nombre, new Variable(nombre, tipo));
        System.out.println("[SCOPE, declaraVariable]: " + nombre + " " + tipo);

    }



    public Scope (Scope scopePadre, String nNombre) {
        this.subprogramas.putAll(scopePadre.subprogramas);
        this.variables.putAll(scopePadre.variables);
        this.nombre = nNombre;
    }

    public Scope (String nNombre) {
        System.out.println("Created new scope " + nNombre);
        this.nombre = nNombre;
    }

    public Scope (Scope scopePadre, String nNombre, ParserRuleContext ctx) {
        this.subprogramas.putAll(scopePadre.subprogramas);
        this.variables.putAll(scopePadre.variables);
        this.nombre = nNombre;
        this.puntero = ctx;
    }

    public ParserRuleContext getPuntero () { return this.puntero; }

    public String getNombre () { return this.nombre; }

    public void inicializaVariable (String nombre) {
        variables.get(nombre).inicializaVariable();
    }

    public void inicializaVariables (List<Variable> variables) {
        for (Variable variable: variables)
            variable.inicializaVariable();
    }

    public Variable getVariable (String nombre) {
        return variables.get(nombre);
    }

    public Subprograma getSubprograma (String nombre) { return subprogramas.get(nombre); };

    public void declaraVariable (Variable variable) {
        if (variables.containsKey(variable.getNombre()))
            throw new IllegalStateException("La variable " + variable.getNombre() + " ya ha sido declarada con anterioridad");
        variables.put(variable.getNombre(), variable);
        System.out.println("[SCOPE, declaraVariable]: " + variable.getNombre() + " " + variable.getTipo());

    }

    public void declaraVariables (List<Variable> variables) {
        for (Variable variable: variables)
            declaraVariable(variable);
    }

    public void declaraSubprogramaNativo (String nombre, String tipo){
        if (subprogramas.containsKey(nombre))
            throw new IllegalStateException("El subprograma nativo " + nombre + " ya ha sido declarado con anterioridad");
        subprogramas.put(nombre, new Subprograma(nombre, tipo));
        System.out.println("[SCOPE, declaraSubprogramaNativo]: " + nombre);
    }

    public void declaraSubprograma (String nombre, String tipo, List<Variable> varsEntrada, List<Variable> varsSalida) {
        if (subprogramas.containsKey(nombre))
            throw new IllegalStateException("El subprograma " + nombre + " ya ha sido declarado con anterioridad");
        subprogramas.put(nombre, new Subprograma(nombre, tipo, varsEntrada, varsSalida));
        System.out.println("[SCOPE, declaraSubprograma]: " + nombre +  ", inpt: " + varsEntrada.toString() + ", out: " + varsSalida.toString());
    }

    public Boolean existeVariable (String nombre) {
        return variables.containsKey(nombre);
    }

    public Boolean existeFuncion (String nombre) {
        return subprogramas.containsKey(nombre) &&
                subprogramas.get(nombre).getTipo().equals("Funcion");
    }

    public Boolean existeProcedimiento (String nombre) {
        return subprogramas.containsKey(nombre) &&
                subprogramas.get(nombre).getTipo() == "Procedimiento";
    }

    public Boolean existeSubprograma (String nombre) { return subprogramas.containsKey(nombre); }
}