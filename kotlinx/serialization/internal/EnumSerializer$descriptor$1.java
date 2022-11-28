package kotlinx.serialization.internal;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.StructureKind;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class EnumSerializer$descriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ EnumSerializer f12428a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f12429b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumSerializer$descriptor$1(EnumSerializer enumSerializer, String str) {
        super(1);
        this.f12428a = enumSerializer;
        this.f12429b = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        invoke2(classSerialDescriptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull ClassSerialDescriptorBuilder buildSerialDescriptor) {
        Enum[] enumArr;
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        enumArr = this.f12428a.values;
        String str = this.f12429b;
        for (Enum r3 : enumArr) {
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, r3.name(), SerialDescriptorsKt.buildSerialDescriptor$default(str + '.' + r3.name(), StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], null, 8, null), null, false, 12, null);
        }
    }
}
