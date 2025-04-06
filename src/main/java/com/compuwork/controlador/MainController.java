/**
 * Controlador principal de la aplicación de gestión de empleados.
 * Maneja la interacción entre la interfaz gráfica y los modelos de datos.
 * 
 * Características principales:
 * 1. Gestión de empleados:
 *    - Registro de nuevos empleados (permanentes y temporales)
 *    - Validación de campos obligatorios
 *    - Manejo de errores y mensajes al usuario
 * 
 * 2. Gestión de departamentos:
 *    - Lista de departamentos disponibles
 *    - Asignación de empleados a departamentos
 * 
 * 3. Reportes:
 *    - Generación de reportes individuales
 *    - Generación de reportes departamentales
 *    - Visualización de información detallada
 * 
 * 4. Interfaz gráfica:
 *    - Diseño moderno con paneles organizados
 *    - Campos de entrada validados
 *    - Colores corporativos para acciones
 *    - Mensajes de retroalimentación al usuario
 */
package com.compuwork.controlador;

import com.compuwork.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private List<Departamento> departamentos = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    
    @FXML private ComboBox<Departamento> comboDepartamentos;
    @FXML private ListView<Empleado> listaEmpleados;
    @FXML private TextArea areaReporte;
    @FXML private TextField txtNombre, txtApellido, txtCc;
    @FXML private DatePicker fechaContratacion;
    @FXML private TextField txtSalarioBase;
    @FXML private RadioButton rbPermanente, rbTemporal;
    @FXML private VBox panelPermanente, panelTemporal;
    @FXML private TextField txtBonificacionAntiguedad, txtBonificacionProyecto;
    @FXML private DatePicker fechaFinContrato;

    /**
     * Inicializa el controlador y configura los elementos de la interfaz.
     * - Crea departamentos de ejemplo
     * - Configura listeners para radio buttons
     * - Inicializa listas y campos
     */
    @FXML
    public void initialize() {
        // Configurar los RadioButtons
        ToggleGroup grupo = new ToggleGroup();
        rbPermanente.setToggleGroup(grupo);
        rbTemporal.setToggleGroup(grupo);
        
        // Listener para mostrar/ocultar paneles según tipo de empleado
        rbPermanente.selectedProperty().addListener((obs, oldVal, newVal) -> {
            panelPermanente.setVisible(newVal);
            panelTemporal.setVisible(!newVal);
        });

        // Inicializar algunos datos de ejemplo
        inicializarDatosEjemplo();
        
        // Configurar los ComboBox y ListView
        actualizarListasDepartamentosYEmpleados();
    }

    private void inicializarDatosEjemplo() {
        // Crear departamentos de ejemplo
        Departamento rrhh = new Departamento(1, "Recursos Humanos");
        Departamento ti = new Departamento(2, "Tecnología de la Información");
        Departamento ventas = new Departamento(3, "Ventas y Marketing");
        Departamento finanzas = new Departamento(4, "Finanzas y Contabilidad");
        Departamento operaciones = new Departamento(5, "Operaciones");
        Departamento logistica = new Departamento(6, "Logística y Distribución");
        Departamento calidad = new Departamento(7, "Control de Calidad");
        
        departamentos.add(rrhh);
        departamentos.add(ti);
        departamentos.add(ventas);
        departamentos.add(finanzas);
        departamentos.add(operaciones);
        departamentos.add(logistica);
        departamentos.add(calidad);

        // Crear algunos empleados de ejemplo
        EmpleadoPermanente emp1 = new EmpleadoPermanente(1, "Juan", "Pérez", "12345678", 
            LocalDate.of(2020, 1, 1), 50000, 5000);
        EmpleadoTemporal emp2 = new EmpleadoTemporal(2, "María", "García", "87654321",
            LocalDate.of(2023, 6, 1), 45000, LocalDate.of(2024, 6, 1), 3000);
        EmpleadoPermanente emp3 = new EmpleadoPermanente(3, "Carlos", "López", "23456789",
            LocalDate.of(2019, 3, 15), 55000, 6000);
        EmpleadoTemporal emp4 = new EmpleadoTemporal(4, "Ana", "Martínez", "98765432",
            LocalDate.of(2023, 9, 1), 48000, LocalDate.of(2024, 9, 1), 4000);

        rrhh.agregarEmpleado(emp1);
        ti.agregarEmpleado(emp2);
        ventas.agregarEmpleado(emp3);
        finanzas.agregarEmpleado(emp4);
        
        empleados.add(emp1);
        empleados.add(emp2);
        empleados.add(emp3);
        empleados.add(emp4);
    }

    private void actualizarListasDepartamentosYEmpleados() {
        comboDepartamentos.setItems(FXCollections.observableArrayList(departamentos));
        listaEmpleados.setItems(FXCollections.observableArrayList(empleados));
    }

    /**
     * Agrega un nuevo empleado al sistema.
     * 
     * Proceso:
     * 1. Valida todos los campos obligatorios
     * 2. Obtiene los valores del formulario
     * 3. Crea el empleado según su tipo (Permanente/Temporal)
     * 4. Asigna el empleado al departamento seleccionado
     * 5. Actualiza las listas
     * 6. Limpia el formulario
     * 
     * Manejo de errores:
     * - Campos incompletos
     * - Valores numéricos inválidos
     * - Errores generales
     */
    @FXML
    private void agregarEmpleado() {
        try {
            // Validar campos obligatorios
            if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || 
                txtCc.getText().isEmpty() || fechaContratacion.getValue() == null || 
                txtSalarioBase.getText().isEmpty() || comboDepartamentos.getValue() == null) {
                mostrarError("Por favor, complete todos los campos obligatorios.");
                return;
            }

            // Crear nuevo empleado
            int id = empleados.size() + 1;
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String cc = txtCc.getText();
            LocalDate fechaContr = fechaContratacion.getValue();
            double salarioBase = Double.parseDouble(txtSalarioBase.getText());
            
            Empleado nuevoEmpleado;
            if (rbPermanente.isSelected()) {
                double bonificacion = Double.parseDouble(txtBonificacionAntiguedad.getText());
                nuevoEmpleado = new EmpleadoPermanente(id, nombre, apellido, cc, 
                    fechaContr, salarioBase, bonificacion);
            } else {
                double bonificacion = Double.parseDouble(txtBonificacionProyecto.getText());
                LocalDate fechaFin = fechaFinContrato.getValue();
                nuevoEmpleado = new EmpleadoTemporal(id, nombre, apellido, cc, 
                    fechaContr, salarioBase, fechaFin, bonificacion);
            }

            // Agregar al departamento seleccionado
            Departamento departamento = comboDepartamentos.getValue();
            departamento.agregarEmpleado(nuevoEmpleado);
            empleados.add(nuevoEmpleado);
            
            // Actualizar la interfaz
            actualizarListasDepartamentosYEmpleados();
            limpiarFormulario();
            
        } catch (NumberFormatException e) {
            mostrarError("Por favor, ingrese valores numéricos válidos para los campos de salario y bonificación.");
        } catch (Exception e) {
            mostrarError("Error al agregar empleado: " + e.getMessage());
        }
    }

    /**
     * Genera un reporte detallado del empleado seleccionado.
     * 
     * El reporte incluye:
     * - Datos personales completos
     * - Información laboral
     * - Salario base y total
     * - Bonificaciones específicas según tipo
     * - Departamento asignado
     */
    @FXML
    private void generarReporte() {
        if (listaEmpleados.getSelectionModel().getSelectedItem() != null) {
            Empleado empleado = listaEmpleados.getSelectionModel().getSelectedItem();
            areaReporte.setText(empleado.generarReporteDesempeno());
        } else if (comboDepartamentos.getValue() != null) {
            Departamento departamento = comboDepartamentos.getValue();
            areaReporte.setText(departamento.generarReporteDepartamento());
        } else {
            mostrarError("Por favor, seleccione un empleado o un departamento para generar el reporte.");
        }
    }

    /**
     * Limpia todos los campos del formulario.
     * Se llama después de agregar un empleado o cuando se necesita reiniciar el formulario.
     */
    private void limpiarFormulario() {
        txtNombre.clear();
        txtApellido.clear();
        txtCc.clear();
        fechaContratacion.setValue(null);
        txtSalarioBase.clear();
        txtBonificacionAntiguedad.clear();
        txtBonificacionProyecto.clear();
        fechaFinContrato.setValue(null);
    }

    /**
     * Muestra un mensaje de error al usuario.
     * Utilizado para notificar problemas en la validación o errores del sistema.
     * 
     * @param mensaje El mensaje de error a mostrar
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
