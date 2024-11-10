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
import com.example.commande_pc.databinding.ChosingItems3Binding;

public class Choosing3Fragment extends Fragment {
    private ChosingItems3Binding binding;
    private Button begin3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ChosingItems3Binding.inflate(inflater,container,false);
        begin3 = binding.button3;
        begin3.setEnabled(true);
        begin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next= new Choosing4Fragment();
                FragmentTransaction exchange= getFragmentManager().beginTransaction();
                exchange.replace(R.id.fragment_container,next);
                exchange.addToBackStack(null);
                exchange.commit();
            }
        });
        return binding.getRoot();
    }
}