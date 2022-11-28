package kotlinx.serialization;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.SerialDescriptor;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class SealedClassSerializer$descriptor$2$1$elementDescriptor$1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ KSerializer[] f12417a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SealedClassSerializer$descriptor$2$1$elementDescriptor$1(KSerializer[] kSerializerArr) {
        super(1);
        this.f12417a = kSerializerArr;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
        invoke2(classSerialDescriptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull ClassSerialDescriptorBuilder buildSerialDescriptor) {
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        KSerializer[] kSerializerArr = this.f12417a;
        int length = kSerializerArr.length;
        int i2 = 0;
        while (i2 < length) {
            KSerializer kSerializer = kSerializerArr[i2];
            i2++;
            SerialDescriptor descriptor = kSerializer.getDescriptor();
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, descriptor.getSerialName(), descriptor, null, false, 12, null);
        }
    }
}
