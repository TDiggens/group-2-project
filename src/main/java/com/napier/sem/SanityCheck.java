package com.napier.sem;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class SanityCheck
{

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    private World data;

    public static void main(String[] args)
    {
        System.out.println("Do not be alarmed, this is a test. Hello Group 2!");
        SanityCheck sanity = new SanityCheck();
        sanity.data = new World();
        sanity.data.setCityList(sanity.generateCityList());
        sanity.data.setDistrictList(sanity.generateDistrictList());
        sanity.data.setCountryList(sanity.generateCountryList());
        sanity.calculatePops();
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 30;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(5000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }


    /* Method to read all the city records from the database, create City objects for them and add
    them to a list
     */
    public ArrayList<City> generateCityList(){
        ArrayList<City> cityList = new ArrayList<City>();
        try
        {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM city ORDER BY population";
            ResultSet rSet = stmt.executeQuery(strSelect);
            while(rSet.next())
            {
                City city = new City();
                city.setCountryCode(rSet.getString("CountryCode"));
                city.setDistrict(rSet.getString("District"));
                city.setId(rSet.getInt("ID"));
                city.setName(rSet.getString("Name"));
                city.setPopulation(rSet.getInt("Population"));
                cityList.add(city);
            }
        }

        catch(Exception e)
        {

            return null;
        }
        return cityList;
    }

    /* Method to read all the country records from the database, create Country objects
     for each one and add them to a list
    */
    public ArrayList<Country> generateCountryList(){
        ArrayList<Country> countryList = new ArrayList<Country>();
        try
        {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM country ORDER BY population";
            ResultSet rSet = stmt.executeQuery(strSelect);

            while(rSet.next()) {
                Country country = new Country();
                country.setCapitalCode(rSet.getInt("Capital"));
                country.setCode(rSet.getString("Code"));
                country.setCode2(rSet.getString("Code2"));
                country.setContinent(rSet.getString("Continent"));
                country.setGnp(rSet.getDouble("GNP"));
                country.setOldGNP(rSet.getDouble("GNPOld"));
                country.setFormOfGov(rSet.getString("GovernmentForm"));
                country.setHeadOfState(rSet.getString("HeadOfState"));
                country.setYearOfIndependence(rSet.getInt("IndepYear"));
                country.setLifeExpectancy(rSet.getDouble("LifeExpectancy"));
                country.setLocalName(rSet.getString("LocalName"));
                country.setName(rSet.getString("Name"));
                country.setPopulation(rSet.getInt("Population"));
                country.setRegion(rSet.getString("Region"));
                country.setSurfaceArea(rSet.getDouble("SurfaceArea"));
                countryList.add(country);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return countryList;
    }

    public ArrayList<District> generateDistrictList(){
        ArrayList<District> districtList = new ArrayList<District>();
        ArrayList<City> cityList = data.getCityList();
        boolean exists = false;
            for(City city : cityList){
                exists = false;
                for(District district : districtList) {
                    if(city.getDistrict() == district.getName()) {
                        exists = true;
                        if(!district.getCityList().contains(city)){
                            district.getCityList().add(city);
                        }
                        break;
                    }
                }
                if(!exists){
                    District district = new District(city.getDistrict());
                    district.getCityList().add(city);
                    districtList.add(district);
                }
            }
        return districtList;
    }

    public ArrayList<Region> generateRegionList(){
        ArrayList<Region> regionList = new ArrayList<Region>();
        ArrayList<Country> countryList = data.getCountryList();
        boolean exists = false;
        for(Country country : countryList){
            exists = false;
            Region tmpRegion = new Region();
            for(Region region : regionList) {
                if(country.getRegion() == region.getName()) {
                    exists = true;
                    if(!region.getCountryList().contains(country)){
                        region.getCountryList().add(country);
                    }
                    break;
                }
            }
            if(!exists){
                Region region = new Region(country.getRegion());
                regionList.add(region);
                region.setContinent(country.getContinent());
                region.getCountryList().add(country);
                }
        }
        return regionList;
    }

    public ArrayList<Continent> generateContinentList(){
        ArrayList<Continent> continentList = new ArrayList<Continent>();
        ArrayList<Region> regionList = data.getRegionList();
        boolean exists = false;
        for(Region region : regionList){
            exists = false;
            for(Continent continent : continentList) {
                if(region.getContinent() == continent.getName()) {
                    exists = true;
                    if(!continent.getRegionList().contains(region)){
                        continent.getRegionList().add(region);
                    }
                    break;
                }
            }
            if(!exists){
                Continent continent = new Continent(region.getContinent());
                continent.getRegionList().add(region);
                continentList.add(continent);
            }
        }
        return continentList;
    }

    public void calculatePops()
    {
        for(District district : data.getDistrictList()){
            district.cacultatePopulation();
        }
        for(Region region: data.getRegionList()){
            region.cacultatePopulation();
        }
        for(Continent continent : data.getContinentList()){
            continent.cacultatePopulation();
        }
    }
}
