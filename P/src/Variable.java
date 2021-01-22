import java.util.Objects;

public class Variable {
    private String nombre;
    private String tipo;
    private boolean inicializada = false;
    private boolean soloLectura = false;

    public Variable (String nNombre, String nTipo) {
        this.nombre = nNombre;
        //this.valor = new Object();
        switch (nTipo) {
            case "LOG" -> this.tipo = "Boolean";
            case "NUM" -> this.tipo = "Integer";
            case "SEQ(NUM)" -> this.tipo = "ArrayList<Integer>";
            case "SEQ(LOG)" -> this.tipo = "ArrayList<Boolean>";
            //case "SEQ" -> this.tipo = "ArrayList<>";
            default -> throw new IllegalStateException("Unexpected value: " + nTipo);
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isSoloLectura () {
        return soloLectura;
    }

    public void setSoloLectura () {
        this.soloLectura = true;
    }

    public boolean isInicializada() {
        return this.inicializada;
    }

    public void inicializaVariable () {
        inicializada = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return nombre.equals(variable.nombre) && tipo.equals(variable.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, tipo);
    }
}
