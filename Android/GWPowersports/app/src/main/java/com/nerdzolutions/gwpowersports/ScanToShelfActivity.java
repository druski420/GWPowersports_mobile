package com.nerdzolutions.gwpowersports;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ScanToShelfActivity extends AppCompatActivity {

    //private String location_id = "";
    //private ArrayAdapter<String> adapter;
    //private String[] locations;
    //private int index = 0;
    //ArrayList<String> stringList = new ArrayList<>();
    private String TAG = "MainActivity";
    private String serial;
    private String location;
    private Button submitButton;
    private EditText serialNumberEditText;
    private EditText locationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_to_shelf);
        submitButton = findViewById(R.id.button_submit);
        serialNumberEditText = findViewById(R.id.text_input_serial_number);
        locationEditText = findViewById(R.id.text_input_location);

        //makeQuery();

//        location_spinner.setOnItemSelectedListener(new SpinnerOnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                //Toast.makeText(parentView.getContext(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
//                location = parentView.getItemAtPosition(position).toString();
//            }
//
//            public void onNothingSelected(AdapterView parentView) {
//                // your code here
//            }
//
//        });

        findViewById(R.id.text_input_serial_number).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_TAB) {
                    submitButton.performClick();
                    //do code
                    return true;

                }
                return false;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serial = serialNumberEditText.getText().toString();

                try {
                    UpdateDB(location, serial);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                serialNumberEditText.getText().clear();
            }
        });

    }

    private void UpdateDB(String location, String serial) throws SQLException {

        try {
            Connection conn = connectionBD();
            if (conn != null) {
                String sqlQuery = "UPDATE InventoryModels SET Location = '" + location + "' WHERE SerialNumber = " + serial;
                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                Toast.makeText(getApplicationContext(), "Serial " + serial + " has been moved to " + location + ".", Toast.LENGTH_LONG).show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private AdapterView.OnItemSelectedListener OnCatSpinnerCL = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            //((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
            ((TextView) parent.getChildAt(0)).setTextSize(24);
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

//    public void makeQuery() {
//        try {
//            Connection conn = connectionBD();
//            if (conn != null) {
//                Statement statement = conn.createStatement();
//                String sqlQuery = "SELECT * FROM LocationsModels";
//                ResultSet resultSet = statement.executeQuery(sqlQuery);
//                while (resultSet.next()) {
//                    location = resultSet.getString(2);
//                    stringList.add(location);
//                    Log.i(TAG, "Location_id: " + location_id + " , Location: " + location);
//                }
//                //Toast.makeText(getApplicationContext(), "Query executed successfully", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Connection to server failed!", Toast.LENGTH_LONG).show();
//            }
//            locations = new String[stringList.size()];
//            for (int i = 0; i < stringList.size(); i++)
//                locations[i] = stringList.get(i);
//            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
//            location_spinner.setAdapter(adapter);
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//            Log.e(TAG, e.getMessage());
//        }
//    }

    private Connection connectionBD() {
        Connection connection = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String conn_string = getString(R.string.connection_string);
            connection = DriverManager.getConnection(conn_string);
            //Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return connection;
    }

    public class SpinnerOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }
}