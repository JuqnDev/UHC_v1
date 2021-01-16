package uhc;

import cn.nukkit.level.GameRule;
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

        /* Set gamerules in level */
        getServer().getDefaultLevel().getGameRules().setGameRule(GameRule.SHOW_COORDINATES, true);
        getServer().getDefaultLevel().getGameRules().setGameRule(GameRule.NATURAL_REGENERATION, false);

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
