package org.bouncycastle.pqc.crypto.xmss;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
/* loaded from: classes4.dex */
public final class BDS implements Serializable {
    private static final long serialVersionUID = 1;
    private List<XMSSNode> authenticationPath;
    private int index;

    /* renamed from: k  reason: collision with root package name */
    private int f14615k;
    private Map<Integer, XMSSNode> keep;
    private transient int maxIndex;
    private Map<Integer, LinkedList<XMSSNode>> retain;
    private XMSSNode root;
    private Stack<XMSSNode> stack;
    private final List<BDSTreeHash> treeHashInstances;
    private final int treeHeight;
    private boolean used;
    private transient WOTSPlus wotsPlus;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BDS(BDS bds) {
        this.wotsPlus = new WOTSPlus(bds.wotsPlus.b());
        this.treeHeight = bds.treeHeight;
        this.f14615k = bds.f14615k;
        this.root = bds.root;
        ArrayList arrayList = new ArrayList();
        this.authenticationPath = arrayList;
        arrayList.addAll(bds.authenticationPath);
        this.retain = new TreeMap();
        for (Integer num : bds.retain.keySet()) {
            this.retain.put(num, (LinkedList) bds.retain.get(num).clone());
        }
        Stack<XMSSNode> stack = new Stack<>();
        this.stack = stack;
        stack.addAll(bds.stack);
        this.treeHashInstances = new ArrayList();
        for (BDSTreeHash bDSTreeHash : bds.treeHashInstances) {
            this.treeHashInstances.add(bDSTreeHash.clone());
        }
        this.keep = new TreeMap(bds.keep);
        this.index = bds.index;
        this.maxIndex = bds.maxIndex;
        this.used = bds.used;
    }

    private BDS(BDS bds, int i2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.wotsPlus = new WOTSPlus(new WOTSPlusParameters(aSN1ObjectIdentifier));
        this.treeHeight = bds.treeHeight;
        this.f14615k = bds.f14615k;
        this.root = bds.root;
        ArrayList arrayList = new ArrayList();
        this.authenticationPath = arrayList;
        arrayList.addAll(bds.authenticationPath);
        this.retain = new TreeMap();
        for (Integer num : bds.retain.keySet()) {
            this.retain.put(num, (LinkedList) bds.retain.get(num).clone());
        }
        Stack<XMSSNode> stack = new Stack<>();
        this.stack = stack;
        stack.addAll(bds.stack);
        this.treeHashInstances = new ArrayList();
        for (BDSTreeHash bDSTreeHash : bds.treeHashInstances) {
            this.treeHashInstances.add(bDSTreeHash.clone());
        }
        this.keep = new TreeMap(bds.keep);
        this.index = bds.index;
        this.maxIndex = i2;
        this.used = bds.used;
        validate();
    }

    private BDS(BDS bds, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.wotsPlus = new WOTSPlus(new WOTSPlusParameters(aSN1ObjectIdentifier));
        this.treeHeight = bds.treeHeight;
        this.f14615k = bds.f14615k;
        this.root = bds.root;
        ArrayList arrayList = new ArrayList();
        this.authenticationPath = arrayList;
        arrayList.addAll(bds.authenticationPath);
        this.retain = new TreeMap();
        for (Integer num : bds.retain.keySet()) {
            this.retain.put(num, (LinkedList) bds.retain.get(num).clone());
        }
        Stack<XMSSNode> stack = new Stack<>();
        this.stack = stack;
        stack.addAll(bds.stack);
        this.treeHashInstances = new ArrayList();
        for (BDSTreeHash bDSTreeHash : bds.treeHashInstances) {
            this.treeHashInstances.add(bDSTreeHash.clone());
        }
        this.keep = new TreeMap(bds.keep);
        this.index = bds.index;
        this.maxIndex = bds.maxIndex;
        this.used = bds.used;
        validate();
    }

    private BDS(BDS bds, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        this.wotsPlus = new WOTSPlus(bds.wotsPlus.b());
        this.treeHeight = bds.treeHeight;
        this.f14615k = bds.f14615k;
        this.root = bds.root;
        ArrayList arrayList = new ArrayList();
        this.authenticationPath = arrayList;
        arrayList.addAll(bds.authenticationPath);
        this.retain = new TreeMap();
        for (Integer num : bds.retain.keySet()) {
            this.retain.put(num, (LinkedList) bds.retain.get(num).clone());
        }
        Stack<XMSSNode> stack = new Stack<>();
        this.stack = stack;
        stack.addAll(bds.stack);
        this.treeHashInstances = new ArrayList();
        for (BDSTreeHash bDSTreeHash : bds.treeHashInstances) {
            this.treeHashInstances.add(bDSTreeHash.clone());
        }
        this.keep = new TreeMap(bds.keep);
        this.index = bds.index;
        this.maxIndex = bds.maxIndex;
        this.used = false;
        nextAuthenticationPath(bArr, bArr2, oTSHashAddress);
    }

