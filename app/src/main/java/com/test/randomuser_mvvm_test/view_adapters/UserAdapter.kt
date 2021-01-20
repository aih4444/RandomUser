package com.test.randomuser_mvvm_test.view_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.randomuser_mvvm_test.R
import com.test.randomuser_mvvm_test.database.DatabaseUser
import com.test.randomuser_mvvm_test.databinding.RowUserBinding
import com.test.randomuser_mvvm_test.domain.User

class UserAdapter(val callback: UserClick) : RecyclerView.Adapter<UserViewHolder>() {
    // private var viewModelAdapter: JokesAdapter? = null

    var result: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val withDataBinding: RowUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            UserViewHolder.LAYOUT,
            parent,
            false
        )
        return UserViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.result = result[position]
            //To handle onclick
            it.resultsCallback = callback
        }
    }

    override fun getItemCount() = result.size
}

class UserViewHolder(val viewDataBinding: RowUserBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_user
    }
}

/**
 * Click listener for User. By giving the block a name it helps a reader understand what it does.
 *
 */
class UserClick(val block: (User) -> Unit) {
    /**
     * Called when a particular user is clicked
     *
     * @param user the user that was clicked
     */
    fun onClick(user: User) = block(user)
}
