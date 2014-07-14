/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 *
 * @author rusakovich
 */
public class CapicomKeyUsage extends CapicomObject {

    private final ActiveXComponent usage;

    CapicomKeyUsage(Dispatch dispatch) {
        usage = new ActiveXComponent(dispatch);
    }

    @Override
    Dispatch getObject() {
        return usage;
    }

    public boolean isCritical() {
        return usage.getPropertyAsBoolean("IsCritical");
    }

    public boolean isCRLSignEnabled() {
        return usage.getPropertyAsBoolean("IsCRLSignEnabled");
    }

    public boolean isDataEnciphermentEnabled() {
        return usage.getPropertyAsBoolean("IsDataEnciphermentEnabled");
    }

    public boolean isDecipherOnlyEnabled() {
        return usage.getPropertyAsBoolean("IsDecipherOnlyEnabled");
    }

    public boolean isDigitalSignatureEnabled() {
        return usage.getPropertyAsBoolean("IsDigitalSignatureEnabled");
    }

    public boolean isEncipherOnlyEnabled() {
        return usage.getPropertyAsBoolean("IsEncipherOnlyEnabled");
    }

    public boolean isKeyAgreementEnabled() {
        return usage.getPropertyAsBoolean("IsKeyAgreementEnabled");
    }

    public boolean isKeyCertSignEnabled() {
        return usage.getPropertyAsBoolean("IsKeyCertSignEnabled");
    }

    public boolean isKeyEnciphermentEnabled() {
        return usage.getPropertyAsBoolean("IsKeyEnciphermentEnabled");
    }

    public boolean isNonRepudiationEnabled() {
        return usage.getPropertyAsBoolean("IsNonRepudiationEnabled");
    }

    public boolean isPresent() {
        return usage.getPropertyAsBoolean("IsPresent");
    }

}
