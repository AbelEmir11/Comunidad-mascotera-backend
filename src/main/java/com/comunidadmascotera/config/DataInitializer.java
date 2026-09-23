package com.comunidadmascotera.config;

import com.comunidadmascotera.entities.*;
import com.comunidadmascotera.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(
            RefugioRepository refugioRepository,
            AnimalRepository animalRepository,
            AvisoZoonosisRepository avisoRepository,
            PatrocinadorRepository patrocinadorRepository,
            SeguimientoMascotaRepository seguimientoRepository) {

        return args -> {
            if (refugioRepository.count() > 0) {
                return; // Ya inicializado
            }

            // 1. Refugios de Mendoza
            Refugio r1 = new Refugio(
                    "angeles-cuatro-patas",
                    "Refugio Ángeles de Cuatro Patas",
                    "Rescate, rehabilitación y amor en el pedemonte mendocino.",
                    "https://images.unsplash.com/photo-1534361960057-19889db9621e?auto=format&fit=crop&w=200&q=80",
                    "https://images.unsplash.com/photo-1548199973-03cce0bbc87b?auto=format&fit=crop&w=1200&q=80",
                    "Godoy Cruz / Luján de Cuyo, Mendoza",
                    "Godoy Cruz",
                    "Espacio sin fines de lucro fundado hace 8 años para rescatar animales de la calle, víctimas de maltrato o abandono severo.",
                    "Comenzamos con un patio familiar y hoy cobijamos a más de 35 animales que recuperan su peso, salud y confianza antes de encontrar un hogar definitivo.",
                    "refugio.angeles.mza",
                    "0110034530000123456789",
                    "Banco Nación / Mercado Pago Directo",
                    "Asociación Civil Ángeles de 4 Patas",
                    "30-71569842-4",
                    "+54 9 261 555-4321",
                    "5492615554321",
                    "angelescuatropatas.mza",
                    "contacto@angeles4patas.org.ar",
                    26,
                    312
            );
            r1.getNeeds().add(new NecesidadRefugio("Alimento Balanceado Adulto (Bolsas 15kg)", 25, 16, "bolsas", "alta", r1));
            r1.getNeeds().add(new NecesidadRefugio("Pipetas Antipulgas (Perros 10-25kg)", 30, 8, "unidades", "alta", r1));
            r1.getNeeds().add(new NecesidadRefugio("Mantas y Toallones de abrigo", 20, 14, "unidades", "media", r1));

            Refugio r2 = new Refugio(
                    "hogar-felino-mendoza",
                    "Hogar Felino Mendoza & Tránsitos",
                    "Cuidado especializado, castraciones y adopciones de gatitos rescatados.",
                    "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?auto=format&fit=crop&w=200&q=80",
                    "https://images.unsplash.com/photo-1543852786-1cf6624b9987?auto=format&fit=crop&w=1200&q=80",
                    "Ciudad de Mendoza (Capital)",
                    "Capital",
                    "Red comunitaria de hogares de tránsito coordinada para rescatar camadas abandonadas y atender felinos.",
                    "Creemos en la importancia de educar sobre la tenencia en departamentos y balcones con red.",
                    "hogar.felino.cuyo",
                    "0720123988000034928172",
                    "Banco Santander",
                    "Red Hogar Felino Mendoza",
                    "27-33829104-7",
                    "+54 9 261 498-1122",
                    "5492614981122",
                    "hogarfelino.mza",
                    "adopciones@hogarfelinomza.com",
                    18,
                    195
            );
            r2.getNeeds().add(new NecesidadRefugio("Alimento Kitten / Cachorros Gato (Bolsas 7.5kg)", 12, 4, "bolsas", "alta", r2));
            r2.getNeeds().add(new NecesidadRefugio("Piedritas Sanitarias Absorbentes (Bolsas)", 40, 22, "bolsas", "media", r2));

            Refugio r3 = new Refugio(
                    "patitas-del-pedemonte",
                    "Patitas del Pedemonte",
                    "Refugio abierto y rescate de animales en zonas rurales y pedemonte.",
                    "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?auto=format&fit=crop&w=200&q=80",
                    "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?auto=format&fit=crop&w=1200&q=80",
                    "Las Compuertas, Luján de Cuyo, Mendoza",
                    "Luján de Cuyo",
                    "Refugio amplio rodeado de naturaleza enfocado en la recuperación de perros de tamaño mediano y grande.",
                    "Muchos de nuestros perros provienen de puestos rurales y senderos de montaña donde fueron abandonados.",
                    "patitas.pedemonte.ok",
                    "0290001310000045819203",
                    "Banco Ciudad / Ualá",
                    "Patitas del Pedemonte ONG",
                    "33-71829301-9",
                    "+54 9 261 672-9900",
                    "5492616729900",
                    "patitas.pedemonte",
                    "info@patitasdelpedemonte.org",
                    32,
                    420
            );
            r3.getNeeds().add(new NecesidadRefugio("Alimento Balanceado Criadores (Bolsas 20kg)", 30, 11, "bolsas", "alta", r3));
            r3.getNeeds().add(new NecesidadRefugio("Vacunas Óctuples (Dosis)", 15, 3, "dosis", "alta", r3));

            refugioRepository.saveAll(List.of(r1, r2, r3));

            // 2. Animales
            Animal a1 = new Animal();
            a1.setId("pet-1");
            a1.setName("Pampa");
            a1.setSpecies("perro");
            a1.setGender("hembra");
            a1.setAge("1 año y 4 meses");
            a1.setApproxAgeMonths(16);
            a1.setSize("mediano");
            a1.setStatus("en_adopcion");
            a1.setStory("Pampa fue rescatada en una acequia del carril Cervantes. Es la perrita más cariñosa del mundo.");
            a1.setUrgent(false);
            a1.setTimeInShelter("3 meses");
            a1.setPhotos(List.of("https://images.unsplash.com/photo-1543466835-00a7907e9de1?auto=format&fit=crop&w=800&q=80", "https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?auto=format&fit=crop&w=800&q=80"));
            a1.setTraits(List.of("Súper cariñosa", "Sociable con perros", "Aprende rápido"));
            a1.setVaccinated(true);
            a1.setNeutered(true);
            a1.setDewormed(true);
            a1.setApartment(true);
            a1.setKids(true);
            a1.setOtherDogs(true);
            a1.setCats(true);
            a1.setEnergyLevel("medio");
            a1.setHoursAloneOk("medias");
            a1.setRefugio(r1);

            Animal a2 = new Animal();
            a2.setId("pet-2");
            a2.setName("Milo");
            a2.setSpecies("gato");
            a2.setGender("macho");
            a2.setAge("8 meses");
            a2.setApproxAgeMonths(8);
            a2.setSize("pequeño");
            a2.setStatus("en_adopcion");
            a2.setStory("Milo nació en un jardín en la Quinta Sección. Es un ronroneador profesional.");
            a2.setUrgent(false);
            a2.setTimeInShelter("2 meses");
            a2.setPhotos(List.of("https://images.unsplash.com/photo-1518791841217-8f162f1e1131?auto=format&fit=crop&w=800&q=80"));
            a2.setTraits(List.of("Curioso", "Ronroneador", "Ideal departamento"));
            a2.setVaccinated(true);
            a2.setNeutered(true);
            a2.setDewormed(true);
            a2.setApartment(true);
            a2.setKids(true);
            a2.setOtherDogs(false);
            a2.setCats(true);
            a2.setEnergyLevel("bajo");
            a2.setHoursAloneOk("muchas");
            a2.setRefugio(r2);

            Animal a3 = new Animal();
            a3.setId("pet-3");
            a3.setName("Ciro");
            a3.setSpecies("perro");
            a3.setGender("macho");
            a3.setAge("2 años");
            a3.setApproxAgeMonths(24);
            a3.setSize("grande");
            a3.setStatus("en_adopcion");
            a3.setStory("Ciro es un compañero leal y noble. Necesita casa con patio cerrado.");
            a3.setUrgent(true);
            a3.setTimeInShelter("6 meses");
            a3.setPhotos(List.of("https://images.unsplash.com/photo-1552053831-71594a27632d?auto=format&fit=crop&w=800&q=80"));
            a3.setTraits(List.of("Guardián noble", "Muy activo", "Fiel"));
            a3.setVaccinated(true);
            a3.setNeutered(true);
            a3.setDewormed(true);
            a3.setApartment(false);
            a3.setKids(true);
            a3.setOtherDogs(true);
            a3.setCats(false);
            a3.setEnergyLevel("alto");
            a3.setHoursAloneOk("medias");
            a3.setRefugio(r3);

            Animal a4 = new Animal();
            a4.setId("pet-4");
            a4.setName("Luna");
            a4.setSpecies("perro");
            a4.setGender("hembra");
            a4.setAge("3 años");
            a4.setApproxAgeMonths(36);
            a4.setSize("pequeño");
            a4.setStatus("en_adopcion");
            a4.setStory("Luna pesa solo 7kg. Es tranquila, ideal para departamentos.");
            a4.setUrgent(false);
            a4.setTimeInShelter("1 mes");
            a4.setPhotos(List.of("https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?auto=format&fit=crop&w=800&q=80"));
            a4.setTraits(List.of("Tranquila", "Ama los sillones", "Pasea sin tirar"));
            a4.setVaccinated(true);
            a4.setNeutered(true);
            a4.setDewormed(true);
            a4.setApartment(true);
            a4.setKids(true);
            a4.setOtherDogs(true);
            a4.setCats(true);
            a4.setEnergyLevel("bajo");
            a4.setHoursAloneOk("muchas");
            a4.setRefugio(r1);

            animalRepository.saveAll(List.of(a1, a2, a3, a4));

            // 3. Avisos de Zoonosis y Campañas
            AvisoZoonosis z1 = new AvisoZoonosis();
            z1.setId("notice-1");
            z1.setType("movil_zoonosis");
            z1.setTitle("Móvil Veterinario en Godoy Cruz: Castraciones y Vacunación Antirrábica");
            z1.setSubtitle("Atención gratuita por orden de llegada con DNI en el departamento.");
            z1.setHighlightBadge("Móvil Zoonosis Gratis");
            z1.setLocation("Polideportivo N° 1 Biritos, Godoy Cruz");
            z1.setMunicipality("Godoy Cruz");
            z1.setDate("Lunes a Viernes de 8:30 a 12:30 hs");
            z1.setActionText("Ver requisitos de ayuno y turnos");
            z1.setActionUrl("/zoonosis");
            z1.setImage("https://images.unsplash.com/photo-1583337130417-3346a1be7dee?auto=format&fit=crop&w=800&q=80");
            z1.setUrgent(true);

            AvisoZoonosis z2 = new AvisoZoonosis();
            z2.setId("notice-2");
            z2.setType("meta_lograda");
            z2.setTitle("¡Meta Cumplida! Refugio Ángeles logró juntar 60 bolsas de alimento");
            z2.setSubtitle("Gracias a 180 vecinos y empresas aliadas de Mendoza.");
            z2.setHighlightBadge("Festejo Comunitario 🎉");
            z2.setLocation("Sede Pedemonte, Luján de Cuyo");
            z2.setDate("Actualizado esta semana");
            z2.setActionText("Ver resumen de agradecimiento");
            z2.setActionUrl("/refugios/angeles-cuatro-patas");
            z2.setImage("https://images.unsplash.com/photo-1548767797-d8c844163c4c?auto=format&fit=crop&w=800&q=80");
            z2.setUrgent(false);

            avisoRepository.saveAll(List.of(z1, z2));

            // 4. Patrocinadores
            Patrocinador p1 = new Patrocinador();
            p1.setId("sp-godoy-cruz");
            p1.setName("Municipalidad de Godoy Cruz");
            p1.setCategory("municipio");
            p1.setTier("platino");
            p1.setLogo("https://images.unsplash.com/photo-1577495508048-b635879837f1?auto=format&fit=crop&w=200&q=80");
            p1.setDescription("Dirección de Salud y Zoonosis. Compromiso con la esterilización quirúrgica gratuita y la tenencia responsable.");
            p1.setLocation("Godoy Cruz, Mendoza");
            p1.setSupportType("Apoyo institucional, móviles de castración y vacunación.");
            p1.setBenefitForAdopters("Vacunación antirrábica y desparasitación gratuita para adoptantes.");
            p1.setContactUrl("https://www.godoycruz.gob.ar");

            Patrocinador p2 = new Patrocinador();
            p2.setId("sp-vet-cordillera");
            p2.setName("Clínica Veterinaria Cordillera");
            p2.setCategory("veterinaria");
            p2.setTier("oro");
            p2.setLogo("https://images.unsplash.com/photo-1576201836106-db1758fd1c97?auto=format&fit=crop&w=200&q=80");
            p2.setDescription("Atención 24hs, cirugía de alta complejidad y diagnóstico por imágenes.");
            p2.setLocation("Av. San Martín 1420, Godoy Cruz");
            p2.setSupportType("Atención médica prioritaria a costo de rescate para refugios.");
            p2.setBenefitForAdopters("20% de descuento en la 1° consulta médica y 15% en vacunas anuales.");
            p2.setContactUrl("#vet-cordillera");

            patrocinadorRepository.saveAll(List.of(p1, p2));

            // 5. Seguimiento Post-Adopción (Álbum de Vida con Semáforo)
            SeguimientoMascota s1 = new SeguimientoMascota();
            s1.setId("fol-1");
            s1.setPetId("pet-adoptada-1");
            s1.setPetName("Bella");
            s1.setPetPhoto("https://images.unsplash.com/photo-1561037404-61cd46aa615b?auto=format&fit=crop&w=400&q=80");
            s1.setSpecies("perro");
            s1.setShelterId("angeles-cuatro-patas");
            s1.setShelterName("Refugio Ángeles de Cuatro Patas");
            s1.setAdopterName("Mariana Gomez");
            s1.setAdopterLocation("Godoy Cruz, Mendoza");
            s1.setAdoptionDate("15 de Agosto de 2026");
            s1.setHealthTrafficLight("verde");
            s1.setLastUpdateDate("Hace 3 días");
            s1.setNextDueDate("15 de Octubre de 2026");

            HitoSeguimiento h1 = new HitoSeguimiento();
            h1.setId("m1");
            h1.setPeriod("Día 15");
            h1.setTitle("Primeros pasos en el hogar");
            h1.setDescription("Subir foto del espacio donde duerme.");
            h1.setDueDate("30 de Agosto de 2026");
            h1.setStatus("completado");
            h1.setCompletedAt("28 de Agosto de 2026");
            h1.setPhotoUrl("https://images.unsplash.com/photo-1548199973-03cce0bbc87b?auto=format&fit=crop&w=600&q=80");
            h1.setNotes("¡Bella ya se apropió del almohadón del living!");
            h1.setBadgeName("Bienvenido al Hogar");
            h1.setBadgeIcon("🏠");
            h1.setBadgeDescription("Primeros 15 días con éxito");
            h1.setSeguimiento(s1);

            HitoSeguimiento h2 = new HitoSeguimiento();
            h2.setId("m2");
            h2.setPeriod("Mes 1");
            h2.setTitle("El rey del sillón");
            h2.setDescription("Foto disfrutando de su rincón favorito.");
            h2.setDueDate("15 de Septiembre de 2026");
            h2.setStatus("completado");
            h2.setCompletedAt("16 de Septiembre de 2026");
            h2.setPhotoUrl("https://images.unsplash.com/photo-1561037404-61cd46aa615b?auto=format&fit=crop&w=600&q=80");
            h2.setNotes("Paseamos todos los días por el Parque San Vicente.");
            h2.setBadgeName("Rey del Sillón");
            h2.setBadgeIcon("🛋️");
            h2.setBadgeDescription("1 mes de adaptación");
            h2.setSeguimiento(s1);

            HitoSeguimiento h3 = new HitoSeguimiento();
            h3.setId("m3");
            h3.setPeriod("Mes 2");
            h3.setTitle("Control Veterinario y Refuerzos");
            h3.setDescription("Foto de la libreta de vacunación o visita de control.");
            h3.setDueDate("15 de Octubre de 2026");
            h3.setStatus("pendiente");
            h3.setBadgeName("Salud de Hierro");
            h3.setBadgeIcon("💉");
            h3.setBadgeDescription("Controles médicos al día");
            h3.setSeguimiento(s1);

            s1.getMilestones().addAll(List.of(h1, h2, h3));
            seguimientoRepository.save(s1);
        };
    }
}
