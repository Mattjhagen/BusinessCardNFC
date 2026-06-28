package com.tapcard.app.utils;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00170\u0016J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0014J2\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\f2\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00170\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u001d"}, d2 = {"Lcom/tapcard/app/utils/NfcService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "nfcAdapter", "Landroid/nfc/NfcAdapter;", "getNfcAdapter", "()Landroid/nfc/NfcAdapter;", "nfcAdapter$delegate", "Lkotlin/Lazy;", "createNdefUrlMessage", "Landroid/nfc/NdefMessage;", "url", "", "getNfcState", "Lcom/tapcard/app/utils/NfcState;", "startTagProgramming", "", "activity", "Landroid/app/Activity;", "onResult", "Lkotlin/Function2;", "", "stopTagProgramming", "writeNdefToTag", "tag", "Landroid/nfc/Tag;", "message", "app_release"})
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
    
    /**
     * Starts listening for physical NFC tags using ReaderMode.
     * When a tag is tapped, the provided callback will be executed.
     */
    public final boolean startTagProgramming(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    java.lang.String url, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onResult) {
        return false;
    }
    
    public final void stopTagProgramming(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    private final void writeNdefToTag(android.nfc.Tag tag, android.nfc.NdefMessage message, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onResult) {
    }
}