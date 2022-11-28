package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes2.dex */
final class ParseRequest {

    /* renamed from: a  reason: collision with root package name */
    final String f9367a;

    /* renamed from: b  reason: collision with root package name */
    final int f9368b;

    private ParseRequest(String str, int i2) {
        this.f9367a = str;
        this.f9368b = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParseRequest a(String str) {
        if (str.length() != 0) {
            char charAt = str.charAt(0);
            int i2 = 16;
            if (str.startsWith("0x") || str.startsWith("0X")) {
                str = str.substring(2);
            } else if (charAt == '#') {
                str = str.substring(1);
            } else if (charAt != '0' || str.length() <= 1) {
                i2 = 10;
            } else {
                str = str.substring(1);
                i2 = 8;
            }
            return new ParseRequest(str, i2);
        }
        throw new NumberFormatException("empty string");
    }
}
