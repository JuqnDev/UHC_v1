package uhc.player.disconnect;

import java.util.HashMap;

public class DisconnectManager {
    
    public HashMap<String, DisconnectMob> disconnects = new HashMap<>();
    
    public HashMap<String, DisconnectMob> getDisconnects() {
        return disconnects;
    }
}