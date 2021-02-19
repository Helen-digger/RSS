package preference

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.preference.ListPreference
import androidx.preference.MultiSelectListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.rssreader.R

class PreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        findPreference<ListPreference>("filter")!!.connectFilterDialog(fragmentManager)


        /*activity?.supportFragmentManager?.beginTransaction()!!
            .replace(R.layout.channels_setting_layout, PreferenceChannelDialogFragment(), "channel")
            .addToBackStack(null)
            .commit();*/


        //findPreference<MultiSelectListPreference>("channel")!!.connectChannelDialog(fragmentManager)

    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (preferenceScreen.key == "channel") {
            inflater.inflate(R.menu.menu_multi_select, menu)
        }
    }*/

}
