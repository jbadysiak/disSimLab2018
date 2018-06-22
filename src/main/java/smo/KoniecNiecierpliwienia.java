package smo;

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

 */
public class KoniecNiecierpliwienia extends BasicSimEvent<Zgloszenie, Object>
{
    private Zgloszenie parent;

    public KoniecNiecierpliwienia(Zgloszenie parent, double delay) throws SimControlException
    {
    	super(parent, delay);
        this.parent = parent;
    }

    public KoniecNiecierpliwienia(Zgloszenie parent) throws SimControlException
    {
    	super(parent);
        this.parent = parent;
    }

    public Zgloszenie getParent() {
        return parent;
    }

    public void setParent(Zgloszenie parent) {
        this.parent = parent;
    }

    @Override
	protected void onInterruption() throws SimControlException {
        System.out.println(simTime()+" - "+simDate(SimDateField.HOUR24)+" - "+simDate(SimDateField.MINUTE)+" - "+simDate(SimDateField.SECOND)+" - "+simDate(SimDateField.MILLISECOND)+": Przerwanie niecierpliwości zgl. nr: " + parent.getTenNr());
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
            Process p = Runtime.getRuntime().exec("python C:\\Users\\jakub.badysiak\\IdeaProjects\\DisSimLab2017_LAB11\\DisSimLab2017\\src\\main\\resources\\pythonPackage\\endOfImpatience.py");

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

        gatewayServer.shutdown();	}

	@Override
	public Object getEventParams() {
		// TODO Auto-generated method stub
		return null;
	}
}