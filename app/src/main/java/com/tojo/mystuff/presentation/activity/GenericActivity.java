package com.tojo.mystuff.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.tojo.mystuff.R;


/**
 * Created by tojo.ny.aina on 26/05/2017.
 */

public class GenericActivity extends FragmentActivity {

    /**
     * remplace le fragment actuellement par un nouveau fragment
     *
     * @param id             : id de la vue sur laquelle le nouveau fragment doit etre mis
     * @param fragment       : le nouveau fragment a mettre
     * @param animated       : true si on doit animer le remplacement, false sinon
     * @param addToBackStack : true si on doit permettre le retour au fragment precedent, false sinon
     */
    protected void replaceFragment(int id, Fragment fragment, boolean animated, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (animated) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(id, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * ajout d'un fragment par dessus celui qui est visible a l'ecran actuellement
     *
     * @param id             : id de la vue sur laquelle le nouveau fragment doit etre mis
     * @param fragment       : le nouveau fragment a mettre
     * @param animated       : true si on doit animer le remplacement, false sinon
     * @param addToBackStack : true si on doit permettre le retour au fragment precedent, false sinon
     */
    protected void addFragment(int id, Fragment fragment, boolean animated, boolean addToBackStack) {
        if (fragment == null) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (animated) {
            transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.stay, R.anim.stay, R.anim.slide_out_bottom);
        }
        transaction.add(id, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * afficher un nouvel activity
     *
     * @param activity : la classe de l'activity
     * @param animated : inclure une animation de transition ou pas
     */
    protected void showActivity(Class<?> activity, boolean animated) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (animated) {
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    public static void checkPermissionDevice(FragmentActivity fragmentActivity, Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(fragmentActivity, permission)) {
            }

            ActivityCompat.requestPermissions(fragmentActivity, new String[]{permission}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }
    }

}
