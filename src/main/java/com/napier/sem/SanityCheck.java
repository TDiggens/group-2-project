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
        sanity.calculateDistrictPops();
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
                    districtList.add(district);
                    if(!district.getCityList().contains(city)){
                        district.getCityList().add(city);
                    }
                }
            }
        return districtList;
    }

    public void calculateDistrictPops()
    {
        for(District district : data.getDistrictList()){
            int population = 0;
            for(City city : district.getCityList()){
                population += city.getPopulation();
            }
            district.setPopulation(population);
        }
    }
}
