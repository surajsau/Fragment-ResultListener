package `in`.surajsau.fragmentresultlistener

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

class TabFragment1 : Fragment() {

    private var fragmentCount: Int = 0

    private val resultText: AppCompatTextView? by lazy { view?.findViewById(R.id.result_text) }

    private val activityButton: AppCompatButton? by lazy { view?.findViewById(R.id.activity_button) }

    private val fragmentButton: AppCompatButton? by lazy { view?.findViewById(R.id.fragment_button) }

    private val childButton: AppCompatButton? by lazy { view?.findViewById(R.id.child_button) }

    private val bg: View? by lazy { view?.findViewById(R.id.bg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("tab1") { _, data ->
            resultText?.text = getString(
                R.string.frag_result,
                data.getString("source"),
                fragmentCount++
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityButton?.setOnClickListener { setFragmentResult("activity", Bundle().apply { putString("source", "Tab1") }) }
        fragmentButton?.setOnClickListener { setFragmentResult("fragment", Bundle().apply { putString("source", "Tab1") }) }
        childButton?.setOnClickListener { childFragmentManager.setFragmentResult("child", Bundle().apply { putString("source", "Tab1") }) }

        bg?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.tab1))

        childFragmentManager.beginTransaction()
            .add(R.id.child_frag, ChildFragment())
            .commit()

        childFragmentManager.setFragmentResultListener("tab1", viewLifecycleOwner) { _, data ->
            resultText?.text = getString(
                R.string.frag_result,
                data.getString("source"),
                ++fragmentCount
            )
        }
    }
}
