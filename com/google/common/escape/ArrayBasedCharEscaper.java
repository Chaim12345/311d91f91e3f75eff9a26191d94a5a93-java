package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;
@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class ArrayBasedCharEscaper extends CharEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final char safeMax;
    private final char safeMin;

    protected ArrayBasedCharEscaper(ArrayBasedEscaperMap arrayBasedEscaperMap, char c2, char c3) {
        Preconditions.checkNotNull(arrayBasedEscaperMap);
        char[][] b2 = arrayBasedEscaperMap.b();
        this.replacements = b2;
        this.replacementsLength = b2.length;
        if (c3 < c2) {
            c3 = 0;
            c2 = CharCompanionObject.MAX_VALUE;
        }
        this.safeMin = c2;
        this.safeMax = c3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArrayBasedCharEscaper(Map map, char c2, char c3) {
        this(ArrayBasedEscaperMap.create(map), c2, c3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.escape.CharEscaper
    public final char[] a(char c2) {
        char[] cArr;
        if (c2 >= this.replacementsLength || (cArr = this.replacements[c2]) == null) {
            if (c2 < this.safeMin || c2 > this.safeMax) {
                return c(c2);
            }
            return null;
        }
        return cArr;
    }

    protected abstract char[] c(char c2);

    @Override // com.google.common.escape.CharEscaper, com.google.common.escape.Escaper
    public final String escape(String str) {
        Preconditions.checkNotNull(str);
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if ((charAt < this.replacementsLength && this.replacements[charAt] != null) || charAt > this.safeMax || charAt < this.safeMin) {
                return b(str, i2);
            }
        }
        return str;
    }
}
