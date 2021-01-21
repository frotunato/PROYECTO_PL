import java.util.Arrays;
import java.util.stream.IntStream;
public class Test {
    private static Integer ultima_posicion (Integer[] entrada) {
        return entrada[entrada.length-1];
    }
    private static Boolean ultima_posicion (Boolean[] entrada) {
        return entrada[entrada.length-1];
    }
    private static Boolean vacia (Boolean[] entrada) {
        return entrada.length == 0;
    }
    private static Boolean vacia (Integer[] entrada) {
        return entrada.length == 0;
    }
    private static void mostrar (Boolean[] entrada) {
        System.out.println("Mostrar: " + Arrays.toString(entrada));
    }
    private static void mostrar (Integer[] entrada) {
        System.out.println("Mostrar: " + Arrays.toString(entrada));
    }
    private static Integer a;
    private static Integer global_i,global_j,global_f,global_k;
    private static Integer[] global_s;
    private static Boolean global_error;
    private static Integer resultado;

    private static Integer test3 (Integer a) {
        Integer c;
        Integer global_i2,c2;
        c=0;
        if (!Arrays.asList().contains(null)&&
                (new Integer[]{1,3} == new Integer[]{1,3})) {
            mostrar(new Integer[]{1,2,3});
            c=11;
        } else {
            mostrar(new Integer[]{1,1,1,1});
            c=2;
        }
        if (!Arrays.asList().contains(null)&&
                (true)) {
            throw new IllegalArgumentException("Aserto no válido");
        }
        return c;
    }

    public static void main(String[] args) throws Exception {
        a=9;
        global_s=new Integer[]{1,2,3,4,5,6,7,6,18};
        resultado=9;
        if (!Arrays.asList(global_s,resultado).contains(null) && !(IntStream.range(0, a).allMatch(p -> (
                (global_s[p] <= resultado))))) {
            throw new IllegalArgumentException("Aserto no válido");
        }
    }
}