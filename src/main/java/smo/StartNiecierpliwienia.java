package smo;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimParameters.SimDateField;
import py4j.GatewayServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Dariusz Pierzchala
 * Description: Zdarzenie początkowe niecierpliwości zgłoszenia. Rozpoczyna niecierpliwość przez losowy czas
 */
public class StartNiecierpliwienia extends BasicSimEvent<Zgloszenie, Object>
{
    private SimGenerator generator;
    private Zgloszenie parent;

    public StartNiecierpliwienia(Zgloszenie parent, double delay) throws SimControlException
    {
    	super(parent, delay);
    	generator = new SimGenerator();
        this.parent = parent;
    }

    public StartNiecierpliwienia(Zgloszenie parent) throws SimControlException
    {
    	super(parent);
    	generator = new SimGenerator();
        this.parent = parent;
    }

	public SimGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(SimGenerator generator) {
		this.generator = generator;
	}

	public Zgloszenie getParent() {
		return parent;
	}

	public void setParent(Zgloszenie parent) {
		this.parent = parent;
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
		String s;

		GatewayServer gatewayServer = new GatewayServer(this);
		gatewayServer.start();

		try {
			Process p = Runtime.getRuntime().exec("python C:\\Users\\jakub.badysiak\\IdeaProjects\\DisSimLab2017_LAB11\\DisSimLab2017\\src\\main\\resources\\pythonPackage\\startOfImpatience.py");

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

//    	System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Początek niecierpliwości zgl. nr: " + parent.getTenNr());
//        double odstep = generator.normal(15.0, 1.0);
//        parent.koniecNiecierpliwosci = new KoniecNiecierpliwienia(parent, odstep);
	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}