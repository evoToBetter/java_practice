package evotobetter.clone;

import evotobetter.copybean.DataUtil;
import evotobetter.copybean.NormalUser;
import evotobetter.copybean.User;
import evotobetter.copybean.UserConverter;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.DebuggingClassWriter;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BeanCopyUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(BeanCopyUtilTest.class);

    @Test
    public void testCopyBeanByBeanUtils() throws InvocationTargetException, IllegalAccessException {
        User user = DataUtil.createData(1);
        logger.info("before copy: {}", user);
        User user1 = new User();
        BeanUtils.copyProperties(user1, user);
        logger.info("after copy: {}", user1);
        Assert.assertNotEquals(user1, user);
    }

    @Test
    public void testCopyBeanByBeanUtilsForDifferentClass() throws InvocationTargetException, IllegalAccessException {

        User user = DataUtil.createData(1);
        logger.info("Before copy: {}", user);
        NormalUser user1 = new NormalUser();
        BeanUtils.copyProperties(user1, user);
        logger.info("After copy: {}", user1);
    }

    @Test
    public void testBeanUtilsBenchmark() throws InvocationTargetException, IllegalAccessException {
        int userSize=10000;
        List<User> userList = DataUtil.createDataList(userSize);
        List<NormalUser> userList1= new ArrayList<>();
        for(int i=0; i<userSize; i++){
            userList1.add(new NormalUser());
        }
        long start=System.currentTimeMillis();
        for(int i=0; i<userSize; i++){
            BeanUtils.copyProperties(userList.get(i), userList1.get(i));
        }
        long end=System.currentTimeMillis();
        logger.info("cost time for copy {} user: {}ms",userSize,end-start);
    }


    @Test
    public void testCopyBeanByBeanCopier() {
        User user = DataUtil.createData(1);
        logger.info("Before copy: {}", user);
        BeanCopier beanCopier = BeanCopier.create(User.class, User.class, false);
        User user1 = new User();
        beanCopier.copy(user, user1, null);
        logger.info("After copy: {}", user1);
        Assert.assertNotEquals(user1, user);
    }

    @Test
    public void testCopyWithoutConverter() {
        User user = DataUtil.createData(1);
        logger.info("Before copy {}", user);
        BeanCopier beanCopier = BeanCopier.create(User.class, NormalUser.class, false);
        NormalUser user1 = new NormalUser();
        beanCopier.copy(user, user1, null);
        logger.info("After copy {}", user1);

    }

    @Test
    public void testCopyWithConverter() {
        String generatedCodePath = "target/generated-sources";
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, generatedCodePath);
        User user = DataUtil.createData(1);
        logger.info("Before copy: {}", user);
        BeanCopier beanCopier = BeanCopier.create(User.class, NormalUser.class, true);
        NormalUser user1 = new NormalUser();
        beanCopier.copy(user, user1, new UserConverter());
        logger.info("After copy: {}", user1);
    }

    @Test
    public void testBeanCopierBenchmark(){
        int userSize=10000;
        List<User> users=DataUtil.createDataList(userSize);
        List<NormalUser> users1=new ArrayList<>();
        for(int i=0; i<userSize; i++){
            users1.add(new NormalUser());
        }
        BeanCopier beanCopier=BeanCopier.create(User.class,NormalUser.class,true);
        UserConverter userConverter=new UserConverter();
        long start=System.currentTimeMillis();
        for(int i=0; i<userSize; i++){
            beanCopier.copy(users.get(i),users1.get(i),userConverter);
        }
        long end=System.currentTimeMillis();
        logger.info("cost time for copy {} user: {}ms",userSize,end-start);
    }
}
