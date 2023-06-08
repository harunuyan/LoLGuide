package com.volie.lolguidestats.ui.fragment.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.volie.lolguidestats.databinding.FragmentSelectLanguageBinding
import com.volie.lolguidestats.helper.SharedPreferenceUtil
import com.volie.lolguidestats.helper.SharedPreferenceUtil.Companion.REGION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SelectLanguageFragment : Fragment() {
    private var _mBinding: FragmentSelectLanguageBinding? = null
    private val mBinding get() = _mBinding!!
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    private lateinit var selectedRegion: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentSelectLanguageBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val languages = listOf(
            "Czech (Czech Republic)",
            "Greek (Greece)",
            "Polish (Poland)",
            "Romanian (Romania)",
            "Hungarian (Hungary)",
            "English (United Kingdom)",
            "German (Germany)",
            "Spanish (Spain)",
            "Italian (Italy)",
            "French (France)",
            "Japanese (Japan)",
            "Korean (Korea)",
            "Spanish (Mexico)",
            "Spanish (Argentina)",
            "Portuguese (Brazil)",
            "English (United States)",
            "Russian (Russia)",
            "Turkish (Turkey)",
            "Malay (Malaysia)",
            "English (Republic of the Philippines)",
            "English (Singapore)",
            "Thai (Thailand)",
            "Vietnamese (Viet Nam)",
            "Indonesian (Indonesia)",
            "Chinese (Malaysia)",
            "Chinese (China)",
            "Chinese (Taiwan)",
        )

        val languageMap = mapOf(
            "Czech (Czech Republic)" to "cs_CZ",
            "Greek (Greece)" to "el_GR",
            "Polish (Poland)" to "pl_PL",
            "Romanian (Romania)" to "ro_RO",
            "Hungarian (Hungary)" to "hu_HU",
            "English (United Kingdom)" to "en_GB",
            "German (Germany)" to "de_DE",
            "Spanish (Spain)" to "es_ES",
            "Italian (Italy)" to "it_IT",
            "French (France)" to "fr_FR",
            "Japanese (Japan)" to "ja_JP",
            "Korean (Korea)" to "ko_KR",
            "Spanish (Mexico)" to "es_MX",
            "Spanish (Argentina)" to "es_AR",
            "Portuguese (Brazil)" to "pt_BR",
            "English (United States)" to "en_US",
            "Russian (Russia)" to "ru_RU",
            "Turkish (Turkey)" to "tr_TR",
            "Malay (Malaysia)" to "ms_MY",
            "English (Republic of the Philippines)" to "en_PH",
            "English (Singapore)" to "en_SG",
            "Thai (Thailand)" to "th_TH",
            "Vietnamese (Viet Nam)" to "vi_VN",
            "Indonesian (Indonesia)" to "id_ID",
            "Chinese (Malaysia)" to "zh_MY",
            "Chinese (China)" to "zh_CN",
            "Chinese (Taiwan)" to "zh_TW"
        )

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mBinding.spinner.adapter = adapter


        if (!sharedPreferenceUtil.getSelectedItem().isNullOrEmpty()) {
            mBinding.spinner.visibility = View.GONE
            mBinding.tvSelectLanguage.visibility = View.GONE
            mBinding.ivSelectLanguage.visibility = View.GONE

            lifecycleScope.launch {
                delay(1500)

                REGION = sharedPreferenceUtil.getSelectedItem().toString()

                val action =
                    SelectLanguageFragmentDirections.actionSelectScreenFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        } else {
            mBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedRegion = languageMap[languages[position]].toString()
                    sharedPreferenceUtil.saveSelectedItem(selectedRegion)
                    REGION = sharedPreferenceUtil.getSelectedItem().toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

            mBinding.ivSelectLanguage.setOnClickListener {
                val action =
                    SelectLanguageFragmentDirections.actionSelectScreenFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        sharedPreferenceUtil = SharedPreferenceUtil(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}