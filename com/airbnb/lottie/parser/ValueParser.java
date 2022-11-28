package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
/* loaded from: classes.dex */
interface ValueParser<V> {
    V parse(JsonReader jsonReader, float f2);
}
