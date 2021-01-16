package uhc.sessions;

import uhc.player.GamePlayer;
import uhc.sessions.types.PlayerSession;

import java.util.HashMap;

public class SessionManager {

    public HashMap<String, PlayerSession> players = new HashMap<>();

    public Object getPlayer(GamePlayer player) {
        return players.get(player.getName());
    }

    public Object getPlayer(String player) {
        return players.get(player);
    }

    public void addPlayer(GamePlayer player) {
        PlayerSession playerSession = new PlayerSession(player.getName());
        players.put(player.getName(), playerSession);
    }

    public void addPlayer(GamePlayer player, boolean spectator) {
        PlayerSession playerSession = new PlayerSession(player.getName(), spectator);
        players.put(player.getName(), playerSession);
    }

    public void resetPlayers() {
        for (PlayerSession player:
             players.values()) {
            player.setSpectator(false);
            player.setTeam(null);
            player.setEliminations(0);
        }
    }
}
