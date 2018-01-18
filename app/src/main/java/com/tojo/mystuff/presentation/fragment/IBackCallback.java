package com.tojo.mystuff.presentation.fragment;

/**
 * tous fragments implementant cette interface fixe l'etat du flag isFromBack du
 * fragment parent afin de reinitaliser l'etat de l'actionbar lors de l'appel a
 * la methode popBackStackFragment() d'AbstractFragment ou lorsqu'on clique sur
 * le bouton Back
 * 
 * @author Matelli
 */

public interface IBackCallback {

	public void setStateOnBack();
}
