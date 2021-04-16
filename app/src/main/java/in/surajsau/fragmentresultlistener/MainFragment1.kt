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

class MainFragment1 : Fragment() {

    private var fragmentCount: Int = 0

    private val resultText: AppCompatTextView? by lazy { view?.findViewById(R.id.result_text) }

    private val activityButton: AppCompatButton? by lazy { view?.findViewById(R.id.activity_button) }

    private val fragmentButton: AppCompatButton? by lazy { view?.findViewById(R.id.fragment_button) }

    private val bg: View? by lazy { view?.findViewById(R.id.bg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("main1") { _, data ->
            resultText?.text = getString(
                R.string.frag_result,
                data.getString("source"),
                ++fragmentCount
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_common, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentButton?.setOnClickListener { setFragmentResult("main2", Bundle().apply { putString("source", "Main1") }) }
        activityButton?.setOnClickListener { setFragmentResult("activity", Bundle().apply { putString("source", "Main1") }) }
        view.findViewById<AppCompatButton>(R.id.tab1_button)?.setOnClickListener { setFragmentResult("tab1", Bundle().apply { putString("source", "Main1") }) }
        view.findViewById<AppCompatButton>(R.id.tab2_button)?.setOnClickListener { setFragmentResult("tab2", Bundle().apply { putString("source", "Main1") }) }
        view.findViewById<AppCompatButton>(R.id.tab3_button)?.setOnClickListener { setFragmentResult("tab3", Bundle().apply { putString("source", "Main1") }) }
        view.findViewById<AppCompatButton>(R.id.tab4_button)?.setOnClickListener { setFragmentResult("tab4", Bundle().apply { putString("source", "Main1") }) }

        bg?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.main1))
    }
}
