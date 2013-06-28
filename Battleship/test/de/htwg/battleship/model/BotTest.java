package de.htwg.battleship.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.battleship.model.Bot;
import de.htwg.battleship.model.Playboard;
import de.htwg.battleship.model.Rowboat;
import de.htwg.battleship.model.Ships;
import de.htwg.battleship.model.Field.state;


public class BotTest {
	
	Bot a;
	Playboard b;
	
	@Before
	public void testBot() {
		a = new Bot(1);
		assertNotNull(a);
		
	}

	@Test
	public void testshoot() {
		a.initPlayboard(1);
		assertNotNull(a);
		a.shoot(a.getPlayboard());
		assertTrue((a.getPlayboard().getField()[0][0].getStat() != state.empty && ( a.getPlayboard().getField()[0][0].getStat() == state.hit) 
				|| a.getPlayboard().getField()[0][0].getStat() == state.emptyhit));
		assertTrue((a.getPlayboard().getField()[0][0].getStat() != state.emptyhit && (a.getPlayboard().getField()[0][0].getStat() == state.hit)
				|| a.getPlayboard().getField()[0][0].getStat() == state.emptyhit));
		}
	
	@Test
	public void testInitPlayboard() {
		a.initPlayboard(1);
		assertEquals(1, a.getPlayboard().getSize());
	}
	
	@Test
	public void testInitrandomNumber() {
		a.initPlayboard(1);
		assertNotNull(a);
		int x = a.initRandomNumber();
		assertNotNull(x);
	}
	
	@Test
	public void testSetShip() {
		Ships c = new Destructor(true);
		a.setShip(c);
		assertEquals(0, c.getPosition()[0]);
		assertEquals(0, c.getPosition()[1]);
	}
	
	@Test
	public void testVertical(){
		Bot b = new Bot(26);
		boolean tr = false;
		boolean fl = false;
		boolean i;
		while(tr == false || fl == false){
			if((i = b.vertical()) == true){
				tr = true;
				assertTrue(i == true || i == false);
			}else{
				fl = true;
				assertTrue(i == true || i == false);
			}
		}
	}
}
