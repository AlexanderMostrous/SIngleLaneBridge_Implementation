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
		System.out.println("Left Side                     Bridge           RightSide");
		for(String s:log)
			if(s.startsWith("B"))
				System.out.println("                                "+s);
			else
				System.out.println(s);
			
				
	}
}
