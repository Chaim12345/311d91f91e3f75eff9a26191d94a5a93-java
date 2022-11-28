package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;
/* loaded from: classes.dex */
public class SimpleBooleanResult implements JsonRpcResult {
    @JsonProperty(required = true)
    public boolean result;

    public SimpleBooleanResult() {
    }

    public SimpleBooleanResult(boolean z) {
        this.result = z;
    }
}
