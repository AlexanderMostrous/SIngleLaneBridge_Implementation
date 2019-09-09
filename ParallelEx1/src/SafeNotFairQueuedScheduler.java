import java.util.concurrent.Semaphore;

public class SafeNotFairQueuedScheduler extends Scheduler
{
	private Semaphore bridgeOccupied = new Semaphore(1);

	public void crossBridge(Car c)
	{
		while(true)
		{
			try 
			{
				bridgeOccupied.acquire();
				enterBridge(c);
				Thread.sleep((int)(Main.crossingTime*1000));
				exitBridge(c);
				bridgeOccupied.release();
				
				break;
			}
			catch (InterruptedException e){e.printStackTrace();}
		}
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