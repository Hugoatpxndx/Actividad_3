import java.util.Scanner;
import java.util.Random;

public class SudokuSolver {
    private static final int TAMANO = 9;
    private static final int SUBCUADRO = 3;
    private static final int VACIO = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static int vidas = 5;
    private static int movimientosCorrectos = 0;
    private static int totalCeldasVacias = 0;

    /**
     * Método principal que inicia el juego de Sudoku interactivo
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("SUDOKU");
        System.out.println();
        System.out.println("NOTA: Puedes ingresar los números juntos (ej: 123) o separados (ej: 1 2 3)");
        System.out.println();

        // Generar sudoku aleatorio
        int[][] tablero = generarSudokuAleatorio(45); // 45 celdas vacías
        totalCeldasVacias = contarCeldasVacias(tablero);
        int[][] solucion = obtenerSolucion(tablero);

        System.out.println("Resuelve este Sudoku. Tienes " + vidas + " vidas.");
        System.out.println("Celdas por completar: " + totalCeldasVacias);
        imprimirTablero(tablero);

        while (vidas > 0 && movimientosCorrectos < totalCeldasVacias) {
            System.out.println("Vidas restantes: " + vidas);
            System.out.println("Movimientos correctos: " + movimientosCorrectos + "/" + totalCeldasVacias);
            System.out.println();
            System.out.println("Opciones:");
            System.out.println("- Ingresa fila columna numero (ej: 123 o 1 2 3)");
            System.out.println("- Escribe 'ayuda' para una pista");
            System.out.println("- Escribe 'solucion' para ver la respuesta completa");
            System.out.println("- Escribe 'salir' para terminar");
            System.out.print("Tu movimiento: ");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("salir")) {
                System.out.println("Juego terminado. ¡Hasta luego!");
                break;
            }

            if (input.equalsIgnoreCase("solucion")) {
                mostrarSolucion(solucion);
                break;
            }

            if (input.equalsIgnoreCase("ayuda")) {
                darAyuda(tablero, solucion);
                continue;
            }

            // Procesar input
            String[] partes;
            if (input.length() == 3 && input.matches("\\d{3}")) {
                // Formato: "123"
                partes = new String[3];
                partes[0] = input.substring(0, 1);
                partes[1] = input.substring(1, 2);
                partes[2] = input.substring(2, 3);
            } else {
                // Formato: "1 2 3"
                partes = input.split(" ");
            }

            if (partes.length != 3) {
                System.out.println("Error: Debes ingresar 3 numeros (123 o 1 2 3)");
                continue;
            }

            try {
                int fila = Integer.parseInt(partes[0]) - 1;
                int columna = Integer.parseInt(partes[1]) - 1;
                int numero = Integer.parseInt(partes[2]);

                if (fila < 0 || fila >= TAMANO || columna < 0 || columna >= TAMANO) {
                    System.out.println("Error: Fila y columna deben ser entre 1 y 9");
                    continue;
                }

                if (numero < 1 || numero > 9) {
                    System.out.println("Error: El numero debe ser entre 1 y 9");
                    continue;
                }

                if (tablero[fila][columna] != VACIO) {
                    System.out.println("Error: Esa celda ya tiene un numero");
                    continue;
                }

                // Verificar si el movimiento es válido usando backtracking
                if (esMovimientoValidoBacktracking(tablero, fila, columna, numero, solucion)) {
                    tablero[fila][columna] = numero;
                    movimientosCorrectos++;
                    System.out.println("¡Correcto! Numero colocado exitosamente.");
                    imprimirTablero(tablero);

                    if (movimientosCorrectos == totalCeldasVacias) {
                        System.out.println("¡FELICITACIONES! Has completado el Sudoku!");
                        System.out.println("Movimientos correctos: " + movimientosCorrectos);
                        System.out.println("Vidas restantes: " + vidas);
                    }
                } else {
                    vidas--;
                    System.out.println("Incorrecto. El numero " + numero + " no puede ir en esa posicion.");
                    System.out.println("Pierdes 1 vida. Vidas restantes: " + vidas);

                    if (vidas == 0) {
                        System.out.println("¡GAME OVER! Te has quedado sin vidas.");
                        System.out.println("La solucion completa era:");
                        mostrarSolucion(solucion);
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa solamente numeros validos (ej: 123 o 1 2 3)");
            } catch (Exception e) {
                System.out.println("Error: Formato invalido. Usa: 123 o 1 2 3");
            }
        }

        scanner.close();
    }

    /**
     * Determina si un movimiento es válido comparando con la solución generada por backtracking
     * @param tablero Matriz del tablero actual
     * @param fila Fila donde se quiere colocar el número
     * @param columna Columna donde se quiere colocar el número
     * @param numero Número que se quiere colocar
     * @param solucion Matriz con la solución completa
     * @return true si el movimiento es correcto, false en caso contrario
     */
    private static boolean esMovimientoValidoBacktracking(int[][] tablero, int fila, int columna,
                                                          int numero, int[][] solucion) {
        return solucion[fila][columna] == numero;
    }

