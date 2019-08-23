import java.util.concurrent.Semaphore;

public class SafeRoundRobbinScheduler extends Scheduler{

	private int nextColour;
	
	private Semaphore waitingBlue = new Semaphore(1), waitingRed = new Semaphore(1);

	/*
	 * To perasma einai asfales xwris sygkrouseis kai ta aftokinhta pernane 
	 * enallaks, ena kokkino, ena mple, ena kokkino, ena mple,...
	 * 
	 */
	public SafeRoundRobbinScheduler(double time, double cars)
	{
		super(time, cars);
		
		
		nextColour = 1;
	}

	public void crossBridge(Car c)
	{
		enterBridge(c);
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
