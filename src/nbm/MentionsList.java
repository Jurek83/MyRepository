/*
 * class is responsible for Mentions List
 * it allows to save to file and load from file Tweeter IDs
 * it allows to add Tweeter IDs to ArrayList and return Mentions list as a string
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jurek
 */
public class MentionsList 
{
    private String fileName = "";                         // file to save/load mentions Tweeters ID
    private final ArrayList<String> list;                // holds tweeter id for mention list
    
    // constructor
    public MentionsList(String file)
    {
        fileName = file;
        list = new ArrayList<>();
    }
    
      // finds all tweeter ID and returns them in ArrayList
    public ArrayList getTweeterID(String body, Pattern pattern)
    {
        ArrayList<String> mentions = new ArrayList<>();
        String tweet_ID = "";
        int tweetIndex=-1;
        for(int i=0; i<body.length(); i++)
        {
            char c = body.charAt(i);   
            if(c=='@'){ tweetIndex = i;}   
            if((tweetIndex>-1) && (c==' ' || i+1==body.length()))
            {
                tweetIndex = -1; 
                Matcher matcher = pattern.matcher(tweet_ID);    
                if(!list.contains(tweet_ID) && matcher.matches())
                {   
                    mentions.add(tweet_ID);
                    list.add(tweet_ID);
                } 
                tweet_ID = ""; 
            }   
            if(tweetIndex>-1){ tweet_ID+= body.charAt(i); }
        }
        if(tweetIndex>-1 && tweet_ID.length()<=16){ list.add(tweet_ID); }
        return mentions;
    }
    // -------------------------------------------------------------------------
    
    // load mention list from file
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
           System.out.println("An error has occurred while loading mentions file.");  
         }
    }
    // -------------------------------------------------------------------------
    
    // save tweeter ID to file for mentions list
    public void save(ArrayList<String> mentions)
    {
        File file = new File(fileName);  
        try
        {
        FileWriter fw = new FileWriter(file, true);
        try (BufferedWriter bw = new BufferedWriter(fw)) 
        {
            for(int i=0; i<mentions.size(); i++)
            {
            bw.write(mentions.get(i)+",");    
            }
            bw.flush();
        }
        }
        catch(IOException e)
        {
            System.out.println("An error has occurred while saving mentions file.");  
        }
    }
    // -------------------------------------------------------------------------
    
    // returns mention list as a string
    public String getList()
    {
        int n = 1;
        String mentions = "";
        for(int i=0; i<list.size(); i++)
        {
            mentions += n+". "+list.get(i)+"\n"; n++;
        }
        return mentions;
    }
    // -------------------------------------------------------------------------
}
