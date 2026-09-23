# ☕ Comunidad Mascotera Mendoza — Backend API (Java Spring Boot)

API RESTful y lógica de negocio para la plataforma de adopción responsable, seguimiento post-adopción con gamificación ("Álbum de Vida"), donaciones directas y red de zoonosis en Mendoza, Argentina.

> 🎓 **Proyecto Universitario / Académico (Programación 3 / Backend):**  
> Desarrollado bajo una arquitectura desacoplada (*Decoupled Backend Architecture*), aplicando principios de **Diseño Orientado a Objetos (POO)**, patrones de diseño por capas (*Layered Architecture*) y buenas prácticas de la industria.  
> La interfaz de usuario cliente se encuentra en el repositorio hermano:  
> 👉 **[comunidad-mascotera-frontend](https://github.com/TU_USUARIO/comunidad-mascotera-frontend)** (Next.js 14+ / React 19).

---

## 🚀 Stack Tecnológico

- **Lenguaje:** Java (compatible con Java 21 LTS y Java 24)
- **Framework:** [Spring Boot 3.4.3](https://spring.io/projects/spring-boot)
  - `spring-boot-starter-web` (Controladores REST y configuración HTTP)
  - `spring-boot-starter-data-jpa` (Persistencia relacional con Hibernate)
  - `spring-boot-starter-validation` (Validación de DTOs y peticiones)
- **Base de Datos:** H2 Database (In-Memory relacional con consola web interactiva)
- **Gestor de Construcción:** Maven con Maven Wrapper (`mvnw` / `mvnw.cmd`)

---

## 🏛️ Arquitectura del Sistema (Capas)

El proyecto sigue la separación estricta de responsabilidades:

```text
com.comunidadmascotera/
├── config/              # Configuración global (CORS, Beans, etc.)
│   └── CorsConfig.java
├── controllers/         # Capa de Entrada / Controladores REST (@RestController)
│   ├── AnimalController.java
│   ├── RefugioController.java
│   ├── SolicitudAdopcionController.java
│   ├── SeguimientoController.java
│   ├── AvisoZoonosisController.java
│   └── PatrocinadorController.java
├── services/            # Capa de Negocio y Lógica (@Service, @Transactional)
│   ├── AnimalService.java          # Incluye algoritmo de Match de compatibilidad
│   ├── RefugioService.java
│   ├── SolicitudAdopcionService.java
│   ├── SeguimientoService.java     # Actualiza medallas y semáforo del refugio
│   ├── AvisoZoonosisService.java
│   └── PatrocinadorService.java
├── repositories/        # Capa de Persistencia (@Repository / JpaRepository)
│   ├── AnimalRepository.java
│   ├── RefugioRepository.java
│   ├── SolicitudAdopcionRepository.java
│   ├── SeguimientoMascotaRepository.java
│   ├── HitoSeguimientoRepository.java
│   ├── AvisoZoonosisRepository.java
│   └── PatrocinadorRepository.java
├── entities/            # Modelo de Dominio Relacional JPA (@Entity, @Table)
│   ├── Animal.java
│   ├── Refugio.java
│   ├── NecesidadRefugio.java
│   ├── SolicitudAdopcion.java
│   ├── SeguimientoMascota.java
│   ├── HitoSeguimiento.java
│   ├── AvisoZoonosis.java
│   └── Patrocinador.java
└── init/                # Semillero de datos iniciales realistas de Mendoza
    └── DataInitializer.java (CommandLineRunner)
```

---

## 📡 Endpoints de la API REST

Todos los endpoints responden en formato JSON y están expuestos bajo el prefijo `/api`:

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/animales` | Listado de animales (filtros: `?especie=`, `?refugioId=`, `?urgente=`) |
| `GET` | `/api/animales/{id}` | Detalle completo de una mascota y su historia |
| `POST` | `/api/animales/test-match` | Algoritmo de afinidad: evalúa estilo de vida y retorna animales compatibles |
| `GET` | `/api/refugios` | Directorio de refugios con datos bancarios directos para donación |
| `GET` | `/api/refugios/{id}` | Perfil del refugio, censo de animales y lista de necesidades |
| `POST` | `/api/adopciones/postular/{animalId}` | Envío de postulación responsable con DNI y teléfono |
| `GET` | `/api/seguimiento` | Lista de seguimientos activos (Álbum de Vida) |
| `POST` | `/api/seguimiento/hitos/{id}/completar` | Carga de foto/reporte periódico: desbloquea medalla y pone semáforo en verde |
| `GET` | `/api/zoonosis` | Campañas municipales de castración, móvil veterinario y eventos |
| `GET` | `/api/patrocinadores` | Alianzas con veterinarias, municipios y empresas |

---

## 🗄️ Consola Web de Base de Datos H2

Para inspeccionar las tablas relacionales y registros generados por Hibernate en tiempo real:

1. Iniciar la aplicación.
2. Abrir en el navegador: **[http://localhost:8080/h2-console](http://localhost:8080/h2-console)**
3. Ingresar las siguientes credenciales:
   - **JDBC URL:** `jdbc:h2:mem:comunidad_mascotera_db`
   - **User Name:** `sa`
   - **Password:** *(dejar en blanco)*
4. Presionar **Connect** y ejecutar consultas SQL estándar como:
   ```sql
   SELECT * FROM ANIMALES;
   SELECT * FROM REFUGIOS;
   SELECT * FROM HITOS_SEGUIMIENTO;
   ```

---

## 🛠️ Instalación y Ejecución

### Prerrequisitos
- **Java JDK:** Versión 17, 21 o 24 instalada (`java -version`).
- No es necesario tener Maven instalado de forma global; el proyecto incluye el ejecutable `mvnw`.

### Pasos

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/TU_USUARIO/comunidad-mascotera-backend.git
   cd comunidad-mascotera-backend
   ```

2. **Compilar el proyecto:**
   - **Windows:**
     ```powershell
     .\mvnw.cmd clean compile
     ```
   - **Linux / macOS:**
     ```bash
     chmod +x mvnw
     ./mvnw clean compile
     ```

3. **Iniciar el servidor Spring Boot:**
   - **Windows:**
     ```powershell
     .\mvnw.cmd spring-boot:run
     ```
   - **Linux / macOS:**
     ```bash
     ./mvnw spring-boot:run
     ```

4. El servidor estará escuchando en **`http://localhost:8080`**.

---

## 🎓 Aspectos Destacados para la Defensa Universitaria

1. **Arquitectura Limpia POO:** Separación clara entre Controlador, Servicio, Repositorio y Entidad.
2. **Sin dependencias frágiles:** Se utilizan getters, setters y constructores nativos de Java, garantizando compilación sin errores en cualquier versión de JDK (incluyendo Java 24).
3. **Persistencia Transaccional:** Uso de `@Transactional` en la capa de servicios para asegurar atomicidad al actualizar hitos y estados de seguimiento.
4. **CORS Centralizado:** Configuración moderna en `CorsConfig` que permite peticiones seguras desde el frontend en Next.js.
