package com.compuwork.modelo;

import java.time.LocalDate;

/**
 * Representa un empleado permanente en la organización.
 * Extiende la clase base Empleado con características específicas para empleados de tiempo completo.
 * 
 * Características específicas:
 * - Bonificación por antigüedad
 * - Cálculo de años de servicio
 * - Cálculo de salario total incluyendo bonificación por antigüedad
 */
public class EmpleadoPermanente extends Empleado {
    private double bonificacionAntiguedad;
    private int aniosServicio;

    public EmpleadoPermanente(int id, String nombre, String apellido, String dni, 
                            LocalDate fechaContratacion, double salarioBase, 
                            double bonificacionAntiguedad) {
        super(id, nombre, apellido, dni, fechaContratacion, salarioBase);
        this.bonificacionAntiguedad = bonificacionAntiguedad;
        this.aniosServicio = calcularAniosServicio();
    }

    private int calcularAniosServicio() {
        return 2023 - getFechaContratacion().getYear();
    }

    @Override
    public double calcularSalarioTotal() {
        return getSalarioBase() + (bonificacionAntiguedad * aniosServicio);
    }

    // Getters y setters adicionales
    public double getBonificacionAntiguedad() { return bonificacionAntiguedad; }
    public void setBonificacionAntiguedad(double bonificacionAntiguedad) { 
        this.bonificacionAntiguedad = bonificacionAntiguedad; 
    }
    
    public int getAniosServicio() { return aniosServicio; }
}
