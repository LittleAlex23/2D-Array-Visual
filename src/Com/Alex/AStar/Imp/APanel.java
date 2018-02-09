package Com.Alex.AStar.Imp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class APanel extends javax.swing.JPanel {
    private JRadioButton selected;
    private final ActionListener listener;
    private final ActionListener radioButtonListener;
    private final String status = "status:";
    private final JLabel statusLabel;
    private final int SIZE = 10;
    private final JButton[][] cellList;
    private final AStarAlgorithm algorithm;
    private JButton source;
    private JButton destination;
    public APanel(JLabel statusLabel, JButton[][] cellList) {
        initComponents();
        algorithm = new AStarAlgorithm();
        this.statusLabel = statusLabel;
        this.cellList = cellList;
        listener = new CellListener();
        radioButtonListener = new RadioButtonListener();
        initializeRadioButton();
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++){
               cellList[i][j].addActionListener(listener);
               cellList[i][j].setText(String.valueOf(i*SIZE + j));
            }
    }
    public final void initializeRadioButton(){
        buttonGroup1.add(destinationButton);
        buttonGroup1.add(sourceButton);
        sourceButton.setSelected(true);
        buttonGroup1.add(wall);
        selected=sourceButton;
        destinationButton.addActionListener(radioButtonListener);
        sourceButton.addActionListener(radioButtonListener);
        wall.addActionListener(radioButtonListener);
    }
    private class RadioButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selected = (JRadioButton)e.getSource();
        }
    }
    class CellListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(!b.getBackground().equals(selected.getBackground())){
                if(selected == sourceButton){
                    if(source != null)
                        source.setBackground(Color.WHITE);
                    source = b;
                }
                else if(selected == destinationButton){
                    if(destination != null)
                        destination.setBackground(Color.WHITE);
                    destination = b;
                }
                b.setBackground(selected.getBackground());
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        solveButton = new javax.swing.JButton();
        sourceButton = new javax.swing.JRadioButton();
        destinationButton = new javax.swing.JRadioButton();
        wall = new javax.swing.JRadioButton();
        clearButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(181, 155));

        solveButton.setText("solve");
        solveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveButtonActionPerformed(evt);
            }
        });

        sourceButton.setBackground(new java.awt.Color(102, 255, 51));
        sourceButton.setText("s");

        destinationButton.setBackground(new java.awt.Color(255, 51, 0));
        destinationButton.setText("d");
        destinationButton.setFocusable(false);

        wall.setBackground(new java.awt.Color(0, 0, 0));
        wall.setText("wall");

        clearButton.setText("clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(solveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
            .addComponent(destinationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sourceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(wall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(sourceButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destinationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wall)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solveButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveButtonActionPerformed
        if(source != null && destination != null){
            algorithm.setUp(cellList, Integer.valueOf(source.getText()), Integer.valueOf(destination.getText()));
            if(!algorithm.solve())
                statusLabel.setText(status + " impossible");;
        }
        else
            statusLabel.setText(status + " need both source and destination");
    }//GEN-LAST:event_solveButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++){
                if(cellList[i][j].getBackground().getRGB() != Color.WHITE.getRGB()){
                    cellList[i][j].setBackground(Color.WHITE);
                    if(selected != null)
                        selected = null;
                }
            }
    }//GEN-LAST:event_clearButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearButton;
    private javax.swing.JRadioButton destinationButton;
    private javax.swing.JButton solveButton;
    private javax.swing.JRadioButton sourceButton;
    private javax.swing.JRadioButton wall;
    // End of variables declaration//GEN-END:variables
}
