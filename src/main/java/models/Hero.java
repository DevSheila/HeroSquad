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

    public String getName(){
        return name;
    }
    public int  getId(){
        return id;
    }
    public int getAge(){
        return age;
    }
    public String getPowers() {
        return powers;
    }

    public String getWeakness() {
        return weakness;
    }

    public int getDefence() {
        return defence;
    }

    public int getAttack() {
        return attack;
    }

    public static ArrayList<Hero> getHeroes() {
        return heroes;
    }
    public void updateHero(boolean occupied){

        this.occupied = occupied;
    }
    public void deleteHero(){
        heroes.remove(id-1);
    }

    public static Hero findById(int id){
        try{
            return heroes.get(id-1);

        }catch(IndexOutOfBoundsException exception){
            return null;
        }
    }
    public  String randomImage() {

        int imagescount = 5;
        JLabel MyImage = new JLabel(new ImageIcon("image" + (int)(Math.floor(Math.random()*imagescount)) + ".png"));
        imageUrl=String.valueOf(MyImage);

        return imageUrl;
    }

    public void setId(int id){
        this.id = id;
    }


    public static void clearAll(){
        heroes.clear();
    }
    public boolean isOccupied() {
        return occupied;
    }


}
