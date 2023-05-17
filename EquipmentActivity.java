package com.mobitech.dealer.poltava.equipment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.mobitech.dealer.poltava.R;
import com.mobitech.dealer.poltava.db.DatabaseHelper;
import com.mobitech.dealer.poltava.db.QueryHelper;

public class EquipmentActivity extends AppCompatActivity {

    private String equipment_guid;
    private String outlet_guid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        Intent intent = getIntent();

        // получение _id
        equipment_guid = intent.getStringExtra("equipment_guid");
        outlet_guid = intent.getStringExtra("outlet_guid");

        // установка доступа
        boolean flagEnabled = false;

        EditText et1 = findViewById(R.id.editText1);
        et1.setFocusable(flagEnabled);
        et1.setFocusableInTouchMode(flagEnabled);

        EditText et2 = findViewById(R.id.editText2);
        et2.setFocusable(flagEnabled);
        et2.setFocusableInTouchMode(flagEnabled);

        EditText et3 = findViewById(R.id.editText3);
        et3.setFocusable(flagEnabled);
        et3.setFocusableInTouchMode(flagEnabled);

        //connection to database
        SQLiteDatabase db = DatabaseHelper.getDb(this);

        // получение данных оборудования

        Cursor c = QueryHelper.getEquipment(db, equipment_guid);

        if (c.moveToFirst()) {

            et1.setText(c.getString(c.getColumnIndex("EQUIPMENT_NAME")));

            et2.setText(c.getString(c.getColumnIndex("MODEL_NAME")));

            et3.setText(c.getString(c.getColumnIndex("ID")));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.proposalUninstall) {

            Intent intent = new Intent(this, UninstallActivity.class);

            intent.putExtra("equipment_guid", equipment_guid);

            intent.putExtra("outlet_guid", outlet_guid);

            startActivity(intent);
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_equipment, menu);
        return true;
    }
}
