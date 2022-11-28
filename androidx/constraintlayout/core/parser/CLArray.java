package androidx.constraintlayout.core.parser;

import java.util.Iterator;
/* loaded from: classes.dex */
public class CLArray extends CLContainer {
    public CLArray(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(char[] cArr) {
        return new CLArray(cArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        String json = toJSON();
        if (i3 > 0 || json.length() + i2 >= CLElement.f1857d) {
            sb.append("[\n");
            Iterator it = this.f1856f.iterator();
            boolean z = true;
            while (it.hasNext()) {
                CLElement cLElement = (CLElement) it.next();
                if (z) {
                    z = false;
                } else {
                    sb.append(",\n");
                }
                a(sb, CLElement.f1858e + i2);
                sb.append(cLElement.toFormattedJSON(CLElement.f1858e + i2, i3 - 1));
            }
            sb.append("\n");
            a(sb, i2);
            sb.append("]");
        } else {
            sb.append(json);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        StringBuilder sb = new StringBuilder(b() + "[");
        boolean z = true;
        for (int i2 = 0; i2 < this.f1856f.size(); i2++) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(((CLElement) this.f1856f.get(i2)).toJSON());
        }
        return ((Object) sb) + "]";
    }
}
