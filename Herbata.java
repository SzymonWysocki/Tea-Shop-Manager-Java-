import ENUM.Enum;

import java.util.Objects;

public abstract class Herbata {
    private Enum rodzaj;
    private String smak;
    private int ilosc;

    public Herbata(Enum rodzaj, String smak) {
        this.rodzaj = rodzaj;
        this.smak = smak;
    }

    public Herbata(Enum rodzaj, String smak, int ilosc){
        this.rodzaj = rodzaj;
        this.smak = smak;
        this.ilosc = ilosc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Herbata herbata = (Herbata) o;
        return rodzaj == herbata.rodzaj && Objects.equals(smak, herbata.smak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rodzaj, smak);
    }

    public Enum getRodzaj(){
        return rodzaj;
    }

    public String getSmak() {
        return smak;
    }

    public int getIlosc() {
        return ilosc;
    }
    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
