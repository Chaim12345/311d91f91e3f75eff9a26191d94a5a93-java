package kotlinx.serialization.json;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class JsonKt {
    @NotNull
    private static final String defaultDiscriminator = "type";
    @NotNull
    private static final String defaultIndent = "    ";

    @NotNull
    public static final Json Json(@NotNull Json from, @NotNull Function1<? super JsonBuilder, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        JsonBuilder jsonBuilder = new JsonBuilder(from);
        builderAction.invoke(jsonBuilder);
        return new JsonImpl(jsonBuilder.build$kotlinx_serialization_json(), jsonBuilder.getSerializersModule());
    }

    public static /* synthetic */ Json Json$default(Json json, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            json = Json.Default;
        }
        return Json(json, function1);
    }

    public static final /* synthetic */ <T> T decodeFromJsonElement(Json json, JsonElement json2) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(json2, "json");
        SerializersModule serializersModule = json.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) json.decodeFromJsonElement(SerializersKt.serializer(serializersModule, (KType) null), json2);
    }

    public static final /* synthetic */ <T> JsonElement encodeToJsonElement(Json json, T t2) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        SerializersModule serializersModule = json.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, ExifInterface.GPS_DIRECTION_TRUE);
        return json.encodeToJsonElement(SerializersKt.serializer(serializersModule, (KType) null), t2);
    }
}
