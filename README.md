# 📦 LogiTrack - Sistema de Gestión de Bodegas

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Auth-black?style=for-the-badge&logo=jsonwebtokens)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**Sistema centralizado de gestión de inventarios y auditoría de movimientos entre bodegas**

[Características](#-características) • [Instalación](#-instalación) • [Uso](#-uso) • [API](#-documentación-api) • [Contribuir](#-contribuir)

</div>

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Requisitos](#-requisitos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Uso](#-uso)
- [Documentación API](#-documentación-api)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Testing](#-testing)
- [Docker](#-docker-opcional)
- [Contribuir](#-contribuir)
- [Licencia](#-licencia)
- [Autores](#-autores)

---

## 📖 Descripción

**LogiTrack** es un sistema backend desarrollado en **Spring Boot** que permite a la empresa LogiTrack S.A. gestionar de manera centralizada el inventario de múltiples bodegas distribuidas en distintas ciudades. 

El sistema reemplaza el control manual en hojas de cálculo por una solución robusta que ofrece:

- ✅ Control completo de movimientos entre bodegas (entradas, salidas, transferencias)
- ✅ Auditoría automática de todos los cambios
- ✅ Autenticación segura con JWT
- ✅ API REST documentada con Swagger/OpenAPI
- ✅ Frontend interactivo y responsivo

---

## ✨ Características

### 🏢 Gestión de Bodegas
- CRUD completo de bodegas
- Asignación de encargados
- Control de capacidad y ubicación
- Activación/desactivación de bodegas

### 📦 Gestión de Productos
- Catálogo completo de productos
- Categorización flexible
- Control de precios
- Estado activo/inactivo

### 🔄 Movimientos de Inventario
- **Entradas**: Registro de ingreso de productos
- **Salidas**: Control de despachos
- **Transferencias**: Movimientos entre bodegas
- Validación automática de stock
- Historial completo de movimientos

### 📊 Sistema de Auditoría
- Registro automático de cambios mediante AOP
- Captura de valores anteriores y nuevos
- Trazabilidad por usuario y fecha
- Reportes de operaciones

### 🔐 Seguridad
- Autenticación con JWT
- Roles de usuario (ADMIN, EMPLEADO)
- Protección de endpoints
- Manejo global de excepciones

### 📈 Consultas y Reportes
- Stock por bodega
- Productos con stock bajo
- Movimientos por rango de fechas
- Auditorías filtradas
- Productos más movidos

---

## 🛠 Tecnologías

### Backend
- **Java 17**
- **Spring Boot 3.5.7**
  - Spring Data JPA
  - Spring Security
  - Spring Validation
  - Spring Web
- **JWT (jjwt 0.11.5)** - Autenticación
- **MySQL 8.0** - Base de datos
- **Lombok** - Reducción de boilerplate
- **Swagger/OpenAPI 3** - Documentación

### Frontend
- **HTML5 / CSS3 / JavaScript (Vanilla)**
- Diseño responsivo
- Fetch API para consumo REST

### Herramientas
- **Maven** - Gestión de dependencias
- **JUnit 5** - Testing
- **Git** - Control de versiones

---

## 📋 Requisitos

- **JDK 17** o superior
- **Maven 3.8+**
- **MySQL 8.0+**
- **Servidor Tomcat** (embebido o externo)
- **Git**

---

## 🚀 Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/logitrack.git
cd logitrack
```

### 2. Configurar la base de datos

Crear la base de datos en MySQL:
```sql
CREATE DATABASE logitrack_db;
```

Ejecutar los scripts SQL (opcional):
```bash
mysql -u root -p logitrack_db < src/main/resources/squema.sql
mysql -u root -p logitrack_db < src/main/resources/data.sql
```

### 3. Configurar `application.properties`

Editar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/logitrack_db
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
```

### 4. Compilar y ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

El servidor estará disponible en: `http://localhost:8080`

---

## ⚙️ Configuración

### Variables de entorno

Puedes sobrescribir la configuración usando variables de entorno:
```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/logitrack_db
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=tu_password
export JWT_SECRET=tu_secreto_jwt
```

### Configuración de JWT

En `application.properties`:
```properties
jwt.secret=mi_clave_secreta_super_segura_2025
jwt.expiration=3600000
```

---

## 💻 Uso

### Acceder a la aplicación

1. **Frontend**: `http://localhost:8080/index.html`
2. **Swagger UI**: `http://localhost:8080/docs`
3. **API Docs**: `http://localhost:8080/v3/api-docs`

### Credenciales por defecto

Si ejecutaste `data.sql`:
```
Admin:
Email: admin@empresa.com
Password: adminpass123

Empleado:
Email: empleado@empresa.com
Password: passemp
```

### Flujo de trabajo típico

1. **Login** → Obtener token JWT
2. **Gestionar Bodegas** → Crear bodegas y asignar encargados
3. **Gestionar Productos** → Agregar productos al catálogo
4. **Registrar Movimientos** → Entradas, salidas o transferencias
5. **Consultar Stock** → Verificar inventario por bodega
6. **Ver Auditoría** → Revisar historial de cambios

---

## 📚 Documentación API

### Endpoints principales

#### 🔐 Autenticación (`/api/auth`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/auth/login` | Iniciar sesión |
| POST | `/auth/register` | Registrar usuario |
| GET | `/auth/me` | Obtener usuario actual |
| GET | `/auth/verify` | Verificar token |
| POST | `/auth/logout` | Cerrar sesión |

#### 🏢 Bodegas (`/api/bodegas`)

| Método | Endpoint | Descripción | Roles |
|--------|----------|-------------|-------|
| GET | `/bodegas` | Listar todas las bodegas | ADMIN, EMPLEADO |
| GET | `/bodegas/{id}` | Obtener bodega por ID | ADMIN, EMPLEADO |
| GET | `/bodegas/encargado/id/{encargadoId}` | Bodegas por encargado | ADMIN, EMPLEADO |
| GET | `/bodegas/capacidad/{cantidad}` | Bodegas con capacidad mayor | ADMIN, EMPLEADO |
| POST | `/bodegas` | Crear bodega | ADMIN |
| PUT | `/bodegas/{id}` | Actualizar bodega | ADMIN |
| DELETE | `/bodegas/{id}` | Eliminar bodega (soft delete) | ADMIN |

#### 📦 Productos (`/api/productos`)

| Método | Endpoint | Descripción | Roles |
|--------|----------|-------------|-------|
| GET | `/productos` | Listar productos | ADMIN, EMPLEADO |
| GET | `/productos/{id}` | Obtener producto | ADMIN, EMPLEADO |
| GET | `/productos/nombre/{nombre}` | Buscar por nombre | ADMIN, EMPLEADO |
| GET | `/productos/categoria/{categoria}` | Filtrar por categoría | ADMIN, EMPLEADO |
| GET | `/productos/precio-mayor/{precio}` | Precio mayor a | ADMIN, EMPLEADO |
| POST | `/productos` | Crear producto | ADMIN |
| PUT | `/productos/{id}` | Actualizar producto | ADMIN |
| DELETE | `/productos/{id}` | Eliminar producto (soft delete) | ADMIN |

#### 📊 Inventario (`/api/inventarios`)

| Método | Endpoint | Descripción | Roles |
|--------|----------|-------------|-------|
| GET | `/inventarios` | Listar todo el inventario | ADMIN, EMPLEADO |
| GET | `/inventarios/{id}` | Obtener inventario por ID | ADMIN, EMPLEADO |
| GET | `/inventarios/producto/{productoId}` | Inventario por producto | ADMIN, EMPLEADO |
| GET | `/inventarios/bodega/{bodegaId}` | Inventario por bodega | ADMIN, EMPLEADO |
| GET | `/inventarios/producto/{productoId}/bodega/{bodegaId}` | Inventario específico | ADMIN, EMPLEADO |
| POST | `/inventarios` | Crear registro de inventario | ADMIN |
| PUT | `/inventarios/{id}` | Actualizar inventario | ADMIN |
| DELETE | `/inventarios/{id}` | Eliminar registro | ADMIN |

#### 🔄 Movimientos (`/api/movimientos`)

| Método | Endpoint | Descripción | Roles |
|--------|----------|-------------|-------|
| GET | `/movimientos` | Listar todos los movimientos | ADMIN, EMPLEADO |
| GET | `/movimientos/recientes` | Movimientos del último mes | ADMIN, EMPLEADO |
| GET | `/movimientos/por-fecha` | Filtrar por rango de fechas | ADMIN, EMPLEADO |
| GET | `/movimientos/por-tipo/{tipo}` | Filtrar por tipo (ENTRADA/SALIDA/TRANSFERENCIA) | ADMIN, EMPLEADO |
| GET | `/movimientos/por-bodega/{bodegaId}` | Movimientos de una bodega | ADMIN, EMPLEADO |
| GET | `/movimientos/por-usuario/{usuarioId}` | Movimientos de un usuario | ADMIN, EMPLEADO |
| GET | `/movimientos/{movimientoId}/detalles` | Detalles de un movimiento | ADMIN, EMPLEADO |
| GET | `/movimientos/reportes/productos-mas-movidos` | Reporte de productos | ADMIN, EMPLEADO |
| GET | `/movimientos/verificar-stock/{bodegaId}/{productoId}/{cantidad}` | Verificar disponibilidad | ADMIN, EMPLEADO |
| GET | `/movimientos/stock-actual/{bodegaId}/{productoId}` | Obtener stock actual | ADMIN, EMPLEADO |
| POST | `/movimientos/entrada/{bodegaId}` | Registrar entrada | ADMIN, EMPLEADO |
| POST | `/movimientos/salida/{bodegaId}` | Registrar salida | ADMIN, EMPLEADO |
| POST | `/movimientos/transferencia/{origenId}/{destinoId}` | Registrar transferencia | ADMIN, EMPLEADO |
| DELETE | `/movimientos/{movimientoId}` | Cancelar movimiento | ADMIN |

#### 📋 Auditoría (`/api/auditoria`)

| Método | Endpoint | Descripción | Roles |
|--------|----------|-------------|-------|
| GET | `/auditoria` | Todas las auditorías | ADMIN |
| GET | `/auditoria/{id}` | Auditoría por ID | ADMIN |
| GET | `/auditoria/por-usuario/{usuarioId}` | Auditorías por usuario | ADMIN |
| GET | `/auditoria/por-operacion/{tipoOperacion}` | Por tipo de operación (INSERT/UPDATE/DELETE) | ADMIN |
| GET | `/auditoria/por-entidad/{entidad}` | Por entidad afectada | ADMIN |
| GET | `/auditoria/por-fecha` | Por rango de fechas | ADMIN |
| GET | `/auditoria/por-usuario-fecha/{usuarioId}` | Usuario y fecha | ADMIN |
| GET | `/auditoria/por-entidad-id` | Por entidad y ID específico | ADMIN |
| GET | `/auditoria/ultimas` | Últimas auditorías | ADMIN |
| GET | `/auditoria/hoy` | Auditorías de hoy | ADMIN |
| GET | `/auditoria/buscar` | Buscar por descripción | ADMIN |
| GET | `/auditoria/reportes/resumen-operaciones` | Resumen de operaciones | ADMIN |
| GET | `/auditoria/reportes/entidades-mas-auditadas` | Entidades más auditadas | ADMIN |

---

## 📝 Ejemplos de uso con cURL

### 1. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@empresa.com",
    "password": "adminpass123"
  }'
```

**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 1,
  "nombre": "Admin General",
  "email": "admin@empresa.com",
  "rol": "ADMIN"
}
```

### 2. Listar productos (con token)
```bash
curl http://localhost:8080/api/productos \
  -H "Authorization: Bearer {TOKEN}"
```

### 3. Crear producto
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Authorization: Bearer {TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop Dell XPS",
    "categoria": "TECNOLOGIA",
    "precio": 1200.00,
    "activo": true
  }'
```

### 4. Crear bodega
```bash
curl -X POST http://localhost:8080/api/bodegas \
  -H "Authorization: Bearer {TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Bodega Central",
    "ubicacion": "Calle Principal 123",
    "capacidad": 5000,
    "encargadoId": 1,
    "activo": true
  }'
```

### 5. Registrar entrada de inventario
```bash
curl -X POST http://localhost:8080/api/movimientos/entrada/1 \
  -H "Authorization: Bearer {TOKEN}" \
  -H "Content-Type: application/json" \
  -d '{
    "detalles": [
      {
        "productoId": 1,
        "cantidad": 50
      },
      {
        "productoId": 2,
        "cantidad": 30
      }
    ],
    "observaciones": "Entrada de mercancía mensual"
  }'
```

### 6. Consultar stock de una bodega
```bash
curl http://localhost:8080/api/inventarios/bodega/1 \
  -H "Authorization: Bearer {TOKEN}"
```

### 7. Ver auditorías de hoy
```bash
curl http://localhost:8080/api/auditoria/hoy \
  -H "Authorization: Bearer {TOKEN}"
```

---

## 📁 Estructura del Proyecto
```
logitrack/
├── src/
│   ├── main/
│   │   ├── java/com/logitrack/logitrack/
│   │   │   ├── annotations/         # Anotaciones personalizadas
│   │   │   │   └── Auditar.java
│   │   │   ├── aspect/             # Aspectos AOP
│   │   │   │   └── AuditoriaAspect.java
│   │   │   ├── config/             # Configuración
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── SwaggerConfig.java
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   ├── controllers/        # Controladores REST
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── BodegaController.java
│   │   │   │   ├── ProductoController.java
│   │   │   │   ├── InventarioController.java
│   │   │   │   ├── MovimientoController.java
│   │   │   │   ├── AuditoriaController.java
│   │   │   │   └── FaviconController.java
│   │   │   ├── dto/                # Data Transfer Objects
│   │   │   │   ├── BodegaDTO.java
│   │   │   │   ├── ProductoDTO.java
│   │   │   │   ├── InventarioDTO.java
│   │   │   │   ├── LoginRequestDTO.java
│   │   │   │   ├── JwtResponseDTO.java
│   │   │   │   └── RegistroUsuarioDTO.java
│   │   │   ├── entities/           # Entidades JPA
│   │   │   │   ├── Usuario.java
│   │   │   │   ├── Bodega.java
│   │   │   │   ├── Producto.java
│   │   │   │   ├── Inventario.java
│   │   │   │   ├── MovimientoInventario.java
│   │   │   │   ├── MovimientoDetalle.java
│   │   │   │   └── AuditoriaCambios.java
│   │   │   ├── enums/              # Enumeraciones
│   │   │   │   ├── Rol.java
│   │   │   │   ├── TipoMovimiento.java
│   │   │   │   └── TipoOperacion.java
│   │   │   ├── exceptions/         # Manejo de excepciones
│   │   │   │   ├── ApiError.java
│   │   │   │   ├── BusinessException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── listeners/          # Listeners JPA
│   │   │   │   └── AuditoriaEntityListener.java
│   │   │   ├── repositories/       # Repositorios Spring Data
│   │   │   │   ├── UsuarioRepository.java
│   │   │   │   ├── BodegaRepository.java
│   │   │   │   ├── ProductoRepository.java
│   │   │   │   ├── InventarioRepository.java
│   │   │   │   ├── MovimientoInventarioRepository.java
│   │   │   │   ├── MovimientoDetalleRepository.java
│   │   │   │   └── AuditoriaCambiosRepository.java
│   │   │   └── services/           # Lógica de negocio
│   │   │       ├── AuthService.java
│   │   │       ├── UsuarioService.java
│   │   │       ├── BodegaService.java
│   │   │       ├── ProductoService.java
│   │   │       ├── InventarioService.java
│   │   │       ├── MovimientoService.java
│   │   │       ├── AuditoriaService.java
│   │   │       ├── JwtService.java
│   │   │       ├── CustomUserDetailsService.java
│   │   │       └── PasswordEncoderService.java
│   │   └── resources/
│   │       ├── static/             # Frontend (HTML/CSS/JS)
│   │       │   ├── css/
│   │       │   │   ├── styles.css
│   │       │   │   ├── auth.css
│   │       │   │   ├── dashboard.css
│   │       │   │   ├── bodegas.css
│   │       │   │   ├── productos.css
│   │       │   │   ├── movimientos.css
│   │       │   │   ├── auditoria.css
│   │       │   │   └── components.css
│   │       │   ├── js/
│   │       │   │   ├── config.js
│   │       │   │   ├── auth.js
│   │       │   │   ├── dashboard.js
│   │       │   │   ├── bodegas.js
│   │       │   │   ├── productos.js
│   │       │   │   ├── movimientos.js
│   │       │   │   ├── auditoria.js
│   │       │   │   └── utils.js
│   │       │   ├── index.html
│   │       │   ├── dashboard.html
│   │       │   ├── bodegas.html
│   │       │   ├── productos.html
│   │       │   ├── movimientos.html
│   │       │   └── auditoria.html
│   │       ├── application.properties
│   │       ├── squema.sql          # Script de creación de BD
│   │       ├── data.sql            # Datos de prueba
│   │       └── info.md             # Información del proyecto
│   └── test/
│       └── java/com/logitrack/logitrack/
│           ├── LogitrackApplicationTests.java
│           └── EndpointsIntegrationTest.java
├── pom.xml                         # Configuración Maven
├── README.md                       # Este archivo
└── LICENSE                         # Licencia del proyecto
```

---

## 🧪 Testing

### Ejecutar tests
```bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests específicos
mvn test -Dtest=EndpointsIntegrationTest

# Con reporte de cobertura
mvn clean test jacoco:report
```

### Tests incluidos

- ✅ **Tests de integración** - Verifican endpoints completos
- ✅ **Tests de autenticación** - Login, registro, JWT
- ✅ **Tests de CRUD** - Productos, bodegas, inventarios
- ✅ **Tests de movimientos** - Entradas, salidas, transferencias
- ✅ **Tests de auditoría** - Registro automático de cambios

### Ver reporte de cobertura
```bash
# Generar reporte
mvn jacoco:report

# Ver en navegador (Linux/Mac)
open target/site/jacoco/index.html

# Ver en navegador (Windows)
start target/site/jacoco/index.html
```

---

## 🐳 Docker (Opcional)

### Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/logitrack-0.0.1-SNAPSHOT.war app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]
```

### docker-compose.yml
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    container_name: logitrack-mysql
    environment:
      MYSQL_ROOT_PASSWORD: rootpass
      MYSQL_DATABASE: logitrack_db
      MYSQL_USER: logitrack
      MYSQL_PASSWORD: logitrack123
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql
      - ./src/main/resources/squema.sql:/docker-entrypoint-initdb.d/1-schema.sql
      - ./src/main/resources/data.sql:/docker-entrypoint-initdb.d/2-data.sql
    networks:
      - logitrack-network
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost"]
      timeout: 20s
      retries: 10

  app:
    build: .
    container_name: logitrack-app
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/logitrack_db
      SPRING_DATASOURCE_USERNAME: logitrack
      SPRING_DATASOURCE_PASSWORD: logitrack123
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
    depends_on:
      mysql:
        condition: service_healthy
    networks:
      - logitrack-network

volumes:
  mysql_data:

networks:
  logitrack-network:
    driver: bridge
```

### Ejecutar con Docker
```bash
# Construir el proyecto
mvn clean package -DskipTests

# Iniciar servicios
docker-compose up -d

# Ver logs
docker-compose logs -f app

# Detener servicios
docker-compose down

# Detener y eliminar volúmenes
docker-compose down -v
```

---

## 🔧 Troubleshooting

### Problema: Error de conexión a MySQL
```
Solution:
1. Verificar que MySQL esté ejecutándose
2. Verificar credenciales en application.properties
3. Verificar que la base de datos exista
```

### Problema: Token JWT inválido
```
Solution:
1. Verificar que el token se envíe en el header Authorization
2. Formato: "Bearer {TOKEN}"
3. Verificar que el token no haya expirado (1 hora por defecto)
```

### Problema: Error 403 Forbidden
```
Solution:
1. Verificar que el usuario tenga el rol correcto
2. Verificar que el endpoint requiera el rol del usuario
3. Revisar SecurityConfig.java
```

### Problema: Frontend no carga recursos
```
Solution:
1. Verificar que los archivos estén en src/main/resources/static/
2. Limpiar y recompilar: mvn clean install
3. Verificar SecurityConfig permite acceso a recursos estáticos
```

---

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Por favor sigue estos pasos:

### 1. Fork el proyecto

### 2. Crea una rama para tu feature
```bash
git checkout -b feature/AmazingFeature
```

### 3. Commit tus cambios
```bash
git commit -m 'Add some AmazingFeature'
```

### 4. Push a la rama
```bash
git push origin feature/AmazingFeature
```

### 5. Abre un Pull Request

### Guías de estilo

- Seguir convenciones de Java (CamelCase, PascalCase)
- Documentar métodos públicos con JavaDoc
- Mantener cobertura de tests > 80%
- Commits descriptivos en español o inglés
- Código limpio y bien comentado

### Reporte de bugs

Si encuentras un bug, por favor crea un issue con:

- Descripción clara del problema
- Pasos para reproducirlo
- Comportamiento esperado vs comportamiento actual
- Screenshots si es posible
- Versión de Java, Spring Boot, MySQL

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.