package Com.Alex.FFStep;


public class FoundStartingCell implements FloodFillState{
    private final FloodFillAlgorithm state;
    public FoundStartingCell(FloodFillAlgorithm state){
        this.state = state;
    }
    // Find a new cell to start flooding or else the flooding is complete
    @Override
    public void next() {
        FloodFillStep step = state.getFloodFill();
        if(step.findStartingCell())
            state.setState(state.getFilledCellState());
        else
            state.setState(state.getEndState());
    }
    @Override
    public String toString(){
        return "Start flooding state";
    }
}
