package uhc.scoreboard;

import cn.nukkit.utils.TextFormat;
import gt.creeperface.nukkit.scoreboardapi.ScoreboardAPI;
import gt.creeperface.nukkit.scoreboardapi.scoreboard.SimpleScoreboard;
import uhc.game.Game;
import uhc.game.utils.GameState;
import uhc.game.utils.GameTimer;
import uhc.player.GamePlayer;
import uhc.sessions.types.PlayerSession;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final Game game;
    private final SimpleScoreboard scoreboard;

    public Scoreboard(Game game) {
        this.game = game;
        scoreboard = ScoreboardAPI.builder().build();
    }

    private List<String> getLines(GamePlayer player) {
        Game game = this.game;
        List<String> lines = new ArrayList<>();
        lines.add(TextFormat.GRAY + "---------------------");
        PlayerSession data = (PlayerSession) player.getData();

        switch (game.getState()) {
            case GameState.WAITING:
                lines.add(TextFormat.RED + "Players: " + TextFormat.WHITE + game.getServer().getOnlinePlayers().size());
                lines.add(TextFormat.RED + "Waiting more players ");
                break;
            case GameState.SETUP:
                lines.add(TextFormat.RED + "Scattering players: " + TextFormat.WHITE + "0");
                break;

            case GameState.COUNTDOWN:
                lines.add(TextFormat.RED + "Start in: " + GameTimer.COUNTDOWN);
                break;

            case GameState.RUNNING:
                lines.add(TextFormat.RED + "Game Time: " + TextFormat.WHITE + GameTimer.getTimeRunning());
                lines.add(TextFormat.RED + "Remaining: "  + TextFormat.WHITE + game.getServer().getOnlinePlayers().size());
                lines.add(TextFormat.RED + "Eliminations: " + TextFormat.WHITE + data.getEliminations());
                lines.add(TextFormat.RED + "Border: " + TextFormat.WHITE + "1000");
                break;
        }
        lines.add("  ");
        lines.add(TextFormat.GRAY + "@YourServer");
        lines.add(TextFormat.GRAY + "--------------------- ");
        return lines;
    }

    public void updateScoreboard(GamePlayer player) {
        scoreboard.clearCache();
        scoreboard.resetAllScores();
        scoreboard.setDisplayName(TextFormat.BOLD + "" + TextFormat.RED + "YOUR SERVER");
        List<String> messages = getLines(player);

        for (int line = 0; line < messages.size(); line++)
            scoreboard.setScore(line, messages.get(line), line);
        scoreboard.update();
        scoreboard.addPlayer(player);
    }
}
