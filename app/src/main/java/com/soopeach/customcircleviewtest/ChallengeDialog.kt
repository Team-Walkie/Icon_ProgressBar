package com.soopeach.customcircleviewtest

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.soopeach.customcircleviewtest.databinding.ChallengeDialogBinding


class ChallengeDialog(
    private val onContinueButtonClick: () -> Unit,
    private val onStopButtonClick: () -> Unit,
) : DialogFragment() {

    lateinit var binding: ChallengeDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ChallengeDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons()
        setDialogText()
    }

    override fun onResume() {

        val display = if (Build.VERSION.SDK_INT >= 30) {
            requireContext().display
        } else {
            val windowManager =
                requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay
        }
        val size = Point()
        display?.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = (size.x * 0.9).toInt()
        params?.height = size.y / 3 * 2
        dialog?.window?.attributes = params as WindowManager.LayoutParams

        super.onResume()
    }

    private fun setDialogText() {
        with(binding) {
            this.progressText.text = "진행률 75%"
            this.warning.text = "정말 그만두시겠어요?"
            this.challengeTitle.text = "햄버거 3개 없애기 챌린지"
        }
    }

    private fun setButtons() {
        with(binding) {
            continueButton.setOnClickListener {
                onContinueButtonClick()
                dismiss()
            }

            stopButton.setOnClickListener {
                onStopButtonClick()
                dismiss()
            }

        }
    }

    private val Int.dp: Int
        get() = (this / Resources.getSystem().displayMetrics.density).toInt()

    companion object {
        const val TAG = "ChallengeDialog"
    }
}