package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.model.v2.State;
import java.lang.annotation.Annotation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SealedClassSerializer;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class State$Companion$$cachedSerializer$delegate$1 extends Lambda implements Function0<KSerializer<Object>> {
    public static final State$Companion$$cachedSerializer$delegate$1 INSTANCE = new State$Companion$$cachedSerializer$delegate$1();

    State$Companion$$cachedSerializer$delegate$1() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final KSerializer<Object> invoke() {
        return new SealedClassSerializer("com.appmattus.certificatetransparency.internal.loglist.model.v2.State", Reflection.getOrCreateKotlinClass(State.class), new KClass[]{Reflection.getOrCreateKotlinClass(State.Pending.class), Reflection.getOrCreateKotlinClass(State.Qualified.class), Reflection.getOrCreateKotlinClass(State.Usable.class), Reflection.getOrCreateKotlinClass(State.ReadOnly.class), Reflection.getOrCreateKotlinClass(State.Retired.class), Reflection.getOrCreateKotlinClass(State.Rejected.class)}, new KSerializer[]{State$Pending$$serializer.INSTANCE, State$Qualified$$serializer.INSTANCE, State$Usable$$serializer.INSTANCE, State$ReadOnly$$serializer.INSTANCE, State$Retired$$serializer.INSTANCE, State$Rejected$$serializer.INSTANCE}, new Annotation[0]);
    }
}
