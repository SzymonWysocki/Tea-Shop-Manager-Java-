import java.util.ArrayList;
public class ListaZakupow{
    private String imieKlienta;
    private ArrayList<Herbata> lista;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(imieKlienta + ": ");

        if(!lista.isEmpty()) {
            sb.append("\n");
            for (Herbata i : lista) {
                sb.append(i.getRodzaj() +  ", smak: " + i.getSmak() + ", ilosc: " + i.getIlosc() + "kg, cena: ");
                try {
                    if (Cennik.getWagaHurt(i) < i.getIlosc() && Cennik.getWagaHurt(i) > 0) {
                        sb.append(Cennik.getCenaHurt(i));
                    } else {
                        sb.append(Cennik.getCena(i));
                    }
                } catch (Exception ex) {
                    sb.append(" -1");
                }
                sb.append("zł " + "\n");
            }
        }else{
            sb.append("--pusta");
        }
        return sb.toString();
    }

    public ArrayList<Herbata> getLista() {
        return lista;
    }
    public void usuwanie(){
        ArrayList<Herbata> temp = new ArrayList<>();
        for(Herbata i : lista){
            try{
                if(Cennik.getCena(i) > -1){
                    continue;
                }
            }catch(Exception ex){
                temp.add(i);
            }
        }
        lista = new ArrayList<Herbata>();
        lista = temp;
    }
    public ListaZakupow() {
        this.lista = new ArrayList<Herbata>();
    }
    public void dodaj(Herbata herbata){
        lista.add(herbata);
    }
    public void getImie(String imie){
        this.imieKlienta = imie;
    }
}