    /**
     * Proporciona una pista al usuario mostrando un número correcto de una celda vacía aleatoria
     * @param tablero Matriz del tablero actual
     * @param solucion Matriz con la solución completa
     */
    private static void darAyuda(int[][] tablero, int[][] solucion) {
        // Encontrar una celda vacía aleatoria para ayudar
        int intentos = 0;
        while (intentos < 100) { // Límite de intentos para evitar bucle infinito
            int fila = random.nextInt(TAMANO);
            int columna = random.nextInt(TAMANO);
            if (tablero[fila][columna] == VACIO) {
                System.out.println("Pista: En la posicion [" + (fila+1) + "," + (columna+1) + "] va el numero: " + solucion[fila][columna]);
                return;
            }
            intentos++;
        }
        System.out.println("No se pudo encontrar una celda vacia para dar pista");
    }

    /**
     * Genera un sudoku aleatorio con el número especificado de celdas vacías
     * @param celdasVacias Número de celdas que deben estar vacías
     * @return Matriz 9x9 representando el tablero de Sudoku
     */
    private static int[][] generarSudokuAleatorio(int celdasVacias) {
        // Primero generar un sudoku completo válido usando backtracking
        int[][] tableroCompleto = new int[TAMANO][TAMANO];
        generarSudokuCompleto(tableroCompleto);

        // Crear una copia y quitar números
        int[][] puzzle = new int[TAMANO][TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            System.arraycopy(tableroCompleto[i], 0, puzzle[i], 0, TAMANO);
        }

        // Quitar números aleatoriamente
        int quitadas = 0;
        while (quitadas < celdasVacias) {
            int fila = random.nextInt(TAMANO);
            int columna = random.nextInt(TAMANO);
            if (puzzle[fila][columna] != VACIO) {
                puzzle[fila][columna] = VACIO;
                quitadas++;
            }
        }

        return puzzle;
    }

    /**
     * Genera un sudoku completo válido usando el algoritmo de backtracking
     * @param tablero Matriz donde se generará el sudoku completo
     */
    private static void generarSudokuCompleto(int[][] tablero) {
        resolverSudoku(tablero);
    }

