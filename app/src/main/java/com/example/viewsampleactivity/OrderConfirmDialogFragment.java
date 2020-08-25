package com.example.viewsampleactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class OrderConfirmDialogFragment extends DialogFragment {
    //ダイアログビルダーを生成
    AlertDialog.Builder builder;

    public OrderConfirmDialogFragment() {
        builder = new AlertDialog.Builder(getActivity());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_msg);

        //Positiveボタンの設定
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListener());
        builder.setNeutralButton(R.string.dialog_btn_nu, new DialogButtonClickListener());

        AlertDialog dialog = builder.create();

        return dialog;
    }

    class DialogButtonClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            //問い合わせ用のメッセージを格納
            String msg = "";
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE://注文
                    msg = getString(R.string.toast_ok);
                    break;
                case DialogInterface.BUTTON_NEGATIVE://キャンセル
                    msg = getString(R.string.toast_ng);
                    break;
                case DialogInterface.BUTTON_NEUTRAL://問い合わせ
                    msg = getString(R.string.toast_nu);
                    break;
            }

            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        }
    }
}
