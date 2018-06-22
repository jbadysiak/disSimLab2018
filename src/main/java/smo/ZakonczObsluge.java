package smo;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie końcowe aktywności gniazda obsługi. Kończy obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimEventSemaphore;
import dissimlab.simcore.SimParameters.SimDateField;
import py4j.GatewayServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ZakonczObsluge extends BasicSimEvent<Smo, Zgloszenie> {

    private Smo smoParent;
	private Zgloszenie zgloszenie;

    public ZakonczObsluge(Smo parent, double delay, Zgloszenie zgl) throws SimControlException {
    	super(parent, delay, zgl);
        this.smoParent = parent;
		this.zgloszenie = zgl;
    }

    public ZakonczObsluge(Smo parent, SimEventSemaphore semafor, Zgloszenie zgl) throws SimControlException {
    	super(parent, semafor, zgl);
        this.smoParent = parent;
    }

	public Smo getSmoParent() {
		return smoParent;
	}

	public void setSmoParent(Smo smoParent) {
		this.smoParent = smoParent;
	}

	public Zgloszenie getZgloszenie() {
		return zgloszenie;
	}

	public void setZgloszenie(Zgloszenie zgloszenie) {
		this.zgloszenie = zgloszenie;
	}

	@Override
	protected void onInterruption() throws SimControlException {
		// TODO
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {
		String s;
		GatewayServer gatewayServer = new GatewayServer(this);
		gatewayServer.start();

		try {
			Process p = Runtime.getRuntime().exec("python C:\\Users\\jakub.badysiak\\IdeaProjects\\DisSimLab2017_LAB11\\DisSimLab2017\\src\\main\\resources\\pythonPackage\\endOfService.py");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			//System.out.println("Standard output");
			while ((s = stdInput.readLine()) != null){
				System.out.println(s);
			}

			//System.out.println("Error output");
			while ((s = stdError.readLine()) != null){
				System.out.println(s);
			}

			//System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		gatewayServer.shutdown();
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}