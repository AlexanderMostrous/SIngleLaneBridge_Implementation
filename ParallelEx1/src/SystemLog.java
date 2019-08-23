import java.util.ArrayList;

public class SystemLog {

	private ArrayList<String> log;
	
	public SystemLog()
	{
		log = new ArrayList<String>();
	}
	
	public synchronized void writeRegistry(String text)
	{
		log.add(text);
	}
	
	public void printLogToConsole()
	{
		for(int i=0;i<2;i++)
			System.out.println();
		System.out.println("Left Side                     Bridge           RightSide");
		for(String s:log)
			if(s.startsWith("B"))
				System.out.println("                                     "+s);
			else
				System.out.println(s);
			
				
	}
}
