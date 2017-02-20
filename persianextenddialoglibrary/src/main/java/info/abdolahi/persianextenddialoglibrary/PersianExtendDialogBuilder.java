package info.abdolahi.persianextenddialoglibrary;

/**
 * Created by aliabdolahi on 2/20/17.
 */
interface PersianExtendDialogBuilder {

    PersianExtendDialogBuilder withOkButton(String okButton);

    PersianExtendDialogBuilder withCancelButton(String cancelButton);

    PersianExtendDialogBuilder withOnDatePickedListener(OnExtendedDatePicked listener);

    PersianExtendDialog build();

}
