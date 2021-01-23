package com.example.genshindomaindb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.genshindomaindb.SimpleViewModel
import org.w3c.dom.Text

class SimpleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var models: List<SimpleViewModel> = ArrayList()

    constructor(models: List<SimpleViewModel>)
    {
        this.models = models
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return SimpleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    // pass data to view holder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SimpleViewHolder ->{holder.bind(models.get(position))}
        }
    }


    override fun getItemViewType(int: Int): Int
    {
        return R.layout.simple_text
    }

    class SimpleViewHolder : RecyclerView.ViewHolder
    {
        var simpleTextView : TextView

        constructor(itemView: View) : super(itemView)
        {
            this.simpleTextView = itemView.findViewById(R.id.simple_text) as TextView
        }

        fun bind(simpleView: SimpleViewModel)
        {
            simpleTextView.setText(simpleView.getSimpleText())
        }

    }
}