public class Test {
    private static int ultima_posicion (int[] entrada) {
        return entrada[entrada.length-1];
    }
    private static boolean ultima_posicion (boolean[] entrada) {
        return entrada[entrada.length-1];
    }
    private static boolean vacia (boolean[] entrada) {
        return entrada.length == 0;
    }
    private static boolean vacia (int[] entrada) {
        return entrada.length == 0;
    }
    private static void mostrar (boolean[] entrada) {
        System.out.println(entrada);
    }
    private static void mostrar (int[] entrada) {
        System.out.println(entrada);
    }
    private static int i,j,max2,max,max3,max4;
    private static boolean as,b;
    private static int[] s;

    private static int test2 (int a) {
        int kk;
        int[] pr;
        boolean[] pr2;
        pr=new int[]{1,2,4,5};
        pr2=new boolean[]{true,true,true,false};
        kk=1+ultima_posicion(pr);
        if (vacia(new int[]{1,2}) == true) {
            kk=kk+1;
        } else {
            kk=kk+25;
        }
        return kk;
    }

    public static void main(String[] args) throws Exception {
        max=1+26*7+7+9*7+32+5;
        j=2+2;
        j=test2(1);
        return;
    }
}