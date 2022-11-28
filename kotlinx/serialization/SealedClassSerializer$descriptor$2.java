package kotlinx.serialization;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Typography;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.SerialKind;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SealedClassSerializer$descriptor$2 extends Lambda implements Function0<SerialDescriptor> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f12412a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ SealedClassSerializer f12413b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ KSerializer[] f12414c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: kotlinx.serialization.SealedClassSerializer$descriptor$2$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ SealedClassSerializer f12415a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ KSerializer[] f12416b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SealedClassSerializer sealedClassSerializer, KSerializer[] kSerializerArr) {
            super(1);
            this.f12415a = sealedClassSerializer;
            this.f12416b = kSerializerArr;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClassSerialDescriptorBuilder classSerialDescriptorBuilder) {
            invoke2(classSerialDescriptorBuilder);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClassSerialDescriptorBuilder buildSerialDescriptor) {
            List<? extends Annotation> list;
            Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "type", BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE).getDescriptor(), null, false, 12, null);
            ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "value", SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.Sealed<" + ((Object) this.f12415a.getBaseClass().getSimpleName()) + Typography.greater, SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], new SealedClassSerializer$descriptor$2$1$elementDescriptor$1(this.f12416b)), null, false, 12, null);
            list = this.f12415a._annotations;
            buildSerialDescriptor.setAnnotations(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SealedClassSerializer$descriptor$2(String str, SealedClassSerializer sealedClassSerializer, KSerializer[] kSerializerArr) {
        super(0);
        this.f12412a = str;
        this.f12413b = sealedClassSerializer;
        this.f12414c = kSerializerArr;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final SerialDescriptor invoke() {
        return SerialDescriptorsKt.buildSerialDescriptor(this.f12412a, PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], new AnonymousClass1(this.f12413b, this.f12414c));
    }
}
