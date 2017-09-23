package br.edu.unicesumar.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by UniCesumar on 02/09/2017.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private final String[] scriptCreate;
    private final String scriptDelete;

    public SQLiteHelper(Context context, String nomeBanco, int versaoBanco,
                        String[] scriptCreate, String scriptDelete) {
        super(context, nomeBanco, null, versaoBanco);
        this.scriptCreate = scriptCreate;
        this.scriptDelete = scriptDelete;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int x = 0; x < scriptCreate.length; x++) {
            String sql = scriptCreate[x];
            Log.i("SQL", sql);
            db.execSQL(sql);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQL", scriptDelete);
        db.execSQL(scriptDelete);
        onCreate(db);
    }
}


