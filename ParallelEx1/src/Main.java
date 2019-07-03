
public class Main {

	public static long systemStartingTime;
	public static long bridgeDelay;//O apaitoumenos xronos gia th diasxish ths gefyras apo ena amaksi.
	
	
	public static void main(String[] args) {
/*
		systemStartingTime = System.currentTimeMillis();
		bridgeDelay = 500;
		
		double carsPerSec = 0.2;
		
		
		//UnsafeScheduler s = new UnsafeScheduler();
		//SafeNotFairRandomScheduler s = new SafeNotFairRandomScheduler();
		//SafeNotFairQueuedScheduler s = new SafeNotFairQueuedScheduler();
		//SafeRoundRobbinScheduler s = new SafeRoundRobbinScheduler();
		SafeRoundRobbinWithAdjustmentsScheduler s = new SafeRoundRobbinWithAdjustmentsScheduler();
		CarGenerator cg = new CarGenerator(s,carsPerSec);
		
		cg.start();
*/
		new CustomizeInputFrame();
		

	}

}
