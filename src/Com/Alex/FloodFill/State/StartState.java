package Com.Alex.FloodFill.State;


public class StartState implements FloodFillState{
    private final FloodFill state;
    public StartState(FloodFill state){
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
