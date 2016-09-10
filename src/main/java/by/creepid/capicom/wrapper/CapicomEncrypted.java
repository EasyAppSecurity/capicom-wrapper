package by.creepid.capicom.wrapper;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * Created by aseprojali on 9/6/16.
 */
public class CapicomEncrypted extends CapicomObject {
    private final ActiveXComponent encryptedData;

    public CapicomEncrypted() {
        this.encryptedData = new ActiveXComponent("CAPICOM.EncryptedData");
    }

    public void setAlgoritmName() {
        ActiveXComponent algorithm = this.encryptedData.getPropertyAsComponent("Algorithm");
        algorithm.setProperty("KeyLength", 0);
        algorithm.setProperty("Name", 4);
    }

    public void setContent(String content) {
        this.encryptedData.setProperty("Content", content);
    }


    public String getContent() {
        return this.encryptedData.getProperty("Content").getString();
    }


    public void setSecret(String key) {
        this.encryptedData.invoke("SetSecret", key);
    }


    public String encrypt() {
        return this.encryptedData.invoke("Encrypt").getString();
    }

    public void decrypt(String content) {
        this.encryptedData.invoke("Decrypt", content);
    }

    @Override
    Dispatch getObject() {
        return encryptedData.getObject();
    }
}

