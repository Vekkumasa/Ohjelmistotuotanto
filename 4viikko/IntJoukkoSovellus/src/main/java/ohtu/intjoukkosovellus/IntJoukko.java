
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 
    
    private static IntJoukko x;
    private static int[] aTaulu;
    private static int[] bTaulu;

    public IntJoukko() {
        alkiot = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alkiot = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            return;
        }
        if (kasvatuskoko < 0) {
            return;
        }
        alkiot = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {   
        if (!kuuluu(luku)) {
            alkiot[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == alkiot.length) {
                int[] uusiTaulukko = kopioiTaulukko(alkiot);
                alkiot = uusiTaulukko;  
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                return true;
            }
        }
        return false;
    }

    private void siirraVasemmalle(int[] taulukko, int indeksi) {
        int apu;
        for (int i = indeksi; i < alkioidenLkm; i++) {
            apu = taulukko[i];
            taulukko[i] = taulukko[i + 1];
            taulukko[i + 1] = apu;
        }
    }
    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                alkiot[i] = 0;
                siirraVasemmalle(alkiot, i);
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private int[] kopioiTaulukko(int[] vanha) {
        int[] uusi = new int[vanha.length + kasvatuskoko];
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
        return uusi;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + alkiot[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += alkiot[i];
                tuotos += ", ";
            }
            tuotos += alkiot[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = alkiot[i];
        }
        return taulu;
    } 
    
    public static void aseta(IntJoukko a, IntJoukko b) {
        x = new IntJoukko();
        aTaulu = a.toIntArray();
        bTaulu = b.toIntArray();
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        aseta(a, b);
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        aseta(a, b);
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    x.lisaa(bTaulu[j]);
                }
            }
        }
        return x;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        aseta(a, b);
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.poista(bTaulu[i]);
        }
 
        return x;
    }   
}
