package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void searchTikkanen() {
        assertNull(stats.search("tiki-talk"));
    }
    
    @Test
    public void searchYzerman() {
        assertEquals(String.format("%-20s","Yzerman") + " DET " + String.format("%2d",42) + " + " + String.format("%2d",56) + " = " + "98", stats.search("Yzerman").toString());
    }
    
    @Test
    public void team() {
        ArrayList<Player> lista = (ArrayList) stats.team("EDM");
        assertEquals(3, lista.size());
        assertEquals(lista.get(2).getName(), "Gretzky");
    }
    
    @Test
    public void topScorers() {
        ArrayList<Player> lista = (ArrayList) stats.topScorers(2);
        assertEquals(3, lista.size());
        assertEquals(124, lista.get(0).getPoints());
        assertEquals(98, lista.get(2).getPoints());
    }
}
