/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import by.creepid.capicom.wrapper.cert.InvalidCertificate;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author rusakovich
 */
public class CapicomSignedDataIntegrationTest extends TestCase {

    public CapicomSignedDataIntegrationTest(String testName) {
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

    /**
     * Test of sign method, of class CapicomSignedData.
     */
    public void testSign() throws Exception {
        System.out.println("****** sign *******");
        CapicomStore store = new CapicomStore(2, "My", 2);

        CapicomCertificate cert = store.getCertificates().getAll()[1];

        CapicomSigner signer = new CapicomSigner();
        signer.setCertificate(cert);

        CapicomSignedData signedData = new CapicomSignedData();
        signedData.setContent("test");

        CapicomAttribute signingTime = new CapicomAttribute();
        signingTime.setName(CapicomAttributeEnum.CAPICOM_AUTHENTICATED_ATTRIBUTE_SIGNING_TIME);
        signingTime.setValue(new Date());

        signer.getAuthenticatedAttributes().add(signingTime);

        try {
            signedData.sign(signer, true);
            fail("Invalid certificate must be thrown");
        } catch (Exception ex) {
            assertTrue((ex instanceof InvalidCertificate));
        }

        cert = store.getCertificates().getAll()[2];

        signer = new CapicomSigner();
        signer.setCertificate(cert);

        signedData = new CapicomSignedData();
        signedData.setContent("test");

        signingTime = new CapicomAttribute();
        signingTime.setName(CapicomAttributeEnum.CAPICOM_AUTHENTICATED_ATTRIBUTE_SIGNING_TIME);
        signingTime.setValue(new Date());

        signer.getAuthenticatedAttributes().add(signingTime);

        String signature = null;
        try {
            signature = signedData.sign(signer, true);
        } catch (InvalidCertificate ex) {
            fail(ex.getMessage());
        }
        assertNotNull(signature);
        assertTrue(signature.length() > 30);
    }

}
