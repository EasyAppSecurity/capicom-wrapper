/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

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
        return cert.invoke("HasPrivateKey").toBoolean();
    }

    public byte[] export() {
        return cert.invoke("Export").getString().getBytes();
    }

    public CapicomPublicKey getPublicKey() {
        return new CapicomPublicKey(cert.invoke("PublicKey").getDispatch());
    }

    @Override
    public Dispatch getObject() {
        return cert.getObject();
    }

}
