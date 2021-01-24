import java.util.Arrays;
public class Test {
    private static Integer ultima_posicion (Object[] entrada) {
        return entrada.length - 1;
    }
    private static Boolean vacia (Object[] entrada) {
        return entrada.length == 0;
    }
    private static void mostrar (Object[] entrada) {
        System.out.println("Mostrar: " + Arrays.toString(entrada));
    }
    private static Integer a,b,c;
    private static Integer[] seq;

    public static void asd (Object[] as) {
        Integer[] s = (Integer[]) as;
        System.out.println("asd");
        s[2] = 25;
    }

    public static void main(String[] args) throws Exception {
        a=1;
        b=2;
        c=a+b;
        asd(new Object[]{1,3,4,5});
    }
}