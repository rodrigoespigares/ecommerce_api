
# Ecommerce API

Este proyecto ha sido creado para resolver una necesidad de un profesor, con este proyecto, quiero ayudar a mi antiguo profesor que usaba una API de ecommerce que le estaba dando bastantes problemas, por eso me he propuesto resolver esos problemas con esta API, ofreciendole los servicios de forma gratuíta para que pueda usarla con sus estudiantes en el futuro curso
## Objetivos del proyecto API REST Ecommerce con Spring Boot

Durante el desarrollo de esta API REST para ecommerce, quiero aprender y aplicar los siguientes conceptos:

👤 **Gestión de usuarios**:

- Registro, login y logout seguro de usuarios.
- Manejo de roles y permisos (cliente, administrador).
- Autenticación con JWT y Spring Security.
- Encriptación segura de contraseñas.

📦 **Gestión de productos**:

- CRUD completo de productos y categorías.
- Manejo de atributos como precio, stock, imágenes y descripción.
- Búsqueda y filtrado avanzado de productos.

🛒 **Gestión de carrito de compra**:

- Crear y modificar carrito de compra por usuario.
- Añadir, eliminar o actualizar productos en el carrito.
- Cálculo dinámico de totales y descuentos aplicados.

📝 **Gestión de pedidos**:

- Creación y actualización de pedidos desde el carrito.
- Control de estados (pendiente, pagado, enviado, cancelado).
- Historial y seguimiento para cada usuario.

💳 **Integración de pagos**:

- Integración con pasarelas seguras (Stripe, PayPal, etc.).
- Validación y confirmación de pagos.
- Manejo de reembolsos y cancelaciones.

📊 **Gestión de inventario**:

- Control en tiempo real del stock disponible.
- Notificaciones y bloqueos por bajo stock.

🏠 **Gestión de clientes**:

- Perfil completo del cliente con datos personales y direcciones.
- Gestión de direcciones de envío y facturación.
- Preferencias y suscripciones para comunicación.

🔐 **Seguridad y control de acceso**:

- Protección de endpoints según roles.
- Prevención contra ataques CSRF, XSS y otros.
- Gestión segura de tokens y sesiones.

📄 **Documentación y pruebas**:

- Documentar API con Swagger/OpenAPI.
- Pruebas unitarias e integración para asegurar calidad.
- Registro de logs y manejo de errores.

⚙️ **Escalabilidad y buenas prácticas**:

- Arquitectura modular y desacoplada.
- Optimización en consultas y uso eficiente de base de datos.
- Diseño RESTful y uso de patrones recomendados.

## Endpoints
### 👤 Usuarios

- `POST /auth/register` — Registro de usuario  
- `POST /auth/login` — Login y obtención de JWT  
- `GET /users/{id}` — Obtener perfil de usuario  
- `PUT /users/{id}` — Actualizar la contraseña del usuario  

---

### 📦 Productos

- `GET /products` — Listar productos (con filtros y paginación)  
- `GET /products/{id}` — Obtener detalle de producto  
- `POST /products` — Crear nuevo producto (solo admin)  
- `PUT /products/{id}` — Actualizar producto (solo admin)  
- `DELETE /products/{id}` — Eliminar producto (solo admin)  

---

### 🗂️ Categorías

- `GET /categories` — Listar categorías  
- `GET /categories/{id}` — Obtener detalle de categoría  
- `POST /categories` — Crear categoría (solo admin)  
- `PUT /categories/{id}` — Actualizar categoría (solo admin)  
- `DELETE /categories/{id}` — Eliminar categoría (solo admin)  

---

### 🛒 Carrito

- `GET /cart` — Obtener carrito del usuario autenticado  
- `POST /cart` — Añadir producto al carrito  
- `PUT /cart/{productId}` — Modificar cantidad de producto en carrito  
- `DELETE /cart/{productId}` — Eliminar producto del carrito  

---

### 📝 Pedidos

- `POST /orders` — Crear nuevo pedido  
- `GET /orders` — Listar pedidos del usuario autenticado  
- `GET /orders/{id}` — Obtener detalle de un pedido  
- `PUT /orders/{id}/status` — Cambiar estado del pedido (solo admin)  
- `DELETE /orders/{id}` — Cancelar pedido (si aplica)  

---

### 💳 Pagos

- `POST /payments/checkout` — Procesar pago del pedido  
- `GET /payments/{id}` — Obtener estado de pago  

---

### 📊 Reportes (solo admin)

- `GET /reports/sales` — Reporte de ventas  
- `GET /reports/stock` — Reporte de stock y productos agotados  

## 🚀 Guía de Instalación para API Ecommerce con Spring Boot

---

### 1. Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

- **Java 24** 
- **Maven** 
- **MySQL** 
- **Git** 

---

### 2. Clonar el repositorio

```bash
git clone https://github.com/tuusuario/tu-repo-ecommerce.git
cd tu-repo-ecommerce
```

---

### 3. Configurar la base de datos

- Crea una base de datos en MySQL:

```sql
CREATE DATABASE ecommerce_db;
```

- Configura el archivo application.properties con tus datos, tienes un ejemplo completo en el archivo application.properties.example

---

## 4. Construir el proyecto

En la raíz del proyecto, ejecuta:

```bash
mvn clean install
```

---

## 5. Ejecutar la aplicación

Puedes ejecutar la aplicación con:

```bash
mvn spring-boot:run
```

---

## 6. Probar los endpoints

- La API estará disponible por defecto en:

  ```bash
  http://localhost:8000
  ```

O en el puerto elegido

- Usa herramientas como **Postman** para probar los endpoints. (Cuando termine la API subiré el json de postman para todos los endpoints)

---

## 7. Ejecutar tests

Para ejecutar pruebas automáticas:

```bash
mvn test
```

## Autor

- [@rodrigoespigares](https://www.github.com/rodrigoespigares)

