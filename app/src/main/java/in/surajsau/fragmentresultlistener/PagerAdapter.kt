package `in`.surajsau.fragmentresultlistener

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> TabFragment1()
        1 -> TabFragment2()
        2 -> TabFragment3()
        3 -> TabFragment4()

        else -> throw Exception("invalid $position")
    }
}
