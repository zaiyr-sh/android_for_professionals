package com.bignerdranch.android.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_TIME = "date"

class TimePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onTimeSelected(time: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val resultDate = SimpleDateFormat("HH:mm").format(calendar.time)
            targetFragment?.let { fragment ->
                (fragment as Callbacks).onTimeSelected(resultDate)
            }
        }
        val initialHour = calendar.get(Calendar.HOUR_OF_DAY)
        val initialMinute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireContext(),
            timeListener,
            initialHour,
            initialMinute,
            true
        )
    }

    companion object {
        fun newInstance() = TimePickerFragment()
    }

}