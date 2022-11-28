package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
abstract class HashMultimapGwtSerializationDependencies<K, V> extends AbstractSetMultimap<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMultimapGwtSerializationDependencies(Map map) {
        super(map);
    }
}
