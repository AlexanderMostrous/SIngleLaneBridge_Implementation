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
}
