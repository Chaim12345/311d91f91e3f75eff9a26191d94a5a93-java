package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonParser;
/* loaded from: classes.dex */
public enum StreamReadFeature {
    AUTO_CLOSE_SOURCE(JsonParser.Feature.AUTO_CLOSE_SOURCE),
    STRICT_DUPLICATE_DETECTION(JsonParser.Feature.STRICT_DUPLICATE_DETECTION),
    IGNORE_UNDEFINED(JsonParser.Feature.IGNORE_UNDEFINED),
    INCLUDE_SOURCE_IN_LOCATION(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION);
    
    private final boolean _defaultState;
    private final JsonParser.Feature _mappedFeature;
    private final int _mask;

    StreamReadFeature(JsonParser.Feature feature) {
        this._mappedFeature = feature;
        this._mask = feature.getMask();
        this._defaultState = feature.enabledByDefault();
    }

    public static int collectDefaults() {
        StreamReadFeature[] values;
        int i2 = 0;
        for (StreamReadFeature streamReadFeature : values()) {
            if (streamReadFeature.enabledByDefault()) {
                i2 |= streamReadFeature.getMask();
            }
        }
        return i2;
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public boolean enabledIn(int i2) {
        return (i2 & this._mask) != 0;
    }

    public int getMask() {
        return this._mask;
    }

    public JsonParser.Feature mappedFeature() {
        return this._mappedFeature;
    }
}
