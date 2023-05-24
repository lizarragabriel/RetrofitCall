package com.lizarragabriel.retrofitcall.utils.extensions

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.lizarragabriel.retrofitcall.utils.MyEvent

fun View.setUpNavigation(
    myEvent: LiveData<MyEvent<Int>>,
    lifecycleOwner: LifecycleOwner
) {
    myEvent.observe(
        lifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                this.myNavigation(it)
            }
        }
    )
}

fun View.myNavigation(
    action: Int
) {
    this.findNavController().navigate(action)
}

fun View.showToast(
    myEvent: LiveData<MyEvent<Int>>,
    lifecycleOwner: LifecycleOwner
) {
    myEvent.observe(
        lifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
            }
        }
    )
}