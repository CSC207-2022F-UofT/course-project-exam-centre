package driver;

public class Main {

    public static void main(String[] args) {
        StateTracker currentState = new StateTracker();
        while (currentState.getRunStatus()) {
            try {
                // ...
            }
            catch (Exception e) {
                // ...
            }
        }
    }
}
