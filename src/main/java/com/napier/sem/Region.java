package com.napier.sem;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.TreeMap;

public class Region {

    private TreeMap<Integer, Country> countryList;
    private String name;
    private int population;

    public Region()
    {

    }

    public Region(String name, int population) {
        this.setName(name);
        this.setPopulation(population);
    }

    public TreeMap<Integer, Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(TreeMap<Integer, Country> countryList) {
        this.countryList = countryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
