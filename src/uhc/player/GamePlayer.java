package uhc.player;

import cn.nukkit.Player;
import cn.nukkit.network.SourceInterface;
import cn.nukkit.utils.LoginChainData;
import uhc.UHCLoader;
import uhc.game.Game;
import uhc.game.utils.GameState;
import uhc.player.utils.PlayerUtils;

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
}
