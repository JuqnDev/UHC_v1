package uhc.game.utils;

public class GameTimer {

    public static int COUNTDOWN = 30;
    public static int RUNNING = 0;
    public static int RESTARTING = 20;

    public static void restart() {
        COUNTDOWN = 30;
        RUNNING = 0;
        RESTARTING = 20;
    }

    public static String getTimeRunning() {
        int input = RUNNING;
        int hours = (input % 86400) / 3600;
        int minutes = ((input % 86400) % 3600 ) / 60;
        int seconds = ((input % 86400) % 3600 ) % 60;
        return ((hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds));
    }
}