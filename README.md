capicom-wrapper
===============

Capicom Java wrapper for Microsoft capicom library

Official doc: http://msdn.microsoft.com/en-US/en-en/library/windows/desktop/aa382434(v=vs.85).aspx

How to use it?
===============
<b>On 32-bit platform:</b><br/><br/>
1. Download CAPICOM – http://www.microsoft.com/en-us/download/details.aspx?id=25281<br/>
2. Open an administrative command prompt<br/>
3. Execute regsvr32.exe capicom.dll

<b>On 64-bit platform:</b><br/><br/>
1. Download CAPICOM – http://www.microsoft.com/en-us/download/details.aspx?id=25281<br/>
2. Open an administrative command prompt<br/>
3. cd to "C:\Program Files (x86)\Microsoft CAPICOM 2.1.0.2 SDK\Lib\X86"<br/>
4. copy CAPICOM.DLL %windir%\syswow64</br><br/>
5. %windir%\syswow64\regsvr32.exe %windir%\syswow64\capicom.dll<br/>

<b>Add in your project:</b><br/><br/>

Repository: 
```xml
<repository>
   <id>capicom-wrapper-mvn-repo</id>
   <url>https://raw.github.com/creepid/capicom-wrapper/mvn-repo/</url>
   <snapshots>
       <enabled>true</enabled>
       <updatePolicy>always</updatePolicy>
   </snapshots>
</repository>
```

And dependency:
```xml
<dependency>
   <groupId>by.creepid</groupId>
   <artifactId>capicom-wrapper</artifactId>
   <version>0.1</version>
</dependency>
```

Code examples
===============
<b>Getting certificates from windows store:</b>

```JAVA
//2  - storeLocation
//"My" - storeName
//2 - openMode
CapicomStore store = new CapicomStore(2, "My", 2);
CapicomCertificate[] certs = store.getCertificates().getAll();

for (CapicomCertificate capicomCertificate : certs) {
  //TO DO SMTH           
}
```

<b>Sign operation:</b>

```JAVA
//2  - storeLocation
//"My" - storeName
//2 - openMode
CapicomStore store = new CapicomStore(2, "My", 2);
//1 - certificate number in windows store
CapicomCertificate cert = store.getCertificates().getAll()[1];
CapicomSigner signer = new CapicomSigner();
signer.setCertificate(cert);

CapicomSignedData signedData = new CapicomSignedData();
//"test" - signing string
signedData.setContent("test");

CapicomAttribute signingTime = new CapicomAttribute();
//Add signing time attribute, see CapicomAttributeEnum                 
signingTime.setName(CapicomAttributeEnum.CAPICOM_AUTHENTICATED_ATTRIBUTE_SIGNING_TIME);
signingTime.setValue(new Date());

signer.getAuthenticatedAttributes().add(signingTime);
//return signature, throws InvalidCertificate otherwice 
String  signature = signedData.sign(signer, true);

```

<b>Verify operation:</b>
```JAVA
String signature = "..."; //signature in base64
boolean isDetached = true;

CapicomSignedData signedData = new CapicomSignedData();
signedData.setContent("test");

try {
     signedData.verify(signature, isDetached);
} catch (InvalidSignature ex) {
    //in wrong signature case
}
   
//get certificate information        
CapicomCertificate cert = signedData.getCertificates().getAll()[0];
cert.display();

//get subject
String subject = signedData.getCertificates().getAll()[0].getSubjectName();

CapicomSigner signer = signedData.getSigners()[0];
CapicomAttribute attr = signer.getAuthenticatedAttributes().getAll()[0];

//sign date...
Date date = attr.getValue().getDateContent();
```
<b>Getting Java java.security.cert.X509Certificate:</b>
```JAVA
CapicomCertificate[] certs = ...;

for (CapicomCertificate capicomCertificate : certs) {
   X509Certificate x509Cert = capicomCertificate.getX509Certificate();
   //...           
}
```
