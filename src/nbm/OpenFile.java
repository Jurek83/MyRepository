/*
 * class allows to load messages from txt file
 */
package nbm;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * @author Jurek
 */
public class OpenFile extends javax.swing.JPanel {

    private final Message message;      // Message object responsible for processing message(s)

    // constructor
    public OpenFile(Message mess) {
        initComponents();
        message = mess; 
        labelFileName.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonSelectFile = new javax.swing.JButton();
        labelSelectFile = new javax.swing.JLabel();
        labelFileName = new javax.swing.JLabel();
        buttonLoad = new javax.swing.JButton();
        scrollPanelStatus = new javax.swing.JScrollPane();
        textPaneStatus = new javax.swing.JTextPane();
        labelStatus = new javax.swing.JLabel();
        labelSIRList = new javax.swing.JLabel();
        scrollPaneSIR = new javax.swing.JScrollPane();
        textAreaSIR = new javax.swing.JTextArea();
        scrollPaneTrending = new javax.swing.JScrollPane();
        textAreaTrending = new javax.swing.JTextArea();
        scrollPaneMentions = new javax.swing.JScrollPane();
        textAreaMentions = new javax.swing.JTextArea();
        labelMentionsList = new javax.swing.JLabel();
        labelTrendingList = new javax.swing.JLabel();
        labelLoadData = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(760, 560));
        setMinimumSize(new java.awt.Dimension(760, 560));
        setPreferredSize(new java.awt.Dimension(760, 560));

