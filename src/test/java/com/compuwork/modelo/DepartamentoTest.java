package com.compuwork.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DepartamentoTest {
    private Departamento departamento;
    private EmpleadoPermanente empleado1;
    private EmpleadoTemporal empleado2;

    @BeforeEach
    void setUp() {
        departamento = new Departamento(1, "Tecnología");
        empleado1 = new EmpleadoPermanente(1, "Juan", "Pérez", "12345678",
            LocalDate.of(2020, 1, 1), 50000, 5000);
        empleado2 = new EmpleadoTemporal(2, "María", "García", "87654321",
            LocalDate.of(2023, 6, 1), 45000,
            LocalDate.of(2024, 6, 1), 3000);
    }

    @Test
    void testAgregarEmpleado() {
        departamento.agregarEmpleado(empleado1);
        
        assertEquals(1, departamento.getEmpleados().size(),
            "La lista de empleados debería tener un empleado");
        assertTrue(departamento.getEmpleados().contains(empleado1),
            "La lista de empleados debería contener al empleado agregado");
        assertEquals(departamento, empleado1.getDepartamento(),
            "El departamento debería estar asignado al empleado");
    }

    @Test
    void testRemoverEmpleado() {
        departamento.agregarEmpleado(empleado1);
        departamento.removerEmpleado(empleado1);
        
        assertEquals(0, departamento.getEmpleados().size(),
            "La lista de empleados debería estar vacía");
        assertNull(empleado1.getDepartamento(),
            "El empleado no debería tener departamento asignado");
    }

    @Test
    void testGenerarReporteDepartamento() {
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);
        
        String reporte = departamento.generarReporteDepartamento();
        
        assertTrue(reporte.contains("Tecnología"),
            "El reporte debe contener el nombre del departamento");
        assertTrue(reporte.contains("2"),
            "El reporte debe mostrar el número correcto de empleados");
        assertTrue(reporte.contains("Juan Pérez"),
            "El reporte debe contener el nombre del primer empleado");
        assertTrue(reporte.contains("María García"),
            "El reporte debe contener el nombre del segundo empleado");
    }

    @Test
    void testCalculoTotalSalarios() {
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);
        
        String reporte = departamento.generarReporteDepartamento();
        double salarioEsperado = empleado1.calcularSalarioTotal() + 
                                empleado2.calcularSalarioTotal();
        
        assertTrue(reporte.contains(String.format("%.2f", salarioEsperado)),
            "El reporte debe mostrar la suma correcta de los salarios");
    }
}
