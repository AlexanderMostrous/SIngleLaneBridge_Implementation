
public class SafeRoundRobbinWithAdjustmentsScheduler extends Scheduler{

	private boolean bridgeIsEmpty;
	private int nextRedToPass, nextBlueToPass, nextColour;
	private long lastCheckpoint=0;
	
	/*
	 * To perasma einai asfales xwris sygkrouseis kai ta aftokinhta pernane 
	 * enallaks, ena kokkino, ena mple, ena kokkino, ena mple,...
	 * An omws kseperastei ena sygkekrimeno xroniko threshold pernaei o prwtos ths idias ouras.
	 * 
	 */
	public SafeRoundRobbinWithAdjustmentsScheduler(double time, double cars)
	{
		super(time, cars);
		
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
		while (!bridgeIsEmpty)
		{
			
			

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
		 * O elegxos dikaiosynhs (dhladh gia to an to xrwma tou trexontos aftokinhtou einai
		 * to swsto) 8a ginei mono sthn periptwsh pou to xroniko threshold den exei kseperastei - 2 * to delay ths gefyras)
		 */
		if(System.currentTimeMillis()-lastCheckpoint<=2*Main.bridgeDelay)
			if(nextColour!=c.getColour())
				try 
			{
				wait();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
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
