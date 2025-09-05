# Proyecto de Algoritmos Recursivos

Este proyecto contiene tres programas en Java que implementan diferentes algoritmos recursivos y de **backtracking**.

## Programas incluidos

1. **Fibonacci Recursivo**  
   Calcula números de la serie Fibonacci de manera recursiva.

2. **Subset Sum**  
   Determina si existe un subconjunto de números que sume un valor objetivo.

3. **Sudoku Interactivo**  
   Juego de Sudoku con generación aleatoria de tableros y sistema de vidas.

---

## Comandos de compilación y ejecución

### Compilar todos los programas
```bash
javac FibonacciRecursivo.java
javac SubsetSum.java
javac SudokuInteractivoAleatorio.java
```
Ejecutar Fibonacci Recursivo

```bash
Copiar código
java FibonacciRecursivo
```
Ejecutar Subset Sum
```bash
Copiar código
java SubsetSum
```
Ejecutar Sudoku Interactivo
```bash
Copiar código
java SudokuInteractivoAleatorio
```
---
## Instrucciones de uso
### Para Fibonacci Recursivo

El programa calculará y mostrará:

- El 10º número de Fibonacci 
- La serie completa hasta el número 10

### Para Subset Sum

El programa probará múltiples conjuntos de números y mostrará:
- Los conjuntos de prueba 
- Los objetivos de suma 
- Si existe o no un subconjunto que sume el objetivo

### Para Sudoku Interactivo

Formatos de entrada aceptados:

- 123 -> fila 1, columna 2, número 3 
- 1 2 3 -> fila 1, columna 2, número 3

Comandos especiales:

- ayuda -> Muestra una pista con un número correcto 
- solucion -> Revela la solución completa del sudoku 
- salir -> Termina el programa

Sistema de vidas:

- 5 vidas iniciales 
- Movimiento correcto: +1 punto 
- Movimiento incorrecto: -1 vida 
- Game Over cuando se pierden todas las vidas
---
### Requisitos del sistema

- Java JDK 8 o superior 
- Terminal o línea de comandos 
- Permisos de ejecución en el directorio
---
# Solución de problemas comunes

### Error: Java no encontrado
```bash
# Verificar instalación de Java
java -version

# Instalar Java en Ubuntu/Debian
sudo apt install openjdk-11-jdk

# Instalar Java en macOS
brew install openjdk

```
### Error: Archivos no compilan
```bash
# Verificar que todos los archivos .java estén en el mismo directorio
ls -la *.java

# Compilar individualmente si es necesario
javac FibonacciRecursivo.java
```

### Error: Permisos denegados
```bash
# Dar permisos de ejecución (Linux/macOS)
chmod +x *.java
```
---
# Personalización
### Modificar dificultad del Sudoku

Editar el parámetro en SudokuInteractivoAleatorio.java:
```bash
int[][] tablero = generarSudokuAleatorio(45); // Cambiar 45 por:
// 30: Fácil, 45: Medio, 60: Difícil
```
### Probar diferentes conjuntos en Subset Sum

Modificar los arrays en SubsetSum.java:
```bash
int[][] conjuntos = {
{3, 34, 4, 12, 5, 2},  // ← Modificar aquí
{1, 2, 3, 4, 5},
// ... agregar más conjuntos
};
```
---
Estructura de archivos
```bash
.
├── FibonacciRecursivo.java          # Algoritmo de Fibonacci
├── SubsetSum.java                   # Algoritmo de Subset Sum  
├── SudokuInteractivoAleatorio.java  # Juego de Sudoku interactivo
└── README.md                        # Este archivo de instrucciones
```
---
Notas importantes

- Todos los archivos deben estar en el mismo directorio 
- Compilar siempre antes de ejecutar 
- El Sudoku genera tableros diferentes cada vez que se ejecuta 
- Los programas son demostrativos y educativos