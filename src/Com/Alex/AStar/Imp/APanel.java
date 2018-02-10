package Com.Alex.AStar.Imp;

import Com.Alex.GUI;
import Com.Alex.SidePanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class APanel extends SidePanel {
    public static APanel instance;
    private final ActionListener listener;
    private final ActionListener radioButtonListener;
    private final String status = "status:";
    private final AStarAlgorithm algorithm;
    private JButton source;
    private JButton destination;
    private APanel() {
        initComponents();
        algorithm = new AStarAlgorithm();
        listener = new CellListener();
        radioButtonListener = new RadioButtonListener();
        initializeRadioButton();
    }
    public static APanel getInstance(){
        if(instance == null){
            instance = new APanel();
        }
        return instance;
    }
    public final void initializeRadioButton(){
        Component[] comp = buttonPanel.getComponents();
            for(Component c : comp){
                buttonGroup.add((JRadioButton)c);
            }
        JRadioButton defaultButton = (JRadioButton)buttonPanel.getComponents()[0];
        defaultButton.setSelected(true);
        selected=defaultButton;
    }
    @Override
    public void setCells(){
        for(int i = 0; i < GUI.SIZE; i++)
            for(int j = 0; j < GUI.SIZE; j++){
               cellList[i][j].setText(String.valueOf(i*GUI.SIZE + j));
            }
        
        addListeners();
    }
    private void addButtonListener(){
        if(cellList[0][0].getListeners(ActionListener.class).length != 1){
            for(int i = 0; i < GUI.SIZE; i++)
                for(int j = 0; j < GUI.SIZE; j++)
                    this.cellList[i][j].addActionListener(listener);
        }
    }
    private void addRadioListener(){
        if(sourceRadioButton.getListeners(ActionListener.class).length != 1){
            Component[] comp = buttonPanel.getComponents();
            for(Component c : comp){
                JRadioButton button = (JRadioButton)c;
                button.addActionListener(radioButtonListener);
            }
        }
    }
    public void removeButtonListener(){
        if(cellList[0][0].getListeners(ActionListener.class).length != 0){
            for(int i = 0; i < GUI.SIZE; i++)
                for(int j = 0; j < GUI.SIZE; j++)
                    this.cellList[i][j].removeActionListener(listener);
        }
    }
    private void removeRadioListener(){
        if(sourceRadioButton.getListeners(ActionListener.class).length != 0){
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
    private class RadioButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selected = (JRadioButton)e.getSource();
        }
    }
    public final void addListeners(){
        addButtonListener();
        addRadioListener();
    }
    private void checkForSourceReplaced(JButton clickedCell){
        if(clickedCell.getBackground().equals(sourceRadioButton.getBackground()))
            source = null;
    }
    private void checkForDestinationReplaced(JButton clickedCell){
        if(clickedCell.getBackground().equals(destRadioButton.getBackground()))
            destination = null;
    }
    class CellListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedCell = (JButton)e.getSource();
            if(!clickedCell.getBackground().equals(selected.getBackground())){
                if(selected == sourceRadioButton){
                    if(source != null)
                        source.setBackground(Color.WHITE);
                    source = clickedCell;
                    checkForDestinationReplaced(clickedCell);
                }
                else if(selected == destRadioButton){
                    if(destination != null)
                        destination.setBackground(Color.WHITE);
                    destination = clickedCell;
                    checkForSourceReplaced(clickedCell);
                }
                else if(selected.getBackground().equals(Color.black)){
                    checkForDestinationReplaced(clickedCell);
                    checkForSourceReplaced(clickedCell);
                }
                clickedCell.setBackground(selected.getBackground());
                hasDirtied = true;
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        solveButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        plain = new javax.swing.JRadioButton();
        wall = new javax.swing.JRadioButton();
        sourceRadioButton = new javax.swing.JRadioButton();
        destRadioButton = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(181, 155));

        solveButton.setText("solve");
        solveButton.setFocusable(false);
        solveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveButtonActionPerformed(evt);
            }
        });

        clearButton.setText("clear");
        clearButton.setFocusable(false);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        plain.setBackground(new java.awt.Color(255, 255, 255));
        plain.setText("clean");
        plain.setFocusable(false);

        wall.setBackground(new java.awt.Color(0, 0, 0));
        wall.setText("wall");
        wall.setFocusable(false);

        sourceRadioButton.setBackground(new java.awt.Color(102, 255, 51));
        sourceRadioButton.setText("s");
        sourceRadioButton.setFocusable(false);

        destRadioButton.setBackground(new java.awt.Color(255, 51, 0));
        destRadioButton.setText("d");
        destRadioButton.setFocusable(false);

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(destRadioButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(wall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(plain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(sourceRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(destRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wall)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plain)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(solveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
            .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solveButton))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Solve the A* algorithm
    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveButtonActionPerformed
        if(source != null && destination != null){
            algorithm.setUp(cellList, Integer.valueOf(source.getText()), Integer.valueOf(destination.getText()));
            removeButtonListener();
            if(!algorithm.solve())
                statusLabel.setText(status + " impossible");
        }
        else
            statusLabel.setText(status + " need both source and destination");
        hasDirtied = true;
    }//GEN-LAST:event_solveButtonActionPerformed

    // Set background color of all jbutton instances to white, if any
    // Reassign action listener to jbutton instances
    // Set source and destination to null
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        if(hasDirtied){
            for(int i = 0; i < GUI.SIZE; i++)
                for(int j = 0; j < GUI.SIZE; j++){
                    if(cellList[i][j].getBackground().getRGB() != Color.WHITE.getRGB()){
                        cellList[i][j].setBackground(Color.WHITE);
                    }
                }
        }
        addButtonListener();
        source = destination = null;
        hasDirtied = false;
    }//GEN-LAST:event_clearButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JRadioButton destRadioButton;
    private javax.swing.JRadioButton plain;
    private javax.swing.JButton solveButton;
    private javax.swing.JRadioButton sourceRadioButton;
    private javax.swing.JRadioButton wall;
    // End of variables declaration//GEN-END:variables
}
