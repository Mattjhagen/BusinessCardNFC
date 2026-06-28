package com.tapcard.app.domain.auth

enum class AuthState {
    UNCONFIGURED,
    LOADING,
    SIGNED_OUT,
    SIGNED_IN,
    ERROR
}
