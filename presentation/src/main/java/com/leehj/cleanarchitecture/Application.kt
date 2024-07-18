package com.leehj.cleanarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
    앱의 생명주기에 연결된 컨테이너 추가를 위해 Application 클래스에 @HiltAndroidApp 어노테이션 추가
    @HiltAndroidApp : 종속 항목 inject 를 사용할 수 있는 Application 의 기본 클래스가 포함된 Hilt 코드 생성을 트리거
 */
@HiltAndroidApp
class Application: Application() {
}