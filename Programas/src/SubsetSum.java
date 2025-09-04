public class SubsetSum {

    /**
     * Aqí se determina si existe un subconjunto que sume el valor objetivo
     * @param conjunto Array de enteros
     * @param objetivo Valor objetivo de la suma
     * @return true si existe un subconjunto, false en caso contrario
     */
    public static boolean existeSubconjuntoSuma(int[] conjunto, int objetivo) {
        return existeSubconjuntoRecursivo(conjunto, objetivo, 0, 0);
    }

    /**
     * Función recursiva auxiliar
     */
    private static boolean existeSubconjuntoRecursivo(int[] conjunto, int objetivo, int indice, int sumaActual) {
        // Encontramos la suma objetiva
        if (sumaActual == objetivo) {
            return true;
        }

        // Nos pasamos del objetivo o terminamos el array
        if (sumaActual > objetivo || indice >= conjunto.length) {
            return false;
        }

        // Probamos incluyendo el elemento actual
        boolean incluir = existeSubconjuntoRecursivo(conjunto, objetivo, indice + 1, sumaActual + conjunto[indice]);

        // Probamos sin incluir el elemento actual
        boolean excluir = existeSubconjuntoRecursivo(conjunto, objetivo, indice + 1, sumaActual);
        return incluir || excluir;
    }

    public static void main(String[] args) {
        int[] conjunto = {1, 3, 5, 9, 7};
        int objetivo = 8;

        System.out.print("Conjunto: ");
        for (int num : conjunto) {
            System.out.print(num + " ");
        }
        System.out.println("\nObjetivo: " + objetivo);

        boolean resultado = existeSubconjuntoSuma(conjunto, objetivo);
        System.out.println("¿Existe un subconjunto que sume " + objetivo + "? " + resultado);

        // Probar con otro objetivo
        objetivo = 30;
        resultado = existeSubconjuntoSuma(conjunto, objetivo);
        System.out.println("¿Existe un subconjunto que sume " + objetivo + "? " + resultado);
    }
}