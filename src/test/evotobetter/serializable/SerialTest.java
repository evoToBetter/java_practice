package evotobetter.serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.function.BiConsumer;

public class SerialTest {

    private static final Logger logger = LoggerFactory.getLogger(SerialTest.class);
    private static final String RECORD_FILE_PATH = "target/record.txt";

    @Test
    public void testSerializeWithDifferentID() throws IOException {
        UserSource userSource = new UserSource("source", 10);
        logger.info("Before serialize, we get: {}", userSource);
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(userSource);
                oos.flush();
                UserDest userDest = (UserDest) ois.readObject();
                logger.info("After serialize, we get: {}", userDest);
            } catch (Exception e) {
                Assert.assertEquals(e.getClass(), ClassCastException.class);
            }

        });
    }

    @Test
    public void testSerializeMoreThanOneObject() throws IOException {
        UserSource user1 = new UserSource("source1", 10);
        UserSource user2 = new UserSource("source2", 20);
        UserSource user3 = new UserSource("source3", 30);
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(user1);
                oos.writeObject(user2);
                oos.writeObject(user1);
                oos.writeObject(user3);
                oos.flush();
                UserSource record1 = (UserSource) ois.readObject();
                UserSource record2 = (UserSource) ois.readObject();
                UserSource record3 = (UserSource) ois.readObject();
                UserSource record4 = (UserSource) ois.readObject();
                Assert.assertEquals(record1, record3);
                Assert.assertNotEquals(user1, record3);
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    @Test
    public void testModifyObject() throws IOException {
        UserSource user1 = new UserSource("s1", 10);
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(user1);
                user1.setAge(20);
                oos.flush();
                UserSource record1 = (UserSource) ois.readObject();
                Assert.assertEquals(record1.getName(), "s1");
                Assert.assertEquals(record1.getAge(), 10);
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    @Test
    public void testModifyStaticField() throws IOException {
        UserSource u1 = new UserSource("u1", 10);
        u1.setRoom("room201");
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(u1);
                oos.flush();
                u1.setRoom("room202");
                UserSource r1 = (UserSource) ois.readObject();
                logger.info("After serialize, we get: {}, r1.room: {}", r1, r1.getRoom());
                Assert.assertEquals(r1.getRoom(), "room202");
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    @Test
    public void testWriteReplace() throws IOException {
        UserSourceWriteReplace user1 = new UserSourceWriteReplace("u1", 10);
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(user1);
                oos.flush();
                List list = (List) ois.readObject();
                Assert.assertEquals(list.get(0), "u1");
                logger.info("After serialize, we get: {}", list);
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    @Test
    public void testReadResolve() throws IOException {
        UserSourceReadResolve u1 = new UserSourceReadResolve("u1", 100);
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(u1);
                oos.flush();
                UserSourceReadResolve r1 = (UserSourceReadResolve) ois.readObject();
                logger.info("After serialize, we get: {}", r1);
                Assert.assertNotEquals(r1.getName(), u1.getName());
                Assert.assertNotEquals(r1.getAge(), u1.getAge());
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    @Test
    public void testExternalizable() throws IOException {
        UserExternalizable u1 = new UserExternalizable("u1", 10);
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(u1);
                oos.flush();
                UserExternalizable r1 = (UserExternalizable) ois.readObject();
                logger.info("After serialize, we get: {}", r1);
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    @Test
    public void testSerializeParentClass() throws IOException {
        TempUser u1 = new TempUser("u1", 10);
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(u1);
                oos.flush();
                TempUser r1 = (TempUser) ois.readObject();
                logger.info("After serialize, we get: {}", r1);
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    @Test
    public void testCustomizeSerialize() throws IOException {
        CustomizeUser u1 = new CustomizeUser("u1", "pp");
        serializeObject((oos, ois) -> {
            try {
                oos.writeObject(u1);
                oos.flush();
                CustomizeUser r1 = (CustomizeUser) ois.readObject();
                logger.info("After serialize, we get: {}", r1);
                Assert.assertEquals(r1.getPassword(), "pass");
            } catch (IOException | ClassNotFoundException e) {
                logger.error("cant serialize object", e);
                Assert.assertNull(e);
            }
        });
    }

    private static void serializeObject(BiConsumer<ObjectOutputStream, ObjectInputStream> biConsumer) throws IOException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(RECORD_FILE_PATH));
            ois = new ObjectInputStream(new FileInputStream(RECORD_FILE_PATH));
            biConsumer.accept(oos, ois);
        } catch (Exception e) {
            logger.error("Can not serial object", e);
            throw e;
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
    }
}
