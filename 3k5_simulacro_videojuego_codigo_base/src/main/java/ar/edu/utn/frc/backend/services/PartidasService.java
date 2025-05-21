package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.entities.Juego;
import ar.edu.utn.frc.backend.entities.Jugador;
import ar.edu.utn.frc.backend.entities.Partida;
import ar.edu.utn.frc.backend.repositories.JuegosRepository;
import ar.edu.utn.frc.backend.repositories.JugadoresRepository;
import ar.edu.utn.frc.backend.repositories.PartidasRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PartidasService {
    private JuegosRepository juegosRepository = new JuegosRepository();
    private JugadoresRepository jugadoresRepository = new JugadoresRepository();
    private PartidasRepository partidasRepository = new PartidasRepository();

    // Paso 1: Cargar datos del archivo CSV
    private List<Partida> partidas = new ArrayList<>();
    private List<Jugador> jugadores = new ArrayList<>();
    private List<Juego> juegos = new ArrayList<>();

    public void cargarDatos(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); // Ignorar la cabecera del archivo CSV

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] datos = line.split(",");

                Long partidaId = Long.parseLong(datos[0]);
                Long jugadorId = Long.parseLong(datos[1]);
                String jugadorNombre = datos[2];
                Long juegoId = Long.parseLong(datos[3]);
                String juegoNombre = datos[4];
                String genero = datos[5];
                LocalDate fechaPartida = LocalDate.parse(datos[6]);
                Integer duracionMinutos = Integer.parseInt(datos[7]);
                Integer puntuacion = Integer.parseInt(datos[8]);
                String estado = datos[9];

                Partida partida = buscarOCrearPartida(
                        partidaId,
                        jugadorId,
                        jugadorNombre,
                        juegoId,
                        juegoNombre,
                        genero,
                        fechaPartida,
                        duracionMinutos,
                        puntuacion,
                        estado
                );

                Jugador jugador = buscarOCrearJugador(jugadorId, jugadorNombre);

                Juego juego = buscarOCrearJuego(
                        juegoId,
                        juegoNombre,
                        genero
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void mostrarDatos() {
        for (Partida partida : partidas) {
            System.out.println(partida);
        }
        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
        for (Juego juego : juegos) {
            System.out.println(juego);
        }
    }

    public Partida buscarOCrearPartida(Long partidaId, Long jugadorId, String jugadorNombre, Long juegoId, String juegoNombre, String genero,
                                       LocalDate fechaPartida, Integer duracionMinutos, Integer puntuacion, String estado) {
        for (Partida partida : partidas) {
            if (partida.getPartidaId() == partidaId) {
                return partida;
            }
        }
        Partida nuevaPartida = new Partida(
                partidaId,
                jugadorId,
                jugadorNombre,
                juegoId,
                juegoNombre,
                genero,
                fechaPartida,
                duracionMinutos,
                puntuacion,
                estado
        );
        partidas.add(nuevaPartida);
        return nuevaPartida;
    }

    public Juego buscarOCrearJuego(Long juegoId, String juegoNombre, String genero) {
        for (Juego juego : juegos) {
            if (juego.getNombre().equals(juegoNombre)) {
                return juego;
            }
        }
        Juego nuevoJuego = new Juego(juegoId, juegoNombre, genero);
        juegos.add(nuevoJuego);
        return nuevoJuego;
    }

    public Jugador buscarOCrearJugador(long jugadorId, String jugadorNombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equals(jugadorNombre)) {
                return jugador;
            }
        }
        Jugador nuevoJugador = new Jugador(jugadorId, jugadorNombre);
        jugadores.add(nuevoJugador);
        return nuevoJugador;
    }

    // Punto 2: Calcular e informar la puntuación promedio por jugador considerando solo partidas con estado "Completada" usando Stream
    public void calcularPuntuacionPromedioPorJugador() {
        Map<String, Double> puntuacionPromedioPorJugador = partidas.stream()
                .filter(partida -> partida.getEstado().equals("Completada"))
                .collect(
                        Collectors.groupingBy(
                                Partida::getJugadorNombre,
                                Collectors.averagingInt(Partida::getPuntuacion)
                        )
                );
        puntuacionPromedioPorJugador.forEach((jugador, puntuacionPromedio) ->
                System.out.println("Jugador: " + jugador + ", Puntuación Promedio: " + puntuacionPromedio)
        );
    }

    // Punto 3: Reporte por género: Crear un archivo CSV llamado reporte_genero.csv que indique el género del juego y la cantidad total de partidas jugadas.
    public void generarReportePorGenero(){
        Map<String, Long> partidasPorGenero = partidas.stream()
                .collect(Collectors.groupingBy(Partida::getGenero, Collectors.counting()));

        StringBuilder reporte = new StringBuilder("Género,Cantidad de Partidas\n");
        partidasPorGenero.forEach((genero, cantidad) -> {
            reporte.append(genero).append(",").append(cantidad).append("\n");
        });

        // Guardar el reporte en un archivo CSV
        try (PrintWriter writer = new PrintWriter("reporte_genero.csv")) {
            writer.write(reporte.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public void mostrarTop10PartidasMejoresPuntuaciones(){
        List<Partida> top10Partidas = partidas.stream()
                .filter(partida -> partida.getEstado().equals("Completada"))
                .sorted((p1, p2) -> Integer.compare(p2.getPuntuacion(), p1.getPuntuacion()))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 de las partidas con mejores puntuaciones:");
        for (Partida partida : top10Partidas) {
            System.out.println(partida);
        }
    }

    public void guardarDatosEnBaseDeDatos() {
        // Guardar jugadores
        for (Jugador jugador : jugadores) {
            jugadoresRepository.save(jugador);
        }

        // Guardar juegos
        for (Juego juego : juegos) {
            juegosRepository.save(juego);
        }

        // Guardar partidas
        for (Partida partida : partidas) {
            partidasRepository.save(partida);
        }
    }
}
