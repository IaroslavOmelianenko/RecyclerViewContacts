package com.github.iaroslavomelianenko.recyclerviewcontacts.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.iaroslavomelianenko.recyclerviewcontacts.util.ContactDiffUtil
import com.github.iaroslavomelianenko.recyclerviewcontacts.holder.ContactHolder
import com.github.iaroslavomelianenko.recyclerviewcontacts.R
import com.github.iaroslavomelianenko.recyclerviewcontacts.model.Contact
import kotlinx.android.synthetic.main.add_update_contact.*


class ContactAdapter(
    private val showMenuDelete: (Boolean) -> Unit
) : RecyclerView.Adapter<ContactHolder>() {

    private var isEnable = false
    private var contactList = ArrayList<Contact>()
    private val itemSelectedList = mutableListOf<Int>()
    private var updatedContactList = ArrayList<Contact>()

    lateinit var viewGroup: ViewGroup


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        viewGroup = parent
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val item = contactList[position]
        holder.fillContactItem(item)

        holder._binding.checkSelected.visibility = View.GONE

        //Long click activates delete selection
        holder._binding.cardViewContact.setOnLongClickListener {
            selectItem(holder, item, position)
            true
        }

        holder._binding.cardViewContact.setOnClickListener {
            if (itemSelectedList.contains(position)) {
                itemSelectedList.removeAt(position)
                holder._binding.checkSelected.visibility = View.GONE
                item.selected = false

                if (itemSelectedList.isEmpty()) {
                    showMenuDelete(false)
                    isEnable = false
                }
            } else if (isEnable) {
                selectItem(holder, item, position)
            } else{
                //Opening dialog window to edit contact if not in selection mode
                val dialog = Dialog(viewGroup.context)
                dialog.setContentView(R.layout.add_update_contact)
                val editName = dialog.etAddUpdateName
                val editSurname = dialog.etAddUpdateSurname
                val editPhoneNumber = dialog.etAddUpdatePhoneNumber

                dialog.tvWindowTitle.setText(R.string.edit_contact)
                dialog.bAddUpdate.setText(R.string.apply)
                editName.setText(contactList[position].contactName)
                editSurname.setText(contactList[position].contactSurname)
                editPhoneNumber.setText(contactList[position].contactPhoneNumber.toString())

                dialog.bAddUpdate.setOnClickListener {
                    updatedContactList.addAll(contactList)
                    updatedContactList[position].contactName = editName.text.toString()
                    updatedContactList[position].contactSurname = editSurname.text.toString()
                    updatedContactList[position].contactPhoneNumber = editPhoneNumber.text.toString().toLong()
                    updateList(updatedContactList)
                    dialog.dismiss()
                }
                dialog.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    private fun selectItem(holder: ContactHolder, contact: Contact, position: Int) {
        isEnable = true
        itemSelectedList.add(position)
        contact.selected = true
        holder._binding.checkSelected.visibility = View.VISIBLE
        showMenuDelete(true)
    }

    fun deleteSelectedItem() {
        if (itemSelectedList.isNotEmpty()) {
            updatedContactList.addAll(contactList)
            updatedContactList.removeAll { contact -> contact.selected }
            isEnable = false
            itemSelectedList.clear()
        }
        updateList(updatedContactList)
    }

    //DiffUtil
    fun updateList(newContactList: ArrayList<Contact>) {
        val diffUtil = ContactDiffUtil(contactList, newContactList)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        contactList.clear()
        contactList.addAll(newContactList)
        diffUtilResults.dispatchUpdatesTo(this)
    }
}