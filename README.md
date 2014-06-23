capicom-wrapper
===============

Capicom Java wrapper for Microsoft capicom library

Official doc: http://msdn.microsoft.com/en-US/en-en/library/windows/desktop/aa382434(v=vs.85).aspx

How to use it?
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
//1 � cetificate number in windows store
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

