package uhc.sessions.types;

public class PlayerSession {

    private final String name;

    private int eliminations = 0;
    private Object team = null;

    private boolean spectator = false;
    private boolean host = false;

    public PlayerSession(String name, boolean spectator) {
        this.name = name;
        this.spectator = spectator;
    }

    public PlayerSession(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getEliminations() {
        return eliminations;
    }

    public Object getTeam() {
        return team;
    }

    public boolean isSpectator() {
        return spectator;
    }

    public boolean isHost() {
        return host;
    }

    public void setEliminations(int eliminations) {
        this.eliminations = eliminations;
    }

    public void addElimination() {
        this.eliminations++;
    }

    public void setTeam(Object team) {
        this.team = team;
    }

    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    public void setHost(boolean host) {
        this.host = host;
    }
}
