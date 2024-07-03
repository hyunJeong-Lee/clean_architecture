package com.leehj.domain.util

/*
    API 호출 결과를 나타내는 sealed class
 */
sealed class ApiResult<T> {
    class Success<T>(val data: T): ApiResult<T>()
    class Error<T>(val error: Throwable): ApiResult<T>()
}

/*
    ApiResult 결과 처리 함수.
 */
inline fun <T, R> ApiResult<T>.getResult(
    success: (ApiResult.Success<T>) -> R,
    error: (ApiResult.Error<T>) -> R
): R = when(this){
    is ApiResult.Success -> success(this)
    is ApiResult.Error -> error(this)
}

inline fun <T> ApiResult<T>.onSuccess(
    action: (T) -> Unit
): ApiResult<T> {
    if(this is ApiResult.Success)action(data)
    return this
}

inline fun <T> ApiResult<T>.onError(
    action: (Throwable) -> Unit
): ApiResult<T>{
    if(this is ApiResult.Error)action(error)
    return this
}