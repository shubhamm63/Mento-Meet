package com.example.mentomeet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {


    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final model model) {
        holder.SNAME.setText(model.getSNAME());
        holder.MENAME.setText(model.getMENAME());
        holder.SROLL.setText(model.getSROLL());
        holder.SCONTACT.setText(model.getSCONTACT());
        holder.SEMAIL.setText(model.getSEMAIL());
        holder.PCONTACT.setText(model.getPCONTACT());
        holder.PEMAIL.setText(model.getPEMAIL());
        holder.MNAME.setText(model.getMNAME());
        holder.FNAME.setText(model.getFNAME());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.SROLL.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontant))
                        .setExpanded(true, 1700)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText SROLL = myview.findViewById(R.id.w);
                final EditText MENAME = myview.findViewById(R.id.w2);
                final EditText SCONTACT = myview.findViewById(R.id.w3);
                final EditText SEMAIL = myview.findViewById(R.id.w5);
                final EditText PCONTACT = myview.findViewById(R.id.w6);
                final EditText PEMAIL = myview.findViewById(R.id.w7);
                Button submit = myview.findViewById(R.id.W8);

                MENAME.setText(model.getMENAME());
                SROLL.setText(model.getSROLL());
                SCONTACT.setText(model.getSCONTACT());
                SEMAIL.setText(model.getSEMAIL());
                PCONTACT.setText(model.getPCONTACT());
                PEMAIL.setText(model.getPEMAIL());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("SROLL", SROLL.getText().toString());
                        map.put("MENAME", MENAME.getText().toString());
                        map.put("SNAME", SCONTACT.getText().toString());
                        map.put("SEMAIL", SEMAIL.getText().toString());
                        map.put("PCONTACT", PCONTACT.getText().toString());
                        map.put("PEMAIL", PEMAIL.getText().toString());

//                        mdatabaseReference.child(myObject.getId()).setValue(myObject)

                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.SROLL.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });


    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);

    }


    class myviewholder extends RecyclerView.ViewHolder {
        TextView SNAME, MENAME, FNAME, MNAME, SCONTACT, SEMAIL, PCONTACT, PEMAIL, SROLL;
        ImageView edit, delete;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            SNAME = (TextView) itemView.findViewById(R.id.textview_Stud_Name);
            MENAME = (TextView) itemView.findViewById(R.id.textview_ment_name);
            FNAME = (TextView) itemView.findViewById(R.id.textview_Father_name);
            MNAME = (TextView) itemView.findViewById(R.id.textview_mother_name);
            SCONTACT = (TextView) itemView.findViewById(R.id.textview_mobile_no);
            SEMAIL = (TextView) itemView.findViewById(R.id.textview_email_id);
            PCONTACT = (TextView) itemView.findViewById(R.id.textview_ppmobile_no);
            PEMAIL = (TextView) itemView.findViewById(R.id.textview_pemail_id);
            SROLL = (TextView) itemView.findViewById(R.id.textview_roll_no);

            edit = (ImageView) itemView.findViewById(R.id.editicon);
            delete = (ImageView) itemView.findViewById(R.id.deleteicon);

        }
    }


}
