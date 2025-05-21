package ar.edu.utn.frc.backend.services;

import ar.edu.utn.frc.backend.entities.Capacitacion;
import ar.edu.utn.frc.backend.entities.Curso;
import ar.edu.utn.frc.backend.entities.Empleado;
import ar.edu.utn.frc.backend.repositories.CapacitacionRepository;
import ar.edu.utn.frc.backend.repositories.CursoRepository;
import ar.edu.utn.frc.backend.repositories.EmpleadoRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CapacitacionService {
    private final EmpleadoRepository empleadoRepository = new EmpleadoRepository();
    private final CapacitacionRepository capacitacionRepository = new CapacitacionRepository();
    private final CursoRepository cursoRepository = new CursoRepository();

    private final List<Empleado> empleados = new ArrayList<>();
    private final List<Capacitacion> capacitaciones = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();

    public void mostrarCapacitaciones() {
        for (Capacitacion capacitacion : capacitaciones) {
            System.out.println(capacitacion);
        }
    }

    // Paso 1: Carga de datos desde 'capacitaciones.csv'
    public void cargarDatosDesdeCSV(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine(); // Ignorar la cabecera del archivo CSV

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] datos = line.split(",");

                int capacitacionId = Integer.parseInt(datos[0]);
                int empleadoId = Integer.parseInt(datos[1]);
                String empleadoNombre = datos[2];
                int cursoId = Integer.parseInt(datos[3]);
                String cursoNombre = datos[4];
                String areaTematica = datos[5];
                LocalDate fechaCapacitacion = LocalDate.parse(datos[6]);
                int duracionHoras = Integer.parseInt(datos[7]);
                int calificacionFinal = Integer.parseInt(datos[8]);
                String estado = datos[9];


                Curso curso = createOrFindCourseByName(cursoNombre, areaTematica);
                Empleado empleado = createOrFindEmployeeByName(empleadoNombre);
                Capacitacion capacitacion = createOrFindCapacitacionByName(empleado, curso, calificacionFinal, fechaCapacitacion, duracionHoras, estado);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Empleado createOrFindEmployeeByName( String nombre) {
        for (Empleado empleado : empleados) {
            if (empleado.getNombre().equals(nombre)) {
                return empleado;
            }
        }
        Empleado nuevoEmpleado = new Empleado(nombre);
        empleados.add(nuevoEmpleado);
        return nuevoEmpleado;
    }

    private Curso createOrFindCourseByName( String nombre, String areaTematica) {
        for (Curso curso : cursos) {
            if (curso.getNombre().equals(nombre)) {
                return curso;
            }
        }
        Curso nuevoCurso = new Curso( nombre, areaTematica);
        cursos.add(nuevoCurso);
        return nuevoCurso;
    }

    private Capacitacion createOrFindCapacitacionByName(Empleado empleado, Curso curso, int calificacionFinal, LocalDate fechaCapacitacion, int duracionHoras, String estado) {
        for (Capacitacion capacitacion : capacitaciones) {
            if (capacitacion.getEmpleado().equals(empleado) && 
                capacitacion.getCurso().equals(curso) && 
                capacitacion.getFechaCapacitacion().equals(fechaCapacitacion)) {
                return capacitacion;
            }
        }
        Capacitacion nuevaCapacitacion = new Capacitacion(empleado, curso, calificacionFinal, fechaCapacitacion, duracionHoras, estado);
        capacitaciones.add(nuevaCapacitacion);
        return nuevaCapacitacion;
    }

    // Paso 2: Calcular e informar la calificación promedio de cada empleado, considerando sólo capacitaciones del área temática "Tecnología" usando stream()
    public void calcularCalificacionPromedioTecnologia() {
        Map<String, Double> calificacionesPromedio = capacitaciones.stream()
                .filter(capacitacion -> capacitacion.getCurso().getAreaTematica().equalsIgnoreCase("Tecnología"))
                .collect(Collectors.groupingBy(
                        capacitacion -> capacitacion.getEmpleado().getNombre(),
                        Collectors.averagingDouble(Capacitacion::getCalificacionFinal)
                ));

        for (Map.Entry<String, Double> entry : calificacionesPromedio.entrySet()) {
            String empleadoNombre = entry.getKey();
            double promedio = entry.getValue();
            System.out.println("Empleado: " + empleadoNombre + ", Calificación Promedio: " + promedio);
        }
    }

    // Paso 3: Crear un archivo CSV llamado reporte_areas.csv que indique el nombre del área temática y la cantidad total de capacitaciones registradas.
    public void generarReporteAreas(){
        Map<String, Long> conteoAreas = capacitaciones.stream()
                .collect(Collectors.groupingBy(capacitacion -> capacitacion.getCurso().getAreaTematica(), Collectors.counting()));

        try (PrintWriter writer = new PrintWriter("reporte_areas.csv")) {
            writer.println("Area Tematica,Cantidad de Capacitaciones");
            for (Map.Entry<String, Long> entry : conteoAreas.entrySet()) {
                String areaTematica = entry.getKey();
                long cantidad = entry.getValue();
                writer.println(areaTematica + "," + cantidad);
                System.out.println("Area Tematica: " + areaTematica + ", Cantidad de Capacitaciones: " + cantidad);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Paso 4: Mostrar en consola el top 5 de capacitaciones con mejor calificación final.
    public void mostrarTop5Capacitaciones() {
        capacitaciones.stream()
                .sorted((c1, c2) -> Double.compare(c2.getCalificacionFinal(), c1.getCalificacionFinal()))
                .limit(5)
                .forEach(capacitacion -> System.out.println(capacitacion));
    }

    public void persistirDatosEnBaseDeDatos() {
        // Persistir los datos
        empleados.forEach(empleadoRepository::save);
        cursos.forEach(cursoRepository::save);
        capacitaciones.forEach(capacitacionRepository::save);
    }
}
