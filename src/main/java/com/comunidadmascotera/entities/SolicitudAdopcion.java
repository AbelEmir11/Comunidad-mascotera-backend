package com.comunidadmascotera.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes_adopcion")
public class SolicitudAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del adoptante es obligatorio")
    private String applicantName;

    @NotBlank(message = "El DNI es obligatorio para la adopción responsable")
    @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener 8 dígitos numéricos")
    private String applicantDni;

    @NotBlank(message = "El WhatsApp de contacto es obligatorio")
    @Pattern(regexp = "^\\d{8,12}$", message = "El teléfono debe tener entre 8 y 12 dígitos")
    private String applicantPhone;

    private String applicantHousing;

    @AssertTrue(message = "Debe aceptar el compromiso de seguimiento")
    private boolean agreedToFollowup;
    private String status;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public SolicitudAdopcion() {}

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = "PENDIENTE";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getApplicantDni() { return applicantDni; }
    public void setApplicantDni(String applicantDni) { this.applicantDni = applicantDni; }

    public String getApplicantPhone() { return applicantPhone; }
    public void setApplicantPhone(String applicantPhone) { this.applicantPhone = applicantPhone; }

    public String getApplicantHousing() { return applicantHousing; }
    public void setApplicantHousing(String applicantHousing) { this.applicantHousing = applicantHousing; }

    public boolean isAgreedToFollowup() { return agreedToFollowup; }
    public void setAgreedToFollowup(boolean agreedToFollowup) { this.agreedToFollowup = agreedToFollowup; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
}
