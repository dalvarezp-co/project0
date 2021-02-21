import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import re;

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
	public final static String LEFT = "left";
	public final static String RIGHT = "right";
	public final static String BACK = "back";
	public final static String NORTH= "N";
	public final static String SOUTH =  "S";
	public final static String WEST= "W";
	public final static String EAST="E";




	private boolean answer;

	private File document;

	private ArrayList<String> nonEmptyLines;

	private ArrayList<String> functionDefinition;

	private ArrayList<String> valueDefinition;

	public SimpleParser(String pRuta)
	{
		document = new File (pRuta);
		answer = true;
		nonEmptyLines=null;
		functionDefinition=null;
		valueDefinition=null;
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
			{
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
			readDocument();
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


		return answer;
	}


	public boolean isInteger (String data )
	{
		try 
		{ 
			Integer.parseInt(data);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isAvailable(String line)
	{
		boolean respuesta= true;
		String linea=line.toLowerCase();
		if(linea.equals(WALK)||linea.equals(ROTATE)||linea.equals(LOOK)||linea.equals(DROP)||
				linea.equals(FREE)||linea.equals(PICK)|| linea.equals(GRAB)||linea.equals(WALKTO)
				||linea.equals(NOP)||linea.equals(BLOCK)||linea.equals(IF)||linea.equals(BLOCKED)||
				linea.equals(FACING)||linea.equals(CAN)||linea.equals(NOT)||linea.equals(DEFINE)||
				linea.equals(LEFT)||linea.equals(RIGHT)||linea.equals(BACK)||linea.equals(NORTH)||
				linea.equals(SOUTH)||linea.equals(WEST)||linea.equals(EAST))
		{
			respuesta= false;	
		}

		else if(respuesta==true)
			for (int i=0; i< valueDefinition.size();i++)
			{
				if (linea.equals(valueDefinition.get(i)))
				{
					respuesta=false;
				}
			}
		else if(respuesta==true)
			for (int i=0; i< functionDefinition.size();i++)
			{
				if (linea.equals(functionDefinition.get(i)))
				{
					respuesta=false;
				}
			}
		return respuesta;

	}

	public boolean isStandardFunction (String line)
	{
		boolean respuesta= true;
		String linea=line.toLowerCase();
		if(linea.equals(WALK)||linea.equals(ROTATE)||linea.equals(LOOK)||linea.equals(DROP)||
				linea.equals(FREE)||linea.equals(PICK)|| linea.equals(GRAB)||linea.equals(WALKTO)
				||linea.equals(NOP)||linea.equals(BLOCK))
		{
			respuesta =false;
		}
		return respuesta;
	}

	public boolean isCondition(String dato)
	{
		if (dato.equals(BLOCKED)||dato.equals(FACING)||dato.equals(CAN)||dato.equals(NOT))
		{
			return true;
		}
		else
			return false;

	}


	public boolean isValueDefinition(String data)
	{
		boolean respuesta=false;
		for (int i=0; i<valueDefinition.size(); i++)
		{
			if(data.equals(valueDefinition.get(i)))
			{
				respuesta= true;
			}
		}
		return respuesta;

	}


	public boolean isFucntionDefinition(String data)
	{
		boolean respuesta=false;
		for (int i=0; i<functionDefinition.size(); i++)
		{
			if(data.equals(functionDefinition.get(i)))
			{
				respuesta= true;
			}
		}
		return respuesta;

	}

	public boolean lineSyntaxRevision( String line)
	{
		boolean isCorrect = false;

		String[] linea= line.split(line);

		if (linea[0].equals(DEFINE))
		{
			if(linea.length==3 && isInteger(linea [2])&& !isInteger(linea[1])&&isAvailable(linea[1]))
			{
				valueDefinition.add(linea[1]);
				valueDefinition.add(linea[2]);
			}


			if (linea.length==3 && isAvailable(linea[1]))
			{
				functionDefinition.add(linea[1]);
			}
		}



		if (linea[0].equals(WALK))
		{
			if (Integer.parseInt(linea[1])>= 0 && linea.length==2 && isValueDefinition(linea[1]))
			{
				isCorrect=true;
			}
		}
		else if (linea[0].equals(ROTATE))
		{
			if (linea[1].equals(LEFT) || linea[1].equals(RIGHT) || linea[1].equals(BACK) && linea.length==2)
			{
				isCorrect=true;
			}
		}
		else if (linea[0].equals(LOOK))
		{
			if((linea[1].equals(NORTH) || linea[1].equals(EAST) || linea[1].equals(WEST) || linea[1].equals(SOUTH)) && linea.length==2 )
			{
				isCorrect=true;
			}
		}
		else if (linea[0].equals(DROP))
		{
			if (Integer.parseInt(linea[1])>= 0 && linea.length==2 && isValueDefinition(linea[1]))
			{
				isCorrect=true;
			}
		}
		else if (linea[0].equals(FREE))
		{
			if (Integer.parseInt(linea[1])>= 0 && linea.length==2 && isValueDefinition(linea[1]))
			{
				isCorrect=true;
			}
		}
		else if (linea[0].equals(PICK))
		{
			if (Integer.parseInt(linea[1])>= 0 && linea.length==2 && isValueDefinition(linea[1]))
			{
				isCorrect=true;
			}
		}
		else if (linea[0].equals(GRAB))
		{
			if (Integer.parseInt(linea[1])>= 0 && linea.length==2 && isValueDefinition(linea[1]))
			{
				isCorrect=true;
			}
		}
		else if (linea[0].equals(WALKTO))
		{
			if (Integer.parseInt(linea[1])>= 0 && linea.length==3 && isValueDefinition(linea[1]))
			{
				if ((linea[2].equals(NORTH) || linea[2].equals(EAST) || linea[2].equals(WEST) || linea[2].equals(SOUTH)))
				{
					isCorrect=true;
				}
			}
		}


		else if (linea[0].equals(NOP))
		{
			if( linea.length==1)
			{
				isCorrect=true;
			}
		}




		else if(linea[0].equals(IF))
		{
			if (linea[1].equals(NOT))
			{
				if (isCondition(linea[2]))
				{
					String variable="("; 
					for(int i= 3; i<linea.length-1;i++)
					{
						variable+= linea[i]+ " ";

					}
					if (lineSyntaxRevision(variable+ ")"));
					{
						if (linea[linea.length-1]=="nop")
						{
							return true;
						}
					}
				}
			}

			else if(isCondition(linea[1]))
			{
				String variable=null;
				for(int i= 3; i<linea.length-1;i++)
				{
					variable+= linea[i]+ " ";

				}
				if (lineSyntaxRevision(variable+ ")"));
				{
					if (linea[linea.length-1]=="nop")
					{
						return true;
					}
				}

			}

		}



		else if(isCorrect==false)
		{
			for (int i=0; i<functionDefinition.size(); i++)
			{
				if(linea[0].equals(functionDefinition.get(i)))
				{
					isCorrect=false;
				}
			}
		}


		else
		{
			isCorrect=false;
		}

		return isCorrect;


	}


	private void readDocument()
	{

		if(answer==true)
		{
			for (int i=0; i<nonEmptyLines.size(); i++)
			{
				String linea= nonEmptyLines.get(i);
				if (linea.contains(DEFINE));
				{
					String datos=null;
					String[] lineDefine= linea.split(" ");
					for (int j=0; j<linea.length();j++)
					{
						lineDefine[j].replace('(', '\0');
						lineDefine[j].replace(')', '\0');
					}

					for (int j=0; j<linea.length();j++)
					{
						functionDefinition.add(lineDefine[j]);
						if (j>0)
						{
							datos= datos+ " " + lineDefine[j] ;
						}
					}

					if (lineDefine[i++].contains(BLOCK))
					{
						answer = blockReader(i++, datos );	
					}

					else 
					{
						lineSyntaxRevision(lineDefine[i++]);
					}

				}

				if(linea.startsWith("(")&& linea.endsWith(")") && 
						!(linea.substring(1, linea.length()-1)).contains("(")&& !(linea.substring(1, linea.length()-1)).contains(")"))
				{

					String dato= (linea.replace("(", "")).replace(")", "");
					{
						answer= lineSyntaxRevision(dato);
					}

				}


			}



		}

	}








	public boolean blockReader (int dato, String datos)
	{
		boolean respuesta=false;
		for (int i=0; i<nonEmptyLines.size(); i++)
		{
			if (nonEmptyLines.get(i).startsWith("(") && nonEmptyLines.get(i).contains("BLOCK")&& nonEmptyLines.get(i).contains("BLOCKED"))
			{
				int parentesisAbiertos=0;
				int parentesisCerrados=0;

				for ( int j=0; j< nonEmptyLines.size();i++)
				{
					for ( int k=0; k<nonEmptyLines.get(i).length();k++)
					{
						if (nonEmptyLines.get(j).charAt(k)=='(' )
						{
							parentesisAbiertos++;
						}
						if (nonEmptyLines.get(j).charAt(k)==')' )
						{
							parentesisCerrados++;
						}
					}
				}


				while (!(parentesisAbiertos==parentesisCerrados))
				{
					if ( parentesisAbiertos>parentesisCerrados)
					{
						for ( int j=i++; j< nonEmptyLines.size();i++)
						{
							for ( int k=0; k<nonEmptyLines.get(i).length();k++)
							{
								if (nonEmptyLines.get(j).charAt(k)=='(' )
								{
									parentesisAbiertos++;
								}
								if (nonEmptyLines.get(j).charAt(k)==')' )
								{
									parentesisCerrados++;
								}
							}
						}
					}


					if(parentesisAbiertos<parentesisCerrados)
					{
						respuesta= false;
					}

					if(parentesisAbiertos==parentesisCerrados)
					{
						respuesta=true;
					}

				}



			}


		}

		return respuesta;
	}
}
