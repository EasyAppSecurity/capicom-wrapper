/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper.cert;

import by.creepid.capicom.wrapper.CapicomCertificate;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author rusakovich
 */
public class X509CertificateAdapter extends X509Certificate implements KeyIdentifierExtension{

    private final X509Certificate exported;

    public X509CertificateAdapter(byte[] content) {
        this.exported = CertUtil.getX509Certificate(content);
    }

    public X509CertificateAdapter(CapicomCertificate capicomCertificate) {
        byte[] decoded = Base64.decode(capicomCertificate.exportString());
        this.exported = CertUtil.getX509Certificate(decoded);
    }

    @Override
    public String getSubjectKeyIdentifier() {
        return CertUtil.getSubjectKeyIdentifier(exported);
    }

    @Override
    public String getAuthorityKeyIdentifier() {
        return CertUtil.getAuthorityKeyIdentifier(exported);
    }

    @Override
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        exported.checkValidity();
    }

    @Override
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        exported.checkValidity(date);
    }

    @Override
    public int getVersion() {
        return exported.getVersion();
    }

    @Override
    public BigInteger getSerialNumber() {
        return exported.getSerialNumber();
    }

    @Override
    public Principal getIssuerDN() {
        return exported.getIssuerDN();
    }

    @Override
    public Principal getSubjectDN() {
        return exported.getSubjectDN();
    }

    @Override
    public Date getNotBefore() {
        return exported.getNotBefore();
    }

    @Override
    public Date getNotAfter() {
        return exported.getNotAfter();
    }

    @Override
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        return exported.getTBSCertificate();
    }

    @Override
    public byte[] getSignature() {
        return exported.getSignature();
    }

    @Override
    public String getSigAlgName() {
        return exported.getSigAlgName();
    }

    @Override
    public String getSigAlgOID() {
        return exported.getSigAlgOID();
    }

    @Override
    public byte[] getSigAlgParams() {
        return exported.getSigAlgParams();
    }

    @Override
    public boolean[] getIssuerUniqueID() {
        return exported.getIssuerUniqueID();
    }

    @Override
    public boolean[] getSubjectUniqueID() {
        return exported.getSubjectUniqueID();
    }

    @Override
    public boolean[] getKeyUsage() {
        return exported.getKeyUsage();
    }

    @Override
    public int getBasicConstraints() {
        return exported.getBasicConstraints();
    }

    @Override
    public byte[] getEncoded() throws CertificateEncodingException {
        return exported.getEncoded();
    }

    @Override
    public void verify(PublicKey key) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        exported.verify(key);
    }

    @Override
    public void verify(PublicKey key, String sigProvider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        exported.verify(key, sigProvider);
    }

    @Override
    public String toString() {
        return exported.toString();
    }

    @Override
    public PublicKey getPublicKey() {
        return exported.getPublicKey();
    }

    @Override
    public boolean hasUnsupportedCriticalExtension() {
        return exported.hasUnsupportedCriticalExtension();
    }

    @Override
    public Set<String> getCriticalExtensionOIDs() {
        return exported.getCriticalExtensionOIDs();
    }

    @Override
    public Set<String> getNonCriticalExtensionOIDs() {
        return exported.getNonCriticalExtensionOIDs();
    }

    @Override
    public byte[] getExtensionValue(String oid) {
        return exported.getExtensionValue(oid);
    }

}
