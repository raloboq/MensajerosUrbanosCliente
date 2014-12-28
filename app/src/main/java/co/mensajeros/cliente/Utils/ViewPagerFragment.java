package co.mensajeros.cliente.Utils;

/**
 * Created by rene on 12/3/14.
 */


        import java.util.ArrayList;
        import java.util.List;

        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.ViewGroup;

        import co.mensajeros.cliente.NewService;
        import co.mensajeros.cliente.NewService_2;
        import co.mensajeros.cliente.OnNextClicked;
        import co.mensajeros.cliente.OnServiceSelected;
        import co.mensajeros.cliente.OnStep2Next;
        import co.mensajeros.cliente.ServiceObject;
        import co.mensajeros.cliente.Utils.ViewPagerAdapter;
        import co.mensajeros.cliente.R;
        import co.mensajeros.cliente.Utils.PagerItem;
        import co.mensajeros.cliente.Utils.SlidingTabLayout;
        import co.mensajeros.cliente.Utils.Utils;

public class ViewPagerFragment extends Fragment implements OnServiceSelected, OnStep2Next {
    private List<PagerItem> mTabs = new ArrayList<PagerItem>();

   public boolean typeselect=false;
    ViewPager mViewPager;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabs.add(new PagerItem(0, "Primer paso", getResources().getColor(Utils.colors[0]),  Color.GRAY));
        mTabs.add(new PagerItem(1, "Segundo paso", getResources().getColor(Utils.colors[1]), Color.GRAY));
        mTabs.add(new PagerItem(2, "Tercer paso", getResources().getColor(Utils.colors[2]), Color.GRAY));

       // mTabs.add(new PagerItem(3, "Cuarto paso", getResources().getColor(Utils.colors[4]), Color.GRAY));



        NewService nservice = new NewService();
        nservice.setonServiceSelected(this);

        NewService_2 nservice2 = new NewService_2();
        nservice2.setonStep2Next(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.viewpager_fragment, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
         mViewPager = (ViewPager) view.findViewById(R.id.mPager);


        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mTabs));

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.mTabs);
        mSlidingTabLayout.setBackgroundResource(R.color.white);
        mSlidingTabLayout.setViewPager(mViewPager);
       

        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return mTabs.get(position).getIndicatorColor();
            }

            @Override
            public int getDividerColor(int position) {
                return mTabs.get(position).getDividerColor();
            }
        });




        /*mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                Log.i("pager","scrolled "+i+" "+v+" "+i2);
            }

            @Override
            public void onPageSelected(int i) {
                Log.i("pager","selected "+i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.i("pager","state changed "+i);
            }
        });*/

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(typeselect)
                return false;
                else
                    return true;
            }
        });








    }


    @Override
    public void serviceselected() {

        typeselect=true;
        mViewPager.setCurrentItem(1);

    }


    @Override
    public void step2next() {
        mViewPager.setCurrentItem(2);
    }
}