package tripletown.kayttoliittyma;

import java.util.Scanner;
import tripletown.pala.Pala;
import tripletown.sovellus.Peli;

public class Tekstikayttoliittyma {

    Peli peli;
    private final Scanner lukija;
    private final String[] palat = {"", "ruoho", "pensas", "puu", "talo", "kartano", "linna"};

    public Tekstikayttoliittyma() {
        this.peli = new Peli();
        this.lukija = new Scanner(System.in);
    }

    public void kaynnista() {
        peli.alustaPelilauta();

        while (true) {

            System.out.println("Pisteet: " + peli.pistetilanne());
            peli.liikutaKarhua();
            tulostaLauta();

            if (pelilautaTaynna()) {
                break;
            }

            while (true) {

                int pala = peli.arvoPala();
                System.out.println("Mihin ruutuun haluat asettaa palan " + palat[pala] + "? Sarake:");
                int x = kysyKoordinaatti();
                System.out.println("Rivi:");
                int y = kysyKoordinaatti();

                Pala asetettu = this.peli.asetaPala(pala, x, y);

                if (asetettu != null) {
                    peli.vierekkaistenPalojenHaku(asetettu);
                    break;
                }

                System.out.println("Ruutu ei ollut tyhjä!");
            }

        }
        System.out.println("Peli loppui.");
    }

    //Varmistaa että pelaajan antama luku on 0:n ja 5:n väliltä
    public int kysyKoordinaatti() {
        int luku = -1;

        while (true) {

            try {
                luku = Integer.parseInt(lukija.nextLine());
            } catch (Exception e) {
            }

            if (luku >= 0 && luku <= 5) {
                return luku;
            }

            System.out.println("Anna luku väliltä 0-5");
        }
    }

    private void tulostaLauta() {

        System.out.println("  0 1 2 3 4 5");

        for (int y = 0; y < 6; y++) {
            System.out.print(y + "|");
            for (int x = 0; x < 6; x++) {

                if (peli.getRuutu(x, y) == null) {
                    System.out.print("_|");
                } else {
                    System.out.print(peli.getRuutu(x, y) + "|");
                }
            }
            System.out.println("");
        }
    }

    private boolean pelilautaTaynna() {

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 6; x++) {
                if (peli.getRuutu(x, y) == null) {
                    return false;
                }
            }
        }
        return true;
    }

}
