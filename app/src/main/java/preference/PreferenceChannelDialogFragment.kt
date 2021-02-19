package preference

import android.R
import android.app.Dialog
import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import kotlin.streams.toList

class PreferenceChannelDialogFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }
}

    /*: DialogFragment() {

    private var checkChannels: BooleanArray? = null
    private var channelLinks: List<String> = emptyList()

    private val prefKey: String
        get() = arguments?.getString(ARG_PREF_KEY) ?: throw IllegalArgumentException("ARG_PREF_KEY not set")

    private val adapter by lazy {
        SimpleAdapter(
            context,
            channelLinks.map { mapOf("title" to it) },
            R.layout.simple_list_item_multiple_choice,
            arrayOf("title"),
            intArrayOf(R.id.text1)
        )
    }

    private fun getResourceChannelLinks(): List<String> {
        return (context?.assets?.open("rss.txt")?.bufferedReader()?.lines()?.toList()!!)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        channelLinks = getResourceChannelLinks()
        checkChannels = BooleanArray(channelLinks.size) { false }
        return AlertDialog.Builder(context!!).setMultiChoiceItems(channelLinks.toTypedArray(), checkChannels) {
            dialog, which, isChecked ->
            checkChannels!![which] = true
        }
            .setPositiveButton(R.string.ok) { _, _ -> saveCurrentFilter() }
            .setNegativeButton(R.string.cancel) { _, _ -> dialog?.dismiss() }
            .create()
    }

    private fun saveCurrentFilter() {
        for (i in checkChannels!!.indices) {
            val checked = this!!.checkChannels!![i]
            if (checked) {
                PreferenceManager.getDefaultSharedPreferences(context)
                    .edit()
                    .putBoolean(prefKey, this!!.checkChannels!![i])
                    .apply()
            }
        }
    }


    companion object {
        private const val ARG_PREF_KEY = "ARG_PREF_KEY"
        fun create(prefKey: String): PreferenceChannelDialogFragment {
            val fragment = PreferenceChannelDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_PREF_KEY, prefKey)
            }
            return fragment
        }
    }
}

fun Preference.connectChannelDialog(fragmentManager: FragmentManager?) = setOnPreferenceClickListener {
    PreferenceChannelDialogFragment.create(key).apply {
        fragmentManager?.let { show(it, "Channel Link") }
    }
    true
}*/

