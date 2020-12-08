package KSP;

import Tekoaly.AI;
import Tekoaly.TekoalyParannettu;
import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KSP {

    private static final Scanner scanner = new Scanner(System.in);
    private AI ai;
    
    public KPSParempiTekoaly(AI ai) {
        this.ai = ai;
    }
    
    @Override
    protected String toisenSiirto() {
        String siirto = ai.annaSiirto();
        System.out.println("Tietokoneen siirto: " + siirto);
        ai.asetaSiirto(this.ekanSiirto);
        return siirto;
    }
}
