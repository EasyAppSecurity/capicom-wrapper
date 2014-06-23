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
public enum CapicomCertInfo {

    CERT_INFO_SUBJECT_SIMPLE_NAME("Returns the display name of the certificate subject", 0),
    CERT_INFO_ISSUER_SIMPLE_NAME("Returns the display name of the issuer of the certificate", 1),
    CERT_INFO_SUBJECT_EMAIL_NAME("Returns the email address of the certificate subject", 2),
    CERT_INFO_ISSUER_EMAIL_NAME("Returns the email address of the issuer of the certificate", 3),
    //UPN - Microsoft's Universal Principal Name
    //User Principal Name (UPN) is the name of a system 
    //user in an e-mail address format. The user name (or "username") 
    //is followed by the "at sign" followed by the name 
    //of the Internet domain with which the user is associated.
    CERT_INFO_SUBJECT_UPN("Returns the UPN of the certificate subject", 4),
    //UPN - Microsoft's Universal Principal Name
    CERT_INFO_ISSUER_UPN("Returns the UPN of the issuer of the certificate", 5),
    CERT_INFO_SUBJECT_DNS_NAME("Returns the DNS name of the certificate subject", 6),
    CERT_INFO_ISSUER_DNS_NAME("Returns the DNS name of the issuer of the certificate", 7);
    private String description;
    private int value;

    private CapicomCertInfo(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public static CapicomCertInfo getCertInfo(int value) {
        if (value > 7 || value < 0) {
            throw new IllegalArgumentException(
                    "Value must be in range: from 0 to 7");
        }
        CapicomCertInfo[] types = CapicomCertInfo.values();
        for (CapicomCertInfo certInfoType : types) {
            if (certInfoType.getValue() == value) {
                return certInfoType;
            }
        }
        return null;
    }
}
