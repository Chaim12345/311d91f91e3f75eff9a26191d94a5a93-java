package com.bumptech.glide.gifdecoder;

import androidx.annotation.ColorInt;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class GifHeader {
    public static final int NETSCAPE_LOOP_COUNT_DOES_NOT_EXIST = -1;
    public static final int NETSCAPE_LOOP_COUNT_FOREVER = 0;

    /* renamed from: d  reason: collision with root package name */
    GifFrame f4695d;

    /* renamed from: f  reason: collision with root package name */
    int f4697f;

    /* renamed from: g  reason: collision with root package name */
    int f4698g;

    /* renamed from: h  reason: collision with root package name */
    boolean f4699h;

    /* renamed from: i  reason: collision with root package name */
    int f4700i;

    /* renamed from: j  reason: collision with root package name */
    int f4701j;

    /* renamed from: k  reason: collision with root package name */
    int f4702k;
    @ColorInt

    /* renamed from: l  reason: collision with root package name */
    int f4703l;
    @ColorInt

    /* renamed from: a  reason: collision with root package name */
    int[] f4692a = null;

    /* renamed from: b  reason: collision with root package name */
    int f4693b = 0;

    /* renamed from: c  reason: collision with root package name */
    int f4694c = 0;

    /* renamed from: e  reason: collision with root package name */
    final List f4696e = new ArrayList();

    /* renamed from: m  reason: collision with root package name */
    int f4704m = -1;

    public int getHeight() {
        return this.f4698g;
    }

    public int getNumFrames() {
        return this.f4694c;
    }

    public int getStatus() {
        return this.f4693b;
    }

    public int getWidth() {
        return this.f4697f;
    }
}
