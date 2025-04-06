package com.compuwork.modelo;

import java.time.LocalDate;

/**
 * Clase base para representar un empleado en el sistema.
 * Contiene los atributos y métodos comunes a todos los tipos de empleados.
 * 
 * Características:
 * - Información personal: nombre, apellido, CC
 * - Información laboral: fecha contratación, salario base
 * - Asociación con departamento
 * - Cálculo de salario total
 * - Generación de reportes de desempeño
 */
public abstract class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private String cc;
    private LocalDate fechaContratacion;
    private double salarioBase;
    private Departamento departamento;

    public Empleado(int id, String nombre, String apellido, String cc, LocalDate fechaContratacion, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cc = cc;
        this.fechaContratacion = fechaContratacion;
        this.salarioBase = salarioBase;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getCc() { return cc; }
    public void setCc(String cc) { this.cc = cc; }
    
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    
    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }
    
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    // Método abstracto para calcular el salario total
    public abstract double calcularSalarioTotal();

    // Método para generar reporte de desempeño
    public String generarReporteDesempeno() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE EMPLEADO ===\n\n");
        reporte.append(String.format("ID: %d\n", id));
        reporte.append(String.format("Nombre completo: %s %s\n", nombre, apellido));
        reporte.append(String.format("CC: %s\n", cc));
        reporte.append(String.format("Departamento: %s\n", 
            departamento != null ? departamento.getNombre() : "Sin asignar"));
        reporte.append(String.format("Fecha de contratación: %s\n", 
            fechaContratacion.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        reporte.append(String.format("Salario base: $%,.2f\n", salarioBase));
        reporte.append(String.format("Salario total: $%,.2f\n", calcularSalarioTotal()));
        reporte.append(String.format("Tipo de empleado: %s\n", 
            this instanceof EmpleadoPermanente ? "Permanente" : "Temporal"));
        
        if (this instanceof EmpleadoPermanente) {
            EmpleadoPermanente emp = (EmpleadoPermanente) this;
            reporte.append(String.format("Años de servicio: %d\n", emp.getAniosServicio()));
            reporte.append(String.format("Bonificación por antigüedad: $%,.2f\n", 
                emp.getBonificacionAntiguedad()));
        } else if (this instanceof EmpleadoTemporal) {
            EmpleadoTemporal emp = (EmpleadoTemporal) this;
            reporte.append(String.format("Fecha fin contrato: %s\n", 
                emp.getFechaFinContrato().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            reporte.append(String.format("Bonificación por proyecto: $%,.2f\n", 
                emp.getBonificacionProyecto()));
        }
        
        return reporte.toString();
    }

    @Override
    public String toString() {
        String tipo = this instanceof EmpleadoPermanente ? "Permanente" : "Temporal";
        String salarioStr = String.format("$%,.0f", calcularSalarioTotal());
        return String.format("%s %s - %s - %s - %s", 
            nombre, apellido, tipo, 
            departamento != null ? departamento.getNombre() : "Sin asignar",
            salarioStr);
    }
}
