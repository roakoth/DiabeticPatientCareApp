package com.example.diabeticpatientcareapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.Calendar;

public class RemindersFragment extends Fragment {
    View view;
    FloatingActionButton fab;
    //Pending intent instance
    private PendingIntent pendingIntent;

    //Alarm Request Code
    private static final int ALARM_REQUEST_CODE = 133;


    private RadioButton secondsRadioButton, minutesRadioButton, hoursRadioButton;

    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // view = inflater.inflate(R.layout.fragment_reminders,container,false);

        final View mRelativelayout = inflater.inflate(R.layout.activity_alarm_main, container, false);
        //Find id of all radio buttons
        secondsRadioButton = (RadioButton) mRelativelayout.findViewById(R.id.seconds_radio_button);
        minutesRadioButton = (RadioButton) mRelativelayout.findViewById(R.id.minutes_radio_button);
        hoursRadioButton = (RadioButton) mRelativelayout.findViewById(R.id.hours_radio_button);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), ALARM_REQUEST_CODE, alarmIntent, 0);

        //Find id of Edit Text
        final EditText editText = (EditText) mRelativelayout.findViewById(R.id.input_interval_time);

        //Set On CLick over start alarm button
        mRelativelayout.findViewById(R.id.start_alarm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInterval = editText.getText().toString().trim();//get interval from edittext

                //check interval should not be empty and 0
                if (!getInterval.equals("") && !getInterval.equals("0"))
                    //finally trigger alarm manager
                    triggerAlarmManager(getTimeInterval(getInterval));

            }
        });

        //set on click over stop alarm button
        mRelativelayout.findViewById(R.id.stop_alarm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Stop alarm manager
                stopAlarmManager();
            }
        });
        //fab=(com.github.clans.fab.FloatingActionButton)view.findViewById(R.id.menu);
        //fab.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View v) {
        //Toast.makeText(getActivity(),"Blood sugar",Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(getActivity(), add_reminder.class);
        // startActivity(intent);
        // }
        //});

        //fab = (com.github.clans.fab.FloatingActionButton) mRelativelayout.findViewById(R.id.fab_button);

//       fab.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//              // Intent intent = new Intent(getActivity(), add_reminder.class);
//               Intent intent = new Intent(getActivity(), AlarmMain.class);
//               startActivity(intent);
//           }
//       });

        return mRelativelayout;

    }

    private int getTimeInterval(String getInterval) {
        int interval = Integer.parseInt(getInterval);//convert string interval into integer

        //Return interval on basis of radio button selection
        if (secondsRadioButton.isChecked())
            return interval;
        if (minutesRadioButton.isChecked())
            return interval * 60;//convert minute into seconds
        if (hoursRadioButton.isChecked()) return interval * 60 * 60;//convert hours into seconds

        //else return 0
        return 0;
    }

    //Trigger alarm manager with entered time interval
    public void triggerAlarmManager(int alarmTriggerTime) {
        // get a Calendar object with current time
        Calendar cal = Calendar.getInstance();
        // add alarmTriggerTime seconds to the calendar object
        cal.add(Calendar.SECOND, alarmTriggerTime);

        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);//set alarm manager with entered timer by converting into milliseconds

        Toast.makeText(getActivity(), "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();

        Log.e("TAG", "Message");
    }

    //Stop/Cancel alarm manager
    public void stopAlarmManager() {

        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);//cancel the alarm manager of the pending intent


        //Stop the Media Player Service to stop sound
        getActivity().stopService(new Intent(getActivity(), AlarmSoundService.class));

        //remove the notification from notification tray
        NotificationManager notificationManager = (NotificationManager) getActivity()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(AlarmNotificationService.NOTIFICATION_ID);

        Toast.makeText(getActivity(), "Alarm Canceled.", Toast.LENGTH_SHORT).show();
    }

}


