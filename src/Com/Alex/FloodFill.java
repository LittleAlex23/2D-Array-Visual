package Com.Alex;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;

public class FloodFill {
    private JButton[][] cellButtons;
    private Node[][] nodes;
    
    // Color the current cell and then add any neighbor that can be flooded to the
    // queue list
    public void fill(Queue<Node> q) {
        int rowSize = cellButtons.length;
        int columnSize = cellButtons[0].length;
        while (!q.isEmpty()) {
            Node current = q.remove();
            int x = current.x;
            int y = current.y;
            cellButtons[x][y].setBackground(Color.BLUE);
            int[][] neighbor = new int[][]{{x-1,y},{x,y+1},{x+1,y},{x,y-1}};
            for(int i = 0; i < 4; i++){
                int[] n = neighbor[i];
                if((0 <= n[0] && n[0] < rowSize) && (0 <= n[1] && n[1] < columnSize) && cellButtons[n[0]][n[1]].getText().equals("0")){
                    cellButtons[n[0]][n[1]].setText("2");
                    q.add(nodes[n[0]][n[1]]);
                }
            }
        }
    }

    // Find the first cell to start the flooding
    public void solve() {
        Queue<Node> q = new LinkedList<>();
        int rowSize = cellButtons.length;
        int columnSize = cellButtons[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                JButton current = cellButtons[i][j];
                if ((i == 0 || i == rowSize - 1 || j == 0 || j == columnSize - 1) && current.getText().equals("0")) {
                    q.add(nodes[i][j]);
                    cellButtons[i][j].setText("2");
                    fill(q);
                }
            }
        }
    }

    public FloodFill(){}
    public final void setUp(JButton[][] cellButtons){
        this.cellButtons = cellButtons;
        nodes = new Node[cellButtons.length][cellButtons.length];
        for (int i = 0; i < cellButtons.length; i++) {
            for (int j = 0; j < cellButtons[0].length; j++) {
                nodes[i][j] = new Node(i,j);
            }
        }
    }
    public void start(){
        solve();
    }
}
