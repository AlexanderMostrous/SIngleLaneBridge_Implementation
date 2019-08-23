
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
	public UnsafeScheduler(double time, double cars){
		super(time, cars);	}

	public void crossBridge(Car c)
	{
		
		enterBridge(c);
		try
		{
			Thread.sleep((int)(this.timeToCross*1000));
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		exitBridge(c);
	}

	public synchronized void enterBridge(Car c)
	{
		c.setPassing(System.currentTimeMillis());
	}

	public synchronized void exitBridge(Car c) {
		c.setPassed(System.currentTimeMillis());
		c.setFinishedPassing(true);
	}
}
