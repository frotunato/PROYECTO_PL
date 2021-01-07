import java.util.ArrayList;
import java.util.List;

public class Subprograma {
    String nombre;
    String tipo;
    List<Variable> entrada = new ArrayList<>();
    List<Variable> salida = new ArrayList<>();


    public Subprograma (String nNombre, String nTipo, List<Variable> nEntrada, List<Variable> nSalida) {
        this.nombre = nNombre;
        this.tipo = nTipo;
        this.entrada.addAll(nEntrada);
        this.salida.addAll(nSalida);
    }

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
