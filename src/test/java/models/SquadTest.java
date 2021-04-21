package models;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SquadTest {

    @After
    public void tearDown() throws Exception {

    }
    private Squad setUpNewSquad() {
        ArrayList<Hero> heroes=new ArrayList<Hero>();
        Hero hero=new Hero("Absorbing Man",30,"Absorbing ","Can Absorb evil thought",20,60);
        Hero otherHero=new Hero("Abraxas",60,"Read Minds ","Gets tired fast",20,60);
        heroes.add(hero);
        heroes.add(otherHero);
        return new Squad(10,"queen","sexism",heroes);
    }

    @Test
    public void createInstanceOfSquad() throws Exception {
        Squad squad = setUpNewSquad();
        assertEquals(true, squad instanceof Squad);
    }



}