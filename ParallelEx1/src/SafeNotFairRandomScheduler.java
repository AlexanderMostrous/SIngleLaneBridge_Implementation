
public class SafeNotFairRandomScheduler extends Scheduler{

	private boolean bridgeIsEmpty;

	/*
	 * To perasma einai asfales xwris sygkrouseis, alla einai adiko.
	 * Ta aftokinhta pernane "opoio prolavei prwto", xwris kan na 
	 * sxhmatizontai oures anamonhs se ka8e mia plevra ths gefyras.
	 * Se realistikes syn8hkes 8a to fantazomastan ws dyo xwmatodromous
	 * ekaterw8en ths gefyras me platwma - ksefwto, sto opoio mporeis 
	 * na prosperaseis ton mprostino sou eite apo deksia eite apo
	 * aristera, stous opoious sygkentrwnontai, ola ta amaksia pou
	 * perimenoun gia na perasoun th gefyra, se enan kyklo. Kata
	 * tropo adiko ormane na perasoun, enw ka8e fora pernaei tyxaia 
	 * monaxa enas ek twn perimenontwn.
	 * 
	 */
	public SafeNotFairRandomScheduler()
	{
		bridgeIsEmpty = true;
	}

	public void crossBridge(Car c)
	{
		enterBridge(c);
	}

	/*
	 * 
	 * Oso h gefyra den einai adeia (dhladh kapoio amaksi th diasxizei)
	 * to nhma 8a kanei wait kai den 8a perasei.
	 */
	public synchronized void enterBridge(Car c)
	{
		while (!bridgeIsEmpty)
		{
			try 
			{
				c.wait();
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
		notifyAll();
	}



}
