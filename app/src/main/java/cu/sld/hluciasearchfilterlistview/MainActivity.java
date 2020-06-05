package cu.sld.hluciasearchfilterlistview;

import java.util.ArrayList;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import cu.sld.hluciasearchfilterlistview.R;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private ListView searchListView;

    private ArrayList<Value> arrayList = new ArrayList<Value>();
    private MyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = (EditText) findViewById(R.id.searchEditText);
        searchListView = (ListView) findViewById(R.id.searchListView);

        arrayList.add(new Value("a", 1000));
        arrayList.add(new Value("b", 1200));
        arrayList.add(new Value("c", 2600));
        arrayList.add(new Value("d", 4600));
        arrayList.add(new Value("e", 1730));
        arrayList.add(new Value("f", 7600));
        arrayList.add(new Value("g", 4270));
        arrayList.add(new Value("h", 9200));
        arrayList.add(new Value("i", 1120));
        arrayList.add(new Value("j", 1560));
        arrayList.add(new Value("k", 6100));
        arrayList.add(new Value("l", 2300));
        arrayList.add(new Value("m", 7600));
        arrayList.add(new Value("n", 3200));
        arrayList.add(new Value("o", 9800));
        arrayList.add(new Value("p", 1500));

        adapter = new MyAdapter(MainActivity.this, arrayList);
        searchListView.setAdapter(adapter);

        //EditText에 텍스트 변경 리스너 추가
        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //현재 문자가있는 어댑터를 필터에 콜백
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}