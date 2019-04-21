package com.example.pema;

import android.os.Bundle;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//Add Income Button
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fabaction1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert_income = new AlertDialog.Builder(MainActivity.this);
                View mView_income = getLayoutInflater().inflate(R.layout.addincomedialogbox,null);
                final Spinner mSpinner_income = (Spinner)mView_income.findViewById(R.id.spinner_category_income);
                ArrayAdapter<String> adapter_income = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.categories_incomes));
                adapter_income.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner_income.setAdapter(adapter_income);
                Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                final int year = calendar.get(Calendar.YEAR);
                final TextView textView_date = mView_income.findViewById(R.id.date_income_view);
                textView_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog_date = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                             String strDate = (monthOfYear+1) + "/" + dayOfMonth + "/"+year;
                             textView_date.setText(strDate);
                            }
                        },year,month,day);

                        dialog_date.show(getFragmentManager(),"DatePickerDialog");
                    }
                });
                final EditText txt_notes_income = (EditText) mView_income.findViewById(R.id.txt_notes_income);
                final EditText txt_total_income = (EditText) mView_income.findViewById(R.id.txt_total_income);
                Button btn_cancel_income = (Button)mView_income.findViewById(R.id.btn_addincome_cancel);
                Button btn_save_income = (Button)mView_income.findViewById(R.id.btn_addincome_save);
                alert_income.setView(mView_income);
                final AlertDialog alertDialog_income = alert_income.create();
                alertDialog_income.setCanceledOnTouchOutside(false);
                btn_cancel_income.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Income Diacarded", Toast.LENGTH_SHORT).show();
                        alertDialog_income.dismiss();
                    }
                });

                btn_save_income.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String value_total_income = txt_total_income.getText().toString().trim();
                        String income_category = mSpinner_income.getSelectedItem().toString();
                        String value_date_income = (String) textView_date.getText();
                        if(TextUtils.isEmpty(value_total_income))
                        {
                            Toast.makeText(MainActivity.this, "Total field cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                        else if (income_category.equalsIgnoreCase("Choose a Category"))
                        {
                            Toast.makeText(MainActivity.this, "Please select a Category", Toast.LENGTH_SHORT).show();
                        }
                        else if (value_date_income.equalsIgnoreCase("Select Date"))
                        {
                            Toast.makeText(MainActivity.this, "Please Select a Date", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Income Added", Toast.LENGTH_SHORT).show();
                            alertDialog_income.dismiss();
                        }
                    }
                });

                alertDialog_income.show();


            }
        });
//Add Expense Button
        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.fabaction2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
               View mView = getLayoutInflater().inflate(R.layout.addexpensedialogbox,null);
                final Spinner mSpinner = (Spinner)mView.findViewById(R.id.spinner_category);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.categories_expenses));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);
                Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                final int year = calendar.get(Calendar.YEAR);
                final TextView textView_date = mView.findViewById(R.id.date_expenses_view);
                textView_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog_date = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String strDate = (monthOfYear+1) + "/" + dayOfMonth + "/"+year;
                                textView_date.setText(strDate);
                            }
                        },year,month,day);

                        dialog_date.show(getFragmentManager(),"DatePickerDialog");
                    }
                });
               final EditText txt_notes = (EditText) mView.findViewById(R.id.txt_notes);
                final EditText txt_total_expenses = (EditText) mView.findViewById(R.id.txt_total);
                Button btn_cancel = (Button)mView.findViewById(R.id.btn_addexpense_cancel);
                Button btn_save = (Button)mView.findViewById(R.id.btn_addexpense_save);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Expense Diacarded", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String value_total_expenses = txt_total_expenses.getText().toString().trim();
                        String expense_category = mSpinner.getSelectedItem().toString();
                        String value_date_expenses = (String) textView_date.getText();



                        if(TextUtils.isEmpty(value_total_expenses))
                        {
                            Toast.makeText(MainActivity.this, "Total field cannot be blank", Toast.LENGTH_SHORT).show();

                        }
                        else if (expense_category.equalsIgnoreCase("Choose a Category"))
                        {
                            Toast.makeText(MainActivity.this, "Select Category Please", Toast.LENGTH_SHORT).show();
                        }
                        else if (value_date_expenses.equalsIgnoreCase("Select Date"))
                        {
                            Toast.makeText(MainActivity.this, "Please Select a Date", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Expense Added", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }
                });

                alertDialog.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,new expensesFragment());
        fragmentTransaction.commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Expenses");
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_expenses) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new expensesFragment());
            fragmentTransaction.commit();

            Objects.requireNonNull(getSupportActionBar()).setTitle("Expenses");
            // Handle the camera action
        } else if (id == R.id.nav_statistics) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new statisticsFragment());
            fragmentTransaction.commitNow();
            Objects.requireNonNull(getSupportActionBar()).setTitle("Statistics");

        } else if (id == R.id.nav_search) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new searchFragment());
            fragmentTransaction.commitNow();
            Objects.requireNonNull(getSupportActionBar()).setTitle("Search");

        } else if (id == R.id.nav_profile) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container,new profileFragment());
            fragmentTransaction.commitNow();
            Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
