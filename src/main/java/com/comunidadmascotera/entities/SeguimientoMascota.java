package com.comunidadmascotera.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seguimiento_mascotas")
public class SeguimientoMascota {

    @Id
    private String id;

    private String petId;
    private String petName;

    @Column(length = 1000)
    private String petPhoto;

    private String species;
    private String shelterId;
    private String shelterName;

    private String adopterName;
    private String adopterLocation;
    private String adoptionDate;

    private String healthTrafficLight; // "verde" | "amarillo" | "rojo"

    private String lastUpdateDate;
    private String nextDueDate;

    @OneToMany(mappedBy = "seguimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "seguimiento-hitos")
    private List<HitoSeguimiento> milestones = new ArrayList<>();

    public SeguimientoMascota() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPetId() { return petId; }
    public void setPetId(String petId) { this.petId = petId; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getPetPhoto() { return petPhoto; }
    public void setPetPhoto(String petPhoto) { this.petPhoto = petPhoto; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getShelterId() { return shelterId; }
    public void setShelterId(String shelterId) { this.shelterId = shelterId; }

    public String getShelterName() { return shelterName; }
    public void setShelterName(String shelterName) { this.shelterName = shelterName; }

    public String getAdopterName() { return adopterName; }
    public void setAdopterName(String adopterName) { this.adopterName = adopterName; }

    public String getAdopterLocation() { return adopterLocation; }
    public void setAdopterLocation(String adopterLocation) { this.adopterLocation = adopterLocation; }

    public String getAdoptionDate() { return adoptionDate; }
    public void setAdoptionDate(String adoptionDate) { this.adoptionDate = adoptionDate; }

    public String getHealthTrafficLight() { return healthTrafficLight; }
    public void setHealthTrafficLight(String healthTrafficLight) { this.healthTrafficLight = healthTrafficLight; }

    public String getLastUpdateDate() { return lastUpdateDate; }
    public void setLastUpdateDate(String lastUpdateDate) { this.lastUpdateDate = lastUpdateDate; }

    public String getNextDueDate() { return nextDueDate; }
    public void setNextDueDate(String nextDueDate) { this.nextDueDate = nextDueDate; }

    public List<HitoSeguimiento> getMilestones() { return milestones; }
    public void setMilestones(List<HitoSeguimiento> milestones) { this.milestones = milestones; }
}
