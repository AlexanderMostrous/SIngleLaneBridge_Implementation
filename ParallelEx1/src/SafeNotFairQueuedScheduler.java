
public class SafeNotFairQueuedScheduler extends Scheduler{

	private boolean bridgeIsEmpty;
	private int nextRedToPass, nextBlueToPass;

	/*
	 * To perasma einai asfales xwris sygkrouseis, alla einai adiko.
	 * Ta aftokinhta pernane "opoio prolavei prwto", alla se ka8e 
	 * plevra ths gefyras sxhmatizetai apo mia oura anamonhs.
	 * Se realistikes syn8hkes 8a to fantazomastan ws dyo dromous
	 * ekaterw8en ths gefyras, stous opoious sygkentrwnontai me taksh,
	 * sthn oura, ola ta amaksia pou perimenoun gia na diasxisoun th gefyra,
	 * kai kata tropo adiko pernaei o enas ek twn dyo prwtwn ths ka8e ouras,
	 * thn ekastote fora.
	 * 
	 */
	public SafeNotFairQueuedScheduler()
	{
		bridgeIsEmpty = true;
		nextBlueToPass = 1;
		nextRedToPass = 1;
	}

	public void crossBridge(Car c)
	{
		enterBridge(c);
	}

	/*
	 * 
	 * To amaksi (nhma) pou 8elei na mpei sth gefyra,
	 * 8a tou apagoreftei h eisodos (wait) an h gefyra den einai adeia (1os elegxos).
	 * h an t idio den einai to prwto amaksi ths seiras tou.
	 */
	public synchronized void enterBridge(Car c)
	{
		while (!bridgeIsEmpty)
		{
			//System.out.println("Failed because bridgeIsEmpty = "+bridgeIsEmpty+" and c.getNum() = "+c.getNum()+" while nextToPass = "+nextToPass);
			try 
			{
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
			nextBlueToPass++;
		else
			nextRedToPass++;
		
		notifyAll();
	}

}