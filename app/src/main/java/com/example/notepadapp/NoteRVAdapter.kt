package com.example.notepadapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(
    val context: Context,
    val NoteClickInterface: NoteClickInterface,
    val NoteClickDeleteInterface: NoteClickDeleteInterface
    ) :RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNoteTitle)
        val timeTV = itemView.findViewById<TextView>(R.id.idTVTimeStamp)
        val deleteTV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv_item,parent, false);
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.timeTV.setText("Last Updated: "+allNotes.get(position).timeStamp)

        holder.deleteTV.setOnClickListener {
            NoteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        holder.itemView.setOnClickListener{
            NoteClickInterface.onNoteClick(allNotes.get(position))
        }

    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface{
    fun onNoteClick(note: Note)
}