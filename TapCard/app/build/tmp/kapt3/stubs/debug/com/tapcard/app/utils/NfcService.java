package com.tapcard.app.utils;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0017"}, d2 = {"Lcom/tapcard/app/utils/NfcService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "nfcAdapter", "Landroid/nfc/NfcAdapter;", "getNfcAdapter", "()Landroid/nfc/NfcAdapter;", "nfcAdapter$delegate", "Lkotlin/Lazy;", "createNdefUrlMessage", "Landroid/nfc/NdefMessage;", "url", "", "getNfcState", "Lcom/tapcard/app/utils/NfcState;", "startSharing", "", "activity", "Landroid/app/Activity;", "stopSharing", "", "app_debug"})
public final class NfcService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy nfcAdapter$delegate = null;
    
    @javax.inject.Inject
    public NfcService(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final android.nfc.NfcAdapter getNfcAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.tapcard.app.utils.NfcState getNfcState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.nfc.NdefMessage createNdefUrlMessage(@org.jetbrains.annotations.NotNull
    java.lang.String url) {
        return null;
    }
    
    @kotlin.Suppress(names = {"DEPRECATION"})
    public final boolean startSharing(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.String url) {
        return false;
    }
    
    @kotlin.Suppress(names = {"DEPRECATION"})
    public final void stopSharing(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
}