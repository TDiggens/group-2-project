package com.napier.sem;


import java.util.ArrayList;

/* Class to represent a region of the world, with appropriate instance variables and a list
of countries it contains.
 */
public class Region {

    private ArrayList<Country> countryList = new ArrayList<Country>();
    private String name;
    private long population;
    private Continent continent;

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

    public void printCountryList(int numberToPrint){
        if(numberToPrint > this.getCountryList().size()){
            numberToPrint = this.getCountryList().size();
        }
        for(int i = 0; i < numberToPrint; i++){
            System.out.println(this.getCountryList().get(i).getName() + " population: " + this.getCountryList().get(i).getPopulation()
                    + " capital city: " + this.getCountryList().get(i).getCapital().getName());
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
}
