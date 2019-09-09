public class Car extends Thread {
	private long arrived, passing, passed;
	private int num, colour;//colour: 1 == blue, 2 == red
	private Scheduler myScheduler;
	private SystemLog myLog;
	private boolean finishedPassing = false;
	
	public Car(int number,int col,Scheduler aScheduler, SystemLog log){

		this.num = number;
		this.colour = col;
		this.myScheduler = aScheduler;
		this.myLog = log;
	}

	public void run()
	{
		while(!finishedPassing)
		{
				this.myScheduler.crossBridge(this);
		}
	}
	
	public Scheduler getMyScheduler() 
	{
		return myScheduler;
	}

	public void setMyScheduler(Scheduler myScheduler) 
	{
		this.myScheduler = myScheduler;
	}

	public int getColour() 
	{
		return colour;
	}

	public void setColour(int colour) 
	{
		this.colour = colour;
	}

	public String toString()
	{

		String text = "";

		if(colour == 1)
			text = "                                     "+"Blue car";
		else
			text = "Red car";

		text += " #"+this.num;
		
		return text;
	}

	public long getArrived() 
	{
		return arrived;
	}

	public void setArrived(long arrived) 
	{
		arrived -= Main.systemStartingTime;
		myLog.writeRegistry(this+" Arrived at "+arrived);
	}

	public long getPassing() 
	{
		return passing;
	}

	public void setPassing(long passing) 
	{
		passing -= Main.systemStartingTime; 
		myLog.writeRegistry(this+" Passing at "+passing);
	}

	public long getPassed() 
	{
		return passed;
	}

	public void setPassed(long passed) 
	{
		passed -= Main.systemStartingTime; 
		myLog.writeRegistry(this+" Passed at "+passed);
	}

	public int getNum() 
	{
		return num;
	}

	public void setNum(int num) 
	{
		this.num = num;
	}

	public void setFinishedPassing(boolean finished)
	{
		finishedPassing = finished;
	}	
}