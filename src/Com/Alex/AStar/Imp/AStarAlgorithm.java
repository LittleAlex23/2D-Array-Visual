package Com.Alex.AStar.Imp;

import java.awt.Color;
import java.util.Iterator;
import java.util.PriorityQueue;
import javax.swing.JButton;
import javax.swing.JPanel;


public class AStarAlgorithm extends JPanel{
    private JButton[][] cellButtons;
    private Node[][] nodes;
    private Node source;
    private Node destination;
    private final PriorityQueue<Node> openList = new PriorityQueue<>();
    private final PriorityQueue<Node> closedList = new PriorityQueue<>();
    public AStarAlgorithm(){
    
    }
    public boolean isWall(Color current, Color wall){
        return current.getRGB() == wall.getRGB();
    }
    public Node findSmallestF(){
        Node node = openList.element();
        double smallestValue = node.getF();
        for (Node current : openList) {
            double currentValue = current.getF();
            if(currentValue < smallestValue){
                smallestValue = currentValue;
                node = current;
            }
        }
        return node;
    }
    public boolean solve(){
        openList.add(source);
        while(!openList.isEmpty()){
            Node current = findSmallestF();
            if(current == destination)
                break;
            int x = current.getI();
            int y = current.getJ();
            openList.remove(current);
            closedList.offer(current);
            int[][] neighbor = new int[][]{{x-1,y},{x,y+1},{x+1,y},{x,y-1}};
            for(int i = 0; i < 4; i++){
                int[] n = neighbor[i];
                if((0 <= n[0] && n[0] < cellButtons.length) && (0 <= n[1] && n[1] < cellButtons.length) 
                        && !closedList.contains(nodes[n[0]][n[1]])
                        && !isWall(cellButtons[n[0]][n[1]].getBackground(),Color.BLACK)){
                    if(current.getG() < nodes[n[0]][n[1]].getG() + 1){
                        nodes[n[0]][n[1]].setG(current.getG()+1);
                        nodes[n[0]][n[1]].setParent(current);
                        nodes[n[0]][n[1]].setF();
                    }
                    if(!openList.contains(nodes[n[0]][n[1]]))
                        openList.add(nodes[n[0]][n[1]]);
                }
            }
        }
        
        if(openList.isEmpty())
            return false;
        Node current = destination.getParent();
        while(current != source){
            cellButtons[current.getI()][current.getJ()].setBackground(java.awt.Color.CYAN);
            current = current.getParent();
        }
        return true;
    }
    public final void setUp(JButton[][] cellButtons, int sourceId, int destinationId){
        this.cellButtons = cellButtons;
        this.source = new Node(sourceId/cellButtons.length, sourceId%cellButtons.length);
        this.destination = new Node(destinationId/cellButtons.length, destinationId%cellButtons.length);
        nodes = new Node[cellButtons.length][cellButtons.length];
        for (int i = 0; i < cellButtons.length; i++) {
            for (int j = 0; j < cellButtons[0].length; j++) {
                if(source.getI() == i && source.getJ() == j){
                    nodes[i][j] = source;
                    source.setG(1);
                }
                else if(destination.getI() == i && destination.getJ() == j)
                    nodes[i][j] = destination;
                else
                    nodes[i][j] = new Node(i,j);
                nodes[i][j].setH(getDistance(nodes[i][j], destination));
            }
        }
        source.setF();
    }
    private double getDistance(Node current, Node destination){
        return Math.sqrt(Math.pow(current.getI() - destination.getI(), 2) + Math.pow(current.getJ() - destination.getJ(), 2));
    }
}
