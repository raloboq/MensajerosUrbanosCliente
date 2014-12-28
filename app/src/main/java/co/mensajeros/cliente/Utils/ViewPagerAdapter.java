package co.mensajeros.cliente.Utils;

/**
 * Created by rene on 12/3/14.
 */
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import co.mensajeros.cliente.Utils.PagerItem;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<PagerItem> mTabs;
    public ViewPagerAdapter(FragmentManager fragmentManager, List<PagerItem> mTabs) {
        super(fragmentManager);
        this.mTabs = mTabs;
    }

    @Override
    public Fragment getItem(int i) {
        return mTabs.get(i).createFragment();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).getTitle();
    }
}