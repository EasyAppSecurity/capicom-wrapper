/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.creepid.capicom.wrapper.exception;

import java.security.GeneralSecurityException;

/**
 *
 * @author rusakovich
 */
public class InvalidSignature extends GeneralSecurityException {

    public InvalidSignature() {
        super();
    }

    public InvalidSignature(String msg) {
        super(msg);
    }

    public InvalidSignature(String message, Throwable cause) {
        super(message, cause);
    }

}
