package com.example.diabeticpatientcareapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.view.helpAdapter;

import java.util.ArrayList;

public class helpFragment extends Fragment {
    View view;
    private Context mContext;
    ArrayList<String> titleHelpList;
    private RecyclerView mRecyclerView;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mRelativelayout = (RelativeLayout) inflater.inflate(R.layout.activity_help_fragment, container, false);
//
        mContext = getActivity();
        titleHelpList = new ArrayList<String>();
        titleHelpList.add(constantsHelp.HELP_AND_SUPPORT);

        mRecyclerView = (RecyclerView) mRelativelayout.findViewById(R.id.title_help);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        helpAdapter adapter = new helpAdapter(mContext,titleHelpList,new CustomItemClicklistener(){

            @Override
            public void onItemClick(View v, int position) {
                Intent desIntent = new Intent(mContext,descriptionActivity.class);
                desIntent.putExtra("titles",titleHelpList.get(position));

                startActivity(desIntent);

                Toast.makeText(mContext,"clicked" +titleHelpList.get(position),Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(adapter);


        return mRelativelayout;
    }
}
