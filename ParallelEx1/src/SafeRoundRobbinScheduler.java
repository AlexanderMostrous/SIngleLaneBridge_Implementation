import java.util.concurrent.Semaphore;

public class SafeRoundRobbinScheduler extends Scheduler{

	private int nextColour;

	private Semaphore blueSem = new Semaphore(1), redSem = new Semaphore(1);

	/*
	 * To perasma einai asfales xwris sygkrouseis kai ta aftokinhta pernane 
	 * enallaks, ena kokkino, ena mple, ena kokkino, ena mple,...
	 * 
	 */
	public SafeRoundRobbinScheduler(double time)
	{
		super(time);
		nextColour = 0;
	}

	public void crossBridge(Car c)
	{
		while(true){
		try 
		{
			
			if(nextColour==0)//Beginning condition. Car is the first car.
			{
				if(c.getColour()==1)//If is blue car
				{
					redSem.acquire();
					nextColour=1;
				}
				else//If is red car
				{
					blueSem.acquire();
					nextColour=2;
				}
			}

			if(c.getColour()==1)//If blue
			{
				blueSem.acquire();
				//System.out.println("Blue semaphore acquired.");
			}
			else//If red
			{
				redSem.acquire();
				//System.out.println("Red semaphore acquired.");
			}


			enterBridge(c);

			Thread.sleep((int)(this.timeToCross*1000));

			exitBridge(c);
			if(c.getColour()==1)
			{
				redSem.release();
				//System.out.println("Red semaphore released.");
			}
			else
			{
				blueSem.release();
				//System.out.println("Blue semaphore released.");
			}
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
