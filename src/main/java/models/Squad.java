package models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Squad {

    private int id;
    private int maxSize;
    private String name;
    private String cause;
    public static ArrayList<Squad> squads = new ArrayList<Squad>();

    private ArrayList<Hero> heroes;

    public Squad(int maxSize, String name, String cause, ArrayList<Hero> heroes) {
        this.name = name;
        this.maxSize = maxSize;
        this.cause = cause;
        this.heroes = heroes;
        squads.add(this);
        this.id = squads.size();

    }

    //getters
    public int getId() {
        return id;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }

    //getters for heroes and squads
    public static ArrayList<Squad> getSquads() {
        return squads;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    //other methods

    public static void clearAll(){
        squads.clear();
    }

    public static Squad findById(int id){
        try {
            return squads.get(id-1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }

    }

    public void deleteSquad(){
        squads.remove(id-1);
    }


    //setters
    public void setId(int id) {
        this.id = id;
    }


}