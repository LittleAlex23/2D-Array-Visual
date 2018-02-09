/*Error reading included file Templates/GUIForms/Classes/../Licenses/license-default.txt*/

package Com.Alex.AStar.Imp;


public class Node implements Comparable<Node>{
    private double f = Integer.MAX_VALUE;
    private double g = Integer.MAX_VALUE;
    private double h;
    private final int i;
    private final int j;
    private Node parent;
    public Node(int i, int j){
        this.i = i;
        this.j = j;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getF() {
        return f;
    }
    public void setF() {
        f = g + h;
    }
    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public int compareTo(Node o) {
        if(this.f < o.getF())
            return -1;
        else if(this.f > o.getF())
            return 1;
        else
            return 0;
    }
}
