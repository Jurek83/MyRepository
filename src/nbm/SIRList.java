/*
 * class is responsible for SIR List
 * it allows to save to file and load from file SIR sort codes and nature of incident
 * it allows to add SIR to ArrayList and return SIR list as string
 */
package nbm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author Jurek
 */
public class SIRList 
{
        private String fileName = "";                         // file to save/load SIR
        private final ArrayList<String> list;                 // holds SIR 
    
    // constructor 
    public SIRList(String file)
    {
        fileName = file;
        list = new ArrayList<>();
    }
    
     // returns SIR list as a String
    public String getList()
    {
        String sir = "";
        for(int i=0; i<list.size(); i++)
        {
            sir += (i+1)+". "+list.get(i)+"\n";
        }
        return sir;
    }
    // -------------------------------------------------------------------------
    
    // saves SIR sort code and nature of incident to file
    public void save(String sir)
    {
        File file = new File(fileName);  
        try
        {
        FileWriter fw = new FileWriter(file, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) 
        {
            bw.write(sir+",");      
            bw.flush();
        }
        }
        catch(IOException e)
        {
            System.out.println("An error has occurred while saving SIR file.");  
        }
    }
    // -------------------------------------------------------------------------
    
    // loads SIRs from file and adds to the list
    public void load()
    {
        File myFile = new File(fileName);
     try 
       {
       FileReader fr = new FileReader(myFile);  
       BufferedReader br = new BufferedReader(fr); 
       String data="";
       while(  (data = br.readLine()) != null   )
          {
          StringTokenizer st = new StringTokenizer(data,",");
          while(st.hasMoreTokens())
              {
              list.add(st.nextToken().trim());  
              }    
          }
         } 
        catch (IOException e) 
         {
           System.out.println("An error has occurred while loading data");  
         }
    }
    // -------------------------------------------------------------------------
    
    // adds SIR string to the list
    public void add(String sir)
    {
        list.add(sir+",");
    }
    // -------------------------------------------------------------------------
}
