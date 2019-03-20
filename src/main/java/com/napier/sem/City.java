package com.napier.sem;
/* A simple class to represent a City record from the database, featuring apropriate instance
variables
 */
public class City implements Comparable < City >{

    private boolean isCapital;
    private Country country;
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private Integer population;
    private District districtObject;

    public City()
    {

    }

    public City(String countryCode, String district, int id, String name, int population)
    {
        this.setCountryCode(countryCode);
        this.setDistrict(district);
        this.setId(id);
        this.setName(name);
        this.setPopulation(population);
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

    public District getDistrictObject() {
        return districtObject;
    }

    public void setDistrictObject(District districtObject) {
        this.districtObject = districtObject;
    }

    @Override
    public String toString() {
        String isCapitalStr = "No.";
        if(isCapital){
            isCapitalStr = "Yes.";
        }
        return "City: " + name +
                ", Capital city?: " + isCapitalStr +
                ", Country: " + country.getName() +
                ", id: " + id +
                ", name: " +
                ", countryCode= " + countryCode  +
                ", district= " + district  +
                ", population= " + population;
    }

    public String report(){
        String isCapitalStr = "No.";
        String reportTitle = "City Report: ";
        if(isCapital){
            isCapitalStr = "Yes.";
            reportTitle = "Capital City Report: ";
        }

         String report = reportTitle + this.getName() + '\n' + '\n' +
                     "Capital City: " + isCapitalStr + '\n' +
                     "Name: " + this.getName() + '\n' +
                     "Country: " + this.getCountry().getName() + '\n' +
                     "District: " +  this.getDistrict() + '\n' +
                     "Population: " + this.getPopulation() + '\n';

        return report;
    }

    @Override
    public int compareTo(City otherCity)
    {
        return population.compareTo(otherCity.getPopulation());
    }


}
