package common;

import java.util.LinkedList;
import java.util.List;

public class Common {

    /* cutHeader - packet method 추출 */
    public static String cutHeader(String request) {
        try {
            return request.substring(0, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* cutBody */
    public static List<String> cutBody(String request, int[] cutPoint) {
        try {
            List<String> body = new LinkedList<>();
            int pointer = 0;
            for (int i = 0; i < cutPoint.length; i++) {
                String item = request.substring(pointer, pointer + cutPoint[i]).trim();
                pointer += cutPoint[i];
                body.add(item);
            }
            return body;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* fullBlank */
    public static String fullBlank(String string, int size) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size - string.length(); i++) {
            sb.append(" ");
        }
        return sb.append(string).toString();
    }

}
