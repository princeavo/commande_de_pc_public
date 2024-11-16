package com.example.commande_pc.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commande_pc.R;
import com.example.commande_pc.entity.Item;
import com.example.commande_pc.entity.OrderItem;
import com.example.commande_pc.ui.orders.OrderChoiceFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ChoiceItemAdapter extends RecyclerView.Adapter<ChoiceItemAdapter.ViewHolder>{
    private ArrayList<Item> items;
    ArrayList<OrderItem> initialState;
    OrderChoiceFragment fragment;
    public ChoiceItemAdapter(OrderChoiceFragment fragment, ArrayList<Item> newItems, ArrayList<OrderItem> state){
        this.fragment = fragment;
        items = newItems;
        this.initialState = state;
    }
    private void setItems(ArrayList<Item> filteredItems) {
        this.items = filteredItems;
        notifyDataSetChanged();
    }
    public Item getItem(int position) {
        return items.get(position);
    }
    @NonNull
    @Override
    public ChoiceItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChoiceItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.preview_item_requester,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceItemAdapter.ViewHolder holder, int position) {
        initialState.stream().filter(orderItem -> orderItem.getItemId() == items.get(position).getId()).findFirst().ifPresent(orderItem -> {
            holder.checkBox.setChecked(true);
            holder.quantityEditText.setText(String.valueOf(orderItem.getQuantity()));
        });
        String description = items.get(position).getDescription();
        String truncatedDescription = description.substring(0,Math.min(description.length(),30)) + "...";
        holder.descriptionTextView.setText(truncatedDescription);
        holder.typeTextView.setText(items.get(position).getType().toUpperCase());
//        holder.deleteItemButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(v.getContext()).setMessage("Etes vous sûr de vouloir supprimer ?").setTitle("Confirmation").setIcon(R.drawable.web_link).setNegativeButton("Oui", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        StoreKeeper storeKeeper = ((StoreKeeper) (MainActivity.getUser()));
//                        int position_of_item = holder.getAdapterPosition();
//                        if(storeKeeper.deleteItem(items.get(position_of_item))){
//                            items.remove(position_of_item);
//                            notifyItemRemoved(position_of_item);
//                        }
//                    }
//                }).setPositiveButton("Non",null).show();
//            }
//        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int quantity = 0;
                try{
                    quantity = Integer.parseInt(holder.quantityEditText.getText().toString());
                    if(quantity <0) {
                        quantity = 0;
                        holder.quantityEditText.setText("0");
                    }
                }catch (NumberFormatException | NullPointerException ignored){
                    holder.quantityEditText.setText("0");
                }
                if(isChecked){
                    fragment.addToList(items.get(holder.getAdapterPosition()).getId(), quantity );
                }else{
                    fragment.deleteFromList(items.get(holder.getAdapterPosition()).getId(), quantity);
                }
            }
        });
        holder.quantityEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int quantity = 0;
                try{
                    quantity = Integer.parseInt(s.toString());
                    if(quantity <0) {
                        quantity = 0;
                        holder.quantityEditText.setText("0");
                    }
                }catch (NumberFormatException | NullPointerException ignored){
                    holder.quantityEditText.setText("0");
                }
                if(holder.checkBox.isChecked()){
                    fragment.updateQuantityOfOrderItemOfList(items.get(holder.getAdapterPosition()).getId(), quantity );
                }
            }
        });
        
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditOrShowComponentDialog editOrShowComponentDialog = new EditOrShowComponentDialog(v.getContext(),getItem(holder.getAdapterPosition()),holder.getAdapterPosition());
//                editOrShowComponentDialog.show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView descriptionTextView,typeTextView;
        private TextInputEditText quantityEditText;
        private CheckBox checkBox;
        //private Button deleteItemButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            quantityEditText = itemView.findViewById(R.id.quantityEditText2);
            quantityEditText.setText("0");
            checkBox = itemView.findViewById(R.id.checkBox);
            //deleteItemButton = itemView.findViewById(R.id.deleteItemButton);
        }
    }
}
