package androidx.camera.core.impl;

import android.util.ArrayMap;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class TagBundle {
    private static final TagBundle EMPTY_TAGBUNDLE = new TagBundle(new ArrayMap());

    /* renamed from: a  reason: collision with root package name */
    protected final Map f1151a;

    /* JADX INFO: Access modifiers changed from: protected */
    public TagBundle(@NonNull Map map) {
        this.f1151a = map;
    }

    @NonNull
    public static TagBundle create(@NonNull Pair<String, Integer> pair) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put((String) pair.first, (Integer) pair.second);
        return new TagBundle(arrayMap);
    }

    @NonNull
    public static TagBundle emptyBundle() {
        return EMPTY_TAGBUNDLE;
    }

    @NonNull
    public static TagBundle from(@NonNull TagBundle tagBundle) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : tagBundle.listKeys()) {
            arrayMap.put(str, tagBundle.getTag(str));
        }
        return new TagBundle(arrayMap);
    }

    @Nullable
    public Integer getTag(@NonNull String str) {
        return (Integer) this.f1151a.get(str);
    }

    @NonNull
    public Set<String> listKeys() {
        return this.f1151a.keySet();
    }
}
