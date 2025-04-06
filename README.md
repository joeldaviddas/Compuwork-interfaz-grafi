# Sistema de Gestión de Empleados

Aplicación de escritorio desarrollada en JavaFX para la gestión de empleados y departamentos.

## Características

### Gestión de Empleados
- Registro de empleados permanentes y temporales
- Campos para datos personales:
  * Nombre y Apellido
  * Número de CC
  * Fecha de contratación
  * Salario base

### Tipos de Empleado
1. **Empleado Permanente**
   - Bonificación por antigüedad
   - Cálculo automático de años de servicio

2. **Empleado Temporal**
   - Fecha de fin de contrato
   - Bonificación por proyecto

### Gestión de Departamentos
- Asignación de empleados a departamentos
- Departamentos disponibles:
  * Recursos Humanos
  * Tecnología de la Información
  * Ventas y Marketing
  * Finanzas y Contabilidad
  * Operaciones
  * Logística y Distribución
  * Control de Calidad

### Reportes
1. **Reporte Individual**
   - Datos personales completos
   - Información salarial detallada
   - Bonificaciones específicas según tipo de empleado
   - Departamento asignado

2. **Reporte de Departamento**
   - Estadísticas del departamento
   - Número total de empleados
   - Distribución por tipo (permanente/temporal)
   - Costos totales (salarios y bonificaciones)
   - Lista detallada de empleados

## Interfaz de Usuario
- Diseño moderno y profesional
- Paneles organizados con sombras y bordes redondeados
- Colores corporativos:
  * Verde (#2ecc71) para acciones de agregar
  * Azul (#3498db) para reportes individuales
  * Morado (#9b59b6) para reportes departamentales
- Campos de entrada validados
- Interfaz responsiva y amigable

## Requisitos Técnicos
- Java 17
- JavaFX 17.0.2
- Maven para gestión de dependencias
- JUnit 5.9.2 para pruebas

## Estructura del Proyecto
CompuWork/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── compuwork/
│   │   │           ├── controlador/
│   │   │           │   └── MainController.java     # Controlador principal de la aplicación
│   │   │           ├── modelo/
│   │   │           │   ├── Empleado.java          # Clase base abstracta para empleados
│   │   │           │   ├── EmpleadoPermanente.java # Implementación de empleado permanente
│   │   │           │   ├── EmpleadoTemporal.java   # Implementación de empleado temporal
│   │   │           │   └── Departamento.java       # Gestión de departamentos
│   │   │           └── Main.java                   # Punto de entrada de la aplicación
│   │   └── resources/
│   │       └── vista/
│   │           └── main.fxml                       # Interfaz gráfica en FXML
│   └── test/
│       └── java/
│           └── com/
│               └── compuwork/
│                   └── modelo/
│                       ├── EmpleadoTest.java       # Pruebas para la clase Empleado
│                       └── DepartamentoTest.java    # Pruebas para la clase Departamento
├── pom.xml                                         # Configuración de Maven
└── README.md                                       # Documentación del proyecto

## Instalación y Ejecución
1. Clonar el repositorio
2. Asegurarse de tener Java 17 y Maven instalados
3. Ejecutar:
   ```bash
   mvn clean javafx:run
   ```

## Características de la Interfaz
- **Panel Izquierdo**: Formulario de registro
  * Datos Personales
  * Datos Laborales
  * Tipo de Empleado y bonificaciones

- **Panel Derecho**: Visualización y reportes
  * Lista de empleados
  * Botones de generación de reportes
  * Área de visualización de reportes

## Validaciones
- Campos obligatorios
- Formato de números para salarios y bonificaciones
- Fechas válidas para contratación y fin de contrato
- Validación de CC
