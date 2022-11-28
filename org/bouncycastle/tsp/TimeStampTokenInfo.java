package org.bouncycastle.tsp;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.tsp.Accuracy;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
/* loaded from: classes4.dex */
public class TimeStampTokenInfo {

    /* renamed from: a  reason: collision with root package name */
    TSTInfo f15094a;

    /* renamed from: b  reason: collision with root package name */
    Date f15095b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampTokenInfo(TSTInfo tSTInfo) {
        this.f15094a = tSTInfo;
        try {
            this.f15095b = tSTInfo.getGenTime().getDate();
        } catch (ParseException unused) {
            throw new TSPException("unable to parse genTime field");
        }
    }

    public Accuracy getAccuracy() {
        return this.f15094a.getAccuracy();
    }

    public byte[] getEncoded() {
        return this.f15094a.getEncoded();
    }

    public Extensions getExtensions() {
        return this.f15094a.getExtensions();
    }

    public Date getGenTime() {
        return this.f15095b;
    }

    public GenTimeAccuracy getGenTimeAccuracy() {
        if (getAccuracy() != null) {
            return new GenTimeAccuracy(getAccuracy());
        }
        return null;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.f15094a.getMessageImprint().getHashAlgorithm();
    }

    public ASN1ObjectIdentifier getMessageImprintAlgOID() {
        return this.f15094a.getMessageImprint().getHashAlgorithm().getAlgorithm();
    }

    public byte[] getMessageImprintDigest() {
        return this.f15094a.getMessageImprint().getHashedMessage();
    }

    public BigInteger getNonce() {
        if (this.f15094a.getNonce() != null) {
            return this.f15094a.getNonce().getValue();
        }
        return null;
    }

    public ASN1ObjectIdentifier getPolicy() {
        return this.f15094a.getPolicy();
    }

    public BigInteger getSerialNumber() {
        return this.f15094a.getSerialNumber().getValue();
    }

    public GeneralName getTsa() {
        return this.f15094a.getTsa();
    }

    public boolean isOrdered() {
        return this.f15094a.getOrdering().isTrue();
    }

    public TSTInfo toASN1Structure() {
        return this.f15094a;
    }

    public TSTInfo toTSTInfo() {
        return this.f15094a;
    }
}
