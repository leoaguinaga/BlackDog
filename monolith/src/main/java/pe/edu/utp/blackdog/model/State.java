package pe.edu.utp.blackdog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum State {
    ON_HOLD("EN ESPERA"), ON_PROCESS("EN PROCESO"), FINISHED("FINALIZADO"), CANCELED("CANCELADO");

    private final String displayName;

    State(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    public static List<State> getStates() {
        return new ArrayList<>(Arrays.asList(State.values()));
    }
}
