
public class CarGenerator extends Thread{

	private int redCounter = 0, blueCounter = 0;
	private double carsPerSecond;
	private Scheduler myScheduler;
	
	public CarGenerator(Scheduler sc, double cps){
		
		this.myScheduler = sc;
		carsPerSecond = cps;
	}

	public void run(){
		Car aCar;
		
		while(true)
		{			
			aCar = this.getNewRandomCar();
			aCar.setMyScheduler(myScheduler);
			aCar.start();

			try 
			{
				sleep((int)(1000/carsPerSecond));
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			
		}
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
		
		
		Car aCar = new Car(num,colour,this.myScheduler);
		
		
		
		aCar.setArrived(System.currentTimeMillis());//Orizetai o xronos afikshs tou amaksiou sth gefyra.
		System.out.println("The #"+aCar.getNum()+"th "+getColText(aCar.getColour())+" car just arrived at "+aCar.getArrived()+".");
		return aCar;
	}
	
	private String getColText(int i)
	{
		if(i==1)
			return "blue";
		else
			return "red";
				
	}
	
}