    /**
     * Resuelve un tablero de Sudoku usando el algoritmo de backtracking recursivo
     * @param tablero Matriz del tablero a resolver
     * @return true si se encontró una solución, false en caso contrario
     */
    private static boolean resolverSudoku(int[][] tablero) {
        for (int fila = 0; fila < TAMANO; fila++) {
            for (int columna = 0; columna < TAMANO; columna++) {
                if (tablero[fila][columna] == VACIO) {
                    // Probar números en orden aleatorio para variedad
                    int[] numeros = generarNumerosAleatorios();
                    for (int numero : numeros) {
                        if (esMovimientoValido(tablero, fila, columna, numero)) {
                            tablero[fila][columna] = numero;
                            if (resolverSudoku(tablero)) {
                                return true;
                            }
                            tablero[fila][columna] = VACIO; // Backtracking
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Genera un array con los números del 1 al 9 en orden aleatorio
     * @return Array de enteros mezclados
     */
    private static int[] generarNumerosAleatorios() {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // Mezclar el array para variedad en la generación
        for (int i = numeros.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = numeros[index];
            numeros[index] = numeros[i];
            numeros[i] = temp;
        }
        return numeros;
    }

    /**
     * Verifica si un número puede colocarse en una posición específica del tablero
     * @param tablero Matriz del tablero
     * @param fila Fila donde se quiere colocar el número
     * @param columna Columna donde se quiere colocar el número
     * @param numero Número que se quiere colocar
     * @return true si el movimiento es válido, false en caso contrario
     */
    private static boolean esMovimientoValido(int[][] tablero, int fila, int columna, int numero) {
        // Verificar fila
        for (int c = 0; c < TAMANO; c++) {
            if (tablero[fila][c] == numero) return false;
        }

        // Verificar columna
        for (int f = 0; f < TAMANO; f++) {
            if (tablero[f][columna] == numero) return false;
        }

        // Verificar subcuadro 3x3
        int inicioFila = fila - fila % SUBCUADRO;
        int inicioColumna = columna - columna % SUBCUADRO;
        for (int f = inicioFila; f < inicioFila + SUBCUADRO; f++) {
            for (int c = inicioColumna; c < inicioColumna + SUBCUADRO; c++) {
                if (tablero[f][c] == numero) return false;
            }
        }

        return true;
    }

    /**
     * Cuenta el número de celdas vacías en el tablero
     * @param tablero Matriz del tablero
     * @return Número de celdas vacías
     */
    private static int contarCeldasVacias(int[][] tablero) {
        int count = 0;
        for (int f = 0; f < TAMANO; f++) {
            for (int c = 0; c < TAMANO; c++) {
                if (tablero[f][c] == VACIO) count++;
            }
        }
        return count;
    }

    /**
     * Obtiene la solución completa de un tablero de Sudoku
     * @param tablero Matriz del tablero a resolver
     * @return Matriz con la solución completa
     */
    private static int[][] obtenerSolucion(int[][] tablero) {
        int[][] copia = new int[TAMANO][TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            System.arraycopy(tablero[i], 0, copia[i], 0, TAMANO);
        }
        resolverSudoku(copia);
        return copia;
    }

    /**
     * Muestra la solución completa del Sudoku
     * @param solucion Matriz con la solución completa
     */
    private static void mostrarSolucion(int[][] solucion) {
        System.out.println("SOLUCION COMPLETA:");
        imprimirTablero(solucion);
    }

    /**
     * Imprime el tablero de Sudoku en formato legible
     * @param tablero Matriz del tablero a imprimir
     */
    public static void imprimirTablero(int[][] tablero) {
        System.out.println();
        System.out.println("    1 2 3   4 5 6   7 8 9");
        System.out.println("  +-------+-------+-------+");

        for (int fila = 0; fila < TAMANO; fila++) {
            System.out.print((fila + 1) + " | ");
            for (int columna = 0; columna < TAMANO; columna++) {
                if (columna % SUBCUADRO == 0 && columna != 0) {
                    System.out.print("| ");
                }
                System.out.print(tablero[fila][columna] == VACIO ? "." : tablero[fila][columna]);
                System.out.print(" ");
            }
            System.out.println("|");

            if ((fila + 1) % SUBCUADRO == 0 && fila != TAMANO - 1) {
                System.out.println("  +-------+-------+-------+");
            }
        }
        System.out.println("  +-------+-------+-------+");
        System.out.println();
    }
}