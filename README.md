# 📋 Todo Spring Boot Application

Una aplicación web completa de gestión de tareas (Todo) desarrollada con **Spring Boot**, **JPA/Hibernate** y **H2 Database**.

## 🚀 Características

- ✅ **API REST completa** - CRUD para gestión de tareas
- ✅ **Persistencia de datos** - Base de datos H2 con almacenamiento en archivo
- ✅ **Validación de entrada** - Validación automática de campos
- ✅ **Manejo de errores** - Respuestas HTTP apropiadas
- ✅ **CORS configurado** - Listo para frontend
- ✅ **Consola H2** - Acceso directo a la base de datos
- ✅ **Hot reload** - Spring Boot DevTools incluido

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Spring Boot 3.5.4** - Framework principal
- **Spring Data JPA** - Acceso a datos
- **Spring Web** - API REST
- **Spring Validation** - Validación de datos
- **H2 Database** - Base de datos embebida
- **Maven** - Gestión de dependencias
- **Lombok** - Reducción de código boilerplate

## 📋 Requisitos Previos

- Java 17 o superior
- Maven 3.6 o superior (o usar el wrapper incluido)

## 🔧 Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/miguelldev-code/todo-spring-boot.git
cd todo-spring-boot
```

### 2. Ejecutar la aplicación
```bash
# Usando Maven wrapper (recomendado)
./mvnw spring-boot:run

# O usando Maven instalado globalmente
mvn spring-boot:run
```

### 3. Acceder a la aplicación
- **API REST**: `http://localhost:8080/api/todos`
- **Consola H2**: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:file:./data/tareas`
  - Usuario: `sa`
  - Contraseña: (vacía)

## 📚 API Endpoints

### 📋 Obtener todas las tareas
```http
GET /api/todos
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "tarea": "Completar el proyecto Spring Boot",
    "completado": false
  }
]
```

### ➕ Crear nueva tarea
```http
POST /api/todos
Content-Type: application/json

{
  "tarea": "Nueva tarea por hacer"
}
```

**Respuesta (201 Created):**
```json
{
  "id": 2,
  "tarea": "Nueva tarea por hacer",
  "completado": false
}
```

### 🔄 Alternar estado de tarea
```http
PUT /api/todos/{id}/completar
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "tarea": "Completar el proyecto Spring Boot",
  "completado": true
}
```

### 🗑️ Eliminar tarea
```http
DELETE /api/todos/{id}
```

**Respuesta:** `200 OK` o `404 Not Found`

## 🗂️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/miguelldev/todo/
│   │   ├── TodoApplication.java          # Clase principal
│   │   ├── controller/
│   │   │   └── TodoController.java       # Controlador REST
│   │   ├── model/
│   │   │   └── Todo.java                 # Entidad JPA
│   │   └── repository/
│   │       └── TodoRepository.java       # Repositorio JPA
│   └── resources/
│       ├── application.properties        # Configuración
│       └── static/
│           └── index.html               # Página de bienvenida
└── test/
    └── java/miguelldev/todo/
        └── TodoApplicationTests.java    # Pruebas unitarias
```

## 🔧 Configuración

La aplicación utiliza H2 como base de datos con las siguientes configuraciones:

```properties
# Base de datos H2 persistente
spring.datasource.url=jdbc:h2:file:./data/tareas
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Consola H2 habilitada
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## 🧪 Ejecutar Pruebas

```bash
# Ejecutar todas las pruebas
./mvnw test

# Ejecutar pruebas con verificación completa
./mvnw verify
```

## 📦 Construir para Producción

```bash
# Generar JAR ejecutable
./mvnw clean package

# Ejecutar el JAR
java -jar target/Todo-0.0.1-SNAPSHOT.jar
```

## 🔒 Consideraciones de Seguridad

- La aplicación está configurada para aceptar solicitudes CORS desde `http://localhost:3000`
- Para producción, asegúrate de configurar adecuadamente:
  - Autenticación y autorización
  - Base de datos externa (PostgreSQL, MySQL)
  - Variables de entorno para configuraciones sensibles

## 🤝 Contribuir

1. Fork el proyecto
2. Crea tu rama de feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - mira el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍💻 Autor

**Miguel** - [@miguelldev-code](https://github.com/miguelldev-code)

## 🙏 Agradecimientos

- Spring Boot Team por el excelente framework
- H2 Database por la base de datos embebida
- Maven por la gestión de dependencias

---
⭐ ¡Si este proyecto te ayudó, dale una estrella en GitHub!
