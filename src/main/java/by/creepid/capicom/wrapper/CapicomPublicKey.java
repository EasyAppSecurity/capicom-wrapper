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
public class CapicomPublicKey extends CapicomObject {

    private final ActiveXComponent key;

    CapicomPublicKey(Dispatch dispatch) {
        key = new ActiveXComponent(dispatch);
    }

    public byte[] getEncodedKey() {
        byte[] encoded = new byte[0];

        ActiveXComponent comp = key.getPropertyAsComponent("EncodedKey");
        if (comp != null) {
            String value = key.getPropertyAsComponent("EncodedKey").getPropertyAsString("Value");
            if (value != null) {
                encoded = value.getBytes();
            }
        }

        return encoded;
    }

    @Override
    Dispatch getObject() {
        return key.getObject();
    }

}
