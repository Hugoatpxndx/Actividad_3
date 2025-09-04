public class FibonacciRecursivo {

    /**
     * Función de Fibonacci de manera recursiva
     * @param n la posición del número Fibonacci a buscar
     * @return el n-ésimo número de Fibonacci
     */
    public static long fibonacci(int n) {
        // El caso base
        if (n == 0) return 0;
        if (n == 1) return 1;

        // El caso recursivo
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("El " + n + "-ésimo número de Fibonacci es: " + fibonacci(n));

        // Mostrar la serie de tool fibonacci completa
        System.out.print("Serie de Fibonacci hasta " + n + ": ");
        for (int i = 0; i <= n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
    }
}