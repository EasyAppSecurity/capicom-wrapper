/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper.data;

import com.jacob.com.Currency;
import java.util.Date;

/**
 *
 * @author rusakovich
 */
public interface CapicomData {

    public Date getDateContent();

    public String getStringContent();

    public int getIntContent();

    public short getShortContent();

    public boolean getBooleanContent();

    public float getFloatBoolean();

    public Currency getCurrencyContent();

    public long getLongContent();

    public byte getByteContent();

}
