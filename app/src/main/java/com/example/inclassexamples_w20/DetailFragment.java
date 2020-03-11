package com.example.inclassexamples_w20;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class DetailFragment extends Fragment {

    private boolean isTablet;
    private Bundle dataFromActivity;
    private long id;

    public void setTablet(boolean tablet) { isTablet = tablet; }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromActivity = getArguments();
        id = dataFromActivity.getLong(FragmentExample.ITEM_ID );

        // Inflate the layout for this fragment
        View result =  inflater.inflate(R.layout.fragment_detail, container, false);

        //show the message
        TextView message = (TextView)result.findViewById(R.id.message);
        message.setText(dataFromActivity.getString(FragmentExample.ITEM_SELECTED));

        //show the id:
        TextView idView = (TextView)result.findViewById(R.id.idText);
        idView.setText("ID=" + id);

        // get the delete button, and add a click listener:
        Button deleteButton = (Button)result.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener( clk -> {

            if(isTablet) { //both the list and details are on the screen:
                FragmentExample parent = (FragmentExample)getActivity();
                parent.deleteMessageId((int)id); //this deletes the item and updates the list


                //now remove the fragment since you deleted it from the database:
                // this is the object to be removed, so remove(this):
                parent.getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
            //for Phone:
            else //You are only looking at the details, you need to go back to the previous list page
            {
                EmptyActivity parent = (EmptyActivity) getActivity();
                Intent backToFragmentExample = new Intent();
                backToFragmentExample.putExtra(FragmentExample.ITEM_ID, dataFromActivity.getLong(FragmentExample.ITEM_ID ));

                parent.setResult(Activity.RESULT_OK, backToFragmentExample); //send data back to FragmentExample in onActivityResult()
                parent.finish(); //go back
            }
        });
        return result;
    }
}
