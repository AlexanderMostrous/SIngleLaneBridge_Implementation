import java.util.concurrent.Semaphore;

public class SafeRoundRobbinWithAdjustmentsScheduler extends Scheduler{

	private int nextColour;
	private long timeThreshold;
	private Semaphore blueSem = new Semaphore(1), redSem = new Semaphore(1);

	public SafeRoundRobbinWithAdjustmentsScheduler(double time)
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

				if((carCounter>=3)&&(c.getColour()==1))//There is an abundance of blue cars and current car is blue.
				{
					redSem.acquire();
					blueSem.release();
				}
				else if((carCounter<=-3)&&(c.getColour()==2))//There is an abundance of red cars and current car is red.
				{

				}
				else
				{
					if(c.getColour()==1)//If blue
					{
						blueSem.acquire();
					}
					else//If red
					{
						redSem.acquire();
					}

				}
				enterBridge(c);

				Thread.sleep((int)(this.timeToCross*1000));

				exitBridge(c);
				if(c.getColour()==1)
				{
					redSem.release();

				}
				else
				{
					blueSem.release();
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

	private void calculateThreshold()
	{
		if(this.timeToCross>=carArrivalWaitingTime)
			timeThreshold = (long) (timeToCross*3);
		else
	}
}
