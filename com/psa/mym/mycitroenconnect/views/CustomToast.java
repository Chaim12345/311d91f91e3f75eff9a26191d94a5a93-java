package com.psa.mym.mycitroenconnect.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public class CustomToast {
    public static void showErrorToast(Context context, String str) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.my_custom_toast, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.textView1)).setText(str);
        ((LinearLayout) inflate.findViewById(R.id.lin_bg)).setBackground(ContextCompat.getDrawable(context, R.drawable.error_toast_border));
        ((ImageView) inflate.findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_warning);
        Toast toast = new Toast(context);
        toast.setView(inflate);
        toast.setDuration(1);
        toast.setGravity(87, 0, 0);
        toast.show();
    }

    public static void showSuccessToast(Context context, String str) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.my_custom_toast, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.textView1)).setText(str);
        ((LinearLayout) inflate.findViewById(R.id.lin_bg)).setBackground(ContextCompat.getDrawable(context, R.drawable.success_toast_border));
        ((ImageView) inflate.findViewById(R.id.imageView1)).setImageResource(R.drawable.ic_success);
        Toast toast = new Toast(context);
        toast.setView(inflate);
        toast.setDuration(0);
        toast.setGravity(87, 0, 0);
        toast.show();
    }
}
