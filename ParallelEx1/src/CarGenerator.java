import java.util.ArrayList;

public class CarGenerator extends Thread{

	private int redCounter = 0, blueCounter = 0, carsOnBridgeSimultaneously;
	private double carArrivalRate, timeToCrossBridge;
	private Scheduler myScheduler;
	private SystemLog log;
	private boolean stop;
	
	public CarGenerator(double rate, double time, int carNum){
		
		stop = false;
		carArrivalRate = rate;
		timeToCrossBridge = time;
		carsOnBridgeSimultaneously = carNum;
		log = new SystemLog();
	}

	public void run(){
		Car aCar;
		
		ArrayList<Car> threadList = new ArrayList<Car>();
		while(!stop)
		{			
			threadList.add(this.getNewRandomCar());
			
			aCar = this.getNewRandomCar();
			aCar.setMyScheduler(myScheduler);
			aCar.start();
			threadList.add(aCar);
			System.out.println("A new car has just been created.");
			try 
			{
				sleep((int)(1000/carArrivalRate));
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		for(Car c:threadList)
			try {
				c.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//Now that all threads have finished running
		log.printLogToConsole();
	}
	
	public Car getNewRandomCar(){
		
		int num, colour;		
		if(Math.random()>=0.5)
		{
			redCounter++;
			num = redCounter;
			colour = 2;
		}
		else
		{
			blueCounter++;
			num = blueCounter;
			colour=1;
		}
		
		Car aCar = new Car(num,colour,this.myScheduler,log);
		
		aCar.setArrived(System.currentTimeMillis());//Orizetai o xronos afikshs tou amaksiou sth gefyra.
		//System.out.println("The #"+aCar.getNum()+"th "+getColText(aCar.getColour())+" car just arrived at "+aCar.getArrived()+".");
		return aCar;
	}
	
	public void setScheduler(Scheduler aScheduler)
	{
		this.myScheduler = aScheduler;
	}

	public SystemLog getLog() {
		return log;
	}
	
	public void stopCarProduction()
	{
		stop = true;
	}
	
}
