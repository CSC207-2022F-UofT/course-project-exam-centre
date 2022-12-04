package entities.factories;

import entities.StateTracker;

public class StateTrackerFactory {

    /**
     * Returns a new StateTracker object.
     */
    public StateTracker create() {
        return new StateTracker();
    }
}
