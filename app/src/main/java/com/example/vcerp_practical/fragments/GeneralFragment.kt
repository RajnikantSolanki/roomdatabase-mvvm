package com.example.vcerp_practical.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.vcerp_practical.ApplicationClass
import com.example.vcerp_practical.BaseFragment
import com.example.vcerp_practical.R
import com.example.vcerp_practical.databinding.FragmentGeneralBinding
import com.example.vcerp_practical.model.ContactResponse
import com.example.vcerp_practical.roomdatabase.DatabaseViewModel


class GeneralFragment : BaseFragment() {

    private lateinit var binding: FragmentGeneralBinding
    private lateinit var activity: AppCompatActivity

    //roomdatabase
    private lateinit var databaseViewModel: DatabaseViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_general, container, false)
        var view: View = binding.root
        activity = ApplicationClass.mInstance!!.getactivity()!!
        initComponents(view)
        return view
    }

    override fun initComponents(rootView: View) {
        roomDatabaseInit()
        binding.btnSubmit.setOnClickListener {
            if(isValid().equals("")){
                val contactResponse = ContactResponse()
                contactResponse.firstName = binding?.etFirstName?.text.toString().trim()
                contactResponse.lastName = binding?.etLastName?.text.toString().trim()
                contactResponse.mobile_no = binding?.etMobileNo?.text.toString().trim()
                contactResponse.emailId = binding?.etEmail?.text.toString().trim()
                Thread {
                    databaseViewModel.insertContact(contactResponse)
                }.start()
                getActivity()?.onBackPressed()
            }else{
                Toast.makeText(activity,isValid(),Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancel.setOnClickListener {
            getActivity()?.onBackPressed()

        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun roomDatabaseInit() {
        databaseViewModel = activity?.let {
            ViewModelProviders.of(it).get(DatabaseViewModel::class.java)
        }!!
    }

    fun isValid():String {
        return if(TextUtils.isEmpty(binding?.etFirstName?.text.toString())){
            getString(R.string.str_enter_first_name)
        }else if(TextUtils.isEmpty(binding?.etLastName?.text.toString())){
            getString(R.string.str_enter_last_name)
        }else if(TextUtils.isEmpty(binding?.etMobileNo?.text.toString())){
            getString(R.string.str_enter_mobile_no)
        }else if(TextUtils.isEmpty(binding?.etEmail?.text.toString())){
            getString(R.string.str_enter_email)
        }else if(!isValidEmail(binding?.etEmail?.text.toString())){
            getString(R.string.str_enter_valid_email)
        }else{
            ""
        }
    }

    fun isValidEmail(inputmail: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(inputmail).matches()
    }

}