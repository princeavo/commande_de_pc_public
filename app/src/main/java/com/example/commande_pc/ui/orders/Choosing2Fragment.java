package com.example.commande_pc.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commande_pc.R;
import com.example.commande_pc.adapters.ChoiceItemAdapter;
import com.example.commande_pc.adapters.ItemsListAdapter;
import com.example.commande_pc.databinding.ChoosingItems1Binding;
import com.example.commande_pc.databinding.ChoosingItems2Binding;
import com.example.commande_pc.entity.Item;

import java.util.ArrayList;

public class Choosing2Fragment extends OrderChoiceFragment {
    private ChoosingItems2Binding binding;
    private Button begin2;
    private ArrayList<Item> items;
    RecyclerView recyclerView;
    

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        binding = ChoosingItems2Binding.inflate(inflater,container,false);
        recyclerView = binding.recycleview;
        begin2 = binding.button2;
        begin2.setEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        Bundle bundle = getArguments();

        items = (ArrayList<Item>) bundle.getSerializable("items");
        
        recyclerView.setAdapter(new ChoiceItemAdapter(this,items));

        begin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next= new Choosing3Fragment();
                FragmentTransaction exchange= getFragmentManager().beginTransaction();
                exchange.replace(R.id.fragment_container,next);
                exchange.addToBackStack(null);
                exchange.commit();
            }
        });
        return binding.getRoot();
    }

    @Override
    public int getPosition() {
        return 0;
    }
}
