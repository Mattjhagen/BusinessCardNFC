package com.tapcard.app.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0003H&J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/tapcard/app/domain/repository/ProfileRepository;", "", "syncStatus", "Lkotlinx/coroutines/flow/Flow;", "Lcom/tapcard/app/domain/model/SyncStatus;", "getSyncStatus", "()Lkotlinx/coroutines/flow/Flow;", "getProfile", "Lcom/tapcard/app/domain/model/Profile;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProfileFlow", "saveProfile", "", "profile", "(Lcom/tapcard/app/domain/model/Profile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateUsernameUniqueness", "", "username", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ProfileRepository {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.tapcard.app.domain.model.Profile> getProfileFlow();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProfile(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.tapcard.app.domain.model.Profile> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object saveProfile(@org.jetbrains.annotations.NotNull
    com.tapcard.app.domain.model.Profile profile, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.tapcard.app.domain.model.SyncStatus> getSyncStatus();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object validateUsernameUniqueness(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}