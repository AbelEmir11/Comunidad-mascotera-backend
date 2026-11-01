package com.comunidadmascotera.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/animales retorna lista con estructura DTO (health, compatibility y shelterId)")
    void testListarAnimalesEstructuraDto() throws Exception {
        mockMvc.perform(get("/api/animales")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(4))))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].species").exists())
                .andExpect(jsonPath("$[0].shelterId").value(notNullValue()))
                .andExpect(jsonPath("$[0].health").isMap())
                .andExpect(jsonPath("$[0].health.vaccinated").isBoolean())
                .andExpect(jsonPath("$[0].health.neutered").isBoolean())
                .andExpect(jsonPath("$[0].health.dewormed").isBoolean())
                .andExpect(jsonPath("$[0].compatibility").isMap())
                .andExpect(jsonPath("$[0].compatibility.apartment").isBoolean())
                .andExpect(jsonPath("$[0].compatibility.energyLevel").isString());
    }

    @Test
    @DisplayName("GET /api/animales/{id} existente retorna 200 con el animal y objetos anidados")
    void testObtenerAnimalPorIdExistente() throws Exception {
        mockMvc.perform(get("/api/animales/pet-1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("pet-1"))
                .andExpect(jsonPath("$.name").value("Pampa"))
                .andExpect(jsonPath("$.species").value("perro"))
                .andExpect(jsonPath("$.shelterId").value("angeles-cuatro-patas"))
                .andExpect(jsonPath("$.health.vaccinated").value(true))
                .andExpect(jsonPath("$.health.neutered").value(true))
                .andExpect(jsonPath("$.compatibility.apartment").value(true))
                .andExpect(jsonPath("$.compatibility.energyLevel").value("medio"));
    }

    @Test
    @DisplayName("GET /api/animales/{id} inexistente retorna 404 Not Found")
    void testObtenerAnimalPorIdInexistente() throws Exception {
        mockMvc.perform(get("/api/animales/pet-inexistente")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/animales?especie=gato filtra correctamente solo gatos")
    void testFiltrarPorEspecieGato() throws Exception {
        mockMvc.perform(get("/api/animales?especie=gato")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[*].species", everyItem(equalToIgnoringCase("gato"))));
    }
}
