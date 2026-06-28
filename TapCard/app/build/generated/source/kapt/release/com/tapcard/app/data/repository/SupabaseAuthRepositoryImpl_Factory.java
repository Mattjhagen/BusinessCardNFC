package com.tapcard.app.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class SupabaseAuthRepositoryImpl_Factory implements Factory<SupabaseAuthRepositoryImpl> {
  @Override
  public SupabaseAuthRepositoryImpl get() {
    return newInstance();
  }

  public static SupabaseAuthRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SupabaseAuthRepositoryImpl newInstance() {
    return new SupabaseAuthRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final SupabaseAuthRepositoryImpl_Factory INSTANCE = new SupabaseAuthRepositoryImpl_Factory();
  }
}
