package com.simform.refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.simform.refresh.SSPullToRefreshLayout;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0015\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 Í\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\fÍ\u0001Î\u0001Ï\u0001Ð\u0001Ñ\u0001Ò\u0001B\"\u0012\n\u0010Ê\u0001\u001a\u0005\u0018\u00010É\u0001\u0012\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007f¢\u0006\u0006\bË\u0001\u0010Ì\u0001J \u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\bH\u0002J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\b\u0010\u0014\u001a\u00020\bH\u0002J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u001a\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002J\u001a\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002J\u0010\u0010 \u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u0010\u0010#\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u0004H\u0002J\b\u0010$\u001a\u00020\bH\u0002J\u0010\u0010'\u001a\u00020\b2\u0006\u0010&\u001a\u00020%H\u0002J\u0010\u0010(\u001a\u00020\b2\u0006\u0010&\u001a\u00020%H\u0002J\u0010\u0010*\u001a\u00020\b2\u0006\u0010)\u001a\u00020\fH\u0002J\u0018\u0010,\u001a\u00020\u00042\u0006\u0010&\u001a\u00020%2\u0006\u0010+\u001a\u00020\fH\u0002J\u0012\u0010/\u001a\u00020\u00152\b\u0010.\u001a\u0004\u0018\u00010-H\u0002J\b\u00100\u001a\u00020\bH\u0002J\u0010\u00102\u001a\u00020\b2\u0006\u00101\u001a\u00020-H\u0007J\u000e\u00105\u001a\u00020\b2\u0006\u00104\u001a\u000203J\u000e\u00108\u001a\u00020\b2\u0006\u00107\u001a\u000206J\u000e\u0010:\u001a\u00020\b2\u0006\u00109\u001a\u00020\fJ\u000e\u0010<\u001a\u00020\b2\u0006\u0010;\u001a\u00020\fJ\u000e\u0010?\u001a\u00020\b2\u0006\u0010>\u001a\u00020=J\u000e\u0010B\u001a\u00020\b2\u0006\u0010A\u001a\u00020@J\u000e\u0010E\u001a\u00020\b2\u0006\u0010D\u001a\u00020CJ\u0010\u0010H\u001a\u00020\b2\b\u0010G\u001a\u0004\u0018\u00010FJ\u0010\u0010J\u001a\u00020\b2\b\u0010I\u001a\u0004\u0018\u00010FJ\u000e\u0010L\u001a\u00020\b2\u0006\u0010K\u001a\u00020\fJ\u000e\u0010N\u001a\u00020\b2\u0006\u0010M\u001a\u00020\fJ\u000e\u0010P\u001a\u00020\b2\u0006\u0010O\u001a\u00020\u0004J\u000e\u0010R\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\u0004J\u0010\u0010T\u001a\u00020\b2\u0006\u0010S\u001a\u00020\u0015H\u0016J \u0010X\u001a\u00020\u00152\u0006\u0010U\u001a\u00020-2\u0006\u0010V\u001a\u00020-2\u0006\u0010W\u001a\u00020\fH\u0016J \u0010Z\u001a\u00020\b2\u0006\u0010U\u001a\u00020-2\u0006\u0010V\u001a\u00020-2\u0006\u0010Y\u001a\u00020\fH\u0016J(\u0010_\u001a\u00020\b2\u0006\u0010V\u001a\u00020-2\u0006\u0010[\u001a\u00020\f2\u0006\u0010\\\u001a\u00020\f2\u0006\u0010^\u001a\u00020]H\u0016J\b\u0010`\u001a\u00020\fH\u0016J\u0010\u0010a\u001a\u00020\b2\u0006\u0010V\u001a\u00020-H\u0016J0\u0010f\u001a\u00020\b2\u0006\u0010V\u001a\u00020-2\u0006\u0010b\u001a\u00020\f2\u0006\u0010c\u001a\u00020\f2\u0006\u0010d\u001a\u00020\f2\u0006\u0010e\u001a\u00020\fH\u0016J\u0010\u0010h\u001a\u00020\b2\u0006\u0010g\u001a\u00020\u0015H\u0016J\b\u0010i\u001a\u00020\u0015H\u0016J\u0010\u0010j\u001a\u00020\u00152\u0006\u0010Y\u001a\u00020\fH\u0016J\b\u0010k\u001a\u00020\bH\u0016J\b\u0010l\u001a\u00020\u0015H\u0016J2\u0010n\u001a\u00020\u00152\u0006\u0010b\u001a\u00020\f2\u0006\u0010c\u001a\u00020\f2\u0006\u0010d\u001a\u00020\f2\u0006\u0010e\u001a\u00020\f2\b\u0010m\u001a\u0004\u0018\u00010]H\u0016J,\u0010o\u001a\u00020\u00152\u0006\u0010[\u001a\u00020\f2\u0006\u0010\\\u001a\u00020\f2\b\u0010^\u001a\u0004\u0018\u00010]2\b\u0010m\u001a\u0004\u0018\u00010]H\u0016J \u0010r\u001a\u00020\u00152\u0006\u0010V\u001a\u00020-2\u0006\u0010p\u001a\u00020\u00042\u0006\u0010q\u001a\u00020\u0004H\u0016J(\u0010s\u001a\u00020\u00152\u0006\u0010V\u001a\u00020-2\u0006\u0010p\u001a\u00020\u00042\u0006\u0010q\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u0015H\u0016J \u0010t\u001a\u00020\u00152\u0006\u0010p\u001a\u00020\u00042\u0006\u0010q\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u0015H\u0016J\u0018\u0010u\u001a\u00020\u00152\u0006\u0010p\u001a\u00020\u00042\u0006\u0010q\u001a\u00020\u0004H\u0016J\u0018\u0010v\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u0010\u0010w\u001a\u00020\u00152\u0006\u0010&\u001a\u00020%H\u0016J\u0010\u0010x\u001a\u00020\u00152\u0006\u0010&\u001a\u00020%H\u0016J\u0010\u0010y\u001a\u00020\u00152\u0006\u0010&\u001a\u00020%H\u0016J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u0010|\u001a\u00020\b2\u0006\u0010{\u001a\u00020zJ\u0010\u0010~\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010}J\u0013\u0010\u0082\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0080\u0001\u001a\u00020\u007fH\u0016R\"\u0010\u0084\u0001\u001a\u000b \u0083\u0001*\u0004\u0018\u000106068\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0019\u0010\u0086\u0001\u001a\u0002068\u0002@\u0002X\u0082D¢\u0006\b\n\u0006\b\u0086\u0001\u0010\u0085\u0001R\u0019\u0010\u0087\u0001\u001a\u0002068\u0002@\u0002X\u0082D¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0085\u0001R\u0019\u0010\u0088\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u0019\u0010\u008a\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0019\u0010\u008c\u0001\u001a\u00020]8\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0019\u0010\u008e\u0001\u001a\u00020]8\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008d\u0001R\u001a\u0010\u0090\u0001\u001a\u00030\u008f\u00018\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u001a\u0010\u0093\u0001\u001a\u00030\u0092\u00018\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0019\u0010\u0095\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u0089\u0001R\u0019\u0010\u0096\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0089\u0001R\u0019\u0010\u0097\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0089\u0001R\u0019\u0010\u0098\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u0089\u0001R\u0019\u0010\u0099\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u0089\u0001R\u0019\u0010\u009a\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009a\u0001\u0010\u0089\u0001R\u0019\u0010\u009b\u0001\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009b\u0001\u0010\u0089\u0001R\u0019\u0010\u009c\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0019\u0010\u009e\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009e\u0001\u0010\u008b\u0001R\u0019\u0010\u009f\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009f\u0001\u0010\u008b\u0001R\u0019\u0010 \u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b \u0001\u0010\u008b\u0001R\u0019\u0010¡\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¡\u0001\u0010\u008b\u0001R\u0019\u0010¢\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¢\u0001\u0010\u008b\u0001R\u0019\u0010£\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b£\u0001\u0010\u008b\u0001R\u0019\u0010¤\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¤\u0001\u0010\u009d\u0001R\u0019\u0010¥\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¥\u0001\u0010\u009d\u0001R\u0019\u0010¦\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¦\u0001\u0010\u009d\u0001R\u0019\u0010§\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b§\u0001\u0010\u009d\u0001R\u0019\u0010¨\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¨\u0001\u0010\u009d\u0001R\u0019\u0010©\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b©\u0001\u0010\u009d\u0001R\u0019\u0010ª\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bª\u0001\u0010\u008b\u0001R\u0019\u0010«\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b«\u0001\u0010\u008b\u0001R\u0019\u0010¬\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¬\u0001\u0010\u008b\u0001R\u0019\u0010\u00ad\u0001\u001a\u00020z8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u00ad\u0001\u0010®\u0001R\u0019\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b.\u0010¯\u0001R\u0019\u0010°\u0001\u001a\u00020-8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b°\u0001\u0010¯\u0001R\u0019\u0010±\u0001\u001a\u00020=8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b±\u0001\u0010²\u0001R\u0019\u0010³\u0001\u001a\u0002068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b³\u0001\u0010\u0085\u0001R\u0019\u0010´\u0001\u001a\u0002038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0001\u0010µ\u0001R\u001b\u0010¶\u0001\u001a\u0004\u0018\u00010}8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0001\u0010·\u0001R\u0019\u0010¸\u0001\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¸\u0001\u0010¹\u0001R\u0019\u0010º\u0001\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bº\u0001\u0010¹\u0001R\u001a\u0010¼\u0001\u001a\u00030»\u00018\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¼\u0001\u0010½\u0001R\u001a\u0010¾\u0001\u001a\u00030»\u00018\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¾\u0001\u0010½\u0001R\u0019\u0010¿\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¿\u0001\u0010À\u0001R\u0019\u0010Á\u0001\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÁ\u0001\u0010À\u0001R\u0019\u0010Ä\u0001\u001a\u00020\f8B@\u0002X\u0082\u0004¢\u0006\b\u001a\u0006\bÂ\u0001\u0010Ã\u0001R\u0019\u0010Æ\u0001\u001a\u00020\f8B@\u0002X\u0082\u0004¢\u0006\b\u001a\u0006\bÅ\u0001\u0010Ã\u0001R\u0019\u0010Ç\u0001\u001a\u00020\u00158B@\u0002X\u0082\u0004¢\u0006\b\u001a\u0006\bÇ\u0001\u0010È\u0001¨\u0006Ó\u0001"}, d2 = {"Lcom/simform/refresh/SSPullToRefreshLayout;", "Landroid/view/ViewGroup;", "Landroidx/core/view/NestedScrollingParent;", "Landroidx/core/view/NestedScrollingChild;", "", "targetEnd", "currentOffset", "interpolatedTime", "", "animateToTargetOffset", "reset", "setTargetOrRefreshViewToInitial", "", "layoutTop", "reviseTargetLayoutTop", "reviseRefreshViewLayoutTop", "measureTarget", "widthMeasureSpec", "heightMeasureSpec", "measureRefreshView", "resetTouchEvent", "", "refreshing", "notify", "setRefreshing", "activeMoveY", "initDragStatus", "from", "Landroid/view/animation/Animation$AnimationListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "animateOffsetToStartPosition", "animateToRefreshingPosition", "computeAnimateToRefreshingDuration", "computeAnimateToStartDuration", "targetOrRefreshViewOffsetY", "moveSpinner", "finishSpinner", "Landroid/view/MotionEvent;", "ev", "onNewerPointerDown", "onSecondaryPointerUp", "offsetY", "setTargetOrRefreshViewOffsetY", "activePointerId", "getMotionEventY", "Landroid/view/View;", "mTarget", "canChildScrollUp", "ensureTarget", "refreshView", "setRefreshView", "Landroid/view/ViewGroup$LayoutParams;", "params", "setRefreshViewParams", "", "assetFileName", "setLottieAnimation", "rawResource", "setGifAnimation", "imageResource", "setImageAsRefresh", "Lcom/simform/refresh/SSDragDistanceConverter;", "dragDistanceConverter", "setDragDistanceConverter", "Lcom/simform/refresh/SSPullToRefreshLayout$RepeatCount;", "count", "setRepeatCount", "Lcom/simform/refresh/SSPullToRefreshLayout$RepeatMode;", "mode", "setRepeatMode", "Landroid/view/animation/Interpolator;", "animateToStartInterpolator", "setAnimateToStartInterpolator", "animateToRefreshInterpolator", "setAnimateToRefreshInterpolator", "animateToStartDuration", "setAnimateToStartDuration", "animateToRefreshDuration", "setAnimateToRefreshDuration", "refreshTargetOffset", "setRefreshTargetOffset", "refreshInitialOffset", "setRefreshInitialOffset", "b", "requestDisallowInterceptTouchEvent", "child", TypedValues.Attributes.S_TARGET, "nestedScrollAxes", "onStartNestedScroll", "axes", "onNestedScrollAccepted", "dx", "dy", "", "consumed", "onNestedPreScroll", "getNestedScrollAxes", "onStopNestedScroll", "dxConsumed", "dyConsumed", "dxUnconsumed", "dyUnconsumed", "onNestedScroll", "enabled", "setNestedScrollingEnabled", "isNestedScrollingEnabled", "startNestedScroll", "stopNestedScroll", "hasNestedScrollingParent", "offsetInWindow", "dispatchNestedScroll", "dispatchNestedPreScroll", "velocityX", "velocityY", "onNestedPreFling", "onNestedFling", "dispatchNestedFling", "dispatchNestedPreFling", "onMeasure", "dispatchTouchEvent", "onInterceptTouchEvent", "onTouchEvent", "Lcom/simform/refresh/SSPullToRefreshLayout$RefreshStyle;", "refreshStyle", "setRefreshStyle", "Lcom/simform/refresh/SSPullToRefreshLayout$OnRefreshListener;", "setOnRefreshListener", "Landroid/util/AttributeSet;", "attrs", "Lcom/simform/refresh/SSPullToRefreshLayout$LayoutParams;", "generateLayoutParams", "kotlin.jvm.PlatformType", "logTag", "Ljava/lang/String;", "ssAnimViewLottieMethodError", "lottieAnimViewGifMethodError", "mTotalUnconsumed", "F", "mNestedScrollInProgress", "Z", "mParentScrollConsumed", "[I", "mParentOffsetInWindow", "Landroidx/core/view/NestedScrollingChildHelper;", "mNestedScrollingChildHelper", "Landroidx/core/view/NestedScrollingChildHelper;", "Landroidx/core/view/NestedScrollingParentHelper;", "mNestedScrollingParentHelper", "Landroidx/core/view/NestedScrollingParentHelper;", "mRefreshInitialOffset", "mRefreshTargetOffset", "mInitialDownY", "mInitialScrollY", "mInitialMotionY", "mCurrentTouchOffsetY", "mTargetOrRefreshViewOffsetY", "mFrom", "I", "mIsAnimatingToStart", "mIsRefreshing", "mIsFitRefresh", "mIsBeingDragged", "mNotifyListener", "mDispatchTargetTouchDown", "mRefreshViewIndex", "mActivePointerId", "mAnimateToStartDuration", "mAnimateToRefreshDuration", "mTouchSlop", "mRefreshViewSize", "mUsingCustomRefreshTargetOffset", "mUsingCustomRefreshInitialOffset", "mRefreshViewMeasured", "mRefreshStyle", "Lcom/simform/refresh/SSPullToRefreshLayout$RefreshStyle;", "Landroid/view/View;", "mRefreshView", "mDragDistanceConverter", "Lcom/simform/refresh/SSDragDistanceConverter;", "mLottieAnimationAssetFileName", "mRefreshLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "mOnRefreshListener", "Lcom/simform/refresh/SSPullToRefreshLayout$OnRefreshListener;", "mAnimateToStartInterpolator", "Landroid/view/animation/Interpolator;", "mAnimateToRefreshInterpolator", "Landroid/view/animation/Animation;", "mAnimateToRefreshingAnimation", "Landroid/view/animation/Animation;", "mAnimateToStartAnimation", "mRefreshingListener", "Landroid/view/animation/Animation$AnimationListener;", "mResetListener", "getTargetOrRefreshViewTop", "()I", "targetOrRefreshViewTop", "getTargetOrRefreshViewOffset", "targetOrRefreshViewOffset", "isTargetValid", "()Z", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "LayoutParams", "OnRefreshListener", "RefreshStyle", "RepeatCount", "RepeatMode", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class SSPullToRefreshLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    private static final int DEFAULT_ANIMATE_DURATION = 300;
    private static final int DEFAULT_REFRESH_SIZE_DP = 70;
    private static final int DEFAULT_REFRESH_TARGET_OFFSET_DP = 50;
    private static final int INVALID_INDEX = -1;
    private static final int INVALID_POINTER = -1;
    private final String logTag;
    @NotNull
    private final String lottieAnimViewGifMethodError;
    private int mActivePointerId;
    private int mAnimateToRefreshDuration;
    @NotNull
    private Interpolator mAnimateToRefreshInterpolator;
    @NotNull
    private final Animation mAnimateToRefreshingAnimation;
    @NotNull
    private final Animation mAnimateToStartAnimation;
    private int mAnimateToStartDuration;
    @NotNull
    private Interpolator mAnimateToStartInterpolator;
    private float mCurrentTouchOffsetY;
    private boolean mDispatchTargetTouchDown;
    @NotNull
    private SSDragDistanceConverter mDragDistanceConverter;
    private int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private float mInitialScrollY;
    private boolean mIsAnimatingToStart;
    private boolean mIsBeingDragged;
    private boolean mIsFitRefresh;
    private boolean mIsRefreshing;
    @NotNull
    private String mLottieAnimationAssetFileName;
    private boolean mNestedScrollInProgress;
    @NotNull
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    @NotNull
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private boolean mNotifyListener;
    @Nullable
    private OnRefreshListener mOnRefreshListener;
    @NotNull
    private final int[] mParentOffsetInWindow;
    @NotNull
    private final int[] mParentScrollConsumed;
    private float mRefreshInitialOffset;
    @NotNull
    private ViewGroup.LayoutParams mRefreshLayoutParams;
    @NotNull
    private RefreshStyle mRefreshStyle;
    private float mRefreshTargetOffset;
    @NotNull
    private View mRefreshView;
    private int mRefreshViewIndex;
    private boolean mRefreshViewMeasured;
    private final int mRefreshViewSize;
    @NotNull
    private final Animation.AnimationListener mRefreshingListener;
    @NotNull
    private final Animation.AnimationListener mResetListener;
    @Nullable
    private View mTarget;
    private float mTargetOrRefreshViewOffsetY;
    private float mTotalUnconsumed;
    private final int mTouchSlop;
    private boolean mUsingCustomRefreshInitialOffset;
    private boolean mUsingCustomRefreshTargetOffset;
    @NotNull
    private final String ssAnimViewLottieMethodError;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u00020\u00058\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\b\u0010\u0007R\u0016\u0010\t\u001a\u00020\u00058\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\u0007R\u0016\u0010\n\u001a\u00020\u00058\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u0007R\u0016\u0010\u000b\u001a\u00020\u00058\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/simform/refresh/SSPullToRefreshLayout$Companion;", "", "", "DECELERATE_INTERPOLATION_FACTOR", "F", "", "DEFAULT_ANIMATE_DURATION", "I", "DEFAULT_REFRESH_SIZE_DP", "DEFAULT_REFRESH_TARGET_OFFSET_DP", "INVALID_INDEX", "INVALID_POINTER", "<init>", "()V", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\u000bB\u0013\b\u0016\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0006\u0010\rB\u0013\b\u0016\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0006\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/simform/refresh/SSPullToRefreshLayout$LayoutParams;", "Landroid/view/ViewGroup$MarginLayoutParams;", "Landroid/content/Context;", "c", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "width", "height", "(II)V", "source", "(Landroid/view/ViewGroup$MarginLayoutParams;)V", "Landroid/view/ViewGroup$LayoutParams;", "(Landroid/view/ViewGroup$LayoutParams;)V", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public static final class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(@Nullable Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(@Nullable ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(@Nullable ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u0004"}, d2 = {"Lcom/simform/refresh/SSPullToRefreshLayout$OnRefreshListener;", "", "", "onRefresh", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public interface OnRefreshListener {
        void onRefresh();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/simform/refresh/SSPullToRefreshLayout$RefreshStyle;", "", "<init>", "(Ljava/lang/String;I)V", "NORMAL", "PINNED", "FLOAT", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public enum RefreshStyle {
        NORMAL,
        PINNED,
        FLOAT;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static RefreshStyle[] valuesCustom() {
            RefreshStyle[] valuesCustom = values();
            return (RefreshStyle[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/simform/refresh/SSPullToRefreshLayout$RepeatCount;", "", "", "count", "I", "getCount", "()I", "<init>", "(Ljava/lang/String;II)V", "INFINITE", "ONCE", "TWICE", "THRICE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public enum RepeatCount {
        INFINITE(-1),
        ONCE(1),
        TWICE(2),
        THRICE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        ELEVEN(11),
        TWELVE(12);
        
        private final int count;

        RepeatCount(int i2) {
            this.count = i2;
        }

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static RepeatCount[] valuesCustom() {
            RepeatCount[] valuesCustom = values();
            return (RepeatCount[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
        }

        public final int getCount() {
            return this.count;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/simform/refresh/SSPullToRefreshLayout$RepeatMode;", "", "", "mode", "I", "getMode", "()I", "<init>", "(Ljava/lang/String;II)V", "REPEAT", "REVERSE", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public enum RepeatMode {
        REPEAT(1),
        REVERSE(2);
        
        private final int mode;

        RepeatMode(int i2) {
            this.mode = i2;
        }

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static RepeatMode[] valuesCustom() {
            RepeatMode[] valuesCustom = values();
            return (RepeatMode[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
        }

        public final int getMode() {
            return this.mode;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RefreshStyle.valuesCustom().length];
            iArr[RefreshStyle.FLOAT.ordinal()] = 1;
            iArr[RefreshStyle.PINNED.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SSPullToRefreshLayout(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.logTag = SSPullToRefreshLayout.class.getName();
        this.ssAnimViewLottieMethodError = "For this method to use you need to Provide SSAnimationView as RefreshView";
        this.lottieAnimViewGifMethodError = "For this method to use you need to Provide SSLottieAnimationView as RefreshView";
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mRefreshViewIndex = -1;
        this.mActivePointerId = -1;
        this.mAnimateToStartDuration = 300;
        this.mAnimateToRefreshDuration = 300;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mRefreshStyle = RefreshStyle.NORMAL;
        this.mLottieAnimationAssetFileName = "lottie_rolling_dots.json";
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i2 = (int) (70 * displayMetrics.density);
        this.mRefreshViewSize = i2;
        this.mRefreshLayoutParams = new ViewGroup.MarginLayoutParams(-1, i2);
        this.mRefreshTargetOffset = 50 * displayMetrics.density;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        setChildrenDrawingOrderEnabled(true);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext()");
        DefaultAnimationView defaultAnimationView = new DefaultAnimationView(context2);
        this.mRefreshView = defaultAnimationView;
        defaultAnimationView.setAnimation(this.mLottieAnimationAssetFileName);
        this.mRefreshView.setVisibility(8);
        addView(this.mRefreshView, new LayoutParams(i2, i2));
        this.mDragDistanceConverter = new SSDragDistanceConverter();
        this.mAnimateToStartInterpolator = new DecelerateInterpolator(2.0f);
        this.mAnimateToRefreshInterpolator = new DecelerateInterpolator(2.0f);
        this.mAnimateToRefreshingAnimation = new Animation() { // from class: com.simform.refresh.SSPullToRefreshLayout$mAnimateToRefreshingAnimation$1

            @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
            /* loaded from: classes3.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[SSPullToRefreshLayout.RefreshStyle.valuesCustom().length];
                    iArr[SSPullToRefreshLayout.RefreshStyle.FLOAT.ordinal()] = 1;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // android.view.animation.Animation
            protected void applyTransformation(float f2, @NotNull Transformation t2) {
                SSPullToRefreshLayout.RefreshStyle refreshStyle;
                View view;
                float f3;
                float f4;
                float f5;
                View view2;
                Intrinsics.checkNotNullParameter(t2, "t");
                refreshStyle = SSPullToRefreshLayout.this.mRefreshStyle;
                if (WhenMappings.$EnumSwitchMapping$0[refreshStyle.ordinal()] == 1) {
                    f4 = SSPullToRefreshLayout.this.mRefreshTargetOffset;
                    f5 = SSPullToRefreshLayout.this.mRefreshInitialOffset;
                    float f6 = f4 + f5;
                    SSPullToRefreshLayout sSPullToRefreshLayout = SSPullToRefreshLayout.this;
                    view2 = sSPullToRefreshLayout.mRefreshView;
                    sSPullToRefreshLayout.animateToTargetOffset(f6, view2.getTop(), f2);
                    return;
                }
                view = SSPullToRefreshLayout.this.mTarget;
                if (view == null) {
                    return;
                }
                SSPullToRefreshLayout sSPullToRefreshLayout2 = SSPullToRefreshLayout.this;
                f3 = sSPullToRefreshLayout2.mRefreshTargetOffset;
                sSPullToRefreshLayout2.animateToTargetOffset(f3, view.getTop(), f2);
            }
        };
        this.mAnimateToStartAnimation = new Animation() { // from class: com.simform.refresh.SSPullToRefreshLayout$mAnimateToStartAnimation$1

            @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
            /* loaded from: classes3.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[SSPullToRefreshLayout.RefreshStyle.valuesCustom().length];
                    iArr[SSPullToRefreshLayout.RefreshStyle.FLOAT.ordinal()] = 1;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // android.view.animation.Animation
            protected void applyTransformation(float f2, @NotNull Transformation t2) {
                SSPullToRefreshLayout.RefreshStyle refreshStyle;
                View view;
                float f3;
                View view2;
                Intrinsics.checkNotNullParameter(t2, "t");
                refreshStyle = SSPullToRefreshLayout.this.mRefreshStyle;
                if (WhenMappings.$EnumSwitchMapping$0[refreshStyle.ordinal()] == 1) {
                    SSPullToRefreshLayout sSPullToRefreshLayout = SSPullToRefreshLayout.this;
                    f3 = sSPullToRefreshLayout.mRefreshInitialOffset;
                    view2 = SSPullToRefreshLayout.this.mRefreshView;
                    sSPullToRefreshLayout.animateToTargetOffset(f3, view2.getTop(), f2);
                    return;
                }
                view = SSPullToRefreshLayout.this.mTarget;
                if (view == null) {
                    return;
                }
                SSPullToRefreshLayout.this.animateToTargetOffset(0.0f, view.getTop(), f2);
            }
        };
        this.mRefreshingListener = new Animation.AnimationListener() { // from class: com.simform.refresh.SSPullToRefreshLayout$mRefreshingListener$1
            /* JADX WARN: Code restructure failed: missing block: B:4:0x000d, code lost:
                r2 = r1.f10832a.mOnRefreshListener;
             */
            @Override // android.view.animation.Animation.AnimationListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onAnimationEnd(@NotNull Animation animation) {
                boolean z;
                SSPullToRefreshLayout.OnRefreshListener onRefreshListener;
                Intrinsics.checkNotNullParameter(animation, "animation");
                z = SSPullToRefreshLayout.this.mNotifyListener;
                if (z && onRefreshListener != null) {
                    onRefreshListener.onRefresh();
                }
                SSPullToRefreshLayout.this.mIsAnimatingToStart = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(@NotNull Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(@NotNull Animation animation) {
                View view;
                Intrinsics.checkNotNullParameter(animation, "animation");
                SSPullToRefreshLayout.this.mIsAnimatingToStart = true;
                view = SSPullToRefreshLayout.this.mRefreshView;
                ((RefreshCallbacks) view).refreshing();
            }
        };
        this.mResetListener = new Animation.AnimationListener() { // from class: com.simform.refresh.SSPullToRefreshLayout$mResetListener$1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(@NotNull Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                SSPullToRefreshLayout.this.reset();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(@NotNull Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(@NotNull Animation animation) {
                View view;
                Intrinsics.checkNotNullParameter(animation, "animation");
                SSPullToRefreshLayout.this.mIsAnimatingToStart = true;
                view = SSPullToRefreshLayout.this.mRefreshView;
                ((RefreshCallbacks) view).refreshComplete();
            }
        };
    }

    public /* synthetic */ SSPullToRefreshLayout(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    private final void animateOffsetToStartPosition(int i2, Animation.AnimationListener animationListener) {
        float f2;
        clearAnimation();
        if (computeAnimateToStartDuration(i2) <= 0) {
            this.mAnimateToStartAnimation.cancel();
            return;
        }
        this.mFrom = i2;
        this.mAnimateToStartAnimation.reset();
        this.mAnimateToStartAnimation.setDuration(computeAnimateToStartDuration(f2));
        this.mAnimateToStartAnimation.setInterpolator(this.mAnimateToStartInterpolator);
        if (animationListener != null) {
            this.mAnimateToStartAnimation.setAnimationListener(animationListener);
        }
        startAnimation(this.mAnimateToStartAnimation);
    }

    private final void animateToRefreshingPosition(int i2, Animation.AnimationListener animationListener) {
        float f2;
        clearAnimation();
        if (computeAnimateToRefreshingDuration(i2) <= 0) {
            this.mAnimateToRefreshingAnimation.cancel();
            return;
        }
        this.mFrom = i2;
        this.mAnimateToRefreshingAnimation.reset();
        this.mAnimateToRefreshingAnimation.setDuration(computeAnimateToRefreshingDuration(f2));
        this.mAnimateToRefreshingAnimation.setInterpolator(this.mAnimateToRefreshInterpolator);
        if (animationListener != null) {
            this.mAnimateToRefreshingAnimation.setAnimationListener(animationListener);
        }
        startAnimation(this.mAnimateToRefreshingAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void animateToTargetOffset(float f2, float f3, float f4) {
        int i2 = this.mFrom;
        setTargetOrRefreshViewOffsetY((int) (((int) (i2 + ((f2 - i2) * f4))) - f3));
    }

    private final boolean canChildScrollUp(View view) {
        ViewGroup viewGroup;
        int childCount;
        int i2 = 0;
        if (view == null) {
            return false;
        }
        if ((view instanceof ViewGroup) && (childCount = (viewGroup = (ViewGroup) view).getChildCount()) > 0) {
            while (true) {
                int i3 = i2 + 1;
                if (canChildScrollUp(viewGroup.getChildAt(i2))) {
                    return true;
                }
                if (i3 >= childCount) {
                    break;
                }
                i2 = i3;
            }
        }
        return ViewCompat.canScrollVertically(view, -1);
    }

    private final int computeAnimateToRefreshingDuration(float f2) {
        float coerceAtMost;
        float coerceAtLeast;
        Intrinsics.stringPlus("from -- refreshing ", Float.valueOf(f2));
        if (f2 < this.mRefreshInitialOffset) {
            return 0;
        }
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            f2 -= this.mRefreshInitialOffset;
        }
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(1.0f, Math.abs(f2 - this.mRefreshTargetOffset) / this.mRefreshTargetOffset);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(0.0f, coerceAtMost);
        return (int) (coerceAtLeast * this.mAnimateToRefreshDuration);
    }

    private final int computeAnimateToStartDuration(float f2) {
        float coerceAtMost;
        float coerceAtLeast;
        Intrinsics.stringPlus("from -- start ", Float.valueOf(f2));
        if (f2 < this.mRefreshInitialOffset) {
            return 0;
        }
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            f2 -= this.mRefreshInitialOffset;
        }
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(1.0f, Math.abs(f2) / this.mRefreshTargetOffset);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(0.0f, coerceAtMost);
        return (int) (coerceAtLeast * this.mAnimateToStartDuration);
    }

    private final void ensureTarget() {
        if (isTargetValid()) {
            return;
        }
        int i2 = 0;
        int childCount = getChildCount();
        if (childCount <= 0) {
            return;
        }
        while (true) {
            int i3 = i2 + 1;
            View childAt = getChildAt(i2);
            if (!Intrinsics.areEqual(childAt, this.mRefreshView)) {
                this.mTarget = childAt;
                return;
            } else if (i3 >= childCount) {
                return;
            } else {
                i2 = i3;
            }
        }
    }

    private final void finishSpinner() {
        if (this.mIsRefreshing || this.mIsAnimatingToStart) {
            return;
        }
        if (getTargetOrRefreshViewOffset() > this.mRefreshTargetOffset) {
            setRefreshing(true, true);
            return;
        }
        this.mIsRefreshing = false;
        animateOffsetToStartPosition((int) this.mTargetOrRefreshViewOffsetY, this.mResetListener);
    }

    private final float getMotionEventY(MotionEvent motionEvent, int i2) {
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        if (findPointerIndex < 0) {
            return -1.0f;
        }
        return motionEvent.getY(findPointerIndex);
    }

    private final int getTargetOrRefreshViewOffset() {
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            return (int) (this.mRefreshView.getTop() - this.mRefreshInitialOffset);
        }
        View view = this.mTarget;
        Intrinsics.checkNotNull(view);
        return view.getTop();
    }

    private final int getTargetOrRefreshViewTop() {
        View view;
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            view = this.mRefreshView;
        } else {
            view = this.mTarget;
            Intrinsics.checkNotNull(view);
        }
        return view.getTop();
    }

    private final void initDragStatus(float f2) {
        float f3 = this.mInitialDownY;
        float f4 = f2 - f3;
        if (this.mIsRefreshing) {
            int i2 = this.mTouchSlop;
            if (f4 > i2 || this.mTargetOrRefreshViewOffsetY > 0.0f) {
                this.mIsBeingDragged = true;
                this.mInitialMotionY = f3 + i2;
                return;
            }
        }
        if (this.mIsBeingDragged) {
            return;
        }
        int i3 = this.mTouchSlop;
        if (f4 > i3) {
            this.mInitialMotionY = f3 + i3;
            this.mIsBeingDragged = true;
        }
    }

    private final boolean isTargetValid() {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (this.mTarget == getChildAt(i2)) {
                    return true;
                }
                if (i3 >= childCount) {
                    break;
                }
                i2 = i3;
            }
        }
        return false;
    }

    private final void measureRefreshView(int i2, int i3) {
        int childMeasureSpec;
        int childMeasureSpec2;
        int coerceAtLeast;
        int coerceAtLeast2;
        ViewGroup.LayoutParams layoutParams = this.mRefreshView.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (marginLayoutParams.width == -1) {
            coerceAtLeast2 = RangesKt___RangesKt.coerceAtLeast(0, (((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin);
            childMeasureSpec = View.MeasureSpec.makeMeasureSpec(coerceAtLeast2, 1073741824);
        } else {
            childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width);
        }
        if (marginLayoutParams.height == -1) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(0, (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin);
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(coerceAtLeast, 1073741824);
        } else {
            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        }
        this.mRefreshView.measure(childMeasureSpec, childMeasureSpec2);
    }

    private final void measureTarget() {
        View view = this.mTarget;
        if (view == null) {
            return;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
    }

    private final void moveSpinner(float f2) {
        float f3;
        float f4;
        this.mCurrentTouchOffsetY = f2;
        if (this.mIsRefreshing) {
            f3 = this.mRefreshTargetOffset;
            f4 = f2 > f3 ? f3 : f2;
            if (f4 < 0.0f) {
                f4 = 0.0f;
            }
        } else {
            f4 = WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1 ? this.mRefreshInitialOffset + this.mDragDistanceConverter.convert(f2, this.mRefreshTargetOffset) : this.mDragDistanceConverter.convert(f2, this.mRefreshTargetOffset);
            f3 = this.mRefreshTargetOffset;
        }
        if (!this.mIsRefreshing) {
            if (f4 > f3 && !this.mIsFitRefresh) {
                this.mIsFitRefresh = true;
                ((RefreshCallbacks) this.mRefreshView).pullToRefresh();
            } else if (f4 <= f3 && this.mIsFitRefresh) {
                this.mIsFitRefresh = false;
                ((RefreshCallbacks) this.mRefreshView).releaseToRefresh();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f2);
        sb.append(" -- ");
        sb.append(f3);
        sb.append(" -- ");
        sb.append(f4);
        sb.append(" -- ");
        sb.append(this.mTargetOrRefreshViewOffsetY);
        sb.append(" -- ");
        sb.append(this.mRefreshTargetOffset);
        setTargetOrRefreshViewOffsetY((int) (f4 - this.mTargetOrRefreshViewOffsetY));
    }

    private final void onNewerPointerDown(MotionEvent motionEvent) {
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.mActivePointerId = pointerId;
        float motionEventY = getMotionEventY(motionEvent, pointerId) - this.mCurrentTouchOffsetY;
        this.mInitialMotionY = motionEventY;
        Intrinsics.stringPlus(" onDown ", Float.valueOf(motionEventY));
    }

    private final void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
        float motionEventY = getMotionEventY(motionEvent, this.mActivePointerId) - this.mCurrentTouchOffsetY;
        this.mInitialMotionY = motionEventY;
        Intrinsics.stringPlus(" onUp ", Float.valueOf(motionEventY));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reset() {
        setTargetOrRefreshViewToInitial();
        this.mCurrentTouchOffsetY = 0.0f;
        ((RefreshCallbacks) this.mRefreshView).reset();
        this.mRefreshView.setVisibility(8);
        this.mIsRefreshing = false;
        this.mIsAnimatingToStart = false;
    }

    private final void resetTouchEvent() {
        this.mInitialScrollY = 0.0f;
        this.mIsBeingDragged = false;
        this.mDispatchTargetTouchDown = false;
        this.mActivePointerId = -1;
    }

    private final int reviseRefreshViewLayoutTop(int i2) {
        int i3 = WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()];
        return (i3 == 1 || i3 != 2) ? i2 + ((int) this.mTargetOrRefreshViewOffsetY) : i2;
    }

    private final int reviseTargetLayoutTop(int i2) {
        return WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] != 1 ? i2 + ((int) this.mTargetOrRefreshViewOffsetY) : i2;
    }

    private final void setRefreshing(boolean z, boolean z2) {
        if (this.mIsRefreshing != z) {
            this.mNotifyListener = z2;
            this.mIsRefreshing = z;
            if (z) {
                animateToRefreshingPosition((int) this.mTargetOrRefreshViewOffsetY, this.mRefreshingListener);
            } else {
                animateOffsetToStartPosition((int) this.mTargetOrRefreshViewOffsetY, this.mResetListener);
            }
        }
    }

    private final void setTargetOrRefreshViewOffsetY(int i2) {
        View view;
        RefreshCallbacks refreshCallbacks;
        float f2;
        float f3;
        View view2;
        if (this.mTarget == null) {
            return;
        }
        RefreshStyle refreshStyle = this.mRefreshStyle;
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i3 = iArr[refreshStyle.ordinal()];
        if (i3 != 1) {
            if (i3 != 2) {
                View view3 = this.mTarget;
                Intrinsics.checkNotNull(view3);
                view3.offsetTopAndBottom(i2);
                view2 = this.mRefreshView;
            } else {
                view2 = this.mTarget;
                Intrinsics.checkNotNull(view2);
            }
            view2.offsetTopAndBottom(i2);
            view = this.mTarget;
            Intrinsics.checkNotNull(view);
        } else {
            this.mRefreshView.offsetTopAndBottom(i2);
            view = this.mRefreshView;
        }
        float top = view.getTop();
        this.mTargetOrRefreshViewOffsetY = top;
        Intrinsics.stringPlus("current offset", Float.valueOf(top));
        if (iArr[this.mRefreshStyle.ordinal()] == 1) {
            refreshCallbacks = (RefreshCallbacks) this.mRefreshView;
            f2 = this.mTargetOrRefreshViewOffsetY;
            f3 = (f2 - this.mRefreshInitialOffset) / this.mRefreshTargetOffset;
        } else {
            refreshCallbacks = (RefreshCallbacks) this.mRefreshView;
            f2 = this.mTargetOrRefreshViewOffsetY;
            f3 = f2 / this.mRefreshTargetOffset;
        }
        refreshCallbacks.pullProgress(f2, f3);
        if (this.mRefreshView.getVisibility() != 0) {
            this.mRefreshView.setVisibility(0);
        }
        invalidate();
    }

    private final void setTargetOrRefreshViewToInitial() {
        setTargetOrRefreshViewOffsetY((int) ((WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1 ? this.mRefreshInitialOffset : 0) - this.mTargetOrRefreshViewOffsetY));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    @NotNull
    /* renamed from: a */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    @NotNull
    /* renamed from: b */
    public LayoutParams generateLayoutParams(@NotNull ViewGroup.LayoutParams p2) {
        Intrinsics.checkNotNullParameter(p2, "p");
        return new LayoutParams(p2);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(@NotNull ViewGroup.LayoutParams p2) {
        Intrinsics.checkNotNullParameter(p2, "p");
        return p2 instanceof LayoutParams;
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(@NotNull MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        int actionMasked = ev.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            onStopNestedScroll(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.view.ViewGroup
    @NotNull
    public LayoutParams generateLayoutParams(@NotNull AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            int i4 = this.mRefreshViewIndex;
            return i4 < 0 ? i3 : i3 == i2 - 1 ? i4 : i3 >= i4 ? i3 + 1 : i3;
        }
        int i5 = this.mRefreshViewIndex;
        return i5 < 0 ? i3 : i3 == 0 ? i5 : i3 <= i5 ? i3 - 1 : i3;
    }

    @Override // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        reset();
        clearAnimation();
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(@NotNull MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        ensureTarget();
        if (this.mTarget == null) {
            return false;
        }
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            if (!isEnabled() || canChildScrollUp(this.mTarget) || this.mIsRefreshing || this.mNestedScrollInProgress) {
                return false;
            }
        } else if (!isEnabled() || (canChildScrollUp(this.mTarget) && !this.mDispatchTargetTouchDown)) {
            return false;
        }
        int actionMasked = ev.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    int i2 = this.mActivePointerId;
                    if (i2 == -1) {
                        return false;
                    }
                    float motionEventY = getMotionEventY(ev, i2);
                    if (motionEventY == -1.0f) {
                        return false;
                    }
                    initDragStatus(motionEventY);
                } else if (actionMasked != 3) {
                    if (actionMasked == 6) {
                        onSecondaryPointerUp(ev);
                    }
                }
            }
            this.mIsBeingDragged = false;
            this.mActivePointerId = -1;
        } else {
            int pointerId = ev.getPointerId(0);
            this.mActivePointerId = pointerId;
            this.mIsBeingDragged = false;
            float motionEventY2 = getMotionEventY(ev, pointerId);
            if (motionEventY2 == -1.0f) {
                return false;
            }
            if (this.mAnimateToRefreshingAnimation.hasEnded() && this.mAnimateToStartAnimation.hasEnded()) {
                this.mIsAnimatingToStart = false;
            }
            this.mInitialDownY = motionEventY2;
            this.mInitialScrollY = this.mTargetOrRefreshViewOffsetY;
            this.mDispatchTargetTouchDown = false;
        }
        return this.mIsBeingDragged;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (getChildCount() == 0) {
            return;
        }
        ensureTarget();
        if (this.mTarget == null) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int reviseTargetLayoutTop = reviseTargetLayoutTop(getPaddingTop());
        int paddingLeft = getPaddingLeft();
        int paddingLeft2 = ((paddingLeft + measuredWidth) - getPaddingLeft()) - getPaddingRight();
        int paddingTop = ((measuredHeight + reviseTargetLayoutTop) - getPaddingTop()) - getPaddingBottom();
        try {
            View view = this.mTarget;
            Intrinsics.checkNotNull(view);
            view.layout(paddingLeft, reviseTargetLayoutTop, paddingLeft2, paddingTop);
        } catch (Exception e2) {
            String str = this.logTag;
            Log.e(str, "error: ignored=" + e2 + TokenParser.SP + e2.getStackTrace());
        }
        int reviseRefreshViewLayoutTop = reviseRefreshViewLayoutTop((int) this.mRefreshInitialOffset);
        this.mRefreshView.layout((measuredWidth - this.mRefreshView.getMeasuredWidth()) / 2, reviseRefreshViewLayoutTop, (measuredWidth + this.mRefreshView.getMeasuredWidth()) / 2, this.mRefreshView.getMeasuredHeight() + reviseRefreshViewLayoutTop);
        StringBuilder sb = new StringBuilder();
        sb.append("onLayout: ");
        sb.append(i2);
        sb.append(" : ");
        sb.append(i3);
        sb.append(" : ");
        sb.append(i4);
        sb.append(" : ");
        sb.append(i5);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        ensureTarget();
        if (this.mTarget == null) {
            return;
        }
        measureTarget();
        measureRefreshView(i2, i3);
        if (!this.mRefreshViewMeasured && !this.mUsingCustomRefreshInitialOffset) {
            int i4 = WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()];
            if (i4 == 1) {
                float f2 = -this.mRefreshView.getMeasuredHeight();
                this.mRefreshInitialOffset = f2;
                this.mTargetOrRefreshViewOffsetY = f2;
            } else if (i4 != 2) {
                this.mTargetOrRefreshViewOffsetY = 0.0f;
                this.mRefreshInitialOffset = -this.mRefreshView.getMeasuredHeight();
            } else {
                this.mRefreshInitialOffset = 0.0f;
                this.mTargetOrRefreshViewOffsetY = 0.0f;
            }
        }
        if (!this.mRefreshViewMeasured && !this.mUsingCustomRefreshTargetOffset && this.mRefreshTargetOffset < this.mRefreshView.getMeasuredHeight()) {
            this.mRefreshTargetOffset = this.mRefreshView.getMeasuredHeight();
        }
        this.mRefreshViewMeasured = true;
        this.mRefreshViewIndex = -1;
        int i5 = 0;
        int childCount = getChildCount();
        if (childCount <= 0) {
            return;
        }
        while (true) {
            int i6 = i5 + 1;
            if (getChildAt(i5) == this.mRefreshView) {
                this.mRefreshViewIndex = i5;
                return;
            } else if (i6 >= childCount) {
                return;
            } else {
                i5 = i6;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(@NotNull View target, float f2, float f3, boolean z) {
        Intrinsics.checkNotNullParameter(target, "target");
        return dispatchNestedFling(f2, f3, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(@NotNull View target, float f2, float f3) {
        Intrinsics.checkNotNullParameter(target, "target");
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(@NotNull View target, int i2, int i3, @NotNull int[] consumed) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(consumed, "consumed");
        if (i3 > 0) {
            float f2 = this.mTotalUnconsumed;
            if (f2 > 0.0f) {
                float f3 = i3;
                if (f3 > f2) {
                    consumed[1] = i3 - ((int) f2);
                    this.mTotalUnconsumed = 0.0f;
                } else {
                    this.mTotalUnconsumed = f2 - f3;
                    consumed[1] = i3;
                }
                moveSpinner(this.mTotalUnconsumed);
            }
        }
        int[] iArr = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i2 - consumed[0], i3 - consumed[1], iArr, null)) {
            consumed[0] = consumed[0] + iArr[0];
            consumed[1] = consumed[1] + iArr[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(@NotNull View target, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(target, "target");
        dispatchNestedScroll(i2, i3, i4, i5, this.mParentOffsetInWindow);
        int i6 = i5 + this.mParentOffsetInWindow[1];
        if (i6 < 0) {
            float abs = this.mTotalUnconsumed + Math.abs(i6);
            this.mTotalUnconsumed = abs;
            moveSpinner(abs);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(@NotNull View child, @NotNull View target, int i2) {
        Intrinsics.checkNotNullParameter(child, "child");
        Intrinsics.checkNotNullParameter(target, "target");
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, i2);
        startNestedScroll(i2 & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(@NotNull View child, @NotNull View target, int i2) {
        Intrinsics.checkNotNullParameter(child, "child");
        Intrinsics.checkNotNullParameter(target, "target");
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            if (!isEnabled() || !canChildScrollUp(this.mTarget) || this.mIsRefreshing || (i2 & 2) == 0) {
                return false;
            }
        } else if (!isEnabled() || !canChildScrollUp(this.mTarget) || (i2 & 2) == 0) {
            return false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(@NotNull View target) {
        Intrinsics.checkNotNullParameter(target, "target");
        this.mNestedScrollingParentHelper.onStopNestedScroll(target);
        this.mNestedScrollInProgress = false;
        if (this.mTotalUnconsumed > 0.0f) {
            finishSpinner();
            this.mTotalUnconsumed = 0.0f;
        }
        stopNestedScroll();
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NotNull MotionEvent ev) {
        float f2;
        StringBuilder sb;
        float f3;
        Intrinsics.checkNotNullParameter(ev, "ev");
        ensureTarget();
        if (this.mTarget == null) {
            return false;
        }
        if (WhenMappings.$EnumSwitchMapping$0[this.mRefreshStyle.ordinal()] == 1) {
            if (!isEnabled() || canChildScrollUp(this.mTarget) || this.mNestedScrollInProgress) {
                return false;
            }
        } else if (!isEnabled() || (canChildScrollUp(this.mTarget) && !this.mDispatchTargetTouchDown)) {
            return false;
        }
        if (this.mRefreshStyle == RefreshStyle.FLOAT && (canChildScrollUp(this.mTarget) || this.mNestedScrollInProgress)) {
            return false;
        }
        int action = ev.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    int i2 = this.mActivePointerId;
                    if (i2 == -1) {
                        return false;
                    }
                    float motionEventY = getMotionEventY(ev, i2);
                    if (motionEventY == -1.0f) {
                        return false;
                    }
                    if (this.mIsAnimatingToStart) {
                        f2 = getTargetOrRefreshViewTop();
                        this.mInitialMotionY = motionEventY;
                        this.mInitialScrollY = f2;
                        sb = new StringBuilder();
                        sb.append("animateToStart overscrollY ");
                        sb.append(f2);
                        sb.append(" -- ");
                        f3 = this.mInitialMotionY;
                    } else {
                        f2 = (motionEventY - this.mInitialMotionY) + this.mInitialScrollY;
                        sb = new StringBuilder();
                        sb.append("overscrollY ");
                        sb.append(f2);
                        sb.append(" --");
                        sb.append(this.mInitialMotionY);
                        sb.append(" -- ");
                        f3 = this.mInitialScrollY;
                    }
                    sb.append(f3);
                    if (this.mIsRefreshing) {
                        if (f2 > 0.0f) {
                            if (f2 > 0.0f && f2 < this.mRefreshTargetOffset && this.mDispatchTargetTouchDown) {
                                ev = MotionEvent.obtain(ev);
                                ev.setAction(3);
                                this.mDispatchTargetTouchDown = false;
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("moveSpinner refreshing -- ");
                            sb2.append(this.mInitialScrollY);
                            sb2.append(" -- ");
                            sb2.append(motionEventY - this.mInitialMotionY);
                        } else if (!this.mDispatchTargetTouchDown) {
                            ev = MotionEvent.obtain(ev);
                            ev.setAction(0);
                            this.mDispatchTargetTouchDown = true;
                        }
                        View view = this.mTarget;
                        Intrinsics.checkNotNull(view);
                        view.dispatchTouchEvent(ev);
                        StringBuilder sb22 = new StringBuilder();
                        sb22.append("moveSpinner refreshing -- ");
                        sb22.append(this.mInitialScrollY);
                        sb22.append(" -- ");
                        sb22.append(motionEventY - this.mInitialMotionY);
                    } else if (!this.mIsBeingDragged) {
                        initDragStatus(motionEventY);
                    } else if (f2 <= 0.0f) {
                        return false;
                    }
                    moveSpinner(f2);
                } else if (action != 3) {
                    if (action == 5) {
                        onNewerPointerDown(ev);
                    } else if (action == 6) {
                        onSecondaryPointerUp(ev);
                    }
                }
            }
            int i3 = this.mActivePointerId;
            if (i3 != -1) {
                if (!(getMotionEventY(ev, i3) == -1.0f)) {
                    if (!this.mIsRefreshing && !this.mIsAnimatingToStart) {
                        resetTouchEvent();
                        finishSpinner();
                        return false;
                    }
                    if (this.mDispatchTargetTouchDown) {
                        View view2 = this.mTarget;
                        Intrinsics.checkNotNull(view2);
                        view2.dispatchTouchEvent(ev);
                    }
                    resetTouchEvent();
                    return false;
                }
            }
            resetTouchEvent();
            return false;
        }
        this.mActivePointerId = ev.getPointerId(0);
        this.mIsBeingDragged = false;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.mTarget instanceof AbsListView)) {
            View view = this.mTarget;
            if (view != null) {
                Intrinsics.checkNotNull(view);
                if (!ViewCompat.isNestedScrollingEnabled(view)) {
                    return;
                }
            }
            super.requestDisallowInterceptTouchEvent(z);
        }
    }

    public final void setAnimateToRefreshDuration(int i2) {
        this.mAnimateToRefreshDuration = i2;
    }

    public final void setAnimateToRefreshInterpolator(@Nullable Interpolator interpolator) {
        Objects.requireNonNull(interpolator, "The animateToRefreshInterpolator can't be null");
        this.mAnimateToRefreshInterpolator = interpolator;
    }

    public final void setAnimateToStartDuration(int i2) {
        this.mAnimateToStartDuration = i2;
    }

    public final void setAnimateToStartInterpolator(@Nullable Interpolator interpolator) {
        Objects.requireNonNull(interpolator, "The animateToStartInterpolator can't be null");
        this.mAnimateToStartInterpolator = interpolator;
    }

    public final void setDragDistanceConverter(@NotNull SSDragDistanceConverter dragDistanceConverter) {
        Intrinsics.checkNotNullParameter(dragDistanceConverter, "dragDistanceConverter");
        this.mDragDistanceConverter = dragDistanceConverter;
    }

    public final void setGifAnimation(int i2) {
        View view = this.mRefreshView;
        if (!(view instanceof SSAnimationView)) {
            throw new Exception(this.lottieAnimViewGifMethodError);
        }
        view.setBackgroundResource(i2);
    }

    public final void setImageAsRefresh(int i2) {
        View view = this.mRefreshView;
        if (!(view instanceof SSAnimationView)) {
            throw new Exception(this.lottieAnimViewGifMethodError);
        }
        ((SSAnimationView) view).setImageResource(i2);
    }

    public final void setLottieAnimation(@NotNull String assetFileName) {
        Intrinsics.checkNotNullParameter(assetFileName, "assetFileName");
        this.mLottieAnimationAssetFileName = assetFileName;
        View view = this.mRefreshView;
        if (!(view instanceof SSLottieAnimationView)) {
            throw new Exception(this.ssAnimViewLottieMethodError);
        }
        ((SSLottieAnimationView) view).setAnimation(assetFileName);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z);
    }

    public final void setOnRefreshListener(@Nullable OnRefreshListener onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
    }

    public final void setRefreshInitialOffset(float f2) {
        this.mRefreshInitialOffset = f2;
        this.mUsingCustomRefreshInitialOffset = true;
        requestLayout();
    }

    public final void setRefreshStyle(@NotNull RefreshStyle refreshStyle) {
        Intrinsics.checkNotNullParameter(refreshStyle, "refreshStyle");
        this.mRefreshStyle = refreshStyle;
    }

    public final void setRefreshTargetOffset(float f2) {
        this.mRefreshTargetOffset = f2;
        this.mUsingCustomRefreshTargetOffset = true;
        requestLayout();
    }

    @SuppressLint({"ResourceType"})
    public final void setRefreshView(@NotNull View refreshView) {
        Intrinsics.checkNotNullParameter(refreshView, "refreshView");
        View view = this.mRefreshView;
        if (view == refreshView) {
            return;
        }
        if (view.getParent() != null) {
            ViewParent parent = this.mRefreshView.getParent();
            Objects.requireNonNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent).removeView(this.mRefreshView);
        }
        refreshView.setVisibility(8);
        addView(refreshView, this.mRefreshLayoutParams);
        if (!(refreshView instanceof SSAnimationView)) {
            if (!(refreshView instanceof SSLottieAnimationView)) {
                throw new ClassCastException("Need SSLottieAnimationView or SSGifAnimationView as RefreshView");
            }
            ((SSLottieAnimationView) refreshView).setAnimation(this.mLottieAnimationAssetFileName);
        }
        this.mRefreshView = refreshView;
    }

    public final void setRefreshViewParams(@NotNull ViewGroup.LayoutParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        this.mRefreshLayoutParams = params;
        this.mRefreshView.setLayoutParams(new ViewGroup.MarginLayoutParams(params.width, params.height));
    }

    public final void setRefreshing(boolean z) {
        if (!z || this.mIsRefreshing == z) {
            setRefreshing(z, false);
            return;
        }
        this.mIsRefreshing = z;
        this.mNotifyListener = false;
        animateToRefreshingPosition((int) this.mTargetOrRefreshViewOffsetY, this.mRefreshingListener);
    }

    public final void setRepeatCount(@NotNull RepeatCount count) {
        Intrinsics.checkNotNullParameter(count, "count");
        View view = this.mRefreshView;
        if (!(view instanceof SSLottieAnimationView)) {
            throw new Exception(this.ssAnimViewLottieMethodError);
        }
        ((SSLottieAnimationView) view).setRepeatCount(count.getCount());
    }

    public final void setRepeatMode(@NotNull RepeatMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        View view = this.mRefreshView;
        if (!(view instanceof SSLottieAnimationView)) {
            throw new Exception(this.ssAnimViewLottieMethodError);
        }
        ((SSLottieAnimationView) view).setRepeatMode(mode.getMode());
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i2) {
        return this.mNestedScrollingChildHelper.startNestedScroll(i2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }
}
