package com.comunidadmascotera.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "avisos_zoonosis")
public class AvisoZoonosis {

    @Id
    private String id;

    private String type;

    @Column(nullable = false, length = 500)
    private String title;

    @Column(length = 1000)
    private String subtitle;

    private String highlightBadge;
    private String location;
    private String municipality;
    private String date;
    private String actionText;
    private String actionUrl;

    @Column(length = 1000)
    private String image;

    private boolean urgent;

    public AvisoZoonosis() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getHighlightBadge() { return highlightBadge; }
    public void setHighlightBadge(String highlightBadge) { this.highlightBadge = highlightBadge; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getMunicipality() { return municipality; }
    public void setMunicipality(String municipality) { this.municipality = municipality; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getActionText() { return actionText; }
    public void setActionText(String actionText) { this.actionText = actionText; }

    public String getActionUrl() { return actionUrl; }
    public void setActionUrl(String actionUrl) { this.actionUrl = actionUrl; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public boolean isUrgent() { return urgent; }
    public void setUrgent(boolean urgent) { this.urgent = urgent; }
}
