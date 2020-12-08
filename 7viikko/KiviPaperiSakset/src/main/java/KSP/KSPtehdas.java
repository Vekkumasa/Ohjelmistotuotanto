package KSP;

import Tekoaly.*;

public class KSPtehdas {
    
    public static KSP pvp() {
        return new KPSPelaajaVsPelaaja();
    }
    
    public static KSP pvDumbAi() {
        return new KPSTekoaly(new Tekoaly());
    }
    
    public static KSP pvSmartAi() {
        return new KPSParempiTekoaly(new TekoalyParannettu(20)); 
    }
}
