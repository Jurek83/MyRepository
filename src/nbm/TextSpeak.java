/*
 * class is responsible for TextSpeak
 * it allows to replace textspeaks with full explanation
 */
package nbm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jurek
 */
public class TextSpeak 
{
    private String [][] textspeak;                    // array holds textspeaks abreviations and full explanations
    
    public TextSpeak(String fileName)
    {
        textspeak = load(fileName);                   // loads textspeaks into array
    }
    
    // loads Textspeks to the array and returns array
    private String[][] load(String fileName)
    {
        File myfile = new File(fileName);
        ArrayList<String> arrayList = new ArrayList<>(); 
        try 
        { 
        if(myfile.length()!=0)
        {
        FileReader fr = new FileReader(myfile);
        BufferedReader br = new BufferedReader(fr);
        
        String data;
        int i = 0;
    
        while((data = br.readLine()) != null)   // line.length
        {
        arrayList.add(data);
        }   
        }
        } 
        catch (IOException e) {  }
        
        textspeak = new String[arrayList.size()][2];
        for(int i=0; i<arrayList.size(); i++)
        {
            String []line = arrayList.get(i).split(",");
            textspeak[i][0] = line[0];
            textspeak[i][1] = line[1];
        }
        return textspeak;
    }
    // -------------------------------------------------------------------------
    
    // replaces textspeak abbreviations with abbreviations and full explanations
    public String replace(String text)
    {
    String s = text;    
        for (String[] tspeak : textspeak) 
        {
            if (s.contains(tspeak[0])) 
            {
                s = s.replaceAll(tspeak[0], tspeak[0] + "<" + tspeak[1] + ">");
            }
        }
    return s;
    }
    // -------------------------------------------------------------------------
     
}
