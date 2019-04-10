package com.napier.sem;

import java.util.ArrayList;

/* A Class designed to represent a Continent with appropriate instance variables
and a list of Region objects to hold the regions it contains
 */
public class Continent implements Comparable < Continent >{

    private Long population;
    private long urbanPopulation;
    private long ruralPopulation;
    private double urbanPopulationPercentage;
    private double ruralPopulationPercentage;
    private String name;
    private ArrayList<Region> regionList = new ArrayList<Region>();

    @Override
    public int compareTo(Continent otherContinent)
    {
        return population.compareTo(otherContinent.getPopulation());
    }

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

    public void calculateUrbanPopulation()
    {
        long urbanPop = 0;
        double urbanPopPercentage;
        for(Region region : regionList)
        {
            urbanPop += region.getUrbanPopulation();
        }
        urbanPopulation = urbanPop;
        ruralPopulation = population - urbanPop;
        urbanPopulationPercentage = ((urbanPop*100)/(double)population);
        ruralPopulationPercentage = (100-urbanPopulationPercentage);
    }

    public void printRegionList(int numberToPrint){
            if (numberToPrint > this.getRegionList().size()) {
                numberToPrint = this.getRegionList().size();
            }
            for (int i = 0; i < numberToPrint; i++) {
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
                "population: " + population + '\n';
    }
}
