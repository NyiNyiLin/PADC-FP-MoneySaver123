package com.padc.nyi.moneysaver123.fragments;

import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;

/**
 * Created by IN-3442 on 30-Sep-16.
 */
public class BaseFragment extends Fragment {

    public void showMenu(View v, final int id){
        PopupMenu popup=new PopupMenu(getActivity(),v);
        popup.getMenuInflater().inflate(R.menu.popup_option_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        onTapItemEdit(id);
                        return true;
                    case R.id.menu_delete:
                        onTapItemDelete(id);
                        return true;
                    default: return  false;
                }

            }
        });
        popup.show();
    }

    protected void onTapItemEdit(int id){};
    protected void onTapItemDelete(int id){};




}
