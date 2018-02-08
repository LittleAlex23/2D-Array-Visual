package Com.Alex.FFStep;


public class ColoringState implements FloodFillState{
    private final FloodFillAlgorithm state;
    public ColoringState(FloodFillAlgorithm state){
        this.state = state;
    }
    @Override
    public void next() {
        FloodFillStep step = state.getFloodFill();
        step.fillWater();
        state.setState(state.getNeighborCheckedState());
    }
    @Override
    public String toString(){
        return "Coloring state";
    }
}
