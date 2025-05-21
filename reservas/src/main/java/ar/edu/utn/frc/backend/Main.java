package ar.edu.utn.frc.backend;
import ar.edu.utn.frc.backend.services.*;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia del servicio de reservas
        ReservaService reservaService = new ReservaService();

        // Paso 1: Cargar datos del archivo CSV
        System.out.println("Cargando datos desde archivo CSV...");
        reservaService.cargarDatos("reservas.csv");

        // Paso 2: Calcular el monto total pagado por cliente
        System.out.println("Calculando montos...");
        reservaService.calcularMontos();

        // Paso 3: Generar un reporte de reservas por habitación
        System.out.println("Generando reporte de reservas por habitación...");
        reservaService.generarReporteReservas("reporte_reservas.csv");

        // Paso 4: Filtrar reservas por estado
        System.out.println("Filtrando reservas activas...");
        reservaService.mostrarReservasActivas();

        // Paso 5: Guardar datos en la base de datos
        System.out.println("Guardando datos en la base de datos...");
        reservaService.guardarDatosEnBaseDeDatos();
    }
}