package com.kannomedia.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.kannomedia.workoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding: ActivityExerciseBinding? = null
    
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0
    
    private var exerciseList = Constants.defaultExerciseList()
    private var currentExercisePosition = -1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        
        setSupportActionBar(binding?.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        setupRestView()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding = null
    }
    
    private fun setupRestView() {
        binding?.tvExerciseTitle?.visibility = View.VISIBLE
        binding?.flRestView?.visibility = View.VISIBLE
        
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.tvUpcomingExerciseName?.text = exerciseList[currentExercisePosition + 1].name
        setRestProgressBar()
    }
    
    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress
        restTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.timerTV?.text = (10 - restProgress).toString()
            }
            
            override fun onFinish() {
                currentExercisePosition++
                setupExerciseView()
            }
        }.start()
    }
    
    private fun setupExerciseView() {
        binding?.tvExerciseTitle?.visibility = View.INVISIBLE
        binding?.flRestView?.visibility = View.INVISIBLE
        
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding?.ivImage?.setImageResource(exerciseList[currentExercisePosition].image)
        binding?.tvExerciseName?.text = exerciseList[currentExercisePosition].name
        setExerciseProgressBar()
    }
    
    private fun setExerciseProgressBar() {
        binding?.progressBarExercise?.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 10 - exerciseProgress
                binding?.timerTVExercise?.text = (10 - exerciseProgress).toString()
            }
        
            override fun onFinish() {
                if (currentExercisePosition < exerciseList.size - 1) {
                    setupRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations!  You have completed the 7 minute workout!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }
}