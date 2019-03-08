package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SanityCheck
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    private World world;

    public static void main(String[] args)
    {
        //Setup
        SanityCheck sanity = new SanityCheck();
        sanity.connect();
        sanity.world = new World();
        sanity.world.setCityList(sanity.generateCityList());
        sanity.world.setDistrictList(sanity.generateDistrictList());
        sanity.world.setCountryList(sanity.generateCountryList());
        sanity.world.setRegionList(sanity.generateRegionList());
        sanity.world.setContinentList(sanity.generateContinentList());
        sanity.calculateCountryUrbanPops();
        sanity.generateCountryLanguages();
        sanity.world.setLanguageList(sanity.generateWorldLanguages());

        //Methods to actually produce reports
        //sanity.listCountriesByPopulation();
        //sanity.listCitiesInCountry();
        sanity.testData();
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
            String strSelect = "SELECT * FROM city ORDER BY population DESC";
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
        /*list to hold the new country objects */
        ArrayList<Country> countryList = new ArrayList<Country>();
        /* try/catch used to handle any exceptions arising from the SQL statements
         */
        try
        {
            /*create a new SQL statement that selects all records from the country table in
            world.sql
             */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM country ORDER BY population DESC";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* for as long as there are more records in the returned result set, create new country
            objects and appropriately assign data from each columns to its' instance variables.
             */
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
            /* Iterate through the new countries and the existing cities list,
            match the capital cities to each country, assign a reference to
            each Country object to any city within that country, add the city's district object
            to the appropriate country's district list, and assign a reference to the correct country
            object to that district's country field.
             */
            for(Country country : countryList){
                for(City city : world.getCityList()){
                    if(city.getId() == country.getCapitalCode()){
                        country.setCapital(city);
                        city.setIsCapital(true);
                    }
                    if(city.getCountryCode().equals(country.getCode())){
                        city.setCountry(country);
                        country.getDistrictList().add(city.getDistrictObject());
                        city.getDistrictObject().setCountry(country);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        /* return the completed country list for external use */
        return countryList;
    }

    /* Method to read unique district names from the city table, create District objects
     for each one and add them to a list
    */
    public ArrayList<District> generateDistrictList() {
        /* list for District objects */
        ArrayList<District> districtList = new ArrayList<District>();
        /* try catch structure used for any potential SQL exceptions */
        try {
            /* SQL statement selects only records with unique values in the district column */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT district FROM city ";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* iterate over the returned rows and create a new District object for each one */
            while(rSet.next()){
                District district = new District(rSet.getString("District"));
                districtList.add(district);
            }
            /*Iterate through each city in the city list and each district in the new district list,
            add appropriate cities to each district's city list, and assign the right district object to
            the city's districtObject field
            */
            for(City city : world.getCityList()){
                for(District district : districtList){
                    district.calculatePopulation();
                    if(city.getDistrict().equals(district.getName())){
                        district.getCityList().add(city);
                        city.setDistrictObject(district);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        /* return the completed district list for external use */
        return districtList;
    }

    /* Method to read unique region names from the country table, create Region objects
     for each one and add them to a list
    */
    public ArrayList<Region> generateRegionList(){
        /* list for newly created Region objects */
        ArrayList<Region> regionList = new ArrayList<Region>();
        /* try/catch structure used for any potential SQL related exceptions */
        try{
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT region FROM country";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /*Iterate through the returned rows and create a new Region object for each one*/
            while(rSet.next()){
                Region region = new Region(rSet.getString("Region"));
                regionList.add(region);
            }
            /* For each region in the new list, iterate through country list and add the appropriate
            countries to the region's country list, assign a reference to the appropriate region object to each country within it,
            then calculate that region's population
             */
            for(Region region : regionList){
                for(Country country : world.getCountryList()){
                    if(country.getRegion().equals(region.getName())){
                        region.getCountryList().add(country);
                        country.setRegionObject(region);
                    }
                }
                region.calculatePopulation();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        /* return the created region list for external use*/
        return regionList;
    }

    /* Method to read unique continent names from the country table, create Continent objects
     for each one and add them to a list
    */
    public ArrayList generateContinentList(){
        /* list for newly created Continent objects */
        ArrayList<Continent> continentList = new ArrayList<Continent>();
        /* try/catch to handle exceptions */
        try{
            /* SQL statement returns only rows with unique values in the continent column
            from country table
             */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT continent FROM country";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* Iterate through the returned rows and make a new Continent object to represent each one
             */
            while(rSet.next()){
                Continent continent = new Continent(rSet.getString("Continent"));
                continentList.add(continent);
            }
            /* For each continent in the newly created list, Iterate through the existing region list , add the appropriate
            regions to that each continent's region list, set the continent field for each region, and calculate the
            population of each continent.
             */
            for(Continent continent : continentList){
                for(Region region : world.getRegionList()){
                    /* check that region's country list is not empty and if it isnt, use the continent
                    field of the first country in the list to establish what continent the region is in
                     */
                    if(!region.getCountryList().isEmpty() &&
                            region.getCountryList().get(0).getContinent().equals(continent.getName())){
                        /* add the region to the right continent's region list and set the continent field
                        for the Region object.
                         */
                        continent.getRegionList().add(region);
                        region.setContinent(continent);
                    }
                    continent.calculatePopulation();
                }
                /* now that all the continents and smaller areas have been set up, we can calculate the world population */
                world.calculatePopulation();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        /* return the created region list for external use*/
        return continentList;
    }

    /* small method to loop through the country list and call each ones calculateUrbanPop method.
     */
    public void calculateCountryUrbanPops(){
        for(Country country : world.getCountryList()){
            country.calculateUrbanPop();
        }
    }

    /* Method to read all the languages from the countryLanguage table, create a CountryLanguage object
    each one and pair them with the appropriate countries in the existing country list.
     */
    public void generateCountryLanguages(){
        /* list to hold the new CountryLanguage objects */
        ArrayList<CountryLanguage> countryLanguageList = new ArrayList<CountryLanguage>();
        /* try/catch to deal with any potential exceptions arising from SQL statement */
        try {
            /* simple SQL statement to select all rows from the CountryLanguage table */
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM countrylanguage";
            ResultSet rSet = stmt.executeQuery(strSelect);
            /* Iterate over returned rows, create a new CountryLanguage object to represent each one,
                set their instance variables appropriately and add them to the list
             */
            while(rSet.next()){
                    CountryLanguage countryLanguage = new CountryLanguage();
                    countryLanguage.setName(rSet.getString("Language"));
                    countryLanguage.setCountryCode(rSet.getString("CountryCode"));
                    countryLanguage.setOfficial(rSet.getBoolean("IsOfficial"));
                    countryLanguage.setPercentageOfSpeakers(rSet.getDouble("Percentage"));
                    countryLanguageList.add(countryLanguage);
            }
            /* iterate over the country list and the new CountryLanguage list and add each CountryLanguage object
            to the appropriate country's language list by matching them via getCountryCode and getCode. Also
            use the country's population and percentage field of the CountryLanguage
            to calculate the number of speakers of that language in the country.
             */
            for(CountryLanguage countryLanguage : countryLanguageList){
                for(Country country : world.getCountryList()){
                    if(countryLanguage.getCountryCode().equals(country.getCode())){
                        country.getLanguageList().add(countryLanguage);
                        countryLanguage.setNumberOfSpeakers(country.getPopulation()
                                *countryLanguage.getPercentageOfSpeakers());
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            /* nothing to return because there is no need to store the list of languages outside of their
            specific countries.
             */
        }
    }
    /* This method doesnt work :) */
    public ArrayList<WorldLanguage> generateWorldLanguages(){
        ArrayList<WorldLanguage> worldLanguageList = new ArrayList<WorldLanguage>();
        try{
            Statement stmt = con.createStatement();
            String strSelect = "SELECT DISTINCT language FROM countrylanguage";
            ResultSet rSet = stmt.executeQuery(strSelect);
            while(rSet.next()){
                WorldLanguage worldLanguage = new WorldLanguage(rSet.getString("Language"));
                world.getLanguageList().add(worldLanguage);
            }
            /* For each world language in the newly created list, iterate through every country in country list and every country language
            in the language list of each country. When a country language matches with a world language by name, add the number of speakers
            in that country to the total speakers the world language has so far. Finally, calculate the percentage of the world population
            that speakers each language
             */
            for(WorldLanguage worldLanguage : worldLanguageList){
                for(Country country : world.getCountryList()){
                    for(CountryLanguage countryLanguage : country.getLanguageList()){
                        if(countryLanguage.getName().equals(worldLanguage.getName())){
                            worldLanguage.setNumberOfSpeakers(worldLanguage.getNumberOfSpeakers() + countryLanguage.getNumberOfSpeakers());
                        }
                    }
                }
                worldLanguage.setPercentageOfSpeakers((worldLanguage.getNumberOfSpeakers()/world.getPopulation()*100));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return worldLanguageList;
    }

    /* Method to list all countries in world ranked by population, implementation for
    issue #2 on github. Simply iterates over the country list and calls report() for each one.
    */
    public void listCountriesByPopulation(){
        for(Country country : world.getCountryList()){
            System.out.println(country.report());
        }
    }

    /* Method to list all cities in a country ranked by population, implementation for
    issue #24 on github.
    */
    public void listCitiesInCountry(){
        //create booleans to control the loop
        boolean exit = false;
        boolean countryFound = false;
        /* while exit is false, loop asking the user for a country name and if found in the
        country list, iterate through the city objects in that countries' district list and call
        report for each city.
         */
        while(!exit){
            System.out.println("Enter the name of a country.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            for(Country country : world.getCountryList()){
                if(input.equals(country.getName())){
                    countryFound = true;
                    for(District district : country.getDistrictList()){
                        for(City city : district.getCityList()){
                            System.out.println(city.report());
                            exit = true;
                        }
                    }
                }
            }
            /* if countryFound is still false then the input was either invalid or a country of that
            name was not found in the list
             */
            if(!countryFound){
                System.out.println("Country not found, please search again");
            }
        }
    }

    /* method to test that the data has been loaded correctly by printing out a sampling of it
     */
    public void testData(){
        System.out.println('\n' + "Cities: " +'\n');
        for(int i = 0; i < 10; i++){
            System.out.println(world.getCityList().get(i).toString());
        }
        System.out.println('\n' + "Districts: " +'\n');
        for(int i = 0; i < 10; i++){
            System.out.println(world.getDistrictList().get(i).toString());
            System.out.println("Cities in " + world.getDistrictList().get(i).getName() + ":");
            world.getDistrictList().get(i).printCityList(10);
            System.out.println('\n');
        }
        System.out.println('\n' + "Countries: " + '\n');
        for(int i = 0; i < 10; i++){
            System.out.println(world.getCountryList().get(i).toString());
            System.out.println("Districts in " + world.getCountryList().get(i).getName() + ":");
            world.getCountryList().get(i).printDistrictList(10);
            System.out.println('\n');
            System.out.println("Languages spoken in " + world.getCountryList().get(i).getName() + ":");
            world.getCountryList().get(i).printLanguageList();
            System.out.println('\n');
        }
        System.out.println('\n' + "Regions: " +'\n');
        for(int i = 0; i < world.getRegionList().size(); i++){
            System.out.println(world.getRegionList().get(i).toString());
            System.out.println("Countries in: " + world.getRegionList().get(i).getName() + ": ");
            world.getRegionList().get(i).printCountryList(10);
            System.out.println('\n');
        }
        System.out.println('\n' + "Continents: " +'\n');
        for(int i = 0; i < world.getContinentList().size(); i++){
            System.out.println(world.getContinentList().get(i).toString());
            System.out.println("Regions in: " + world.getContinentList().get(i).getName() + ": ");
            world.getContinentList().get(i).printRegionList(10);
            System.out.println('\n');
        }
        System.out.println('\n' + "World: " +'\n');
        System.out.println(world.toString());
    }
}
