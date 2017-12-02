import Config.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SignatureGeneration sg = new SignatureGeneration();
        Binarylog bl = new Binarylog();

        String Message = "Hello";
        int S = 128;
        int t = 256;
        int k = 16;
        System.out.println(Arrays.asList(sg.separateMessage(Message, k, t)));
    }
}
