import java.util.Arrays;
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

    private static Object[] busca_max (Integer[] lista,Integer tope) {
        Integer max;
        Integer max2;
        Integer i,tmp;
        tmp=0;
        i=0;
        if (!Arrays.asList().contains(null) && (null == new Integer[]{1,3})) {
            mostrar(new Integer[]{1,2,3});
            i=1;
        } else {
            mostrar(new Integer[]{1,1,1});
            i=2;
        }
        while (!Arrays.asList(i,tope).contains(null) && (i < tope)) {
            if (!Arrays.asList(lista,i,tmp).contains(null) && (lista[i] > tmp)) {
                tmp=lista[i];
            }
            i=i+1;
        }
        return new Object[]{5,6};
    }
    private static Object[] salida_busca_max;
    private static Integer test3 (Integer a) {
        Integer c;
        Integer global_i2,c2;
        c=0;
        if (!Arrays.asList().contains(null) && (new Integer[]{1,3} == new Integer[]{1,3})) {
            mostrar(new Integer[]{1,2,3});
            c=11;
        } else {
            mostrar(new Integer[]{1,1,1,1});
            c=2;
        }
        if (!Arrays.asList(a).contains(null) && (a > 10)) {
            if (!Arrays.asList(a).contains(null) && (a > 15)) {
                return 15;
            } else {
                return 11;
            }
        } else {
            c=25;
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        a=100+255;
        global_s=new Integer[]{6,18};
        a=test3(test3(23)+1);
    }
}