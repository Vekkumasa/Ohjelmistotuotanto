package ohtu.kivipaperisakset;

import KSP.KPSTekoaly;
import KSP.KPSPelaajaVsPelaaja;
import KSP.KPSParempiTekoaly;
import KSP.KSP;
import KSP.KSPtehdas;
import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);
    private static KSP ksp;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                ksp = KSPtehdas.pvp();
                ksp.pelaa();
            } else if (vastaus.endsWith("b")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                ksp = KSPtehdas.pvDumbAi();
                ksp.pelaa();
            } else if (vastaus.endsWith("c")) {
                System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                ksp = KSPtehdas.pvSmartAi();
                ksp.pelaa();
            } else {
                break;
            }

        }

    }
}
