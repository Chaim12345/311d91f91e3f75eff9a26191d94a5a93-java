package androidx.constraintlayout.core.parser;

import java.util.Iterator;
/* loaded from: classes.dex */
public class CLObject extends CLContainer implements Iterable<CLKey> {

    /* loaded from: classes.dex */
    private class CLObjectIterator implements Iterator {

        /* renamed from: a  reason: collision with root package name */
        CLObject f1863a;

        /* renamed from: b  reason: collision with root package name */
        int f1864b = 0;

        public CLObjectIterator(CLObject cLObject, CLObject cLObject2) {
            this.f1863a = cLObject2;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1864b < this.f1863a.size();
        }

        @Override // java.util.Iterator
        public Object next() {
            CLKey cLKey = (CLKey) this.f1863a.f1856f.get(this.f1864b);
            this.f1864b++;
            return cLKey;
        }
    }

    public CLObject(char[] cArr) {
        super(cArr);
    }

    public static CLObject allocate(char[] cArr) {
        return new CLObject(cArr);
    }

    @Override // java.lang.Iterable
    public Iterator<CLKey> iterator() {
        return new CLObjectIterator(this, this);
    }

    public String toFormattedJSON() {
        return toFormattedJSON(0, 0);
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i2, int i3) {
        StringBuilder sb = new StringBuilder(b());
        sb.append("{\n");
        Iterator it = this.f1856f.iterator();
        boolean z = true;
        while (it.hasNext()) {
            CLElement cLElement = (CLElement) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(",\n");
            }
            sb.append(cLElement.toFormattedJSON(CLElement.f1858e + i2, i3 - 1));
        }
        sb.append("\n");
        a(sb, i2);
        sb.append("}");
        return sb.toString();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        StringBuilder sb = new StringBuilder(b() + "{ ");
        Iterator it = this.f1856f.iterator();
        boolean z = true;
        while (it.hasNext()) {
            CLElement cLElement = (CLElement) it.next();
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(cLElement.toJSON());
        }
        sb.append(" }");
        return sb.toString();
    }
}
