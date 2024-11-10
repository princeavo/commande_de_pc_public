package com.example.commande_pc.ui.orders;

import androidx.fragment.app.Fragment;

public abstract class OrderChoiceFragment extends Fragment {
    public OrderChoiceFragment(){
        super();
    }
    public void addToList(long item_id,int quantity){
        PlaceOrderFragment.addToOrderItems(item_id,quantity,getPosition());
    }
    abstract int getPosition();
}
