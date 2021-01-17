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

        initListener();
        initGame();
        initTask();
    }

    private void initListener() {
        new UHCListener(this);
    }

    private void initGame() {
        this.game = new Game(this);
    }

    private void initTask() {
        getServer().getScheduler().scheduleRepeatingTask(new GameTask(this), 1);
    }

    public static UHCLoader getInstance() {
        return instance;
    }

    public Game getGame() {
        return game;
    }
}
