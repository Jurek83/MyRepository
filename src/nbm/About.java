/*
 * class gives some hints how to use system
 */
package nbm;
/**
 * @author Jurek
 */
public class About extends javax.swing.JPanel {
    
    // string contains hints how to use program
    private final String instructions = "How to use program:\n\n"
            + "A. To add single message click File then click Add Message. Both fields Header and Body must be filled in.\n\n"
            + "1. To add SMS type in Header 'S' or 's' followed by 9 numeric characters (0-9). '-' and '   '  are allowed. \n"
            + "Then type in Body international phone number. International phone number must start with '+' or '00'. \n"
            + "Then international phone number may contain only numeric characters (0-9) and '-'. Spaces '   ' are not allowed.\n"
            + "International phone number must have at least 9 characters and no more than 16 characters including '+' and '00' and excluding '-'.\n"
            + "Then type SMS message. SMS message must not be empty and must not exceed 140 characters.\n\n"
            + "2. To add Email type in Header 'E' or 'e' followed by 9 numeric characters (0-9). '-' and '   '  are allowed. \n"
            + "Then type in first line firstname lastname email and subject separated by space '  ' \n"
            + "Email must have valid email address. Subject must not be empty and must not exceed 20 characters.\n"
            + "2.1 To add SIR email in subject type SIR and date in format dd/mm/yy.\n"
            + "Then in next line type valid sort code for instance 11-22-33 and in the next line type valid nature of incident.\n"
            + "\"Theft\", \"Staff Attack\", \"ATM Theft\", \"Raid\", \"Customer Attack\"," 
            + "\"Staff Abuse\", \"Bomb Threat\", \"Terrorism\", \"Suspicious Incident\"\n,"
            + " \"Intelligence\", \"Cash Loss\". Finally in next line type email message.\n"
            + "2.2 To add ordinary email type normal subject and then in next line type email message.\n"
            + "Email message must not be empty and must not exceed 1028 characters (including sort code and nature of incident.).\n\n"
            + "3. To add Tweet type in Header 'T' or 't' followed by 9 numeric characters (0-9). '-' and '   '  are allowed. \n"
            + "Then type in Body Tweeter ID. Tweeter ID must start with '@'. Tweeter ID may have up to 16 characters including '@' character.\n"
            + "Then type Tweet message. Tweet message must not be empty and must not exceed 140 characters.\n\n"
            + "B: To load messages from file click File then Open File. Then select file to load messages and click Load. \n"
            + "Only txt files are allowed. Each file must be prepared properly in order to be properly processed by system.\n";

    // constructor
    public About() {
        initComponents();
        textAreaHint.setText(instructions); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneHint = new javax.swing.JScrollPane();
        textAreaHint = new javax.swing.JTextArea();

        textAreaHint.setEditable(false);
        textAreaHint.setColumns(20);
        textAreaHint.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        textAreaHint.setLineWrap(true);
        textAreaHint.setRows(5);
        scrollPaneHint.setViewportView(textAreaHint);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneHint, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneHint, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPaneHint;
    private javax.swing.JTextArea textAreaHint;
    // End of variables declaration//GEN-END:variables
}
