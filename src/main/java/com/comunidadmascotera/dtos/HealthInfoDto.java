package com.comunidadmascotera.dtos;

public class HealthInfoDto {

    private boolean vaccinated;
    private boolean neutered;
    private boolean dewormed;
    private boolean microchip;
    private String specialCare;

    public HealthInfoDto() {}

    public HealthInfoDto(boolean vaccinated, boolean neutered, boolean dewormed, boolean microchip, String specialCare) {
        this.vaccinated = vaccinated;
        this.neutered = neutered;
        this.dewormed = dewormed;
        this.microchip = microchip;
        this.specialCare = specialCare;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public boolean isDewormed() {
        return dewormed;
    }

    public void setDewormed(boolean dewormed) {
        this.dewormed = dewormed;
    }

    public boolean isMicrochip() {
        return microchip;
    }

    public void setMicrochip(boolean microchip) {
        this.microchip = microchip;
    }

    public String getSpecialCare() {
        return specialCare;
    }

    public void setSpecialCare(String specialCare) {
        this.specialCare = specialCare;
    }
}
