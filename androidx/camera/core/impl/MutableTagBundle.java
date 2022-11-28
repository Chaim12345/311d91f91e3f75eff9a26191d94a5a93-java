package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.annotation.NonNull;
import java.util.Map;
/* loaded from: classes.dex */
public class MutableTagBundle extends TagBundle {
    private MutableTagBundle(Map<String, Integer> map) {
        super(map);
    }

    @NonNull
    public static MutableTagBundle create() {
        return new MutableTagBundle(new ArrayMap());
    }

    @NonNull
    public static MutableTagBundle from(@NonNull TagBundle tagBundle) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : tagBundle.listKeys()) {
            arrayMap.put(str, tagBundle.getTag(str));
        }
        return new MutableTagBundle(arrayMap);
    }

    public void addTagBundle(@NonNull TagBundle tagBundle) {
        Map map;
        Map map2 = this.f1151a;
        if (map2 == null || (map = tagBundle.f1151a) == null) {
            return;
        }
        map2.putAll(map);
    }

    public void putTag(@NonNull String str, @NonNull Integer num) {
        this.f1151a.put(str, num);
    }
}
