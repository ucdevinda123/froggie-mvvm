package com.froggie.design.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {

    private var _lottieComposition = MutableLiveData<LottieComposition>()
    var lottieComposition = _lottieComposition
    private var _playState = MutableLiveData<Boolean>()
    var playState = _playState

    fun loadLottieFileFromUrl(context: Context?, url: String) {
       // viewModelScope.launch {
            LottieCompositionFactory.fromUrl(context, url).addListener { composition ->
                run {
                    _lottieComposition.postValue(composition)
                }
            }
        //}

    }
}