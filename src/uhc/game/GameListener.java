package uhc.game;

import cn.nukkit.entity.EntityLiving;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.entity.EntityRegainHealthEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.potion.Effect;
import cn.nukkit.utils.TextFormat;
import uhc.player.GamePlayer;

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
    public void handleRegainHealth(EntityRegainHealthEvent event) {
        Object entity = event.getEntity();

        if (!(entity instanceof EntityLiving))
            return;

        if (((EntityLiving) entity).hasEffect(Effect.REGENERATION))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void handleChat(PlayerChatEvent event) {
        Object player = event.getPlayer();

        if (player instanceof GamePlayer) {
            event.setFormat(TextFormat.GREEN + ((GamePlayer) player).getDeviceOS() + " " + TextFormat.GRAY + ((GamePlayer) player).getName() + ": " + TextFormat.WHITE + event.getMessage());
        }
    }
}
