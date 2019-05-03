package com.uipep.android.searchplaces.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uipep.android.searchplaces.api.bean.places_response.PredictionsItem
import com.uipep.android.searchplaces.databinding.SearchResultRowBinding
import com.uipep.android.searchplaces.interfaces.PlaceClickListerner

class SearchResultAdapter(
    var listOfCandidatesItem: List<PredictionsItem?>? = ArrayList(),
    var placeClickListerner: PlaceClickListerner
) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(
            binding = SearchResultRowBinding.inflate(LayoutInflater.from(p0.context), p0, false),
            listOfCandidatesItem = listOfCandidatesItem,
            placeClickListerner = placeClickListerner
        )

    override fun getItemCount(): Int = listOfCandidatesItem!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.placeTitleTV.text = listOfCandidatesItem?.get(position)?.structuredFormatting?.mainText
        holder.binding.placeFormattedAddressTV.text = listOfCandidatesItem?.get(position)?.structuredFormatting?.secondaryText

    }


    fun setSearchCandidates(listOfCandidatesItem: List<PredictionsItem?>?) {
        this.listOfCandidatesItem = listOfCandidatesItem
        notifyDataSetChanged()
    }

    class ViewHolder(
        var listOfCandidatesItem: List<PredictionsItem?>?,
        var binding: SearchResultRowBinding,
        var placeClickListerner: PlaceClickListerner
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View) {
            placeClickListerner.onPlaceClicked(listOfCandidatesItem?.get(adapterPosition))
        }

    }

}