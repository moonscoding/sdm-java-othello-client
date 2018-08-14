package sample;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Tester {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{ 64, 22 };
        String position = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(position);
        System.out.println(position.getBytes().length);
        System.out.println(position.getBytes()[0]);
        System.out.println(position.getBytes().toString());
    }
}
