package detection;

public class EmptyDetection implements Detection {
    @Override
    public boolean detect() {
        return false;
    }

    @Override
    public void collision() {

    }
}
