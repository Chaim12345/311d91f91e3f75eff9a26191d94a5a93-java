package org.apache.http.impl.auth;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.KerberosCredentials;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;
/* loaded from: classes3.dex */
public abstract class GGSSchemeBase extends AuthSchemeBase {
    private final Base64 base64codec;
    private final Log log;
    private State state;
    private final boolean stripPort;
    private byte[] token;
    private final boolean useCanonicalHostname;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.http.impl.auth.GGSSchemeBase$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State = iArr;
            try {
                iArr[State.UNINITIATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.CHALLENGE_RECEIVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.TOKEN_GENERATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        TOKEN_GENERATED,
        FAILED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GGSSchemeBase() {
        this(true, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GGSSchemeBase(boolean z) {
        this(z, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GGSSchemeBase(boolean z, boolean z2) {
        this.log = LogFactory.getLog(getClass());
        this.base64codec = new Base64(0);
        this.stripPort = z;
        this.useCanonicalHostname = z2;
        this.state = State.UNINITIATED;
    }

    private String resolveCanonicalHostname(String str) {
        InetAddress byName = InetAddress.getByName(str);
        String canonicalHostName = byName.getCanonicalHostName();
        return byName.getHostAddress().contentEquals(canonicalHostName) ? str : canonicalHostName;
    }

    @Override // org.apache.http.auth.AuthScheme
    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return authenticate(credentials, httpRequest, null);
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase, org.apache.http.auth.ContextAwareAuthScheme
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        HttpHost targetHost;
        Args.notNull(httpRequest, "HTTP request");
        int i2 = AnonymousClass1.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[this.state.ordinal()];
        if (i2 == 1) {
            throw new AuthenticationException(getSchemeName() + " authentication has not been initiated");
        } else if (i2 == 2) {
            throw new AuthenticationException(getSchemeName() + " authentication has failed");
        } else {
            if (i2 == 3) {
                try {
                    HttpRoute httpRoute = (HttpRoute) httpContext.getAttribute("http.route");
                    if (httpRoute == null) {
                        throw new AuthenticationException("Connection route is not available");
                    }
                    if (!isProxy() || (targetHost = httpRoute.getProxyHost()) == null) {
                        targetHost = httpRoute.getTargetHost();
                    }
                    String hostName = targetHost.getHostName();
                    if (this.useCanonicalHostname) {
                        try {
                            hostName = resolveCanonicalHostname(hostName);
                        } catch (UnknownHostException unused) {
                        }
                    }
                    if (!this.stripPort) {
                        hostName = hostName + ":" + targetHost.getPort();
                    }
                    if (this.log.isDebugEnabled()) {
                        this.log.debug("init " + hostName);
                    }
                    this.token = generateToken(this.token, hostName, credentials);
                    this.state = State.TOKEN_GENERATED;
                } catch (GSSException e2) {
                    this.state = State.FAILED;
                    if (e2.getMajor() == 9 || e2.getMajor() == 8) {
                        throw new InvalidCredentialsException(e2.getMessage(), e2);
                    }
                    if (e2.getMajor() != 13) {
                        if (e2.getMajor() == 10 || e2.getMajor() == 19 || e2.getMajor() == 20) {
                            throw new AuthenticationException(e2.getMessage(), e2);
                        }
                        throw new AuthenticationException(e2.getMessage());
                    }
                    throw new InvalidCredentialsException(e2.getMessage(), e2);
                }
            } else if (i2 != 4) {
                throw new IllegalStateException("Illegal state: " + this.state);
            }
            String str = new String(this.base64codec.encode(this.token));
            if (this.log.isDebugEnabled()) {
                this.log.debug("Sending response '" + str + "' back to the auth server");
            }
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
            charArrayBuffer.append(isProxy() ? "Proxy-Authorization" : "Authorization");
            charArrayBuffer.append(": Negotiate ");
            charArrayBuffer.append(str);
            return new BufferedHeader(charArrayBuffer);
        }
    }

    GSSContext createGSSContext(GSSManager gSSManager, Oid oid, GSSName gSSName, GSSCredential gSSCredential) {
        GSSContext createContext = gSSManager.createContext(gSSName.canonicalize(oid), oid, gSSCredential, 0);
        createContext.requestMutualAuth(true);
        return createContext;
    }

    protected byte[] generateGSSToken(byte[] bArr, Oid oid, String str) {
        return generateGSSToken(bArr, oid, str, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] generateGSSToken(byte[] bArr, Oid oid, String str, Credentials credentials) {
        GSSManager manager = getManager();
        GSSContext createGSSContext = createGSSContext(manager, oid, manager.createName("HTTP@" + str, GSSName.NT_HOSTBASED_SERVICE), credentials instanceof KerberosCredentials ? ((KerberosCredentials) credentials).getGSSCredential() : null);
        return bArr != null ? createGSSContext.initSecContext(bArr, 0, bArr.length) : createGSSContext.initSecContext(new byte[0], 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public byte[] generateToken(byte[] bArr, String str) {
        return null;
    }

    protected byte[] generateToken(byte[] bArr, String str, Credentials credentials) {
        return generateToken(bArr, str);
    }

    protected GSSManager getManager() {
        return GSSManager.getInstance();
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isComplete() {
        State state = this.state;
        return state == State.TOKEN_GENERATED || state == State.FAILED;
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase
    protected void parseChallenge(CharArrayBuffer charArrayBuffer, int i2, int i3) {
        State state;
        String substringTrimmed = charArrayBuffer.substringTrimmed(i2, i3);
        if (this.log.isDebugEnabled()) {
            Log log = this.log;
            log.debug("Received challenge '" + substringTrimmed + "' from the auth server");
        }
        if (this.state == State.UNINITIATED) {
            this.token = Base64.decodeBase64(substringTrimmed.getBytes());
            state = State.CHALLENGE_RECEIVED;
        } else {
            this.log.debug("Authentication already attempted");
            state = State.FAILED;
        }
        this.state = state;
    }
}
