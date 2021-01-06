import java.util.ArrayList;
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
