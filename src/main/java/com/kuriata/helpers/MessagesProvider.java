package com.kuriata.helpers;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesProvider {
    private static final String MESSAGES_BUNDLE_NAME = "text";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, Locale.getDefault());
    private MessagesProvider(){}
    public static void updateLocale(){
        resourceBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, Locale.getDefault());
    }

    public static String getMessage(String messageKey){
        String result = resourceBundle.getString(messageKey);
        return result;
    }
}
