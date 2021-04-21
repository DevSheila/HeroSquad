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

}