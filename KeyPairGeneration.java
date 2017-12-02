import Config.*;

public class KeyPairGeneration {
    PRG prg = new PRG();
    MD5HEX md5H = new MD5HEX();

    public static String X = "";
    public static String Y = "";

    public void generatePairKey(Integer t, Integer l) {
        String Xi = "";
        String Yi = "";

        for(int i = 1; i <= t; i++){
            Xi = prg.Random128(l);
            X += Xi;
            Yi = md5H.md5Custom(Xi);
            Y += Yi;
        }
    }
}
