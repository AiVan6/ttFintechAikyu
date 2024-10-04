package com.example.ttwebzaim.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StopFactorServiceTest {

    @Autowired
    private StopFactorService stopFactorService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void searchStopFactor() {
        String regPersonString = "ISAAC ABRAHAM";
        String verifiedNameString = "ISAAC ABRAHAM";

        boolean result = stopFactorService.searchLevenshteinDistance(regPersonString, verifiedNameString);

        assertTrue(result, "Расстояние Левенштейна должно быть меньше distanceRatioThreshold");
    }

    @Test
    void searchLevenshteinDistance(){

        List<String> regPersons = new ArrayList<>(Arrays.asList("ISAAC ABRAHAM"));
        List<String> verifiedNames = new ArrayList<>(Arrays.asList("ABRAHAMOGADA", "SAMUELOGADA"));

        int result = stopFactorService.findMaxLevenshteinDistance(regPersons, verifiedNames);

        assertEquals(result,10);
    }

    @Test
    void testGenerateWord(){
        List<String> list = new ArrayList<>(Arrays.asList("A","B","C","D"));

        List<String> result = stopFactorService.generateWord(list);

        assertEquals(result,Arrays.asList("AB","AC","AD","BC","BD","CD"));
    }

}