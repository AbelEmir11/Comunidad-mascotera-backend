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
            // 1. Refugios de Mendoza
            Refugio r1 = refugioRepository.findById("angeles-cuatro-patas").orElse(null);
            Refugio r2 = refugioRepository.findById("hogar-felino-mendoza").orElse(null);
            Refugio r3 = refugioRepository.findById("patitas-del-pedemonte").orElse(null);

            if (r1 == null || r2 == null || r3 == null) {
                r1 = new Refugio(
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
                        "+54 9 261 367-0507",
                        "5492613670507",
                        "angelescuatropatas.mza",
                        "contacto@angeles4patas.org.ar",
                        26,
                        312
                );
                r1.getNeeds().add(new NecesidadRefugio("Alimento Balanceado Adulto (Bolsas 15kg)", 25, 16, "bolsas", "alta", r1));
                r1.getNeeds().add(new NecesidadRefugio("Pipetas Antipulgas (Perros 10-25kg)", 30, 8, "unidades", "alta", r1));
                r1.getNeeds().add(new NecesidadRefugio("Mantas y Toallones de abrigo", 20, 14, "unidades", "media", r1));

                r2 = new Refugio(
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
                        "+54 9 261 367-0507",
                        "5492613670507",
                        "hogarfelino.mza",
                        "adopciones@hogarfelinomza.com",
                        18,
                        195
                );
                r2.getNeeds().add(new NecesidadRefugio("Alimento Kitten / Cachorros Gato (Bolsas 7.5kg)", 12, 4, "bolsas", "alta", r2));
                r2.getNeeds().add(new NecesidadRefugio("Piedritas Sanitarias Absorbentes (Bolsas)", 40, 22, "bolsas", "media", r2));

                r3 = new Refugio(
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
                        "+54 9 261 367-0507",
                        "5492613670507",
                        "patitas.pedemonte",
                        "info@patitasdelpedemonte.org",
                        32,
                        420
                );
                r3.getNeeds().add(new NecesidadRefugio("Alimento Balanceado Criadores (Bolsas 20kg)", 30, 11, "bolsas", "alta", r3));
                r3.getNeeds().add(new NecesidadRefugio("Vacunas Óctuples (Dosis)", 15, 3, "dosis", "alta", r3));

                refugioRepository.saveAll(List.of(r1, r2, r3));
            } else {
                // Actualizar telefonos de contacto del tutor
                r1.setPhone("+54 9 261 367-0507");
                r1.setWhatsapp("5492613670507");
                r2.setPhone("+54 9 261 367-0507");
                r2.setWhatsapp("5492613670507");
                r3.setPhone("+54 9 261 367-0507");
                r3.setWhatsapp("5492613670507");
                refugioRepository.saveAll(List.of(r1, r2, r3));
            }

            // 2. Animales base (si no existen)
            if (!animalRepository.existsById("pet-1")) {
                Animal a1 = new Animal();
                a1.setId("pet-1");
                a1.setName("Pampa");
                a1.setSpecies("perro");
                a1.setGender("hembra");
                a1.setAge("1 año y 4 meses");
                a1.setApproxAgeMonths(16);
                a1.setSize("mediano");
                a1.setStatus("en_adopcion");
                a1.setStory("Pampa fue rescatada en una acequia del carril Cervantes. Estaba asustada, pero hoy es la perrita más agradecida y mimosa del mundo.");
                a1.setUrgent(false);
                a1.setTimeInShelter("3 meses");
                a1.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1543466835-00a7907e9de1?auto=format&fit=crop&w=800&q=80",
                        "https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?auto=format&fit=crop&w=800&q=80"
                ));
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
                animalRepository.save(a1);
            }

            if (!animalRepository.existsById("pet-2")) {
                Animal a2 = new Animal();
                a2.setId("pet-2");
                a2.setName("Milo");
                a2.setSpecies("gato");
                a2.setGender("macho");
                a2.setAge("8 meses");
                a2.setApproxAgeMonths(8);
                a2.setSize("pequeño");
                a2.setStatus("en_adopcion");
                a2.setStory("Milo nació en un jardín en la Quinta Sección. Es un ronroneador profesional y compañero ideal de departamento.");
                a2.setUrgent(false);
                a2.setTimeInShelter("2 meses");
                a2.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?auto=format&fit=crop&w=800&q=80"
                ));
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
                animalRepository.save(a2);
            }

            if (!animalRepository.existsById("pet-3")) {
                Animal a3 = new Animal();
                a3.setId("pet-3");
                a3.setName("Ciro");
                a3.setSpecies("perro");
                a3.setGender("macho");
                a3.setAge("2 años");
                a3.setApproxAgeMonths(24);
                a3.setSize("grande");
                a3.setStatus("en_adopcion");
                a3.setStory("Ciro es un compañero leal y noble con energía para salir a caminar por el parque y cuidar el hogar. Necesita casa con patio.");
                a3.setUrgent(true);
                a3.setTimeInShelter("6 meses");
                a3.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1552053831-71594a27632d?auto=format&fit=crop&w=800&q=80"
                ));
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
                animalRepository.save(a3);
            }

            if (!animalRepository.existsById("pet-4")) {
                Animal a4 = new Animal();
                a4.setId("pet-4");
                a4.setName("Luna");
                a4.setSpecies("perro");
                a4.setGender("hembra");
                a4.setAge("3 años");
                a4.setApproxAgeMonths(36);
                a4.setSize("pequeño");
                a4.setStatus("en_adopcion");
                a4.setStory("Luna pesa solo 7kg. Es tranquila, súper mimosita y le encanta dormir la siesta sobre mantas.");
                a4.setUrgent(false);
                a4.setTimeInShelter("1 mes");
                a4.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?auto=format&fit=crop&w=800&q=80"
                ));
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
                animalRepository.save(a4);
            }

            // Nuevos animales rescatados reales de Mendoza (pet-5 a pet-10)
            if (!animalRepository.existsById("pet-5")) {
                Animal a5 = new Animal();
                a5.setId("pet-5");
                a5.setName("Negro");
                a5.setSpecies("perro");
                a5.setGender("macho");
                a5.setAge("2 años");
                a5.setApproxAgeMonths(24);
                a5.setSize("mediano");
                a5.setStatus("en_adopcion");
                a5.setStory("El Negro fue rescatado en una parada de micro en Las Heras donde esperaba que alguien le compartiera comida. Es un criollo puro corazón, agradecido y muy obediente.");
                a5.setUrgent(true);
                a5.setTimeInShelter("4 meses");
                a5.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1587300003388-59208cc962cb?auto=format&fit=crop&w=800&q=80"
                ));
                a5.setTraits(List.of("Compañero fiel", "Súper agradecido", "Camina al lado"));
                a5.setVaccinated(true);
                a5.setNeutered(true);
                a5.setDewormed(true);
                a5.setApartment(true);
                a5.setKids(true);
                a5.setOtherDogs(true);
                a5.setCats(true);
                a5.setEnergyLevel("medio");
                a5.setHoursAloneOk("muchas");
                a5.setRefugio(r1);
                animalRepository.save(a5);
            }

            if (!animalRepository.existsById("pet-6")) {
                Animal a6 = new Animal();
                a6.setId("pet-6");
                a6.setName("Choco");
                a6.setSpecies("perro");
                a6.setGender("macho");
                a6.setAge("5 meses");
                a6.setApproxAgeMonths(5);
                a6.setSize("mediano");
                a6.setStatus("en_adopcion");
                a6.setStory("Cachorrito rescatado en senderos de Luján de Cuyo. Es pura ternura, le encanta corretear en el pasto y llenarte de besos.");
                a6.setUrgent(true);
                a6.setTimeInShelter("1 mes");
                a6.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1591160690555-5debfba289f0?auto=format&fit=crop&w=800&q=80"
                ));
                a6.setTraits(List.of("Cachorro juguetón", "Alegre", "Sociable"));
                a6.setVaccinated(true);
                a6.setNeutered(false);
                a6.setDewormed(true);
                a6.setApartment(true);
                a6.setKids(true);
                a6.setOtherDogs(true);
                a6.setCats(true);
                a6.setEnergyLevel("alto");
                a6.setHoursAloneOk("pocas");
                a6.setRefugio(r3);
                animalRepository.save(a6);
            }

            if (!animalRepository.existsById("pet-7")) {
                Animal a7 = new Animal();
                a7.setId("pet-7");
                a7.setName("Brisa");
                a7.setSpecies("perro");
                a7.setGender("hembra");
                a7.setAge("1 año");
                a7.setApproxAgeMonths(12);
                a7.setSize("mediano");
                a7.setStatus("en_adopcion");
                a7.setStory("Brisa es una mestiza barbincha simpatiquísima rescatada en Maipú. Tiene una mirada alegre, aprende trucos rapidísimo y le encanta estar con niños.");
                a7.setUrgent(false);
                a7.setTimeInShelter("2 meses");
                a7.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1561037404-61cd46aa615b?auto=format&fit=crop&w=800&q=80"
                ));
                a7.setTraits(List.of("Barbincha alegre", "Le encantan los chicos", "Muy sociable"));
                a7.setVaccinated(true);
                a7.setNeutered(true);
                a7.setDewormed(true);
                a7.setApartment(true);
                a7.setKids(true);
                a7.setOtherDogs(true);
                a7.setCats(true);
                a7.setEnergyLevel("medio");
                a7.setHoursAloneOk("medias");
                a7.setRefugio(r1);
                animalRepository.save(a7);
            }

            if (!animalRepository.existsById("pet-8")) {
                Animal a8 = new Animal();
                a8.setId("pet-8");
                a8.setName("Simba");
                a8.setSpecies("gato");
                a8.setGender("macho");
                a8.setAge("1 año");
                a8.setApproxAgeMonths(12);
                a8.setSize("mediano");
                a8.setStatus("en_adopcion");
                a8.setStory("Simba es un gato naranja rescatado de un techo en Guaymallén. Es tranquilo, ama sentarse en la ventana a mirar los árboles y dormir siestas al sol.");
                a8.setUrgent(false);
                a8.setTimeInShelter("3 meses");
                a8.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1574158622682-e40e69881006?auto=format&fit=crop&w=800&q=80"
                ));
                a8.setTraits(List.of("Gato naranja mimoso", "Dormilón", "Limpio"));
                a8.setVaccinated(true);
                a8.setNeutered(true);
                a8.setDewormed(true);
                a8.setApartment(true);
                a8.setKids(true);
                a8.setOtherDogs(false);
                a8.setCats(true);
                a8.setEnergyLevel("bajo");
                a8.setHoursAloneOk("muchas");
                a8.setRefugio(r2);
                animalRepository.save(a8);
            }

            if (!animalRepository.existsById("pet-9")) {
                Animal a9 = new Animal();
                a9.setId("pet-9");
                a9.setName("Tito");
                a9.setSpecies("perro");
                a9.setGender("macho");
                a9.setAge("7 años");
                a9.setApproxAgeMonths(84);
                a9.setSize("grande");
                a9.setStatus("en_adopcion");
                a9.setStory("Tito es un señor perro rescatado en San Martín. Es súper tranquilo, camina despacio y solo busca un rincón calentito donde recibir caricias y pasar su madurez feliz.");
                a9.setUrgent(true);
                a9.setTimeInShelter("8 meses");
                a9.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1544568100-847a948585b9?auto=format&fit=crop&w=800&q=80"
                ));
                a9.setTraits(List.of("Señor maduro", "Súper tranquilo", "Paseos lentos", "No ladra"));
                a9.setVaccinated(true);
                a9.setNeutered(true);
                a9.setDewormed(true);
                a9.setApartment(true);
                a9.setKids(true);
                a9.setOtherDogs(true);
                a9.setCats(true);
                a9.setEnergyLevel("bajo");
                a9.setHoursAloneOk("muchas");
                a9.setRefugio(r3);
                animalRepository.save(a9);
            }

            if (!animalRepository.existsById("pet-10")) {
                Animal a10 = new Animal();
                a10.setId("pet-10");
                a10.setName("Pelusa");
                a10.setSpecies("gato");
                a10.setGender("hembra");
                a10.setAge("2 años");
                a10.setApproxAgeMonths(24);
                a10.setSize("pequeño");
                a10.setStatus("en_adopcion");
                a10.setStory("Pelusa es una gatita carey rescatada de la Peatonal Sarmiento. Es muy suave, silenciosa y se acuesta en tu falda en cuanto te sentás.");
                a10.setUrgent(false);
                a10.setTimeInShelter("2 meses");
                a10.setPhotos(List.of(
                        "https://images.unsplash.com/photo-1573865526739-10659fec78a5?auto=format&fit=crop&w=800&q=80"
                ));
                a10.setTraits(List.of("Gata faldera", "Tranquila", "Ronroneo suave"));
                a10.setVaccinated(true);
                a10.setNeutered(true);
                a10.setDewormed(true);
                a10.setApartment(true);
                a10.setKids(true);
                a10.setOtherDogs(false);
                a10.setCats(true);
                a10.setEnergyLevel("bajo");
                a10.setHoursAloneOk("muchas");
                a10.setRefugio(r2);
                animalRepository.save(a10);
            }

            // 3. Avisos Zoonosis (si no existen)
            if (avisoRepository.count() == 0) {
                AvisoZoonosis az1 = new AvisoZoonosis();
                az1.setType("movil_zoonosis");
                az1.setTitle("Móvil Veterinario en Godoy Cruz: Castraciones y Vacunación Antirrábica");
                az1.setSubtitle("Atención gratuita por orden de llegada con DNI que acredite domicilio en el departamento.");
                az1.setHighlightBadge("Móvil Zoonosis Gratis");
                az1.setLocation("Polideportivo Nº 1 Biritos, Godoy Cruz");
                az1.setMunicipality("Godoy Cruz");
                az1.setDate("Lunes a Viernes de 8:30 a 12:30 hs");
                az1.setActionText("Ver requisitos de ayuno y turnos");
                az1.setActionUrl("#movil-info");
                az1.setImage("https://images.unsplash.com/photo-1583337130417-3346a1be7dee?auto=format&fit=crop&w=800&q=80");
                az1.setUrgent(true);

                AvisoZoonosis az2 = new AvisoZoonosis();
                az2.setType("meta_lograda");
                az2.setTitle("¡Meta Cumplida! Refugio Ángeles logró juntar 60 bolsas de alimento");
                az2.setSubtitle("Gracias a 180 vecinos y donaciones directas por Alias, los 38 perritos tienen comida asegurada.");
                az2.setHighlightBadge("Festejo Comunitario 🎉");
                az2.setLocation("Sede Pedemonte, Luján de Cuyo");
                az2.setDate("Actualizado recientemente");
                az2.setActionText("Ver resumen de agradecimiento");
                az2.setActionUrl("#celebracion-meta");
                az2.setImage("https://images.unsplash.com/photo-1548767797-d8c844163c4c?auto=format&fit=crop&w=800&q=80");
                az2.setUrgent(false);

                AvisoZoonosis az3 = new AvisoZoonosis();
                az3.setType("castracion");
                az3.setTitle("Campaña Masiva de Castración Comunitaria en Guaymallén");
                az3.setSubtitle("Operativo conjunto entre rescatistas independientes y veterinarios matriculados.");
                az3.setHighlightBadge("Castraciones Gratuitas");
                az3.setLocation("Plaza del Encuentro, Guaymallén");
                az3.setMunicipality("Guaymallén");
                az3.setDate("Sábado 28 de Noviembre - 9:00 a 14:00 hs");
                az3.setActionText("Anotá a tu mascota aquí");
                az3.setActionUrl("#inscripcion-castracion");
                az3.setImage("https://images.unsplash.com/photo-1629740067905-bd3f515aa739?auto=format&fit=crop&w=800&q=80");
                az3.setUrgent(false);

                avisoRepository.saveAll(List.of(az1, az2, az3));
            }

            // 4. Patrocinadores (si no existen)
            if (patrocinadorRepository.count() == 0) {
                Patrocinador sp1 = new Patrocinador();
                sp1.setId("sp-godoy-cruz");
                sp1.setName("Municipalidad de Godoy Cruz");
                sp1.setCategory("municipio");
                sp1.setTier("platino");
                sp1.setLogo("https://images.unsplash.com/photo-1577495508048-b635879837f1?auto=format&fit=crop&w=200&q=80");
                sp1.setDescription("Dirección de Salud y Zoonosis. Compromiso con la esterilización quirúrgica gratuita y la tenencia responsable.");
                sp1.setLocation("Godoy Cruz, Mendoza");
                sp1.setSupportType("Móviles de castración y vacunación antirrábica.");
                sp1.setBenefitForAdopters("Vacunación antirrábica y desparasitación gratuita.");
                sp1.setContactUrl("https://www.godoycruz.gob.ar");

                patrocinadorRepository.save(sp1);
            }

            // 5. Seguimiento / Álbum de Vida inicial (si no existe)
            if (seguimientoRepository.count() == 0) {
                SeguimientoMascota seg1 = new SeguimientoMascota();
                seg1.setId("fol-1");
                seg1.setPetId("pet-adoptada-1");
                seg1.setPetName("Bella");
                seg1.setPetPhoto("https://images.unsplash.com/photo-1561037404-61cd46aa615b?auto=format&fit=crop&w=400&q=80");
                seg1.setSpecies("perro");
                seg1.setShelterId("angeles-cuatro-patas");
                seg1.setShelterName("Refugio Ángeles de Cuatro Patas");
                seg1.setAdopterName("Mariana Godoy");
                seg1.setAdopterLocation("Godoy Cruz, Mendoza");
                seg1.setAdoptionDate("15 de Octubre de 2026");
                seg1.setHealthTrafficLight("verde");
                seg1.setLastUpdateDate("Hace 2 días");
                seg1.setNextDueDate("15 de Noviembre de 2026");

                HitoSeguimiento h1 = new HitoSeguimiento();
                h1.setId("mh-1");
                h1.setPeriod("Día 15");
                h1.setTitle("Primeros pasos en el hogar");
                h1.setDescription("Foto del espacio donde duerme y primeros paseos.");
                h1.setDueDate("30 de Octubre de 2026");
                h1.setStatus("completado");
                h1.setPhotoUrl("https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?auto=format&fit=crop&w=800&q=80");
                h1.setCompletedAt("Completado el 29 de Octubre");
                h1.setNotes("Bella durmió toda la noche en su cucha. Ya reconoció el patio y pasea con correa.");
                h1.setBadgeName("Bienvenida al Hogar");
                h1.setBadgeIcon("🏠");
                h1.setBadgeDescription("Completó los primeros 15 días con éxito.");
                h1.setSeguimiento(seg1);

                seg1.getMilestones().add(h1);
                seguimientoRepository.save(seg1);
            }
        };
    }
}
