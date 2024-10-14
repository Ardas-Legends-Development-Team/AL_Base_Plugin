package com.ardaslegends.albaseplugin.models.DBModels;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class DBPlayerModel {
    @Id
    private String uuid;
    @Column(name = "pvp", nullable = false)
    private boolean pvp;
    @ManyToOne
    @JoinColumn(name = "faction_id")
    private DBFactionModel faction;

    public DBPlayerModel(String uuid, DBFactionModel faction) {
        this.uuid = uuid;
        this.faction = faction;
        this.pvp = false;
    }

    public DBPlayerModel(String uuid) {
        this.uuid = uuid;
        this.pvp = false;
    }

    public DBPlayerModel() {

    }

    public String getUuid() {
        return uuid;
    }

    public boolean isPvp() {
        return pvp;
    }

    public DBFactionModel getFaction() {
        return faction;
    }

    public void setFaction(DBFactionModel faction) {
        this.faction = faction;
    }
}
