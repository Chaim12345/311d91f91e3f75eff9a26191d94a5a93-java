package kotlinx.serialization.internal;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
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
public final class ObjectSerializer$descriptor$2 extends Lambda implements Function0<SerialDescriptor> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f12433a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ObjectSerializer f12434b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: kotlinx.serialization.internal.ObjectSerializer$descriptor$2$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends Lambda implements Function1<ClassSerialDescriptorBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ObjectSerializer f12435a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ObjectSerializer objectSerializer) {
            super(1);
            this.f12435a = objectSerializer;
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
            list = this.f12435a._annotations;
            buildSerialDescriptor.setAnnotations(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ObjectSerializer$descriptor$2(String str, ObjectSerializer objectSerializer) {
        super(0);
        this.f12433a = str;
        this.f12434b = objectSerializer;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final SerialDescriptor invoke() {
        return SerialDescriptorsKt.buildSerialDescriptor(this.f12433a, StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], new AnonymousClass1(this.f12434b));
    }
}
