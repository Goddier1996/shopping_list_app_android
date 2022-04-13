package com.example.shoppinglist;

import android.graphics.Paint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    Item[] items = new Item[10];
    ArrayAdapter<Item> adapter;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        items[0] = new Item(false, 1, "milk");
        items[1] = new Item(false, 1, "bread");
        items[2] = new Item(false, 1, "soap");
        items[3] = new Item(false, 1, "fish");
        items[4] = new Item(false, 1, "eggs");
        items[5] = new Item(false, 1, "cheese");
        items[6] = new Item(false, 1, "chocolate");
        items[7] = new Item(false, 1, "meat");
        items[8] = new Item(false, 1, "water");
        items[9] = new Item(false, 1, "delicacy");

        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                tv = (TextView) view;
                if (items[position].isChecked() == false) {
                    items[position].setChecked(true);
                    tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    items[position].setChecked(false);
                    tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }
                adapter.notifyDataSetChanged();
            }

        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            //clear all
            case R.id.clear_all:
                for (int i = 0; i < items.length; i++){
                    items[i].setAmount(1);
                    items[i].setChecked(false);
                    TextView tv1 =(TextView) ((ViewGroup) lv).getChildAt(i);
                    tv1.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }
                adapter.notifyDataSetChanged();
                break;

            //send
            case R.id.send:
                for (int i = 0; i < items.length; i++) {
                    if (items[i].isChecked() == false) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("+972542546828", null, items[i].toString(), null, null);
                    }
                }
                break;
        }
        return true;
    }






    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("amount");

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = info.position;
        switch (id) {

            case R.id.item11:
                items[menuItemIndex].setAmount(1);
                adapter.notifyDataSetChanged();
                break;

            case R.id.item22:
                items[menuItemIndex].setAmount(2);
                adapter.notifyDataSetChanged();
                break;

            case R.id.item33:
                items[menuItemIndex].setAmount(3);
                adapter.notifyDataSetChanged();
                break;

            case R.id.item44:
                items[menuItemIndex].setAmount(4);
                adapter.notifyDataSetChanged();
                break;

            case R.id.item55:
                items[menuItemIndex].setAmount(5);
                adapter.notifyDataSetChanged();
                break;

        }
        return true;
    }
}