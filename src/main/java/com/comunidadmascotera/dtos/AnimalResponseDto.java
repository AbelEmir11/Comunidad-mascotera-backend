package com.comunidadmascotera.dtos;

import com.comunidadmascotera.entities.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalResponseDto {

    private String id;
    private String name;
    private String species;
    private String gender;
    private String age;
    private int approxAgeMonths;
    private String size;
    private String status;
    private String story;
    private boolean urgent;
    private String timeInShelter;
    private List<String> photos = new ArrayList<>();
    private List<String> traits = new ArrayList<>();
    private String shelterId;
    private HealthInfoDto health;
    private CompatibilityDto compatibility;

    public AnimalResponseDto() {}

    public static AnimalResponseDto fromEntity(Animal animal) {
        if (animal == null) {
            return null;
        }

        AnimalResponseDto dto = new AnimalResponseDto();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setSpecies(animal.getSpecies());
        dto.setGender(animal.getGender());
        dto.setAge(animal.getAge());
        dto.setApproxAgeMonths(animal.getApproxAgeMonths());
        dto.setSize(animal.getSize());
        dto.setStatus(animal.getStatus());
        dto.setStory(animal.getStory());
        dto.setUrgent(animal.isUrgent());
        dto.setTimeInShelter(animal.getTimeInShelter());
        dto.setPhotos(animal.getPhotos() != null ? new ArrayList<>(animal.getPhotos()) : new ArrayList<>());
        dto.setTraits(animal.getTraits() != null ? new ArrayList<>(animal.getTraits()) : new ArrayList<>());

        if (animal.getRefugio() != null) {
            dto.setShelterId(animal.getRefugio().getId());
        }

        HealthInfoDto healthDto = new HealthInfoDto(
                animal.isVaccinated(),
                animal.isNeutered(),
                animal.isDewormed(),
                animal.isMicrochip(),
                animal.getSpecialCare()
        );
        dto.setHealth(healthDto);

        CompatibilityDto compatDto = new CompatibilityDto(
                animal.isApartment(),
                animal.isKids(),
                animal.isOtherDogs(),
                animal.isCats(),
                animal.getEnergyLevel(),
                animal.getHoursAloneOk(),
                false
        );
        dto.setCompatibility(compatDto);

        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getApproxAgeMonths() {
        return approxAgeMonths;
    }

    public void setApproxAgeMonths(int approxAgeMonths) {
        this.approxAgeMonths = approxAgeMonths;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getTimeInShelter() {
        return timeInShelter;
    }

    public void setTimeInShelter(String timeInShelter) {
        this.timeInShelter = timeInShelter;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

    public String getShelterId() {
        return shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public HealthInfoDto getHealth() {
        return health;
    }

    public void setHealth(HealthInfoDto health) {
        this.health = health;
    }

    public CompatibilityDto getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(CompatibilityDto compatibility) {
        this.compatibility = compatibility;
    }
}
