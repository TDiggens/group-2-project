package com.napier.sem;
/* A simple class to represent a City record from the database, featuring apropriate instance
variables
 */
public class City {

    private boolean isCapital;
    private Country country;
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private int population;

    public City()
    {

    }

    public boolean getIsCapital(){
        return isCapital;
    }

    public void setIsCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }

    public Country getCountry(){
        return country;
    }

    public void setCountry(Country country){
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "isCapital=" + isCapital +
                ", country=" + country +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }

    public String report(){

         String report = "City Report: " + this.getName() + '\n' + '\n' +
                     "Name: " + this.getName() + '\n' +
                     "Country: " + this.getCountry().getName() + '\n' +
                     "District: " +  this.getDistrict() + '\n' +
                     "Population: " + this.getPopulation();

        return report;
    }
}
