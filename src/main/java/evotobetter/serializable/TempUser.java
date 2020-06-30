package evotobetter.serializable;

import java.io.Serializable;

public class TempUser extends UserBase implements Serializable {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TempUser(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public String toString() {
        return "TempUser{" +
                "age=" + age +
                "," + super.toString() +
                '}';
    }
}
