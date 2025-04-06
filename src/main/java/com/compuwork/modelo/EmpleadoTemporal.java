package com.compuwork.modelo;

import java.time.LocalDate;

/**
 * Representa un empleado temporal en la organización.
 * Extiende la clase base Empleado con características específicas para empleados por contrato.
 * 
 * Características específicas:
 * - Fecha de fin de contrato
 * - Bonificación por proyecto
 * - Cálculo de salario total incluyendo bonificación por proyecto
 * - Validación de fechas de contrato
 */
public class EmpleadoTemporal extends Empleado {
    private LocalDate fechaFinContrato;
    private double bonificacionProyecto;

    public EmpleadoTemporal(int id, String nombre, String apellido, String dni, 
                           LocalDate fechaContratacion, double salarioBase, 
                           LocalDate fechaFinContrato, double bonificacionProyecto) {
        super(id, nombre, apellido, dni, fechaContratacion, salarioBase);
        this.fechaFinContrato = fechaFinContrato;
        this.bonificacionProyecto = bonificacionProyecto;
    }

    @Override
    public double calcularSalarioTotal() {
        return getSalarioBase() + bonificacionProyecto;
    }

    // Getters y setters adicionales
    public LocalDate getFechaFinContrato() { return fechaFinContrato; }
    public void setFechaFinContrato(LocalDate fechaFinContrato) { 
        this.fechaFinContrato = fechaFinContrato; 
    }
    
    public double getBonificacionProyecto() { return bonificacionProyecto; }
    public void setBonificacionProyecto(double bonificacionProyecto) { 
        this.bonificacionProyecto = bonificacionProyecto; 
    }
}
