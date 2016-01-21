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
public interface KeyIdentifierExtension {

    public String getSubjectKeyIdentifier();

    public String getAuthorityKeyIdentifier();
}
