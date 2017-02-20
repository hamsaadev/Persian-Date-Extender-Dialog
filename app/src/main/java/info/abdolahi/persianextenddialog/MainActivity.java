package info.abdolahi.persianextenddialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Date;

import info.abdolahi.persianextenddialoglibrary.OnExtendedDatePicked;
import info.abdolahi.persianextenddialoglibrary.PersianExtendDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPicker(View view) {
        PersianExtendDialog dialog = new PersianExtendDialog.Builder(this, new Date(), R.string.title)
                .withOkButton("تایید")
                .withCancelButton("انصراف")
                .withOnDatePickedListener(new OnExtendedDatePicked() {
                    @Override
                    public void onDate(Date date, int num) {
                        Log.d(TAG, "onDate: " + date.toString() + " num: " + num);
                    }

                    @Override
                    public void onDismissed() {
                        Log.d(TAG, "onDismissed");
                    }
                }).build();
        dialog.show();
    }
}