    private BDS(WOTSPlus wOTSPlus, int i2, int i3, int i4) {
        this.wotsPlus = wOTSPlus;
        this.treeHeight = i2;
        this.maxIndex = i4;
        this.f14615k = i3;
        if (i3 <= i2 && i3 >= 2) {
            int i5 = i2 - i3;
            if (i5 % 2 == 0) {
                this.authenticationPath = new ArrayList();
                this.retain = new TreeMap();
                this.stack = new Stack<>();
                this.treeHashInstances = new ArrayList();
                for (int i6 = 0; i6 < i5; i6++) {
                    this.treeHashInstances.add(new BDSTreeHash(i6));
                }
                this.keep = new TreeMap();
                this.index = 0;
                this.used = false;
                return;
            }
        }
        throw new IllegalArgumentException("illegal value for BDS parameter k");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BDS(XMSSParameters xMSSParameters, int i2, int i3) {
        this(xMSSParameters.e(), xMSSParameters.getHeight(), xMSSParameters.a(), i3);
        this.maxIndex = i2;
        this.index = i3;
        this.used = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BDS(XMSSParameters xMSSParameters, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        this(xMSSParameters.e(), xMSSParameters.getHeight(), xMSSParameters.a(), (1 << xMSSParameters.getHeight()) - 1);
        initialize(bArr, bArr2, oTSHashAddress);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BDS(XMSSParameters xMSSParameters, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress, int i2) {
        this(xMSSParameters.e(), xMSSParameters.getHeight(), xMSSParameters.a(), (1 << xMSSParameters.getHeight()) - 1);
        initialize(bArr, bArr2, oTSHashAddress);
        while (this.index < i2) {
            nextAuthenticationPath(bArr, bArr2, oTSHashAddress);
            this.used = false;
        }
    }

    private BDSTreeHash getBDSTreeHashInstanceForUpdate() {
        BDSTreeHash bDSTreeHash = null;
        for (BDSTreeHash bDSTreeHash2 : this.treeHashInstances) {
            if (!bDSTreeHash2.e() && bDSTreeHash2.f() && (bDSTreeHash == null || bDSTreeHash2.b() < bDSTreeHash.b() || (bDSTreeHash2.b() == bDSTreeHash.b() && bDSTreeHash2.c() < bDSTreeHash.c()))) {
                bDSTreeHash = bDSTreeHash2;
            }
        }
        return bDSTreeHash;
    }

    private void initialize(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        LTreeAddress lTreeAddress = (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).l();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).k();
        for (int i2 = 0; i2 < (1 << this.treeHeight); i2++) {
            oTSHashAddress = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(i2).n(oTSHashAddress.d()).o(oTSHashAddress.e()).f(oTSHashAddress.getKeyAndMask())).l();
            WOTSPlus wOTSPlus = this.wotsPlus;
            wOTSPlus.g(wOTSPlus.f(bArr2, oTSHashAddress), bArr);
            WOTSPlusPublicKeyParameters c2 = this.wotsPlus.c(oTSHashAddress);
            lTreeAddress = (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress.a())).h(lTreeAddress.b())).n(i2).o(lTreeAddress.e()).p(lTreeAddress.f()).f(lTreeAddress.getKeyAndMask())).l();
            XMSSNode a2 = XMSSNodeUtil.a(this.wotsPlus, c2, lTreeAddress);
            hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress.a())).h(hashTreeAddress.b())).n(i2).f(hashTreeAddress.getKeyAndMask())).k();
            while (!this.stack.isEmpty() && this.stack.peek().getHeight() == a2.getHeight()) {
                int height = i2 / (1 << a2.getHeight());
                if (height == 1) {
                    this.authenticationPath.add(a2);
                }
                if (height == 3 && a2.getHeight() < this.treeHeight - this.f14615k) {
                    this.treeHashInstances.get(a2.getHeight()).g(a2);
                }
                if (height >= 3 && (height & 1) == 1 && a2.getHeight() >= this.treeHeight - this.f14615k && a2.getHeight() <= this.treeHeight - 2) {
                    if (this.retain.get(Integer.valueOf(a2.getHeight())) == null) {
                        LinkedList<XMSSNode> linkedList = new LinkedList<>();
                        linkedList.add(a2);
                        this.retain.put(Integer.valueOf(a2.getHeight()), linkedList);
                    } else {
                        this.retain.get(Integer.valueOf(a2.getHeight())).add(a2);
                    }
                }
                HashTreeAddress hashTreeAddress2 = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress.a())).h(hashTreeAddress.b())).m(hashTreeAddress.d()).n((hashTreeAddress.e() - 1) / 2).f(hashTreeAddress.getKeyAndMask())).k();
                XMSSNode b2 = XMSSNodeUtil.b(this.wotsPlus, this.stack.pop(), a2, hashTreeAddress2);
                XMSSNode xMSSNode = new XMSSNode(b2.getHeight() + 1, b2.getValue());
                hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress2.a())).h(hashTreeAddress2.b())).m(hashTreeAddress2.d() + 1).n(hashTreeAddress2.e()).f(hashTreeAddress2.getKeyAndMask())).k();
                a2 = xMSSNode;
            }
            this.stack.push(a2);
        }
        this.root = this.stack.pop();
    }

    private void nextAuthenticationPath(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        List<XMSSNode> list;
        XMSSNode removeFirst;
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        if (this.used) {
            throw new IllegalStateException("index already used");
        }
        int i2 = this.index;
        if (i2 > this.maxIndex - 1) {
            throw new IllegalStateException("index out of bounds");
        }
        int calculateTau = XMSSUtil.calculateTau(i2, this.treeHeight);
        if (((this.index >> (calculateTau + 1)) & 1) == 0 && calculateTau < this.treeHeight - 1) {
            this.keep.put(Integer.valueOf(calculateTau), this.authenticationPath.get(calculateTau));
        }
        LTreeAddress lTreeAddress = (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).l();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).k();
        if (calculateTau == 0) {
            oTSHashAddress = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(this.index).n(oTSHashAddress.d()).o(oTSHashAddress.e()).f(oTSHashAddress.getKeyAndMask())).l();
            WOTSPlus wOTSPlus = this.wotsPlus;
            wOTSPlus.g(wOTSPlus.f(bArr2, oTSHashAddress), bArr);
            this.authenticationPath.set(0, XMSSNodeUtil.a(this.wotsPlus, this.wotsPlus.c(oTSHashAddress), (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress.a())).h(lTreeAddress.b())).n(this.index).o(lTreeAddress.e()).p(lTreeAddress.f()).f(lTreeAddress.getKeyAndMask())).l()));
        } else {
            int i3 = calculateTau - 1;
            WOTSPlus wOTSPlus2 = this.wotsPlus;
            wOTSPlus2.g(wOTSPlus2.f(bArr2, oTSHashAddress), bArr);
            XMSSNode b2 = XMSSNodeUtil.b(this.wotsPlus, this.authenticationPath.get(i3), this.keep.get(Integer.valueOf(i3)), (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress.a())).h(hashTreeAddress.b())).m(i3).n(this.index >> calculateTau).f(hashTreeAddress.getKeyAndMask())).k());
            this.authenticationPath.set(calculateTau, new XMSSNode(b2.getHeight() + 1, b2.getValue()));
            this.keep.remove(Integer.valueOf(i3));
            for (int i4 = 0; i4 < calculateTau; i4++) {
                if (i4 < this.treeHeight - this.f14615k) {
                    list = this.authenticationPath;
                    removeFirst = this.treeHashInstances.get(i4).getTailNode();
                } else {
                    list = this.authenticationPath;
                    removeFirst = this.retain.get(Integer.valueOf(i4)).removeFirst();
                }
                list.set(i4, removeFirst);
            }
            int min = Math.min(calculateTau, this.treeHeight - this.f14615k);
            for (int i5 = 0; i5 < min; i5++) {
                int i6 = this.index + 1 + ((1 << i5) * 3);
                if (i6 < (1 << this.treeHeight)) {
                    this.treeHashInstances.get(i5).d(i6);
                }
            }
        }
        for (int i7 = 0; i7 < ((this.treeHeight - this.f14615k) >> 1); i7++) {
            BDSTreeHash bDSTreeHashInstanceForUpdate = getBDSTreeHashInstanceForUpdate();
            if (bDSTreeHashInstanceForUpdate != null) {
                bDSTreeHashInstanceForUpdate.h(this.stack, this.wotsPlus, bArr, bArr2, oTSHashAddress);
            }
        }
        this.index++;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.maxIndex = objectInputStream.available() != 0 ? objectInputStream.readInt() : (1 << this.treeHeight) - 1;
        int i2 = this.maxIndex;
        if (i2 > (1 << this.treeHeight) - 1 || this.index > i2 + 1 || objectInputStream.available() != 0) {
            throw new IOException("inconsistent BDS data detected");
        }
    }

    private void validate() {
        if (this.authenticationPath == null) {
            throw new IllegalStateException("authenticationPath == null");
        }
        if (this.retain == null) {
            throw new IllegalStateException("retain == null");
        }
        if (this.stack == null) {
            throw new IllegalStateException("stack == null");
        }
        if (this.treeHashInstances == null) {
            throw new IllegalStateException("treeHashInstances == null");
        }
        if (this.keep == null) {
            throw new IllegalStateException("keep == null");
        }
        if (!XMSSUtil.isIndexValid(this.treeHeight, this.index)) {
            throw new IllegalStateException("index in BDS state out of bounds");
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.maxIndex);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List a() {
        ArrayList arrayList = new ArrayList();
        for (XMSSNode xMSSNode : this.authenticationPath) {
            arrayList.add(xMSSNode);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b() {
        return this.index;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSNode c() {
        return this.root;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        this.used = true;
    }

    public int getMaxIndex() {
        return this.maxIndex;
    }

    public BDS getNextState(byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        return new BDS(this, bArr, bArr2, oTSHashAddress);
    }

    public BDS withMaxIndex(int i2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return new BDS(this, i2, aSN1ObjectIdentifier);
    }

    public BDS withWOTSDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return new BDS(this, aSN1ObjectIdentifier);
    }
}
