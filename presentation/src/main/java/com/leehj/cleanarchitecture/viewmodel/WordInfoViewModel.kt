package com.leehj.cleanarchitecture.viewmodel

import androidx.lifecycle.ViewModel
import com.leehj.domain.usecase.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
) : ViewModel() {
}