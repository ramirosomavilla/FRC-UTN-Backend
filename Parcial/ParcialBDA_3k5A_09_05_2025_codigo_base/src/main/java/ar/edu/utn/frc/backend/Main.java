package ar.edu.utn.frc.backend;

import ar.edu.utn.frc.backend.services.CapacitacionService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CapacitacionService capacitacionService = new CapacitacionService();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        boolean datosCargados = false;
        boolean datosPersistidos = false;

        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("\nPaso 1: Cargando datos desde 'capacitaciones.csv'...");
                    capacitacionService.cargarDatosDesdeCSV("capacitaciones.csv");
                    capacitacionService.mostrarCapacitaciones();
                    datosCargados = true;
                    break;
                case 2:
                    if (!datosCargados) {
                        mostrarAdvertenciaDatosNoCargados();
                        break;
                    }
                    System.out.println("\nPaso 2: Calculando calificación promedio de capacitaciones del área temática 'Tecnología'...");
                    capacitacionService.calcularCalificacionPromedioTecnologia();
                    break;
                case 3:
                    if (!datosCargados) {
                        mostrarAdvertenciaDatosNoCargados();
                        break;
                    }
                    System.out.println("\nPaso 3: Generando reporte_areas.csv...");
                    capacitacionService.generarReporteAreas();
                    break;
                case 4:
                    if (!datosCargados) {
                        mostrarAdvertenciaDatosNoCargados();
                        break;
                    }
                    System.out.println("\nPaso 4: Mostrando top 5 capacitaciones con mejor calificación final...");
                    capacitacionService.mostrarTop5Capacitaciones();
                    break;
                case 5:
                    if (!datosCargados) {
                        mostrarAdvertenciaDatosNoCargados();
                        break;
                    }
                    if (datosPersistidos) {
                        System.out.println("\n⚠️ ADVERTENCIA: Los datos ya han sido cargados en la base de datos anteriormente.");
                        System.out.println("No es necesario cargarlos nuevamente.");
                        break;
                    }
                    System.out.println("\nPaso 5: Cargando datos en Base de Datos...");
                    try {
                        capacitacionService.persistirDatosEnBaseDeDatos();
                        System.out.println("Datos cargados en la base de datos. :)");
                        datosPersistidos = true;
                    } catch (Exception e) {
                        System.out.println("\n❌ Error al cargar los datos en la base de datos:");
                        System.out.println("Los datos ya existen en la base de datos.");
                        datosPersistidos = true;
                    }
                    break;
                case 6:
                    System.out.println("\nEjecutando todos los pasos en secuencia...");
                    ejecutarTodosLosPasos(capacitacionService);
                    datosCargados = true;
                    datosPersistidos = true;
                    break;
                case 0:
                    salir = true;
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Por favor, intente nuevamente.");
            }
            
            if (!salir) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ DE OPERACIONES ===");
        System.out.println("1. Cargar datos desde CSV");
        System.out.println("2. Calcular calificación promedio (Tecnología)");
        System.out.println("3. Generar reporte de áreas");
        System.out.println("4. Mostrar top 5 capacitaciones");
        System.out.println("5. Cargar datos en Base de Datos");
        System.out.println("6. Ejecutar todos los pasos");
        System.out.println("0. Salir");
        System.out.print("\nSeleccione una opción: ");
    }

    private static void mostrarAdvertenciaDatosNoCargados() {
        System.out.println("\n⚠️ ADVERTENCIA: No se han cargado los datos desde el CSV.");
        System.out.println("Por favor, seleccione la opción 1 para cargar los datos primero.");
    }

    private static int obtenerOpcion(Scanner scanner) {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void ejecutarTodosLosPasos(CapacitacionService capacitacionService) {
        System.out.println("\nPaso 1: Cargando datos desde 'capacitaciones.csv'...");
        capacitacionService.cargarDatosDesdeCSV("capacitaciones.csv");
        capacitacionService.mostrarCapacitaciones();

        System.out.println("\nPaso 2: Calculando calificación promedio de capacitaciones del área temática 'Tecnología'...");
        capacitacionService.calcularCalificacionPromedioTecnologia();

        System.out.println("\nPaso 3: Generando reporte_areas.csv...");
        capacitacionService.generarReporteAreas();

        System.out.println("\nPaso 4: Mostrando top 5 capacitaciones con mejor calificación final...");
        capacitacionService.mostrarTop5Capacitaciones();

        System.out.println("\nPaso 5: Cargando datos en Base de Datos...");
        try {
            capacitacionService.persistirDatosEnBaseDeDatos();
            System.out.println("Datos cargados en la base de datos. :)");
        } catch (Exception e) {
            System.out.println("\n❌ Error al cargar los datos en la base de datos:");
            System.out.println("Los datos ya existen en la base de datos.");
        }
    }
}