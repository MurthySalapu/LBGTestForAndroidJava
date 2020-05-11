package com.lbg.lbgtest.ui.fragments;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.lbg.lbgtest.BR;
import com.lbg.lbgtest.databinding.ItemViewBinding;
import com.lbg.lbgtest.model.CommonResult;
import com.lbg.lbgtest.viewmodel.ItemAdapterViewModel;

import java.util.List;

/**
 * Name - Item view Adapter
 * Purpose - Bind the item to Recycler view
 */
public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.GenericViewHolder> {

    private int layoutId;
    private List<CommonResult> dataSet;
    private ItemAdapterViewModel viewModel;
    private OnItemClickListener onItemClickListener;

    public ItemViewAdapter(@LayoutRes int layoutId, List<CommonResult> commonResults,
                           ItemAdapterViewModel viewModel,OnItemClickListener onItemClickListener) {
        this.layoutId = layoutId;
        this.dataSet = commonResults;
        this.viewModel = viewModel;
        this.onItemClickListener = onItemClickListener;
        subscribeObserver();
    }

     private void subscribeObserver(){
        viewModel.getUrl().observeForever( new Observer<String>() {
            @Override
            public void onChanged(String url) {
                if(!TextUtils.isEmpty(url)) {
                    onItemClickListener.onClicked(url);
                    viewModel.getUrl().setValue(null);
                }
            }
        });
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }


    @NonNull
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemViewBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setDataSet(List<CommonResult> data) {
        this.dataSet = data;
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ItemViewBinding binding;

        GenericViewHolder(ItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(Integer position) {
            binding.setVariable(BR.dataModel, dataSet.get(position));
            binding.setVariable(BR.viewModel,viewModel);
            binding.executePendingBindings();
        }

    }

    /**
     * Item click on Adapter view
     */
    public interface OnItemClickListener{
        void onClicked(String url);
    }
}
