package realm.io.realmsurveys;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.UUID;

public class SharedPrefsUtils {

    private static final String defaultsUserKey = BuildConfig.APPLICATION_ID;
    private static SharedPrefsUtils privateInstance;

    private SharedPreferences sharedPreferences;

    public static void init(Application application) {
        if(privateInstance == null) {
            privateInstance = new SharedPrefsUtils(application);
        }
    }

    private SharedPrefsUtils(Application application) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
    }

    public static SharedPrefsUtils getInstance() {
        return privateInstance;
    }

    public String uniqueUserId() {

        String idForCurrentUser = sharedPreferences.getString(defaultsUserKey, null);

        if (idForCurrentUser == null) {

            idForCurrentUser = UUID.randomUUID().toString();
            sharedPreferences.edit().putString(defaultsUserKey, idForCurrentUser).apply();
        }

        return idForCurrentUser;
    }

}
