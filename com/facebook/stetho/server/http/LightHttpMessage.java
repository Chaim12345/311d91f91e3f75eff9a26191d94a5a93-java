package com.facebook.stetho.server.http;

import androidx.annotation.Nullable;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class LightHttpMessage {
    public final ArrayList<String> headerNames = new ArrayList<>();
    public final ArrayList<String> headerValues = new ArrayList<>();

    public void addHeader(String str, String str2) {
        this.headerNames.add(str);
        this.headerValues.add(str2);
    }

    @Nullable
    public String getFirstHeaderValue(String str) {
        int size = this.headerNames.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equals(this.headerNames.get(i2))) {
                return this.headerValues.get(i2);
            }
        }
        return null;
    }

    public void reset() {
        this.headerNames.clear();
        this.headerValues.clear();
    }
}
