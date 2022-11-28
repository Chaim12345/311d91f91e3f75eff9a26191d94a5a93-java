package com.google.api.client.testing.util;

import com.google.api.client.util.BackOff;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
@Beta
/* loaded from: classes2.dex */
public class MockBackOff implements BackOff {
    private long backOffMillis;
    private int maxTries = 10;
    private int numTries;

    public final int getMaxTries() {
        return this.maxTries;
    }

    public final int getNumberOfTries() {
        return this.numTries;
    }

    @Override // com.google.api.client.util.BackOff
    public long nextBackOffMillis() {
        int i2 = this.numTries;
        if (i2 < this.maxTries) {
            long j2 = this.backOffMillis;
            if (j2 != -1) {
                this.numTries = i2 + 1;
                return j2;
            }
        }
        return -1L;
    }

    @Override // com.google.api.client.util.BackOff
    public void reset() {
        this.numTries = 0;
    }

    public MockBackOff setBackOffMillis(long j2) {
        Preconditions.checkArgument(j2 == -1 || j2 >= 0);
        this.backOffMillis = j2;
        return this;
    }

    public MockBackOff setMaxTries(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        this.maxTries = i2;
        return this;
    }
}
