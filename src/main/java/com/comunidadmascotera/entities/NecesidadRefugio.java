package com.comunidadmascotera.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "necesidades_refugio")
public class NecesidadRefugio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String item;

    private int needed;
    private int current;
    private String unit;
    private String urgency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refugio_id")
    @JsonBackReference(value = "refugio-necesidades")
    private Refugio refugio;

    public NecesidadRefugio() {}

    public NecesidadRefugio(String item, int needed, int current, String unit, String urgency, Refugio refugio) {
        this.item = item;
        this.needed = needed;
        this.current = current;
        this.unit = unit;
        this.urgency = urgency;
        this.refugio = refugio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public int getNeeded() { return needed; }
    public void setNeeded(int needed) { this.needed = needed; }

    public int getCurrent() { return current; }
    public void setCurrent(int current) { this.current = current; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }

    public Refugio getRefugio() { return refugio; }
    public void setRefugio(Refugio refugio) { this.refugio = refugio; }
}
