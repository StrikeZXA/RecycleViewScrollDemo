package www.wen.com.recycleviewscrolldemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

/**
 * @author WEN
 * @Description:
 * @date 2020/12/4 23:15
 */
class RecycleAdapter(private val onClickListener: CallBack, private val intList: List<Int>): RecyclerView.Adapter<RecycleAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.button.setOnClickListener {
            onClickListener.call(it,position,intList[position])
        }
        holder.button.text = intList[position].toString()
    }

    override fun getItemCount(): Int {
        return intList.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val button:Button = itemView.findViewById<Button>(R.id.button1)

    }

    interface CallBack{

        fun call(view:View,position:Int,data:Int)

    }

}