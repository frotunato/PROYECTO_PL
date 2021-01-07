import java.util.ArrayList;
import java.util.List;

public class Procedimiento {
    String nombre;
    List<Variable> entrada;

    public Procedimiento (String nNombre, List<Variable> nEntrada) {
        this.nombre = nNombre;
        this.entrada = nEntrada;
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

}