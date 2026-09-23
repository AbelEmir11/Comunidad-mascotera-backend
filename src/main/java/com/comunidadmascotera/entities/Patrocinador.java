package com.comunidadmascotera.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "patrocinadores")
public class Patrocinador {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String category;
    private String tier;

    @Column(length = 1000)
    private String logo;

    @Column(length = 1500)
    private String description;

    private String location;

    @Column(length = 1000)
    private String supportType;

    @Column(length = 1000)
    private String benefitForAdopters;

    private String contactUrl;

    public Patrocinador() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTier() { return tier; }
    public void setTier(String tier) { this.tier = tier; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSupportType() { return supportType; }
    public void setSupportType(String supportType) { this.supportType = supportType; }

    public String getBenefitForAdopters() { return benefitForAdopters; }
    public void setBenefitForAdopters(String benefitForAdopters) { this.benefitForAdopters = benefitForAdopters; }

    public String getContactUrl() { return contactUrl; }
    public void setContactUrl(String contactUrl) { this.contactUrl = contactUrl; }
}
