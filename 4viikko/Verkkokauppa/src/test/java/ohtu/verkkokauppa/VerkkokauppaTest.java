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

public class VerkkokauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k =  new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
    }
    
    @Test
    public void ostoksenJalkeenPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(1)).thenReturn(10);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void useammanEriTuotteenOstaminenKutsuuTilisiirtoaOikeillaParametreilla() {
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kossu", 10));
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(2);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 15);
    }
    
    @Test
    public void useammanSamanTuotteenOstaminenKutsuuTilisiirtoaOikeillaParemtreilla() {     
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(1)).thenReturn(10);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
    }
    
    @Test
    public void kahdenEriTuotteenOstaminenJoistaToinenOnLoppunutKutsuuTilisiirtoaOikeillaParametreilla() {   
        when(viite.uusi()).thenReturn(42);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kossu", 10));
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }
    
    @Test
    public void aloitaAsiointiNollaaEdellisetOstokset() {
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kossu", 10));
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void jokaiselleMaksutapahtumalleOmaViitenumero() {
        when(viite.uusi()).thenReturn(1).thenReturn(2).thenReturn(3);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(1)).thenReturn(10);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), eq("33333-44455"), eq(5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(3), eq("12345"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void tuotePoistetaanOstoskoristaKutsuuVarastonMetodiaPalautavarastoon() {
        Tuote maito = new Tuote(1, "maito", 5);
        
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(maito);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.poistaKorista(1);
        
        verify(varasto, times(1)).palautaVarastoon(maito);
    }
    
}