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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.diabeticpatientcareapp.view.TitleAdapter;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class tipsFragment extends Fragment {
    View view;
    FloatingActionButton fab;
    private static final String TAG = "diabetes_care_tips";
    private Context mContext;
    ArrayList<String> titleArrayList;
    private RecyclerView mRecyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mRelativelayout = (RelativeLayout) inflater.inflate(R.layout.activity_diabetes_care_tips,container,false);
        //fab = (com.github.clans.fab.FloatingActionButton) mRelativelayout.findViewById(R.id.fab_button1);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), diabetes_care_tips.class);
//                startActivity(intent);
//            }
//        });
        mContext = getActivity();

        titleArrayList = new ArrayList<String>();

        titleArrayList.add(constants.REDUCE_BLOOD_SUGAR_THROUGH_DIET);
        titleArrayList.add(constants.EXERCISE_TIPS_FOR_DIABETIC_PATIENTS);
        titleArrayList.add(constants.DIET_SPECIFIED_FOR_LOW_BLOOD_SUGAR_PATIENTS);
        titleArrayList.add(constants.GENERAL_TIPS);
        titleArrayList.add(constants.TIPS_FOR_MANAGING_LOW_BLOOD_SUGAR);

        mRecyclerView = (RecyclerView) mRelativelayout.findViewById(R.id.title_layout_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TitleAdapter adapter = new TitleAdapter(mContext, titleArrayList, new CustomItemClicklistener() {
            @Override
            public void onItemClick(View v, int position) {

                Intent desIntent = new Intent(mContext,descriptionActivity.class);
                desIntent.putExtra("titles",titleArrayList.get(position));

                startActivity(desIntent);

                Toast.makeText(mContext,"clicked" +titleArrayList.get(position),Toast.LENGTH_SHORT).show();

            }
        });

        mRecyclerView.setAdapter(adapter);



        return mRelativelayout;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                // Toast.makeText(this,"Forum is selected",Toast.LENGTH_SHORT).show();
                Intent i = new Intent (getActivity(),forum.class);
                startActivity(i);
                return true;

            default:return super.onOptionsItemSelected(item);

        }



    }

    // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);

        return true;

    }

}
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tips_fragment);
//    }

