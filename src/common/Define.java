package common;

public class Define {

    // @Define
    public static String HOST = "localhost";
    public static short PORT = 9999;
    public static int BUFFER_SIZE = (int) Math.pow(2, 10); // 1024
    public static String[] STANDBY_TABLE_COLUMNS = new String[] { "wins", "title", "user", "status", "count"};

    // @Protocol
    public static char RECEIVE_TYPE = 'a';
    public static boolean ROOM_STATUS_A = false; // 대기
    public static boolean ROOM_STATUS_B = true;  // 진행
    public static String ROOM_STRING_A = "대기";
    public static String ROOM_STRING_B = "진행";

    // @Test
    public static final byte PAGE_INDEX = 0;
    public static final byte PAGE_STANDBY = 1;
    public static final byte PAGE_GAME = 2;

    // @Error
    public static String ERROR_A = "a"; // 서버통신두절



}
