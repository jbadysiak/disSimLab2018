package smo;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;


public class Otoczenie extends BasicSimObj {
    public Zglaszaj zglaszaj;
    public Smo smo;
	private Zgloszenie zgloszenie;

	public Otoczenie(Smo smo) throws SimControlException {
        // Powołanie instancji pierwszego zdarzenia
    	zglaszaj = new Zglaszaj(this, 0.0);
        // SMO dla zgłoszeń
        this.smo = smo;
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

	public Zglaszaj getZglaszaj() {
		return zglaszaj;
	}

	public void setZglaszaj(Zglaszaj zglaszaj) {
		this.zglaszaj = zglaszaj;
	}

	public Smo getSmo() {
		return smo;
	}

	public void setSmo(Smo smo) {
		this.smo = smo;
	}

	public Zgloszenie getZgloszenie() {
		return zgloszenie;
	}

	public void setZgloszenie(double czas, Smo smo) {
		this.zgloszenie = new Zgloszenie(czas, smo);
	}
}
