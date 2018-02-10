package Com.Alex.FloodFill.State;

import Com.Alex.FloodFill.Imp.Node;
import Com.Alex.GUI;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;


public class FloodFillAlgorithmStep {
    private JButton[][] cellButtons;
    private Node[][] nodes;
    private Node current;
    private int currentI;
    private int currentJ;
    private final Queue<Node> q = new LinkedList();
    
    // Find first cell to start filling water
    public boolean findStartingCell() {
        int rowSize = cellButtons.length;
        int columnSize = cellButtons[0].length;
        for (int i = currentI; i < rowSize; i++) {
            for (int j = currentJ; j < columnSize; j++) {
                JButton c = cellButtons[i][j];
                if ((i == 0 || i == rowSize - 1 || j == 0 || j == columnSize - 1) && c.getText().equals("0")) {
                    q.add(nodes[i][j]);
                    currentI = i;
                    currentJ = j+1;
                    c.setBackground(Color.red);
                    c.setText("2");
                    return true;
                }
            }
        }
        return false;
    }
    // Color current square blue
    public void fillWater(){
        current = q.remove();
        int x = current.x;
        int y = current.y;
        if(cellButtons[x][y].getBackground()!=Color.BLUE)
            cellButtons[x][y].setBackground(Color.BLUE);
        else
            cellButtons[x][y].setBackground(Color.ORANGE);
    }
    
    // Add any neighbor that can be flooded to the queue
    public boolean findNeighbor() {
        int rowSize = cellButtons.length;
        int columnSize = cellButtons[0].length;
        int x = current.x;
        int y = current.y;
        int[][] neighbor = new int[][]{{x-1,y},{x,y+1},{x+1,y},{x,y-1}};
        for(int i = 0; i < 4; i++){
            int[] n = neighbor[i];
            if((0 <= n[0] && n[0] < rowSize) && (0 <= n[1] && n[1] < columnSize) 
                    && cellButtons[n[0]][n[1]].getText().equals("0")){
                q.add(nodes[n[0]][n[1]]);
                cellButtons[n[0]][n[1]].setText("2");
                cellButtons[n[0]][n[1]].setBackground(Color.green);
            }
        }
        return q.isEmpty();
    }
    public FloodFillAlgorithmStep(){
    }
    
    // Reset the state
    public void reset(){
        currentI = currentJ = 0;
        q.clear();
    }
    public final void setUp(){
        this.cellButtons = GUI.cellList;
        nodes = new Node[cellButtons.length][cellButtons.length];
        for (int i = 0; i < cellButtons.length; i++) {
            for (int j = 0; j < cellButtons[0].length; j++) {
                nodes[i][j] = new Node(i,j);
            }
        }
    }
}
