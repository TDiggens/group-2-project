package com.napier.sem;

import java.util.ArrayList;
/* Class to represent a district within a country, with the list of cities it contains

 */
public class District {

    private String name;
    private int population;
    private Country country;
    private ArrayList<City> cityList = new ArrayList<City>();

    public District(){

    }

    public District(String name) {
        this.name = name;

    }

    public void calculatePopulation(){
        int p = 0;
        for(City city : this.getCityList()){
            p = p + (city.getPopulation());
        }
        setPopulation(p);
    }

    public void printCityList(int numberToPrint){
            if (numberToPrint > this.getCityList().size()) {
                numberToPrint = this.getCityList().size();
            }
            for (int i = 0; i < numberToPrint; i++) {
                System.out.println(this.getCityList().get(i).getName() + " population: " + this.getCityList().get(i).getPopulation());
            }
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

    public ArrayList<City> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "District: "
                + this.getName() + '\n' +
                "Country: " + this.getCountry().getName() + '\n' +
                ", population: " + this.getPopulation();

    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
