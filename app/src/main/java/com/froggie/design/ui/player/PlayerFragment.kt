package com.froggie.design.ui.player

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.froggie.design.R
import com.froggie.design.databinding.FragmentPlayerBinding
import com.froggie.design.viewmodel.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

@AndroidEntryPoint
class PlayerFragment : Fragment(R.layout.fragment_player) {

    private lateinit var playerBinding: FragmentPlayerBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private val animationArgs by navArgs<PlayerFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerBinding = FragmentPlayerBinding.bind(view)
        hideAndShowLoader(true)
        registerLottiePlayerListeners()
        playerViewModel.loadLottieFileFromUrl(
            context,
            animationArgs.AnimationData.lottieUrl
        )

        playerViewModel.lottieComposition.observe(viewLifecycleOwner) { lottieMetaData ->
            run {
                onLoadLottieCompositionData(lottieMetaData)
            }
        }
    }


    private val playerAnimationListenerCallBack = object : Animator.AnimatorListener {
        override fun onAnimationStart(p0: Animator?) {
            togglePlayButton(true)
        }

        override fun onAnimationEnd(p0: Animator?) {
            togglePlayButton(false)
        }

        override fun onAnimationCancel(p0: Animator?) {
            togglePlayButton(false)
        }

        override fun onAnimationRepeat(p0: Animator?) {
            togglePlayButton(false)
        }
    }

    private val animateUpdateListenerCallCallback = ValueAnimator.AnimatorUpdateListener {
        run {
            playerBinding.playerControls.currentFrameView.text =
                updateFramesAndDurationLabel(playerBinding.playerAnimation)
            playerBinding.playerControls.seekBar.progress =
                ((it.animatedValue as Float) * playerBinding.playerControls.seekBar.max).roundToInt()
        }
    }

    private fun minScale() = 0.05f

    private fun maxScale(): Float {
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()
        return min(
            screenWidth,
            screenHeight
        )
    }

    private val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
            val minScale = minScale()
            val maxScale = maxScale()
            val scale = minScale + progress / 100f * (maxScale - minScale)
            playerBinding.playerAnimation.scale = scale
        }

        override fun onStartTrackingTouch(p0: SeekBar?) = Unit

        override fun onStopTrackingTouch(p0: SeekBar?) = Unit
    }


    private fun registerLottiePlayerListeners() {
        playerBinding.playerAnimation.addAnimatorListener(playerAnimationListenerCallBack)
        playerBinding.playerAnimation.addAnimatorUpdateListener(animateUpdateListenerCallCallback)
        playerBinding.playerControls.seekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        playerBinding.playerControls.playButton.setOnClickListener {
            if (playerBinding.playerAnimation.isAnimating) {
                playerBinding.playerAnimation.pauseAnimation()
                togglePlayButton(false)
            } else {
                playerBinding.playerAnimation.resumeAnimation()
                togglePlayButton(true)
            }
            playerBinding.playerControls.playButton.isActivated =
                playerBinding.playerAnimation.isAnimating
            playerBinding.playerAnimation.invalidate()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        playerBinding.playerAnimation.removeAnimatorListener(playerAnimationListenerCallBack)
        playerBinding.playerAnimation.removeUpdateListener(animateUpdateListenerCallCallback)
    }


    private fun onLoadLottieCompositionData(composition: LottieComposition?) {
        composition ?: return
        /*  playerBinding.playerAnimation.setRenderMode(RenderMode.HARDWARE)
          playerBinding.playerAnimation.imageAssetsFolder = "images/"*/
        playerBinding.playerAnimation.enableMergePathsForKitKatAndAbove(true)
        //  playerBinding.playerAnimation.setCacheComposition(true)
        playerBinding.playerAnimation.setComposition(composition)
        hideAndShowLoader(false)
    }


    private fun updateFramesAndDurationLabel(animation: LottieAnimationView): String {
        val currentFrame = animation.frame.toString()
        val totalFrames = ("%.0f").format(animation.maxFrame)

        val animationSpeed: Float = abs(animation.speed)

        val totalTime = ((animation.duration / animationSpeed) / 1000.0)
        val totalTimeFormatted = ("%.1f").format(totalTime)

        val progress = (totalTime / 100.0) * ((animation.progress * 100.0).roundToInt())
        val progressFormatted = ("%.1f").format(progress)

        return "$currentFrame/$totalFrames\n$progressFormatted/$totalTimeFormatted"
    }

    private fun togglePlayButton(isActivated: Boolean) {
        playerBinding.playerControls.playButton.isActivated = isActivated
        if (isActivated) playerBinding.playerControls.playButton.setImageResource(R.drawable.ic_baseline_pause_24) else playerBinding.playerControls.playButton.setImageResource(
            R.drawable.ic_baseline_play_arrow_24
        )
    }

    private fun hideAndShowLoader(isVisible: Boolean) {
        playerBinding.loadingView.isVisible = isVisible
    }
}