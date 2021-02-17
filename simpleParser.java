import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class SimpleParser 
{
	public final static String WALK= "walk";
	public final static String ROTATE= "rotate";
	public final static String LOOK= "look";
	public final static String DROP= "drop";
	public final static String FREE= "free";
	public final static String PICK= "pick";
	public final static String GRAB= "grab";
	public final static String WALKTO= "walkTo";
	public final static String NOP= "NOP";
	public final static String BLOCK= "block";
	public final static String IF= "if";
	public final static String BLOCKED= "blocked?";
	public final static String FACING= "facing?";
	public final static String CAN= "can";
	public final static String NOT= "not";
	public final static String DEFINE= "define";
	

	private boolean answer;

	private File document;

	private ArrayList<String> nonEmptyLines;

	public SimpleParser(String pRuta)
	{
		document = new File (pRuta);
		answer = true;
		nonEmptyLines=null;
	}

	public void documentReader() throws Exception
	{
		FileReader fReader = new FileReader (document);
		BufferedReader bReader = new BufferedReader(fReader);
		String line = bReader.readLine();
		int amountOfContinuousEmptyLines = 0;

		try
		{
			while (amountOfContinuousEmptyLines < 5)
		
			if(line == null)
			{
				bReader.readLine();
				amountOfContinuousEmptyLines++;
			}

			else
			{
				amountOfContinuousEmptyLines = 0;

				nonEmptyLines.add(line.trim());

				bReader.readLine();
			}
		}
		catch(Exception e)
		{
			System.out.println("No se pudo leer el archivo");
		}
		
		finally
		{
		fReader.close();
		bReader.close();
		}

	}




	public boolean respuesta(String line)
	{
		
		int i=0;
		while (nonEmptyLines.indexOf(nonEmptyLines.get(i))<nonEmptyLines.size())
		{
			if (lineSyntaxRevision(nonEmptyLines.get(i))==false)
			{
			answer=false;	
				break;
			}
			i++;
		}
		
		return answer;
	}



	public boolean lineSyntaxRevision( String line)
	{
		boolean isCorrect = false;
		String[] linea= token(line);
		String token1 = linea[0];

		if (linea.equals(WALK))
		{

		}
		else if (linea.equals(ROTATE))
		{

		}
		else if (linea.equals(LOOK))
		{

		}
		else if (linea.equals(DROP))
		{

		}
		else if (linea.equals(FREE))
		{

		}
		else if (linea.equals(PICK))
		{

		}
		else if (linea.equals(GRAB))
		{

		}
		else if (linea.equals(WALKTO))
		{

		}
		else if (linea.equals(NOP))
		{
			if( linea.length==1)
			{
				isCorrect=true;
			}
		}
		else if()
		{
			
		}
		else
		{
			isCorrect=false;
		}

		return isCorrect;
	}


	private String[] token(String data)
	{
		
		if(data.startsWith("("))
		{
			if(data.endsWith(")"))
					{
				String dato= data.substring(1, data.length()-1)
				String[] simpleCommand=dato.split(" ");
				return simpleCommand;
					}
		}
		
		else {
			return null;
		}
		
		
	}


}
