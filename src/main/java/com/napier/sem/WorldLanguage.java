package com.napier.sem;

public class WorldLanguage implements Comparable < WorldLanguage > {
    private String name;
    private Double numberOfSpeakers;
    private double percentageOfSpeakers;

    public WorldLanguage(String name)
    {
        setName(name);
    }

    @Override
    public int compareTo(WorldLanguage otherWorldLanguage)
    {
        return numberOfSpeakers.compareTo(otherWorldLanguage.getNumberOfSpeakers());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumberOfSpeakers() {
        return numberOfSpeakers;
    }

    public void setNumberOfSpeakers(double numberOfSpeakers) {
        this.numberOfSpeakers = numberOfSpeakers;
    }

    public double getPercentageOfSpeakers() {
        return percentageOfSpeakers;
    }

    public void setPercentageOfSpeakers(double percentageOfSpeakers) {
        this.percentageOfSpeakers = percentageOfSpeakers;
    }
}
