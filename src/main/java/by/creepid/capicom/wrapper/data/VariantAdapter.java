/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.creepid.capicom.wrapper.data;

import com.jacob.com.Currency;
import com.jacob.com.Variant;
import java.util.Date;

/**
 *
 * @author rusakovich
 */
public class VariantAdapter implements CapicomData {

    private final Variant var;

    public VariantAdapter(Object obj) {
        if (!(obj instanceof Variant)) {
            throw new IllegalArgumentException("Not variant argument!");
        }

        var = (Variant) obj;
    }

    public VariantAdapter(Variant var) {
        this.var = var;
    }

    @Override
    public Date getDateContent() {
        return var.getJavaDate();
    }

    @Override
    public String getStringContent() {
        return var.getString();
    }

    @Override
    public int getIntContent() {
        return var.getInt();
    }

    @Override
    public short getShortContent() {
        return var.getShort();
    }

    @Override
    public boolean getBooleanContent() {
        return var.getBoolean();
    }

    @Override
    public float getFloatBoolean() {
        return var.getFloat();
    }

    @Override
    public Currency getCurrencyContent() {
        return var.getCurrency();
    }

    @Override
    public long getLongContent() {
        return var.getLong();
    }

    @Override
    public byte getByteContent() {
        return var.getByte();
    }

}
