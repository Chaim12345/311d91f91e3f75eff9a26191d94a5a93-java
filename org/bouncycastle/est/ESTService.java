package org.bouncycastle.est;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.est.CsrAttrs;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cmc.CMCException;
import org.bouncycastle.cmc.SimplePKIResponse;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
/* loaded from: classes3.dex */
public class ESTService {

    /* renamed from: a  reason: collision with root package name */
    protected static final Set f13553a;
    private static final Pattern pathInValid;
    private final ESTClientProvider clientProvider;
    private final String server;

    static {
        HashSet hashSet = new HashSet();
        f13553a = hashSet;
        hashSet.add("cacerts");
        hashSet.add("simpleenroll");
        hashSet.add("simplereenroll");
        hashSet.add("fullcmc");
        hashSet.add("serverkeygen");
        hashSet.add("csrattrs");
        pathInValid = Pattern.compile("^[0-9a-zA-Z_\\-.~!$&'()*+,;:=]+");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ESTService(String str, String str2, ESTClientProvider eSTClientProvider) {
        String str3;
        String verifyServer = verifyServer(str);
        if (str2 != null) {
            str3 = "https://" + verifyServer + "/.well-known/est/" + verifyLabel(str2);
        } else {
            str3 = "https://" + verifyServer + "/.well-known/est";
        }
        this.server = str3;
        this.clientProvider = eSTClientProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String annotateRequest(byte[] bArr) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        int i2 = 0;
        do {
            int i3 = i2 + 48;
            if (i3 < bArr.length) {
                printWriter.print(Base64.toBase64String(bArr, i2, 48));
                i2 = i3;
            } else {
                printWriter.print(Base64.toBase64String(bArr, i2, bArr.length - i2));
                i2 = bArr.length;
            }
            printWriter.print('\n');
        } while (i2 < bArr.length);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store) {
        return storeToArray(store, null);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store, Selector<X509CertificateHolder> selector) {
        Collection<X509CertificateHolder> matches = store.getMatches(selector);
        return (X509CertificateHolder[]) matches.toArray(new X509CertificateHolder[matches.size()]);
    }

    private String verifyLabel(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        while (str.startsWith("/") && str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() != 0) {
            if (!pathInValid.matcher(str).matches()) {
                throw new IllegalArgumentException("Server path " + str + " contains invalid characters");
            } else if (f13553a.contains(str)) {
                throw new IllegalArgumentException("Label " + str + " is a reserved path segment.");
            } else {
                return str;
            }
        }
        throw new IllegalArgumentException("Label set but after trimming '/' is not zero length string.");
    }

    private String verifyServer(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            try {
                str = str.substring(0, str.length() - 1);
            } catch (Exception e2) {
                if (e2 instanceof IllegalArgumentException) {
                    throw ((IllegalArgumentException) e2);
                }
                throw new IllegalArgumentException("Scheme and host is invalid: " + e2.getMessage(), e2);
            }
        }
        if (str.contains("://")) {
            throw new IllegalArgumentException("Server contains scheme, must only be <dnsname/ipaddress>:port, https:// will be added arbitrarily.");
        }
        URL url = new URL("https://" + str);
        if (url.getPath().length() != 0 && !url.getPath().equals("/")) {
            throw new IllegalArgumentException("Server contains path, must only be <dnsname/ipaddress>:port, a path of '/.well-known/est/<label>' will be added arbitrarily.");
        }
        return str;
    }

