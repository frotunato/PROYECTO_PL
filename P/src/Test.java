public class Test {
    private static Integer llk;
    private static int ultima_posicion(int[] entrada) {
        return entrada[entrada.length - 1];
    }

    private static boolean ultima_posicion(boolean[] entrada) {
        return entrada[entrada.length - 1];
    }

    private static boolean vacia(boolean[] entrada) {
        int t;
        t = llk * 5;
        return entrada.length == 0;
    }

    private static boolean vacia(int[] entrada) {
        return entrada.length == 0;
    }

    private static void mostrar(boolean[] entrada) {
        System.out.println(entrada);
    }

    private static void mostrar(int[] entrada) {
        System.out.println(entrada);
    }

    private static int global_i, global_j, global_k;
    private static int[] global_s;

    private static void test2(int a) {
        int acum, limite;
        acum = a + 25;
        limite = 10;
        while (limite < 10) {
            limite = limite - 1;
            acum = a * acum;
        }
        global_i = a * acum;
    }

    private static int test(int a, int b) {
        int c;
        int global_i2;
        c = a + b;
        return c + 1;
    }

    public static void main(String[] args) throws Exception {
        test2(25);
        global_i = test(1, 2);
        vacia(new boolean[]{true, false});
    }
}