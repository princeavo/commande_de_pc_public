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
import com.example.commande_pc.databinding.ChoosingItem8Binding;
import com.example.commande_pc.databinding.ChoosingItems2Binding;

public class Choosing8Fragment extends Fragment {
    private ChoosingItem8Binding binding;
    private Button begin8;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ChoosingItem8Binding.inflate(inflater,container,false);
        begin8 = binding.button8;
        begin8.setEnabled(true);
        begin8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next= new Choosing9Fragment();
                FragmentTransaction exchange= getFragmentManager().beginTransaction();
                exchange.replace(R.id.fragment_container,next);
                exchange.addToBackStack(null);
                exchange.commit();
            }
        });
        return binding.getRoot();
    }
}
