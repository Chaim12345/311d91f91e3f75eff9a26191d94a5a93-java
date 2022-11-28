package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import java.util.Map;
/* loaded from: classes.dex */
class EngineKeyFactory {
    /* JADX INFO: Access modifiers changed from: package-private */
    public EngineKey a(Object obj, Key key, int i2, int i3, Map map, Class cls, Class cls2, Options options) {
        return new EngineKey(obj, key, i2, i3, map, cls, cls2, options);
    }
}
