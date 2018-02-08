package Com.Alex.FFStep;

import javax.swing.JButton;

public class FloodFillAlgorithm {
    private final FloodFillState initial;
    private final FloodFillState startingFloodCell;
    private final FloodFillState fillCell;
    private final FloodFillState neighbor;
    private final FloodFillState endState;
    private FloodFillState state;
    private final FloodFillStep step;
    public FloodFillAlgorithm(JButton[][] cellButtons){
        step = new FloodFillStep();
        step.setUp(cellButtons);
        initial = new StartState(this);
        startingFloodCell = new FoundStartingCell(this);
        fillCell = new ColoringState(this);
        neighbor = new CheckingNeighborState(this);
        endState = new EndState();
        state = initial;
    }
    public String next(){
        state.next();
        return state.toString();
    }
    public void setState(FloodFillState state){
        this.state = state;
    }
    public FloodFillState getInitialState(){
        return initial;
    }
    public FloodFillState getStartFloodingState(){
        return startingFloodCell;
    }
    public FloodFillState getNeighborCheckedState(){
        return neighbor;
    }
    public FloodFillState getFilledCellState(){
        return fillCell;
    }
    public FloodFillState getEndState(){
        return endState;
    }
    public void reset(){
        step.reset();
        state = initial;
    }
    public FloodFillStep getFloodFill(){
        return step;
    }
}
