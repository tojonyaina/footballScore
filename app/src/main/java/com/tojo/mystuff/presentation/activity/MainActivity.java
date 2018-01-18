package com.tojo.mystuff.presentation.activity;

import com.tojo.mystuff.R;
import com.tojo.mystuff.presentation.fragment.FragmentAllMatches_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Tojo Ny Aina on 16/01/2018.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends GenericActivity {

    @AfterViews
    protected void init() {
        this.addFragment(R.id.maincontainer, new FragmentAllMatches_(), false, false);
    }
}
