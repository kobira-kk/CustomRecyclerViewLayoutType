package com.example.samplerecyclerview


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.recyclerView = view.findViewById(R.id.container_recycler_view)
        this.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = MainViewAdapter(
                generateItemList(),
                object : MainViewAdapter.ListListener {
                    override fun onClickItem(tappedView: View, itemAModel: ItemAModel) {
                        this@MainFragment.onClickItem(tappedView, itemAModel)
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.recyclerView?.adapter = null
        this.recyclerView = null
    }

    private fun generateItemList(): List<ItemAModel> {
        val itemList = mutableListOf<ItemAModel>()
        for(i in 0..100) {
            val moge: ItemType.Hoge = if (i % 2 == 0 ) ItemType.Hoge.ItemA else ItemType.Hoge.ItemB
            val item: ItemAModel = ItemAModel().apply {
                type = moge
                text = "${i}番目の要素。"
            }
            itemList.add(item)
        }
        return itemList
    }

    private fun onClickItem(tappedView: View, itemAModel: ItemAModel) {

    }
}

class ItemType {
    enum class Hoge {
        ItemA,
        ItemB
    }
}