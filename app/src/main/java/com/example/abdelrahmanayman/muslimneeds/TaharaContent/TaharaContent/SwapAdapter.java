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
    private Fragment fragment ;
    public SwapAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
            fragment= new FragmentPage();
        switch (position) {
            case 0:
              SetFragment(R.drawable.wdo2_1 , R.string.wdo2details1 , R.string.wdo2details1Tips  );
                break;
            case 1:
                SetFragment(R.drawable.wdo2_2 , R.string.wdo2details2 , R.string.wdo2details2Tips  );
                break;
            case 2:
                SetFragment(R.drawable.wdo2_3 , R.string.wdo2details3 , R.string.wdo2details3Tips );
                break;
            case 3:
                SetFragment(R.drawable.wdo2_4 , R.string.wdo2details4 , R.string.wdo2details4Tips  );
                break;
            case 4:
                SetFragment(R.drawable.wdo2_5 , R.string.wdo2details5 , R.string.wdo2details5Tips  );
                break;
            case 5:
                SetFragment(R.drawable.wdo2_6 , R.string.wdo2details6 , R.string.wdo2details6Tips  );
                break;
            case 6:
               SetFragment(R.drawable.wdo2_6 , R.string.wdo2details7 , R.string.wdo2details7Tips);
                break;
            case 7:
                SetFragment(R.drawable.wdo2_8 , R.string.wdo2details8 , R.string.wdo2details8Tips  );
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 8;
    }

    private void SetFragment (int iconImage ,int detailsName , int detailsName1  ){

        Wdo2DetailsModel wdo2obj;
        Bitmap bMap;
        Bundle bundle;
        bMap = BitmapFactory.decodeResource(context.getResources(),iconImage);
        wdo2obj = new Wdo2DetailsModel(context.getString(detailsName), bMap, context.getString(detailsName1));
        bundle = new Bundle();
        bundle.putParcelable("details", wdo2obj);
        fragment.setArguments(bundle);
    }
}
