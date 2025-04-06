/**
 * Clase que representa un departamento en la organización.
 * Gestiona la información y operaciones relacionadas con un departamento y sus empleados.
 * 
 * Características:
 * - Información básica: ID y nombre del departamento
 * - Gestión de empleados asignados
 * - Generación de reportes departamentales con:
 *   * Estadísticas de empleados (permanentes/temporales)
 *   * Información de costos (salarios y bonificaciones)
 *   * Lista detallada de empleados
 * 
 * Departamentos disponibles:
 * - Recursos Humanos
 * - Tecnología de la Información
 * - Ventas y Marketing
 * - Finanzas y Contabilidad
 * - Operaciones
 * - Logística y Distribución
 * - Control de Calidad
 */
package com.compuwork.modelo;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private int id;
    private String nombre;
    private List<Empleado> empleados;

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        empleado.setDepartamento(this);
    }

    public void removerEmpleado(Empleado empleado) {
        empleados.remove(empleado);
        empleado.setDepartamento(null);
    }

    public String generarReporteDepartamento() {
        StringBuilder reporte = new StringBuilder();
        reporte.append(String.format("=== REPORTE DE DEPARTAMENTO ===\n\n"));
        reporte.append(String.format("Departamento: %s\n", nombre));
        reporte.append(String.format("ID: %d\n", id));
        reporte.append(String.format("Total de empleados: %d\n\n", empleados.size()));

        // Estadísticas
        int empleadosPermanentes = 0;
        int empleadosTemporales = 0;
        double totalSalarios = 0;
        double totalBonificaciones = 0;

        for (Empleado emp : empleados) {
            if (emp instanceof EmpleadoPermanente) {
                empleadosPermanentes++;
                EmpleadoPermanente permanent = (EmpleadoPermanente) emp;
                totalBonificaciones += permanent.getBonificacionAntiguedad() * permanent.getAniosServicio();
            } else {
                empleadosTemporales++;
                EmpleadoTemporal temporal = (EmpleadoTemporal) emp;
                totalBonificaciones += temporal.getBonificacionProyecto();
            }
            totalSalarios += emp.getSalarioBase();
        }

        // Mostrar estadísticas
        reporte.append("=== Estadísticas ===\n");
        reporte.append(String.format("Empleados permanentes: %d\n", empleadosPermanentes));
        reporte.append(String.format("Empleados temporales: %d\n", empleadosTemporales));
        reporte.append(String.format("Total salarios base: $%,.2f\n", totalSalarios));
        reporte.append(String.format("Total bonificaciones: $%,.2f\n", totalBonificaciones));
        reporte.append(String.format("Costo total mensual: $%,.2f\n\n", totalSalarios + totalBonificaciones));

        // Lista de empleados
        reporte.append("=== Lista de Empleados ===\n");
        for (Empleado emp : empleados) {
            String tipo = emp instanceof EmpleadoPermanente ? "Permanente" : "Temporal";
            reporte.append(String.format("- %s %s (%s) - Salario Total: $%,.2f\n",
                emp.getNombre(), emp.getApellido(), tipo, emp.calcularSalarioTotal()));
        }

        return reporte.toString();
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public List<Empleado> getEmpleados() { return new ArrayList<>(empleados); }

    @Override
    public String toString() {
        return String.format("%s (ID: %d) - %d empleados", nombre, id, empleados.size());
    }
}
