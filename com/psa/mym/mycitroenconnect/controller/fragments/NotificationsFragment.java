package com.psa.mym.mycitroenconnect.controller.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.BusBaseFragment;
import com.psa.mym.mycitroenconnect.R;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import com.psa.mym.mycitroenconnect.controller.activities.MainActivity;
import com.psa.mym.mycitroenconnect.controller.adapters.NotificationTypeAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.NotificationsAdapter;
import com.psa.mym.mycitroenconnect.controller.adapters.OnNotificationClickListener;
import com.psa.mym.mycitroenconnect.controller.adapters.OnNotificationTypeClickListener;
import com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertFragment;
import com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface;
import com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener;
import com.psa.mym.mycitroenconnect.controller.dialog.SingleCustomDialog;
import com.psa.mym.mycitroenconnect.model.ErrorResponse;
import com.psa.mym.mycitroenconnect.model.MyNotificationModel;
import com.psa.mym.mycitroenconnect.model.NotificationType;
import com.psa.mym.mycitroenconnect.model.notification.Notification;
import com.psa.mym.mycitroenconnect.model.notification.NotificationResponse;
import com.psa.mym.mycitroenconnect.services.FCMService;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import com.psa.mym.mycitroenconnect.utils.ExtensionsKt;
import com.psa.mym.mycitroenconnect.utils.Logger;
import com.psa.mym.mycitroenconnect.utils.shared_preference.SharedPref;
import com.psa.mym.mycitroenconnect.views.recycler_view.LoadMoreScrollListener;
import com.psa.mym.mycitroenconnect.views.recycler_view.OnLoadMoreListener;
import com.psa.mym.mycitroenconnect.views.recycler_view.SwipeToDeleteCallback;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import com.simform.refresh.SSPullToRefreshLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NotificationsFragment extends BusBaseFragment implements SSPullToRefreshLayout.OnRefreshListener, OnNotificationTypeClickListener, OnNotificationClickListener, OnLoadMoreListener {
    private int notificationCount;
    @Nullable
    private NotificationTypeAdapter notificationTypeAdapter;
    @Nullable
    private NotificationsAdapter notificationsAdapter;
    private int pageNumber;
    @Nullable
    private MainActivity parentActivity;
    @Nullable
    private LoadMoreScrollListener scrollListener;
    private Skeleton skeleton;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    private List<NotificationType> types = new ArrayList();
    @NotNull
    private ArrayList<Notification> notifications = new ArrayList<>();
    @NotNull
    private String notificationType = "All";

    private final void displayData() {
        Skeleton skeleton = this.skeleton;
        if (skeleton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skeleton");
            skeleton = null;
        }
        if (skeleton.isSkeleton()) {
            Skeleton skeleton2 = this.skeleton;
            if (skeleton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("skeleton");
                skeleton2 = null;
            }
            ExtensionsKt.showData$default(skeleton2, 0L, 1, null);
        }
    }

    private final void displayLoading() {
        LinearLayoutCompat llNotification = (LinearLayoutCompat) _$_findCachedViewById(R.id.llNotification);
        Intrinsics.checkNotNullExpressionValue(llNotification, "llNotification");
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Skeleton createSkeleton = SkeletonLayoutUtils.createSkeleton(llNotification, ExtensionsKt.skeletonConfig(requireContext));
        createSkeleton.showSkeleton();
        this.skeleton = createSkeleton;
    }

    private final void getMoreNotificationList() {
        if (this.notifications.size() < this.notificationCount) {
            this.pageNumber++;
            NotificationsAdapter notificationsAdapter = this.notificationsAdapter;
            if (notificationsAdapter != null) {
                notificationsAdapter.addLoadingView();
            }
            SharedPref.Companion companion = SharedPref.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            String vinNumber = companion.getVinNumber(requireContext);
            FCMService fCMService = new FCMService();
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            FCMService.getNotificationList$default(fCMService, requireActivity, vinNumber, this.pageNumber, 0, getPriority(), 8, null);
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private final void getNotificationList() {
        this.notifications.clear();
        NotificationsAdapter notificationsAdapter = this.notificationsAdapter;
        if (notificationsAdapter != null && notificationsAdapter != null) {
            notificationsAdapter.notifyDataSetChanged();
        }
        this.pageNumber = 0;
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String vinNumber = companion.getVinNumber(requireContext);
        FCMService fCMService = new FCMService();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        FCMService.getNotificationList$default(fCMService, requireActivity, vinNumber, 0, 0, getPriority(), 12, null);
    }

    private final int getPriority() {
        if (this.notificationType.length() > 0) {
            String str = this.notificationType;
            switch (str.hashCode()) {
                case -1506188017:
                    if (str.equals("General Alerts")) {
                        return 2;
                    }
                    break;
                case 65921:
                    str.equals("All");
                    break;
                case 107099704:
                    if (str.equals("Critical Alerts")) {
                        return 1;
                    }
                    break;
                case 1480045700:
                    if (str.equals("Maintenance Alerts")) {
                        return 3;
                    }
                    break;
            }
        }
        return -1;
    }

    private final void init() {
        MainActivity mainActivity;
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            mainActivity = null;
        } else {
            FragmentActivity activity = getActivity();
            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.psa.mym.mycitroenconnect.controller.activities.MainActivity");
            mainActivity = (MainActivity) activity;
        }
        this.parentActivity = mainActivity;
        SSPullToRefreshLayout sSPullToRefreshLayout = (SSPullToRefreshLayout) _$_findCachedViewById(R.id.swipeRefresh);
        if (sSPullToRefreshLayout != null) {
            sSPullToRefreshLayout.setLottieAnimation("loading_indicator.json");
            sSPullToRefreshLayout.setRepeatMode(SSPullToRefreshLayout.RepeatMode.REVERSE);
            sSPullToRefreshLayout.setRepeatCount(SSPullToRefreshLayout.RepeatCount.INFINITE);
            sSPullToRefreshLayout.setOnRefreshListener(this);
        }
        initNotificationType();
        initNotifications();
    }

    private final void initNotificationType() {
        this.types.add(new NotificationType("All", true));
        this.types.add(new NotificationType("Critical Alerts", false));
        this.types.add(new NotificationType("General Alerts", false));
        this.types.add(new NotificationType("Maintenance Alerts", false));
        this.notificationTypeAdapter = new NotificationTypeAdapter(this.types, this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvNotificationType);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(this.notificationTypeAdapter);
            recyclerView.setItemAnimator(null);
        }
    }

    private final void initNotifications() {
        this.notificationsAdapter = new NotificationsAdapter(this.notifications, this);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvNotification);
        if (recyclerView != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(false);
            recyclerView.setAdapter(this.notificationsAdapter);
            recyclerView.setItemAnimator(null);
            LoadMoreScrollListener loadMoreScrollListener = new LoadMoreScrollListener(linearLayoutManager);
            loadMoreScrollListener.setOnLoadMoreListener(this);
            recyclerView.addOnScrollListener(loadMoreScrollListener);
            this.scrollListener = loadMoreScrollListener;
        }
        SharedPref.Companion companion = SharedPref.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isGuestUser(requireContext)) {
            return;
        }
        setUpItemTouchHelper();
    }

    private final void setUpItemTouchHelper() {
        final Context requireContext = requireContext();
        new ItemTouchHelper(new SwipeToDeleteCallback(requireContext) { // from class: com.psa.mym.mycitroenconnect.controller.fragments.NotificationsFragment$setUpItemTouchHelper$itemTouchHelper$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(requireContext);
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i2) {
                super.onSelectedChanged(viewHolder, i2);
                ((SSPullToRefreshLayout) NotificationsFragment.this._$_findCachedViewById(R.id.swipeRefresh)).setEnabled(i2 != 1);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int i2) {
                ArrayList arrayList;
                String notificationId;
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                int absoluteAdapterPosition = viewHolder.getAbsoluteAdapterPosition();
                AppUtil.Companion companion = AppUtil.Companion;
                Context requireContext2 = NotificationsFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                companion.showDialog(requireContext2);
                arrayList = NotificationsFragment.this.notifications;
                Notification notification = (Notification) arrayList.get(absoluteAdapterPosition);
                if (notification == null || (notificationId = notification.getNotificationId()) == null) {
                    return;
                }
                NotificationsFragment notificationsFragment = NotificationsFragment.this;
                FCMService fCMService = new FCMService();
                Context requireContext3 = notificationsFragment.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                fCMService.deleteNotification(requireContext3, notificationId, absoluteAdapterPosition);
            }
        }).attachToRecyclerView((RecyclerView) _$_findCachedViewById(R.id.rvNotification));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004e, code lost:
        if (r11.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_LowFuelAlert) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0057, code lost:
        if (r11.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_CrashAlert) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
        r11 = kotlin.jvm.internal.Intrinsics.areEqual(r9.getPriority(), "1");
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003c, code lost:
        if (r11.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_TowAwayAlert) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0045, code lost:
        if (r11.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_IntrusionAlert) != false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showNotificationDetails(Notification notification) {
        boolean z;
        MyNotificationModel myNotificationModel = new MyNotificationModel("", notification.getTitle(), notification.getBody(), "", ExtensionsKt.toSimpleString(new Date(notification.getSignalTimeStamp()), AppConstants.DISPLAY_DATE_FORMAT), "", notification.getPriority(), "");
        String alertState = myNotificationModel.getAlertState();
        switch (alertState.hashCode()) {
            case -894483947:
                break;
            case 93415538:
                break;
            case 970664341:
                break;
            case 1881199922:
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            new SingleCustomDialog(requireContext(), myNotificationModel, getString(uat.psa.mym.mycitroenconnect.R.string.ok), new OnDialogOkClickListener() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.NotificationsFragment$showNotificationDetails$2
                @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener
                public void onDialogCancelClick(@Nullable Dialog dialog, @Nullable MyNotificationModel myNotificationModel2) {
                }

                @Override // com.psa.mym.mycitroenconnect.controller.dialog.OnDialogOkClickListener
                public void onDialogOkClick(@Nullable Dialog dialog, @Nullable MyNotificationModel myNotificationModel2) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });
            return;
        }
        FullScreenAlertFragment.Companion companion = FullScreenAlertFragment.Companion;
        FullScreenAlertFragment newInstance = companion.newInstance(myNotificationModel.getAlertState());
        newInstance.show(getChildFragmentManager(), companion.getTAG());
        newInstance.setOnFullScreenAlertListener(new FullScreenAlertInterface() { // from class: com.psa.mym.mycitroenconnect.controller.fragments.NotificationsFragment$showNotificationDetails$1
            /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
                if (r4.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_IntrusionAlert) != false) goto L6;
             */
            /* JADX WARN: Code restructure failed: missing block: B:19:0x0037, code lost:
                if (r4.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_CrashAlert) == false) goto L23;
             */
            /* JADX WARN: Code restructure failed: missing block: B:21:0x003a, code lost:
                r4 = r3.f10508a.parentActivity;
             */
            /* JADX WARN: Code restructure failed: missing block: B:22:0x0040, code lost:
                if (r4 == null) goto L11;
             */
            /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
                r4.navigateToLocateFragment();
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:?, code lost:
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
                if (r4.equals(com.psa.mym.mycitroenconnect.common.AppConstants.Notification_TowAwayAlert) == false) goto L23;
             */
            @Override // com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onAlertBtnClick(@Nullable String str) {
                MainActivity mainActivity;
                if (str != null) {
                    switch (str.hashCode()) {
                        case -894483947:
                            break;
                        case 93415538:
                            if (str.equals(AppConstants.Notification_LowFuelAlert)) {
                                mainActivity = NotificationsFragment.this.parentActivity;
                                if (mainActivity != null) {
                                    mainActivity.navigateToChargingStationFragment();
                                    return;
                                }
                                return;
                            }
                            break;
                        case 970664341:
                            break;
                        case 1881199922:
                            break;
                    }
                }
                Logger logger = Logger.INSTANCE;
                logger.e("AlertState " + str);
            }

            @Override // com.psa.mym.mycitroenconnect.controller.dialog.FullScreenAlertInterface
            public void onAlertDismissClick() {
            }
        });
        newInstance.setCancelable(false);
    }

    private final void showNotificationList(boolean z) {
        if (z) {
            RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvNotification);
            if (recyclerView != null) {
                ExtensionsKt.show(recyclerView);
            }
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvNoNotification);
            if (textView != null) {
                ExtensionsKt.hide(textView);
                return;
            }
            return;
        }
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.rvNotification);
        if (recyclerView2 != null) {
            ExtensionsKt.show(recyclerView2);
        }
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvNoNotification);
        if (textView2 != null) {
            ExtensionsKt.show(textView2);
        }
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment
    @Nullable
    public View _$_findCachedViewById(int i2) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i2)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @Subscribe
    @SuppressLint({"NotifyDataSetChanged"})
    public final void getMessage(@NotNull NotificationResponse event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.pageNumber == 0) {
            this.notificationCount = event.getCount();
            displayData();
            showNotificationList(true);
            MainActivity mainActivity = this.parentActivity;
            if (mainActivity != null) {
                mainActivity.getUnreadNotificationCount();
            }
            SSPullToRefreshLayout sSPullToRefreshLayout = (SSPullToRefreshLayout) _$_findCachedViewById(R.id.swipeRefresh);
            if (sSPullToRefreshLayout != null) {
                sSPullToRefreshLayout.setRefreshing(false);
            }
        } else {
            NotificationsAdapter notificationsAdapter = this.notificationsAdapter;
            if (notificationsAdapter != null) {
                notificationsAdapter.removeLoadingView();
            }
        }
        LoadMoreScrollListener loadMoreScrollListener = this.scrollListener;
        if (loadMoreScrollListener != null) {
            loadMoreScrollListener.setLoaded();
        }
        NotificationsAdapter notificationsAdapter2 = this.notificationsAdapter;
        if (notificationsAdapter2 != null) {
            notificationsAdapter2.addNotifications(event.getNotifications());
        }
    }

    @Subscribe
    public final void getResponse(@NotNull ErrorResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (Intrinsics.areEqual(response.getApiName(), AppConstants.API_NAME_NOTIF_LIST)) {
            SSPullToRefreshLayout sSPullToRefreshLayout = (SSPullToRefreshLayout) _$_findCachedViewById(R.id.swipeRefresh);
            if (sSPullToRefreshLayout != null) {
                sSPullToRefreshLayout.setRefreshing(false);
            }
            displayData();
            showNotificationList(false);
            MainActivity mainActivity = this.parentActivity;
            if (mainActivity != null) {
                mainActivity.getUnreadNotificationCount();
            }
        }
    }

    @Subscribe
    public final void getResponse(@NotNull FCMService.NotificationServiceResponse response) {
        MainActivity mainActivity;
        Intrinsics.checkNotNullParameter(response, "response");
        NotificationsAdapter notificationsAdapter = this.notificationsAdapter;
        if (notificationsAdapter != null) {
            String apiName = response.getApiName();
            if (Intrinsics.areEqual(apiName, "ReadNotification")) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                ExtensionsKt.showToast(requireContext, response.getMessage());
                notificationsAdapter.readNotification(response.getPosition());
                mainActivity = this.parentActivity;
                if (mainActivity == null) {
                    return;
                }
            } else if (!Intrinsics.areEqual(apiName, "DeleteNotification")) {
                Logger logger = Logger.INSTANCE;
                logger.e("Response: " + response);
                return;
            } else {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                ExtensionsKt.showToast(requireContext2, "Notification deleted successfully");
                notificationsAdapter.deleteNotification(response.getPosition());
                mainActivity = this.parentActivity;
                if (mainActivity == null) {
                    return;
                }
            }
            mainActivity.getUnreadNotificationCount();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(uat.psa.mym.mycitroenconnect.R.layout.fragment_notifications, viewGroup, false);
    }

    @Override // com.psa.mym.mycitroenconnect.BusBaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.psa.mym.mycitroenconnect.views.recycler_view.OnLoadMoreListener
    public void onLoadMore() {
        getMoreNotificationList();
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.OnNotificationClickListener
    public void onNotificationClick(int i2, @NotNull Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        if (!notification.getRead()) {
            SharedPref.Companion companion = SharedPref.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (!companion.isGuestUser(requireContext)) {
                AppUtil.Companion companion2 = AppUtil.Companion;
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                companion2.showDialog(requireContext2);
                FCMService fCMService = new FCMService();
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                fCMService.readNotification(requireContext3, notification.getNotificationId(), i2);
            }
        }
        showNotificationDetails(notification);
    }

    @Override // com.psa.mym.mycitroenconnect.controller.adapters.OnNotificationTypeClickListener
    @SuppressLint({"NotifyDataSetChanged"})
    public void onNotificationTypeClick(int i2) {
        int i3 = 0;
        for (NotificationType notificationType : this.types) {
            int i4 = i3 + 1;
            notificationType.setSelected(i3 == i2);
            i3 = i4;
        }
        NotificationTypeAdapter notificationTypeAdapter = this.notificationTypeAdapter;
        if (notificationTypeAdapter != null) {
            notificationTypeAdapter.notifyDataSetChanged();
        }
        this.notificationType = this.types.get(i2).getNotificationType();
        getNotificationList();
    }

    @Override // com.simform.refresh.SSPullToRefreshLayout.OnRefreshListener
    public void onRefresh() {
        SSPullToRefreshLayout sSPullToRefreshLayout = (SSPullToRefreshLayout) _$_findCachedViewById(R.id.swipeRefresh);
        if (sSPullToRefreshLayout != null) {
            sSPullToRefreshLayout.setRefreshing(true);
        }
        getNotificationList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        displayLoading();
        getNotificationList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        init();
    }
}
