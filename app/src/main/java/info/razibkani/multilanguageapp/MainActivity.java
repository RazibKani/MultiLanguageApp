package info.razibkani.multilanguageapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnBahasa;
    private Button mBtnEnglish;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnBahasa = (Button) findViewById(R.id.btn_bahasa);
        mBtnEnglish = (Button) findViewById(R.id.btn_english);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerAdapter(this);
        mAdapter.setItemData(getListItem());
        mRecyclerView.setAdapter(mAdapter);

        mBtnBahasa.setOnClickListener(this);
        mBtnEnglish.setOnClickListener(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bahasa:
                LocaleHelper.setLocale(this, "in");
                recreate();
                break;

            case R.id.btn_english:
                LocaleHelper.setLocale(this, "en");
                recreate();
                break;

            default:
                break;
        }
    }

    private ArrayList<Item> getListItem() {

        ArrayList<Item> items = new ArrayList<>();

        int index = 0;
        while (index < 3) {
            Item item = new Item();

            item.setTitle(getResources().getStringArray(R.array.array_title)[index]);
            item.setDescription(getResources().getStringArray(R.array.array_description)[index]);

            items.add(item);

            index++;
        }

        return items;
    }

    /**
     * Recyclerview Adapter
     */
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private LayoutInflater mInflater;
        private List<Item> mListItems;

        public RecyclerAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mListItems = new ArrayList<>();
        }

        public void setItemData(List<Item> items) {
            mListItems.clear();
            mListItems = items;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (getItemCount() > 0) {
                Item item = mListItems.get(position);

                holder.itemTitle.setText(item.getTitle());
                holder.itemDescription.setText(item.getDescription());
            }
        }

        @Override
        public int getItemCount() {
            return mListItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView itemTitle;
            private TextView itemDescription;

            ViewHolder(View itemView) {
                super(itemView);
                itemTitle = (TextView) itemView.findViewById(R.id.item_title);
                itemDescription = (TextView) itemView.findViewById(R.id.item_description);
            }
        }
    }
}