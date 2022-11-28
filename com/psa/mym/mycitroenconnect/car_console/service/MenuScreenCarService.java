package com.psa.mym.mycitroenconnect.car_console.service;

import androidx.car.app.CarAppService;
import androidx.car.app.Session;
import androidx.car.app.validation.HostValidator;
import com.psa.mym.mycitroenconnect.car_console.session.MenuScreenSession;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes2.dex */
public final class MenuScreenCarService extends CarAppService {
    @Override // androidx.car.app.CarAppService
    @NotNull
    public HostValidator createHostValidator() {
        HostValidator build;
        String str;
        if ((getApplicationInfo().flags & 2) != 0) {
            build = HostValidator.ALLOW_ALL_HOSTS_VALIDATOR;
            str = "{\n            HostValida…HOSTS_VALIDATOR\n        }";
        } else {
            build = new HostValidator.Builder(getApplicationContext()).addAllowedHosts(R.array.hosts_allowlist_sample).build();
            str = "{\n            HostValida…       .build()\n        }";
        }
        Intrinsics.checkNotNullExpressionValue(build, str);
        return build;
    }

    @Override // androidx.car.app.CarAppService
    @NotNull
    public Session onCreateSession() {
        return new MenuScreenSession();
    }
}
