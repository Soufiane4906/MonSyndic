package com.example.monsyndic.fragements;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.monsyndic.Bien;
import com.example.monsyndic.Documents;
import com.example.monsyndic.Maps_Activity;
import com.example.monsyndic.MonProfil;
import com.example.monsyndic.Paiement;
import com.example.monsyndic.R;
import com.example.monsyndic.Reclamation;
import com.example.monsyndic.Weather;


public class HomeFragment extends Fragment {

    private ImageView PaiementImage, ProfilImage, ReclamationImage, DocumentImage, SituationImage, BienImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        PaiementImage = view.findViewById(R.id.soldImage);
        ProfilImage = view.findViewById(R.id.profilImage);
        DocumentImage = view.findViewById(R.id.documentImage);
        SituationImage = view.findViewById(R.id.situationImage);
        ReclamationImage = view.findViewById(R.id.reclamationImage);
        BienImage = view.findViewById(R.id.bienImage);

        PaiementImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Paiement.class));
            }
        });
        ProfilImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MonProfil.class));
            }
        });
        DocumentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Documents.class));
            }
        });
        ReclamationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Reclamation.class));
            }
        });
        BienImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Weather.class));
            }
        });
        SituationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Maps_Activity.class));
            }
        });

        return view;
    }
}
