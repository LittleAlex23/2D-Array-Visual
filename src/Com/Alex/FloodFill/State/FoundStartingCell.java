package Com.Alex.FloodFill.State;


public class FoundStartingCell implements FloodFillState{
    private final FloodFill state;
    public FoundStartingCell(FloodFill state){
        this.state = state;
    }
    // Find a new cell to start flooding or else the flooding is complete
    @Override
    public void next() {
        FloodFillAlgorithmStep step = state.getFloodFill();
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
