import org.antlr.v4.misc.OrderedHashMap;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Programa {
    private final Map<String, Variable> variables = new OrderedHashMap<>();
    private final Map<String, Programa> subprogramas = new OrderedHashMap<>();
    private final List<Boolean> retornos = new ArrayList<>();
    private ParserRuleContext puntero = null;
    String tipo, nombre;

    public Programa (String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public ParserRuleContext getPuntero () {
        return this.puntero;
    }

    public void setPuntero (ParserRuleContext nPuntero) {
        this.puntero = nPuntero;
    }

    public String getTipo () { return this.tipo; }

    public boolean esArgumento () {
        return esFuncion(null) && getVarsSalida().size() == 1;
    }

    public boolean esFuncion (String scope) {
        if (scope == null)
            return this.tipo.equals("Funcion");
        else
            return subprogramas.get(scope).esFuncion(null);
    }

    public void addRetorno (String scope, boolean esRetornoValido) {
        if (scope == null)
            retornos.add(esRetornoValido);
        else
            subprogramas.get(scope).addRetorno(null, esRetornoValido);
    }

    public boolean existeRetornoValido (String scope) {
        if (scope == null)
            return retornos.contains(true);
        else
            return subprogramas.get(scope).existeRetornoValido(null);
    }

    public List<Variable> getVariables () {
        return new ArrayList<>(this.variables.values());
    }

    public String getNombre () {
        return this.nombre;
    }

    public Variable getVariable (String scope, String nombre) {
        if (scope == null)
            return variables.get(nombre);
        else
            return subprogramas.get(scope).getVariable(nombre);
    }

    public Variable getVariable (String nombre) {
        return this.variables.get(nombre);
    }

    public Boolean existeVariable (String nombre) {
        return variables.containsKey(nombre);
    }

    public void declaraVariable (String scope, Variable variable) {
        //if (variables.containsKey(variable.getNombre()))
        //    throw new IllegalStateException("La variable " + variable.getNombre() + " ya ha sido declarada con anterioridad");
        if (scope == null)
            this.variables.put(variable.getNombre(), variable);
        else
            subprogramas.get(scope).declaraVariable(null, variable);
        System.out.println("[PROGRAMA, declaraVariable]: " + variable.getNombre() + " " + variable.getTipo());
    }

    public void eliminaVariable (String scope, Variable variable) {
        if (scope == null)
            this.variables.remove(variable.getNombre());
        else
            subprogramas.get(scope).eliminaVariable(null, variable);
    }

    public List<Variable> getVarsEntrada () {
        return this.variables.values().stream().filter(x -> x.getScope().equals("Entrada")).collect(Collectors.toList());
    }

    public List<Variable> getVarsSalida () {
        return this.variables.values().stream().filter(x -> x.getScope().equals("Salida")).collect(Collectors.toList());
    }
    public List<Variable> getVarsInternas () {
        return this.variables.values().stream().filter(x -> x.getScope().equals("Interna")).collect(Collectors.toList());
    }

    public void declaraVarsEntrada (List<Variable> entrada) {
        for (Variable var: entrada) {
            var.inicializaVariable();
            var.setSoloLectura();
            var.setScope("Entrada");
            this.variables.put(var.getNombre(), var);
        }
    }

    public void declaraVarsSalida (List<Variable> salida) {
        for (Variable var: salida) {
            var.inicializaVariable();
            var.setScope("Salida");
            this.variables.put(var.getNombre(), var);
        }
    }

    public void declaraVarsInternas (List<Variable> interna) {
        for (Variable var: interna) {
            var.inicializaVariable();
            var.setScope("Interna");
            this.variables.put(var.getNombre(), var);
        }
    }

    public List<String> getTiposEntrada () {
        return this.variables.values().stream()
                .filter(x -> x.getScope().equals("Entrada"))
                .map(Variable::getTipo)
                .collect(Collectors.toList());
    }

    public List<String> getTiposSalida () {
        return this.variables.values().stream()
                .filter(x -> x.getScope().equals("Salida"))
                .map(Variable::getTipo)
                .collect(Collectors.toList());
    }


    public void declaraVariables (List<Variable> variables) {
       for (Variable variable: variables)
           this.variables.put(variable.getNombre(), variable);
    }

    public void declaraSubprogramaNativo (String nombre, String tipo){
        if (subprogramas.containsKey(nombre))
            throw new IllegalStateException("El subprograma nativo " + nombre + " ya ha sido declarado con anterioridad");
        subprogramas.put(nombre, new Programa(nombre, tipo));
        System.out.println("[SCOPE, declaraSubprogramaNativo]: " + nombre);
    }

    public void inicializaVariable (String scope, String nombre) {
        if (variables.containsKey(nombre))
            if (scope == null)
                variables.get(nombre).inicializaVariable();
            else
                subprogramas.get(scope).inicializaVariable(null, nombre);;
    }

    public void inicializaVariables (String scope, List<String> nombres) {
        if (scope == null)
            for (String nombre: nombres)
                variables.get(nombre).inicializaVariable();
        else
            for (String nombre: nombres)
                subprogramas.get(scope).getVariable(nombre).inicializaVariable();
    }

    public Programa getSubprograma (String nombre) { return subprogramas.get(nombre); }

    public void declaraSubprograma (Programa subprograma) {
        if (subprogramas.containsKey(subprograma.getNombre()))
            throw new IllegalStateException("El subprograma " + subprograma.getNombre() + " ya ha sido declarado con anterioridad");
        subprogramas.put(subprograma.getNombre(), subprograma);
        System.out.println("[SCOPE, declaraSubprograma]: " + subprograma.getNombre());
    }

    public boolean existeSubprograma (String nombre) { return subprogramas.containsKey(nombre); }

    public boolean existeFuncion (String nombre) {
        return existeSubprograma(nombre) && subprogramas.containsKey(nombre) &&
                subprogramas.get(nombre).getTipo().equals("Funcion");
    }

    public boolean existeProcedimiento (String nombre) {
        return existeSubprograma(nombre) && subprogramas.containsKey(nombre) &&
                subprogramas.get(nombre).getTipo().equals("Procedimiento");
    }

    public boolean existeVariable (String scope, String nombre) {
        if (scope == null)
            return variables.containsKey(nombre);
        else
            return subprogramas.get(scope).existeVariable(nombre);
    }


}
