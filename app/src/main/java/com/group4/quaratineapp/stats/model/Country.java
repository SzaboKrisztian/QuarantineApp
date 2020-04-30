package com.group4.quaratineapp.stats.model;

public class Country implements Comparable<Country> {
    private String name;
    private String code;
    private int confirmed;
    private int active;
    private int dead;
    private int recovered;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public void add(Country other) {
        this.recovered += other.recovered;
        this.active += other.active;
        this.confirmed += other.confirmed;
        this.dead += other.dead;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", confirmed=" + confirmed +
                ", active=" + active +
                ", dead=" + dead +
                ", recovered=" + recovered +
                '}';
    }

    @Override
    public int compareTo(Country o) {
        return Integer.compare(this.confirmed, o.confirmed);
    }
}
