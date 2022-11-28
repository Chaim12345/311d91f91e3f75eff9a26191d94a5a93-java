package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.FieldSet.FieldDescriptorLite;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class ExtensionSchema<T extends FieldSet.FieldDescriptorLite<T>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int a(Map.Entry entry);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object b(ExtensionRegistryLite extensionRegistryLite, MessageLite messageLite, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract FieldSet c(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract FieldSet d(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean e(MessageLite messageLite);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void f(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object g(Reader reader, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet, Object obj2, UnknownFieldSchema unknownFieldSchema);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void h(Reader reader, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void i(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void j(Writer writer, Map.Entry entry);
}
