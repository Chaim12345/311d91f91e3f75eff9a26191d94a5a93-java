package org.apache.http.impl.auth;

import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthOption;
import org.apache.http.auth.AuthProtocolState;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.ContextAwareAuthScheme;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthenticationStrategy;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Asserts;
/* loaded from: classes3.dex */
public class HttpAuthenticator {
    private final Log log;

    /* renamed from: org.apache.http.impl.auth.HttpAuthenticator$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$http$auth$AuthProtocolState;

        static {
            int[] iArr = new int[AuthProtocolState.values().length];
            $SwitchMap$org$apache$http$auth$AuthProtocolState = iArr;
            try {
                iArr[AuthProtocolState.CHALLENGED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.HANDSHAKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.FAILURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$apache$http$auth$AuthProtocolState[AuthProtocolState.UNCHALLENGED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public HttpAuthenticator() {
        this(null);
    }

    public HttpAuthenticator(Log log) {
        this.log = log == null ? LogFactory.getLog(getClass()) : log;
    }

    private Header doAuth(AuthScheme authScheme, Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return authScheme instanceof ContextAwareAuthScheme ? ((ContextAwareAuthScheme) authScheme).authenticate(credentials, httpRequest, httpContext) : authScheme.authenticate(credentials, httpRequest);
    }

    private void ensureAuthScheme(AuthScheme authScheme) {
        Asserts.notNull(authScheme, "Auth scheme");
    }

    public void generateAuthResponse(HttpRequest httpRequest, AuthState authState, HttpContext httpContext) {
        AuthScheme authScheme = authState.getAuthScheme();
        Credentials credentials = authState.getCredentials();
        int i2 = AnonymousClass1.$SwitchMap$org$apache$http$auth$AuthProtocolState[authState.getState().ordinal()];
        if (i2 == 1) {
            Queue<AuthOption> authOptions = authState.getAuthOptions();
            if (authOptions != null) {
                while (!authOptions.isEmpty()) {
                    AuthOption remove = authOptions.remove();
                    AuthScheme authScheme2 = remove.getAuthScheme();
                    Credentials credentials2 = remove.getCredentials();
                    authState.update(authScheme2, credentials2);
                    if (this.log.isDebugEnabled()) {
                        Log log = this.log;
                        log.debug("Generating response to an authentication challenge using " + authScheme2.getSchemeName() + " scheme");
                    }
                    try {
                        httpRequest.addHeader(doAuth(authScheme2, credentials2, httpRequest, httpContext));
                        return;
                    } catch (AuthenticationException e2) {
                        if (this.log.isWarnEnabled()) {
                            Log log2 = this.log;
                            log2.warn(authScheme2 + " authentication error: " + e2.getMessage());
                        }
                    }
                }
                return;
            }
            ensureAuthScheme(authScheme);
        } else if (i2 == 3) {
            ensureAuthScheme(authScheme);
            if (authScheme.isConnectionBased()) {
                return;
            }
        } else if (i2 == 4) {
            return;
        }
        if (authScheme != null) {
            try {
                httpRequest.addHeader(doAuth(authScheme, credentials, httpRequest, httpContext));
            } catch (AuthenticationException e3) {
                if (this.log.isErrorEnabled()) {
                    Log log3 = this.log;
                    log3.error(authScheme + " authentication error: " + e3.getMessage());
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00b7 A[Catch: MalformedChallengeException -> 0x00e5, TryCatch #0 {MalformedChallengeException -> 0x00e5, blocks: (B:3:0x0001, B:5:0x0009, B:6:0x0023, B:8:0x002d, B:10:0x0035, B:35:0x00b1, B:37:0x00b7, B:39:0x00bd, B:41:0x00c5, B:42:0x00db, B:27:0x0072, B:29:0x0084, B:31:0x0094, B:33:0x00ab, B:22:0x0056, B:24:0x005c), top: B:51:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean handleAuthChallenge(HttpHost httpHost, HttpResponse httpResponse, AuthenticationStrategy authenticationStrategy, AuthState authState, HttpContext httpContext) {
        Queue<AuthOption> select;
        try {
            if (this.log.isDebugEnabled()) {
                Log log = this.log;
                log.debug(httpHost.toHostString() + " requested authentication");
            }
            Map<String, Header> challenges = authenticationStrategy.getChallenges(httpHost, httpResponse, httpContext);
            if (challenges.isEmpty()) {
                this.log.debug("Response contains no authentication challenges");
                return false;
            }
            AuthScheme authScheme = authState.getAuthScheme();
            int i2 = AnonymousClass1.$SwitchMap$org$apache$http$auth$AuthProtocolState[authState.getState().ordinal()];
            if (i2 != 1 && i2 != 2) {
                if (i2 != 3) {
                    if (i2 == 4) {
                        return false;
                    }
                    if (i2 != 5) {
                        select = authenticationStrategy.select(challenges, httpHost, httpResponse, httpContext);
                        if (select != null || select.isEmpty()) {
                            return false;
                        }
                        if (this.log.isDebugEnabled()) {
                            Log log2 = this.log;
                            log2.debug("Selected authentication options: " + select);
                        }
                        authState.setState(AuthProtocolState.CHALLENGED);
                        authState.update(select);
                        return true;
                    }
                }
                authState.reset();
                select = authenticationStrategy.select(challenges, httpHost, httpResponse, httpContext);
                if (select != null) {
                }
                return false;
            } else if (authScheme == null) {
                this.log.debug("Auth scheme is null");
                authenticationStrategy.authFailed(httpHost, null, httpContext);
                authState.reset();
                authState.setState(AuthProtocolState.FAILURE);
                return false;
            }
            if (authScheme != null) {
                Header header = challenges.get(authScheme.getSchemeName().toLowerCase(Locale.ROOT));
                if (header != null) {
                    this.log.debug("Authorization challenge processed");
                    authScheme.processChallenge(header);
                    if (!authScheme.isComplete()) {
                        authState.setState(AuthProtocolState.HANDSHAKE);
                        return true;
                    }
                    this.log.debug("Authentication failed");
                    authenticationStrategy.authFailed(httpHost, authState.getAuthScheme(), httpContext);
                    authState.reset();
                    authState.setState(AuthProtocolState.FAILURE);
                    return false;
                }
                authState.reset();
            }
            select = authenticationStrategy.select(challenges, httpHost, httpResponse, httpContext);
            if (select != null) {
            }
            return false;
        } catch (MalformedChallengeException e2) {
            if (this.log.isWarnEnabled()) {
                Log log3 = this.log;
                log3.warn("Malformed challenge: " + e2.getMessage());
            }
            authState.reset();
            return false;
        }
    }

    public boolean isAuthenticationRequested(HttpHost httpHost, HttpResponse httpResponse, AuthenticationStrategy authenticationStrategy, AuthState authState, HttpContext httpContext) {
        if (authenticationStrategy.isAuthenticationRequested(httpHost, httpResponse, httpContext)) {
            this.log.debug("Authentication required");
            if (authState.getState() == AuthProtocolState.SUCCESS) {
                authenticationStrategy.authFailed(httpHost, authState.getAuthScheme(), httpContext);
            }
            return true;
        }
        int i2 = AnonymousClass1.$SwitchMap$org$apache$http$auth$AuthProtocolState[authState.getState().ordinal()];
        if (i2 == 1 || i2 == 2) {
            this.log.debug("Authentication succeeded");
            authState.setState(AuthProtocolState.SUCCESS);
            authenticationStrategy.authSucceeded(httpHost, authState.getAuthScheme(), httpContext);
            return false;
        } else if (i2 != 3) {
            authState.setState(AuthProtocolState.UNCHALLENGED);
            return false;
        } else {
            return false;
        }
    }
}
