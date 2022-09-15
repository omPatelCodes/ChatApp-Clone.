package com.opproject.whatsfinalclone.Adpters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.opproject.whatsfinalclone.Fragments.CallFrag;
import com.opproject.whatsfinalclone.Fragments.ChatsFrag;
import com.opproject.whatsfinalclone.Fragments.StatusFrag;

public class ReplaceFragAdp extends FragmentPagerAdapter {
    public ReplaceFragAdp(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ChatsFrag();
            case 1: return new StatusFrag();
            case 2: return new CallFrag();
            default: return new ChatsFrag();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "Chats";
        }
        if (position == 1){
            title = "Status";
        }
        if (position == 2){
            title = "Calls";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
