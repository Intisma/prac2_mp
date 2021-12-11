package tests;

import dataGenerator.RandomSetGenerator;
import information.ADTsetResources;

public class StaticTest {
    public static void main(String[] args) {
        RandomSetGenerator setGenerator = new RandomSetGenerator();
        ADTsetResources set = setGenerator.generateSet(false);
        System.out.println(set);

    }
}
