package com.comunidadmascotera.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animales")
public class Animal {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String gender;

    private String age;
    private int approxAgeMonths;
    private String size;
    private String status;

    @Column(length = 2000)
    private String story;

    private boolean urgent;
    private String timeInShelter;

    @ElementCollection
    @CollectionTable(name = "animal_fotos", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "foto_url", length = 1000)
    private List<String> photos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "animal_rasgos", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "rasgo")
    private List<String> traits = new ArrayList<>();

    private boolean vaccinated;
    private boolean neutered;
    private boolean dewormed;
    private boolean microchip;
    private String specialCare;

    private boolean apartment;
    private boolean kids;
    private boolean otherDogs;
    private boolean cats;
    private String energyLevel;
    private String hoursAloneOk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "refugio_id")
    @JsonBackReference(value = "refugio-animales")
    private Refugio refugio;

    public Animal() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public int getApproxAgeMonths() { return approxAgeMonths; }
    public void setApproxAgeMonths(int approxAgeMonths) { this.approxAgeMonths = approxAgeMonths; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStory() { return story; }
    public void setStory(String story) { this.story = story; }

    public boolean isUrgent() { return urgent; }
    public void setUrgent(boolean urgent) { this.urgent = urgent; }

    public String getTimeInShelter() { return timeInShelter; }
    public void setTimeInShelter(String timeInShelter) { this.timeInShelter = timeInShelter; }

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }

    public List<String> getTraits() { return traits; }
    public void setTraits(List<String> traits) { this.traits = traits; }

    public boolean isVaccinated() { return vaccinated; }
    public void setVaccinated(boolean vaccinated) { this.vaccinated = vaccinated; }

    public boolean isNeutered() { return neutered; }
    public void setNeutered(boolean neutered) { this.neutered = neutered; }

    public boolean isDewormed() { return dewormed; }
    public void setDewormed(boolean dewormed) { this.dewormed = dewormed; }

    public boolean isMicrochip() { return microchip; }
    public void setMicrochip(boolean microchip) { this.microchip = microchip; }

    public String getSpecialCare() { return specialCare; }
    public void setSpecialCare(String specialCare) { this.specialCare = specialCare; }

    public boolean isApartment() { return apartment; }
    public void setApartment(boolean apartment) { this.apartment = apartment; }

    public boolean isKids() { return kids; }
    public void setKids(boolean kids) { this.kids = kids; }

    public boolean isOtherDogs() { return otherDogs; }
    public void setOtherDogs(boolean otherDogs) { this.otherDogs = otherDogs; }

    public boolean isCats() { return cats; }
    public void setCats(boolean cats) { this.cats = cats; }

    public String getEnergyLevel() { return energyLevel; }
    public void setEnergyLevel(String energyLevel) { this.energyLevel = energyLevel; }

    public String getHoursAloneOk() { return hoursAloneOk; }
    public void setHoursAloneOk(String hoursAloneOk) { this.hoursAloneOk = hoursAloneOk; }

    public Refugio getRefugio() { return refugio; }
    public void setRefugio(Refugio refugio) { this.refugio = refugio; }

    public String getShelterId() { return refugio != null ? refugio.getId() : null; }
    public String getShelterName() { return refugio != null ? refugio.getName() : null; }
    public String getShelterLocation() { return refugio != null ? refugio.getLocation() : null; }
}
