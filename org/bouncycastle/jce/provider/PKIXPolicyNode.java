package org.bouncycastle.jce.provider;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes3.dex */
public class PKIXPolicyNode implements PolicyNode {

    /* renamed from: a  reason: collision with root package name */
    protected List f13825a;

    /* renamed from: b  reason: collision with root package name */
    protected int f13826b;

    /* renamed from: c  reason: collision with root package name */
    protected Set f13827c;

    /* renamed from: d  reason: collision with root package name */
    protected PolicyNode f13828d;

    /* renamed from: e  reason: collision with root package name */
    protected Set f13829e;

    /* renamed from: f  reason: collision with root package name */
    protected String f13830f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f13831g;

    public PKIXPolicyNode(List list, int i2, Set set, PolicyNode policyNode, Set set2, String str, boolean z) {
        this.f13825a = list;
        this.f13826b = i2;
        this.f13827c = set;
        this.f13828d = policyNode;
        this.f13829e = set2;
        this.f13830f = str;
        this.f13831g = z;
    }

    public void addChild(PKIXPolicyNode pKIXPolicyNode) {
        this.f13825a.add(pKIXPolicyNode);
        pKIXPolicyNode.setParent(this);
    }

    public Object clone() {
        return copy();
    }

    public PKIXPolicyNode copy() {
        HashSet hashSet = new HashSet();
        for (String str : this.f13827c) {
            hashSet.add(new String(str));
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : this.f13829e) {
            hashSet2.add(new String(str2));
        }
        PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), this.f13826b, hashSet, null, hashSet2, new String(this.f13830f), this.f13831g);
        for (PKIXPolicyNode pKIXPolicyNode2 : this.f13825a) {
            PKIXPolicyNode copy = pKIXPolicyNode2.copy();
            copy.setParent(pKIXPolicyNode);
            pKIXPolicyNode.addChild(copy);
        }
        return pKIXPolicyNode;
    }

    @Override // java.security.cert.PolicyNode
    public Iterator getChildren() {
        return this.f13825a.iterator();
    }

    @Override // java.security.cert.PolicyNode
    public int getDepth() {
        return this.f13826b;
    }

    @Override // java.security.cert.PolicyNode
    public Set getExpectedPolicies() {
        return this.f13827c;
    }

    @Override // java.security.cert.PolicyNode
    public PolicyNode getParent() {
        return this.f13828d;
    }

    @Override // java.security.cert.PolicyNode
    public Set getPolicyQualifiers() {
        return this.f13829e;
    }

    @Override // java.security.cert.PolicyNode
    public String getValidPolicy() {
        return this.f13830f;
    }

    public boolean hasChildren() {
        return !this.f13825a.isEmpty();
    }

    @Override // java.security.cert.PolicyNode
    public boolean isCritical() {
        return this.f13831g;
    }

    public void removeChild(PKIXPolicyNode pKIXPolicyNode) {
        this.f13825a.remove(pKIXPolicyNode);
    }

    public void setCritical(boolean z) {
        this.f13831g = z;
    }

    public void setExpectedPolicies(Set set) {
        this.f13827c = set;
    }

    public void setParent(PKIXPolicyNode pKIXPolicyNode) {
        this.f13828d = pKIXPolicyNode;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(this.f13830f);
        stringBuffer.append(" {\n");
        for (int i2 = 0; i2 < this.f13825a.size(); i2++) {
            stringBuffer.append(((PKIXPolicyNode) this.f13825a.get(i2)).toString(str + "    "));
        }
        stringBuffer.append(str);
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }
}
