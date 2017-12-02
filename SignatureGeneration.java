import Config.*;

public class SignatureGeneration {
    Binarylog bl = new Binarylog();
    MD5Binary md5B = new MD5Binary();

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

    public String signatureCreation(Integer k, Integer [] blocksOfMessage, Integer [] sk){ // Creating a signature
        String SIGNATURE= "";
        for(int i = 0; i < k; i++){
            SIGNATURE += sk[blocksOfMessage[i]];
        }
        return SIGNATURE;
    }
}
