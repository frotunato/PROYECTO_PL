import java.util.List;

public class Funcion {
    String nombre;
    List<Variable> entrada;
    List<Variable> salida;

    public Funcion (String nNombre, List<Variable> nEntrada, List<Variable> nSalida) {
        this.nombre = nNombre;
        this.entrada = nEntrada;
        this.salida = nSalida;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Variable> getEntrada() {
        return entrada;
    }

    public List<Variable> getSalida() {
        return salida;
    }
}
