package com.soopeach.customcircleviewtest

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.SeekBarBindingAdapter.setProgress
import com.soopeach.customcircleviewtest.databinding.ChallengeIndicatorViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChallengeIndicatorView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var _binding: ChallengeIndicatorViewBinding? = null
    private val binding: ChallengeIndicatorViewBinding get() = _binding!!

    @SuppressLint("CustomViewStyleable")
    private val attrsArray = context.obtainStyledAttributes(
        attrs,
        R.styleable.ChallengeIndicator
    )

    init {
        val layoutInflater = LayoutInflater.from(context)
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.challenge_indicator_view, this, false)

        // 0
        val defaultProgress = (0.. 100).shuffled().first()

        binding.progressIndicator.progress = defaultProgress
//        setProgressWithAnimation(
//            attrsArray.getInteger(
//                R.styleable.ChallengeIndicator_progressState,
//                defaultProgress
//            )
//        )

        addView(binding.root)
        attrsArray.recycle()
    }

    private fun setProgressWithAnimation(progressState: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(progressState) {
                binding.progressIndicator.progress += 1
                setIconPosition()
                setTextPosition()
                delay(10)
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        setIconPosition()
        setTextPosition()
    }

    private fun setIconPosition() {
        with(binding) {
            val progressPosition = progressIndicator.progress * progressIndicator.measuredWidth / 100F
            progressIcon.x = progressPosition - (progressIcon.measuredWidth / 4)
        }
    }

    private fun setTextPosition() {

        with(binding) {

            val startInfoTextPosition = startInfoText.measuredWidth
            val endInfoTextPosition = endInfoText.measuredWidth
            var progressTextPosition = progressIcon.x

//            if (progressIcon.x < startInfoTextPosition) progressTextPosition += startInfoTextPosition
//            if (endInfoTextPosition < progressIcon.x) progressTextPosition -= endInfoTextPosition

            progressInfoText.x = progressTextPosition
            println("테스트 ${startInfoText.measuredWidth}")
            // 겹치는 경우

        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

}