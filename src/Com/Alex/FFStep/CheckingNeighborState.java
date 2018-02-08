package Com.Alex.FFStep;

public class CheckingNeighborState implements FloodFillState{
    private final FloodFillAlgorithm state;
    public CheckingNeighborState(FloodFillAlgorithm state){
        this.state = state;
    }
    @Override
    public void next() {
        FloodFillStep step = state.getFloodFill();
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
