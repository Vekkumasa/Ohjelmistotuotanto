package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
    private TextField tulosKentta;
    private TextField syoteKentta;
    private Button undo;
    private Button nollaa;
    private Sovelluslogiikka sovellus;
    
    
    public Nollaa(TextField tulosKentta, TextField syoteKentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tulosKentta = tulosKentta;
        this.syoteKentta = syoteKentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }
    
    public void suorita() {
        sovellus.nollaa();
        this.tulosKentta.setText("" + sovellus.tulos());
    }
    
    public void peru() {
        this.tulosKentta.setText("" + sovellus.edellinen());
    }
}
