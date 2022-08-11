package com.example.vcerp_practical.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vcerp_practical.ApplicationClass
import com.example.vcerp_practical.R
import com.example.vcerp_practical.adapters.ContactListAdapter
import com.example.vcerp_practical.databinding.ActivityMainBinding
import com.example.vcerp_practical.model.ContactResponse
import com.example.vcerp_practical.roomdatabase.DatabaseViewModel

class MainActivity : AppCompatActivity() , ContactListAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    //roomdatabase
    private lateinit var databaseViewModel: DatabaseViewModel
    private lateinit var adapter: ContactListAdapter

    lateinit var filteredContactList: ArrayList<ContactResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.setTitle("Contact List")
        ApplicationClass.setmInstance(application as ApplicationClass)
        ApplicationClass.mInstance!!.setActity(this)

        roomDatabaseInit()

        adapter = ContactListAdapter(arrayListOf(),this)

        binding?.floatingActionButton.setOnClickListener {
            openNewActivity()
        }

        databaseViewModel.allContactList!!.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                retrieveList(it)
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

    }

    private fun retrieveList(list: List<ContactResponse>) {
        filteredContactList = list as ArrayList<ContactResponse>

        if(!filteredContactList.isEmpty()) {
            binding.rvContactList.visibility = View.VISIBLE
            binding.tvNoDataFound.visibility = View.GONE
            adapter.apply {
                addUsers(list)
                notifyDataSetChanged()
            }
            setUpUi(list)
        }else{
            binding.rvContactList.visibility = View.GONE
            binding.tvNoDataFound.visibility = View.VISIBLE
        }
    }

    private fun setUpUi(list: List<ContactResponse>) {
        binding.rvContactList.layoutManager= LinearLayoutManager(this)
        adapter= ContactListAdapter(list as ArrayList<ContactResponse>,this)
        binding.rvContactList.adapter = adapter
        adapter.setDeleteItemClick(this)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun roomDatabaseInit() {
        databaseViewModel = let {
            ViewModelProviders.of(it).get(DatabaseViewModel::class.java)
        }!!
    }

    fun openNewActivity() {
        val intent = Intent(this, AddContactActivity::class.java)
        startActivity(intent)
    }

    override fun onItemClick(contactResponse: ContactResponse) {
        showDeleteDialog(contactResponse.id)
    }

    private fun showDeleteDialog(id: Int?) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Contact")
        //set message for alert dialog
        builder.setMessage("Are you sure want to delete this contact?")
        //performing positive action
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            Thread{
                databaseViewModel.deleteContact(id!!)
            }.start()

        }
        //performing negative action
        builder.setNegativeButton("No"){dialogInterface, which ->
            dialogInterface.dismiss()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}