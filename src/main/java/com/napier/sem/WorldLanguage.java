package com.napier.sem;

public class WorldLanguage {
    private String name;
    private double numberOfSpeakers;
    private double percentageOfSpeakers;

    public WorldLanguage(String name)
    {
        setName(name);
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
