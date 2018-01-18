package com.tojo.mystuff.presentation.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tojo.mystuff.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

/**
 * Created by Tojo Ny Aina on 18/01/2018.
 */

@EActivity(R.layout.activity_bottombar)
public class BottomBarActivity extends FragmentActivity {

    private int[] deaimages = {R.mipmap.dea_football2, R.mipmap.dea_gender_neutral_user, R.mipmap.dea_bookmark_ribbon, R.mipmap.dea_settings};
    private int[] images = {R.mipmap.football2, R.mipmap.gender_neutral_user, R.mipmap.bookmark_ribbon, R.mipmap.settings};

    @ViewsById({R.id.btnMatches, R.id.btnTopPlayer, R.id.btnFavorite, R.id.btnSetting})
    protected List<LinearLayout> bottomButtons;

    @ViewsById({R.id.imgMatches, R.id.imgTopPlayer, R.id.imgFavorite, R.id.imgSetting})
    protected List<ImageView> bottomImages;

    @ViewsById({R.id.txtMatches, R.id.txtTopPlayer, R.id.txtFavorite, R.id.txtSetting})
    protected List<TextView> bottomTextes;

    @AfterViews
    protected void init() {
        for (LinearLayout button : this.bottomButtons) {
            button.setOnClickListener(click);
        }
    }

    private void buttonBottomEvent(int button) {
        this.deactivateStateBottomButtons();
        for (LinearLayout l : bottomButtons) {
            if (l.getId() == button) {
                int index = this.bottomButtons.indexOf(l);
                this.bottomTextes.get(index).setTextColor(ContextCompat.getColor(getBaseContext(), R.color.blueApp));
                this.bottomImages.get(index).setImageDrawable(ContextCompat.getDrawable(getBaseContext(), this.images[index]));
            }
        }
    }

    private void deactivateStateBottomButtons() {
        for (ImageView b : this.bottomImages) {
            int index = this.bottomImages.indexOf(b);
            b.setImageDrawable(ContextCompat.getDrawable(this.getBaseContext(), this.deaimages[index]));
            bottomTextes.get(index).setTextColor(ContextCompat.getColor(this.getBaseContext(), R.color.grayApp));
        }
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buttonBottomEvent(v.getId());
        }
    };
}
