# 🐾 Contrato REST de Lectura de Animales (Issue B01)

Este documento define el contrato de datos expuesto por el backend Spring Boot para la consulta del catálogo y la ficha de animales en Comunidad Mascotera Mendoza, consumido por el frontend (Next.js/React).

---

## 🏛️ 1. Justificación del Uso de DTOs

Anteriormente, `AnimalController` exponía directamente la entidad JPA `@Entity Animal`. Esto generaba los siguientes problemas:
1. **Acoplamiento de capas:** La estructura de persistencia (tablas de BD, relaciones JPA como `@ManyToOne` con anotaciones Jackson `@JsonBackReference`) determinaba directamente el formato JSON consumido por el cliente.
2. **Incompatibilidad con el Frontend:** El modelo cliente `Pet` requiere objetos anidados `health` (`HealthInfo`) y `compatibility` (`CompatibilityProfile`), mientras que la base de datos almacena los datos sanitarios y de convivencia como atributos planos en la tabla `animales`.
3. **Pérdida de datos del refugio:** `@JsonBackReference` en la relación con `Refugio` suprimía la serialización del refugio en el JSON, impidiendo al frontend conocer el `shelterId` asignado.

Con el patrón DTO (**Data Transfer Object**) implementado mediante `AnimalResponseDto`, `HealthInfoDto` y `CompatibilityDto`:
- Se desacopla la capa de persistencia de la capa web de presentación.
- Se proporciona una estructura uniforme y tipada que responde exactamente a las necesidades de la UI.
- No se expone información interna innecesaria ni se producen ciclos de serialización.

---

## 📡 2. Endpoints y Parámetros

### 2.1. Listado de Animales: `GET /api/animales`

Devuelve una colección de animales según los filtros opcionales aplicados.

#### Parámetros de Consulta (Query Params):
| Parámetro | Tipo | Descripción | Comportamiento |
| :--- | :--- | :--- | :--- |
| `urgente` | `Boolean` | Filtra animales con adopción urgente (`true`). | **Mayor prioridad:** Si se envía `urgente=true`, se evalúa primero retornando solo los animales urgentes. |
| `refugioId` | `String` | Identificador del refugio (ej. `angeles-cuatro-patas`). | Si no hay filtro urgente, filtra animales alojados en dicho refugio. |
| `especie` | `String` | Especie del animal (`perro` o `gato`). | Si no aplican los anteriores, filtra por especie ignorando mayúsculas/minúsculas (`perro`, `gato`, o `todos`). |
| *(sin params)* | - | Sin filtros. | Devuelve la lista completa de animales disponibles en adopción. |

---

### 2.2. Detalle de un Animal: `GET /api/animales/{id}`

Devuelve la ficha detallada de un animal específico.

#### Parámetros de Ruta (Path Variable):
| Parámetro | Tipo | Descripción |
| :--- | :--- | :--- |
| `id` | `String` | Identificador único del animal (ej: `pet-1`). |

#### Códigos de Respuesta HTTP:
- `200 OK`: Animal encontrado; cuerpo con el objeto JSON del animal.
- `404 Not Found`: No existe ningún animal con el identificador solicitado (cuerpo vacío).

---

## 📋 3. Formato de Respuesta JSON (Contrato Unificado)

Tanto el listado (`GET /api/animales`) como el detalle (`GET /api/animales/{id}`) utilizan **la misma estructura**:

```json
{
  "id": "pet-1",
  "name": "Pampa",
  "species": "perro",
  "gender": "hembra",
  "age": "1 año y 4 meses",
  "approxAgeMonths": 16,
  "size": "mediano",
  "status": "en_adopcion",
  "story": "Pampa fue rescatada en una acequia del carril Cervantes. Es la perrita más cariñosa del mundo.",
  "urgent": false,
  "timeInShelter": "3 meses",
  "photos": [
    "https://images.unsplash.com/photo-1543466835-00a7907e9de1?auto=format&fit=crop&w=800&q=80",
    "https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?auto=format&fit=crop&w=800&q=80"
  ],
  "traits": [
    "Súper cariñosa",
    "Sociable con perros",
    "Aprende rápido"
  ],
  "shelterId": "angeles-cuatro-patas",
  "health": {
    "vaccinated": true,
    "neutered": true,
    "dewormed": true,
    "microchip": false,
    "specialCare": null
  },
  "compatibility": {
    "apartment": true,
    "kids": true,
    "otherDogs": true,
    "cats": true,
    "energyLevel": "medio",
    "hoursAloneOk": "medias",
    "experienceRequired": false
  }
}
```

---

## 🤝 4. Acuerdo sobre Identificadores de Ejemplo (`pet-1` a `pet-4` vs `pet-6`)

### Situación Detectada:
- El inicializador del backend (`DataInitializer.java`) siembra **4 animales reales y validados** (`pet-1`, `pet-2`, `pet-3`, `pet-4`) vinculados a sus respectivos refugios.
- Los datos de prueba iniciales del frontend (`mockData.ts`) incluían 6 identificadores (`pet-1` a `pet-6`).

### Decisión Acordada con el Equipo Frontend:
1. **El backend mantiene su semillero oficial de 4 animales** (`pet-1` a `pet-4`), preservando la integridad de datos sin agregar entidades ficticias arbitrarias.
2. **El frontend (en la Issue F01) consumirá dinámicamente el listado del backend:**
   - La pantalla de catálogo renderizará los animales reales devueltos por `GET /api/animales`.
   - Si un usuario ingresa directamente por URL a un ID no persistido en el backend (por ejemplo `/mascotas/pet-5` o `/mascotas/pet-6`), el backend responderá legítimamente `404 Not Found`.
   - El frontend mostrará adecuadamente el estado visible de "Animal no encontrado" en lugar de fallar o simular un éxito inexistente.
