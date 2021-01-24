public class InformadorErrores {
    private String nombre;
    private String color;
    public InformadorErrores (String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    public void informa (String mensaje){
        System.out.println(color + this.nombre + " " +
                mensaje + ConsoleColors.RESET);
    }
}
