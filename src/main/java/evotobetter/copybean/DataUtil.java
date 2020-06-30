package evotobetter.copybean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static User createData(int index){
        return new User(index,"vUser"+index,index*10, LocalDateTime.now(),new BigDecimal(index*10));
    }

    public static List<User> createDataList(int size){
        List<User> users=new ArrayList<>();
        for(int i=0; i<size; i++){
            users.add(createData(i));
        }
        return users;
    }
}
