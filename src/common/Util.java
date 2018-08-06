package common;

public class Util {

    /* Log */
    public static void log (String log) {
        if(!Config.dist) System.out.println(log);
    }
}
