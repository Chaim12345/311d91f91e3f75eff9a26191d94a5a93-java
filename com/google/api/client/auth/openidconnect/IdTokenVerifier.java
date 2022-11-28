package com.google.api.client.auth.openidconnect;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Preconditions;
import java.util.Collection;
import java.util.Collections;
@Beta
/* loaded from: classes2.dex */
public class IdTokenVerifier {
    public static final long DEFAULT_TIME_SKEW_SECONDS = 300;
    private final long acceptableTimeSkewSeconds;
    private final Collection<String> audience;
    private final Clock clock;
    private final Collection<String> issuers;

    @Beta
    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Clock f7979a = Clock.SYSTEM;

        /* renamed from: b  reason: collision with root package name */
        long f7980b = 300;

        /* renamed from: c  reason: collision with root package name */
        Collection f7981c;

        /* renamed from: d  reason: collision with root package name */
        Collection f7982d;

        public IdTokenVerifier build() {
            return new IdTokenVerifier(this);
        }

        public final long getAcceptableTimeSkewSeconds() {
            return this.f7980b;
        }

        public final Collection<String> getAudience() {
            return this.f7982d;
        }

        public final Clock getClock() {
            return this.f7979a;
        }

        public final String getIssuer() {
            Collection collection = this.f7981c;
            if (collection == null) {
                return null;
            }
            return (String) collection.iterator().next();
        }

        public final Collection<String> getIssuers() {
            return this.f7981c;
        }

        public Builder setAcceptableTimeSkewSeconds(long j2) {
            Preconditions.checkArgument(j2 >= 0);
            this.f7980b = j2;
            return this;
        }

        public Builder setAudience(Collection<String> collection) {
            this.f7982d = collection;
            return this;
        }

        public Builder setClock(Clock clock) {
            this.f7979a = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        public Builder setIssuer(String str) {
            return setIssuers(str == null ? null : Collections.singleton(str));
        }

        public Builder setIssuers(Collection<String> collection) {
            Preconditions.checkArgument(collection == null || !collection.isEmpty(), "Issuers must not be empty");
            this.f7981c = collection;
            return this;
        }
    }

    public IdTokenVerifier() {
        this(new Builder());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IdTokenVerifier(Builder builder) {
        this.clock = builder.f7979a;
        this.acceptableTimeSkewSeconds = builder.f7980b;
        Collection collection = builder.f7981c;
        this.issuers = collection == null ? null : Collections.unmodifiableCollection(collection);
        Collection collection2 = builder.f7982d;
        this.audience = collection2 != null ? Collections.unmodifiableCollection(collection2) : null;
    }

    public final long getAcceptableTimeSkewSeconds() {
        return this.acceptableTimeSkewSeconds;
    }

    public final Collection<String> getAudience() {
        return this.audience;
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final String getIssuer() {
        Collection<String> collection = this.issuers;
        if (collection == null) {
            return null;
        }
        return collection.iterator().next();
    }

    public final Collection<String> getIssuers() {
        return this.issuers;
    }

    public boolean verify(IdToken idToken) {
        Collection<String> collection;
        Collection<String> collection2 = this.issuers;
        return (collection2 == null || idToken.verifyIssuer(collection2)) && ((collection = this.audience) == null || idToken.verifyAudience(collection)) && idToken.verifyTime(this.clock.currentTimeMillis(), this.acceptableTimeSkewSeconds);
    }
}
