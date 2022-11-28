package com.google.crypto.tink.shaded.protobuf;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class UnknownFieldSchema<T, B> {
    abstract void a(Object obj, int i2, int i3);

    abstract void b(Object obj, int i2, long j2);

    abstract void c(Object obj, int i2, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void d(Object obj, int i2, ByteString byteString);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void e(Object obj, int i2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object f(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object g(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int h(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int i(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void j(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object k(Object obj, Object obj2);

    final void l(Object obj, Reader reader) {
        while (reader.getFieldNumber() != Integer.MAX_VALUE && m(obj, reader)) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean m(Object obj, Reader reader) {
        int tag = reader.getTag();
        int tagFieldNumber = WireFormat.getTagFieldNumber(tag);
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            e(obj, tagFieldNumber, reader.readInt64());
            return true;
        } else if (tagWireType == 1) {
            b(obj, tagFieldNumber, reader.readFixed64());
            return true;
        } else if (tagWireType == 2) {
            d(obj, tagFieldNumber, reader.readBytes());
            return true;
        } else if (tagWireType != 3) {
            if (tagWireType != 4) {
                if (tagWireType == 5) {
                    a(obj, tagFieldNumber, reader.readFixed32());
                    return true;
                }
                throw InvalidProtocolBufferException.d();
            }
            return false;
        } else {
            Object n2 = n();
            int a2 = WireFormat.a(tagFieldNumber, 4);
            l(n2, reader);
            if (a2 == reader.getTag()) {
                c(obj, tagFieldNumber, r(n2));
                return true;
            }
            throw InvalidProtocolBufferException.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object n();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void o(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void p(Object obj, Object obj2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean q(Reader reader);

    abstract Object r(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void s(Object obj, Writer writer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void t(Object obj, Writer writer);
}
