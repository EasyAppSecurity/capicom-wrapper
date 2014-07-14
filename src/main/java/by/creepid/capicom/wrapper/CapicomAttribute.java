/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import by.creepid.capicom.wrapper.data.CapicomData;
import by.creepid.capicom.wrapper.data.VariantAdapter;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 *
 * @author rusakovich
 */
public class CapicomAttribute extends CapicomObject {

    private final ActiveXComponent attr;

    CapicomAttribute(Dispatch dispatch) {
        this.attr = new ActiveXComponent(dispatch);
    }

    public CapicomAttribute() {
        this.attr = new ActiveXComponent("CAPICOM.Attribute");
    }

    public CapicomAttribute(CapicomAttributeEnum name, Object value) {
        this();
        setName(name);
        setValue(value);
    }

    public CapicomAttributeEnum getName() {
        return CapicomAttributeEnum.getCapicomAttribute(attr.getPropertyAsInt("Name"));
    }

    public final void setName(CapicomAttributeEnum name) {
        attr.setProperty("Name", name.getValue());
    }

    public CapicomData getValue() {
        return new VariantAdapter(attr.getProperty("Value"));
    }

    public final void setValue(Object object) {
        attr.setProperty("Value", new Variant(object));
    }

    @Override
    Dispatch getObject() {
        return attr.getObject();
    }

}
