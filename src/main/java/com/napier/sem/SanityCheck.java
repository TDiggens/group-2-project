package com.napier.sem;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class SanityCheck
{

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    private World world;

    public static void main(String[] args)
    {
        System.out.println("Do not be alarmed, this is a test. Hello Group 2!");
        SanityCheck sanity = new SanityCheck();
        sanity.connect();
        sanity.world = new World();
        sanity.world.setCityList(sanity.generateCityList());
        sanity.world.setDistrictList(sanity.generateDistrictList());
        sanity.world.setCountryList(sanity.generateCountryList());
        sanity.world.setRegionList(sanity.generateRegionList());
        sanity.world.setContinentList(sanity.generateContinentList());
        sanity.calculatePops();
        sanity.generateCountryLanguages();
        sanity.generateWorldLanguages();
        sanity.disconnect();
        
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load database driver
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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
        ArrayList<City> cityList = world.getCityList();
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
        ArrayList<Country> countryList = world.getCountryList();
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
        ArrayList<Region> regionList = world.getRegionList();
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
        for(District district : world.getDistrictList()){
            district.cacultatePopulation();
        }
        for(Region region: world.getRegionList()){
            region.cacultatePopulation();
        }
        for(Continent continent : world.getContinentList()){
            continent.cacultatePopulation();
        }
        world.calculatePopulation();
    }

    public void generateCountryLanguages(){
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM countrylanguage";
            ResultSet rSet = stmt.executeQuery(strSelect);
            ArrayList<CountryLanguage> countryLanguageList = new ArrayList<CountryLanguage>();
            while(rSet.next()){
                    CountryLanguage countryLanguage = new CountryLanguage();
                    countryLanguage.setName(rSet.getString("Language"));
                    countryLanguage.setCountryCode(rSet.getString("CountryCode"));
                    countryLanguage.setOfficial(rSet.getBoolean("IsOfficial"));
                    countryLanguage.setSpokenBy(rSet.getDouble("Percentage"));
                    countryLanguageList.add(countryLanguage);
            }
            
            for(CountryLanguage countryLanguage : countryLanguageList){
                for(Country country : world.getCountryList()){
                    if(countryLanguage.getCountryCode() == country.getCode()){
                        country.getLanguageList().add(countryLanguage);
                    }
                }
            }
            for(Country country : world.getCountryList()){
                for(CountryLanguage countryLanguage : country.getLanguageList()){
                    countryLanguage.setNumberOfSpeakers(countryLanguage.getSpokenBy()/100 * country.getPopulation());
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void generateWorldLanguages(){
        for(Country country : world.getCountryList()){
            boolean exists = false;
            double extraSpeakers;
            double currentSpeakers;
            double totalSpeakers;
            for(CountryLanguage countryLanguage : country.getLanguageList()){
                for(Map.Entry<WorldLanguage, Double> worldLanguage : world.getLanguageList().entrySet()){
                        if(countryLanguage.getName() == worldLanguage.getKey().getName()){
                            exists = true;
                            currentSpeakers = worldLanguage.getValue();
                            extraSpeakers = countryLanguage.getNumberOfSpeakers();
                            totalSpeakers = currentSpeakers + extraSpeakers;
                            world.getLanguageList().put(worldLanguage.getKey(), totalSpeakers);
                            break;
                        }
                }
                if(!exists){
                    world.getLanguageList().put(new WorldLanguage(countryLanguage.getName()), countryLanguage.getNumberOfSpeakers());
                }
            }
            for(Map.Entry<WorldLanguage, Double> worldLanguage : world.getLanguageList().entrySet()){
                worldLanguage.getKey().setPercentageOfSpeakers(worldLanguage.getValue()/world.getPopulation());
            }
        }
    }

    public void testData(){
        for(int i = 0; i < 10; i++){
            System.out.println(world.getCityList().get(i).toString());
        }
        for(int i = 0; i < 10; i++){
            System.out.println(world.getDistrictList().get(i).toString());
        }
        for(int i = 0; i < 10; i++){
            System.out.println(world.getCountryList().get(i).toString());
        }
        for(int i = 0; i < world.getRegionList().size()-1; i++){
            System.out.println(world.getRegionList().get(i).toString());
        }
        for(int i = 0; i < world.getContinentList().size()-1; i++){
            System.out.println(world.getContinentList().get(i).toString());
        }
    }


}
