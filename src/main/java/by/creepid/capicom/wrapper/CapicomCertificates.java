/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public class CapicomCertificates extends CapicomObject{

    private final ActiveXComponent certs;

    CapicomCertificates(ActiveXComponent certs) {
        this.certs = certs;
    }

    public int getCount() {
        return certs.getPropertyAsInt("Count");
    }

    public CapicomCertificate[] getAll() {
        EnumVariant en = new EnumVariant(certs.getObject());

        List<CapicomCertificate> certList = new ArrayList<>();

        while (en.hasMoreElements()) {
            CapicomCertificate cert = new CapicomCertificate(en.nextElement().getDispatch());
            certList.add(cert);
        }

        return certList.toArray(new CapicomCertificate[certList.size()]);
    }

    @Override
    Dispatch getObject() {
        return certs.getObject();
    }

}
