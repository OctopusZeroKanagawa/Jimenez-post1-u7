# productos-web — Gestión de Productos con Spring Boot y Thymeleaf

Aplicación web CRUD completa para la gestión de productos, desarrollada como parte del **Post-Contenido 1 de la Unidad 7 (Spring Boot Básico)** del curso de Programación Web — Universidad Francisco de Paula Santander, 2026.

---

## Descripción del Proyecto

La aplicación permite **crear, listar, editar y eliminar productos** (CRUD completo) usando:

- **Spring Boot 3.2** como framework principal
- **Thymeleaf** como motor de plantillas HTML
- **Patrón MVC** para separar responsabilidades (Modelo / Vista / Controlador)
- **Patrón Post/Redirect/Get (PRG)** para evitar reenvíos de formulario al recargar la página
- **Persistencia en memoria** (HashMap) sin base de datos externa

---

## Estructura del Proyecto

```
productos-web/
├── src/
│   ├── main/
│   │   ├── java/com/universidad/productosweb/
│   │   │   ├── ProductosWebApplication.java   ← Punto de entrada Spring Boot
│   │   │   ├── model/
│   │   │   │   └── Producto.java              ← Entidad con id, nombre, descripcion, precio
│   │   │   ├── service/
│   │   │   │   └── ProductoService.java       ← Lógica CRUD + HashMap en memoria
│   │   │   └── controller/
│   │   │       └── ProductoController.java    ← Rutas HTTP y patrón PRG
│   │   └── resources/
│   │       ├── application.properties         ← Configuración del servidor
│   │       ├── static/                        ← Archivos estáticos (CSS/JS externos)
│   │       └── templates/
│   │           └── productos/
│   │               ├── lista.html             ← Vista: tabla con todos los productos
│   │               └── formulario.html        ← Vista: formulario crear/editar
│   └── test/
│       └── java/com/universidad/productosweb/
│           └── ProductosWebApplicationTests.java
├── pom.xml                                    ← Dependencias Maven
└── README.md
```

---

## Prerrequisitos

| Requisito | Versión mínima |
|-----------|---------------|
| Java JDK  | 17            |
| Maven     | 3.8+          |
| IDE       | IntelliJ IDEA (recomendado) o VS Code con Extension Pack for Java |
| Navegador | Chrome o Firefox |

Verificar Java instalado:
```bash
java -version
mvn -version
```

---

## Instrucciones de Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/OctopusZeroKanagawa/Jimenez-post1-u7
cd apellido-post1-u7
```

### 2. Compilar el proyecto

```bash
mvn clean compile
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La consola debe mostrar:

```
Started ProductosWebApplication in X.XXX seconds
```

### 4. Abrir en el navegador

```
http://localhost:8080/productos
```

---

## Rutas Disponibles

| Método | URL                          | Descripción                        |
|--------|------------------------------|------------------------------------|
| GET    | `/productos`                 | Lista todos los productos          |
| GET    | `/productos/nuevo`           | Formulario para crear producto     |
| POST   | `/productos/guardar`         | Guarda nuevo producto o edición    |
| GET    | `/productos/editar/{id}`     | Formulario prellenado para editar  |
| GET    | `/productos/eliminar/{id}`   | Elimina el producto y redirige     |

---

## Componentes Clave

### `Producto.java` (Model)
POJO con cuatro atributos: `id` (Long), `nombre`, `descripcion` (String) y `precio` (Double). Los getters/setters son obligatorios para el binding con Thymeleaf.

### `ProductoService.java` (Service)
Singleton gestionado por Spring (`@Service`). Usa un `LinkedHashMap<Long, Producto>` como base de datos en memoria con un contador autoincremental de IDs. Inicializa tres productos de ejemplo al arrancar.

### `ProductoController.java` (Controller)
Maneja todas las rutas bajo `/productos`. Implementa el patrón PRG: las operaciones de escritura terminan siempre en `return "redirect:/productos"` para evitar que F5 reenvíe el formulario.

### `lista.html` y `formulario.html` (Views)
Plantillas Thymeleaf que usan `th:each`, `th:text`, `th:href`, `th:field` y `th:object` para el renderizado dinámico y el binding bidireccional con el modelo.

---

## Patrón PRG en Acción

```
Navegador → POST /productos/guardar
              ↓
          Controlador guarda el producto
              ↓
          return "redirect:/productos"   ← HTTP 302
              ↓
Navegador → GET /productos              ← recarga segura
```

Si el usuario pulsa **F5** sobre la lista, el navegador repite el GET (seguro), no el POST (que duplicaría datos).

---

## Capturas de Pantalla

> Agregar capturas de pantalla aquí tras ejecutar la aplicación:

| Vista | Descripción |
|-------|-------------|
| `[captura lista]` | Tabla con los productos cargados al inicio |
| `[captura nuevo]` | Formulario vacío para agregar un producto |
| `[captura editar]` | Formulario prellenado con datos del producto |

---

## Datos de Ejemplo (precargados)

| ID | Nombre  | Descripción                   | Precio    |
|----|---------|-------------------------------|-----------|
| 1  | Laptop  | Laptop 15 pulgadas 16GB RAM   | $1,299.99 |
| 2  | Mouse   | Mouse inalámbrico ergonómico  | $29.99    |
| 3  | Teclado | Teclado mecánico TKL          | $89.99    |

> Los datos se reinician al reiniciar la aplicación (persistencia en memoria).

---

## Autor

**Andres Felipe Jimenez Ramírez** — Ingeniería de Sistemas  
Universidad Francisco de Paula Santander · 2026
