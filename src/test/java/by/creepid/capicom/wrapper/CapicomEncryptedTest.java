package by.creepid.capicom.wrapper;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by aseprojali on 9/6/16.
 */
public class CapicomEncryptedTest extends TestCase {

    public CapicomEncryptedTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void testEncrypt() throws Exception {
        CapicomEncrypted capicomEncrypted = new CapicomEncrypted();
        capicomEncrypted.setAlgoritmName();
        capicomEncrypted.setContent("test");
        capicomEncrypted.setSecret("test");
        String encrypt = capicomEncrypted.encrypt();
        Assert.assertNotNull(encrypt);


        CapicomEncrypted capicomDecrypted = new CapicomEncrypted();
        capicomDecrypted.setAlgoritmName();
        capicomDecrypted.setSecret("test");
        capicomDecrypted.decrypt(encrypt);
        String decrypt = capicomDecrypted.getContent();
        Assert.assertEquals("test", decrypt);

    }
}