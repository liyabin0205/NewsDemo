package com.app.newsdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.newsdemo.bean.News;
import com.app.newsdemo.utils.Myadapter;
import com.app.newsdemo.utils.NewsTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsTask.NewsCallBack {

    private static String URL_NEWS = "http://v.juhe.cn/toutiao/index?type=top&key=6928eda123d4aef596b726b4addadf48";

    private ListView lv;
    private Myadapter adapter;
    private ArrayList<News> list = new ArrayList<>();
    private ArrayList<News> list1 = new ArrayList<>();
    private String citySubing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "新闻已更新", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        initView();
        initData(URL_NEWS);
        initAdapter();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = list.get(position).getUrl();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("news_url",url);
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        adapter = new Myadapter(list, MainActivity.this);
        lv.setAdapter(adapter);
    }

    private void initData(String Url_News) {
        new NewsTask(this, MainActivity.this).execute(Url_News);
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.listview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        citySubing = URL_NEWS.substring(URL_NEWS.indexOf("type=")+5,URL_NEWS.indexOf("&key"));
        switch (item.getItemId()) {
            case R.id.action_top:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"top");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.top, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_yule:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"yule");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.yule, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_tiyu:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"tiyu");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.tiyu, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_caijing:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"caijing");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.caijing, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_guoji:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"guoji");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.guoji, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_guonei:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"guonei");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.guonei, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_junshi:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"junshi");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.junshi, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_keji:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"keji");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.keji, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_shehui:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"shehui");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.shehui, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_shishang:
                URL_NEWS = URL_NEWS.replaceAll(citySubing,"shishang");
                initData(URL_NEWS);
                Toast.makeText(MainActivity.this, R.string.shishang, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getNews(ArrayList<News> result) {
        list.clear();
        list.addAll(result);
        adapter.notifyDataSetChanged();
    }
}
