package evotobetter.serializable;

import java.io.Serializable;

public class UserBase implements Serializable {
    private static final long serialVersionUID = 8442911884250023786L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBase(String name) {
        this.name = name;
    }

    public UserBase() {
    }


    @Override
    public String toString() {
        return "UserBase{" +
                "name='" + name + '\'' +
                '}';
    }
}
