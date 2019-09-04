
public abstract class Scheduler{

	protected double timeToCross;
	public Scheduler(double time)
	{
		timeToCross = time;
	}
	public abstract void crossBridge(Car c);
	public abstract void enterBridge(Car c);
	public abstract void exitBridge(Car c);
	
}
