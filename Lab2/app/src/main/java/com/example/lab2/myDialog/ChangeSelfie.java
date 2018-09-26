package com.example.lab2.myDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lab2.R;

public class ChangeSelfie extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("上传头像");

        builder.setItems(R.array.pic_lists, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
                switch (which){
                    case 0:Toast.makeText(getContext(),"您选择了[拍摄]",Toast.LENGTH_SHORT).show();break;
                    case 1:Toast.makeText(getContext(),"您选择了[从相册选择]",Toast.LENGTH_SHORT).show();break;
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getContext(),"您选择了[取消]",Toast.LENGTH_SHORT).show();
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}