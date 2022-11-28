package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.util.Comparator;
/* loaded from: classes.dex */
public final /* synthetic */ class l implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ l f1174a = new l();

    private /* synthetic */ l() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int lambda$static$0;
        lambda$static$0 = OptionsBundle.lambda$static$0((Config.Option) obj, (Config.Option) obj2);
        return lambda$static$0;
    }
}
