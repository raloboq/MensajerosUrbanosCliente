package co.mensajeros.cliente.Utils;

/**
 * Created by rene on 12/3/14.
 */
import android.support.v4.app.Fragment;

import co.mensajeros.cliente.ChangeTime;
import co.mensajeros.cliente.NewService;
import co.mensajeros.cliente.NewService_2;
import co.mensajeros.cliente.NewService_3;
import co.mensajeros.cliente.NewService4;
import co.mensajeros.cliente.NewService5;


public class PagerItem {

    private final CharSequence mTitle;
    private final int mIndicatorColor;
    private final int mDividerColor;
    private final int position;

    private Fragment[] listFragments;
    public PagerItem(int position, CharSequence title, int indicatorColor, int dividerColor) {
        this.mTitle = title;
        this.position = position;
        this.mIndicatorColor = indicatorColor;
        this.mDividerColor = dividerColor;

        listFragments = new Fragment[] {NewService.newInstance(0), NewService_2.newInstance(0), NewService_3.newInstance(0)};
    }

    public Fragment createFragment() {
        return listFragments[position];
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public int getIndicatorColor() {
        return mIndicatorColor;
    }

    public int getDividerColor() {
        return mDividerColor;
    }
}
