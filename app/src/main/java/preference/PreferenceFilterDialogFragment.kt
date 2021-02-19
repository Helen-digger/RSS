package preference

import android.R
import android.app.Dialog
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import util.FilterTypes

class PreferenceFilterDialogFragment : DialogFragment() {

    private var currentFilter: FilterTypes? = FilterTypes.None
    private var filters: List<FilterTypes> = emptyList()

    private val prefKey: String
        get() = arguments?.getString(ARG_PREF_KEY) ?: throw IllegalArgumentException("ARG_PREF_KEY not set")

    private val adapter by lazy {
        SimpleAdapter(
            context,
            filters.map { mapOf("title" to it.name) },
            R.layout.simple_list_item_single_choice,
            arrayOf("title"),
            intArrayOf(R.id.text1)
        )
    }

    private fun getCurrentFilter(): FilterTypes? = PreferenceManager
        .getDefaultSharedPreferences(context)
        .getString(prefKey, null)
        ?.let { FilterTypes.getByName(it) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        filters = FilterTypes.values().toList()
        currentFilter = getCurrentFilter()
        val currentPosition = FilterTypes.values().indexOfFirst { currentFilter == FilterTypes.valueOf(it.name) }

        return AlertDialog.Builder(context!!)
            .setPositiveButton(R.string.ok) { _, _ -> saveCurrentFilter() }
            .setNegativeButton(R.string.cancel) { _, _ -> dialog?.dismiss() }
            .setSingleChoiceItems(adapter, currentPosition) { _, which ->
                filters[which].also {
                    currentFilter = it
                }
            }
            .create()
    }

    private fun saveCurrentFilter() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(prefKey, currentFilter?.toString())
            .apply()
    }

    companion object {
        private const val ARG_PREF_KEY = "ARG_PREF_KEY"

        fun create(prefKey: String): PreferenceFilterDialogFragment {
            val fragment = PreferenceFilterDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_PREF_KEY, prefKey)
            }
            return fragment
        }
    }
}

fun Preference.connectFilterDialog(fragmentManager: FragmentManager?) = setOnPreferenceClickListener {
    PreferenceFilterDialogFragment.create(key).apply {
        fragmentManager?.let { show(it, "Image filter") }
    }
    true
}