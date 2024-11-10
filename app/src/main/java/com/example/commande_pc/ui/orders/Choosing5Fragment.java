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
import com.example.commande_pc.databinding.ChoosingItems4Binding;
import com.example.commande_pc.databinding.ChoosingItems5Binding;

public class Choosing5Fragment extends Fragment {
    private ChoosingItems5Binding binding;
    private Button begin5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ChoosingItems5Binding.inflate(inflater,container,false);
        begin5 = binding.button5;
        begin5.setEnabled(true);
        begin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment next= new Choosing6Fragment();
                FragmentTransaction exchange= getFragmentManager().beginTransaction();
                exchange.replace(R.id.fragment_container,next);
                exchange.addToBackStack(null);
                exchange.commit();
            }
        });
        return binding.getRoot();
    }
}
