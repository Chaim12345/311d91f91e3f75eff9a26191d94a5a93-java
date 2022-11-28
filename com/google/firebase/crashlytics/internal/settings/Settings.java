package com.google.firebase.crashlytics.internal.settings;
/* loaded from: classes2.dex */
public class Settings {
    public final int cacheDuration;
    public final long expiresAtMillis;
    public final FeatureFlagData featureFlagData;
    public final double onDemandBackoffBase;
    public final int onDemandBackoffStepDurationSeconds;
    public final double onDemandUploadRatePerMinute;
    public final SessionData sessionData;
    public final int settingsVersion;

    /* loaded from: classes2.dex */
    public static class FeatureFlagData {
        public final boolean collectAnrs;
        public final boolean collectReports;

        public FeatureFlagData(boolean z, boolean z2) {
            this.collectReports = z;
            this.collectAnrs = z2;
        }
    }

    /* loaded from: classes2.dex */
    public static class SessionData {
        public final int maxCompleteSessionsCount;
        public final int maxCustomExceptionEvents;

        public SessionData(int i2, int i3) {
            this.maxCustomExceptionEvents = i2;
            this.maxCompleteSessionsCount = i3;
        }
    }

    public Settings(long j2, SessionData sessionData, FeatureFlagData featureFlagData, int i2, int i3, double d2, double d3, int i4) {
        this.expiresAtMillis = j2;
        this.sessionData = sessionData;
        this.featureFlagData = featureFlagData;
        this.settingsVersion = i2;
        this.cacheDuration = i3;
        this.onDemandUploadRatePerMinute = d2;
        this.onDemandBackoffBase = d3;
        this.onDemandBackoffStepDurationSeconds = i4;
    }

    public boolean isExpired(long j2) {
        return this.expiresAtMillis < j2;
    }
}
