package src;

import java.lang.reflect.InvocationTargetException;

import src.*;

public class Advent {
    
    public static int adventDay = 3;

    public static void main(String[] args) {
        System.out.println("Advent of Code 2022");
        // solveAll();
        solveLast();
    }
    
    public static void solveDay(int day) {
        try {
            Class<?> cls = Class.forName("src.Day" + day + ".Answer");
            cls.getDeclaredConstructor().newInstance();
        } catch (IllegalArgumentException | SecurityException | ClassNotFoundException | NoSuchMethodException 
            | InvocationTargetException | IllegalAccessException | InstantiationException ex) {

            ex.printStackTrace();
            System.out.println("Error getting class for Day" + day + ": " + ex.getMessage());
        }
    }

    public static void solveLast() {
        solveDay(adventDay);
    }
    
    public static void solveAll() {
        for (int day = 1; day <= adventDay; day++) {
            solveDay(day);
        }
    }
}
