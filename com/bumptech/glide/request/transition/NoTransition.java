package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes.dex */
public class NoTransition<R> implements Transition<R> {

    /* renamed from: a  reason: collision with root package name */
    static final NoTransition f4834a = new NoTransition();
    private static final TransitionFactory<?> NO_ANIMATION_FACTORY = new NoAnimationFactory();

    /* loaded from: classes.dex */
    public static class NoAnimationFactory<R> implements TransitionFactory<R> {
        @Override // com.bumptech.glide.request.transition.TransitionFactory
        public Transition<R> build(DataSource dataSource, boolean z) {
            return NoTransition.f4834a;
        }
    }

    public static <R> Transition<R> get() {
        return f4834a;
    }

    public static <R> TransitionFactory<R> getFactory() {
        return (TransitionFactory<R>) NO_ANIMATION_FACTORY;
    }

    @Override // com.bumptech.glide.request.transition.Transition
    public boolean transition(Object obj, Transition.ViewAdapter viewAdapter) {
        return false;
    }
}