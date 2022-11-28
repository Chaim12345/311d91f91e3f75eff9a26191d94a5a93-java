package org.bouncycastle.jsse.provider;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.tls.SessionID;
import org.bouncycastle.tls.TlsSession;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLSessionContext implements SSLSessionContext {
    private static final Logger LOG = Logger.getLogger(ProvSSLSessionContext.class.getName());
    private static final int provSessionCacheSize = PropertyUtils.c("javax.net.ssl.sessionCacheSize", 20480, 0, Integer.MAX_VALUE);

    /* renamed from: d  reason: collision with root package name */
    protected final ContextData f13963d;

    /* renamed from: a  reason: collision with root package name */
    protected final Map f13960a = new LinkedHashMap<SessionID, SessionEntry>(16, 0.75f, true) { // from class: org.bouncycastle.jsse.provider.ProvSSLSessionContext.1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<SessionID, SessionEntry> entry) {
            boolean z = ProvSSLSessionContext.this.f13964e > 0 && size() > ProvSSLSessionContext.this.f13964e;
            if (z) {
                ProvSSLSessionContext.this.removeSessionByPeer(entry.getValue());
            }
            return z;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    protected final Map f13961b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    protected final ReferenceQueue f13962c = new ReferenceQueue();

    /* renamed from: e  reason: collision with root package name */
    protected int f13964e = provSessionCacheSize;

    /* renamed from: f  reason: collision with root package name */
    protected int f13965f = 86400;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class SessionEntry extends SoftReference<ProvSSLSession> {
        private final String peerKey;
        private final SessionID sessionID;

        SessionEntry(SessionID sessionID, ProvSSLSession provSSLSession, ReferenceQueue referenceQueue) {
            super(provSSLSession, referenceQueue);
            if (sessionID == null || provSSLSession == null || referenceQueue == null) {
                throw null;
            }
            this.sessionID = sessionID;
            this.peerKey = ProvSSLSessionContext.makePeerKey(provSSLSession);
        }

        public String getPeerKey() {
            return this.peerKey;
        }

        public SessionID getSessionID() {
            return this.sessionID;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionContext(ContextData contextData) {
        this.f13963d = contextData;
    }

    private ProvSSLSession accessSession(SessionEntry sessionEntry) {
        if (sessionEntry != null) {
            ProvSSLSession provSSLSession = sessionEntry.get();
            if (provSSLSession != null) {
                long currentTimeMillis = System.currentTimeMillis();
                if (!invalidateIfCreatedBefore(sessionEntry, getCreationTimeLimit(currentTimeMillis))) {
                    provSSLSession.a(currentTimeMillis);
                    return provSSLSession;
                }
            }
            removeSession(sessionEntry);
            return null;
        }
        return null;
    }

    private long getCreationTimeLimit(long j2) {
        int i2 = this.f13965f;
        if (i2 < 1) {
            return Long.MIN_VALUE;
        }
        return j2 - (i2 * 1000);
    }

    private boolean invalidateIfCreatedBefore(SessionEntry sessionEntry, long j2) {
        ProvSSLSession provSSLSession = sessionEntry.get();
        if (provSSLSession == null) {
            return true;
        }
        if (provSSLSession.getCreationTime() < j2) {
            provSSLSession.i();
        }
        return !provSSLSession.isValid();
    }

    private static String makePeerKey(String str, int i2) {
        if (str == null || i2 < 0) {
            return null;
        }
        return (str + AbstractJsonLexerKt.COLON + Integer.toString(i2)).toLowerCase(Locale.ENGLISH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String makePeerKey(ProvSSLSession provSSLSession) {
        if (provSSLSession == null) {
            return null;
        }
        return makePeerKey(provSSLSession.getPeerHost(), provSSLSession.getPeerPort());
    }

    private static SessionID makeSessionID(byte[] bArr) {
        if (TlsUtils.isNullOrEmpty(bArr)) {
            return null;
        }
        return new SessionID(bArr);
    }

    private static <K, V> void mapAdd(Map<K, V> map, K k2, V v) {
        if (map == null || v == null) {
            throw null;
        }
        if (k2 != null) {
            map.put(k2, v);
        }
    }

    private static <K, V> V mapGet(Map<K, V> map, K k2) {
        Objects.requireNonNull(map);
        if (k2 == null) {
            return null;
        }
        return map.get(k2);
    }

    private static <K, V> V mapRemove(Map<K, V> map, K k2) {
        Objects.requireNonNull(map);
        if (k2 == null) {
            return null;
        }
        return map.remove(k2);
    }

    private static <K, V> boolean mapRemove(Map<K, V> map, K k2, V v) {
        if (map == null || v == null) {
            throw null;
        }
        if (k2 != null) {
            V remove = map.remove(k2);
            if (remove == v) {
                return true;
            }
            if (remove != null) {
                map.put(k2, remove);
                return false;
            }
            return false;
        }
        return false;
    }

    private void processQueue() {
        int i2 = 0;
        while (true) {
            SessionEntry sessionEntry = (SessionEntry) this.f13962c.poll();
            if (sessionEntry == null) {
                break;
            }
            removeSession(sessionEntry);
            i2++;
        }
        if (i2 > 0) {
            Logger logger = LOG;
            logger.fine("Processed " + i2 + " session entries (soft references) from the reference queue");
        }
    }

    private void removeAllExpiredSessions() {
        processQueue();
        long creationTimeLimit = getCreationTimeLimit(System.currentTimeMillis());
        Iterator it = this.f13960a.values().iterator();
        while (it.hasNext()) {
            SessionEntry sessionEntry = (SessionEntry) it.next();
            if (invalidateIfCreatedBefore(sessionEntry, creationTimeLimit)) {
                it.remove();
                removeSessionByPeer(sessionEntry);
            }
        }
    }

    private void removeSession(SessionEntry sessionEntry) {
        mapRemove(this.f13960a, sessionEntry.getSessionID(), sessionEntry);
        removeSessionByPeer(sessionEntry);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeSessionByPeer(SessionEntry sessionEntry) {
        return mapRemove(this.f13961b, sessionEntry.getPeerKey(), sessionEntry);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JcaTlsCrypto c() {
        return this.f13963d.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLContextSpi d() {
        return this.f13963d.c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ProvSSLSession e(String str, int i2) {
        ProvSSLSession accessSession;
        processQueue();
        SessionEntry sessionEntry = (SessionEntry) mapGet(this.f13961b, makePeerKey(str, i2));
        accessSession = accessSession(sessionEntry);
        if (accessSession != null) {
            this.f13960a.get(sessionEntry.getSessionID());
        }
        return accessSession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ProvSSLSession f(byte[] bArr) {
        processQueue();
        return accessSession((SessionEntry) mapGet(this.f13960a, makeSessionID(bArr)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void g(byte[] bArr) {
        SessionEntry sessionEntry = (SessionEntry) mapRemove(this.f13960a, makeSessionID(bArr));
        if (sessionEntry != null) {
            removeSessionByPeer(sessionEntry);
        }
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized Enumeration<byte[]> getIds() {
        ArrayList arrayList;
        removeAllExpiredSessions();
        arrayList = new ArrayList(this.f13960a.size());
        for (SessionID sessionID : this.f13960a.keySet()) {
            arrayList.add(sessionID.getBytes());
        }
        return Collections.enumeration(arrayList);
    }

    @Override // javax.net.ssl.SSLSessionContext
    public SSLSession getSession(byte[] bArr) {
        Objects.requireNonNull(bArr, "'sessionID' cannot be null");
        return f(bArr);
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized int getSessionCacheSize() {
        return this.f13964e;
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized int getSessionTimeout() {
        return this.f13965f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ProvSSLSession h(String str, int i2, TlsSession tlsSession, JsseSessionParameters jsseSessionParameters, boolean z) {
        processQueue();
        if (!z) {
            return new ProvSSLSession(this, str, i2, tlsSession, jsseSessionParameters);
        }
        SessionID makeSessionID = makeSessionID(tlsSession.getSessionID());
        SessionEntry sessionEntry = (SessionEntry) mapGet(this.f13960a, makeSessionID);
        ProvSSLSession provSSLSession = sessionEntry == null ? null : sessionEntry.get();
        if (provSSLSession == null || provSSLSession.m() != tlsSession) {
            ProvSSLSession provSSLSession2 = new ProvSSLSession(this, str, i2, tlsSession, jsseSessionParameters);
            if (makeSessionID != null) {
                sessionEntry = new SessionEntry(makeSessionID, provSSLSession2, this.f13962c);
                this.f13960a.put(makeSessionID, sessionEntry);
            }
            provSSLSession = provSSLSession2;
        }
        if (sessionEntry != null) {
            mapAdd(this.f13961b, sessionEntry.getPeerKey(), sessionEntry);
        }
        return provSSLSession;
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized void setSessionCacheSize(int i2) {
        int size;
        if (this.f13964e == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("'size' cannot be < 0");
        }
        this.f13964e = i2;
        removeAllExpiredSessions();
        if (this.f13964e > 0 && (size = this.f13960a.size()) > this.f13964e) {
            Iterator it = this.f13960a.values().iterator();
            for (size = this.f13960a.size(); it.hasNext() && size > this.f13964e; size--) {
                it.remove();
                removeSessionByPeer((SessionEntry) it.next());
            }
        }
    }

    @Override // javax.net.ssl.SSLSessionContext
    public synchronized void setSessionTimeout(int i2) {
        if (this.f13965f == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("'seconds' cannot be < 0");
        }
        this.f13965f = i2;
        removeAllExpiredSessions();
    }
}
