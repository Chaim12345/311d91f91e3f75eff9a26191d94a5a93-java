package com.android.volley;
/* loaded from: classes.dex */
public class DefaultRetryPolicy implements RetryPolicy {
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 2500;
    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;

    public DefaultRetryPolicy() {
        this(DEFAULT_TIMEOUT_MS, 1, 1.0f);
    }

    public DefaultRetryPolicy(int i2, int i3, float f2) {
        this.mCurrentTimeoutMs = i2;
        this.mMaxNumRetries = i3;
        this.mBackoffMultiplier = f2;
    }

    protected boolean a() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }

    public float getBackoffMultiplier() {
        return this.mBackoffMultiplier;
    }

    @Override // com.android.volley.RetryPolicy
    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    @Override // com.android.volley.RetryPolicy
    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }

    @Override // com.android.volley.RetryPolicy
    public void retry(VolleyError volleyError) {
        this.mCurrentRetryCount++;
        int i2 = this.mCurrentTimeoutMs;
        this.mCurrentTimeoutMs = i2 + ((int) (i2 * this.mBackoffMultiplier));
        if (!a()) {
            throw volleyError;
        }
    }
}
