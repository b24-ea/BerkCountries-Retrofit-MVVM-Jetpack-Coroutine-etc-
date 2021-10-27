package com.aldanmaz.berkcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aldanmaz.berkcountries.R
import com.aldanmaz.berkcountries.databinding.FragmentCountryDetailBinding
import com.aldanmaz.berkcountries.util.downloadFromUrl
import com.aldanmaz.berkcountries.util.placeHolderProgressBar
import com.aldanmaz.berkcountries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country_detail.*


class CountryDetailFragment : Fragment() {

    private lateinit var viewModel : CountryViewModel
    private  var countryUuid = 0
    private lateinit var dataBinding : FragmentCountryDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            countryUuid = CountryDetailFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)



        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer{ country ->
            country?.let {
                dataBinding.selectedCountry = it
                /*
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryRegion.text = country.countryRegion
                countryCurrency.text = country.countryCurrency
                countryLanguage.text = country.countryLanguage
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeHolderProgressBar(it))
                }


                 */

            }
        })
    }
}