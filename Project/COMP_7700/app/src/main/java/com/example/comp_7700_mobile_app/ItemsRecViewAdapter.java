package com.example.comp_7700_mobile_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsRecViewAdapter extends RecyclerView.Adapter<ItemsRecViewAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private Context context;
    private String currentContext;

    public ItemsRecViewAdapter(ArrayList<Item> itemsIn, Context contextIn, String currentContextIn) {
        items = itemsIn;
        notifyDataSetChanged();
        context = contextIn;
        currentContext = currentContextIn;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtViewTitleOfItem.setText(items.get(position).getName());
        holder.txtViewPriceOfItem.setText(String.valueOf(items.get(position).getPrice()));
        holder.txtViewDescriptionOfItem.setText(items.get(position).getDescription());
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getInstance().addItemToCart(items.get(position));
                holder.btnAddToCart.setVisibility(View.GONE);
                holder.btnRemoveFromCart.setVisibility(View.VISIBLE);
                /* try {
                    AppManager.getInstance().addItemToCart(items.get(position));
                    holder.btnAddToCart.setVisibility(View.GONE);
                    holder.btnRemoveFromCart.setVisibility(View.VISIBLE);
                }
                catch (Exception e) {
                    Toast.makeText(context, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                } */
            }
        });
        holder.btnRemoveFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getInstance().removeItemFromCart(items.get(position));
                holder.btnRemoveFromCart.setVisibility(View.GONE);
                holder.btnAddToCart.setVisibility(View.VISIBLE);
                /* try {
                    AppManager.getInstance().removeItemFromCart(items.get(position));
                    holder.btnRemoveFromCart.setVisibility(View.GONE);
                    holder.btnAddToCart.setVisibility(View.VISIBLE);
                }
                catch (Exception e) {
                    Toast.makeText(context, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                } */
            }
        });
        holder.btnRemoveFromSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AppManager.getInstance().removeItemToSell(items.get(position));
                    notifyDataSetChanged();
                }
                catch (Exception e) {
                    Toast.makeText(context, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (currentContext.equals(ItemsActivity.THIS_CONTEXT)) {
            if (!AppManager.getInstance().getItemsInCart().contains(items.get(position))) {
                holder.btnAddToCart.setVisibility(View.VISIBLE);
            }
            else {
                holder.btnRemoveFromCart.setVisibility(View.VISIBLE);
            }
            /* try {
                if (!AppManager.getInstance().getItemsInCart().contains(items.get(position))) {
                    holder.btnAddToCart.setVisibility(View.VISIBLE);
                }
                else {
                    holder.btnRemoveFromCart.setVisibility(View.VISIBLE);
                }
            }
            catch (Exception e) {
                Toast.makeText(context, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
            } */
        }
        else if (currentContext.equals(SellersItemsActivity.THIS_CONTEXT)) {
            holder.btnRemoveFromSale.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // method that will be called only in the Review Cart activity
    public double subtotal() {
        double subtotal = 0;
        for (int i = 0; i < items.size(); i++) {
            subtotal += items.get(i).getPrice();
        }
        return subtotal;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewTitleOfItem, txtViewPriceOfItem, txtViewDescriptionOfItem;
        private Button btnAddToCart, btnRemoveFromCart, btnRemoveFromSale;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewTitleOfItem = itemView.findViewById(R.id.txtViewTitleOfItem);
            txtViewPriceOfItem = itemView.findViewById(R.id.txtViewPriceOfItem);
            txtViewDescriptionOfItem = itemView.findViewById(R.id.txtViewDescriptionOfItem);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            btnRemoveFromSale = itemView.findViewById(R.id.btnRemoveFromSale);
            btnRemoveFromCart = itemView.findViewById(R.id.btnRemoveFromCart);
        }
    }
}
