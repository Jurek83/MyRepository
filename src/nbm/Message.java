/*
 * class is responsible for processing message, detecting message type
 * dealing with trending list, mentions list ans SIR list
 * it also allows to select file to load messages and reading this file
 */
package nbm;
import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Jurek
 */
public final class Message 
{
    private final String messagesFile;          // json messages filename
    
    private final TextSpeak textSpeak;          // TextSpeak object
    
    private final TrendingList trendList;       // TrendingList object
    private final MentionsList mentList;        // MentionsList object 
    private final SIRList sirList;              // SIRList object 
   
    private final Pattern phonePat;         // phone pattern to validate phone number
    private final Pattern emailPat;         // email pattern to validate email
    private final Pattern tweetPat;         // tweet pattern to validate tweeter id
    
    private final String sirIncidents[];        // types of incidents for SIR emails                  
    
    // constructor - parameters filenames for files: textspeask,  JSON messages, trending list, mentions list, SIR list 
    public Message(String ts, String mess, String trends, String ment, String sirs)     
    {
    messagesFile = mess;                // messages json file name   
    
    textSpeak = new TextSpeak(ts);      // initializes textSpeak object
    
    phonePat = Pattern.compile("^(\\+|\\d)(?:([0-9]|\\-) ?){2,20}([0-9]|\\-)$");                             // initialize phonePattern
    emailPat = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);       // initialize emailPattern
    tweetPat = Pattern.compile("^@([A-Za-z0-9_]{1,15})$");                                 // initialize tweetPattern     
    
    trendList = new TrendingList(trends);               // initializes trendList object
    mentList = new MentionsList(ment);                  // initializes mentList object
    sirList = new SIRList(sirs);                        // initializes sirList object
    trendList.load();                                   // loads trending list
    mentList.load();                                    // loads mention list
    sirList.load();                                     // loads SIRs
    
