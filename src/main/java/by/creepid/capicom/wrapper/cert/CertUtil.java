/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper.cert;

import by.creepid.capicom.wrapper.utils.Hex;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 *
 * @author rusakovich
 */
public class CertUtil {

    private CertUtil() {
    }

    public static X509Certificate getX509Certificate(byte[] cert) {
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            InputStream in = new ByteArrayInputStream(cert);
            return (X509Certificate) certFactory.generateCertificate(in);
        } catch (CertificateException thr) {
            throw new SecurityException(thr);
        }
    }

    public static String getSubjectKeyIdentifier(X509Certificate cert) {
        byte[] subjectKeyIdentifierExtension = cert.getExtensionValue("2.5.29.14");
        //first bytes 4 22 4 20
        byte[] subjectKeyId = Arrays.copyOfRange(subjectKeyIdentifierExtension, 4, subjectKeyIdentifierExtension.length);
        return Hex.toHexString(subjectKeyId);
    }

    public static String getAuthorityKeyIdentifier(X509Certificate cert) {
        byte[] authorityKeyIdentifierExtension = cert.getExtensionValue("2.5.29.35");
        //first bytes 4 24 48 22
        byte[] authorityKeyId = Arrays.copyOfRange(authorityKeyIdentifierExtension, 6, authorityKeyIdentifierExtension.length);
        return Hex.toHexString(authorityKeyId);
    }

}
