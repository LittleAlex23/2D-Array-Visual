package Com.Alex.FFStep;


public class StartState implements FloodFillState{
    private final FloodFillAlgorithm state;
    public StartState(FloodFillAlgorithm state){
        this.state = state;
    }

    @Override
    public void next() {
        state.setState(state.getStartFloodingState());
    }
    @Override
    public String toString(){
        return "Start state";
    }
}
