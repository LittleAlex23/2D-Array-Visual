package Com.Alex.FloodFill.Imp;

import Com.Alex.FloodFill.State.FloodFill;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class FPanel extends javax.swing.JPanel {
    private final FloodFillAlgorithm flood;
    private final FloodFill floodstep;
    private final int SIZE = 10;
    private final JButton[][] cellList;
    private final ActionListener listener;
    private final ActionListener radioButtonListener;
    private JRadioButton selected;
    private final String status = "status:";
    private final JLabel statusLabel;
    public FPanel(JLabel statusLabel, JButton[][] cellList) {
        initComponents();
        this.cellList = cellList;
        this.statusLabel = statusLabel;
        
        listener = new CellListener();
        radioButtonListener = new RadioButtonListener();
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                this.cellList[i][j].addActionListener(listener);
        flood = new FloodFillAlgorithm();
        floodstep = new FloodFill(this.cellList);
        initializeRadioButton();
    }
    public final void initializeRadioButton(){
        buttonGroup1.add(groundButton);
        buttonGroup1.add(blankButton);
        groundButton.setSelected(true);
        selected=groundButton;
        groundButton.addActionListener(radioButtonListener);
        blankButton.addActionListener(radioButtonListener);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        blankButton = new javax.swing.JRadioButton();
        groundButton = new javax.swing.JRadioButton();
        clearButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(181, 155));

        blankButton.setBackground(new java.awt.Color(255, 255, 255));
        blankButton.setText("0");

        groundButton.setBackground(new java.awt.Color(204, 51, 0));
        groundButton.setText("1");

        clearButton.setText("clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        nextButton.setText(">>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        startButton.setText("start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(blankButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(groundButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
            .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(startButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(groundButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++){
                if(!cellList[i][j].getText().equals("0")){
                    cellList[i][j].setBackground(Color.WHITE);
                    cellList[i][j].setText("0");
                }
            }
        floodstep.reset();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        statusLabel.setText(status + " " + floodstep.next());
    }//GEN-LAST:event_nextButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        flood.setUp(cellList);
        flood.solve();
    }//GEN-LAST:event_startButtonActionPerformed

    class CellListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(!b.getText().equals(selected.getText())){
                b.setBackground(selected.getBackground());
                b.setText(selected.getText());
            }
        }
    }
    private class RadioButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selected = (JRadioButton)e.getSource();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton blankButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearButton;
    private javax.swing.JRadioButton groundButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
