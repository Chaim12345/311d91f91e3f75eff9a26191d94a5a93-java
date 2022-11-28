package com.psa.mym.mycitroenconnect.controller.fragments.my_trips;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.MyTripsSummaryAdapter;
import com.psa.mym.mycitroenconnect.model.trip.TripResponseDTO;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripSummaryFragment$getMessage$1", f = "TripSummaryFragment.kt", i = {}, l = {372}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class TripSummaryFragment$getMessage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f10573a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ TripSummaryFragment f10574b;

    /* JADX INFO: Access modifiers changed from: package-private */
    @DebugMetadata(c = "com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripSummaryFragment$getMessage$1$1", f = "TripSummaryFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.psa.mym.mycitroenconnect.controller.fragments.my_trips.TripSummaryFragment$getMessage$1$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        int f10575a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ TripSummaryFragment f10576b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(TripSummaryFragment tripSummaryFragment, Continuation continuation) {
            super(2, continuation);
            this.f10576b = tripSummaryFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(this.f10576b, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            MyTripsSummaryAdapter myTripsSummaryAdapter;
            List<TripResponseDTO> list;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.f10575a == 0) {
                ResultKt.throwOnFailure(obj);
                this.f10576b.showTripDetails(true);
                this.f10576b.initTripCards();
                myTripsSummaryAdapter = this.f10576b.cardAdapter;
                if (myTripsSummaryAdapter != null) {
                    list = this.f10576b.tripSummaryDataList;
                    myTripsSummaryAdapter.updateTripList(list);
                }
                this.f10576b.displayData();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TripSummaryFragment$getMessage$1(TripSummaryFragment tripSummaryFragment, Continuation continuation) {
        super(2, continuation);
        this.f10574b = tripSummaryFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new TripSummaryFragment$getMessage$1(this.f10574b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((TripSummaryFragment$getMessage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        List list;
        List list2;
        List list3;
        List list4;
        List list5;
        List list6;
        List list7;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f10573a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity activity = this.f10574b.getActivity();
            list = this.f10574b.tripSummaryDataList;
            int size = list.size();
            for (int i3 = 0; i3 < size && activity != null && this.f10574b.isAdded(); i3++) {
                list2 = this.f10574b.tripSummaryDataList;
                if (((TripResponseDTO) list2.get(i3)).getTripStartLocation().length() > 0) {
                    list6 = this.f10574b.tripSummaryDataList;
                    String[] startAllIdsArray = TextUtils.split(((TripResponseDTO) list6.get(i3)).getTripStartLocation(), ",");
                    Intrinsics.checkNotNullExpressionValue(startAllIdsArray, "startAllIdsArray");
                    if (!(startAllIdsArray.length == 0)) {
                        list7 = this.f10574b.tripSummaryDataList;
                        TripResponseDTO tripResponseDTO = (TripResponseDTO) list7.get(i3);
                        AppUtil.Companion companion = AppUtil.Companion;
                        String str = startAllIdsArray[0];
                        Intrinsics.checkNotNullExpressionValue(str, "startAllIdsArray[0]");
                        double parseDouble = Double.parseDouble(str);
                        String str2 = startAllIdsArray[1];
                        Intrinsics.checkNotNullExpressionValue(str2, "startAllIdsArray[1]");
                        tripResponseDTO.setTripSourceName(companion.getAddressNameString(activity, parseDouble, Double.parseDouble(str2)));
                    }
                }
                list3 = this.f10574b.tripSummaryDataList;
                if (((TripResponseDTO) list3.get(i3)).getTripEndLocation().length() > 0) {
                    list4 = this.f10574b.tripSummaryDataList;
                    String[] endAllIdsArray = TextUtils.split(((TripResponseDTO) list4.get(i3)).getTripEndLocation(), ",");
                    Intrinsics.checkNotNullExpressionValue(endAllIdsArray, "endAllIdsArray");
                    if (!(endAllIdsArray.length == 0)) {
                        list5 = this.f10574b.tripSummaryDataList;
                        TripResponseDTO tripResponseDTO2 = (TripResponseDTO) list5.get(i3);
                        AppUtil.Companion companion2 = AppUtil.Companion;
                        String str3 = endAllIdsArray[0];
                        Intrinsics.checkNotNullExpressionValue(str3, "endAllIdsArray[0]");
                        double parseDouble2 = Double.parseDouble(str3);
                        String str4 = endAllIdsArray[1];
                        Intrinsics.checkNotNullExpressionValue(str4, "endAllIdsArray[1]");
                        tripResponseDTO2.setTripDestName(companion2.getAddressNameString(activity, parseDouble2, Double.parseDouble(str4)));
                    }
                }
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.f10574b, null);
            this.f10573a = 1;
            if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
