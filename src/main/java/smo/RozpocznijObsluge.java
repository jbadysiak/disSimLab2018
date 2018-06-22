package smo;
/**
 * @author Dariusz Pierzchala
 * 
 * Description: Zdarzenie początkowe aktywności gniazda obsługi. Rozpoczyna obsługę przez losowy czas obiektów - zgłoszeń.
 */

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;
import py4j.GatewayServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RozpocznijObsluge extends BasicSimEvent<Smo, Zgloszenie>
{
    private Smo smoParent;
    private SimGenerator generator;

    public RozpocznijObsluge(Smo parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
        this.smoParent = parent;
    }

    public RozpocznijObsluge(Smo parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
        this.smoParent = parent;
    }

	public Smo getSmoParent() {
		return smoParent;
	}

	public void setSmoParent(Smo smoParent) {
		this.smoParent = smoParent;
	}

	public SimGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(SimGenerator generator) {
		this.generator = generator;
	}

	@Override
	protected void onInterruption() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onTermination() throws SimControlException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void stateChange() throws SimControlException {

		String s = null;

		GatewayServer gatewayServer = new GatewayServer(this);
		gatewayServer.start();

		try {
			Process p = Runtime.getRuntime().exec("python C:\\Users\\jakub.badysiak\\IdeaProjects\\DisSimLab2017_LAB11\\DisSimLab2017\\src\\main\\resources\\pythonPackage\\startOfService.py");

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

//    	if (smoParent.liczbaZgl() > 0)
//        {
//        	// Zablokuj gniazdo
//        	smoParent.setWolne(false);
//        	// Pobierz zgłoszenie
//        	Zgloszenie zgl = smoParent.usun();
//        	// Przerwanie niecierpliwosci
//        	zgl.koniecNiecierpliwosci.interrupt();
//        	// Wygeneruj czas obsługi
//        	double czasObslugi = generator.normal(9.0, 1.0);
//            System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": SMO- Początek obsługi zgl. nr: " + zgl.getTenNr());
//        	// Zaplanuj koniec obsługi
//        	smoParent.zakonczObsluge = new ZakonczObsluge(smoParent, czasObslugi, zgl);
//        }
		
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}