package com.bignerdranch.android.geomain

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var toast: Toast
    private lateinit var prevButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var numberCorrectAnswerTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0
    private var countCorrectAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        numberCorrectAnswerTextView = findViewById(R.id.number_correct_answer_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
            isChoosingButtonsClickable(false)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
            isChoosingButtonsClickable(false)
        }

        prevButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.lastIndex
            }
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
            isChoosingButtonsClickable(false)
        }

        nextButton.setOnClickListener {
            if (currentIndex + 1 == questionBank.size) {
                numberCorrectAnswerTextView.text =
                    resources.getString(R.string.number_correct_answer, countCorrectAnswer)
            } else {
                numberCorrectAnswerTextView.text = ""
            }
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            isChoosingButtonsClickable(true)
        }

        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            countCorrectAnswer++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
    }

    private fun isChoosingButtonsClickable(isClickable: Boolean) {
        trueButton.isClickable = isClickable
        falseButton.isClickable = isClickable
    }
}