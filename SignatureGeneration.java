import Config.*;

public class SignatureGeneration {
    Binarylog bl = new Binarylog();
    MD5Binary md5B = new MD5Binary();

    public String hashMessage(String Message){ //Хэширование сообщения
        Message = md5B.md5Custom(Message);
        return Message;
    }

    public Integer [] separateMessage(String Message, Integer k, Integer t){ // Разделение сообщение на блоки
        Message = hashMessage(Message);
        Integer [] blocksOfMessage = new Integer[k];
        int j = 0;  //счетчик для индексов массива
        for(int i = 0; i < Message.length(); i = i + (int)Math.ceil(bl.binlog((double) t))) { // проход по массиву через каждые w символа для нахождения новой подстроки
            String S1 = Message.substring(i, i + (int)Math.ceil(bl.binlog((double) t))); // нахождение подстроки с длиной в w символа
            blocksOfMessage[j++] = Integer.parseInt(S1, 2); // присваивание подстроки к элементу массива
        }
        return blocksOfMessage;
    }

    public String signatureCreation(Integer k, Integer [] blocksOfMessage, Integer [] sk){ // Создание подписи
        String SIGNATURE= "";
        for(int i = 0; i < k; i++){
            SIGNATURE += sk[blocksOfMessage[i]];
        }
        return SIGNATURE;
    }
}
