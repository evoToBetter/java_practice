package evotobetter.serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserExternalizable implements Externalizable {

    private static final long serialVersionUID = -4657038821475181629L;

    private static final Logger logger = LoggerFactory.getLogger(UserExternalizable.class);

    private String name;

    private int age;

    private static String room;

    public UserExternalizable() {
    }

    public UserExternalizable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static String getRoom() {
        return room;
    }

    public static void setRoom(String room) {
        UserExternalizable.room = room;
    }

    @Override
    public String toString() {
        return "UserSource{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        StringBuffer reverse = new StringBuffer(this.name).reverse();
        logger.info("revers name for serialize: {}", reverse);
        out.writeObject(reverse);
        out.writeInt(this.age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        logger.info("name after reserialize: {}", this.name);
        this.age = in.readInt();
    }
}
