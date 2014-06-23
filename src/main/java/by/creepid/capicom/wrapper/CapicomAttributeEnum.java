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
public enum CapicomAttributeEnum {

    //The attribute contains the time that the signature was created.
    CAPICOM_AUTHENTICATED_ATTRIBUTE_SIGNING_TIME(0),
    //The attribute contains the name of the signed document.
    CAPICOM_AUTHENTICATED_ATTRIBUTE_DOCUMENT_NAME(1),
    //The attribute contains a description of the signed document.
    CAPICOM_AUTHENTICATED_ATTRIBUTE_DOCUMENT_DESCRIPTION(2);

    private CapicomAttributeEnum(int value) {
        this.value = value;
    }

    public int value;

    public int getValue() {
        return value;
    }

    public static CapicomAttributeEnum getCapicomAttribute(int value) {
        if (value > 3 || value < 0) {
            throw new IllegalArgumentException(
                    "Value must be in range: from 0 to 3");
        }
        CapicomAttributeEnum[] types = CapicomAttributeEnum.values();
        for (CapicomAttributeEnum type : types) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

}
