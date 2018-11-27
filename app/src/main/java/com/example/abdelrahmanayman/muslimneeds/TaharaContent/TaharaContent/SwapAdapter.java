package com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.abdelrahmanayman.muslimneeds.R;

public class SwapAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public SwapAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new FragmentPage();
        Wdo2DetailsModel wdo2obj;
        Bitmap bMap;
        Bundle bundle;
        switch (position) {
            case 0:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_1);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details1), bMap, context.getString(R.string.wdo2details1Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
            case 1:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_2);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details2), bMap, context.getString(R.string.wdo2details2Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
            case 2:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_3);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details3), bMap, context.getString(R.string.wdo2details3Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
            case 3:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_4);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details4), bMap, context.getString(R.string.wdo2details4Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
            case 4:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_5);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details5), bMap, context.getString(R.string.wdo2details5Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
            case 5:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_6);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details6), bMap, context.getString(R.string.wdo2details6Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
            case 6:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_6);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details7), bMap, context.getString(R.string.wdo2details7Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
            case 7:
                bMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wdo2_8);
                wdo2obj = new Wdo2DetailsModel(context.getString(R.string.wdo2details8), bMap, context.getString(R.string.wdo2details8Tips));
                bundle = new Bundle();
                bundle.putParcelable("details", wdo2obj);
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 8;
    }
}