        buttonSelectFile.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        buttonSelectFile.setText("Select");
        buttonSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectFileActionPerformed(evt);
            }
        });

        labelSelectFile.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelSelectFile.setText(" Select File:");

        labelFileName.setText("labelFileName");

        buttonLoad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        buttonLoad.setText("Load");
        buttonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoadActionPerformed(evt);
            }
        });

        scrollPanelStatus.setViewportView(textPaneStatus);

        labelStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelStatus.setText(" Status:");

        labelSIRList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelSIRList.setText(" SIR List:");

        textAreaSIR.setEditable(false);
        textAreaSIR.setColumns(20);
        textAreaSIR.setRows(3);
        textAreaSIR.setTabSize(4);
        scrollPaneSIR.setViewportView(textAreaSIR);

        textAreaTrending.setEditable(false);
        textAreaTrending.setColumns(16);
        textAreaTrending.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textAreaTrending.setRows(3);
        textAreaTrending.setTabSize(4);
        scrollPaneTrending.setViewportView(textAreaTrending);

        textAreaMentions.setEditable(false);
        textAreaMentions.setColumns(16);
        textAreaMentions.setRows(3);
        textAreaMentions.setTabSize(4);
        scrollPaneMentions.setViewportView(textAreaMentions);

        labelMentionsList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelMentionsList.setText(" Mentions List:");

        labelTrendingList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTrendingList.setText(" Trending List:");

        labelLoadData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelLoadData.setText(" Load Data:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanelStatus)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSIRList, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMentionsList, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(labelTrendingList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelLoadData, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(buttonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneSIR, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(scrollPaneMentions, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPaneTrending, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSelectFile)
                    .addComponent(buttonSelectFile)
                    .addComponent(labelFileName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLoadData)
                    .addComponent(buttonLoad))
                .addGap(18, 18, 18)
                .addComponent(labelStatus)
                .addGap(18, 18, 18)
                .addComponent(scrollPanelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSIRList)
                    .addComponent(labelMentionsList)
                    .addComponent(labelTrendingList))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneMentions, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(scrollPaneTrending)
                    .addComponent(scrollPaneSIR))
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

     // adds text to text pane when processing messages
  private void appendToPane(JTextPane textPane, String msg, Color color)
    {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attribSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        attribSet = styleContext.addAttribute(attribSet, StyleConstants.FontFamily, "Arial");
        attribSet = styleContext.addAttribute(attribSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        int length = textPane.getDocument().getLength();
        textPane.setCaretPosition(length);
        textPane.setCharacterAttributes(attribSet, false);
        textPane.replaceSelection(msg);
    }
    
    // allows to select txt file to load data 
    private void buttonSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectFileActionPerformed
        labelFileName.setText(message.selectFile());
    }//GEN-LAST:event_buttonSelectFileActionPerformed
    // allows to load messages from select6ed file
    private void buttonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoadActionPerformed
        
        textPaneStatus.setText("");         // clears status text pane
        textAreaMentions.setText("");       // clears mentions textarea 
        textAreaTrending.setText("");       // clears trending textarea
        textAreaSIR.setText("");            // clears SIR textarea         
        textPaneStatus.setEditable(true);   // allows to add message to textPaneStatus    
        if(!labelFileName.getText().equals("")) // if file was selected
        {
         
        ArrayList<String> messagesList = message.importMessages(labelFileName.getText());   // gets imported messages
        
        ArrayList<String> header = new ArrayList<>();       // holds message header 
        ArrayList<String> body = new ArrayList<>();         // holds message body
        ArrayList<String> status = new ArrayList<>();       // holds status messages being processed
        
        // prepares messages before adding them
        for(int i=0; i<messagesList.size(); i++)
        { 
            String temp[] = messagesList.get(i).split("strBreak");
            String s = "";
            
            for(int x=1; x<temp.length; x++)
            {
                temp[x] = temp[x].replaceAll("\t", "");
                temp[x] = temp[x].replaceAll("\n", ""); 
                temp[x] = temp[x].trim();
                s += temp[x]+" \n ";
            }
           header.add(temp[0]);     
           body.add(s);
        }
        
        // prepares status messges
        for(int i=0; i<header.size(); i++)
        {
         String messages[] = message.addMessage(header.get(i), body.get(i));
         status.add("Message: "+(i+1)+" \n");   

         for(int y = 0; y<messages.length; y++)
         {
         if(!messages[y].equals("")){status.add(messages[y]);  }
         }
         status.add("\n\n");
        }
        // adds messages to textPane
        for (String message : status) 
            {
                if(!message.equals(""))
                {
                    if(!message.startsWith("Error"))
                    {
                        appendToPane(textPaneStatus,message, Color.BLACK);
                    }
                    else
                    {
                        appendToPane(textPaneStatus, message, Color.RED);
                    }
                }
            }
       
            textAreaMentions.setText(message.getTrending());            // set trending list textarea
            textAreaTrending.setText(message.getMentions());            // set mentions list textarea
            textAreaSIR.setText(message.getSIR());                      // set sir list textarea
            textPaneStatus.setEditable(false);                          // remove ability to edit textPane
            labelFileName.setText("");                                  // clears fileName
            }
        else                                                            // if no fileName selected display error
        {
            appendToPane(textPaneStatus, "Error\nNo file was selected to load data."
            + " Please select '.txt'file and then click Load to load messages.", Color.RED);
        }     
    }//GEN-LAST:event_buttonLoadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLoad;
    private javax.swing.JButton buttonSelectFile;
    private javax.swing.JLabel labelFileName;
    private javax.swing.JLabel labelLoadData;
    private javax.swing.JLabel labelMentionsList;
    private javax.swing.JLabel labelSIRList;
    private javax.swing.JLabel labelSelectFile;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelTrendingList;
    private javax.swing.JScrollPane scrollPaneMentions;
    private javax.swing.JScrollPane scrollPaneSIR;
    private javax.swing.JScrollPane scrollPaneTrending;
    private javax.swing.JScrollPane scrollPanelStatus;
    private javax.swing.JTextArea textAreaMentions;
    private javax.swing.JTextArea textAreaSIR;
    private javax.swing.JTextArea textAreaTrending;
    private javax.swing.JTextPane textPaneStatus;
    // End of variables declaration//GEN-END:variables
}
