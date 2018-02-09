package Com.Alex.FloodFill.State;

public class CheckingNeighborState implements FloodFillState{
    private final FloodFill state;
    public CheckingNeighborState(FloodFill state){
        this.state = state;
    }
    @Override
    public void next() {
        FloodFillAlgorithmStep step = state.getFloodFill();
        if(!step.findNeighbor())
            state.setState(state.getFilledCellState());
        else{
            state.setState(state.getStartFloodingState());
        }
    }
    @Override
    public String toString(){
        return "Coloring neighbor state";
    }
}
