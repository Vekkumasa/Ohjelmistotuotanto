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

public class VarastoTest {
    Kirjanpito kirjanpito;
    Varasto varasto;
    Tuote maito;
    Tuote kossu;
    
    @Before
    public void setUp() {
        kirjanpito = mock(Kirjanpito.class);
        varasto = new Varasto(kirjanpito);
        maito = new Tuote(1, "maito", 5);
        kossu = new Tuote(2, "kossu", 10);
    }

    @Test
    public void tuoteOtetaanVarastosta() {
        varasto.otaVarastosta(maito);
        
        verify(kirjanpito).lisaaTapahtuma("otettiin varastosta maito");
    }
    
    @Test
    public void tuotePalautetaanVarastoon() {
        varasto.palautaVarastoon(kossu);
        
        verify(kirjanpito).lisaaTapahtuma("palautettiin varastoon kossu");
    }
    
    @Test
    public void virheellistaTuotetettaEiLoydy() {
        assertNull(varasto.haeTuote(10));
    }
    
}