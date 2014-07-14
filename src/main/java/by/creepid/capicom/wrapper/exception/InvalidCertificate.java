/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper.exception;

import by.creepid.capicom.wrapper.cert.CertStatus;
import java.security.GeneralSecurityException;

/**
 *
 * @author rusakovich
 */
public class InvalidCertificate extends GeneralSecurityException {

    private CertStatus certStatus;

    public InvalidCertificate() {
        super();
    }

    public InvalidCertificate(CertStatus certStatus) {
        super();
        this.certStatus = certStatus;
    }

    public InvalidCertificate(String msg) {
        super(msg);
    }

    public InvalidCertificate(String msg, CertStatus certStatus) {
        super(msg);
        this.certStatus = certStatus;
    }

    public InvalidCertificate(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCertificate(Throwable cause, CertStatus certStatus) {
        super(cause);
        this.certStatus = certStatus;
    }

    public InvalidCertificate(String message, Throwable cause, CertStatus certStatus) {
        super(message, cause);
        this.certStatus = certStatus;
    }

    public InvalidCertificate(Throwable cause) {
        super(cause);
    }

    public CertStatus getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(CertStatus certStatus) {
        this.certStatus = certStatus;
    }

}