    // initializes sirIncidents
    sirIncidents = new String[] {"Theft", "Staff Attack", "ATM Theft", "Raid", "Customer Attack",   
    "Staff Abuse", "Bomb Threat", "Terrorism", "Suspicious Incident", "Intelligence", "Cash Loss"};
    }
    // -------------------------------------------------------------------------
    
    // checks if header has correct message id format and length and returns type of message
    private char checkHeader(String header, String object[])
    { 
        String messageID = header.replaceAll("-", "");       // remove '-'
        Pattern patern = Pattern.compile("[\\s]");           // pattern for white spaces. 
        Matcher matcher = patern.matcher(messageID);         // find white spaces
        messageID = matcher.replaceAll("");                  // remove white spaces        
        messageID = messageID.toUpperCase();                 // change messageID to uppercase
        // check message length = 10 characters
        if(messageID.length()!=10){ return 'W'; }                               // W = Wrong format
        // check message type SMS, Email, Tweet
        char c = messageID.charAt(0);
        if(c!='S' && c!='E' && c!='T' ){ return 'W'; }                          // W = Wrong format
        // check if message type is followed by nine numeric characters
        for(int i=1; i<messageID.length(); i++)
        {
        if(!Character.isDigit(messageID.charAt(i))){ return 'W'; }              // W = Wrong format
        }
        object[0] = messageID;
        return c;
    }
    // -------------------------------------------------------------------------

    // process sms - makes sure it has correct phone number and message is not empty and not > 140, replaces textspeak
    private boolean processSMS(String body, String messages[], String object[])
    {    
            // split into telephone number and message
            int start = -1, end = -1, step = 1; 
            for(int i=0; i<body.length(); i++)
            {
                char c = body.charAt(i);
                if(step==1 && c!=' ' && c!='\t' && c!='\n')
                {
                if(c=='+' || c=='0'){ start = i; step = 2; }
                else { break; }
                }
                if(step==2 && (c==' ' || c=='\t' || c=='\n' )){ end = i; step = 3; }
                else if(step==2 && i+1==body.length()){ end = i+1; step = 3; }
            }            
            // gets phone number - Sender    
            if(start>-1){messages[2] = body.substring(start, end);}
            // gets message - Message
            if(end>-1 && end+1<=body.length()){ messages[4] = body.substring(end+1, body.length()); }
             
            // check if SMS message has not only spaces ' '
            boolean wrongMessage = true;
            for(int i=0; i<messages[4].length(); i++){ if(messages[4].charAt(i)!=' '){ wrongMessage = false;} }
            if(wrongMessage){ messages[4] = ""; }
            else { messages[4] = messages[4].trim(); }
            // get rid of '-' from telephone number
            messages[2] = messages[2].replaceAll("-", "");          
            //phone number matcher
            Matcher mather = phonePat.matcher(messages[2]);

            // check if phone number is valid - f it has proper length and format
            if(messages[2].length()<9 || messages[2].length()>15 || !mather.matches())
            {
                messages[2] = "Error\nSMS body must start with valid international phone number. "
                        + "International phone number must start with '00' or '+'. "
                        + "Then international phone number may contain only numerical characters (0 to 9) "
                        + "and '-'. International phone number must have at least 9 characters "
                        + "and no more than 15 excluding '-'.\n"; 
                messages[4] = "";
                return false;
            }
            
            object[1] = messages[2];
            messages[2] = "Sender:\t"+messages[2]+"\n";
            
            // check message lenghth
            if(messages[4].equals(""))          // check if message is empty, message must not be empty 
            { 
                messages[4] = "Error\nSMS message must not be empty."; return false; 
            }
            else if(messages[4].length()>140)   // check if message exceeds 140 characters, message must not exceed 140 characters
            { 
                messages[4] = "Error\nSMS message must not exceed 140 characters."; return false; 
            }
                
            // replace textspeak with full explanation
            messages[4] = textSpeak.replace(messages[4]);
            
            object[3] = messages[4];
            messages[4] = "Message:\t"+messages[4]+"\n";

            return true;   
    }
    // -------------------------------------------------------------------------
    
    // process tweet - makes sure Tweeter ID is coorect, checks message length, replaces textspeak, gets mentions and trending list
    private boolean processTweet(String body, String messages[], String object[])
    {
            // split into Tweeter ID and message
            int start = -1, end = -1, step = 1; 
            for(int i=0; i<body.length(); i++)
            {
                char c = body.charAt(i);
                if(step==1 && c!=' ' && c!='\t' && c!='\n')
                {
                if(c=='@'){ start = i; step = 2; }
                else { break; }
                }
                if(step==2 && (c==' ' || c=='\t' || c=='\n' )){ end = i; step = 3; }
                else if(step==2 && i+1==body.length()){ end = i+1; step = 3; }      
            } 
        
            // gets Tweeter ID - Sender    
            if(start>-1){messages[2] = body.substring(start, end);}
            // gets message - Message
            if(end>-1 && end+1<=body.length()){ messages[4] = body.substring(end+1, body.length()); }
          
            // check if Tweeter message has not only spaces ' '
            boolean wrongMessage = true;
            for(int i=0; i<messages[4].length(); i++){ if(messages[4].charAt(i)!=' '){ wrongMessage = false;} }
            if(wrongMessage){ messages[4] = ""; }
            else { messages[4] = messages[4].trim(); }
            
            Matcher matcher = tweetPat.matcher(messages[2]);
            
            // check if Tweeter ID is empty or bigger than 16 character  and if format is correct
            if(messages[2].length()==0 || messages[2].length()>16  || !matcher.matches())
            {
               messages[2] = "Error\nTweeter  body must start with valid Tweeter ID. Tweeter ID must "
                       + "not be empty and must not exceed 16 characters. "
                        + "Tweeter ID must start with '@' followed by a maximum of 15 characters."
                        + "Allowed characters include a-z, A-Z, 0-9 and _";
               messages[4] = "";
               return false;       
            }
        
            object[1] = messages[2];
            messages[2] = "Sender:\t"+messages[2]+"\n";
            
            // check if message is empty, message must not be empty or if message does not exceed 140 charcters (max 140 characters)
            if(messages[4].equals(""))
            { 
                messages[4] = "Error\nTweet message must not be empty."; return false; 
            }
            else if(messages[4].length()>140)
            { 
                messages[4] = "Error\nTweet message must not exceed 140 characters."; return false;
            }
            
            // replace textspeak with full explanation
            messages[4] = textSpeak.replace(messages[4]);
            
            object[3] = messages[4];
            messages[4] = "Message:\t"+messages[4]+"\n";
            
            // gets hashtags from message
            ArrayList hashTags = trendList.getHashtags(messages[4]);
            // puts hashtags to trendingList
            trendList.addToList(hashTags);
            // save hashtags to trendingList file
            trendList.save(hashTags);
            // ads tweeter ID to mention list and returns mentions array list
            ArrayList mentions = mentList.getTweeterID(messages[4], tweetPat);
            // saves mentions to file
            mentList.save(mentions);
            
        return true;
    }
    // -------------------------------------------------------------------------
    
    // process email
    private boolean processEmail(String body, String messages[], String object[])
    {
        boolean isSir = false;
        String firstname = "", lastname = "", email = "", subject = "", sortCode = "", incident = "", rest = "";
        ArrayList<String> lines = new ArrayList<>();
        for (String line : body.split("\\n")) 
        { 
            line = line.replaceAll("\\n","");
            line = line.replaceAll("\\t","");
            line = line.trim();
            if(!line.equals("")){ lines.add(line); }
        }

        String parts[] = lines.get(0).split(" ");
        if(parts.length<4)
        {
            messages[4] = "Error\nFirst text line of email must contain firstname lastname email subject "
                    + "separated by space ('   '). For instance John Smith johns@outlook.com some subject";
            return false;
        }
        else
        {
            firstname = parts[0];
            lastname = parts[1];
            email = parts[2];
            for(int j=3; j<parts.length; j++){ subject += parts[j]+ " "; }
            firstname = firstname.trim();
            lastname = lastname.trim();
            email = email.trim();
            subject = subject.trim();
        }
        
         // check if email address is valid
        Matcher mather = emailPat.matcher(email);
        if(!mather.matches())
        {
            messages[2] = "Error\nIncorrect email address. Please type correct email address.\n";
            messages[4] = "";
            return false;
        }
        object[1] = firstname+" "+lastname+" "+email;
        messages[2] = "Sender:\t"+object[1]+"\n";
      
        // check subject
        if(subject.length()>20){messages[4] = "Error\nSubject must not exceed 20 characters."; return false; }
        if(subject.equals("")){messages[4] = "Error\nSubject must not be empty."; return false; }
        
        object[2] = subject;
        messages[3] = "Subject:\t"+subject+"\n";
        
        if(subject.toLowerCase().contains("sir") )      // SIR email
        {
            isSir = true;
            // check if subject has correct format of date
            String date = subject.toLowerCase().replace("sir", "");
            Pattern patern = Pattern.compile("[\\s]");
            Matcher matcher = patern.matcher(date);                 // find white spaces
            date = matcher.replaceAll("");                          // remove white spaces
            patern = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{2})");    // pattern for date  
            matcher = patern.matcher(date);    
           
            if(!matcher.matches())
            {
            messages[4] = "Error\nWrong date format for SIR email. Date should be dd/mm/yy";
            messages[3] = "";
            return false;    
            }
            
            // check if day month year have correct value
            String t[] = date.split("/");
            int month = Integer.parseInt(t[1]);
            int day = Integer.parseInt(t[0]);
            int year = Integer.parseInt(t[2]);
            
            if(month<1 || month>12)
            {
            messages[4] = "Error\nWrong month. Month in date must be between 1 and 12.";
            messages[3] = "";
            return false;  
            }
            // max days for each month
            int days [] = {31, 29 , 31, 30, 31, 30, 31, 31, 30, 31, 30 ,31};
            
            if(day<1 || day>days [month-1])
            {
            messages[4] = "Error\nWrong day for month "+ month +".";
            messages[3] = "";
            return false;    
            }
            
            if(year<20)
            {
            messages[4] = "Error\nWrong year. Enter correct year.";
            messages[3] = "";
            return false;     
            }
           
            // check if SIR email has sort code
            if(lines.size()<2){ messages[4] = "Error\nSIR email must contain valid sort code."; return false;}
            else{ sortCode = lines.get(1); }
            
            String tempMessage = sortCode;
            sortCode = sortCode.toLowerCase();
            sortCode = sortCode.replace(":", "");
            sortCode = sortCode.replace("sort", "");
            sortCode = sortCode.replace("code", "");
            sortCode = sortCode.replace("code:", "");
            sortCode = sortCode.trim();
            
            patern = Pattern.compile("([0-9]{2})-([0-9]{2})-([0-9]{2})");    // pattern for sort code  
            matcher = patern.matcher(sortCode);  
            
            if(!matcher.matches())
            {
            messages[4] = "Error\nWrong format of sort code. Sort code must have the following format for instance"
                    + "11-22-33";
            return false;    
            }
            
            // check if SIR email has sort code
            if(lines.size()<3){ messages[4] = "Error\nSIR email must contain valid nature of incident."; return false;}
            else{ incident = lines.get(2); }
            
            tempMessage += " "+incident;
            incident = incident.toLowerCase();
            incident = incident.replace(":", "");
            incident = incident.replace("nature", "");
            incident = incident.replace("of", "");
            incident = incident.replace("incident", "");
            incident = incident.replace("incident:", "");
            incident = incident.trim();
            
            boolean invalidIncident = true;
            for(int i=0; i<sirIncidents.length; i++)
            {
                if(sirIncidents[i].toLowerCase().equals(incident)){ invalidIncident = false;}
            }
            
            if(invalidIncident)
            {
                messages[4] = "Error\nSIR email must contain valid nature of incident, for instance: ";
                for(int i=0; i<sirIncidents.length; i++){ messages[4] += sirIncidents[i] + ", ";}
                return false;
            }
            
            // check if SIR email has message
            if(lines.size()<4){ messages[4] = "Error\nSIR email must contain message."; return false;}
            else{ rest += tempMessage+" "; for(int i=3; i<lines.size(); i++){ rest += lines.get(i)+" "; }}
            rest = rest.trim();
            // check message length
            if(rest.length()>1028){messages[4] = "Error\nSIR email message must not exceed 1028 characters."; return false;}
            
        }
        else                                            // normal email
        {
            if(lines.size()<2){ messages[4] = "Error\nEmail must contain message."; return false;}
            else{for(int i=1; i<lines.size(); i++){ rest += lines.get(i)+" "; }}
            // check message length
            if(rest.length()>1028){messages[4] = "Error\nEmail message must not exceed 1028 characters."; return false;}
        }
        // removes url
        rest = replaceURL(rest);
        
        object[3] = rest;
        messages[4] = "Message:\t"+rest+"\n";
        // adds sir to sir file and list if email is SIR
        if(isSir)
        {    
        sirList.save(sortCode+" "+incident);    // add SIR to file
        sirList.add(sortCode+" "+incident);     // add SIR to ArrayList
        }   
        return true;
    }
    // -------------------------------------------------------------------------
    
    // method allows to add message to file
    public String[] addMessage(String header, String body)
    {    
    // holds strings for JSON object, 0 - message id, 1 - message sender, 2 - message subject, 3 -message
    String object[] = new String[4];   
    // initialize each object string with empty string
    for(int i=0; i<object.length; i++){ object[i]=""; }
    
    // messages displayed during processing each message
    // 0 - empty header/body, 1 - message type, 2 - message sender, 3 - message subject, 4 - message, 5 - save status
    String messages[] = new String[6]; 
    // initialize each message with empty string
    for(int i=0; i<messages.length; i++){ messages[i]=""; }
     
    // check if header and/or body is not empty
    if(header.equals("") || body.equals(""))
    { 
        messages[0] = "Error\nBoth \"Header\" and \"Body\" must not be empty."; 
        return messages;
    }
    
    // get type of message and validate Message ID in header
    char type = checkHeader(header, object);
    
    // used to skip step if message body is incorrect
    boolean nextStep = true;
    
    // based on message type, message will be processed in different way
    switch(type)
    {
        case 'S':                           // SMS
        {   
            messages[1] = "Type:\tSMS\n";                   
            nextStep = processSMS( body, messages, object);
            break;
        }
        case 'E':                           // Email
        {
           messages[1] = "Type:\tEmail\n";
           nextStep = processEmail(body, messages, object);
           break;
        }
        case 'T':                           // Tweet
        {
            messages[1] = "Type:\tTweet\n";
            nextStep = processTweet( body, messages, object);
            break;
        }
        default:                            // if type == 'W' - error
        {
        messages[1] = "Error\nWrong format of Message ID in Header. Message ID in Header must start with "
                + "letter: 'S' or 's' or 'E' or 'e' or 'T' or 't' followed by 9 numeric characters (0 to 9)."
                + " '-' and '   '  are allowed.";  
        return messages;
        }
    }
    
    // if message was processed properly append it to JSON file
    if(nextStep)
    { 
        if(save(object)){ messages[5] = "Status:\tMessage was successfully saved to file.";}
        else{ messages[5] = "Error\nCould not write data to file. Please try again.";}
        
    }
    return messages;
    }
    // -------------------------------------------------------------------------
    
    // replaces URL with <URL Quarantined>
    private String replaceURL(String s)
    {
        String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        return s.replaceAll(regex, "<URL Quarantined>");
    }
    // -------------------------------------------------------------------------
    
    // saves message to JSON file
    private boolean save(String object[])
    {
            JSONObject obj = new JSONObject();  
            obj.put("message_id", object[0]);
            obj.put("sender", object[1]);
            obj.put("subject", object[2]);
            obj.put("message", object[3]);
            
            File myfile = new File (messagesFile);
            try
            {
            FileWriter fw = new FileWriter(myfile, true);   
                try (BufferedWriter bw = new BufferedWriter(fw)) 
                {
                    bw.write (obj.toJSONString());
                    bw.newLine();
                    bw.flush();
                } 
            return true;
            }
            catch(IOException ex)
            {  
            return false;
            }
    }
    // -------------------------------------------------------------------------
    
    // allows to import messages from txt file
    public ArrayList<String> importMessages(String file)
    {
        ArrayList<String> list = new ArrayList<>();
        File myFile = new File(file);
     try 
       {
       FileReader fr = new FileReader(myFile);  
       BufferedReader br = new BufferedReader(fr); 
       String data="";
       while(  (data = br.readLine()) != null   )
          {
           list.add(data);           
          }
       
         } 
        catch (IOException e) 
         {
           System.out.println("An error has occurred while loading data");  
         }
        
        return list;
    }
    // -------------------------------------------------------------------------
    
    // allows to select txt file to load data from
    public String selectFile()
    {
        String selectedFile = "";
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
        chooser.setFileFilter(filter);
        int returnValue = chooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION) { selectedFile =   chooser.getSelectedFile().getPath(); }
        return selectedFile;
    }
    // ------------------------------------------------------------------------- 

    public String getTrending() { return trendList.getList(); }         // gets trendng list as String
    public String getMentions() { return mentList.getList(); }          // gets mentions list as String
    public String getSIR() {return sirList.getList(); }                 // gets SIR list as String
}
