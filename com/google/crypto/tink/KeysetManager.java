package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Iterator;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public final class KeysetManager {
    @GuardedBy("this")
    private final Keyset.Builder keysetBuilder;

    private KeysetManager(Keyset.Builder builder) {
        this.keysetBuilder = builder;
    }

    private synchronized boolean keyIdExists(int i2) {
        boolean z;
        Iterator<Keyset.Key> it = this.keysetBuilder.getKeyList().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().getKeyId() == i2) {
                z = true;
                break;
            }
        }
        return z;
    }

    private synchronized Keyset.Key newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        KeyData newKeyData;
        int newKeyId;
        OutputPrefixType outputPrefixType;
        newKeyData = Registry.newKeyData(keyTemplate);
        newKeyId = newKeyId();
        outputPrefixType = keyTemplate.getOutputPrefixType();
        if (outputPrefixType == OutputPrefixType.UNKNOWN_PREFIX) {
            outputPrefixType = OutputPrefixType.TINK;
        }
        return Keyset.Key.newBuilder().setKeyData(newKeyData).setKeyId(newKeyId).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(outputPrefixType).build();
    }

    private synchronized int newKeyId() {
        int randPositiveInt;
        do {
            randPositiveInt = randPositiveInt();
        } while (keyIdExists(randPositiveInt));
        return randPositiveInt;
    }

    private static int randPositiveInt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        int i2 = 0;
        while (i2 == 0) {
            secureRandom.nextBytes(bArr);
            i2 = ((bArr[0] & Byte.MAX_VALUE) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return i2;
    }

    public static KeysetManager withEmptyKeyset() {
        return new KeysetManager(Keyset.newBuilder());
    }

    public static KeysetManager withKeysetHandle(KeysetHandle keysetHandle) {
        return new KeysetManager(keysetHandle.b().toBuilder());
    }

    public synchronized KeysetManager add(KeyTemplate keyTemplate) {
        addNewKey(keyTemplate.a(), false);
        return this;
    }

    @Deprecated
    public synchronized KeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        addNewKey(keyTemplate, false);
        return this;
    }

    @Deprecated
    public synchronized int addNewKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, boolean z) {
        Keyset.Key newKey;
        newKey = newKey(keyTemplate);
        this.keysetBuilder.addKey(newKey);
        if (z) {
            this.keysetBuilder.setPrimaryKeyId(newKey.getKeyId());
        }
        return newKey.getKeyId();
    }

    public synchronized KeysetManager delete(int i2) {
        if (i2 != this.keysetBuilder.getPrimaryKeyId()) {
            for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
                if (this.keysetBuilder.getKey(i3).getKeyId() == i2) {
                    this.keysetBuilder.removeKey(i3);
                }
            }
            throw new GeneralSecurityException("key not found: " + i2);
        }
        throw new GeneralSecurityException("cannot delete the primary key");
        return this;
    }

    public synchronized KeysetManager destroy(int i2) {
        if (i2 != this.keysetBuilder.getPrimaryKeyId()) {
            for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
                Keyset.Key key = this.keysetBuilder.getKey(i3);
                if (key.getKeyId() == i2) {
                    if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED && key.getStatus() != KeyStatusType.DESTROYED) {
                        throw new GeneralSecurityException("cannot destroy key with id " + i2 + " and status " + key.getStatus());
                    }
                    this.keysetBuilder.setKey(i3, key.toBuilder().setStatus(KeyStatusType.DESTROYED).clearKeyData().build());
                }
            }
            throw new GeneralSecurityException("key not found: " + i2);
        }
        throw new GeneralSecurityException("cannot destroy the primary key");
        return this;
    }

    public synchronized KeysetManager disable(int i2) {
        if (i2 != this.keysetBuilder.getPrimaryKeyId()) {
            for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
                Keyset.Key key = this.keysetBuilder.getKey(i3);
                if (key.getKeyId() == i2) {
                    if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED) {
                        throw new GeneralSecurityException("cannot disable key with id " + i2 + " and status " + key.getStatus());
                    }
                    this.keysetBuilder.setKey(i3, key.toBuilder().setStatus(KeyStatusType.DISABLED).build());
                }
            }
            throw new GeneralSecurityException("key not found: " + i2);
        }
        throw new GeneralSecurityException("cannot disable the primary key");
        return this;
    }

    public synchronized KeysetManager enable(int i2) {
        for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
            Keyset.Key key = this.keysetBuilder.getKey(i3);
            if (key.getKeyId() == i2) {
                KeyStatusType status = key.getStatus();
                KeyStatusType keyStatusType = KeyStatusType.ENABLED;
                if (status != keyStatusType && key.getStatus() != KeyStatusType.DISABLED) {
                    throw new GeneralSecurityException("cannot enable key with id " + i2 + " and status " + key.getStatus());
                }
                this.keysetBuilder.setKey(i3, key.toBuilder().setStatus(keyStatusType).build());
            }
        }
        throw new GeneralSecurityException("key not found: " + i2);
        return this;
    }

    public synchronized KeysetHandle getKeysetHandle() {
        return KeysetHandle.a(this.keysetBuilder.build());
    }

    @Deprecated
    public synchronized KeysetManager promote(int i2) {
        return setPrimary(i2);
    }

    @Deprecated
    public synchronized KeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        addNewKey(keyTemplate, true);
        return this;
    }

    public synchronized KeysetManager setPrimary(int i2) {
        for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
            Keyset.Key key = this.keysetBuilder.getKey(i3);
            if (key.getKeyId() == i2) {
                if (!key.getStatus().equals(KeyStatusType.ENABLED)) {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i2);
                } else {
                    this.keysetBuilder.setPrimaryKeyId(i2);
                }
            }
        }
        throw new GeneralSecurityException("key not found: " + i2);
        return this;
    }
}
