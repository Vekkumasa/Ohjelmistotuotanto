package ohtu.verkkokauppa;

import java.util.ArrayList;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.Kauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KirjanpitoTest {

    Kirjanpito kirjanpito;
    Viitegeneraattori viite;
    
    @Before
    public void setUp() {
        kirjanpito = new Kirjanpito();
        viite = new Viitegeneraattori();
    }

    @Test
    public void kirjanpitoonLisataanKirjaus() {
        kirjanpito.lisaaTapahtuma("testi");
        ArrayList<String> lista = kirjanpito.getTapahtumat();
        
        assertEquals(lista.get(0), "testi");
    }
    
    @Test
    public void viitegeneraattoriTesti() {
        int luku = viite.uusi();
        
        assertEquals(1, luku);
    }
    
    
}