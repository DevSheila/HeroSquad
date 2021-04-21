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

    public Hero(String name, int age, String powers, String weakness, int defence, int attack){
        this.name = name;
        this.age = age;
        this.powers=powers;
        this.weakness= weakness;
        this.defence =defence;
        this.attack = attack;
        String imageUrl = "png";
        this.imageUrl = imageUrl;
        heroes.add(this);
        this.id= heroes.size();


    }
}
