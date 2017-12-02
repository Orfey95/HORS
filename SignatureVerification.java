import Config.*;

public class SignatureVerification {
    Binarylog bl = new Binarylog();
    MD5Binary md5B = new MD5Binary();
    MD5HEX md5H = new MD5HEX();
    SignatureGeneration sg = new SignatureGeneration();

    public String hashMessage(String Message){ // Message hashing
        Message = md5B.md5Custom(Message);
        return Message;
    }

    public Integer [] separateMessage(String Message, Integer k, Integer t){ // Separation of the message into blocks
        Message = hashMessage(Message);
        Integer [] blocksOfMessage = new Integer[k];
        int j = 0;
        for(int i = 0; i < Message.length(); i = i + (int)Math.ceil(bl.binlog((double) t))) {
            String S1 = Message.substring(i, i + (int)Math.ceil(bl.binlog((double) t)));
            blocksOfMessage[j++] = Integer.parseInt(S1, 2);
        }
        return blocksOfMessage;
    }

    public String hashSignature(Integer k, Integer l){ //Hashing the signature
        String sig = "";
        for(int i = 0; i < k; i++){
            sig += md5H.md5Custom(SignatureGeneration.SIGNATURE.substring(i * l, i * l + l));
        }
        return sig;
    }

    public boolean verification(String Message, Integer k, Integer t, Integer S, Integer l){
        S = S / 4;
        String sig = hashSignature(k, l);
        Integer [] blocksOfMessage = separateMessage(Message, k, t);
        int j = 0;
        for(int i = 0; i < k; i++){
            if(sig.substring(i * S, i * S + S).compareTo(KeyPairGeneration.Y.substring(blocksOfMessage[i] * S, blocksOfMessage[i] * S + S)) == 0){
                j++;
            }
        }

        if(j == k)
            return true;
        else
            return false;
    }
}
