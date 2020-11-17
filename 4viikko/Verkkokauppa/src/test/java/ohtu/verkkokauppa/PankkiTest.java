package ohtu.verkkokauppa;

import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.Kauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PankkiTest {

    Pankki pankki;
    Kirjanpito kirjanpito;
    String tililta;
    String tilille;
    int viitenumero;
    
    @Before
    public void setUp() {
        kirjanpito = mock(Kirjanpito.class);
        pankki = new Pankki(kirjanpito);
        tililta = "1234";
        tilille = "4321";
        viitenumero = 2;
    }

    @Test
    public void tilisiirtoKirjataanKirjanpitoon() {
        pankki.tilisiirto("pekka", viitenumero, tililta, tilille, 0);
        
        verify(kirjanpito).lisaaTapahtuma("tilisiirto: tililtä " + tililta + " tilille " + tilille + " viite " + viitenumero + " summa 0e" );
    }
    
}