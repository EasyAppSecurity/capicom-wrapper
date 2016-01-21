/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import by.creepid.capicom.wrapper.cert.KeyIdentifierExtension;
import by.creepid.capicom.wrapper.cert.X509CertificateAdapter;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 *
 * @author rusakovich
 */
public class CapicomCertificate extends CapicomObject {

    private final ActiveXComponent cert;

    CapicomCertificate(Dispatch dispatch) {
        cert = new ActiveXComponent(dispatch);
    }

    public String getSubjectName() {
        return cert.getProperty("SubjectName").getString();
    }

    public void display() {
        cert.invoke("Display");
    }

    public String getInfo(CapicomCertInfo type) {
        return cert.invoke("GetInfo", type.getValue()).getString();
    }

    public boolean hasPrivateKey() {
        return cert.invoke("HasPrivateKey").getBoolean();
    }

    public final byte[] export() {
        return cert.invoke("Export").getString().getBytes();
    }

    public final String exportString() {
        return cert.invoke("Export").getString();
    }

    public CapicomPublicKey getPublicKey() {
        return new CapicomPublicKey(cert.invoke("PublicKey").getDispatch());
    }

    public String getIssuerName() {
        return cert.getPropertyAsString("IssuerName");
    }

    public String getSerialNumber() {
        return cert.getPropertyAsString("SerialNumber");
    }

    public Date getValidFromDate() {
        return cert.getProperty("ValidFromDate").getJavaDate();
    }

    public Date getValidToDate() {
        return cert.getProperty("ValidToDate").getJavaDate();
    }

    public CapicomKeyUsage getKeyUsage() {
        return new CapicomKeyUsage(cert.invokeGetComponent("KeyUsage").getObject());
    }

    @Override
    public Dispatch getObject() {
        return cert.getObject();
    }

    public X509Certificate getX509Certificate() {
        byte[] decoded = Base64.decode(exportString());
        return new X509CertificateAdapter(decoded);
    }

    public KeyIdentifierExtension getKeyIdentifierExtension() {
        byte[] decoded = Base64.decode(exportString());
        return new X509CertificateAdapter(decoded);
    }

}
