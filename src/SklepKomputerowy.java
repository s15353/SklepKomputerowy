import java.text.SimpleDateFormat;
import java.util.Date;

public class SklepKomputerowy {
    private Produkt[] produkty = new Produkt[1000];
    private Klient[] klienci = new Klient[1000];
    private Zamowienie[] zamowienia = new Zamowienie[1000];
    private int liczbaProduktow = 0;
    private int liczbaKlientow = 0;
    private int liczbaZamowien = 0;

    public void dodajProdukt(Produkt produkt) {
        produkty[liczbaProduktow++] = produkt;
    }
    public void dodajKlienta(Klient klient) {
        klienci[liczbaKlientow++] = klient;
    }
    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produkty, int[] ilosci) {
        Zamowienie zamowienie = new Zamowienie();
        zamowienie.setId(++liczbaZamowien);
        zamowienie.setKlient(klient);
        zamowienie.setProdukty(produkty);
        zamowienie.setIlosci(ilosci);
        zamowienie.setDataZamowienia(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        zamowienie.setStatus("Nowe");
        zamowienia[liczbaZamowien - 1] = zamowienie;
        return zamowienie;
    }
    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        Produkt[] produkty = zamowienie.getProdukty();
        int[] ilosci = zamowienie.getIlosci();
        for (int i = 0; i < produkty.length; i++) {
            produkty[i].setIloscWMagazynie(produkty[i].getIloscWMagazynie() - ilosci[i]);
        }
    }
    public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getId() == idZamowienia) {
                zamowienia[i].setStatus(nowyStatus);
                break;
            }
        }
    }
    public void wyswietlProduktyWKategorii(String kategoria) {
        for (int i = 0; i < liczbaProduktow; i++) {
            if (produkty[i].getKategoria().equalsIgnoreCase(kategoria)) {
                produkty[i].wyswietlInformacje();
            }
        }
    }
    public void wyswietlZamowieniaKlienta(int idKlienta) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getKlient().getId() == idKlienta) {
                zamowienia[i].wyswietlSzczegoly();
                System.out.println();
            }
        }
    }
}
