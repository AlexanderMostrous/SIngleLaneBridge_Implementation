
public class SafeRoundRobbinScheduler extends Scheduler{

	private boolean bridgeIsEmpty;
	private int nextRedToPass, nextBlueToPass, nextColour;
	

	/*
	 * To perasma einai asfales xwris sygkrouseis kai ta aftokinhta pernane 
	 * enallaks, ena kokkino, ena mple, ena kokkino, ena mple,...
	 * 
	 */
	public SafeRoundRobbinScheduler()
	{
		bridgeIsEmpty = true;
		nextBlueToPass = 1;
		nextRedToPass = 1;
		nextColour = 1;
	}

	public void crossBridge(Car c)
	{
		enterBridge(c);
	}

	/*
	 * 
	 * To amaksi (nhma) pou 8elei na mpei sth gefyra,
	 * 8a tou apagoreftei h eisodos (wait) an h gefyra den einai adeia,
	 * an to xrwma tou den einai to swsto (afto tou opoiou einai h seira)
	 * h an t idio den einai to prwto amaksi ths seiras tou.
	 */
	public synchronized void enterBridge(Car c)
	{
		while ((!bridgeIsEmpty)||(nextColour!=c.getColour()))
		{
			//System.out.println("Failed because bridgeIsEmpty = "+bridgeIsEmpty+" and c.getNum() = "+c.getNum()+" while nextToPass = "+nextToPass);
			try 
			{
				Thread.sleep(10);
				wait();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		/*
		 * To amaksi (nhma) pou 8elei na mpei sth gefyra, 8a tou apagoreftei
		 * h eisodos (wait) an t idio den einai to prwto amaksi ths seiras tou (2os elegxos).
		 */
		
		int nextToPass;
		if(c.getColour()==1)
			nextToPass = nextBlueToPass;
		else
			nextToPass = nextRedToPass;
		
		while (c.getNum()!=nextToPass)
		{
			try 
			{
				wait();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}

		bridgeIsEmpty = false;
		c.setPassing(System.currentTimeMillis());
		
		try 
		{
			Thread.sleep(Main.bridgeDelay);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		exitBridge(c);
	}

	public synchronized void exitBridge(Car c)
	{
		c.setPassed(System.currentTimeMillis());
		
		bridgeIsEmpty = true;
		
		if(c.getColour()==1)
		{
			nextBlueToPass++;
			nextColour = 2;
		}
		else
		{
			nextRedToPass++;
			nextColour = 1;
		}
		
		notifyAll();
	}

}
