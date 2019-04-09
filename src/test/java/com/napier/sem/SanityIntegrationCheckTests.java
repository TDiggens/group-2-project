package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SanityIntegrationCheckTest
{
    static SanityCheck sanityCheck;

    @BeforeAll
    static void init()
    {
        sanityCheck = new SanityCheck();
        sanityCheck.connect("localhost:33060");
    }

    @Test
    void listCitiesInCountryNull()
    {
        System.out.println("Output for test listCitiesInCountryNull: " + '\n');
        sanityCheck.listCitiesInCountry(null);
    }

}
