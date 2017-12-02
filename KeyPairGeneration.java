import Config.*;
public class KeyPairGeneration {
    PRG prg = new PRG();
    MD5Binary md5B = new MD5Binary();

    public static String X = "";
    public static String Y = "";



    public void generatePairKey(Integer t) {
        String Xi = "";
        String Yi = "";

        for(int i = 1; i <= t; i++){
            Xi = prg.Random128();
            X += Xi;
            Yi = md5B.md5Custom(Xi);
            Y += Yi;
        }
    }

}
