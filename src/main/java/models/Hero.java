package models;

import javax.swing.*;
import java.util.ArrayList;

public class Hero {
    private int id;
    private String name;
    private int age;
    private String imageUrl;
    private String powers;
    private String weakness;
    private int defence;
    private int attack;

    private boolean occupied;
    private static ArrayList<Hero> heroes = new ArrayList<Hero>();
}
