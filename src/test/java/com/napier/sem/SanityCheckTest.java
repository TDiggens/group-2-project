package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SanityCheckTest
{
    static SanityCheck sanityCheck;

    @BeforeAll
    static void init()
    {
        sanityCheck = new SanityCheck();
    }

    @Test
    void listCitiesInCountryNull()
    {
        sanityCheck.listCitiesInCountry(null);
    }
}
