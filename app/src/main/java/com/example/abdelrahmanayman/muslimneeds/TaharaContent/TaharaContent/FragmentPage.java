package com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrahmanayman.muslimneeds.R;

public class FragmentPage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view;
        view = inflater.inflate(R.layout.page_fragment, container, false);
        // Fragment Setup
        Bundle bundle = getArguments();
        Wdo2DetailsModel model = null;
        if (bundle != null) {
            model = bundle.getParcelable("details");
        }
        if (getArguments() != null && model != null) {

            TextView tips = view.findViewById(R.id.tips);
            tips.setText(model.getTips());
            TextView header = view.findViewById(R.id.header);
            header.setText(model.getHeader());
            ImageView image = view.findViewById(R.id.descIv);
            image.setImageBitmap(model.getImage());
        }
        return view;
    }
}
