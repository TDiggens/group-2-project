package com.napier.sem;

import java.util.ArrayList;
import java.util.TreeMap;

public class Continent {

    private int population;
    private String name;
    private TreeMap<Integer, Region> regionList = new TreeMap<Integer, Region>();

    public Continent()
    {

    }

    public Continent(int population, String name, TreeMap<Integer, Region> regionList) {
        this.population = population;
        this.name = name;
        this.regionList = regionList;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeMap<Integer, Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(TreeMap<Integer, Region> regionList) {
        this.regionList = regionList;
    }
}
