/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

/**
 *
 * @author rusakovich
 */
public enum CapicomKeyStorageFlag {

    //Default key storage.
    CAPICOM_KEY_STORAGE_DEFAULT(0),
    //The key is exportable.
    CAPICOM_KEY_STORAGE_EXPORTABLE(1),
    //The key is user protected.
    CAPICOM_KEY_STORAGE_USER_PROTECTED(2);

    private final int value;

    private CapicomKeyStorageFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
