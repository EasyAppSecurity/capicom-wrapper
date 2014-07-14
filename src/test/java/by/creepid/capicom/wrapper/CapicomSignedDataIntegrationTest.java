/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import by.creepid.capicom.wrapper.exception.InvalidCertificate;
import by.creepid.capicom.wrapper.exception.InvalidSignature;
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

    /**
     * Test of verify method, of class CapicomSignedData.
     */
    public void testVerify() {
        System.out.println("**** verify ****");

        String signature = "MIIMIgYJKoZIhvcNAQcCoIIMEzCCDA8CAQExDzANBgkrBgEEAeJwASoFADALBgkq\n"
                + "hkiG9w0BBwGgggn4MIIE3jCCBJygAwIBAgIMQOPcVWM1rQIAAAACMA0GCSsGAQQB\n"
                + "4nABKwUAMIIBFDFlMGMGA1UEAx5cBBQENQQ8BD4EPQRBBEIEQAQwBEYEOAQ+BD0E\n"
                + "PQRLBDkAIAQ6BD4EQAQ9BDUEMgQ+BDkAIARDBDQEPgRBBEIEPgQyBDUEQARPBE4E\n"
                + "SQQ4BDkAIARGBDUEPQRCBEAxHzAdBgNVBAoeFgQXBBAEHgAgACIEEAQSBBUEIQQi\n"
                + "ACIxCzAJBgNVBAYTAkJZMRMwEQYDVQQHHgoEHAQ4BD0EQQQ6MUcwRQYDVQQJHj4E\n"
                + "PwRAAC4AIAQzBDAENwQ1BEIESwAgACIEHwRABDAEMgQ0BDAAIgAsACAANQAsACAE\n"
                + "PgREBDgEQQAgADMEHTEfMB0GCSqGSIb3DQEJARYQd2VsY29tZUBhdmVzdC5ieTAi\n"
                + "GA8yMDExMDUxMTEzMDIxM1oYDzIwMjMwNTEwMjA1OTU5WjCCARoxazBpBgNVBAMe\n"
                + "YgQUBDUEPAQ+BD0EQQRCBEAEMARGBDgEPgQ9BD0ESwQ5ACAEPwQ+BDQERwQ4BD0E\n"
                + "NQQ9BD0ESwQ5ACAEQwQ0BD4EQQRCBD4EMgQ1BEAETwROBEkEOAQ5ACAERgQ1BD0E\n"
                + "QgRAMR8wHQYDVQQKHhYEFwQQBB4AIAAiBBAEEgQVBCEEIgAiMQswCQYDVQQGEwJC\n"
                + "WTETMBEGA1UEBx4KBBwEOAQ9BEEEOjFHMEUGA1UECR4+BD8EQAAuACAEMwQwBDcE\n"
                + "NQRCBEsAIAAiBB8EQAQwBDIENAQwACIALAAgADUALAAgBD4ERAQ4BEEAIAAzBB0x\n"
                + "HzAdBgkqhkiG9w0BCQEWEHdlbGNvbWVAYXZlc3QuYnkwggFHMBgGCSsGAQQB4nAB\n"
                + "JaALBgkrBgEEAeJwBwIDggEpADCCASQwgY8GCSsGAQQB4nABIwOBgQJYm2LeoTxd\n"
                + "XPjBIm8ipEM6d0En7ZK+X4ujkdrC+mPoYXCSlwnAWQJjH19Xw/UDu6QzNbqoB2SM\n"
                + "SBblniti9/DSPJnBzCHvc5WzrrMSQ6JqaQH2j68pS1gzl+SF5m5+75gxrkGHY7Bk\n"
                + "aAPJw2ANlPt3IcUxsNyPUViOxLlDILlTlDCBjwYJKwYBBAHicAEgA4GBAhBsOqXC\n"
                + "1ekotYDh5ddnttY/Fg7YldwbzX0kN0m5+qq5Ve7JCE8DBkI/Wo5NCi716HB5oMOa\n"
                + "RhbNRip0xs6U5R8l3jiHrd6vSOpTgAu7XaXSAnuQwC/XyggSUl6iOn6Ui4l8CX2x\n"
                + "KQ2jB/bSxjGJzZTiV9lQhNOmYSMOrNImAtGQo4HSMIHPMB8GA1UdIwQYMBaAFOCL\n"
                + "xwzn7RGRAwpnen/KGok8CDp0MAwGA1UdEwQFMAMBAf8wDAYDVR0PBAUDAweGADAd\n"
                + "BgNVHQ4EFgQUx2QN52DYTM/MzwOTwWO3tQllO7IwMwYJKwYBBAHicAUEBCYwJDAi\n"
                + "gA8yMDExMDUxMTEyNTcxMlqBDzIwMjYwNTExMTI1NzEyWjA8BggrBgEEAeJwCAQw\n"
                + "Hi4AMQAuADMALgA2AC4AMQAuADQALgAxAC4AMQAyADYANQA2AC4AOAAuADUALgAy\n"
                + "MA0GCSsGAQQB4nABKwUAAy0AHdSBb123jap6MFCWYl1hVEyjgZ7hDRNUvRbxlOGp\n"
                + "pt2JmBtp0iVk47QCumkwggUSMIIE0KADAgECAgxA5FaMFbVCNQAAAA8wDQYJKwYB\n"
                + "BAHicAErBQAwggEaMWswaQYDVQQDHmIEFAQ1BDwEPgQ9BEEEQgRABDAERgQ4BD4E\n"
                + "PQQ9BEsEOQAgBD8EPgQ0BEcEOAQ9BDUEPQQ9BEsEOQAgBEMENAQ+BEEEQgQ+BDIE\n"
                + "NQRABE8ETgRJBDgEOQAgBEYENQQ9BEIEQDEfMB0GA1UECh4WBBcEEAQeACAAIgQQ\n"
                + "BBIEFQQhBCIAIjELMAkGA1UEBhMCQlkxEzARBgNVBAceCgQcBDgEPQRBBDoxRzBF\n"
                + "BgNVBAkePgQ/BEAALgAgBDMEMAQ3BDUEQgRLACAAIgQfBEAEMAQyBDQEMAAiACwA\n"
                + "IAA1ACwAIAQ+BEQEOARBACAAMwQdMR8wHQYJKoZIhvcNAQkBFhB3ZWxjb21lQGF2\n"
                + "ZXN0LmJ5MCIYDzIwMTQwMTEzMDcwMTQ2WhgPMjAyMDAxMTIyMTU5NTlaMIIBEDE/\n"
                + "MD0GA1UEAx42BCIEQwQ8BDUEOwRMACAEIQQ1BEAEMwQ1BDkAIAQQBDsENQQ6BEEE\n"
                + "MAQ9BDQEQAQ+BDIEOARHMRUwEwYDVQQEHgwEIgRDBDwENQQ7BEwxMTAvBgNVBCke\n"
                + "KAQhBDUEQAQzBDUEOQAgBBAEOwQ1BDoEQQQwBD0ENARABD4EMgQ4BEcxCzAJBgNV\n"
                + "BAYTAkJZMRUwEwYDVQQIHgwEHAQ4BD0EQQQ6BDAxEzARBgNVBAceCgQcBDgEPQRB\n"
                + "BDoxKzApBgNVBAkeIgQdBDUENwQwBDIEOARBBDgEPAQ+BEEEQgQ4ACAAMQA3ADIx\n"
                + "HTAbBgkqhkiG9w0BCQEWDnR1bWVsQHRvcGJ5LmJ5MIIBRzAYBgkrBgEEAeJwASWg\n"
                + "CwYJKwYBBAHicAcCA4IBKQAwggEkMIGPBgkrBgEEAeJwASMDgYECe5kOSMFHrExb\n"
                + "UatCDiwezQ3e7j1z1liTz0oh4QvUkzeNZsdG7Km+Vbyiy0supEN4ALTFSFdFnOZR\n"
                + "Ldh/JM3kYojv1bKcImhGJ928obVG0aD8Kyct8iZd4/yEcE2yN7qYZO/FY0h5Npjl\n"
                + "OTAGyeVT8n1Cxy+xZsmCdYmwFc4XeoAwgY8GCSsGAQQB4nABIAOBgQJqnpci3SnK\n"
                + "H5FYLYTspeIdGBYPali+BDrfBE0Xu8p5tGGqht3ovOKgC2jKcm6TrBs4exlmjC3d\n"
                + "NSUFVlF/1Iwhlt1lc2abIUvzSmK8lgFqnArdY95yrVruXa6m2pi1o4WlW9SPlX7R\n"
                + "PWBGbwLFn++sMJUAv+fYTEmCuruk1ht98KOCAQkwggEFMB8GA1UdIwQYMBaAFMdk\n"
                + "Dedg2EzPzM8Dk8Fjt7UJZTuyMAkGA1UdEwQCMAAwDAYDVR0PBAUDAwe4ADAdBgNV\n"
                + "HQ4EFgQUDuRrP8aPZBVADKoM+znKzs6pmCUwHQYDVR0lBBYwFAYIKwYBBQUHAwQG\n"
                + "CCsGAQUFBwMCMB8GCSsGAQQB4nAFAQQSHhAEFAQ4BEAENQQ6BEIEPgRAMDMGCSsG\n"
                + "AQQB4nAFBAQmMCQwIoAPMjAxNDAxMTMwNzAzMjFagQ8yMDI5MDExMzA3MDMyMVow\n"
                + "NQYJKwYBBAHicAUDBCgeJgQUBD4EOgRDBDwENQQ9BEIAIAQ9BD4EPAQ1BEAAIAQ+\n"
                + "BDQEOAQ9MA0GCSsGAQQB4nABKwUAAy0ADXawbSrlH2Ahz3cKXv1KyXhBuA4bT562\n"
                + "MxU+/vz/qhthkuO9V3DoiMPczWUxggHuMIIB6gIBATCCASwwggEaMWswaQYDVQQD\n"
                + "HmIEFAQ1BDwEPgQ9BEEEQgRABDAERgQ4BD4EPQQ9BEsEOQAgBD8EPgQ0BEcEOAQ9\n"
                + "BDUEPQQ9BEsEOQAgBEMENAQ+BEEEQgQ+BDIENQRABE8ETgRJBDgEOQAgBEYENQQ9\n"
                + "BEIEQDEfMB0GA1UECh4WBBcEEAQeACAAIgQQBBIEFQQhBCIAIjELMAkGA1UEBhMC\n"
                + "QlkxEzARBgNVBAceCgQcBDgEPQRBBDoxRzBFBgNVBAkePgQ/BEAALgAgBDMEMAQ3\n"
                + "BDUEQgRLACAAIgQfBEAEMAQyBDQEMAAiACwAIAA1ACwAIAQ+BEQEOARBACAAMwQd\n"
                + "MR8wHQYJKoZIhvcNAQkBFhB3ZWxjb21lQGF2ZXN0LmJ5AgxA5FaMFbVCNQAAAA8w\n"
                + "DQYJKwYBBAHicAEqBQCgaTAYBgkqhkiG9w0BCQMxCwYJKoZIhvcNAQcBMBwGCSqG\n"
                + "SIb3DQEJBTEPFw0xNDA3MDcxNjE2NTVaMC8GCSqGSIb3DQEJBDEiBCAGMzaG6Hge\n"
                + "+ysRq8GqgW3Ip+wzZA7y1pbcgGQNnzGqKjANBgkrBgEEAeJwASUFAAQsEYB0yz67\n"
                + "NAEScJmDb4gI7WJIF5p1dikPBbstKounMt5PcfS0CHcxTZSoZbQ=";
        boolean isDetached = true;

        CapicomSignedData signedData = new CapicomSignedData();
        signedData.setContent("test");

        try {
            signedData.verify(signature, isDetached);
        } catch (InvalidSignature ex) {
            fail("Exception while verify: " + ex.getMessage());
        }

        CapicomCertificate cert = signedData.getCertificates().getAll()[0];
        assertNotNull(cert);
        cert.display();

        String subject = signedData.getCertificates().getAll()[0].getSubjectName();
        assertNotNull(subject);

        CapicomSigner signer = signedData.getSigners()[0];
        CapicomAttribute attr = signer.getAuthenticatedAttributes().getAll()[0];

        Date date = attr.getValue().getDateContent();
        assertNotNull(date);
    }

}
