
public class UnsafeScheduler extends Scheduler{

	private int counter,totalCars;
	
	/*
	 * To perasma den einai asfales kai mporei na
	 * periexei sygkrouseis, ka8ws ka8e aftokinhto 
	 * pernaei amesws afotou ftasei sth gefyra,
	 * adiaforwntas gia to an erxetai allo aftokinhto
	 * apo apenanti.
	 * 
	 */
	public UnsafeScheduler(){
				
	}

	public synchronized void crossBridge(Car c)
	{
		enterBridge(c);
		exitBridge(c);
	}

	public synchronized void enterBridge(Car c)
	{
		c.setPassing(System.currentTimeMillis());
	}

	public void exitBridge(Car c) {
		c.setPassed(System.currentTimeMillis());
	}
}
