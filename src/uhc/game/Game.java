package uhc.game;

import cn.nukkit.Server;
import cn.nukkit.level.Level;
import uhc.game.utils.GameState;
import uhc.UHCLoader;
import uhc.scoreboard.Scoreboard;

public class Game {

    private final UHCLoader plugin;
    private final Server server;

    private final Scoreboard scoreboard;

    private Level level;

    private int state = GameState.WAITING;

    public Game(UHCLoader plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();

        /* Register main game listener */
        new GameListener(this);

        /* Create scoreboard */
        this.scoreboard = new Scoreboard(this);
    }

    public UHCLoader getPlugin() {
        return plugin;
    }

    public Server getServer()
    {
        return server;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Object getLevel() {
        return level;
    }

    public int getState() {
        return state;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setState(int state) {
        this.state = state;
    }
}
