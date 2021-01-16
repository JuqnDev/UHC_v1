package uhc;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCreationEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import uhc.player.GamePlayer;

public class UHCListener implements Listener {

    public UHCListener(UHCLoader plugin) {
        Server server = plugin.getServer();

        server.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void handleCreation(PlayerCreationEvent event) {
        event.setPlayerClass(GamePlayer.class);
    }

    @EventHandler
    public void handleLogin(PlayerJoinEvent event) {
        event.setJoinMessage("");
    }

    @EventHandler
    public void handleQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }
}
