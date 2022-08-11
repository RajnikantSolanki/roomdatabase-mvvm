package com.example.vcerp_practical

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), View.OnClickListener {
    private var mLastClickTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_base, container, false)
    }


    abstract fun initComponents(rootView: View)


    override fun onClick(v: View) {
        /**
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - mLastClickTime < MAX_CLICK_INTERVAL) {

            return
        }
        mLastClickTime = SystemClock.elapsedRealtime()
    }


    fun handleDoubleClick() {
        /**
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - mLastClickTime < MAX_CLICK_INTERVAL) {

            return
        }
        mLastClickTime = SystemClock.elapsedRealtime()
    }

    companion object {

        private val MAX_CLICK_INTERVAL = 3000
    }


}
