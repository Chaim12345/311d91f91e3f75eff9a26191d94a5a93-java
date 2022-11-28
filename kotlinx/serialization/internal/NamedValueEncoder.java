package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
@InternalSerializationApi
/* loaded from: classes3.dex */
public abstract class NamedValueEncoder extends TaggedEncoder<String> {
    @NotNull
    protected String f(@NotNull String parentName, @NotNull String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        if (parentName.length() == 0) {
            return childName;
        }
        return parentName + '.' + childName;
    }

    @NotNull
    protected String g(@NotNull SerialDescriptor descriptor, int i2) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return descriptor.getElementName(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    @NotNull
    /* renamed from: h */
    public final String getTag(@NotNull SerialDescriptor serialDescriptor, int i2) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        return i(g(serialDescriptor, i2));
    }

    @NotNull
    protected final String i(@NotNull String nestedName) {
        Intrinsics.checkNotNullParameter(nestedName, "nestedName");
        String str = (String) c();
        if (str == null) {
            str = "";
        }
        return f(str, nestedName);
    }
}
