package tripletown.pala;

public class Ruoho implements Pala {

    private final int pisteet = 5;

    @Override
    public int getPisteet() {
        return this.pisteet;
    }

    @Override
    public String toString() {
        return "R";
    }

    
}
