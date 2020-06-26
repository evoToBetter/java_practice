package evotobetter.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectCloner {

    private ObjectCloner(){

    }

    public static Object deepCopy(Object oldObject) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos=null;
        ObjectInputStream ois=null;
        try{
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(bos);
            oos.writeObject(oldObject);
            oos.flush();
            ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
            ois=new ObjectInputStream(bis);
            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(oos!=null){
                oos.close();
            }
            if(ois!=null){
                ois.close();
            }
        }
    }
}
