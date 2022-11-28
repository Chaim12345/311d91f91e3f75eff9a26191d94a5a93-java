package com.appmattus.certificatetransparency.internal.loglist.parser;

import com.appmattus.certificatetransparency.internal.loglist.LogListJsonBadFormat;
import com.appmattus.certificatetransparency.internal.loglist.LogServerInvalidKey;
import com.appmattus.certificatetransparency.internal.loglist.model.v2.Log;
import com.appmattus.certificatetransparency.internal.loglist.model.v2.LogListV2;
import com.appmattus.certificatetransparency.internal.loglist.model.v2.Operator;
import com.appmattus.certificatetransparency.internal.loglist.model.v2.State;
import com.appmattus.certificatetransparency.internal.utils.Base64;
import com.appmattus.certificatetransparency.internal.utils.PublicKeyFactory;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import com.appmattus.certificatetransparency.loglist.LogServer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class LogListJsonParserV2 implements LogListJsonParser {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Json json = JsonKt.Json$default(null, LogListJsonParserV2$Companion$json$1.INSTANCE, 1, null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final LogListResult buildLogServerList(LogListV2 logListV2) {
        int collectionSizeOrDefault;
        List<Operator> operators = logListV2.getOperators();
        ArrayList arrayList = new ArrayList();
        for (Operator operator : operators) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, operator.getLogs());
        }
        ArrayList<Log> arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            Log log = (Log) obj;
            if (!(log.getState() == null || (log.getState() instanceof State.Pending) || (log.getState() instanceof State.Rejected))) {
                arrayList2.add(obj);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
        for (Log log2 : arrayList2) {
            try {
                arrayList3.add(new LogServer(PublicKeyFactory.INSTANCE.fromByteArray(Base64.INSTANCE.decode(log2.getKey())), ((log2.getState() instanceof State.Retired) || (log2.getState() instanceof State.ReadOnly)) ? Long.valueOf(log2.getState().getTimestamp()) : null));
            } catch (IllegalArgumentException e2) {
                return new LogServerInvalidKey(e2, log2.getKey());
            } catch (NoSuchAlgorithmException e3) {
                return new LogServerInvalidKey(e3, log2.getKey());
            } catch (InvalidKeySpecException e4) {
                return new LogServerInvalidKey(e4, log2.getKey());
            }
        }
        return new LogListResult.Valid(arrayList3);
    }

    @Override // com.appmattus.certificatetransparency.internal.loglist.parser.LogListJsonParser
    @NotNull
    public LogListResult parseJson(@NotNull String logListJson) {
        Intrinsics.checkNotNullParameter(logListJson, "logListJson");
        try {
            return buildLogServerList((LogListV2) json.decodeFromString(LogListV2.Companion.serializer(), logListJson));
        } catch (SerializationException e2) {
            return new LogListJsonBadFormat(e2);
        }
    }
}
