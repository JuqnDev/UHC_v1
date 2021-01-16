package uhc.game;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.TextFormat;
import uhc.game.utils.GameState;
import uhc.player.GamePlayer;
import uhc.sessions.types.PlayerSession;

public class GameListener implements Listener {

    private final Game game;

    public GameListener(Game game) {
        this.game = game;

        game.getServer().getPluginManager().registerEvents(this, game.getPlugin());
    }

    private Game getGame() {
        return game;
    }

    @EventHandler
    public void handleBreak(BlockBreakEvent event) {
        Object player = event.getPlayer();

        if (player instanceof GamePlayer) {
            if (!((GamePlayer) player).hasPermission("build.permission"))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void handlePlace(BlockPlaceEvent event) {
        Object player = event.getPlayer();

        if (player instanceof GamePlayer) {
            if (!((GamePlayer) player).hasPermission("build.permission"))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void handleDamage(EntityDamageEvent event) {
        if (getGame().getState() != GameState.RUNNING) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void handleChat(PlayerChatEvent event) {
        Object player = event.getPlayer();

        if (player instanceof GamePlayer) {
            if (event.getMessage().equals("%addkill")) {
                ((PlayerSession) ((GamePlayer) player).getData()).addElimination();
            }
        }
    }

    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Object player = event.getPlayer();

        if (player instanceof GamePlayer && ((GamePlayer) player).isSpawned()) {
            ((GamePlayer) player).join();
        }
    }
}
