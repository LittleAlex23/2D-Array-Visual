package Com.Alex;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public abstract class SidePanel extends JPanel {
    protected javax.swing.ButtonGroup buttonGroup;
    protected final JButton[][] cellList;
    protected final JLabel statusLabel;
    protected JRadioButton selected;
    protected boolean hasDirtied;
    public SidePanel(){
        cellList = GUI.cellList;
        statusLabel = GUI.statusLabel;
        buttonGroup = new javax.swing.ButtonGroup();
    }
    public void cleanCells(){
        for(int i = 0; i < GUI.SIZE; i++)
            for(int j = 0; j < GUI.SIZE; j++){
                    cellList[i][j].setBackground(Color.WHITE);
            }
        hasDirtied = false;
    }
    public abstract void removeListeners();
    public abstract void setCells();
}
