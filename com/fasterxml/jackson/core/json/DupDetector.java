package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashSet;
/* loaded from: classes.dex */
public class DupDetector {

    /* renamed from: a  reason: collision with root package name */
    protected final Object f5180a;

    /* renamed from: b  reason: collision with root package name */
    protected String f5181b;

    /* renamed from: c  reason: collision with root package name */
    protected String f5182c;

    /* renamed from: d  reason: collision with root package name */
    protected HashSet f5183d;

    private DupDetector(Object obj) {
        this.f5180a = obj;
    }

    public static DupDetector rootDetector(JsonGenerator jsonGenerator) {
        return new DupDetector(jsonGenerator);
    }

    public static DupDetector rootDetector(JsonParser jsonParser) {
        return new DupDetector(jsonParser);
    }

    public DupDetector child() {
        return new DupDetector(this.f5180a);
    }

    public JsonLocation findLocation() {
        Object obj = this.f5180a;
        if (obj instanceof JsonParser) {
            return ((JsonParser) obj).getCurrentLocation();
        }
        return null;
    }

    public Object getSource() {
        return this.f5180a;
    }

    public boolean isDup(String str) {
        String str2 = this.f5181b;
        if (str2 == null) {
            this.f5181b = str;
            return false;
        } else if (str.equals(str2)) {
            return true;
        } else {
            String str3 = this.f5182c;
            if (str3 == null) {
                this.f5182c = str;
                return false;
            } else if (str.equals(str3)) {
                return true;
            } else {
                if (this.f5183d == null) {
                    HashSet hashSet = new HashSet(16);
                    this.f5183d = hashSet;
                    hashSet.add(this.f5181b);
                    this.f5183d.add(this.f5182c);
                }
                return !this.f5183d.add(str);
            }
        }
    }

    public void reset() {
        this.f5181b = null;
        this.f5182c = null;
        this.f5183d = null;
    }
}
