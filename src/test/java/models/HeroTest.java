package models;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @After
    public void tearDown() throws Exception {
        Hero.clearAll();
    }
    private Hero setUpNewHero() {
        return new Hero("Paper Cut",20,"Make any weapon from paper ","water",56,89);
    }
    private Hero setUpOtherHero() {
        return new Hero("Carmen Sandiego",19,"Pick Pocketing","flu",78,90);
    }
    @Test
    public void createInstanceOfHero_true() throws Exception{
        Hero hero=setUpNewHero();
        assertEquals(true,hero instanceof Hero);
    }

    @Test
    public void returnAllInstancesOfHero() throws Exception{
        Hero hero=setUpNewHero();
        Hero otherHero=setUpOtherHero();
        assertEquals(2,Hero.getHeroes().size());
    }
    @Test
    public void findById() throws Exception{
        Hero hero=setUpNewHero();
        Hero otherHero=setUpOtherHero();
        Hero foundHero=Hero.findById(1);
        assertEquals(hero,foundHero);
    }

    @Test
    public void testIfInstanceIsUpdated() throws Exception {
        Hero hero=setUpNewHero();
        int formerID=hero.getId();
        boolean formerOccupied=hero.isOccupied();
        hero.updateHero(true);
        assertEquals(formerID,hero.getId());
        assertNotEquals(formerOccupied,hero.isOccupied());
    }

    @Test
    public void deleteHeroById() {
        Hero hero=setUpNewHero();

        Hero otherHero=setUpOtherHero();
        hero.deleteHero();
        assertEquals(1,Hero.getHeroes().size());
        assertEquals(Hero.getHeroes().get(0).getId(),2);


    }
    @Test
    public void deleteAllHeroes() {
        Hero hero=setUpNewHero();
        Hero otherHero=setUpOtherHero();
        hero.clearAll();
        assertEquals(0,Hero.getHeroes().size());

    }
}