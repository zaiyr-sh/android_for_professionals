package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        crimes.add(Crime(title = "Crime with bug", isSolved = true, requiresPolice = true))
        for (i in 0 until 100) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = false
            crime.requiresPolice = i % 2 == 0
            crimes += crime
        }

    }

}