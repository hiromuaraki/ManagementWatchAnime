package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.viewsampleactivity.MainActivity;
import com.example.viewsampleactivity.R;


import java.util.List;

import Dto.MultiDto;

public class CustomAdapter extends BaseAdapter {
    private List<MultiDto> mDtoLit = null;
    private Context context = null;
    private int resource = 0;
    private TextView multiRow;
    private ListView multiList;

    public CustomAdapter(Context context, int resource, List<MultiDto> list) {
        this.mDtoLit = list;
        this.context = context;
        this.resource = resource;

    }

    @Override
    /**
     * データの個数を返す
     */
    public int getCount() {
        return mDtoLit.size();
    }

    /**
     * 指定された順番にある項目を返す
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mDtoLit.get(position);
    }

    /**
     * 指定された順番にある項目の識別idを返すメソッド
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return mDtoLit.get(position).getNum1();
    }

    /**
     * リスト項目を表示するためのメソッド
     * 自作アダプターを作成するにあたって一番重要
     * 実際にユーザが呼ぶ箇所ではなく、リストを生成するために自動で呼ばれる
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainActivity activity = (MainActivity) context;

        // 再利用可能なビューが無かったら生成する
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(resource, null, true);
        }
        multiRow = (TextView) convertView.findViewById(R.id.multiRow);
        multiList = (ListView) convertView.findViewById(R.id.muList);

        if ((position % 2) != 0) {
            //奇数行
            convertView.setBackgroundColor(Color.MAGENTA);
        } else {
            //偶数行
            convertView.setBackgroundColor(Color.WHITE);
        }

        MultiDto dto = (MultiDto) getItem(position);
        //複数選択された行のみ背景色を変更
        if (dto.getisFlg()) {
            convertView.setBackgroundColor(Color.GREEN);
        }
        return convertView;
    }
}

