package `in`.surajsau.fragmentresultlistener

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

class ChildFragment : Fragment() {

    private val resultText: AppCompatTextView? by lazy { view?.findViewById(R.id.result_text) }

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("child") { _, data ->
            resultText?.text = getString(R.string.frag_result, data.getString("source"), ++count)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.parent_button).setOnClickListener {
            setFragmentResult("tab1", Bundle().apply { putString("source", "child") })
        }

        view.findViewById<AppCompatButton>(R.id.activity_button).setOnClickListener {
            parentFragment?.setFragmentResult("activity", Bundle().apply { putString("source", "child") })
        }
    }
}
