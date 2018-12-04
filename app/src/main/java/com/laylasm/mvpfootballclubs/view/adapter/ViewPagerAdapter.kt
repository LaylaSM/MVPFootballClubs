package com.laylasm.mvpfootballclubs.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){

    var fragmentList = arrayListOf<Fragment>()
    var headList = arrayListOf<String>()

    fun fillFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        headList.add(title)
    }
    override fun getItem(position: Int) = fragmentList[position]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = headList[position]
}