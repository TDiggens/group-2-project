package com.napier.sem;

import java.util.ArrayList;

/* A Class designed to represent a Continent with appropriate instance variables
and a list of Region objects to hold the regions it contains
 */
public class Continent {

    private long population;
    private String name;
    private ArrayList<Region> regionList = new ArrayList<Region>();
    public Continent(){}

    public Continent(String name)
    {
        setName(name);
    }
    
    public void calculatePopulation(){
        long p = 0;
        for(Region region : regionList){
            p += region.getPopulation();
        }
        setPopulation(p);
    }

    public void printRegionList(int numberToPrint){
        for(int i = 0; i < numberToPrint; i++){
            System.out.println(this.getRegionList().get(i).getName() + ", population: " + this.getRegionList().get(i).getPopulation());
        }
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Region> getRegionList() {
        return regionList;
    }

    public void setRegionList(ArrayList<Region> regionList) {
        this.regionList = regionList;
    }

    @Override
    public String toString() {
        return
                 name + ": " + '\n' +
                "population: " + population + '\n' +
                ", regionList: " + regionList + '\n'
                ;
    }
}
