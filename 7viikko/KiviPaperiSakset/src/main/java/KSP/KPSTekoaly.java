package KSP;

import Tekoaly.AI;
import Tekoaly.Tekoaly;
import java.util.Scanner;

public class KPSTekoaly extends KSP {

    private static final Scanner scanner = new Scanner(System.in);
    private AI ai;
    
    public KPSTekoaly(AI ai) {
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