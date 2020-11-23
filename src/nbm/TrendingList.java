/*
 * class is responsible for Trending List
 * it allows to save to file and load from file hashtags
 * it allows to add hashtags to trending ArrayList and return trending list as a string
 */
package nbm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Jurek
 */
public class TrendingList
{
    private String fileName = "";                 // file to save/load hashtags
    private final Map<String, Integer> list;      // holds hashtags for trending list
    
    // constructor
    public TrendingList(String file)
    {
        fileName = file;
        list = new HashMap<>();
    }
    
    // load trending list from file
    public void load()
    {
        ArrayList<String> hashTags = new ArrayList<>(); 
        File myFile = new File(fileName);
    
     try 
       {
       FileReader fr = new FileReader(myFile);  
       BufferedReader br = new BufferedReader(fr); 
       String data;
       while(  (data = br.readLine()) != null   )
          {
          StringTokenizer st = new StringTokenizer(data,",");
          while(st.hasMoreTokens())
              {
              hashTags.add(st.nextToken().trim());  
              }    
          }
         } 
        catch (IOException e) 
         {
           System.out.println("An error has occurred while loading trending list.");  
         }
         addToList(hashTags);
    }
    // -------------------------------------------------------------------------
    
    // save hashtag to trending list file
    public void save(ArrayList<String> hashtags)
    {
        File file = new File(fileName); 
        try
        {
        FileWriter fw = new FileWriter(file, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) 
        {
            for(int i=0; i<hashtags.size(); i++)
            {
            bw.write(hashtags.get(i)+",");    
            }
            bw.flush();
        }
        }
        catch(IOException e)
        {
            System.out.println("An error has occurred while saving trending list to file.");  
        }
    }
    // -------------------------------------------------------------------------
    
    // finds all hashtags and returnes them in ArrayList
    public ArrayList<String> getHashtags(String body)
    {
        ArrayList<String> hashtagsList = new ArrayList<>();
        String hashtag = "";
        int start=-1;
        for(int i=0; i<body.length(); i++)
        {
            char c = body.charAt(i);   
            if(c=='#'){ start = i;}   
            if((start>-1) && (c==' ' || i+1==body.length()) ){start = -1; hashtagsList.add(hashtag); hashtag = ""; } 
            if(start>-1){ hashtag+= body.charAt(i); }
        }     
    return hashtagsList;    
    }
    // -------------------------------------------------------------------------
    
    // adds hashtags to trending list
    public void addToList(ArrayList<String> array)
    {
        array.forEach((s) -> {
            if(list.containsKey(s)){ int value = list.get(s); list.put(s, value+1); }
            else{  list.put(s, 1); }
        });
    }
    // -------------------------------------------------------------------------
    
    // returns trending list as a string
    public String getList()
    {
        int n = 1;
        String trendList = "";
        for (Map.Entry entry : list.entrySet()) {
            trendList += n+". "+ entry.getKey()+"\t"+entry.getValue()+"\n";
            n++;
        }
        return trendList;
    }
    // -------------------------------------------------------------------------
}
