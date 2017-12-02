import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        KeyPairGeneration kpg = new KeyPairGeneration();
        SignatureGeneration sg = new SignatureGeneration();
        SignatureVerification sv = new SignatureVerification();

        String Message = "Hello";
        int S = 128;

        Scanner inL = new Scanner(System.in);
        System.out.printf("Input parametr l (length of pseudo random sequence):\n"); //128
        int l = Integer.parseInt(inL.nextLine());

        Scanner inK = new Scanner(System.in);
        System.out.printf("Input parametr k (S(mod k) = 0):\n"); //16
        int k = Integer.parseInt(inK.nextLine());

        int t = (int) Math.pow(2, (S / k)); //256
        long start = System.currentTimeMillis(); // STAR TIMER
        kpg.generatePairKey(t, l);
        System.out.println("X: " + KeyPairGeneration.X);
        System.out.println("Y: " + KeyPairGeneration.Y);
        System.out.println("SIGNATURE: " + sg.signatureCreation(k, t, l, Message));
        System.out.println("Y`: " + sv.hashSignature(k, l));
        long finish = System.currentTimeMillis(); // FINISH TIMER
        long timeConsumedMillis = finish - start;
        //Statistic
        System.out.println("\n" + "Statictic:");
        System.out.println("Length of Private key: " + KeyPairGeneration.X.length() + " bits");
        System.out.println("Length of Public key: " + KeyPairGeneration.Y.length() + " bits");
        System.out.println("Length of Signature: " + SignatureGeneration.SIGNATURE.length() + " bits");
        System.out.println("Time: " + timeConsumedMillis + "ms" + "\n");
        //Statistic
        if(sv.verification(Message, k, t, S, l))
            System.out.println("Signature is valid");
        else
            System.out.println("Signature is NOT valid");
        Scanner inExit = new Scanner(System.in);
        System.out.printf("Exit or not? (0 - Exit, 1 - Start):\n");
        int exitOrNot = Integer.parseInt(inExit.nextLine());
        if(exitOrNot == 1){
            Reboot();
            main(args);
        }
    }

    public static void Reboot(){ // Reboot global parametrs
        KeyPairGeneration.X = "";
        KeyPairGeneration.Y = "";
        SignatureGeneration.SIGNATURE = "";
    }
}
