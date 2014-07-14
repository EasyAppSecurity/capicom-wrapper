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
public class CapicomSigners extends CapicomObject {

    private final ActiveXComponent signers;

    CapicomSigners(ActiveXComponent signers) {
        this.signers = signers;
    }

    public int getCount() {
        return signers.getPropertyAsInt("Count");
    }

    public CapicomSigner[] getAll() {
        EnumVariant en = new EnumVariant(signers.getObject());

        List<CapicomSigner> signerList = new ArrayList<>();

        while (en.hasMoreElements()) {
            CapicomSigner signer = new CapicomSigner(en.nextElement().getDispatch());
            signerList.add(signer);
        }

        return signerList.toArray(new CapicomSigner[signerList.size()]);
    }

    @Override
    Dispatch getObject() {
        return signers.getObject();
    }

}
