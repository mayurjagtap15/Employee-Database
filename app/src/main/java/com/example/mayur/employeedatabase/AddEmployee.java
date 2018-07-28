package com.example.mayur.employeedatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEmployee extends Fragment {

    public EditText edtName, edtEmail, edtContact,edtSkill;
    public Button btnSave;
    public DatabaseReference mFirebaseDatabase;
    public FirebaseDatabase mFirebaseInstance;

   private DatabaseReference myref;

    public String employeeId;

    public AddEmployee() {
        // Required empty public constructor


        //android:digits="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);

        edtName = view.findViewById(R.id.edtname);
        edtEmail =view.findViewById(R.id.edtemail);
        edtContact = view.findViewById(R.id.edtcontact);
        edtSkill=view.findViewById(R.id.edtskill);
        btnSave = view.findViewById(R.id.btnsave);

        mFirebaseInstance = FirebaseDatabase.getInstance();

       // DatabaseReference myRef = mFirebaseDatabase.getRef("employeedatabase");

        // get reference to 'employeedatabase' node

        mFirebaseDatabase = mFirebaseInstance.getReference("employeedatabase");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("employeedatabase").setValue("Employee Management System Database");
        // app_title change listener
        mFirebaseInstance.getReference("employeedatabase").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String appTitle = dataSnapshot.getValue(String.class);

                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                Log.d("Token", "Refreshed token: " + refreshedToken);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed", "Failed to read app title value.", databaseError.toException());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String contact = edtContact.getText().toString().trim();
                String skill = edtSkill.getText().toString().trim();

            /*    if (name.isEmpty()) {
                    edtName.setError("Please enter a name");
                    edtName.requestFocus();
                    return;
                }
                if (TextUtils.isDigitsOnly(name)) {
                    edtName.setError("Please enter a name in alphabets");
                    edtName.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    edtEmail.setError("Please enter a email address");
                    edtEmail.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    edtContact.setError("Please enter mobile number");
                    edtContact.requestFocus();
                    return;
                }

                if (skill.isEmpty()) {
                    edtSkill.setError("Please enter skill");
                    edtSkill.requestFocus();
                    return;
                }
*/

                // Check for already existed userId
                AddAssociate(name, email, contact, skill);



            }
        });

        return view;

    }

    public void AddAssociate(String name, String email, String contact, String skill) {


        Employee employee = new Employee(name,email,contact,skill);

        if (TextUtils.isEmpty(employeeId)) {
            employeeId = mFirebaseDatabase.push().getKey();
        }

        mFirebaseDatabase.child(employeeId).setValue(employee);
        Toast.makeText(getActivity(), "associate added successfully ", Toast.LENGTH_LONG).show();
        edtName.setText("");
        edtEmail.setText("");
        edtContact.setText("");
        edtSkill.setText("");

    }


}