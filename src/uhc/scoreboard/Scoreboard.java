package uhc.scoreboard;

import cn.nukkit.utils.TextFormat;
import gt.creeperface.nukkit.scoreboardapi.ScoreboardAPI;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.SimpleScoreboard;
import uhc.game.Game;
import uhc.game.utils.GameState;
import uhc.player.GamePlayer;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final Game game;
    private final SimpleScoreboard scoreboard;

    public Scoreboard(Game game) {
        this.game = game;
        scoreboard = ScoreboardAPI.builder().build();
    }

    private List<String> getLines() {
        Game game = this.game;
        List<String> lines = new ArrayList<>();
        lines.add(TextFormat.GRAY + "---------------------");

        switch (game.getState()) {
            case GameState.WAITING:
                lines.add(TextFormat.AQUA + "Players: " + TextFormat.WHITE + game.getServer().getOnlinePlayers().size());
                lines.add(TextFormat.AQUA + "Waiting more players ");
                break;
            case GameState.SETUP:
                lines.add(TextFormat.AQUA + "Scattering players: " + TextFormat.WHITE + "0");
                break;
        }
        lines.add("  ");
        lines.add(TextFormat.GRAY + "--------------------- ");
        return lines;
    }

    public void updateScoreboard(GamePlayer player) {
        scoreboard.clearCache();
        scoreboard.resetAllScores();
        scoreboard.setDisplayName(TextFormat.AQUA + "Trander UHC");
        List<String> messages = getLines();

        for (int line = 0; line < messages.size(); line++)
            scoreboard.setScore(line, messages.get(line), line);
        scoreboard.update();
        scoreboard.addPlayer(player);
    }
}
