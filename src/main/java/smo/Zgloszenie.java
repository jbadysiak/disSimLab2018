package smo;

import dissimlab.random.SimGenerator;
import smo.KoniecNiecierpliwienia;
import smo.Smo;
import smo.StartNiecierpliwienia;
import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

/**
 * Description: Klasa zgloszenia obsługiwanego w gnieździe obsługi.
 *
 * @author Dariusz Pierzchala
 */

public class Zgloszenie extends BasicSimObj {
    public KoniecNiecierpliwienia koniecNiecierpliwosci;
    public Smo smo;
    private double czasOdniesienia;
    private static int nr = 0;
    private int tenNr;
    private StartNiecierpliwienia startNiecierpliwienia;

    private int priorytet = 0;

    public Zgloszenie(double czas, Smo smo, int priorytet) {
        this(czas, smo);
        this.priorytet = priorytet;
    }

    public Zgloszenie(double czas, Smo smo) {
        czasOdniesienia = czas;
        setTenNr();
        this.smo = smo;
        SimGenerator simGenerator = new SimGenerator();
        priorytet = simGenerator.uniformInt(1, 10);
    }

    public void uruchomNiecierpliwienie() throws SimControlException {
        startNiecierpliwienia = new StartNiecierpliwienia(this);
    }

    public Smo getSmo() {
        return smo;
    }

    public void setSmo(Smo smo) {
        this.smo = smo;
    }

    public KoniecNiecierpliwienia getKoniecNiecierpliwosci() {
        return koniecNiecierpliwosci;
    }

    public void setKoniecNiecierpliwosci(KoniecNiecierpliwienia koniecNiecierpliwosci) {
        this.koniecNiecierpliwosci = koniecNiecierpliwosci;
    }

    @Override
    public void reflect(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean filter(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub
        return false;
    }

    public void setTenNr() {
        this.tenNr = nr++;
    }

    public int getTenNr() {
        return tenNr;
    }

    public static int getNr() {
        return nr;
    }

    public static void setNr(int nr) {
        Zgloszenie.nr = nr;
    }

    public int getPriorytet() {
        return priorytet;
    }

    @Override
    public String toString() {
        return String.valueOf(tenNr);
    }
}