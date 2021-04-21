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
        return new Hero("Paper Cut",30,"Make any weapon from paper ","water",56,89);
    }
    @Test
    public void createInstanceOfHero_true() throws Exception{
        Hero hero=setUpNewHero();
        assertEquals(true,hero instanceof Hero);
    }
}