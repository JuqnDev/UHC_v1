package uhc.player;

import cn.nukkit.Player;
import cn.nukkit.level.Level;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.utils.LoginChainData;
import uhc.UHCLoader;
import uhc.game.Game;
import uhc.game.utils.GameState;
import uhc.player.utils.PlayerUtils;
import uhc.sessions.types.PlayerSession;

import java.net.InetSocketAddress;

public class GamePlayer extends Player {

    public GamePlayer(SourceInterface interfaz, Long clientID, InetSocketAddress socketAddress) {
        super(interfaz, clientID, socketAddress);
    }

    private Game getGame() {
        return UHCLoader.getInstance().getGame();
    }

    public boolean isSpawned() {
        return spawned;
    }

    public Object getData() {
        return getGame().getSessions().getPlayer(this);
    }

    @Override
    protected void processMovement(int tickDiff) {
        if (getGame().getState() != GameState.RUNNING) {
            PlayerUtils.setFood(this, 20);
        }

        super.processMovement(tickDiff);
    }

    public String getDeviceOS() {
        LoginChainData data = getLoginChainData();
        int deviceType = data.getDeviceOS();

        switch (deviceType) {
            case 1:
                return "Android";
            case 2:
                return "iOS";
            case 3:
                return "MacOS";
            case 4:
                return "FireOS";
            case 5:
                return "Gear-VR";
            case 6:
                return "Hololens-VR";
            case 7:
                return "Win 10";
            case 8:
                return "Windows 32";
            case 9:
                return "Dedicated";
            case 10:
                return "TVOS";
            case 11:
                return "PS4";
            case 12:
                return "Nintendo Switch";
            case 13:
                return "Xbox";
            case 20:
                return "Linux";
            default:
                return "Unknown";
        }
    }

    public void join() {
        PlayerSession data = (PlayerSession) getData();

        if (getGame().getSessions().getPlayer(this) == null)
            getGame().getSessions().addPlayer(this);

        switch (getGame().getState()) {
            case GameState.WAITING:
                reset();
                teleport(getServer().getDefaultLevel().getSpawnLocation());
                break;

            case GameState.SETUP:
                if (!data.isScattering()) {
                    reset();
                    teleport(getServer().getDefaultLevel().getSpawnLocation());
                }
                break;

            case GameState.COUNTDOWN:
            case GameState.RUNNING:
            case GameState.RESTARTING:
                if (!data.isScattering()) {
                    if (!data.isSpectator() || (data.isSpectator() && getGamemode() == 0)) {
                        data.setSpectator(true);
                        reset();
                        Level level = (Level) getGame().getLevel();
                        teleport(level.getSpawnLocation().add(0, 1, 0));
                    }
                }
                break;
        }
    }

    public void reset() {
        setHealth(20);
        setGamemode(0);
        setExperience(0, 0);
        getInventory().clearAll();
        removeAllEffects();
    }
}
