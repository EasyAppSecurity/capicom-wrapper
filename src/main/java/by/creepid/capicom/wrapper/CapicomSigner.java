/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 *
 * @author rusakovich
 */
public class CapicomSigner extends CapicomObject {

    private final ActiveXComponent signer;

    public CapicomSigner() {
        signer = new ActiveXComponent("CAPICOM.Signer");
    }

    public void setCertificate(CapicomCertificate cert) {
        signer.setProperty("Certificate", cert.getObject());
    }

    public CapicomCertificate getCertificate() {
        CapicomCertificate cert = null;

        Variant var = signer.getProperty("Certificate");
        if (var != null) {
            cert = new CapicomCertificate(var.getDispatch());
        }

        return cert;
    }

    public CapicomAuthenticatedAttributes getAuthenticatedAttributes() {
        ActiveXComponent attrs = signer.getPropertyAsComponent("AuthenticatedAttributes");
        return new CapicomAuthenticatedAttributes(attrs.getObject());
    }

    @Override
    Dispatch getObject() {
        return signer.getObject();
    }

}
