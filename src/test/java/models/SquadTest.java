package models;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SquadTest {

    @After
    public void tearDown() throws Exception {
        Squad.clearAll();

    }
    private Squad setUpNewSquad() {
        ArrayList<Hero> heroes=new ArrayList<Hero>();
        Hero hero=new Hero("Paper Cut",20,"Make any weapon from paper ","water",56,89);;
        Hero otherHero=new Hero("Carmen Sandiego",19,"Pick Pocketing","flu",78,90);
        heroes.add(hero);
        heroes.add(otherHero);
        return new Squad(10,"Myth Busters","justice and equality",heroes);
    }
    @Test
    public void createInstanceOfSquad() throws Exception {
        Squad squad = setUpNewSquad();
        assertEquals(true, squad instanceof Squad);
    }

    @Test
    public void returnAllInstances_true() throws Exception {
        Squad squad = setUpNewSquad();
        Squad otherSquad = setUpNewSquad();
        assertEquals(2, Squad.getSquads().size());
    }

    @Test
    public void SquadInstancesAreContainedInSquadClass() throws Exception {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        assertTrue(Squad.getSquads().contains(squad));
        assertTrue(Squad.getSquads().contains(otherSquad));
    }

    @Test
    public void findSquadById() throws Exception {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        Squad foundSquad=Squad.findById(1);
        assertEquals(squad,foundSquad);
    }

    @Test
    public void deleteSquadByID() {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        squad.deleteSquad();
        assertEquals(1,Squad.getSquads().size());
        assertEquals(Squad.getSquads().get(0).getId(),2);

    }
    @Test
    public void deleteAllSquads() {
        Squad squad=setUpNewSquad();
        Squad otherSquad=setUpNewSquad();
        Squad.clearAll();
        assertEquals(0,Squad.getSquads().size());

    }




}