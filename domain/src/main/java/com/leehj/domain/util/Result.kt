package com.leehj.domain.util

sealed class Result<out T>(val data: T? = null, val message: String? = null) {
    class Loading<out T>(data: T?=null): Result<T>(data)
    class Success<out T>(data: T?): Result<T>(data)
    class Error<out T>(message: String, data: T? = null): Result<T>(data, message)
}

/*
    1. sealed class - 제한된 클래스, 봉인 클래스
    : 상속 가능한 하위 클래스의 종류를 제한.
    - 모든 하위 클래스를 처리하도록 강제하여 누락된 경우 컴파일 오류 발생 -> 코드 안정성 높임
    => Result 클래스는 Loading, Success, Error 3개의 하위 클래스만 가질 수 있다.

    2. Generies - 타입 매개변수
    : 타입을 매개변수로 받아 다양한 타입에 대해 동작하도록 처리 가능
    - 타입 안정성 유지. 코드 중복을 줄이고 재사용성을 높일 수 있음.
    => Result<T> 는 T 매개변수를 활용해 어떤 타입의 데이터로 결과로 담을 수 있다.

    3. data class - 데이터 저장
    : 데이터 저장 용도로 사용. 컴파일러가 자동으로 equals(), hashCode(), toString() 등의 메소드 생성.
    - 불필요한 코드 작성을 줄이고, 데이터를 간결하게 표시 가능

    4. Nullable Type - null 허용
    : 타입 뒤에 ? 를 붙여 해당 타입의 변수가 null 값을 가질 수 있도록 함.
    - ? 를 사용하면 컴파일 시점에 null 값 처리를 강제해, 런타임 시 발생할 수 있는 NPE 예방 가능 (Null Check 없이 해당 객체의 프로퍼티 접근 불가능)
    -> data: T? : data 결과가 null 일 수 있음.

    5. Variance
    : 제네릭 타입의 상속 관계에 대한 규칙 정의. 타입 안정성을 유지하면서 타입 간의 호환성 확장
    - out : 공변성(convariance) 를 나타내며, 제네릭 타입 매개변수가 "생산자" 역할을 한다는 것을 의미.
    (즉, 해당 타입의 객체는 값을 반환하는 데에만 사용되고, 설정하는 데에는 사용되지 않는다. 읽기 전용)
 */