import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class  SimpleParser
{

    private boolean answer;

    private File document;

    public SimpleParser(String pRuta)
    {
        document = new File (pRuta);
        answer = true;
    }

    public boolean documentReader()
    {
        FileReader fReader = new FileReader (document);
        BufferedReader bReader = new BufferedReader(fReader);
        String line = lector.readLine;
        int amountOfContinuousEmptyLines = 0;

        while (amountOfcontinuousEmptyLines < 5)
            if(line == null)
            {
                lector.readline;
                amountOfconutnuousEmptyLines++;
            }

            else
            {
                amountOfcontinuousEmptyLines = 0;

                if (lineSyntaxRevision(line) == false)
                {
                    answer = false;
                    break;
                }

                lector.readLine;
            }
    }

    fReader.close();
    bReader.close();

    return answer;
}

public boolean  lineSyntaxRevision( String line)
{
    boolean isCorrect = false;
    line

    if ()
    {

    }

    return isCorrect;
}



}
