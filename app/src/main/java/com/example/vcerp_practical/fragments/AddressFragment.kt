package com.example.vcerp_practical.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.example.CityArray
import com.example.example.DistrictArray
import com.example.example.StatesArray
import com.example.vcerp_practical.ApplicationClass
import com.example.vcerp_practical.BaseFragment
import com.example.vcerp_practical.R
import com.example.vcerp_practical.databinding.FragmentAddressBinding
import com.example.vcerp_practical.mvvm.AddressViewModel
import com.example.vcerp_practical.roomdatabase.DatabaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressFragment : BaseFragment() {

    private lateinit var binding: FragmentAddressBinding
    private lateinit var activity: AppCompatActivity

    private val addressViewModel : AddressViewModel by viewModel()

    //roomdatabase
    private lateinit var databaseViewModel: DatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        var view: View = binding.root
        binding?.viewModel = addressViewModel
        activity = ApplicationClass.mInstance!!.getactivity()!!
        initComponents(view)
        return view
    }

    override fun initComponents(rootView: View) {
        roomDatabaseInit()
        //getAddress()
        //setUpObserver()
        retrieveStateList()
        retrieveCityList()
        retrieveDistList()

    }

    private fun getAddress() {
        if (isOnline(activity, true)) {
            addressViewModel.doState()
/*            addressViewModel.doCity()
            addressViewModel.doDist()*/
        }else{
            addressViewModel.stateResponse!!.observe(activity, Observer { words ->
                words?.let {
                    retrieveStateList(it.statesArray)
                }
            })

            addressViewModel.cityResponse!!.observe(activity, Observer { words ->
                words?.let {
                    retrieveCityList(it.cityArray)
                }
            })

            addressViewModel.distResponse!!.observe(activity, Observer { words ->
                words?.let {
                    retrieveDistList(it.districtArray)
                }
            })
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun roomDatabaseInit() {
        databaseViewModel = activity?.let {
            ViewModelProviders.of(it).get(DatabaseViewModel::class.java)
        }!!
    }

    private fun setUpObserver() {
        // state
        addressViewModel.stateResponse.observe(activity, Observer {data->
            for (data in data.statesArray!!) {
                databaseViewModel.insertState(data)
            }
            data.statesArray?.let { retrieveStateList(it) }
        })

        //city
        addressViewModel.cityResponse.observe(activity, Observer {data->
            for (data in data.cityArray!!) {
                databaseViewModel.insertCity(data)
            }
            data.cityArray?.let { retrieveCityList(it) }
        })

        // district
        addressViewModel.distResponse.observe(activity, Observer { data->
            for (data in data.districtArray!!) {
                databaseViewModel.insertDist(data)
            }
            data.districtArray?.let { retrieveDistList(it) }
        })
    }

    private fun retrieveStateList(stateList: List<StatesArray> = emptyList()) {
      /*  val states = mutableListOf<String>()

        for(item in stateList.indices){
            stateList.get(item).stateName?.let { states.add(it) }
            Log.e("State",""+stateList.get(item).stateName)
        }
        val stateAdapter = ArrayAdapter(activity,
            android.R.layout.simple_spinner_item, states)
        binding.spinnerState.adapter = stateAdapter*/

        val statesArray = listOf<String>("Gujarat","Rajasthan")

        val stateAdapter = ArrayAdapter(activity,
            android.R.layout.simple_spinner_item, statesArray)
        binding.spinnerState.adapter = stateAdapter
    }

    private fun retrieveCityList(cityList: List<CityArray> = emptyList()) {

        val cityArray = listOf<String>("Udaipur","dungarpur","banswala","Jaipur Outer","Jaipur inner","Pali",
        "Barmer","Kalol","Halol","Ahemdabad","Sanand","Bawda","Vadodara","Chhani")

        val stateAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, cityArray)
        binding.spinnerCity.adapter = stateAdapter
    }

    private fun retrieveDistList(distList: List<DistrictArray> = emptyList()) {

        val distArray = listOf<String>("Ahemdabad","Vadodara","Panchmahal","Udaipur")

        val stateAdapter = ArrayAdapter(activity,
            android.R.layout.simple_spinner_item, distArray)
        binding.spinnerDistirct.adapter = stateAdapter
    }

    fun isOnline(context: Activity, message: Boolean): Boolean {
        val mConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = mConnectivityManager.activeNetworkInfo
        if (netInfo != null) {
            if (netInfo.isConnected) {
                return true
            }
        }
        if (message) {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(context.getString(R.string.app_name))
            dialog.setCancelable(false)
            dialog.setMessage(context.getString(R.string.check_connection))
            dialog.setPositiveButton(context.getString(R.string.settings)) { dialog, id ->
                dialog.dismiss()
                context.startActivity(Intent(Settings.ACTION_SETTINGS))
            }
            dialog.setNegativeButton(context.getString(android.R.string.cancel)) { dialog, id -> dialog.dismiss() }
            //dialog.show();

            return false
        }
        return false
    }

}