package info.abdolahi.persianextenddialoglibrary;

import java.util.Date;

/**
 * Created by aliabdolahi on 2/20/17.
 */

public interface OnExtendedDatePicked {
    void onDate(Date date, int num);
    void onDismissed();
}
