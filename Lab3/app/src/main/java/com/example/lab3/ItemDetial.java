package com.example.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ItemDetial extends AppCompatActivity {

    int selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detial);
        this.getSupportActionBar().hide();

        Intent intent = this.getIntent();
        selectedItem = intent.getIntExtra("selectedItem",0);

        ((ImageView)findViewById(R.id.item_img)).setImageDrawable(getResources().getDrawable(Item.items[selectedItem].picture_id));
        ((TextView)findViewById(R.id.item_name)).setText(Item.items[selectedItem].name);
        ((TextView)findViewById(R.id.item_price)).setText("￥ " + Item.items[selectedItem].price);
        ((TextView)findViewById(R.id.item_info)).setText(Item.items[selectedItem].infoType + " " + Item.items[selectedItem].info);
        SetStar();

        ListView selection_list = (ListView)findViewById(R.id.item_detail_selection);
        List<Map<String, Object>> data = new ArrayList<>();
        SimpleAdapter mAdapter = new SimpleAdapter(this, data, R.layout.detail_selection,
                new String[]{"content"},new int[] {R.id.detail_selection_item});

        String[] contents = new String[]{"一键下单","分享商品","不感兴趣","查看更多商品促销信息"};
        //再加入内容
        for(int i = 0; i < contents.length; ++i){
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("content",contents[i]);
            data.add(temp);
        }
        selection_list.setAdapter(mAdapter);
    }

    public void PutIntCart(View v){
        MainActivity.AddItemToCart(selectedItem);
        Toast.makeText(getApplicationContext(),"商品已添加到购物车",Toast.LENGTH_SHORT).show();
    }

    public void LikeItem(View v){
        Item.items[selectedItem].star = !Item.items[selectedItem].star;
        SetStar();
    }

    public void SetStar(){
        if(!Item.items[selectedItem].star)
            ((ImageView)findViewById(R.id.item_like)).setImageDrawable(getResources().getDrawable(R.drawable.empty_star));
        else ((ImageView)findViewById(R.id.item_like)).setImageDrawable(getResources().getDrawable(R.drawable.full_star));
    }

    public void Back(View v){
        finish();
        onBackPressed();
    }
}
