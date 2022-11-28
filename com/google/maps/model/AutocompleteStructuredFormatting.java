package com.google.maps.model;

import com.google.maps.model.AutocompletePrediction;
import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes2.dex */
public class AutocompleteStructuredFormatting implements Serializable {
    private static final long serialVersionUID = 1;
    public String mainText;
    public AutocompletePrediction.MatchedSubstring[] mainTextMatchedSubstrings;
    public String secondaryText;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append("\"");
        sb.append(this.mainText);
        sb.append("\"");
        sb.append(" at ");
        sb.append(Arrays.toString(this.mainTextMatchedSubstrings));
        if (this.secondaryText != null) {
            sb.append(", secondaryText=\"");
            sb.append(this.secondaryText);
            sb.append("\"");
        }
        sb.append(")");
        return sb.toString();
    }
}
