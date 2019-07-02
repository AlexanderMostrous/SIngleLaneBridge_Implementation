
public class Car extends Thread {
	private long arrived, passing, passed;
	private int num, colour;//colour: 1 == blue, 2 == red
	private Scheduler myScheduler;

	public Car(int number,int col,Scheduler aScheduler){

		this.num = number;
		this.colour = col;
		this.myScheduler = aScheduler;
	}

	public void run()
	{
		while(this.passed==0)
		{
				this.myScheduler.crossBridge(this);
		}
		System.out.println(this);
	}




	public Scheduler getMyScheduler() {
		return myScheduler;
	}

	public void setMyScheduler(Scheduler myScheduler) {
		this.myScheduler = myScheduler;
	}

	public int getColour() {
		return colour;
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	public String toString(){

		clearIrrelevantTime();

		String text = "";

		if(colour == 1)
			text = "Blue car";
		else
			text = "Red  car";

		text += " #"+this.num+" Arrived at "+ this.arrived+", started passing at "+this.passing+" and finished passing at "+this.passed+".";
		
		return text;
	}

	//8etei tous xronous sxetikous ws pros thn arxh tou peiramatos.
	private void clearIrrelevantTime(){
		this.arrived = arrived - Main.systemStartingTime;
		this.passing = passing - Main.systemStartingTime;
		this.passed = passed - Main.systemStartingTime;
	}


	public long getArrived() {
		return arrived;
	}

	public void setArrived(long arrived) {
		this.arrived = arrived;
	}

	public long getPassing() {
		return passing;
	}

	public void setPassing(long passing) {
		this.passing = passing;
	}

	public long getPassed() {
		return passed;
	}

	public void setPassed(long passed) {
		this.passed = passed;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}


}
