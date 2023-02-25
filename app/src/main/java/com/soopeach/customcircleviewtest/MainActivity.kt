package com.soopeach.customcircleviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.adapters.SeekBarBindingAdapter.OnStartTrackingTouch
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val test = findViewById<ChallengeIndicatorView>(R.id.challenge_progress_indicator)

        setDialog()

    }

    private fun setDialog() {
        val dialogButton = findViewById<Button>(R.id.create_dialog_button)

        dialogButton.setOnClickListener {
            val dialog = ChallengeDialog(
                onContinueButtonClick = {
                    Log.d("다이어로그 테스트", "계속 클릭")
                },
                onStopButtonClick = {
                    Log.d("다이어로그 테스트", "그만 클릭")
                }
            )

            dialog.show(supportFragmentManager, ChallengeDialog.TAG)
        }
    }
}