package Com.Alex.FloodFill.State;


public class ColoringState implements FloodFillState{
    private final FloodFill state;
    public ColoringState(FloodFill state){
        this.state = state;
    }
    @Override
    public void next() {
        FloodFillAlgorithmStep step = state.getFloodFill();
        step.fillWater();
        state.setState(state.getNeighborCheckedState());
    }
    @Override
    public String toString(){
        return "Coloring state";
    }
}
