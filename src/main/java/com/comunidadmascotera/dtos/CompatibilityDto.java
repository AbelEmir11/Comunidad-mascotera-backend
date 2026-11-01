package com.comunidadmascotera.dtos;

public class CompatibilityDto {

    private boolean apartment;
    private boolean kids;
    private boolean otherDogs;
    private boolean cats;
    private String energyLevel;
    private String hoursAloneOk;
    private boolean experienceRequired;

    public CompatibilityDto() {}

    public CompatibilityDto(boolean apartment, boolean kids, boolean otherDogs, boolean cats,
                            String energyLevel, String hoursAloneOk, boolean experienceRequired) {
        this.apartment = apartment;
        this.kids = kids;
        this.otherDogs = otherDogs;
        this.cats = cats;
        this.energyLevel = energyLevel;
        this.hoursAloneOk = hoursAloneOk;
        this.experienceRequired = experienceRequired;
    }

    public boolean isApartment() {
        return apartment;
    }

    public void setApartment(boolean apartment) {
        this.apartment = apartment;
    }

    public boolean isKids() {
        return kids;
    }

    public void setKids(boolean kids) {
        this.kids = kids;
    }

    public boolean isOtherDogs() {
        return otherDogs;
    }

    public void setOtherDogs(boolean otherDogs) {
        this.otherDogs = otherDogs;
    }

    public boolean isCats() {
        return cats;
    }

    public void setCats(boolean cats) {
        this.cats = cats;
    }

    public String getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(String energyLevel) {
        this.energyLevel = energyLevel;
    }

    public String getHoursAloneOk() {
        return hoursAloneOk;
    }

    public void setHoursAloneOk(String hoursAloneOk) {
        this.hoursAloneOk = hoursAloneOk;
    }

    public boolean isExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(boolean experienceRequired) {
        this.experienceRequired = experienceRequired;
    }
}
