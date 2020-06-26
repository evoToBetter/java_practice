package evotobetter.clone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DogCloneable implements Cloneable, Serializable {
    public final List<String> names=new ArrayList<>();
    public List<String> nicknames =new ArrayList<>();
    public int age;
    public int weight;

    public DogCloneable clone(){
        try{
            return (DogCloneable)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new Error("Is too");
        }
    }


}
