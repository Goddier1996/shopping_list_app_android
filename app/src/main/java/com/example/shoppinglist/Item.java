package com.example.shoppinglist;

import android.widget.TextView;

public class Item {
    private boolean checked;
    private int amount;
    private String name;

    //ctor
    public Item(boolean checked, int amount,String name)  {
        this.checked = checked;
        this.amount = amount;
        this.name = name;
    }


    // set+get
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return getName() + "-" + getAmount();
    }
}
