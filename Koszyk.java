import java.util.ArrayList;

public class Koszyk {

    private ArrayList<Herbata> koszyk;
    private ArrayList<Herbata> koszykKopia;
    private String imieKlienta;

    public Koszyk() {
        this.koszyk = new ArrayList<Herbata>();
    }
    public void przepakowywanie(ListaZakupow lista){
        ArrayList<Herbata> temp = lista.getLista();
        for(Herbata i : temp){
            try{
                if(Cennik.getCena(i) > -1){
                    koszyk.add(i);
                }
            }catch(Exception ex){
                continue;
            }
        }
        lista.usuwanie();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(imieKlienta + ":");

        if(!koszyk.isEmpty()) {
            sb.append("\n");
            for (Herbata i : koszyk) {
                sb.append(i.getRodzaj() + ", smak: " + i.getSmak() + ", ilosc: " + i.getIlosc() + "kg, cena: ");
                if (Cennik.getWagaHurt(i) < i.getIlosc() && Cennik.getWagaHurt(i) > 0) {
                    sb.append(Cennik.getCenaHurt(i));
                } else {
                    sb.append(Cennik.getCena(i));
                }
                sb.append("zł\n");
            }
        }else{
            sb.append(" --pusto");
        }
        return sb.toString();
    }
    public void koszykPoZaplacie(ArrayList<Herbata> nowyKoszyk){
        this.koszykKopia = this.koszyk;
        this.koszyk = nowyKoszyk;
    }
    public void dodaj(Herbata herbata){
        koszyk.add(herbata);
    }

    public void usun(Herbata usun){
        for(Herbata i : koszyk){
            if(i.equals(usun))
                koszyk.remove(usun);
        }
    }
    public void getImie (String imie){
        this.imieKlienta = imie;
    }
    public ArrayList<Herbata> getKoszyk() {
        return koszyk;
    }
    public ArrayList<Herbata> getKoszykKopia() {
        return koszykKopia;
    }
}
