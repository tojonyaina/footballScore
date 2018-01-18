package com.tojo.mystuff.presentation.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

import com.tojo.mystuff.R;

/**
 * Created by tojo.ny.aina on 26/05/2017.
 */

public class GenericFragment extends Fragment {

    private static ProgressDialog progress;

    /**
     * remplace le fragment actuel par un nouveau fragment
     *
     * @param id             : id de la vue sur laquelle le nouveau fragment doit etre mis
     * @param fragment       : le nouveau fragment a mettre
     * @param animated       : true si on doit animer le remplacement, false sinon
     * @param addToBackStack : true si on doit permettre le retour au fragment precedent, false sinon
     */
    protected void replaceFragment(int id, Fragment fragment, boolean animated, boolean addToBackStack) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
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

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
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
     * retirer de l'ecran ce fragment
     *
     * @param animated : true si on doit animer la suppression du fragment, false sinon
     */
    public void pop(boolean animated) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        if (animated) {
            trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
        }
        trans.remove(this);
        trans.commit();
    }


    public void popBackStackFragment() {
        if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            if (this instanceof IBackCallback) {
                ((IBackCallback) this).setStateOnBack();
            }
        }
    }


    /**
     * afficher une barre de progresseion
     *
     * @param message : le message à afficher dans la barre de progression
     */
    protected void showProgress(String message) {
        dismissProgress();
        try {
            progress = new ProgressDialog(getActivity());
            progress.setMessage(message);
            progress.setCancelable(false);
            progress.show();
        } catch (Exception e) {

        }
    }

    /**
     * retirer la barre de progression de l'ecran
     */
    protected void dismissProgress() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    /**
     * test si le device est connecté à internet
     *
     * @return
     */
    public boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity.getActiveNetworkInfo() != null) {
            if (connectivity.getActiveNetworkInfo().isConnected()) {
                return true;
            }
        }
        return false;
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

}
