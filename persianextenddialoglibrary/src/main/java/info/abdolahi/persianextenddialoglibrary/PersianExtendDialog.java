package info.abdolahi.persianextenddialoglibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import info.abdolahi.persianextenddialoglibrary.util.PersianCalendar;
import info.abdolahi.persianextenddialoglibrary.util.PersianHelper;


/**
 * Created by aliabdolahi on 2/20/17.
 */

public class PersianExtendDialog {

    private final Context context;
    private String okButtonString = "Ok";
    private String dismissButtonString = "Cancel";
    private Date initialDate;
    private int titleDateResource;
    private OnExtendedDatePicked listener;
    private int extendDay = 1;
    private AlertDialog builtDialog;

    private PersianExtendDialog(Context context) {
        this.context = context;
    }

    private void setOkButtonString(String okButtonString) {
        this.okButtonString = okButtonString;
    }

    private void setDismissButtonString(String dismissButtonString) {
        this.dismissButtonString = dismissButtonString;
    }

    private void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public void setListener(OnExtendedDatePicked listener) {
        this.listener = listener;
    }

    public void setTitleDateResource(int titleDateResource) {
        this.titleDateResource = titleDateResource;
    }

    @SuppressLint("InflateParams")
    public void create() {

        // Init persian_extend_dialog builder
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        // Setup layout
        View view = LayoutInflater.from(context).inflate(R.layout.persian_extend_dialog, null, false);
        final NumberPicker picker = (NumberPicker) view.findViewById(R.id.npPicker);
        final TextView title = (TextView) view.findViewById(R.id.tvTitle);

        // Get string data
        String[] data = context.getResources().getStringArray(R.array.persianExtendDialogLibrary_dates);

        // Setup number picker
        picker.setWrapSelectorWheel(false);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setMinValue(0);
        picker.setMaxValue(data.length - 1);
        picker.setDisplayedValues(data);

        // update title string
        updateTitleString(getDateOfSelectedPosition(picker.getValue()), title, picker);

        // listen to picker change
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                updateTitleString(getDateOfSelectedPosition(picker.getValue()), title, picker);
            }
        });

        // Listen to persian_extend_dialog change
        dialogBuilder.setPositiveButton(okButtonString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.onDate(getDateOfSelectedPosition(picker.getValue()), getExtendDayByArray(picker.getValue()));
                }
                dialogInterface.dismiss();
            }
        });

        dialogBuilder.setNegativeButton(dismissButtonString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.onDismissed();
                }
                dialogInterface.dismiss();
            }
        });

        // set view into persian_extend_dialog
        dialogBuilder.setView(view);

        builtDialog = dialogBuilder.create();
    }

    public AlertDialog getBuiltDialog() {
        return builtDialog;
    }

    public void show() {
        if (builtDialog != null)
            builtDialog.show();
    }

    private Date getDateOfSelectedPosition(int i) {
        extendDay = getExtendDayByArray(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initialDate);
        calendar.add(Calendar.DATE, extendDay);
        return calendar.getTime();
    }

    private int getExtendDayByArray(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 14;
            case 6:
                return 30;
            case 7:
                return 60;
            case 8:
                return 90;
            case 9:
                return -1;
            default:
                return 1;
        }
    }

    private void updateTitleString(Date dateOfSelectedPosition, TextView title, NumberPicker picker) {


        PersianCalendar calendar = new PersianCalendar();
        calendar.setTime(dateOfSelectedPosition);
        String dateString = PersianHelper.toPersianNumber(
                calendar.getPersianDay() + " " + calendar.getPersianMonthName() + " " + calendar.getPersianYear()
        );
        if (picker.getValue() == 9) {
            dateString = context.getString(R.string.persianExtendDialogLibrary_always);
        }

        title.setText(context.getString(titleDateResource, dateString));
    }

    public static class Builder implements PersianExtendDialogBuilder {

        private PersianExtendDialog dialog;

        public Builder(@NonNull Context context, @NonNull Date initialDate, @StringRes int titleDateResource) {
            this.dialog = new PersianExtendDialog(context);
            this.dialog.setInitialDate(initialDate);
            this.dialog.setTitleDateResource(titleDateResource);
        }

        @Override
        public Builder withOkButton(String okButton) {
            dialog.setOkButtonString(okButton);
            return this;
        }

        @Override
        public Builder withCancelButton(String cancelButton) {
            dialog.setDismissButtonString(cancelButton);
            return this;
        }

        @Override
        public Builder withOnDatePickedListener(OnExtendedDatePicked listener) {
            dialog.setListener(listener);
            return this;
        }

        @Override
        public PersianExtendDialog build() {
            dialog.create();
            return dialog;
        }

    }

}
