package com.example.thirdpracremaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import android.widget.Toast

class SharedViewModel : ViewModel() {
    private val _currentFragmentIndex = MutableLiveData<Int>(0)
    val currentFragmentIndex: LiveData<Int> get() = _currentFragmentIndex

    private val _displayedData = MutableLiveData<String>("")
    val displayedData: LiveData<String> get() = _displayedData

    fun nextFragment(maxIndex: Int) {
        if (_currentFragmentIndex.value!! < maxIndex) {
            _currentFragmentIndex.value = _currentFragmentIndex.value!! + 1
        }
    }

    fun previousFragment() {
        if (_currentFragmentIndex.value!! > 0) {
            _currentFragmentIndex.value = _currentFragmentIndex.value!! - 1
        }
    }

    fun loadDataFromFile(context: Context, fileName: String) {
        val data = try {
            context.openFileInput(fileName).bufferedReader().useLines { lines ->
                lines.fold("") { some, text -> "$some\n$text" }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
        _displayedData.value = data
    }

    fun saveDataToFile(context: Context, fileName: String, data: String) {
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(data.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(context, "Data saved to file", Toast.LENGTH_LONG).show()
        _displayedData.value = data
    }
}
