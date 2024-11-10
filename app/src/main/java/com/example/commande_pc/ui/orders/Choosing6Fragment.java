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
import com.example.commande_pc.databinding.ChoosingItems6Binding;

public class Choosing6Fragment extends Fragment {
    private ChoosingItems6Binding binding;
    private Button begin6;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ChoosingItems6Binding.inflate(inflater,container,false);
        begin6 = binding.button6;
        begin6.setEnabled(true);
        begin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next= new Choosing7Fragment();
                FragmentTransaction exchange= getFragmentManager().beginTransaction();
                exchange.replace(R.id.fragment_container,next);
                exchange.addToBackStack(null);
                exchange.commit();
            }
        });
        return binding.getRoot();
    }
}
