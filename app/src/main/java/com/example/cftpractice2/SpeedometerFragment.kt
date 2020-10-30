package com.example.cftpractice2

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class SpeedometerFragment(var mContext: Context): Fragment() {

    companion object {
        fun newInstance(mContext: Context) = SpeedometerFragment(mContext)
    }
    lateinit var speedometer: Speedometer
    lateinit var buttonMore: Button
    lateinit var buttonLess: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.speedometer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        speedometer = view.findViewById(R.id.speedometer)
        buttonMore = view.findViewById(R.id.speedometer_button_more)
        buttonLess = view.findViewById(R.id.speedometer_button_less)
        buttonMore.setOnClickListener { onButtonMoreClick() }
        buttonLess.setOnClickListener { onButtonLessClick() }


    }

    fun onButtonMoreClick(){
        Log.i("SpeedometerFragment", "Button more was clicked!")
        if(speedometer.value < speedometer.maxValue) {
            speedometer.setValueAnimated(speedometer.value + 10)
            speedometer.value += 10
        }
        else Toast.makeText(mContext, "Скорость уже на пределе!", Toast.LENGTH_SHORT).show()
    }

    fun onButtonLessClick(){
        Log.i("SpeedometerFragment", "Button less was clicked(")
        if(speedometer.value >= 10) {
            speedometer.setValueAnimated(speedometer.value - 10)
            speedometer.value -= 10
        }
            else Toast.makeText(mContext, "Медленнее некуда!", Toast.LENGTH_SHORT).show()
    }
}