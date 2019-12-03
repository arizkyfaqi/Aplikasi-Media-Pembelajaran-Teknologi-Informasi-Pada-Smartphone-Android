package com.arfanet.nuvo.belajarti.download_pdf;



/**
 * Created by Nuvo on 29/11/2017.
 */

import android.os.Environment;

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
