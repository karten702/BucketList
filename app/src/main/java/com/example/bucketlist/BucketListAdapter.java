package com.example.bucketlist;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListAdapter.ViewHolder> {

    private List<BucketItem> bucketItemList;
    private BucketItemClickListener bucketItemClickListener;

    public BucketListAdapter(List<BucketItem> bucketItemList, BucketItemClickListener bucketItemClickListener) {
        this.bucketItemList = bucketItemList;
        this.bucketItemClickListener = bucketItemClickListener;
    }

    @NonNull
    @Override
    public BucketListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.grid_cell, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BucketListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.titleLabel.setText(bucketItemList.get(viewHolder.getAdapterPosition()).getTitle());
        viewHolder.descriptionLabel.setText(bucketItemList.get(viewHolder.getAdapterPosition()).getDescription());

        if (bucketItemList.get(viewHolder.getAdapterPosition()).getCompleted()){
            viewHolder.titleLabel.setPaintFlags(viewHolder.titleLabel.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.descriptionLabel.setPaintFlags(viewHolder.descriptionLabel.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.completedCheckbox.setChecked(Boolean.TRUE);
        }
        else{
            viewHolder.titleLabel.setPaintFlags(viewHolder.titleLabel.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.descriptionLabel.setPaintFlags(viewHolder.descriptionLabel.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.completedCheckbox.setChecked(Boolean.FALSE);
        }
    }

    @Override
    public int getItemCount() {
        return bucketItemList.size();
    }

    public interface BucketItemClickListener {

        void onCheckboxClick(BucketItem bucketItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleLabel;
        private TextView descriptionLabel;
        private CheckBox completedCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleLabel = itemView.findViewById(R.id.TitleLabel);
            descriptionLabel = itemView.findViewById(R.id.descriptionLabel);
            completedCheckbox = itemView.findViewById(R.id.bucketItemCheckbox);

            completedCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bucketItemClickListener.onCheckboxClick(bucketItemList.get(getAdapterPosition()));
                }
            });
        }
    }

}