    protected EnrollmentResponse b(ESTResponse eSTResponse) {
        long time;
        ESTRequest originalRequest = eSTResponse.getOriginalRequest();
        if (eSTResponse.getStatusCode() != 202) {
            if (eSTResponse.getStatusCode() == 200) {
                try {
                    return new EnrollmentResponse(new SimplePKIResponse(ContentInfo.getInstance(new ASN1InputStream(eSTResponse.getInputStream()).readObject())).getCertificates(), -1L, null, eSTResponse.getSource());
                } catch (CMCException e2) {
                    throw new ESTException(e2.getMessage(), e2.getCause());
                }
            }
            throw new ESTException("Simple Enroll: " + originalRequest.getURL().toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
        }
        String header = eSTResponse.getHeader("Retry-After");
        if (header == null) {
            throw new ESTException("Got Status 202 but not Retry-After header from: " + originalRequest.getURL().toString());
        }
        try {
            try {
                time = System.currentTimeMillis() + (Long.parseLong(header) * 1000);
            } catch (NumberFormatException unused) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                time = simpleDateFormat.parse(header).getTime();
            }
            return new EnrollmentResponse(null, time, originalRequest, eSTResponse.getSource());
        } catch (Exception e3) {
            throw new ESTException("Unable to parse Retry-After header:" + originalRequest.getURL().toString() + " " + e3.getMessage(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
        }
    }

    public CACertsResponse getCACerts() {
        ESTResponse eSTResponse;
        ESTException th;
        Store<X509CertificateHolder> store;
        Store<X509CRLHolder> store2;
        String str;
        Store<X509CertificateHolder> store3;
        Store<X509CRLHolder> store4;
        try {
            URL url = new URL(this.server + "/cacerts");
            ESTClient makeClient = this.clientProvider.makeClient();
            ESTRequest build = new ESTRequestBuilder("GET", url).withClient(makeClient).build();
            eSTResponse = makeClient.doRequest(build);
            try {
                if (eSTResponse.getStatusCode() == 200) {
                    String firstValue = eSTResponse.getHeaders().getFirstValue("Content-Type");
                    if (firstValue == null || !firstValue.startsWith("application/pkcs7-mime")) {
                        if (firstValue != null) {
                            str = " got " + firstValue;
                        } else {
                            str = " but was not present.";
                        }
                        throw new ESTException("Response : " + url.toString() + "Expecting application/pkcs7-mime " + str, null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                    }
                    if (eSTResponse.getContentLength() == null || eSTResponse.getContentLength().longValue() <= 0) {
                        store3 = null;
                        store4 = null;
                    } else {
                        SimplePKIResponse simplePKIResponse = new SimplePKIResponse(ContentInfo.getInstance((ASN1Sequence) new ASN1InputStream(eSTResponse.getInputStream()).readObject()));
                        store3 = simplePKIResponse.getCertificates();
                        store4 = simplePKIResponse.getCRLs();
                    }
                    store = store3;
                    store2 = store4;
                } else if (eSTResponse.getStatusCode() != 204) {
                    throw new ESTException("Get CACerts: " + url.toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                } else {
                    store = null;
                    store2 = null;
                }
                CACertsResponse cACertsResponse = new CACertsResponse(store, store2, build, eSTResponse.getSource(), this.clientProvider.isTrusted());
                try {
                    eSTResponse.close();
                    e = null;
                } catch (Exception e2) {
                    e = e2;
                }
                if (e != null) {
                    if (e instanceof ESTException) {
                        throw ((ESTException) e);
                    }
                    throw new ESTException("Get CACerts: " + url.toString(), e, eSTResponse.getStatusCode(), null);
                }
                return cACertsResponse;
            } catch (Throwable th2) {
                th = th2;
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } catch (Throwable th3) {
                    if (eSTResponse != null) {
                        try {
                            eSTResponse.close();
                        } catch (Exception unused) {
                        }
                    }
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            eSTResponse = null;
            th = th4;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:6|7|(2:9|(2:14|15))(2:32|(5:36|18|19|20|(2:22|(2:24|25)(2:26|27))(2:28|29)))|17|18|19|20|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a7, code lost:
        r1 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CSRRequestResponse getCSRAttributes() {
        ESTResponse eSTResponse;
        ESTException th;
        ESTRequest build;
        CSRAttributesResponse cSRAttributesResponse;
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        try {
            URL url = new URL(this.server + "/csrattrs");
            ESTClient makeClient = this.clientProvider.makeClient();
            eSTResponse = makeClient.doRequest(new ESTRequestBuilder("GET", url).withClient(makeClient).build());
            try {
                int statusCode = eSTResponse.getStatusCode();
                if (statusCode != 200) {
                    if (statusCode != 204 && statusCode != 404) {
                        throw new ESTException("CSR Attribute request: " + build.getURL().toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                    }
                } else if (eSTResponse.getContentLength() != null && eSTResponse.getContentLength().longValue() > 0) {
                    cSRAttributesResponse = new CSRAttributesResponse(CsrAttrs.getInstance(ASN1Sequence.getInstance(new ASN1InputStream(eSTResponse.getInputStream()).readObject())));
                    eSTResponse.close();
                    e = null;
                    if (e == null) {
                        if (e instanceof ESTException) {
                            throw ((ESTException) e);
                        }
                        throw new ESTException(e.getMessage(), e, eSTResponse.getStatusCode(), null);
                    }
                    return new CSRRequestResponse(cSRAttributesResponse, eSTResponse.getSource());
                }
                cSRAttributesResponse = null;
                eSTResponse.close();
                e = null;
                if (e == null) {
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } catch (Throwable th3) {
                    if (eSTResponse != null) {
                        try {
                            eSTResponse.close();
                        } catch (Exception unused) {
                        }
                    }
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            eSTResponse = null;
            th = th4;
        }
    }

    public EnrollmentResponse simpleEnroll(EnrollmentResponse enrollmentResponse) {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                ESTClient makeClient = this.clientProvider.makeClient();
                eSTResponse = makeClient.doRequest(new ESTRequestBuilder(enrollmentResponse.getRequestToRetry()).withClient(makeClient).build());
                return b(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse simpleEnroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                byte[] bytes = annotateRequest(pKCS10CertificationRequest.getEncoded()).getBytes();
                StringBuilder sb = new StringBuilder();
                sb.append(this.server);
                sb.append(z ? "/simplereenroll" : "/simpleenroll");
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withClient = new ESTRequestBuilder("POST", url).withData(bytes).withClient(makeClient);
                withClient.addHeader("Content-Type", "application/pkcs10");
                withClient.addHeader("Content-Length", "" + bytes.length);
                withClient.addHeader("Content-Transfer-Encoding", "base64");
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withClient);
                }
                eSTResponse = makeClient.doRequest(withClient.build());
                return b(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse simpleEnrollPoP(boolean z, final PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, final ContentSigner contentSigner, ESTAuth eSTAuth) {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(this.server);
                sb.append(z ? "/simplereenroll" : "/simpleenroll");
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withConnectionListener = new ESTRequestBuilder("POST", url).withClient(makeClient).withConnectionListener(new ESTSourceConnectionListener() { // from class: org.bouncycastle.est.ESTService.1
                    @Override // org.bouncycastle.est.ESTSourceConnectionListener
                    public ESTRequest onConnection(Source source, ESTRequest eSTRequest) {
                        if (source instanceof TLSUniqueProvider) {
                            TLSUniqueProvider tLSUniqueProvider = (TLSUniqueProvider) source;
                            if (tLSUniqueProvider.isTLSUniqueAvailable()) {
                                PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder2 = new PKCS10CertificationRequestBuilder(pKCS10CertificationRequestBuilder);
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                pKCS10CertificationRequestBuilder2.setAttribute(PKCSObjectIdentifiers.pkcs_9_at_challengePassword, new DERPrintableString(Base64.toBase64String(tLSUniqueProvider.getTLSUnique())));
                                byteArrayOutputStream.write(ESTService.this.annotateRequest(pKCS10CertificationRequestBuilder2.build(contentSigner).getEncoded()).getBytes());
                                byteArrayOutputStream.flush();
                                ESTRequestBuilder withData = new ESTRequestBuilder(eSTRequest).withData(byteArrayOutputStream.toByteArray());
                                withData.setHeader("Content-Type", "application/pkcs10");
                                withData.setHeader("Content-Transfer-Encoding", "base64");
                                withData.setHeader("Content-Length", Long.toString(byteArrayOutputStream.size()));
                                return withData.build();
                            }
                        }
                        throw new IOException("Source does not supply TLS unique.");
                    }
                });
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withConnectionListener);
                }
                eSTResponse = makeClient.doRequest(withConnectionListener.build());
                return b(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }
}
