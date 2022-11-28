package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.EOFException;
import java.io.InputStream;
/* loaded from: classes.dex */
public interface InputAccessor {

    /* loaded from: classes.dex */
    public static class Std implements InputAccessor {

        /* renamed from: a  reason: collision with root package name */
        protected final InputStream f5144a;

        /* renamed from: b  reason: collision with root package name */
        protected final byte[] f5145b;

        /* renamed from: c  reason: collision with root package name */
        protected final int f5146c;

        /* renamed from: d  reason: collision with root package name */
        protected int f5147d;

        /* renamed from: e  reason: collision with root package name */
        protected int f5148e;

        public Std(InputStream inputStream, byte[] bArr) {
            this.f5144a = inputStream;
            this.f5145b = bArr;
            this.f5146c = 0;
            this.f5148e = 0;
            this.f5147d = 0;
        }

        public Std(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        public Std(byte[] bArr, int i2, int i3) {
            this.f5144a = null;
            this.f5145b = bArr;
            this.f5148e = i2;
            this.f5146c = i2;
            this.f5147d = i2 + i3;
        }

        public DataFormatMatcher createMatcher(JsonFactory jsonFactory, MatchStrength matchStrength) {
            InputStream inputStream = this.f5144a;
            byte[] bArr = this.f5145b;
            int i2 = this.f5146c;
            return new DataFormatMatcher(inputStream, bArr, i2, this.f5147d - i2, jsonFactory, matchStrength);
        }

        @Override // com.fasterxml.jackson.core.format.InputAccessor
        public boolean hasMoreBytes() {
            int read;
            int i2 = this.f5148e;
            if (i2 < this.f5147d) {
                return true;
            }
            InputStream inputStream = this.f5144a;
            if (inputStream == null) {
                return false;
            }
            byte[] bArr = this.f5145b;
            int length = bArr.length - i2;
            if (length >= 1 && (read = inputStream.read(bArr, i2, length)) > 0) {
                this.f5147d += read;
                return true;
            }
            return false;
        }

        @Override // com.fasterxml.jackson.core.format.InputAccessor
        public byte nextByte() {
            if (this.f5148e < this.f5147d || hasMoreBytes()) {
                byte[] bArr = this.f5145b;
                int i2 = this.f5148e;
                this.f5148e = i2 + 1;
                return bArr[i2];
            }
            throw new EOFException("Failed auto-detect: could not read more than " + this.f5148e + " bytes (max buffer size: " + this.f5145b.length + ")");
        }

        @Override // com.fasterxml.jackson.core.format.InputAccessor
        public void reset() {
            this.f5148e = this.f5146c;
        }
    }

    boolean hasMoreBytes();

    byte nextByte();

    void reset();
}
