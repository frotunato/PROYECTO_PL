public class Variable {
    String nombre;
    String tipo;
    boolean inicializada = false;

    public Variable (String nNombre, String nTipo) {
        this.nombre = nNombre;
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
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isInicializada() {
        return inicializada;
    }

    public void inicializaVariable () {
        inicializada = true;
    }

}
