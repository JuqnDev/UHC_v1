package uhc;

import cn.nukkit.plugin.PluginBase;
import uhc.game.Game;
import uhc.task.GameTask;

public class UHCLoader extends PluginBase {

    private static UHCLoader instance;

    private Game game;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");

        /* Register listener */
        new UHCListener(this);

        /* Register game class */
        this.game = new Game(this);

        /* Task */
        getServer().getScheduler().scheduleRepeatingTask(new GameTask(this), 1);
    }

    public static UHCLoader getInstance() {
        return instance;
    }

    public Game getGame() {
        return game;
    }
}
