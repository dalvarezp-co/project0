

public class  SimpleParser
{

	private boolean answer;

	private File document;

	public SimpleParser(String pRuta)
	{
		document= new File (pRuta);
		answer= false;
	}

	public boolean documentReader()
	{
		FileReader fReader = new FileReader (document);
		BufferedReader bReader= new BufferedReader(fReader);


		String line = lector.readline

		while(line != null)
		{


		}

		fReader.close();
		bReader.close();

		return answer;
	}


}
