package br.com.top.mambandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(val apps: List<App>, val listener: ((App) -> Unit)? = null) :
    RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_app, null) as Button
        return AppViewHolder(view)
    }

    override fun getItemCount() = apps.size

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.button.text = apps[position].name
        holder.button.setOnClickListener {
            listener?.invoke(apps[position])
        }
    }

    open class AppViewHolder(val button: Button) : RecyclerView.ViewHolder(button)
}