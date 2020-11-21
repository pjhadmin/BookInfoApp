package com.patch.bookinfoapp.common.extension

import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.toggleAnimation(isLike: Boolean? = false, isInit: Boolean? = false) {
    isInit?.let {
        if(it) {
            if (isLike == true) {
                speed = 1F
                playAnimation()
            } else {
                speed = -1F
            }
        } else {
            speed = if (isLike == true) {
                1F
            } else {
                if (speed < 0F) {
                    1F
                } else {
                    -1F
                }
            }
            playAnimation()
        }
    }
}