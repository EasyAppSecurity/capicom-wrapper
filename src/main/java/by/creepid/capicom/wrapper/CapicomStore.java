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
public class CapicomStore extends CapicomObject {

    private final ActiveXComponent store;

    private void open(int storeLocation, String storeName, int openMode) {
        Variant[] var = new Variant[]{new Variant(storeLocation), new Variant(storeName), new Variant(openMode)};
        store.invoke("Open", var);
    }

    public CapicomStore(int storeLocation, String storeName, int openMode) {
        store = new ActiveXComponent("CAPICOM.Store");
        open(storeLocation, storeName, openMode);
    }

    public CapicomCertificates getCertificates() {
        ActiveXComponent certs = store.getPropertyAsComponent("Certificates");
        return new CapicomCertificates(certs);
    }

    @Override
    Dispatch getObject() {
        return store.getObject();
    }

}
