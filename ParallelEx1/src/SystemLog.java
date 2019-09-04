import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SystemLog {

	//private ArrayList<String> log;
	private String directory, newLineProperty;
	private FileWriter writer;

	public SystemLog(String dir)
	{
		this.directory = dir;
		newLineProperty = System.getProperty("line.separator");

		try
		{
			writer = new FileWriter(directory+"\\test.txt");
			writer.write("Left Side                     Bridge           RightSide"+newLineProperty);
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void writeRegistry(String text)
	{

		try {
			writer.write(text+newLineProperty);
		} catch (IOException e) {
			//e.printStackTrace();
			//No need to do anything
		}

	}
	
	public void setDirectory(String dir)
	{
		this.directory = dir;
	}
	
	public void closeWriter()
	{
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
