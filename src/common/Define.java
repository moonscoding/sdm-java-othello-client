package common;

public class Define {

    // @Define
    public static final boolean isDebug = java.
            lang.
            management.
            ManagementFactory.
            getRuntimeMXBean().
            getInputArguments().
            toString().
            indexOf("-agentlib:jdwp") > 0;
    public static final String HOST = "localhost";
    public static final short PORT_DEBUG = 2000;
    public static final short PORT_PROP = 3000;
    public static final int BUFFER_SIZE = (int) 1 << 13; // 8192 ( byte )
    public static final String[] STANDBY_TABLE_COLUMNS = new String[] { "wins", "title", "user", "status", "count"};
    public static final boolean ROOM_STATUS_A = false; // 대기
    public static final boolean ROOM_STATUS_B = true;  // 진행
    public static final String ROOM_STRING_A = "대기";
    public static final String ROOM_STRING_B = "진행";
    public static final boolean GUARDIAN = true;
    public static final boolean CHALLENGER = false;

    // @URL
    public static final String URL_REG_URSE = "a";
    public static final String URL_REQ_CREATE = "b";
    public static final String URL_REQ_ENTRY = "c";
    public static final String URL_RES_ENTRY = "d";
    public static final String URL_RES_ENTRY_ACCEPT = "e";
    public static final String URL_RES_ENTRY_REJECT = "f";
    public static final String URL_REQ_READY = "g";
    public static final String URL_RES_READY_ALL = "h";
    public static final String URL_RES_READY_ACCEPT = "i";
    public static final String URL_RES_READY_REJECT = "j";
    public static final String URL_RES_START = "k";
    public static final String URL_REQ_LEAVE = "l";
    public static final String URL_RES_LEAVE_GUARDIAN = "m";
    public static final String URL_RES_LEAVE_CHALLENGER = "n";
    public static final String URL_REG_TURN = "o"; // == URL_RES_TURN
    public static final String URL_RES_TURN = "o"; // == URL_REQ_TURN
    public static final String URL_REG_RESULT = "p";
    public static final String URL_REG_CHAT = "q"; // == URL_RES_CHAT
    public static final String URL_RES_CHAT = "q"; // == URL_REQ_CHAT
    public static final String URL_REQ_UPDATE = "r"; // == URL_RES_UPDATE
    public static final String URL_RES_UPDATE = "r"; // == URL_REQ_UPDATE

    // @SIZE
    public static final int SIZE_METHOD = 1; // 1글자 ( 2byte * 1 )
    public static final int SIZE_USER_NAME = 10; // 10글자 ( 2byte * 10 )
    public static final int SIZE_CHAT = 100; // 100글자 ( 2byte * 100 )
    public static final int SIZE_ROOM_TITLE = 10; // 10글자 ( 2byte * 10 )
    public static final int SIZE_ROOM_ID = 36; // 36 ( 2byte * 36 )
    public static final int SIZE_ROOM_WINS = 2; // 2글자 ( 2byte * 2 ) => int
    public static final int SIZE_ROOM_STATUS = 1; // 1글자 ( 2byte * 1 )
    public static final int SIZE_XY = 1; // 1글자 ( 2byte * 1 ) => 앞에 1byte X // 뒤에 1byte Y
    public static final int SIZE_ROOM = SIZE_ROOM_ID + SIZE_ROOM_TITLE + SIZE_ROOM_WINS + SIZE_USER_NAME + SIZE_ROOM_STATUS;
    public static final int SIZE_ROOMS = SIZE_ROOM * 50;

    // @PROTOCOL
    public static final int[] URL_REG_URSE_PROTOCOL
            = new int[] {SIZE_METHOD, SIZE_USER_NAME}; // |header(2)|username(20)|
    public static final int[] URL_REQ_CREATE_PROTOCOL
            = new int[] {SIZE_METHOD, SIZE_ROOM_TITLE}; // |header(2)|roomtitle(20)|
    public static final int[] URL_REQ_ENTRY_PROTOCOL
            = new int[] {SIZE_METHOD, SIZE_ROOM_ID}; // |header(2)|roomid(72)|
    public static final int[] URL_RES_ENTRY_PROTOCOL
            = new int[] {SIZE_METHOD, SIZE_USER_NAME}; // |header(2)|username(20)|
    public static final int[] URL_REQ_READY_PROTOCOL
            = null; // |header|
    public static final int[] URL_REG_START_PROTOCOL
            = null; // |header|
    public static final int[] URL_REQ_LEAVE_PROTOCOL
            = null; // |header|
    public static final int[] URL_REG_TURN_PROTOCOL
            = new int[] {SIZE_METHOD, SIZE_XY}; // |header(2)|xy(2)|
    public static final int[] URL_REG_RESULT_PROTOCOL
            = null; // |header|
    public static final int[] URL_REG_CHAT_PROTOCOL
            = new int[] {SIZE_METHOD, SIZE_CHAT}; // |header(2)|chat(200)|
    public static final int[] URL_RES_UPDATE_PROTOCOL
            = new int[] {SIZE_METHOD, SIZE_ROOMS}; // |header(2)|rooms(4300)|
    public static final int[] URL_RES_SINGLE_ROOM_PROTOCOL
            = new int[] {SIZE_ROOM_ID, SIZE_ROOM_TITLE, SIZE_ROOM_WINS, SIZE_USER_NAME, SIZE_ROOM_STATUS};

    // @Test
    public static final byte PAGE_INDEX = 0;
    public static final byte PAGE_STANDBY = 1;
    public static final byte PAGE_GAME = 2;

    // @Error
    public static String ERROR_A = "a"; // 서버통신두절



}
