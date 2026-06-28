package com.tapcard.app.data.repository;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000bH\u0016J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\f\u0010\u001b\u001a\u00020\u000f*\u00020\u001cH\u0002J\f\u0010\u001d\u001a\u00020\u001c*\u00020\u000fH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/tapcard/app/data/repository/SyncProfileRepositoryImpl;", "Lcom/tapcard/app/domain/repository/ProfileRepository;", "profileDao", "Lcom/tapcard/app/data/local/ProfileDao;", "(Lcom/tapcard/app/data/local/ProfileDao;)V", "_syncStatus", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/tapcard/app/domain/model/SyncStatus;", "client", "Lio/github/jan/supabase/SupabaseClient;", "syncStatus", "Lkotlinx/coroutines/flow/Flow;", "getSyncStatus", "()Lkotlinx/coroutines/flow/Flow;", "getProfile", "Lcom/tapcard/app/domain/model/Profile;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProfileFlow", "saveProfile", "", "profile", "(Lcom/tapcard/app/domain/model/Profile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validateUsernameUniqueness", "", "username", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomainModel", "Lcom/tapcard/app/data/local/ProfileEntity;", "toEntity", "app_debug"})
public final class SyncProfileRepositoryImpl implements com.tapcard.app.domain.repository.ProfileRepository {
    @org.jetbrains.annotations.NotNull
    private final com.tapcard.app.data.local.ProfileDao profileDao = null;
    @org.jetbrains.annotations.Nullable
    private final io.github.jan.supabase.SupabaseClient client = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.tapcard.app.domain.model.SyncStatus> _syncStatus = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.Flow<com.tapcard.app.domain.model.SyncStatus> syncStatus = null;
    
    @javax.inject.Inject
    public SyncProfileRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.tapcard.app.data.local.ProfileDao profileDao) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.tapcard.app.domain.model.SyncStatus> getSyncStatus() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<com.tapcard.app.domain.model.Profile> getProfileFlow() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getProfile(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.tapcard.app.domain.model.Profile> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object saveProfile(@org.jetbrains.annotations.NotNull
    com.tapcard.app.domain.model.Profile profile, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object validateUsernameUniqueness(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    private final com.tapcard.app.domain.model.Profile toDomainModel(com.tapcard.app.data.local.ProfileEntity $this$toDomainModel) {
        return null;
    }
    
    private final com.tapcard.app.data.local.ProfileEntity toEntity(com.tapcard.app.domain.model.Profile $this$toEntity) {
        return null;
    }
}