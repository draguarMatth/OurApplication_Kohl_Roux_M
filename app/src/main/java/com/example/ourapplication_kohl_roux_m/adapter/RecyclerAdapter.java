package com.example.ourapplication_kohl_roux_m.adapter;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Trajet;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;

import java.util.List;
import java.util.Objects;


public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> mData;
    private RecyclerViewItemClickListener mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        ViewHolder(TextView textView) {
            super(textView);
            mTextView = textView;
        }
    }

    public RecyclerAdapter(RecyclerViewItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(view -> mListener.onItemClick(view, viewHolder.getAdapterPosition()));
        v.setOnLongClickListener(view -> {
            mListener.onItemLongClick(view, viewHolder.getAdapterPosition());
            return true;
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T item = mData.get(position);
        if (item.getClass().equals(Car.class))
            holder.mTextView.setText(((Car) item).getNickName());
        if (item.getClass().equals(Trajet.class))
            holder.mTextView.setText(((Trajet) item).getName() + " " + ((Trajet) item).getKmTot());
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> data) {
        if (mData == null) {
            mData = data;
            notifyItemRangeInserted(0, data.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mData.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof Car) {
                        return ((Car) mData.get(oldItemPosition)).getUid().equals(((Car) data.get(newItemPosition)).getUid());
                    }
                    if (mData instanceof Trajet) {
                        return ((Trajet) mData.get(oldItemPosition)).getCarId().equals(
                                ((Trajet) data.get(newItemPosition)).getCarId());
                    }
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof Car) {
                        Car newCar = (Car) data.get(newItemPosition);
                        Car oldCar = (Car) mData.get(newItemPosition);
                        return Objects.equals(newCar.getUid(),(oldCar.getUid()))
                                && Objects.equals(newCar.getNickName(), oldCar.getNickName())
                               /* && Objects.equals(newCar.getBalance(), oldCar.getBalance())
                                && newCar.getOwner().equals(oldCar.getOwner()) */ ;
                    }
                    if (mData instanceof Trajet) {
                        Trajet newClient = (Trajet) data.get(newItemPosition);
                        Trajet oldClient = (Trajet) mData.get(newItemPosition);
                        return Objects.equals(newClient.getCarId(), oldClient.getCarId())
                                && Objects.equals(newClient.getName(), oldClient.getName())
                                && Objects.equals(newClient.getDate(), oldClient.getDate())
                               /* && newClient.getPassword().equals(oldClient.getPassword()) */ ;
                    }
                    return false;
                }
            });
            mData = data;
            result.dispatchUpdatesTo(this);
        }
    }
}
