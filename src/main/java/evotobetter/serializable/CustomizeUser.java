package evotobetter.serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CustomizeUser implements Serializable {
    private static final long serialVersionUID = -3405037012102644406L;

    private static final Logger logger = LoggerFactory.getLogger(CustomizeUser.class);

    private String name;
    private String password;

    public CustomizeUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            ObjectOutputStream.PutField putFields = oos.putFields();
            String pass = "encryption";
            logger.info("Before serialize, we get password: {}", pass);
            putFields.put("password", pass);
            putFields.put("name", name);
            oos.writeFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream ois) {
        try {
            ObjectInputStream.GetField getField = ois.readFields();
            Object pass = getField.get("password", "");
            logger.info("After serialize, we get password: {}", pass);
            Object name = getField.get("name", "");
            logger.info("After serialize, we get name: {}", name);
            this.name = (String) name;
            this.password = "pass";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CustomizeUser{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
