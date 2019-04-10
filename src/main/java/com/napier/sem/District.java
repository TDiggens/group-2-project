package com.napier.sem;

import java.util.ArrayList;
/* Class to represent a district within a country, with the list of cities it contains

 */
public class District implements Comparable < District > {

    private String name;
    private Integer population;
    private Country country;
    private ArrayList<City> cityList = new ArrayList<City>();

    public District()
    {

    }

    public District(String name)
    {
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
                System.out.println(this.getCityList().get(i).report());
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "District: "
                + this.getName() + '\n' +
                "Country: " + this.getCountry().getName() + '\n' +
                ", population: " + this.getPopulation();

    }

    public String report()
    {
        if(!cityList.isEmpty()) {
            String report = "District Report: " + name + '\n' + '\n' +
                    "Country: " + country.getName() + '\n' +
                    "Population: " + population + '\n' +
                    "Largest City " + cityList.get(0) + '\n' + '\n';
            return report;
        }
        else
        {
            String report = "District Report: " + name + '\n' + '\n' +
                "Country: " + country.getName() + '\n' +
                "Population: " + population + '\n';
            return report;
        }
    }

    @Override
    public int compareTo(District otherDistrict)
    {
        return population.compareTo(otherDistrict.getPopulation());
    }


}
