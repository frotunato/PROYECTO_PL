import org.antlr.v4.misc.OrderedHashMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Scope {
    private final Map<String, Variable> variables = new OrderedHashMap<>();
    private final Map<String, Funcion> funciones = new OrderedHashMap<>();
    private final Map<String, Procedimiento> procedimientos = new OrderedHashMap<>();

    public Collection<Variable> getVariablesDeclaradas () {
        return variables.values();
    }

    public void declaraVariable (String nombre, String tipo) {
        if (variables.containsKey(nombre))
            throw new IllegalStateException("La variable " + nombre + " ya ha sido declarada con anterioridad");
        variables.put(nombre, new Variable(nombre, tipo));
        System.out.println("[SCOPE, declaraVariable]: " + nombre + " " + tipo);

    }

    public void inicializaVariable (String nombre) {
        variables.get(nombre).inicializaVariable();
    }

    public Variable getVariable (String nombre) {
        return variables.get(nombre);
    }

    public void declaraVariable (Variable variable) {
        if (variables.containsKey(variable.nombre))
            throw new IllegalStateException("La variable " + variable.nombre + " ya ha sido declarada con anterioridad");
        variables.put(variable.nombre, variable);
        System.out.println("[SCOPE, declaraVariable]: " + variable.nombre + " " + variable.tipo);

    }

    public void declaraVariables (List<Variable> variables) {
        for (Variable variable: variables)
            declaraVariable(variable);
    }

    public void declaraFuncion (String nombre, List<Variable> varsEntrada, List<Variable> varsSalida) {
        if (funciones.containsKey(nombre))
            throw new IllegalStateException("La funcion " + nombre + " ya ha sido declarada con anterioridad");
        funciones.put(nombre, new Funcion(nombre, varsEntrada, varsSalida));
        System.out.println("[SCOPE, declaraFuncion]: " + nombre +  ", inpt: " + varsEntrada.toString() + ", out: " + varsEntrada.toString());
        //System.out.println("[" + this.getClass() + "], mem <- " + nombre + ", tipo " + tipoVariable);
    }

    public Boolean existeVariable (String nombre) {
        //Boolean estado = this.memoria.containsKey(nombreVariable);
        //System.out.println("[" + this.getClass() + "] " + nombreVariable + " declarada? " + estado);
        return variables.containsKey(nombre);
    }

    public Boolean existeFuncion (String nombre) {
        return funciones.containsKey(nombre);
    }
}
