package com.example.lab3;
//该界面用来做商品清单和购物车
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    public static List<Map<String, Object>> cartData = new ArrayList<>();
    public static SimpleAdapter cartAdaptor;
    static Boolean firstLoad = true;
    private int current_list = 0;   //0:商品; 1: 购物车
    public static RecyclerView mRecyclerView; //总的
    public static RecyclerView.Adapter mAdapter;  //管理数据，管理ViewHolders
    public static RecyclerView.LayoutManager mLayoutManager; //管理整体布局（采用Linear）
    public static OnItemClickListener mItemClickListener; //管理按
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.all_list);

        //去掉顶部ActionBar
        getSupportActionBar().hide();


        if(firstLoad){
            firstLoad = false;
            //初始化购物车
            InitCart();
            //初始化商品列表
            InitList();
        }
        ListView cart_list = (ListView)findViewById(R.id.cart);
        cart_list.setAdapter(cartAdaptor);
        cart_list.setOnItemLongClickListener(new CartLongClick(this));
        cart_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) return;
                Intent intent = new Intent(getBaseContext(), ItemDetial.class);
                intent.putExtra("selectedItem",Item.cart.get(i-1));
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ((AllListAdapter)mAdapter).setOnItemClickListner(mItemClickListener);
        current_list = 1;
        ChangeList(findViewById(R.id.floatingActionButton));
    }


    public void ChangeList(View v){
        if(current_list == 0){
            ((ListView)findViewById(R.id.cart)).setVisibility(View.VISIBLE);
            ((RecyclerView)findViewById(R.id.all_list)).setVisibility(View.INVISIBLE);
            ((FloatingActionButton)v).setImageResource(R.drawable.mainpage);
            current_list = 1;
        }
        else{
            ((ListView)findViewById(R.id.cart)).setVisibility(View.INVISIBLE);
            ((RecyclerView)findViewById(R.id.all_list)).setVisibility(View.VISIBLE);
            ((FloatingActionButton)v).setImageResource(R.drawable.shoplist);
            current_list = 0;
        }
    }

    private void InitList() {
        RecyclerView all_list = findViewById(R.id.all_list);
        all_list.setVisibility(View.VISIBLE);   //默认显示

        if(Item.all.isEmpty()){
            for(int i = 0; i < Item.items.length; ++i){
                Item.all.add(i);
            }
        }

        mRecyclerView = (RecyclerView)findViewById(R.id.all_list);
        mRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        mAdapter = new AllListAdapter(Item.all);

        mItemClickListener = new AllListClickManager();


    }

    public class AllListClickManager implements OnItemClickListener{
        @Override
        public void onClick(int pos) {
            Intent intent = new Intent(getBaseContext(), ItemDetial.class);
            intent.putExtra("selectedItem",pos);
            startActivity(intent);
        }
        @Override
        public void onLongClick(int pos) {
            Item.all.remove(pos);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void InitCart() {
        //购物车
        ListView cart_list = (ListView)findViewById(R.id.cart);
        cart_list.setVisibility(View.INVISIBLE); //默认隐藏
        /*
            //购物车默认（测试用）
            this.cart.add(1);
        */

        //数据存储器
        cartAdaptor = new SimpleAdapter(this, cartData, R.layout.cart_item,
                new String[]{"caption","name","price"},new int[] {R.id.cart_item_caption,R.id.cart_item_name,R.id.cart_item_price});

        //先加入标题
        Map<String, Object> temp1 = new LinkedHashMap<>();
        temp1.put("caption", "*");
        temp1.put("name","购物车");
        temp1.put("price","价格");
        cartData.add(temp1);
    }

    public static void AddItemToCart(int id){
        Item.cart.add(id);
        Map<String, Object> temp = new LinkedHashMap<>();
        temp.put("caption", Item.items[id].name.charAt(0));
        temp.put("name",Item.items[id].name);
        temp.put("price","￥"+Item.items[id].price);
        cartData.add(temp);
        cartAdaptor.notifyDataSetChanged();
    }

    public class CartLongClick implements AdapterView.OnItemLongClickListener{


        Context context;
        CartLongClick(Context c){context = c;}
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            //弹出对话框
            if(i != 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("从商品中移除"+Item.items[i-1].name+"?");
                builder.setPositiveButton("确认", new ConfirmDelete(i));
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            return true;
        }
    }

    public class ConfirmDelete implements DialogInterface.OnClickListener {
        int itemIndex;
        ConfirmDelete(int i){
            itemIndex = i;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            cartData.remove(itemIndex);
            Item.cart.remove(itemIndex-1);
            System.out.println(Item.cart.toString());
            cartAdaptor.notifyDataSetChanged();
        }
    }
}
