package com.github.iaroslavomelianenko.recyclerviewcontacts

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.iaroslavomelianenko.recyclerviewcontacts.adapter.ContactAdapter
import com.github.iaroslavomelianenko.recyclerviewcontacts.databinding.ActivityMainBinding
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Contact
import kotlinx.android.synthetic.main.add_update_contact.*

class MainActivity : AppCompatActivity() {

    lateinit var _binding: ActivityMainBinding
    private var mainMenu: Menu? = null
    private var contactList = ArrayList<Contact>()
    private var updatedContactList = ArrayList<Contact>()
    private lateinit var contactAdapter: ContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        //Filling contact list
        for (i in 1..20) {
            contactList.add(Contact(i, "Name$i", "Surname$i", 89210000000 + i, false))
        }

        contactAdapter = ContactAdapter { show -> showDeleteMenu(show) }

        //Launching DiffUtil
        contactAdapter.updateList(contactList)

        //Add contact function
        _binding.ibAdd.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.add_update_contact)
            val editName = dialog.etAddUpdateName
            val editSurname = dialog.etAddUpdateSurname
            val editPhoneNumber = dialog.etAddUpdatePhoneNumber

            dialog.tvWindowTitle.setText(R.string.new_contact)
            dialog.bAddUpdate.setText(R.string.add)

            dialog.bAddUpdate.setOnClickListener {
                updatedContactList.addAll(contactList)
                updatedContactList.add(
                    Contact(
                        updatedContactList.size+1,
                        editName.text.toString(),
                        editSurname.text.toString(),
                        editPhoneNumber.text.toString().toLong(),
                        false
                    )
                )
                contactAdapter.updateList(updatedContactList)
                dialog.dismiss()
            }
            dialog.show()
        }

        _binding.bCountryCodes.setOnClickListener{
            startActivity(Intent(this@MainActivity, CountryCodesActivity::class.java))
        }
        _binding.rcvContacts.layoutManager = LinearLayoutManager(this@MainActivity)
        _binding.rcvContacts.adapter = contactAdapter
    }


    //Menu functions
    private fun showDeleteMenu(show: Boolean) {
        mainMenu?.findItem(R.id.mDelete)?.isVisible = show
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mainMenu = menu
        menuInflater.inflate(R.menu.custom_menu, mainMenu)
        showDeleteMenu(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mDelete -> {
                delete()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    //Alert dialog for deleting contacts
    private fun delete() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete")
        alertDialog.setMessage("Do you want to delete this contacts?")
        alertDialog.setPositiveButton("Delete") { _, _ ->
            contactAdapter.deleteSelectedItem()
            showDeleteMenu(false)
        }
        alertDialog.setNegativeButton("Cancel") { _, _ -> }
        alertDialog.show()
    }
}