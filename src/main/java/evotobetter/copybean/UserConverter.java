package evotobetter.copybean;

import net.sf.cglib.core.Converter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserConverter implements Converter {

    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Object convert(Object value, Class target, Object context) {
        if(value instanceof Integer){
            return ((Integer) value).intValue();
        }else if(value instanceof LocalDateTime){
            return dtf.format((LocalDateTime)value);
        }else if(value instanceof BigDecimal){
            return ((BigDecimal) value).toPlainString();
        }
        return value;
    }
}
