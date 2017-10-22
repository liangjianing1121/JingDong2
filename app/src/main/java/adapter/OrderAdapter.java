package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by DELL on 2017/10/22.
 */

public class OrderAdapter  extends FragmentPagerAdapter{
        private Context context;
        private List<Fragment> list;
        private String[] title={"全部","待支付","已支付","已取消"};
        public OrderAdapter(FragmentManager fm, Context context, List<Fragment> list) {
            super(fm);
            this.context = context;
            this.list = list;
        }
        public OrderAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }


        //重写方法实现tablayout和viewpager联动
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
}