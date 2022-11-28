package org.bouncycastle.est;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes3.dex */
public class HttpAuth implements ESTAuth {
    private static final DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder = new DefaultDigestAlgorithmIdentifierFinder();
    private static final Set<String> validParts;
    private final DigestCalculatorProvider digestCalculatorProvider;
    private final SecureRandom nonceGenerator;
    private final char[] password;
    private final String realm;
    private final String username;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("realm");
        hashSet.add("nonce");
        hashSet.add("opaque");
        hashSet.add("algorithm");
        hashSet.add("qop");
        validParts = Collections.unmodifiableSet(hashSet);
    }

    public HttpAuth(String str, String str2, char[] cArr) {
        this(str, str2, cArr, null, null);
    }

    public HttpAuth(String str, String str2, char[] cArr, SecureRandom secureRandom, DigestCalculatorProvider digestCalculatorProvider) {
        this.realm = str;
        this.username = str2;
        this.password = cArr;
        this.nonceGenerator = secureRandom;
        this.digestCalculatorProvider = digestCalculatorProvider;
    }

    public HttpAuth(String str, char[] cArr) {
        this(null, str, cArr, null, null);
    }

    public HttpAuth(String str, char[] cArr, SecureRandom secureRandom, DigestCalculatorProvider digestCalculatorProvider) {
        this(null, str, cArr, secureRandom, digestCalculatorProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ESTResponse doDigestFunction(ESTResponse eSTResponse) {
        String str;
        String str2;
        eSTResponse.close();
        ESTRequest originalRequest = eSTResponse.getOriginalRequest();
        try {
            Map b2 = HttpUtil.b("Digest", eSTResponse.getHeader("WWW-Authenticate"));
            try {
                String path = originalRequest.getURL().toURI().getPath();
                for (Object obj : b2.keySet()) {
                    if (!validParts.contains(obj)) {
                        throw new ESTException("Unrecognised entry in WWW-Authenticate header: '" + obj + "'");
                    }
                }
                String method = originalRequest.getMethod();
                String str3 = (String) b2.get("realm");
                String str4 = (String) b2.get("nonce");
                String str5 = (String) b2.get("opaque");
                String str6 = "algorithm";
                String str7 = (String) b2.get("algorithm");
                String str8 = "qop";
                String str9 = (String) b2.get("qop");
                ArrayList arrayList = new ArrayList();
                String str10 = this.realm;
                if (str10 != null && !str10.equals(str3)) {
                    throw new ESTException("Supplied realm '" + this.realm + "' does not match server realm '" + str3 + "'", null, 401, null);
                }
                if (str7 == null) {
                    str7 = MessageDigestAlgorithms.MD5;
                }
                if (str7.length() == 0) {
                    throw new ESTException("WWW-Authenticate no algorithm defined.");
                }
                String upperCase = Strings.toUpperCase(str7);
                if (str9 == null) {
                    throw new ESTException("Qop is not defined in WWW-Authenticate header.");
                }
                if (str9.length() == 0) {
                    throw new ESTException("QoP value is empty.");
                }
                String[] split = Strings.toLowerCase(str9).split(",");
                int i2 = 0;
                while (true) {
                    String str11 = str6;
                    String str12 = str8;
                    if (i2 == split.length) {
                        AlgorithmIdentifier lookupDigest = lookupDigest(upperCase);
                        if (lookupDigest == null || lookupDigest.getAlgorithm() == null) {
                            throw new IOException("auth digest algorithm unknown: " + upperCase);
                        }
                        DigestCalculator digestCalculator = getDigestCalculator(upperCase, lookupDigest);
                        OutputStream outputStream = digestCalculator.getOutputStream();
                        String makeNonce = makeNonce(10);
                        update(outputStream, this.username);
                        update(outputStream, ":");
                        update(outputStream, str3);
                        update(outputStream, ":");
                        update(outputStream, this.password);
                        outputStream.close();
                        byte[] digest = digestCalculator.getDigest();
                        if (upperCase.endsWith("-SESS")) {
                            DigestCalculator digestCalculator2 = getDigestCalculator(upperCase, lookupDigest);
                            OutputStream outputStream2 = digestCalculator2.getOutputStream();
                            update(outputStream2, Hex.toHexString(digest));
                            update(outputStream2, ":");
                            update(outputStream2, str4);
                            update(outputStream2, ":");
                            update(outputStream2, makeNonce);
                            outputStream2.close();
                            digest = digestCalculator2.getDigest();
                        }
                        String hexString = Hex.toHexString(digest);
                        DigestCalculator digestCalculator3 = getDigestCalculator(upperCase, lookupDigest);
                        OutputStream outputStream3 = digestCalculator3.getOutputStream();
                        if (((String) arrayList.get(0)).equals("auth-int")) {
                            DigestCalculator digestCalculator4 = getDigestCalculator(upperCase, lookupDigest);
                            str = "auth-int";
                            OutputStream outputStream4 = digestCalculator4.getOutputStream();
                            originalRequest.writeData(outputStream4);
                            outputStream4.close();
                            byte[] digest2 = digestCalculator4.getDigest();
                            update(outputStream3, method);
                            update(outputStream3, ":");
                            update(outputStream3, path);
                            update(outputStream3, ":");
                            update(outputStream3, Hex.toHexString(digest2));
                        } else {
                            str = "auth-int";
                            if (((String) arrayList.get(0)).equals("auth")) {
                                update(outputStream3, method);
                                update(outputStream3, ":");
                                update(outputStream3, path);
                            }
                        }
                        outputStream3.close();
                        String hexString2 = Hex.toHexString(digestCalculator3.getDigest());
                        DigestCalculator digestCalculator5 = getDigestCalculator(upperCase, lookupDigest);
                        OutputStream outputStream5 = digestCalculator5.getOutputStream();
                        boolean contains = arrayList.contains("missing");
                        update(outputStream5, hexString);
                        update(outputStream5, ":");
                        update(outputStream5, str4);
                        update(outputStream5, ":");
                        if (contains) {
                            update(outputStream5, hexString2);
                            str2 = str;
                        } else {
                            update(outputStream5, "00000001");
                            update(outputStream5, ":");
                            update(outputStream5, makeNonce);
                            update(outputStream5, ":");
                            str2 = str;
                            if (((String) arrayList.get(0)).equals(str2)) {
                                update(outputStream5, str2);
                            } else {
                                update(outputStream5, "auth");
                            }
                            update(outputStream5, ":");
                            update(outputStream5, hexString2);
                        }
                        outputStream5.close();
                        String hexString3 = Hex.toHexString(digestCalculator5.getDigest());
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", this.username);
                        hashMap.put("realm", str3);
                        hashMap.put("nonce", str4);
                        hashMap.put("uri", path);
                        hashMap.put("response", hexString3);
                        if (!((String) arrayList.get(0)).equals(str2)) {
                            if (((String) arrayList.get(0)).equals("auth")) {
                                hashMap.put(str12, "auth");
                            }
                            hashMap.put(str11, upperCase);
                            if (str5 != null || str5.length() == 0) {
                                hashMap.put("opaque", makeNonce(20));
                            }
                            ESTRequestBuilder withHijacker = new ESTRequestBuilder(originalRequest).withHijacker(null);
                            withHijacker.setHeader("Authorization", HttpUtil.a("Digest", hashMap));
                            return originalRequest.getClient().doRequest(withHijacker.build());
                        }
                        hashMap.put(str12, str2);
                        hashMap.put("nc", "00000001");
                        hashMap.put("cnonce", makeNonce);
                        hashMap.put(str11, upperCase);
                        if (str5 != null) {
                        }
                        hashMap.put("opaque", makeNonce(20));
                        ESTRequestBuilder withHijacker2 = new ESTRequestBuilder(originalRequest).withHijacker(null);
                        withHijacker2.setHeader("Authorization", HttpUtil.a("Digest", hashMap));
                        return originalRequest.getClient().doRequest(withHijacker2.build());
                    } else if (!split[i2].equals("auth") && !split[i2].equals("auth-int")) {
                        throw new ESTException("QoP value unknown: '" + i2 + "'");
                    } else {
                        String trim = split[i2].trim();
                        if (!arrayList.contains(trim)) {
                            arrayList.add(trim);
                        }
                        i2++;
                        str6 = str11;
                        str8 = str12;
                    }
                }
            } catch (Exception e2) {
                throw new IOException("unable to process URL in request: " + e2.getMessage());
            }
        } catch (Throwable th) {
            throw new ESTException("Parsing WWW-Authentication header: " + th.getMessage(), th, eSTResponse.getStatusCode(), new ByteArrayInputStream(eSTResponse.getHeader("WWW-Authenticate").getBytes()));
        }
    }

    private DigestCalculator getDigestCalculator(String str, AlgorithmIdentifier algorithmIdentifier) {
        try {
            return this.digestCalculatorProvider.get(algorithmIdentifier);
        } catch (OperatorCreationException e2) {
            throw new IOException("cannot create digest calculator for " + str + ": " + e2.getMessage());
        }
    }

    private AlgorithmIdentifier lookupDigest(String str) {
        if (str.endsWith("-SESS")) {
            str = str.substring(0, str.length() - 5);
        }
        return str.equals("SHA-512-256") ? digestAlgorithmIdentifierFinder.find(NISTObjectIdentifiers.id_sha512_256) : digestAlgorithmIdentifierFinder.find(str);
    }

    private String makeNonce(int i2) {
        byte[] bArr = new byte[i2];
        this.nonceGenerator.nextBytes(bArr);
        return Hex.toHexString(bArr);
    }

    private void update(OutputStream outputStream, String str) {
        outputStream.write(Strings.toUTF8ByteArray(str));
    }

    private void update(OutputStream outputStream, char[] cArr) {
        outputStream.write(Strings.toUTF8ByteArray(cArr));
    }

    @Override // org.bouncycastle.est.ESTAuth
    public void applyAuth(ESTRequestBuilder eSTRequestBuilder) {
        eSTRequestBuilder.withHijacker(new ESTHijacker() { // from class: org.bouncycastle.est.HttpAuth.1
            @Override // org.bouncycastle.est.ESTHijacker
            public ESTResponse hijack(ESTRequest eSTRequest, Source source) {
                ESTResponse eSTResponse = new ESTResponse(eSTRequest, source);
                if (eSTResponse.getStatusCode() == 401) {
                    String header = eSTResponse.getHeader("WWW-Authenticate");
                    if (header != null) {
                        String lowerCase = Strings.toLowerCase(header);
                        if (lowerCase.startsWith(CMSAttributeTableGenerator.DIGEST)) {
                            return HttpAuth.this.doDigestFunction(eSTResponse);
                        }
                        if (!lowerCase.startsWith("basic")) {
                            throw new ESTException("Unknown auth mode: " + lowerCase);
                        }
                        eSTResponse.close();
                        Map b2 = HttpUtil.b("Basic", eSTResponse.getHeader("WWW-Authenticate"));
                        if (HttpAuth.this.realm != null && !HttpAuth.this.realm.equals(b2.get("realm"))) {
                            throw new ESTException("Supplied realm '" + HttpAuth.this.realm + "' does not match server realm '" + ((String) b2.get("realm")) + "'", null, 401, null);
                        }
                        ESTRequestBuilder withHijacker = new ESTRequestBuilder(eSTRequest).withHijacker(null);
                        if (HttpAuth.this.realm != null && HttpAuth.this.realm.length() > 0) {
                            withHijacker.setHeader("WWW-Authenticate", "Basic realm=\"" + HttpAuth.this.realm + "\"");
                        }
                        if (HttpAuth.this.username.contains(":")) {
                            throw new IllegalArgumentException("User must not contain a ':'");
                        }
                        char[] cArr = new char[HttpAuth.this.username.length() + 1 + HttpAuth.this.password.length];
                        System.arraycopy(HttpAuth.this.username.toCharArray(), 0, cArr, 0, HttpAuth.this.username.length());
                        cArr[HttpAuth.this.username.length()] = AbstractJsonLexerKt.COLON;
                        System.arraycopy(HttpAuth.this.password, 0, cArr, HttpAuth.this.username.length() + 1, HttpAuth.this.password.length);
                        withHijacker.setHeader("Authorization", "Basic " + Base64.toBase64String(Strings.toByteArray(cArr)));
                        ESTResponse doRequest = eSTRequest.getClient().doRequest(withHijacker.build());
                        Arrays.fill(cArr, (char) 0);
                        return doRequest;
                    }
                    throw new ESTException("Status of 401 but no WWW-Authenticate header");
                }
                return eSTResponse;
            }
        });
    }
}
