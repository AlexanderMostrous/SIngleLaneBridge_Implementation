import java.util.concurrent.Semaphore;

public class SafeRoundRobbinScheduler extends Scheduler{

	private int nextColour;
	
	private Semaphore waiting = new Semaphore(1);

	/*
	 * To perasma einai asfales xwris sygkrouseis kai ta aftokinhta pernane 
	 * enallaks, ena kokkino, ena mple, ena kokkino, ena mple,...
	 * 
	 */
	public SafeRoundRobbinScheduler(double time)
	{
		super(time);
		
		
		nextColour = 1;
	}

	public void crossBridge(Car c)
	{
			try 
			{
					waiting.acquire();
			} 
			catch (InterruptedException e1) {e1.printStackTrace();}
		
		
		
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
