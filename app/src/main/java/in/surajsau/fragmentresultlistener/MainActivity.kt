package `in`.surajsau.fragmentresultlistener

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frag1, MainFragment1())
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.frag2, MainFragment2())
            .commit()

        TabLayoutMediator(
            findViewById(R.id.tabs),
            findViewById<ViewPager2>(R.id.pager).apply {
                adapter = PagerAdapter(this@MainActivity)
            }
        ) { tab, position ->
            tab.text = "Tab ${(position + 1)}"
        }

        supportFragmentManager.setFragmentResultListener("activity", this) { _, data ->
            findViewById<AppCompatTextView>(R.id.result_text).text = getString(
                R.string.activity_result,
                data.getString("source"),
                ++count
            )
        }
    }
}
