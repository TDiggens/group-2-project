package com.napier.sem;


import java.util.ArrayList;

/* Class to represent a region of the world, with appropriate instance variables and a list
of countries it contains.
 */
public class Region implements Comparable < Region >{

    private ArrayList<Country> countryList = new ArrayList<Country>();
    private String name;
    private Long population;
    private long urbanPopulation;
    private long ruralPopulation;
    private double urbanPopulationPercentage;
    private double ruralPopulationPercentage;
    private Continent continent;

    @Override
    public int compareTo(Region otherRegion)
    {
        return population.compareTo(otherRegion.getPopulation());
    }

    public Region(){}

    public Region(String name)
    {
        setName(name);
    }

    /* method to calculate the population by summing countries in the regions' populations
    */
    public void calculatePopulation(){
        long p = 0;
        for(Country country : this.getCountryList()){
            p += ((long)country.getPopulation());
        }
        setPopulation(p);
    }

    public void calculateUrbanPopulation()
    {
        long urbanPop = 0;
        double urbanPopPercentage;
        for(Country country : countryList)
        {
            urbanPop += country.getUrbanPopulation();
        }
        urbanPopulation = urbanPop;
        ruralPopulation = population - urbanPop;
        urbanPopulationPercentage = ((urbanPop*100)/(double)population);
        ruralPopulationPercentage = (100-urbanPopulationPercentage);
    }


    public void printCountryList(int numberToPrint){
        if (numberToPrint > this.getCountryList().size()) {
                numberToPrint = this.getCountryList().size();
            }
            for (int i = 0; i < numberToPrint; i++) {
                System.out.print(this.getCountryList().get(i).getName() + "- population: " + this.getCountryList().get(i).getPopulation());
                if(this.getCountryList().get(i).getCapital() != null){
                        System.out.println(", capital city: " + this.getCountryList().get(i).getCapital().getName());
                }
            }

    }

    public ArrayList<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Region: " + name +
                ", population: " + population +
                ", continent: " + continent.getName();
    }

    public String report()
    {
        String report = "Region Report: " +  name + '\n' + '\n' +
                "Continent " + continent.getName() + '\n' +
                "Largest Country " + getCountryList().get(0) + '\n' +
                "Population: " + population + '\n' +
                "Urban population: " + urbanPopulation + " (" + urbanPopulationPercentage + ") " + '\n' +
                "Rural population: " + ruralPopulation + " (" + ruralPopulationPercentage + ") " + '\n';

        return report;
    }



    public long getUrbanPopulation() {
        return urbanPopulation;
    }

    public void setUrbanPopulation(long urbanPopulation) {
        this.urbanPopulation = urbanPopulation;
    }

    public long getRuralPopulation() {
        return ruralPopulation;
    }

    public void setRuralPopulation(long ruralPopulation) {
        this.ruralPopulation = ruralPopulation;
    }

    public double getUrbanPopulationPercentage() {
        return urbanPopulationPercentage;
    }

    public void setUrbanPopulationPercentage(double urbanPopulationPercentage) {
        this.urbanPopulationPercentage = urbanPopulationPercentage;
    }

    public double getRuralPopulationPercentage() {
        return ruralPopulationPercentage;
    }

    public void setRuralPopulationPercentage(double ruralPopulationPercentage) {
        this.ruralPopulationPercentage = ruralPopulationPercentage;
    }
}
