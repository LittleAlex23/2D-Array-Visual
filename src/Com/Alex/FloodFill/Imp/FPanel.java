package Com.Alex.FloodFill.Imp;

import Com.Alex.FloodFill.State.FloodFill;
import Com.Alex.GUI;
import Com.Alex.SidePanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class FPanel extends SidePanel {
    public static FPanel instance;
    private final FloodFillAlgorithm flood;
    private final FloodFill floodFill;
    private final ActionListener listener;
    private final ActionListener radioButtonListener;
    private final String status = "status:";
    private Timer timer;
    private FFTimerTask task;
    private FPanel() {
        initComponents();
        listener = new CellListener();
        radioButtonListener = new RadioButtonListener();
        flood = new FloodFillAlgorithm();
        floodFill = new FloodFill();
        initializeRadioButton();
    }
    public static FPanel getInstance(){
        if(instance == null){
            instance = new FPanel();
        }
        return instance;
    }
    @Override
    public void setCells(){
        for(int i = 0; i < GUI.SIZE; i++)
            for(int j = 0; j < GUI.SIZE; j++){
                    cellList[i][j].setText("0");
            }
        addListeners();
    }
    private void initializeRadioButton(){
        Component[] comp = buttonPanel.getComponents();
        for(Component c : comp){
            buttonGroup.add((JRadioButton)c);
        }
        JRadioButton defaultButton = (JRadioButton)buttonPanel.getComponents()[0];
        defaultButton.setSelected(true);
        selected=defaultButton;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clearButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        solveButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        blankButton = new javax.swing.JRadioButton();
        groundButton = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(181, 155));

        clearButton.setText("clear");
        clearButton.setFocusable(false);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        nextButton.setText(">>");
        nextButton.setFocusable(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        solveButton.setText("solve");
        solveButton.setFocusable(false);
        solveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveButtonActionPerformed(evt);
            }
        });

        playButton.setText("play");
        playButton.setFocusable(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        buttonPanel.setEnabled(false);

        blankButton.setBackground(new java.awt.Color(255, 255, 255));
        blankButton.setText("0");
        blankButton.setFocusable(false);

        groundButton.setBackground(new java.awt.Color(204, 51, 0));
        groundButton.setText("1");
        groundButton.setFocusable(false);

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(blankButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(groundButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(blankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groundButton)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
            .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(solveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
            .addComponent(buttonPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearButton)
                .addGap(5, 5, 5)
                .addComponent(playButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solveButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        for(int i = 0; i < GUI.SIZE; i++)
            for(int j = 0; j < GUI.SIZE; j++){
                if(!cellList[i][j].getText().equals("0")){
                    cellList[i][j].setBackground(Color.WHITE);
                    cellList[i][j].setText("0");
                }
            }
        addButtonListener();
        floodFill.reset();
        solveButton.setEnabled(true);
        nextButton.setEnabled(true);
        playButton.setEnabled(true);
        if(playButton.getText().equals("stop")){
            task.cancel();
            timer.purge();
            playButton.setText("play");
        }
        hasDirtied = false;
    }//GEN-LAST:event_clearButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        if(!floodFill.isFinish())
            statusLabel.setText(status + " " + floodFill.next());
        else
            nextButton.setEnabled(false);
        hasDirtied = true;
    }//GEN-LAST:event_nextButtonActionPerformed

    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveButtonActionPerformed
        flood.setUp();
        flood.solve();
        hasDirtied = true;
    }//GEN-LAST:event_solveButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        if(playButton.getText().equals("play")){
            task = new FFTimerTask();
            timer = new Timer();
            timer.scheduleAtFixedRate(task, 0, 100);
            playButton.setText("stop");
        }
        else{
            playButton.setText("play");
            task.cancel();
            timer.purge();
        }
        if(solveButton.isEnabled()){
            solveButton.setEnabled(false);
            removeListeners();
        }
        hasDirtied = true;
    }//GEN-LAST:event_playButtonActionPerformed
    public final void addRadioListener(){
        if(blankButton.getListeners(ActionListener.class).length != 1){
            Component[] comp = buttonPanel.getComponents();
            for(Component c : comp){
                JRadioButton button = (JRadioButton)c;
                button.addActionListener(radioButtonListener);
            }
        }
    }
    public final void addButtonListener(){
        if(cellList[0][0].getListeners(ActionListener.class).length != 1){
            for(int i = 0; i < GUI.SIZE; i++)
                for(int j = 0; j < GUI.SIZE; j++)
                    this.cellList[i][j].addActionListener(listener);
        }
    }
    public final void addListeners(){
        addButtonListener();
        addRadioListener();
    }
    public void removeButtonListener(){
        if(cellList[0][0].getListeners(ActionListener.class).length != 0){
            for(int i = 0; i < GUI.SIZE; i++)
                for(int j = 0; j < GUI.SIZE; j++)
                    this.cellList[i][j].removeActionListener(listener);
        }
    }
    public void removeRadioListener(){
        if(blankButton.getListeners(ActionListener.class).length != 0){
            Component[] comp = buttonPanel.getComponents();
            for(Component c : comp){
                JRadioButton button = (JRadioButton)c;
                button.removeActionListener(radioButtonListener);
            }
        }
    }
    @Override
    public void removeListeners() {
        removeButtonListener();
        removeRadioListener();
    }
    class CellListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(!b.getText().equals(selected.getText())){
                b.setBackground(selected.getBackground());
                b.setText(selected.getText());
                hasDirtied = true;
            }
        }
    }
    private class RadioButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            selected = (JRadioButton)e.getSource();
        }
    }
    class FFTimerTask extends TimerTask{
        public FFTimerTask(){
        }
        @Override
        public void run() {
            if(!floodFill.isFinish())
                statusLabel.setText(status + " " +floodFill.next());
            else{
                this.cancel();
                playButton.setText("play");
                nextButton.setEnabled(false);
                playButton.setEnabled(false);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton blankButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JRadioButton groundButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton solveButton;
    // End of variables declaration//GEN-END:variables
}
