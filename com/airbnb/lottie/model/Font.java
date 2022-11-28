package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class Font {
    private final float ascent;
    private final String family;
    private final String name;
    private final String style;

    public Font(String str, String str2, String str3, float f2) {
        this.family = str;
        this.name = str2;
        this.style = str3;
        this.ascent = f2;
    }

    public String getFamily() {
        return this.family;
    }

    public String getName() {
        return this.name;
    }

    public String getStyle() {
        return this.style;
    }
}
