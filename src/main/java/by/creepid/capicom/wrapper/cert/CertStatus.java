/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper.cert;

/**
 *
 * @author rusakovich
 */
public enum CertStatus {

    CERT_STATUS_UNKNOW(0),
    CERT_VALID(1),
    CERT_NOT_YET_VALID(2),
    CERT_EXPIRED(3),
    CERT_REVOKED(4),
    CERT_PATH_NOT_BUILT(5),
    CERT_PATH_INVALID(6),
    CA_CERT_REVOKED(7),
    REVOCATION_UNAVAILABLE(8);

    private int status;

    private CertStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
