package uhc.game;

import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Level;
import uhc.entity.Disconnect;
import uhc.game.utils.GameState;
import uhc.UHCLoader;
import uhc.scoreboard.Scoreboard;
import uhc.sessions.SessionManager;

public class Game {

    private final UHCLoader plugin;
    private final Server server;

    private Scoreboard scoreboard;
    private SessionManager sessions;

    private Object level = null;

    private int state = GameState.WAITING;

    public Game(UHCLoader plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();

        initManagers();
        initListener();
        initEntities();
    }

    private void initManagers() {
        this.scoreboard = new Scoreboard(this);
        this.sessions = new SessionManager();
    }

    private void initListener() {
        new GameListener(this);
    }

    private void initEntities() {
        Entity.registerEntity("Disconnect", Disconnect.class, true);
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

    public SessionManager getSessions() {
        return sessions;
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
