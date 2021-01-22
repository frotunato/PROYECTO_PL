import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class Subprograma {
    private String nombre;
    private String tipo;
    private ParserRuleContext puntero = null;

    private List<Variable> entrada = new ArrayList<>();
    private List<Variable> salida = new ArrayList<>();
    private List<Variable> declaradas = new ArrayList<>();

    public Subprograma (String nNombre, String nTipo) {
        this.nombre = nNombre;
        this.tipo = nTipo;
    }

    public void setPuntero (ParserRuleContext nPuntero) {
        this.puntero = nPuntero;
    }

    public Subprograma (String nNombre, String nTipo, List<Variable> nEntrada, List<Variable> nSalida) {
        if (nTipo.equals("Funcion") && nSalida.size() == 0)
            throw new IllegalArgumentException("Un subprograma debe tener argumentos de salida");
        this.nombre = nNombre;
        this.tipo = nTipo;
        this.entrada.addAll(nEntrada);
        this.salida.addAll(nSalida);
    }

    public Subprograma (String nNombre, String nTipo, List<Variable> nEntrada, List<Variable> nSalida, List<Variable> declaradas) {
        if (nTipo.equals("Funcion") && nSalida.size() == 0)
            throw new IllegalArgumentException("Un subprograma debe tener argumentos de salida");
        this.nombre = nNombre;
        this.tipo = nTipo;
        this.entrada.addAll(nEntrada);
        this.salida.addAll(nSalida);
        this.declaradas.addAll(declaradas);
    }

    public ParserRuleContext getPuntero () { return this.puntero; }

    public boolean esFuncion () {
        return this.tipo.equals("Funcion");
    }

    public boolean esArgumento () {
        return esFuncion() && this.salida.size() == 1;
    }

    public String getTipo () { return this.tipo; }

    public String getNombre() {
        return nombre;
    }

    public List<Variable> getEntrada() {
        return entrada;
    }

    public List<Variable> getDeclaradas () {
        return declaradas;
    }

    public boolean existeVariable (String nombre) {
        boolean res = false;
        boolean paro = false;
        for (Variable variable: salida) {
            if (variable.getNombre().equals(nombre)) {
                res = true;
                paro = true;
                break;
            }
        }
        if (!paro)
            for (Variable variable: entrada) {
                if (variable.getNombre().equals(nombre)) {
                    res = true;
                    paro = true;
                    break;
                }
            }
        if (!paro)
            for (Variable variable: declaradas) {
                if (variable.getNombre().equals(nombre)) {
                    res = true;
                    break;
                }
            }
        return res;
    }

    public List<String> getTiposEntrada () {
        List<String> tipos = new ArrayList<>();
        for (Variable entrada: this.entrada)
            tipos.add(entrada.getTipo());
        return tipos;
    }

    public List<String> getTiposSalida () {
        List<String> tipos = new ArrayList<>();
        for (Variable salida: this.salida)
            tipos.add(salida.getTipo());
        return tipos;
    }

    public List<Variable> getSalida() {
        return salida;
    }
}
