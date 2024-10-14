package com.ardaslegends.albaseplugin.models.DBModels;

import jakarta.persistence.*;

import java.util.List;

/**
 * This is a Model for a Facton.
 * This Model is used to load a Faction from the Database
 */
@Entity
@Table(name = "factions")
public class DBFactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "faction_name", nullable = false)
    private String factionName;
    @Column(name = "leader_ign", nullable = true)
    private String leaderIGN;
    @Column(name = "stockpile", nullable = true)
    private double stockpile;
    @Column(name = "deposit", nullable = true)
    private double deposit;
    @OneToMany(mappedBy = "faction")
    private List<DBRegionModel> regions;
    @OneToMany(mappedBy = "faction")
    private List<DBClaimbuildModel> claimbuildModels;

    public DBFactionModel(String factionName) {
        this.factionName = factionName;
        this.stockpile = 0;
        this.deposit = 0;
    }

    public DBFactionModel() {
        this.stockpile = 0;
        this.deposit = 0;
    }

    /**
     * Adds the specified amount to the stockpile.
     *
     * @param amount the amount to be added to the stockpile
     * @return the new stockpile amount
     */
    public double addStockpile(double amount) {
        this.stockpile += amount;
        return stockpile;
    }

    /**
     * Removes the specified amount from the stockpile.
     *
     * @param amount the amount to be removed from the stockpile
     * @return the amount removed if successful, or -1 if the stockpile does not have enough to cover the removal
     */
    public double removeStockpile(double amount) {
        if (this.stockpile - amount >= 0) {
            this.stockpile -= amount;
            return amount;
        } else {
            return -1;
        }
    }

    /**
     * Adds the specified amount to the deposit.
     *
     * @param amount the amount to be added to the deposit
     * @return the new deposit amount
     */
    public double addDeposit(double amount) {
        this.deposit += amount;
        return deposit;
    }

    /**
     * Removes the specified amount from the deposit.
     *
     * @param amount the amount to be removed from the deposit
     * @return the amount removed if successful, or -1 if the deposit does not have enough to cover the removal
     */
    public double removeDeposit(double amount) {
        if (this.deposit - amount >= 0) {
            this.deposit -= amount;
            return amount;
        } else {
            return -1;
        }
    }

    /**
     * Add a Region to the Faction
     * @param region
     */
    public void addRegion(DBRegionModel region) {
        regions.add(region);
    }

    /**
     * Add a List of Regions to the Faction
     * @param regions
     */
    public void addRegions(List<DBRegionModel> regions) {
        this.regions.addAll(regions);
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getLeaderIGN() {
        return leaderIGN;
    }

    public void setLeaderIGN(String leaderIGN) {
        this.leaderIGN = leaderIGN;
    }

    public double getStockpile() {
        return stockpile;
    }

    public double getDeposit() {
        return deposit;
    }

    public List<DBRegionModel> getRegions() {
        return regions;
    }
}
