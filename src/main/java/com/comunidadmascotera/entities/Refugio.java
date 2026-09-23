package com.comunidadmascotera.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "refugios")
public class Refugio {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    private String tagline;

    @Column(length = 1000)
    private String logo;

    @Column(length = 1000)
    private String coverImage;

    private String location;
    private String zone;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String story;

    private String alias;
    private String cbu;
    private String bankName;
    private String titular;
    private String cuit;

    private String phone;
    private String whatsapp;
    private String instagram;
    private String email;

    private int animalsCount;
    private int adoptionsCount;

    @OneToMany(mappedBy = "refugio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "refugio-necesidades")
    private List<NecesidadRefugio> needs = new ArrayList<>();

    @OneToMany(mappedBy = "refugio", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "refugio-animales")
    private List<Animal> animales = new ArrayList<>();

    public Refugio() {}

    public Refugio(String id, String name, String tagline, String logo, String coverImage, 
                   String location, String zone, String description, String story, 
                   String alias, String cbu, String bankName, String titular, String cuit, 
                   String phone, String whatsapp, String instagram, String email, 
                   int animalsCount, int adoptionsCount) {
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.logo = logo;
        this.coverImage = coverImage;
        this.location = location;
        this.zone = zone;
        this.description = description;
        this.story = story;
        this.alias = alias;
        this.cbu = cbu;
        this.bankName = bankName;
        this.titular = titular;
        this.cuit = cuit;
        this.phone = phone;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
        this.email = email;
        this.animalsCount = animalsCount;
        this.adoptionsCount = adoptionsCount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTagline() { return tagline; }
    public void setTagline(String tagline) { this.tagline = tagline; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStory() { return story; }
    public void setStory(String story) { this.story = story; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getCbu() { return cbu; }
    public void setCbu(String cbu) { this.cbu = cbu; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public String getCuit() { return cuit; }
    public void setCuit(String cuit) { this.cuit = cuit; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWhatsapp() { return whatsapp; }
    public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }

    public String getInstagram() { return instagram; }
    public void setInstagram(String instagram) { this.instagram = instagram; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAnimalsCount() { return animalsCount; }
    public void setAnimalsCount(int animalsCount) { this.animalsCount = animalsCount; }

    public int getAdoptionsCount() { return adoptionsCount; }
    public void setAdoptionsCount(int adoptionsCount) { this.adoptionsCount = adoptionsCount; }

    public List<NecesidadRefugio> getNeeds() { return needs; }
    public void setNeeds(List<NecesidadRefugio> needs) { this.needs = needs; }

    public List<Animal> getAnimales() { return animales; }
    public void setAnimales(List<Animal> animales) { this.animales = animales; }
}
