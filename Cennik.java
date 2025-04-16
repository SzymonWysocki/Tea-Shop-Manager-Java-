import ENUM.Enum;

import java.util.ArrayList;
import java.util.HashMap;

public class Cennik {

    private static Cennik instance = null;

    private static HashMap<Herbata, ArrayList<Integer>> MapaCen;
    private Cennik() {
        MapaCen = new HashMap<>();
    }

    public static Cennik pobierzCennik(){
        if (instance == null)
            instance = new Cennik();

        return instance;
    }
    public void dodaj(Enum rodzaj, String smak, int iloscZnizka, int cena, int cenaZnizka){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(iloscZnizka);
        arr.add(cena);
        arr.add(cenaZnizka);

        String nazwaKlasy = rodzaj.name();

        switch(rodzaj){
            case CZARNA -> MapaCen.put(new Czarna(smak), arr);
            case ZIELONA -> MapaCen.put(new Zielona(smak), arr);
            case NIEBIESKA -> MapaCen.put(new Niebieska(smak), arr);
            case CZERWONA -> MapaCen.put(new Czerwona(smak), arr);
        }
    }
    public void dodaj(Enum rodzaj, String smak, int cena){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int iloscZnizka = 0;
        int cenaZnizka = 0;

        arr.add(iloscZnizka);
        arr.add(cena);
        arr.add(cenaZnizka);

        switch(rodzaj){
            case CZARNA -> MapaCen.put(new Czarna(smak), arr);
            case ZIELONA -> MapaCen.put(new Zielona(smak), arr);
            case NIEBIESKA -> MapaCen.put(new Niebieska(smak), arr);
            case CZERWONA -> MapaCen.put(new Czerwona(smak), arr);
        }
    }
    public static int getWagaHurt(Herbata herbata){
        return MapaCen.get(herbata).get(0);
    }
    public static int getCena(Herbata herbata){
        return MapaCen.get(herbata).get(1);
    }
    public static int getCenaHurt(Herbata herbata){
        return MapaCen.get(herbata).get(2);
    }
}
