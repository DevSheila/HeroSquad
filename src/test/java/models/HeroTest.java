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
    @Test
    public void createInstanceOfHero_true() throws Exception{
        Hero hero=setUpNewHero();
        assertEquals(true,hero instanceof Hero);
    }

    @Test
    public void returnAllInstancesOfHero() throws Exception{
        Hero hero=setUpNewHero();
        Hero otherHero=new Hero("Carmen Sandiego",19,"Pick Pocketing","flu",78,90);
        assertEquals(2,Hero.getHeroes().size());
    }
}