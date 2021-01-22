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
    private static Integer a,i,max;
    private static Integer global_i,global_j,global_f,global_k;
    private static Integer[] global_s;
    private static Boolean global_error;
    private static Integer resultado;

    private static Integer bucle_1_avance (Integer[] seq,Integer pos) {
        Integer r;
        r=ultima_posicion(seq)-pos;
        return r;
    }
    private static Integer test3 (Integer a) {
        Integer c;
        Integer global_i2,c2;
        c=0;
        if (!Arrays.asList().contains(null) &&
                (new Integer[]{1,3} == new Integer[]{1,3})) {
            mostrar(new Integer[]{1,2,3});
            c=11;
        } else {
            mostrar(new Integer[]{1,1,1,1});
            c=2;
        }
        if (!Arrays.asList().contains(null) &&
                (!false)) {
            throw new IllegalArgumentException("Aserto no válido");
        }
        return c;
    }

    public static void main(String[] args) throws Exception {
        a=11;
        global_s=new Integer[]{100,4,2,7,9,0,11};
        i=0;
        max=global_s[i];
        while (!Arrays.asList(i,global_s).contains(null) &&
                (i <= ultima_posicion(global_s))) {
            if (!Arrays.asList(global_s,i,max).contains(null) &&
                    (global_s[i] > max)) {
                max=global_s[i];
            }
            i=i+1;
        }
    }
}