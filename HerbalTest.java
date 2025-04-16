import static ENUM.Enum.*;

import java.util.ArrayList;

public class HerbalTest {

    // suma wartości herbaty (o podanym smaku) z koszyka
    static double wartosc(Koszyk k, String smak) {
        double wartosc = 0;
        ArrayList<Herbata> koszyk = k.getKoszyk();
        for(Herbata i : koszyk){
            if(i.getSmak().equals(smak)){
                if (Cennik.getWagaHurt(i) < i.getIlosc() && Cennik.getWagaHurt(i) > 0) {
                    wartosc += + i.getIlosc()*Cennik.getCenaHurt(i);
                } else {
                    wartosc += i.getIlosc()*Cennik.getCena(i);
                }
            }
        }
        return wartosc;
    }

    public static void main(String[] args) {

        // cennik
        Cennik cennik = Cennik.pobierzCennik();

        // dodawanie nowych cen do cennika
        cennik.dodaj(ZIELONA, "imbir", 4, 80, 70);	// zielona herbata o smaku imbirowym kosztuje 80 zł/kg jeśli klient kupi nie więcej niż 4 kg,
        // w innym przypadku kosztuje 70 zł/kg

        cennik.dodaj(CZARNA, "kiwi", 120);		// czarna herbata o smaku kiwi kosztuje 120 zł/kg niezależnie od ilości

        cennik.dodaj(CZERWONA, "truskawka", 80);	// czerwona herbata o smaku truskawkowym kosztuje 80 zł/kg niezależnie od ilości

        cennik.dodaj(ZIELONA, "jaśmin", 150);		// zielona herbata o smaku jaśminowym kosztuje 150 zł/kg niezależnie od ilości

        // Klient Herbal deklaruje kwotę 2000 zł na zakupy
        Klient herbal = new Klient("herbal", 2000);

        // Klient Herbal dodaje do listy zakupów
        // różne rodzaje herbaty: 5 kg zielonej imbirowej, 5 kg czerwonej truskawkowej,
        // 3 kg niebieskiej waniliowej, 3 kg czarnej kiwi
        herbal.dodaj(new Zielona("imbir", 5));
        herbal.dodaj(new Czerwona("truskawka", 5));
        herbal.dodaj(new Niebieska("wanilia", 3));
        herbal.dodaj(new Czarna("kiwi", 3));

        // Lista zakupów Herbala
        ListaZakupow listaHerbala = herbal.pobierzListeZakupow();

        System.out.println("Lista zakupów klienta " + listaHerbala);

        // Przed płaceniem, klient przepakuje herbaty z listy zakupów do koszyka.
        // Możliwe, że na liście zakupów są herbaty niemające ceny w cenniku,
        // w takim przypadku nie trafiłyby do koszyka
        Koszyk koszykHerbala = herbal.pobierzKoszyk();
        herbal.przepakuj();

        // Co jest na liście zakupów Herbala
        System.out.println("Po przepakowaniu, lista zakupów klienta " + herbal.pobierzListeZakupow());

        // Co jest w koszyku Herbala
        System.out.println("Po przepakowaniu, koszyk klienta " + koszykHerbala);

        // Jaka jest wartość herbaty o smaku truskawkowym w koszyku Herbala
        System.out.println("Herbata truskawkowa w koszyku Herbala kosztowała:  "
                + wartosc(koszykHerbala, "truskawka"));

        // Klient zapłaci...
        herbal.zaplac(false);	// płaci przelewem (false), bez prowizji

        // Ile Herbalowi zostało pieniędzy?
        System.out.println("Po zapłaceniu, Herbalowi zostało : " + herbal.pobierzPortfel() + " zł");

        // Mogło klientowi zabraknąć srodków, wtedy herbaty są odkładane,
        // w innym przypadku koszyk jest pusty po zapłaceniu
        System.out.println("Po zapłaceniu, koszyk klienta " + herbal.pobierzKoszyk());
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykHerbala);

        // Teraz przychodzi Kawuś,
        // deklaruje 660 zł na zakupy
        Klient kawus = new Klient("kawus", 660);

        // Nabrał herbaty za dużo jak na tę kwotę
        kawus.dodaj(new Czarna("kiwi", 4));
        kawus.dodaj(new Zielona("imbir", 3));

        // Co Kawuś ma na swojej liście zakupów
        System.out.println("Lista zakupów klienta " + kawus.pobierzListeZakupow());

        // Przepakowanie z listy zakupów do koszyka,
        // może się okazać, że na liście zakupów są herbaty niemające ceny w cenniku,
        // w takim razie zostałyby usunięte z koszyka (ale nie z listy zakupów)
        Koszyk koszykKawusia = kawus.pobierzKoszyk();
        kawus.przepakuj();

        // Co jest na liście zakupów Kawusia
        System.out.println("Po przepakowaniu, lista zakupów klienta " + kawus.pobierzListeZakupow());

        // A co jest w koszyku Kawusia
        System.out.println("Po przepakowaniu, koszyk klienta " + kawus.pobierzKoszyk());

        // Kawuś płaci
        kawus.zaplac(true);	// płaci kartą płatniczą (true), prowizja = 2,5%

        // Ile Kawusiowi zostało pieniędzy?
        System.out.println("Po zapłaceniu, Kawusiowi zostało : " + kawus.pobierzPortfel() + " zł");

        // Co zostało w koszyku Kawusia (za mało pieniędzy miał)
        System.out.println("Po zapłaceniu, koszyk klienta " + koszykKawusia);

        kawus.zwroc(CZARNA, "kiwi", 4);	// zwrot (do koszyka) herbaty czarnej o smaku kiwi w ilości 4 kg z ostatniej transakcji

        // Ile klientowi kawus zostało pieniędzy?
        System.out.println("Po zwrocie, klientowi kawus zostało: " + kawus.pobierzPortfel() + " zł");

        // Co zostało w koszyku klienta kawus
        System.out.println("Po zwrocie, koszyk klienta " + koszykKawusia);

    }
}


