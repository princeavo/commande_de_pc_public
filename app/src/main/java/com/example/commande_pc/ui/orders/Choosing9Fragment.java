package com.example.commande_pc.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.commande_pc.R;
import com.example.commande_pc.databinding.ChoosingItems2Binding;
import com.example.commande_pc.databinding.ChoosingItems9Binding;

public class Choosing9Fragment extends Fragment {
    private ChoosingItems9Binding binding;
    private Button begin9;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ChoosingItems9Binding.inflate(inflater,container,false);
        begin9 = binding.button9;
        begin9.setEnabled(true);
        begin9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next= new Choosing10Fragment();
                FragmentTransaction exchange= getFragmentManager().beginTransaction();
                exchange.replace(R.id.fragment_container,next);
                exchange.addToBackStack(null);
                exchange.commit();
            }
        });
        return binding.getRoot();
    }
}
