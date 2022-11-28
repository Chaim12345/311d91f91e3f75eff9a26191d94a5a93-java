package org.bouncycastle.mime;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes4.dex */
public abstract class MimeWriter {

    /* renamed from: a  reason: collision with root package name */
    protected final Headers f14359a;

    /* JADX INFO: Access modifiers changed from: protected */
    public MimeWriter(Headers headers) {
        this.f14359a = headers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List a(Map map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (String str : map.keySet()) {
            arrayList.add(str + ": " + ((String) map.get(str)));
        }
        return arrayList;
    }

    public abstract OutputStream getContentStream();

    public Headers getHeaders() {
        return this.f14359a;
    }
}
