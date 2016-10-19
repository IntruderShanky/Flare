package com.intrusoft.indicator.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.intrusoft.indicator.Flare;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    //Image Ids
    int images[] = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mapping
        Flare indicator = (Flare) findViewById(R.id.indicator);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        //setting Adapter
        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.pager_view, null, false);
                ImageView header = (ImageView) view.findViewById(R.id.header);
                header.setImageResource(images[position]);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((LinearLayout) object);
            }

        });

        //setting PageChangeListener
        pager.addOnPageChangeListener(this /* OnPageChangeListener */);

        //setting Flare
        indicator.setUpWithViewPager(pager);
        indicator.addOnPageChangeListener(this /* OnPageChangeListener */);

        indicator.setIndicatorColor(Color.BLUE);
        indicator.setIndicatorGap(20);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
