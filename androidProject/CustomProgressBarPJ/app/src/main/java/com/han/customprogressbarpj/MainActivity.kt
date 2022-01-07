package com.han.customprogressbarpj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.han.customprogressbarpj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var bar1: CustomCircleProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
        }

        binding.progressOne.setProgressWithAnimation(65f)

        (binding.seekBarProgress as SeekBar).setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                with (binding.progressOne) {
                    setProgressValue(progress.toFloat())
                    setProgressWithAnimation(progress.toFloat())
                    backgroundProgressBarWidth = 20f // progress.toFloat()
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

    }
}