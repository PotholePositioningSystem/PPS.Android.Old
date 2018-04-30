package pps.android.ui.activities

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pps.android.R

/**
 * A placeholder fragment containing a simple view.
 */
class AddIssueActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_issue, container, false)
    }
}
