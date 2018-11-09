package util;

import java.io.*;

public class Resource {
    public static BufferedReader stream (String fileName) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(new File("resources/" + fileName))));
    }
}
