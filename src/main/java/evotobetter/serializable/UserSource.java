package evotobetter.serializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserSource implements Serializable {
    private static final long serialVersionUID = 1251837160108199960L;

    private String name;

    private int age;

    private static String room;

    public UserSource(String name, int age) {
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
        UserSource.room = room;
    }

    @Override
    public String toString() {
        return "UserSource{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
