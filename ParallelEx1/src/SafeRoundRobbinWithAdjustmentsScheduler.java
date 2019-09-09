import java.util.concurrent.Semaphore;

public class SafeRoundRobbinWithAdjustmentsScheduler extends Scheduler{

	private int nextColour;
	private long timeThreshold, timeLastBluePassed, timeLastRedPassed;
	private Semaphore blueSem = new Semaphore(1), redSem = new Semaphore(1), arbitrator = new Semaphore(1);

	/*
	 * Not yet Working as Planned
	 */
	public SafeRoundRobbinWithAdjustmentsScheduler()
	{
		calculateThreshold();
		nextColour = 0;
		timeLastBluePassed = 0;
		timeLastRedPassed = 0;
	}

	public void crossBridge(Car c)
	{
		long currentTime;
		
		while(true){
			try 
			{
				arbitrator.acquire();
				if(nextColour==0)//Beginning condition. Car is the first car.
				{
					currentTime = System.currentTimeMillis();
					timeLastBluePassed = currentTime;
					timeLastRedPassed = currentTime;
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
					
				currentTime = System.currentTimeMillis();
				
				//If we have a big wait in queue.
				if(currentTime-timeLastBluePassed>timeThreshold||currentTime-timeLastRedPassed>timeThreshold)//Big wait in queue.
				{//If the big wait is on blue cars and the car trying to cross is blue
					
					if(currentTime-timeLastBluePassed>timeThreshold&&nextColour==1)
					{//Then the blue queue must be prioritized.
						blueSem.release();
					}//If the big wait is on red cars and the car trying to cross is red
					else if(currentTime-timeLastRedPassed>timeThreshold&&nextColour==2)
					{//Then the red queue must be prioritized.
						redSem.release();
					}
					
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

				Thread.sleep((int)(Main.crossingTime*1000));

				exitBridge(c);
				if(c.getColour()==1)
				{
					timeLastBluePassed = System.currentTimeMillis();
					redSem.release();
				}
				else
				{
					timeLastRedPassed = System.currentTimeMillis();
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
		if(Main.crossingTime>=1/Main.carRate)//If time taken for the bridge-crossing is bigger than the time taken for the next car to arrive then 
			timeThreshold = (long) (Main.crossingTime*2000);
		else
			timeThreshold = (long) (Main.crossingTime*1500);
		System.out.println(timeThreshold);
	}
}