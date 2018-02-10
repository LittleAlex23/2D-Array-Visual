package Com.Alex.FloodFill.State;


public class EndState implements FloodFillState{
    private final FloodFill state;
    public EndState(FloodFill state){
        this.state = state;
    }
    @Override
    public void next() {
        state.signalStop();
    }
    @Override
    public String toString(){
        return "End state";
    }
}
