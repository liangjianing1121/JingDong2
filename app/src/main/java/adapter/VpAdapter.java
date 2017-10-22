package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.PopupMenuCompat;
import android.view.ViewGroup;

import java.util.IllegalFormatCodePointException;
import java.util.List;

import fragment.FenleiFragment;

/**
 * Created by DELL on 2017/10/10.
 */

public class VpAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragments;

    public VpAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
