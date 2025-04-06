package com.compuwork.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {
    private EmpleadoPermanente empleadoPermanente;
    private EmpleadoTemporal empleadoTemporal;
    private Departamento departamento;

    @BeforeEach
    void setUp() {
        empleadoPermanente = new EmpleadoPermanente(1, "Juan", "Pérez", "12345678",
            LocalDate.of(2020, 1, 1), 50000, 5000);
        
        empleadoTemporal = new EmpleadoTemporal(2, "María", "García", "87654321",
            LocalDate.of(2023, 6, 1), 45000, 
            LocalDate.of(2024, 6, 1), 3000);
        
        departamento = new Departamento(1, "Recursos Humanos");
    }

    @Test
    void testCalcularSalarioEmpleadoPermanente() {
        // El empleado tiene 3 años de servicio (2020-2023), por lo que debe recibir
        // 3 veces la bonificación por antigüedad
        double salarioEsperado = 50000 + (5000 * 3);
        assertEquals(salarioEsperado, empleadoPermanente.calcularSalarioTotal(), 
            "El salario del empleado permanente no se calculó correctamente");
    }

    @Test
    void testCalcularSalarioEmpleadoTemporal() {
        double salarioEsperado = 45000 + 3000;
        assertEquals(salarioEsperado, empleadoTemporal.calcularSalarioTotal(),
            "El salario del empleado temporal no se calculó correctamente");
    }

    @Test
    void testAsignacionDepartamento() {
        departamento.agregarEmpleado(empleadoPermanente);
        
        assertEquals(departamento, empleadoPermanente.getDepartamento(),
            "El departamento no se asignó correctamente al empleado");
        assertTrue(departamento.getEmpleados().contains(empleadoPermanente),
            "El empleado no se agregó correctamente a la lista del departamento");
    }

    @Test
    void testRemoverEmpleadoDeDepartamento() {
        departamento.agregarEmpleado(empleadoPermanente);
        departamento.removerEmpleado(empleadoPermanente);
        
        assertNull(empleadoPermanente.getDepartamento(),
            "El departamento no se removió correctamente del empleado");
        assertFalse(departamento.getEmpleados().contains(empleadoPermanente),
            "El empleado no se removió correctamente de la lista del departamento");
    }

    @Test
    void testGenerarReporteDesempeno() {
        departamento.agregarEmpleado(empleadoPermanente);
        String reporte = empleadoPermanente.generarReporteDesempeno();
        
        assertTrue(reporte.contains("Juan Pérez"),
            "El reporte debe contener el nombre del empleado");
        assertTrue(reporte.contains("Recursos Humanos"),
            "El reporte debe contener el nombre del departamento");
        assertTrue(reporte.contains("50000"),
            "El reporte debe contener el salario base");
    }
}
