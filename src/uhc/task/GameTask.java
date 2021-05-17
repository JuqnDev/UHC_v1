package uhc.task;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import uhc.UHCLoader;
import uhc.game.Game;
import uhc.player.GamePlayer;
import uhc.task.utils.TaskUtils;

public class GameTask extends Task {

    private final UHCLoader plugin;
    private int currentTick = 0;

    public GameTask(UHCLoader plugin) {
        this.plugin = plugin;
    }

    private UHCLoader getPlugin() {
        return this.plugin;
    }

    @Override
    public void onRun(int i) {
        int oneSecond = TaskUtils.secondsToTick(1);

        /*if (currentTick % oneSecond == 0 || currentTick == 0) {
            updatePlayers();
        }*/
        currentTick++;
    }

    private void updatePlayers() {
        Game game = getPlugin().getGame();

        for (Player player:
             getPlugin().getServer().getOnlinePlayers().values()) {
            if (player instanceof GamePlayer && ((GamePlayer) player).isSpawned()) {
                game.getScoreboard().updateScoreboard((GamePlayer) player);
                player.setNameTag(TextFormat.WHITE + player.getName() + TextFormat.GREEN  + " [" + ((GamePlayer) player).getDeviceOS() + "]\n" + TextFormat.WHITE + Math.round(player.getHealth() * 100.0) / 100.0 + TextFormat.RED + " ❤");
            }
        }
    }
}
