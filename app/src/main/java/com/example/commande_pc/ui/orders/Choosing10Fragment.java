package com.example.commande_pc.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.commande_pc.databinding.ChoosingItems10Binding;
import com.example.commande_pc.databinding.ChoosingItems9Binding;

public class Choosing10Fragment extends Fragment {
    private ChoosingItems10Binding binding;
    private Button end;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = ChoosingItems10Binding.inflate(inflater,container,false);
        end = binding.endbutton;
        end.setEnabled(true);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return binding.getRoot();
    }
}
