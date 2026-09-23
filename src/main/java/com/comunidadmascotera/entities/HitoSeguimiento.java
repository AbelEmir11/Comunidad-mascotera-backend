package com.comunidadmascotera.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "hitos_seguimiento")
public class HitoSeguimiento {

    @Id
    private String id;

    private String period;
    private String title;

    @Column(length = 1000)
    private String description;

    private String dueDate;
    private String status;

    @Column(length = 1000)
    private String photoUrl;

    @Column(length = 2000)
    private String notes;

    private String completedAt;

    private String badgeName;
    private String badgeIcon;
    private String badgeDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seguimiento_id")
    @JsonBackReference(value = "seguimiento-hitos")
    private SeguimientoMascota seguimiento;

    public HitoSeguimiento() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getCompletedAt() { return completedAt; }
    public void setCompletedAt(String completedAt) { this.completedAt = completedAt; }

    public String getBadgeName() { return badgeName; }
    public void setBadgeName(String badgeName) { this.badgeName = badgeName; }

    public String getBadgeIcon() { return badgeIcon; }
    public void setBadgeIcon(String badgeIcon) { this.badgeIcon = badgeIcon; }

    public String getBadgeDescription() { return badgeDescription; }
    public void setBadgeDescription(String badgeDescription) { this.badgeDescription = badgeDescription; }

    public SeguimientoMascota getSeguimiento() { return seguimiento; }
    public void setSeguimiento(SeguimientoMascota seguimiento) { this.seguimiento = seguimiento; }
}
