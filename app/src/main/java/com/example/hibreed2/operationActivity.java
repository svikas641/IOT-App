package com.example.hibreed2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class operationActivity extends AppCompatActivity {

	private ToggleButton toggleButton1;
	private ToggleButton toggleButton2;
	private ToggleButton toggleButton3;
	private ToggleButton toggleButton4;
	private Button logoutButton;
	private FirebaseAuth mAuth;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.operationactivity);
		overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
		checkstatus();
		addListenerOnButtonClick();

	}
	public void addListenerOnButtonClick(){
		toggleButton1 =  findViewById(R.id.toggleButton);
		toggleButton2 =  findViewById(R.id.toggleButton2);
		toggleButton3 =  findViewById(R.id.toggleButton3);
		toggleButton4 =  findViewById(R.id.toggleButton4);
		logoutButton = findViewById(R.id.buttonLogout);

		toggleButton1.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(toggleButton1.isChecked()){
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R1");
					myRef.setValue(0);
				}
				else{
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R1");
					myRef.setValue(1);
				}

			}
		});

		toggleButton2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(toggleButton2.isChecked()){
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R2");
					myRef.setValue(0);
				}
				else{
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R2");
					myRef.setValue(1);
				}
			}
		});

		toggleButton3.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				if(toggleButton3.isChecked()){
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R3");
					myRef.setValue(0);
				}
				else{
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R3");
					myRef.setValue(1);
				}

			}
		});
		toggleButton4.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(toggleButton4.isChecked()){
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R4");
					myRef.setValue(0);
				}
				else{
					FirebaseDatabase database = FirebaseDatabase.getInstance();
					DatabaseReference myRef = database.getReference("R4");
					myRef.setValue(1);
				}

			}
		});
		logoutButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				signOut();
			}
		});
	}
	public void signOut() {
		mAuth.getInstance().signOut();
		Intent i = new Intent(getApplicationContext(),login.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(i);

	}

	public void checkstatus(){
		FirebaseDatabase database = FirebaseDatabase.getInstance();

		DatabaseReference myRef1 = database.getReference("R1");
		myRef1.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				int i=Integer.parseInt(dataSnapshot.getValue().toString());
				if(i==1){
					toggleButton1.setChecked(false);
				}
				else
					toggleButton1.setChecked(true);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.d("Info","The read failed:" + databaseError.getCode());

			}
		});

		DatabaseReference myRef2 = database.getReference("R2");
		myRef2.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				int i=Integer.parseInt(dataSnapshot.getValue().toString());
				if(i==1){
					toggleButton2.setChecked(false);
				}
				else
					toggleButton2.setChecked(true);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.d("Info","The read failed:" + databaseError.getCode());

			}
		});

		DatabaseReference myRef3 = database.getReference("R3");
		myRef3.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				int i=Integer.parseInt(dataSnapshot.getValue().toString());
				if(i==1){
					toggleButton3.setChecked(false);
				}
				else
					toggleButton3.setChecked(true);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.d("Info","The read failed:" + databaseError.getCode());

			}
		});

		DatabaseReference myRef4 = database.getReference("R4");
		myRef4.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				int i=Integer.parseInt(dataSnapshot.getValue().toString());
				if(i==1){
					toggleButton4.setChecked(false);
				}
				else
					toggleButton4.setChecked(true);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				Log.d("Info","The read failed:" + databaseError.getCode());

			}
		});

	}
	boolean doubleBackToExitPressedOnce = false;

	@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			return;
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce=false;
			}
		}, 2000);
	}

}