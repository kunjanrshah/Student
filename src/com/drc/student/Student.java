package com.drc.student;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Student extends Activity implements OnClickListener {
	EditText txtrollno, txtname, txtcourse, txtusername, txtpassword, txtupdate, txtdelete, txtselect, txtsem;
	EditText txtsub1, txtsub2, txtsub3, txtsub4, txttotal;
	Button btnlogin, btncancel, btnloggermany, btnoutlogin, btnoutcancel;
	ImageButton btnnext, btngermany;
	DBAdapter db = new DBAdapter(this);
	Menu menu;
	AlertDialog.Builder alert;
	AlertDialog alertdialog;
	TextView lblinfo, lblroll, lblname, lblcourse, lblsem, lblsub1, lblsub2, lblsub3, lblsub4, lbltotal, lblusername, lblpassword;
	String Log_TAG = "Student";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		btnnext = (ImageButton) findViewById(R.id.btnnext);
		btnnext.setOnClickListener(this);
		btngermany = (ImageButton) findViewById(R.id.btngermany);
		btngermany.setOnClickListener(this);
	}

	public void declaregermany() {
		String username, password, login, cancel, info;
		username = "Benutzername";
		password = "Kennwort";
		info = "Schüler Informationen";
		cancel = "stornieren";
		login = "Login";
		lblusername = (TextView) findViewById(R.id.lblusername);
		lblusername.setText(username);
		lblpassword = (TextView) findViewById(R.id.lblpassword);
		lblpassword.setText(password);
		lblinfo = (TextView) findViewById(R.id.lblinfo);
		lblinfo.setText(info);
		btncancel = (Button) findViewById(R.id.btncancel);
		btncancel.setText(cancel);
		txtusername = (EditText) findViewById(R.id.txtusername);
		txtpassword = (EditText) findViewById(R.id.txtpassword);
		btncancel.setOnClickListener(this);
		btnloggermany = (Button) findViewById(R.id.btnlogin);
		btnloggermany.setText(login);
		btnloggermany.setOnClickListener(this);
	}

	public void DisplayTitle(Cursor c) {
		txtrollno.setText(c.getString(1));
		txtname.setText(c.getString(2));
		txtcourse.setText(c.getString(3));
		txtsem.setText(c.getString(4));
		txtsub1.setText(c.getString(5));
		txtsub2.setText(c.getString(6));
		txtsub3.setText(c.getString(7));
		txtsub4.setText(c.getString(8));
		txttotal.setText(c.getString(9));
		/*
		 * Toast.makeText(this, "id: " + c.getString(0) + "\n" + "Roll No.: " +
		 * c.getString(1) + "\n" + "Name: " + c.getString(2) + "\n" +
		 * "Course:  " + c.getString(3), Toast.LENGTH_LONG).show();
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add:
			String roll,
			name,
			course,
			sem;
			String sub1,
			sub2,
			sub3,
			sub4,
			total;
			roll = txtrollno.getText().toString();
			name = txtname.getText().toString();
			course = txtcourse.getText().toString();
			sem = txtsem.getText().toString();
			sub1 = txtsub1.getText().toString();
			sub2 = txtsub2.getText().toString();
			sub3 = txtsub3.getText().toString();
			sub4 = txtsub4.getText().toString();
			int i,
			j,
			k,
			l,
			m;
			i = Integer.parseInt(txtsub1.getText().toString());
			j = Integer.parseInt(txtsub2.getText().toString());
			k = Integer.parseInt(txtsub3.getText().toString());
			l = Integer.parseInt(txtsub4.getText().toString());
			m = i + j + k + l;
			txttotal.setText(String.valueOf(m));
			total = txttotal.getText().toString();
			if (roll.equals("") && name.equals("") && course.equals("") && sem.equals("") && txtsub1.getText().toString().equals("") && txtsub2.getText().toString().equals("") && txtsub3.getText().toString().equals("") && txtsub4.getText().toString().equals("") && txttotal.getText().toString().equals("")) {
				Toast.makeText(this, "Please Fill Above Details", Toast.LENGTH_LONG).show();
				break;
			} else {
				db.open();
				db.insertTitle(roll, name, course, sem, sub1, sub2, sub3, sub4, total);
				db.close();
				Toast.makeText(this, "Data Save Successfully", Toast.LENGTH_LONG).show();
				break;
			}
		case R.id.update:
			roll = txtrollno.getText().toString();
			Log.i(Log_TAG, ".. roll ...  " + roll);
			name = txtname.getText().toString();
			Log.i(Log_TAG, ".. name ...  " + name);
			course = txtcourse.getText().toString();
			Log.i(Log_TAG, ".. course ...  " + course);
			sem = txtsem.getText().toString();
			Log.i(Log_TAG, ".. sem ...  " + sem);
			Log.i(Log_TAG, "this is working");
			sub1 = txtsub1.getText().toString();
			Log.i(Log_TAG, ".. Sub1 ...  " + sub1);
			sub2 = txtsub2.getText().toString();
			Log.i(Log_TAG, ".. Sub2 ...  " + sub2);
			sub3 = txtsub3.getText().toString();
			Log.i(Log_TAG, ".. Sub3 ...  " + sub3);
			sub4 = txtsub4.getText().toString();
			Log.i(Log_TAG, ".. Sub4 ...  " + sub4);
			total = txttotal.getText().toString();
			Log.i(Log_TAG, ".. total ...  " + total);
			if (roll.equals("") && name.equals("") && course.equals("") && sem.equals("") && txtsub1.getText().toString().equals("") && txtsub2.getText().toString().equals("") && txtsub3.getText().toString().equals("") && txtsub4.getText().toString().equals("") && txttotal.getText().toString().equals("")) {
				Toast.makeText(this, "Please Select Data", Toast.LENGTH_LONG).show();
				break;
			} else {
				long id;
				id = Integer.parseInt(txtrollno.getText().toString());
				db.open();
				if (db.updateTitle(id, roll, name, course, sem, sub1, sub2, sub3, sub4, total))
					Toast.makeText(getBaseContext(), "Update successful.", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getBaseContext(), "Update failed.", Toast.LENGTH_LONG).show();
				db.close();
				break;
			}
		case R.id.delete:
			alert = new AlertDialog.Builder(this);
			alert.setTitle("Delete Data");
			alert.setMessage("Enter Roll No.");
			txtdelete = new EditText(this);
			alert.setView(txtdelete);
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.i(Log_TAG, ".. This Delete is Working ...  ");
					if (txtdelete.getText().toString().equals("")) {
						Log.i(Log_TAG, ".. This Delete function is Working ...  ");
						Toast.makeText(getBaseContext(), "Please Enter Roll No.", Toast.LENGTH_LONG).show();
					} else {
						db.open();
						long id;
						id = Integer.parseInt(txtdelete.getText().toString());

						if (db.deleteTitle(id))
							Toast.makeText(getBaseContext(), "Delete successful.", Toast.LENGTH_LONG).show();
						else
							Toast.makeText(getBaseContext(), "The Data is not Found", Toast.LENGTH_LONG).show();
						db.close();
					}
				}
			});
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alert.setCancelable(true);
				}
			});
			alert.setIcon(R.drawable.delete);
			alert.show();
			break;
		case R.id.select:
			alert = new AlertDialog.Builder(this);
			alert.setTitle("Search Data");
			alert.setMessage("Enter Roll No.");
			txtselect = new EditText(this);
			alert.setView(txtselect);
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.i(Log_TAG, ".. This Select is Working ...  ");
					if (txtselect.getText().toString().equals("")) {
						Log.i(Log_TAG, ".. This Select function is Working ...  ");
						Toast.makeText(getBaseContext(), "Please Enter Roll No.", Toast.LENGTH_LONG).show();
					} else {
						db.open();
						long id;
						id = Integer.parseInt(txtselect.getText().toString());
						Cursor c = db.getTitle(id);
						if (c.moveToFirst())
							DisplayTitle(c);
						else
							Toast.makeText(getBaseContext(), "No Data found", Toast.LENGTH_LONG).show();
						db.close();
					}
				}
			});
			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alert.setCancelable(true);
				}
			});
			alert.setIcon(R.drawable.search);
			alert.show();
			break;
		case R.id.cancel:
			alertdialog = new AlertDialog.Builder(Student.this).create();
			alertdialog.setTitle("Quit........");
			alertdialog.setMessage("R u sure?");
			alertdialog.setCancelable(true);
			alertdialog.setCanceledOnTouchOutside(true);
			alertdialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					setContentView(R.layout.login);
					out();

				}
			});
			alertdialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					alertdialog.cancel();
					txtrollno.setText(null);
					txtname.setText(null);
					txtcourse.setText(null);
					txtsem.setText(null);
					txtsub1.setText(null);
					txtsub2.setText(null);
					txtsub3.setText(null);
					txtsub4.setText(null);
					txttotal.setText(null);
				}
			});
			alertdialog.setIcon(R.drawable.cancel);
			alertdialog.show();
			break;
		}
		return true;
	}

	public void out() {
		btnlogin = (Button) findViewById(R.id.btnlogin);
		btnlogin.setOnClickListener(this);
		btncancel = (Button) findViewById(R.id.btncancel);
		btncancel.setOnClickListener(this);
		txtusername = (EditText) findViewById(R.id.txtusername);
		txtpassword = (EditText) findViewById(R.id.txtpassword);
	}

	@Override
	public void onClick(View v) {
		if (v == btnlogin) {
			if (txtusername.getText().toString().equals("Dipak") && txtpassword.getText().toString().equals("jaimataji")) {
				Toast.makeText(this, "Thank You for using this Software", Toast.LENGTH_LONG).show();
				setContentView(R.layout.main);
				txtrollno = (EditText) findViewById(R.id.txtrollno);
				txtrollno.setSingleLine(true);
				txtname = (EditText) findViewById(R.id.txtname);
				txtname.setSingleLine(true);
				txtcourse = (EditText) findViewById(R.id.txtcourse);
				txtcourse.setSingleLine(true);
				txtsem = (EditText) findViewById(R.id.txtsem);
				txtsem.setSingleLine(true);
				txtsub1 = (EditText) findViewById(R.id.txtsub1);
				txtsub1.setSingleLine(true);
				txtsub2 = (EditText) findViewById(R.id.txtsub2);
				txtsub2.setSingleLine(true);
				txtsub3 = (EditText) findViewById(R.id.txtsub3);
				txtsub3.setSingleLine(true);
				txtsub4 = (EditText) findViewById(R.id.txtsub4);
				txtsub4.setSingleLine(true);
				txttotal = (EditText) findViewById(R.id.txttotal);
				txttotal.setSingleLine(true);
			} else {
				Toast.makeText(this, "Please Enter Valid User Name & Password", Toast.LENGTH_LONG).show();
			}
		} else if (v == btncancel) {
			setContentView(R.layout.splash);
			btnnext = (ImageButton) findViewById(R.id.btnnext);
			btnnext.setOnClickListener(this);
			btngermany = (ImageButton) findViewById(R.id.btngermany);
			btngermany.setOnClickListener(this);
		} else if (v == btnnext) {
			setContentView(R.layout.login);
			btnlogin = (Button) findViewById(R.id.btnlogin);
			btnlogin.setOnClickListener(this);
			btncancel = (Button) findViewById(R.id.btncancel);
			btncancel.setOnClickListener(this);
			txtusername = (EditText) findViewById(R.id.txtusername);
			txtpassword = (EditText) findViewById(R.id.txtpassword);
		} else if (v == btngermany) {
			setContentView(R.layout.login);
			declaregermany();
		} else if (v == btnloggermany) {
			if (txtusername.getText().toString().equals("Dipak") && txtpassword.getText().toString().equals("jaimataji")) {
				Toast.makeText(this, "Vielen Dank für die Nutzung dieser Software", Toast.LENGTH_LONG).show();
				setContentView(R.layout.main);
				declaregermanyinfo();
				txtrollno = (EditText) findViewById(R.id.txtrollno);
				txtrollno.setSingleLine(true);
				txtname = (EditText) findViewById(R.id.txtname);
				txtname.setSingleLine(true);
				txtcourse = (EditText) findViewById(R.id.txtcourse);
				txtcourse.setSingleLine(true);
				txtsem = (EditText) findViewById(R.id.txtsem);
				txtsem.setSingleLine(true);
				txtsub1 = (EditText) findViewById(R.id.txtsub1);
				txtsub1.setSingleLine(true);
				txtsub2 = (EditText) findViewById(R.id.txtsub2);
				txtsub2.setSingleLine(true);
				txtsub3 = (EditText) findViewById(R.id.txtsub3);
				txtsub3.setSingleLine(true);
				txtsub4 = (EditText) findViewById(R.id.txtsub4);
				txtsub4.setSingleLine(true);
				txttotal = (EditText) findViewById(R.id.txttotal);
				txttotal.setSingleLine(true);
			} else {
				Toast.makeText(this, "Please Enter Valid User Name & Password", Toast.LENGTH_LONG).show();
			}
		}
	}

	public void declaregermanyinfo() {
		String roll, name, course, sem, sub1, sub2, sub3, sub4, total, info;
		info = "Schüler Informationen";
		roll = "Roll Nr.";
		name = "Name";
		course = "Kurs";
		sem = "Semester";
		sub1 = "Fachgebiet 1";
		sub2 = "Fachgebiet 2";
		sub3 = "Fachgebiet 3";
		sub4 = "Fachgebiet 3";
		total = "Insgesamt";
		lblinfo = (TextView) findViewById(R.id.lblinfo);
		lblinfo.setText(info);
		lblroll = (TextView) findViewById(R.id.lblroll);
		lblroll.setText(roll);
		lblname = (TextView) findViewById(R.id.lblname);
		lblname.setText(name);
		lblcourse = (TextView) findViewById(R.id.lblcourse);
		lblcourse.setText(course);
		lblsem = (TextView) findViewById(R.id.lblsem);
		lblsem.setText(sem);
		lblsub1 = (TextView) findViewById(R.id.lblsub1);
		lblsub1.setText(sub1);
		lblsub2 = (TextView) findViewById(R.id.lblsub2);
		lblsub2.setText(sub2);
		lblsub3 = (TextView) findViewById(R.id.lblsub3);
		lblsub3.setText(sub3);
		lblsub4 = (TextView) findViewById(R.id.lblsub4);
		lblsub4.setText(sub4);
		lbltotal = (TextView) findViewById(R.id.lbltotal);
		lbltotal.setText(total);
	}
}