import ENUM.Enum;

import java.util.ArrayList;
import java.util.HashMap;

public class Klient {
    private String imie;
    private double kasa;
    private double kasaKopia;
    private int id;
    private static int idTemp = 0;
    private ListaZakupow lista;
    private Koszyk koszyk;
    private ArrayList<Herbata> koszykPoprzedni = new ArrayList<>();
    int czyKoszykILista = 0;

    private HashMap<Herbata,ArrayList<Double>> kupione = new HashMap<>();

    public Klient(String imie, int kasa){

        this.id = idTemp++;
        this.imie = imie;
        this.kasa = kasa;
        this.kasaKopia = this.kasa;
        this.czyKoszykILista++;

        if(czyKoszykILista == 1){
            this.lista = new ListaZakupow();
            this.koszyk = new Koszyk();
        }
    }
    public void dodaj(Herbata herbata){
        lista.dodaj(herbata);
    }

    public ListaZakupow pobierzListeZakupow(){
        lista.getImie(this.imie);
        return lista;
    }
    public void przepakuj(){
        koszyk.przepakowywanie(lista);
        koszyk.getImie(this.imie);
    }
    public Koszyk pobierzKoszyk() {
        return koszyk;
    }
    public double pobierzPortfel(){
        return kasa;
    }
    public void zaplac(boolean metoda){// false - bez prowizji

        double wartoscHerbaty = 0;
        ArrayList<Herbata> tempKoszyk = koszyk.getKoszyk();
        ArrayList<Herbata> zadrogie = new ArrayList<>();

        double prowizja;
        prowizja = metoda ? 1.025 : 1;

        double cena = 0;

        for(Herbata i : tempKoszyk){
            double wartoscTemp = 0;

            if (Cennik.getWagaHurt(i) < i.getIlosc() && Cennik.getWagaHurt(i) > 0) {
                cena = Cennik.getCenaHurt(i);
            } else {
                cena = Cennik.getCena(i);
            }

            wartoscTemp = i.getIlosc()*cena;

                if ((wartoscHerbaty + wartoscTemp) <= kasa) {
                    wartoscHerbaty += wartoscTemp;

                    ArrayList<Double> arrTemp = new ArrayList<>();
                    arrTemp.add((double)i.getIlosc());
                    arrTemp.add(cena);
                    kupione.put(i, arrTemp);

                } else {
                    for(int ilosc = 1; ilosc < i.getIlosc(); ilosc++){

                        if(Cennik.getWagaHurt(i) < ilosc && Cennik.getWagaHurt(i) > 0){
                            wartoscTemp = ilosc * Cennik.getCenaHurt(i);
                            cena = Cennik.getCenaHurt(i);
                        }else{
                            wartoscTemp = ilosc * Cennik.getCena(i);
                            cena = Cennik.getCena(i);
                        }

                        if((wartoscHerbaty + wartoscTemp) <= kasa){
                            if(wartoscHerbaty + (ilosc + 1) * cena <= kasa){
                                continue;
                            }else {
                                wartoscHerbaty += wartoscTemp;

                                ArrayList<Double> arrTemp = new ArrayList<>();
                                arrTemp.add((double)i.getIlosc());
                                arrTemp.add(cena);
                                kupione.put(i, arrTemp);

                                i.setIlosc(i.getIlosc() - ilosc);
                                zadrogie.add(i);
                                break;
                            }

                        }
                    }
                }
        }
        kasa = kasa - (wartoscHerbaty*prowizja);
        koszyk.koszykPoZaplacie(zadrogie);
    }
    public void zwroc(Enum rodzaj, String smak, int ilosc){
        ArrayList<Herbata> koszykPoprzedni = koszyk.getKoszykKopia();

        for(Herbata i : koszykPoprzedni){
            if(i.getRodzaj().equals(rodzaj) && i.getSmak().equals(smak) && ilosc <= kupione.get(i).get(0) && kasa <= kasaKopia){

                boolean x = true;
                for(Herbata j : koszyk.getKoszyk()){
                    if(j.getRodzaj().equals(rodzaj) && j.getSmak().equals(smak)) {
                        x = false;
                        j.setIlosc(ilosc + j.getIlosc());
                    }
                }
                i.setIlosc(ilosc);
                if(x)
                    koszyk.dodaj(i);

                if(Cennik.getWagaHurt(i) < ilosc && Cennik.getWagaHurt(i) > 0){
                    kasa += Cennik.getCenaHurt(i) * ilosc;
                }else{
                    kasa += Cennik.getCena(i) * ilosc;
                }
            }
        }
    }
}
