# 📖 API REST Documentation

## 📍 Base URL
```
http://localhost:8080/api/todos
```

## 🔧 Headers
```
Content-Type: application/json
Accept: application/json
```

---

## 📋 Endpoints

### 1. 📋 Obtener todas las tareas

**GET** `/api/todos`

Obtiene la lista completa de todas las tareas almacenadas.

#### Respuesta
- **Código:** `200 OK`
- **Formato:** JSON Array

```json
[
  {
    "id": 1,
    "tarea": "Completar documentación de la API",
    "completado": false
  },
  {
    "id": 2,
    "tarea": "Implementar frontend React",
    "completado": true
  }
]
```

#### Ejemplo con cURL
```bash
curl -X GET http://localhost:8080/api/todos \
  -H "Accept: application/json"
```

---

### 2. ➕ Crear nueva tarea

**POST** `/api/todos`

Crea una nueva tarea en la lista.

#### Body de la petición
```json
{
  "tarea": "Descripción de la nueva tarea"
}
```

#### Validaciones
- `tarea`: **Requerido**, no puede estar vacío, máximo 255 caracteres

#### Respuesta Exitosa
- **Código:** `201 Created`
- **Formato:** JSON Object

```json
{
  "id": 3,
  "tarea": "Descripción de la nueva tarea",
  "completado": false
}
```

#### Respuesta de Error (Validación)
- **Código:** `400 Bad Request`

```json
{
  "timestamp": "2025-08-28T19:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "La tarea no puede estar vacía",
  "path": "/api/todos"
}
```

#### Ejemplo con cURL
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{
    "tarea": "Estudiar Spring Boot"
  }'
```

---

### 3. 🔄 Alternar estado de completado

**PUT** `/api/todos/{id}/completar`

Cambia el estado de completado de una tarea (de `true` a `false` o viceversa).

#### Parámetros de Ruta
- `id` (Long): ID de la tarea a modificar

#### Respuesta Exitosa
- **Código:** `200 OK`
- **Formato:** JSON Object

```json
{
  "id": 1,
  "tarea": "Completar documentación de la API",
  "completado": true
}
```

#### Respuesta de Error (Tarea no encontrada)
- **Código:** `404 Not Found`

#### Ejemplo con cURL
```bash
# Alternar estado de la tarea con ID 1
curl -X PUT http://localhost:8080/api/todos/1/completar \
  -H "Accept: application/json"
```

---

### 4. 🗑️ Eliminar tarea

**DELETE** `/api/todos/{id}`

Elimina permanentemente una tarea de la lista.

#### Parámetros de Ruta
- `id` (Long): ID de la tarea a eliminar

#### Respuesta Exitosa
- **Código:** `200 OK`
- **Body:** Vacío

#### Respuesta de Error (Tarea no encontrada)
- **Código:** `404 Not Found`

#### Ejemplo con cURL
```bash
# Eliminar tarea con ID 2
curl -X DELETE http://localhost:8080/api/todos/2
```

---

## 🛠️ Ejemplos de Uso Completo

### Workflow típico con JavaScript (Frontend)

```javascript
const API_BASE = 'http://localhost:8080/api/todos';

// 1. Obtener todas las tareas
async function obtenerTareas() {
  const response = await fetch(API_BASE);
  const tareas = await response.json();
  return tareas;
}

// 2. Crear nueva tarea
async function crearTarea(descripcion) {
  const response = await fetch(API_BASE, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ tarea: descripcion })
  });
  
  if (response.ok) {
    const nuevaTarea = await response.json();
    return nuevaTarea;
  }
  throw new Error('Error al crear la tarea');
}

// 3. Alternar estado de tarea
async function alternarTarea(id) {
  const response = await fetch(`${API_BASE}/${id}/completar`, {
    method: 'PUT'
  });
  
  if (response.ok) {
    const tareaActualizada = await response.json();
    return tareaActualizada;
  }
  throw new Error('Tarea no encontrada');
}

// 4. Eliminar tarea
async function eliminarTarea(id) {
  const response = await fetch(`${API_BASE}/${id}`, {
    method: 'DELETE'
  });
  
  return response.ok;
}
```

### Ejemplo con Python (requests)

```python
import requests
import json

BASE_URL = "http://localhost:8080/api/todos"

# Obtener todas las tareas
def obtener_tareas():
    response = requests.get(BASE_URL)
    return response.json() if response.status_code == 200 else []

# Crear nueva tarea
def crear_tarea(descripcion):
    data = {"tarea": descripcion}
    response = requests.post(
        BASE_URL, 
        json=data,
        headers={"Content-Type": "application/json"}
    )
    return response.json() if response.status_code == 201 else None

# Alternar estado
def alternar_tarea(id_tarea):
    url = f"{BASE_URL}/{id_tarea}/completar"
    response = requests.put(url)
    return response.json() if response.status_code == 200 else None

# Eliminar tarea
def eliminar_tarea(id_tarea):
    url = f"{BASE_URL}/{id_tarea}"
    response = requests.delete(url)
    return response.status_code == 200
```

---

## 🚫 Códigos de Error

| Código | Descripción | Causa Común |
|--------|-------------|-------------|
| `200` | OK | Operación exitosa |
| `201` | Created | Recurso creado exitosamente |
| `400` | Bad Request | Datos de entrada inválidos |
| `404` | Not Found | Tarea no existe |
| `500` | Internal Server Error | Error interno del servidor |

---

## 🔒 CORS

La aplicación está configurada para aceptar peticiones desde:
- `http://localhost:3000` (desarrollo frontend)

Para modificar los orígenes permitidos, edita `TodoController.java`:

```java
@CrossOrigin(origins = {"http://localhost:3000", "https://mi-frontend.com"})
```

---

## 📊 Modelo de Datos

### Todo Entity

| Campo | Tipo | Requerido | Descripción |
|-------|------|-----------|-------------|
| `id` | Long | Sí (auto) | Identificador único |
| `tarea` | String | Sí | Descripción de la tarea (máx. 255 chars) |
| `completado` | Boolean | No | Estado de completado (default: false) |

### Constraints de Validación

- **tarea**:
  - `@NotBlank`: No puede estar vacío
  - `@Size(max = 255)`: Máximo 255 caracteres

---

## 🧪 Testing de la API

### Con Postman

1. Importa la colección desde este [enlace](#) (próximamente)
2. Configura la variable `{{base_url}}` como `http://localhost:8080/api/todos`
3. Ejecuta las peticiones de prueba

### Con Thunder Client (VS Code)

```json
{
  "client": "Thunder Client",
  "collectionName": "Todo API",
  "requests": [
    {
      "name": "Get All Todos",
      "method": "GET",
      "url": "http://localhost:8080/api/todos"
    },
    {
      "name": "Create Todo",
      "method": "POST",
      "url": "http://localhost:8080/api/todos",
      "body": {
        "type": "json",
        "raw": "{\"tarea\": \"Test todo\"}"
      }
    }
  ]
}
```

---

## 📈 Próximas Mejoras

- [ ] Paginación para listas grandes
- [ ] Filtros por estado (completado/pendiente)
- [ ] Ordenamiento por fecha de creación
- [ ] Búsqueda por texto
- [ ] Autenticación JWT
- [ ] Rate limiting
- [ ] Documentación OpenAPI/Swagger

---

¿Encontraste algún error o tienes sugerencias? ¡Abre un [issue](https://github.com/miguelldev-code/todo-spring-boot/issues) en GitHub!
