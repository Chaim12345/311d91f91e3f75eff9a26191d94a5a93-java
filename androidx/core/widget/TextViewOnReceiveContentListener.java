package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentListener;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public final class TextViewOnReceiveContentListener implements OnReceiveContentListener {
    private static final String LOG_TAG = "ReceiveContent";

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static final class Api16Impl {
        private Api16Impl() {
        }

        static CharSequence a(@NonNull Context context, @NonNull ClipData.Item item, int i2) {
            if ((i2 & 1) != 0) {
                CharSequence coerceToText = item.coerceToText(context);
                return coerceToText instanceof Spanned ? coerceToText.toString() : coerceToText;
            }
            return item.coerceToStyledText(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ApiImpl {
        private ApiImpl() {
        }

        static CharSequence a(@NonNull Context context, @NonNull ClipData.Item item, int i2) {
            CharSequence coerceToText = item.coerceToText(context);
            return ((i2 & 1) == 0 || !(coerceToText instanceof Spanned)) ? coerceToText : coerceToText.toString();
        }
    }

    @NonNull
    private static CharSequence coerceToText(@NonNull ClipData clipData, @NonNull Context context, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
            CharSequence coerceToText = coerceToText(context, clipData.getItemAt(i3), i2);
            if (coerceToText != null) {
                spannableStringBuilder.append(coerceToText);
            }
        }
        return spannableStringBuilder;
    }

    private static CharSequence coerceToText(@NonNull Context context, @NonNull ClipData.Item item, int i2) {
        return Build.VERSION.SDK_INT >= 16 ? Api16Impl.a(context, item, i2) : ApiImpl.a(context, item, i2);
    }

    private static void onReceiveForDragAndDrop(@NonNull TextView textView, @NonNull ContentInfoCompat contentInfoCompat) {
        replaceSelection((Editable) textView.getText(), coerceToText(contentInfoCompat.getClip(), textView.getContext(), contentInfoCompat.getFlags()));
    }

    private static void replaceSelection(@NonNull Editable editable, @NonNull CharSequence charSequence) {
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        int max = Math.max(0, Math.min(selectionStart, selectionEnd));
        int max2 = Math.max(0, Math.max(selectionStart, selectionEnd));
        Selection.setSelection(editable, max2);
        editable.replace(max, max2, charSequence);
    }

    @Override // androidx.core.view.OnReceiveContentListener
    @Nullable
    public ContentInfoCompat onReceiveContent(@NonNull View view, @NonNull ContentInfoCompat contentInfoCompat) {
        if (Log.isLoggable(LOG_TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("onReceive: ");
            sb.append(contentInfoCompat);
        }
        int source = contentInfoCompat.getSource();
        if (source == 2) {
            return contentInfoCompat;
        }
        if (source == 3) {
            onReceiveForDragAndDrop((TextView) view, contentInfoCompat);
            return null;
        }
        ClipData clip = contentInfoCompat.getClip();
        int flags = contentInfoCompat.getFlags();
        TextView textView = (TextView) view;
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean z = false;
        for (int i2 = 0; i2 < clip.getItemCount(); i2++) {
            CharSequence coerceToText = coerceToText(context, clip.getItemAt(i2), flags);
            if (coerceToText != null) {
                if (z) {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), coerceToText);
                } else {
                    replaceSelection(editable, coerceToText);
                    z = true;
                }
            }
        }
        return null;
    }
}
