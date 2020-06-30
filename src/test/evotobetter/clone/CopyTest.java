package evotobetter.clone;

import evotobetter.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CopyTest {
    private static final Logger logger= LoggerFactory.getLogger(CopyTest.class);
    @Test
    public void testShallowCopy(){
        DogCloneable bowser=new DogCloneable();
        bowser.names.add("Fido");
        bowser.nicknames.add("Fido");
        DogCloneable bobBarker=bowser.clone();
        bowser.names.add("Bowser");
        bobBarker.names.add("Bob Barker");
        bowser.nicknames.add("Bowser");
        bobBarker.nicknames.add("Bob Barker");
        logger.info("names:");
        // bowser has same names with bobBarker
        Assert.assertEquals(bowser.names.size(),bobBarker.names.size());
        for(int i=0; i<3; i++){
            Assert.assertEquals(bowser.names.get(i),bobBarker.names.get(i));
        }
        logger.info("passed");
        logger.info("nicknames:");
        // bowser has same nicknames with bobBarker
        Assert.assertEquals(bowser.nicknames.size(),bobBarker.nicknames.size());
        for(int i=0; i<3; i++){
            Assert.assertEquals(bowser.nicknames.get(i),bobBarker.nicknames.get(i));
        }
        logger.info("passed");
    }

    @Test
    public void testDeepCopy() throws IOException, ClassNotFoundException {
        DogCloneable bowser=new DogCloneable();
        bowser.names.add("Fido");
        bowser.nicknames.add("Fido");
        DogCloneable bobBarker= (DogCloneable) ObjectCloner.deepCopy(bowser);
        bowser.names.add("Bowser");
        bobBarker.names.add("Bob Barker");
        bowser.nicknames.add("Bowser");
        bobBarker.nicknames.add("Bob Barker");
        Log.info("names:");
        // bowser has different names with bobBarker
        Assert.assertEquals(bowser.names.get(0),bobBarker.names.get(0));
        Assert.assertNotEquals(bowser.names.get(1),bobBarker.names.get(1));

        Log.info("nicknames");
        // bowser has different nicknames with bobBarker
        Assert.assertEquals(bowser.names.get(0),bobBarker.names.get(0));
        Assert.assertNotEquals(bowser.names.get(1),bobBarker.names.get(1));
    }

}
