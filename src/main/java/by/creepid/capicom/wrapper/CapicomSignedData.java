/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import by.creepid.capicom.wrapper.cert.CertStatus;
import by.creepid.capicom.wrapper.cert.InvalidCertificate;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 *
 * @author rusakovich
 */
public class CapicomSignedData extends CapicomObject {

    private final ActiveXComponent signed;

    public CapicomSignedData() {
        signed = new ActiveXComponent("CAPICOM.SignedData");
    }

    @Override
    Dispatch getObject() {
        return signed.getObject();
    }

    public String getContent() {
        String result = "";

        Variant var = signed.getProperty("Content");
        if (var != null) {
            result = var.getString();
        }

        return result;
    }

    public String sign(CapicomSigner signer, boolean isDetached)
            throws InvalidCertificate {
        try {
            int detachedVar = (isDetached) ? 1 : 0;
            Variant[] vars = new Variant[]{new Variant(signer.getObject()), new Variant(detachedVar)};

            return signed.invoke("Sign", vars).getString();
        } catch (Exception ex) {
            throw new InvalidCertificate(ex.getMessage(), CertStatus.CERT_VALID);
        }
    }

    public void setContent(String content) {
        signed.setProperty("Content", content);
    }

}
