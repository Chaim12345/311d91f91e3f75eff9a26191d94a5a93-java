package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.StructureKind;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class EnumDescriptor$elementDescriptors$2 extends Lambda implements Function0<SerialDescriptor[]> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f12425a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f12426b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ EnumDescriptor f12427c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumDescriptor$elementDescriptors$2(int i2, String str, EnumDescriptor enumDescriptor) {
        super(0);
        this.f12425a = i2;
        this.f12426b = str;
        this.f12427c = enumDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final SerialDescriptor[] invoke() {
        int i2 = this.f12425a;
        SerialDescriptor[] serialDescriptorArr = new SerialDescriptor[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            serialDescriptorArr[i3] = SerialDescriptorsKt.buildSerialDescriptor$default(this.f12426b + '.' + this.f12427c.getElementName(i3), StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], null, 8, null);
        }
        return serialDescriptorArr;
    }
}
