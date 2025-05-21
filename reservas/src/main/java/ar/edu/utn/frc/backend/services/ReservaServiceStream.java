package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.entities.*;
import ar.edu.utn.frc.backend.repositories.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class ReservaServiceStream {
    private ClienteRepository clienteRepository = new ClienteRepository();
    private HabitacionRepository habitacionRepository = new HabitacionRepository();
    private ReservaRepository reservaRepository = new ReservaRepository();

    private List<Cliente> clientes = new ArrayList<>();
    private List<Habitacion> habitaciones = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    // Paso 1: Cargar datos del archivo CSV usando Streams
    public void cargarDatos(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            reservas = lines.skip(1) // Salta la cabecera del archivo CSV
                    .map(line -> line.split(",")) // Divide cada línea en un arreglo de datos
                    .map(datos -> {
                        Cliente cliente = buscarOCrearCliente(datos[1]);
                        Habitacion habitacion = buscarOCrearHabitacion(Long.parseLong(datos[2]));
                        return new Reserva(
                                datos[0],
                                cliente,
                                habitacion,
                                datos[3],
                                datos[4],
                                Double.parseDouble(datos[5]),
                                datos[6]
                        );
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Busca un cliente existente por nombre o crea uno nuevo si no existe
    private Cliente buscarOCrearCliente(String nombre) {
        return clientes.stream()
                .filter(c -> c.getNombre().equals(nombre))
                .findFirst()
                .orElseGet(() -> {
                    Cliente nuevoCliente = new Cliente(nombre);
                    clientes.add(nuevoCliente);
                    return nuevoCliente;
                });
    }

    // Busca una habitación existente por ID o crea una nueva si no existe
    private Habitacion buscarOCrearHabitacion(Long habitacionId) {
        return habitaciones.stream()
                .filter(h -> Objects.equals(h.getHabitacionId(), habitacionId))
                .findFirst()
                .orElseGet(() -> {
                    Habitacion nuevaHabitacion = new Habitacion(habitacionId);
                    habitaciones.add(nuevaHabitacion);
                    return nuevaHabitacion;
                });
    }

    // Paso 2: Calcular el monto total pagado por cliente y por estado específico
    public void calcularMontos() {
        // Calcula el monto total pagado agrupado por cliente
        Map<String, Double> montoPorCliente = reservas.stream()
                .collect(Collectors.groupingBy(r -> r.getCliente().getNombre(),
                        Collectors.summingDouble(Reserva::getTotalPagado)));

        // Calcula el monto total para reservas confirmadas
        double montoTotalPorEstado = reservas.stream()
                .filter(r -> "Confirmada".equalsIgnoreCase(r.getEstado()))
                .mapToDouble(Reserva::getTotalPagado)
                .sum();

        montoPorCliente.forEach((cliente, total) ->
                System.out.println("Cliente: " + cliente + ", Total Pagado: " + total));

        System.out.println("Monto Total de las reservas Confirmadas: " + montoTotalPorEstado);
    }

    // Paso 3: Generar un reporte de reservas por habitación
    public void generarReporteReservas(String filePath) {
        // Agrupa y cuenta la cantidad de reservas por habitación
        Map<Long, Long> reservasPorHabitacion = reservas.stream()
                .collect(Collectors.groupingBy(r -> r.getHabitacion().getNumero(), Collectors.counting()));

        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("Habitación ID,Cantidad de Reservas");

            reservasPorHabitacion.forEach((habitacionId, cantidad) -> {
                String output = habitacionId + "," + cantidad;
                System.out.println("Habitación: " + habitacionId + ", Cantidad de reservas: " + cantidad);
                writer.println(output);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Paso 4: Filtrar reservas Activas y ordenarlas por total pagado
    public void mostrarReservasActivas() {
        reservas.stream()
                .filter(r -> r.getEstado().equalsIgnoreCase("Confirmada") || r.getEstado().equalsIgnoreCase("Pendiente"))
                .sorted(Comparator.comparingDouble(Reserva::getTotalPagado))
                .forEach(r -> System.out.println("Reserva ID: " + r.getReservaId() +
                        ", Cliente: " + r.getCliente().getNombre() +
                        ", Fecha Inicio: " + r.getFechaInicio() +
                        ", Fecha Fin: " + r.getFechaFin() +
                        ", Total Pagado: " + r.getTotalPagado() +
                        ", Estado: " + r.getEstado()));
    }

    // Paso 5: Guardar datos en la base de datos
    public void guardarDatosEnBaseDeDatos() {
        clientes.forEach(clienteRepository::save);
        habitaciones.forEach(habitacionRepository::save);
        reservas.forEach(reservaRepository::save);
    }
}