package evotobetter.copybean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NormalUser {
    private int id;
    private String name;
    private int age;
    private String gmtBoth;
    private String balance;

    public NormalUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGmtBoth() {
        return gmtBoth;
    }

    public void setGmtBoth(String gmtBoth) {
        this.gmtBoth = gmtBoth;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "NormalUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gmtBoth='" + gmtBoth + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
