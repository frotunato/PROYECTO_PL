import java.util.Arrays;
public class Test {
    private static Integer ultima_posicion (int[] entrada) {
        return entrada[entrada.length-1];
    }
    private static Boolean ultima_posicion (boolean[] entrada) {
        return entrada[entrada.length-1];
    }
    private static Boolean vacia (Boolean[] entrada) {
        return entrada.length == 0;
    }
    private static Boolean vacia (Integer[] entrada) {
        return entrada.length == 0;
    }
    private static void mostrar (boolean[] entrada) {
        System.out.println(entrada);
    }
    private static void mostrar (int[] entrada) {
        System.out.println(entrada);
    }
    private static Integer a;
    private static Integer global_i,global_j,global_k;
    private static Integer[] global_s;
    private static Boolean global_error;

    private static void test2 (Integer b) {
        Integer a,acum,limite;
        a=b;
        acum=a+25;
        limite=0;
        while (limite < 2) {
            limite=limite+1;
            a=a*acum;
        }
        global_j=acum;
    }
    private static Integer test (Integer a,Integer b) {
        Integer c;
        Integer global_i2,c2;
        c=0;

        if (!Arrays.asList(global_error,global_k).contains(null) && (global_error == true || 1 < 2)) {
            global_i=25;
            c=global_i+b;
        }
        return c+1;
    }

    public static void main(String[] args) throws Exception {
        a=100;
        test2(25);
        global_i=test(global_j,2);
    }
}