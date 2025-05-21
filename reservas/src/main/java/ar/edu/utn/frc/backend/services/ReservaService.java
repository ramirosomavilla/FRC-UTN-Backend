package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.entities.*;
import ar.edu.utn.frc.backend.repositories.*;

import java.io.*;
import java.util.*;

public class ReservaService {
    private ClienteRepository clienteRepository = new ClienteRepository();
    private HabitacionRepository habitacionRepository = new HabitacionRepository();
    private ReservaRepository reservaRepository = new ReservaRepository();

    private List<Cliente> clientes = new ArrayList<>();
    private List<Habitacion> habitaciones = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    // Paso 1: Cargar datos del archivo CSV
    public void cargarDatos(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); // Ignorar la cabecera del archivo CSV

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] datos = line.split(",");

                String reservaId = datos[0];
                String nombreCliente = datos[1];
                long habitacionId = Long.parseLong(datos[2]);
                String fechaInicio = datos[3];
                String fechaFin = datos[4];
                double totalPagado = Double.parseDouble(datos[5]);
                String estado = datos[6];

                Cliente cliente = buscarOCrearCliente(nombreCliente);
                Habitacion habitacion = buscarOCrearHabitacion(habitacionId);
                Reserva reserva = new Reserva(reservaId, cliente, habitacion, fechaInicio, fechaFin, totalPagado, estado);
                reservas.add(reserva);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Cliente buscarOCrearCliente(String nombre) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre)) {
                return cliente;
            }
        }
        Cliente nuevoCliente = new Cliente(nombre);
        clientes.add(nuevoCliente);
        return nuevoCliente;
    }

    private Habitacion buscarOCrearHabitacion(Long habitacionId) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getHabitacionId() == habitacionId) {
                return habitacion;
            }
        }
        Habitacion nuevaHabitacion = new Habitacion(habitacionId);
        habitaciones.add(nuevaHabitacion);
        return nuevaHabitacion;
    }

    // Paso 2: Calcular el monto total pagado por cliente y por estado específico
    public void calcularMontos() {
        Map<String, Double> montoPorCliente = new HashMap<>();
        double montoTotalPorEstado = 0;

        for (Reserva reserva : reservas) {
            // Calcular total pagado por cada cliente
            String nombreCliente = reserva.getCliente().getNombre();
            montoPorCliente.put(nombreCliente, montoPorCliente.getOrDefault(nombreCliente, 0.0) + reserva.getTotalPagado());

            // Calcular total de reservas Confirmadas
            if (reserva.getEstado().equals("Confirmada")) {
                montoTotalPorEstado += reserva.getTotalPagado();
            }
        }

        // Mostrar resultados
        for (Map.Entry<String, Double> entry : montoPorCliente.entrySet()) {
            System.out.println("Cliente: " + entry.getKey() + ", Total Pagado: " + entry.getValue());
        }
        System.out.println("Monto Total de las reservas Confirmadas: " + montoTotalPorEstado);
    }

    // Paso 3: Generar un reporte de reservas por habitación usando Scanner y PrintWriter
    public void generarReporteReservas(String filePath) {
        try (Scanner scanner = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(filePath)) {

            Map<Long, Integer> reservasPorHabitacion = new HashMap<>();

            // Contar las reservas por habitación
            for (Reserva reserva : reservas) {
                Long habitacionId = reserva.getHabitacion().getNumero();
                reservasPorHabitacion.put(habitacionId, reservasPorHabitacion.getOrDefault(habitacionId, 0) + 1);
            }

            // Escribir el encabezado en el archivo CSV
            writer.println("Habitación ID,Cantidad de Reservas");

            // Escribir el reporte de reservas en el archivo y mostrarlo en la consola
            for (Map.Entry<Long, Integer> entry : reservasPorHabitacion.entrySet()) {
                String output = entry.getKey() + "," + entry.getValue();
                System.out.println("Habitación: " + entry.getKey() + ", Cantidad de reservas: " + entry.getValue());
                writer.println(output);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Paso 4: Filtrar reservas Activas
    public void mostrarReservasActivas() {
        List<Reserva> reservasActivas = new ArrayList<>();

        // Filtrar reservas activas según el estado (consideraremos "Confirmada" y "Pendiente" como activas)
        for (Reserva reserva : reservas) {
            if (reserva.getEstado().equalsIgnoreCase("Confirmada") || reserva.getEstado().equalsIgnoreCase("Pendiente")) {
                reservasActivas.add(reserva);
            }
        }

        // Ordenar las reservas activas por el total pagado
        reservasActivas.sort(Comparator.comparing(Reserva::getTotalPagado));

        // Mostrar reservas activas por pantalla
        for (Reserva reserva : reservasActivas) {
            System.out.println("Reserva ID: " + reserva.getReservaId() + ", Cliente: " + reserva.getCliente().getNombre() + ", Fecha Inicio: " + reserva.getFechaInicio() + ", Fecha Fin: " + reserva.getFechaFin() + ", Total Pagado: " + reserva.getTotalPagado() + ", Estado: " + reserva.getEstado());
        }
    }


    // Paso 5: Guardar datos en la base de datos
    public void guardarDatosEnBaseDeDatos() {
        for (Cliente cliente : clientes) {
            clienteRepository.save(cliente);
        }
        for (Habitacion habitacion : habitaciones) {
            habitacionRepository.save(habitacion);
        }
        for (Reserva reserva : reservas) {
            reservaRepository.save(reserva);
        }
    }
}
