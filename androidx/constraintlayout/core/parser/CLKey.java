package androidx.constraintlayout.core.parser;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class CLKey extends CLContainer {
    private static ArrayList<String> sections;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        sections = arrayList;
        arrayList.add("ConstraintSets");
        sections.add("Variables");
        sections.add("Generate");
        sections.add(TypedValues.Transition.NAME);
        sections.add("KeyFrames");
        sections.add(TypedValues.Attributes.NAME);
        sections.add("KeyPositions");
        sections.add("KeyCycles");
    }

    public CLKey(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(String str, CLElement cLElement) {
        CLKey cLKey = new CLKey(str.toCharArray());
        cLKey.setStart(0L);
        cLKey.setEnd(str.length() - 1);
        cLKey.set(cLElement);
        return cLKey;
    }

    public static CLElement allocate(char[] cArr) {
        return new CLKey(cArr);
    }

    public String getName() {
        return content();
    }

    public CLElement getValue() {
        if (this.f1856f.size() > 0) {
            return (CLElement) this.f1856f.get(0);
        }
        return null;
    }

    public void set(CLElement cLElement) {
        if (this.f1856f.size() > 0) {
            this.f1856f.set(0, cLElement);
        } else {
            this.f1856f.add(cLElement);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int i2, int i3) {
        StringBuilder sb = new StringBuilder(b());
        a(sb, i2);
        String content = content();
        if (this.f1856f.size() <= 0) {
            return content + ": <> ";
        }
        sb.append(content);
        sb.append(": ");
        if (sections.contains(content)) {
            i3 = 3;
        }
        if (i3 <= 0) {
            String json = ((CLElement) this.f1856f.get(0)).toJSON();
            if (json.length() + i2 < CLElement.f1857d) {
                sb.append(json);
                return sb.toString();
            }
        }
        sb.append(((CLElement) this.f1856f.get(0)).toFormattedJSON(i2, i3 - 1));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        StringBuilder sb;
        String str;
        if (this.f1856f.size() > 0) {
            sb = new StringBuilder();
            sb.append(b());
            sb.append(content());
            sb.append(": ");
            str = ((CLElement) this.f1856f.get(0)).toJSON();
        } else {
            sb = new StringBuilder();
            sb.append(b());
            sb.append(content());
            str = ": <> ";
        }
        sb.append(str);
        return sb.toString();
    }
}
