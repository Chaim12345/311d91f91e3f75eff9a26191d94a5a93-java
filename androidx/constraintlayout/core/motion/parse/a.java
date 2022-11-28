package androidx.constraintlayout.core.motion.parse;

import androidx.constraintlayout.core.motion.parse.KeyParser;
import androidx.constraintlayout.core.motion.utils.TypedValues;
/* loaded from: classes.dex */
public final /* synthetic */ class a implements KeyParser.DataType {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f1728a = new a();

    private /* synthetic */ a() {
    }

    @Override // androidx.constraintlayout.core.motion.parse.KeyParser.DataType
    public final int get(int i2) {
        return TypedValues.Attributes.getType(i2);
    }
}
