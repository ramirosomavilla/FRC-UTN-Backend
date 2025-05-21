package ar.edu.utn.frc.backend;

import ar.edu.utn.frc.backend.services.PartidasService;

public class Main {
    public static void main(String[] args) {
        PartidasService partidasService = new PartidasService();


        // Punto 1: Cargar datos de partidas
        partidasService.cargarDatos("partidas.csv");
        System.out.println("Datos de partidas cargados.");
        partidasService.mostrarDatos();

        // Punto 2: Calcular e informar la puntuación promedio por jugador considerando solo partidas con estado "Completada"
        System.out.println("Puntuación promedio por jugador:");
        partidasService.calcularPuntuacionPromedioPorJugador();

        // Punto 3: Reporte por género: Crear un archivo CSV llamado reporte_genero.csv que indique el género del juego y la cantidad total de partidas jugadas.
        System.out.println("Generando reporte por género...");
        partidasService.generarReportePorGenero();

        // Punto 4: Mejores partidas: Mostrar en consola el top 10 de las partidas con mejores puntuaciones.
        System.out.println("Top 10 de las partidas con mejores puntuaciones:");
        partidasService.mostrarTop10PartidasMejoresPuntuaciones();

        // Punto 5: Carga en Base de Datos: Persistir toda la información en memoria en una base de
        //datos relacional usando JPA.
        System.out.println("Guardando datos en la base de datos...");
        partidasService.guardarDatosEnBaseDeDatos();
    }
}