package com.example.commande_pc.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.commande_pc.MainActivity;
import com.example.commande_pc.R;
import com.example.commande_pc.databinding.ChoosingItems1Binding;
import com.example.commande_pc.databinding.ChoosingItems2Binding;
import com.example.commande_pc.databinding.ChoosingItems4Binding;
import com.example.commande_pc.databinding.ChosingItems3Binding;
import com.example.commande_pc.entity.Item;
import com.example.commande_pc.entity.OrderItem;
import com.example.commande_pc.entity.Requester;
import com.example.commande_pc.entity.StoreKeeper;

import java.util.ArrayList;

public class PlaceOrderFragment extends Fragment {
    private ChoosingItems1Binding binding;
    private Button begin;
    public static ArrayList<ArrayList<OrderItem>> orderItems = new ArrayList<>(9);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ChoosingItems1Binding.inflate(inflater,container,false);
        begin = binding.button;
        begin.setEnabled(true);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FirstFragment()).addToBackStack(null).commit();
        ArrayList<Item> items = ((Requester) (MainActivity.getUser())).getItems();
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next= new Choosing2Fragment();
                Bundle args = new Bundle();
                args.putSerializable("items",items);
                next.setArguments(args);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, next).addToBackStack(null).commit();
            }
        });
        return binding.getRoot();
    }
    private void nextStep(){

    }
    public static void addToOrderItems(long item_id, int quantity,int position){
        orderItems.get(position).add(new OrderItem(item_id,quantity));
    }
    public static void removeFromOrderItems(long item_id, int quantity,int position){
        orderItems.get(position).removeIf(o -> o.getItemId() == item_id);
    }
}

