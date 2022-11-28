package androidx.constraintlayout.core.motion.parse;

import androidx.constraintlayout.core.motion.parse.KeyParser;
import androidx.constraintlayout.core.motion.utils.TypedValues;
/* loaded from: classes.dex */
public final /* synthetic */ class b implements KeyParser.Ids {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ b f1729a = new b();

    private /* synthetic */ b() {
    }

    @Override // androidx.constraintlayout.core.motion.parse.KeyParser.Ids
    public final int get(String str) {
        return TypedValues.Attributes.getId(str);
    }
}
