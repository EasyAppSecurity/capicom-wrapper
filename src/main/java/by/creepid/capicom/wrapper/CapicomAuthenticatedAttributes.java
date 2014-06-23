/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rusakovich
 */
public class CapicomAuthenticatedAttributes extends CapicomObject {

    private final ActiveXComponent attrs;

    CapicomAuthenticatedAttributes(Dispatch dispatch) {
        attrs = new ActiveXComponent(dispatch);
    }

    public CapicomAttribute[] getAll() {
        List<CapicomAttribute> attrsList = new ArrayList<>();

        EnumVariant en = new EnumVariant(attrs.getObject());
        while (en.hasMoreElements()) {
            CapicomAttribute attr = new CapicomAttribute(en.nextElement().getDispatch());

            attrsList.add(attr);
        }

        return attrsList.toArray(new CapicomAttribute[attrsList.size()]);
    }

    public void add(CapicomAttribute attr) {
        attrs.invoke("Add", new Variant[]{new Variant(attr.getObject())});
    }

    @Override
    Dispatch getObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
