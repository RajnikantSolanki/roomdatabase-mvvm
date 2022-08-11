package com.example.vcerp_practical.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.vcerp_practical.fragments.AddressFragment
import com.example.vcerp_practical.fragments.GeneralFragment

internal class MyAdapter(
   var context: Context,
   fm: FragmentManager,
   var totalTabs: Int
) :
FragmentPagerAdapter(fm) {
   override fun getItem(position: Int): Fragment {
      return when (position) {
         0 -> {
            GeneralFragment()
         }
         1 -> {
            AddressFragment()
         }
         else -> getItem(position)
      }
   }
   override fun getCount(): Int {
      return totalTabs
   }
}