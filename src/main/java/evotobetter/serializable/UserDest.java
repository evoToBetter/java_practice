package evotobetter.serializable;

import java.io.Serializable;

public class UserDest implements Serializable {
    private static final long serialVersionUID = -5601835544663900012L;

    private String name;

    private int age;

    public UserDest(String name, int age) {
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

    @Override
    public String toString() {
        return "UserDest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
