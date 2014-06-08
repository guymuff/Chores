package com.guyamuff.chores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

/**
 * Created by Arch Stanton on 9/22/13.
 */
public class DBManager extends SQLiteOpenHelper {

    static final String DB_NAME = "chores";
    static final int DB_VERSION = 1;

    DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
//DELETE FROM todays_chores WHERE chore_fk NOT IN (16, 27);
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(MainActivity.TAG, "Bootstrapping db");

        Random rand = new Random();

        try {
            sqLiteDatabase.execSQL("CREATE TABLE chore (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, frequency INTEGER, last_completed INTEGER );");

            long now = System.currentTimeMillis();
            putChoreInDB("Change and launder bath mats, towels, and washcloths.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Clean toilet.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Clean shower.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Clean bathroom sink.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Dust bathroom light fixture.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Empty bathroom trash bin and wipe the inside and outside.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Mop floor.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe mirrors.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Change sheets.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Dust bedroom.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Dust dining room.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Vacuum upholstery", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Dust hallway.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Discard foods and beverages past their prime.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Flush kitchen drain with boiling water.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe kitchen surfaces, including sink, countertops, the outside of the ventilation hood, refrigerator.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe cupboard doors.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe the inside of the oven, microwave, and toaster oven.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe the inside and outside of kitchen trash and recycling bins.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Fluff and rotate sofa cushions.", Frequency.WEEKLY, sqLiteDatabase, rand, now);
            putChoreInDB("Scrub grout.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe insides of medicine cabinets.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe tub and shower surrounds.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Launder duvet covers, pillow protectors, mattress pads, and shams.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Dust closet shelves and storage bins.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Vacuum closet floors and baseboards.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Discard food in the freezer that's past its prime.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wash ventilation hood filters.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Flush drains with vinegar, boiling water, and baking soda.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe interior and exterior doors and trim.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Vacuum window treatments, moldings, and windowsills.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe switch plates.", Frequency.MONTHLY, sqLiteDatabase, rand, now);
            putChoreInDB("Launder pillows.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Turn mattresses.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Vacuum mattresses, box springs, and bed frames.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Clean oven.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Organize pantry, discarding expired food.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Replace baking soda in refrigerator and freezer.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Remove contents of cabinets and clean interiors.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe kitchen ceiling.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe the inside of the refrigerator.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Clean leather furniture.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wipe baseboards and moldings.", Frequency.SEASONALLY, sqLiteDatabase, rand, now);
            putChoreInDB("Dust slat blinds.", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Dust Books and Shelves", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Clean Rugs, Carpets, and Floors", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Sort Through Wardrobes", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Wash Windows", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Launder or dry-clean blankets.", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Discard expired cosmetics, beauty products, and medications.", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Reorganize closets, giving away unwanted items.", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Clean out files.", Frequency.YEARLY, sqLiteDatabase, rand, now);
            putChoreInDB("Oil window and door hinges.", Frequency.YEARLY, sqLiteDatabase, rand, now);

            sqLiteDatabase.execSQL("CREATE TABLE todays_chores (chore_fk INTEGER REFERENCES chore(id), previous_time INTEGER);");
        } catch (SQLException ex) {
            Log.e(MainActivity.TAG, ex.getMessage(), ex);
        }
    }

    protected void putChoreInDB(String name, Frequency freq, SQLiteDatabase db, Random rand, long now) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("frequency", freq.millis);

        long last = nextLong(freq, rand, now);
        contentValues.put("last_completed", last);

        db.insertOrThrow("chore", null, contentValues);
    }

    protected long nextLong(Frequency freq, Random rand, long now) {
        return now - ((long) Math.floor(rand.nextDouble() * freq.millis));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    static final String[] CHORE_COLS = {"id", "name", "frequency", "last_completed"};

    public Chore[] queryChores() {
        Chore[] ret = null;

        SQLiteDatabase db = getReadableDatabase();

        try {
            db.beginTransaction();
            Cursor cursor = db.query("chore", CHORE_COLS, null, null, null, null, null);
            int rows = cursor.getCount();
            ret = new Chore[rows];

            int idIdx = cursor.getColumnIndexOrThrow("id");
            int nameIdx = cursor.getColumnIndexOrThrow("name");
            int freqIdx = cursor.getColumnIndexOrThrow("frequency");
            int lastIdx = cursor.getColumnIndexOrThrow("last_completed");

            cursor.moveToFirst();
            for (int i = 0; i < rows; i++) {
                ret[i] = new Chore();
                ret[i].id = cursor.getLong(idIdx);
                ret[i].name = cursor.getString(nameIdx);
                ret[i].frequency = cursor.getLong(freqIdx);
                ret[i].lastCompleted = cursor.getLong(lastIdx);
                cursor.moveToNext();
            }
            db.endTransaction();
        } catch (SQLiteException ex) {
            Log.e(MainActivity.TAG, ex.getMessage(), ex);
        }

        return ret;
    }

    public void updateCompletionTime(Chore c) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("last_completed", c.lastCompleted);
        String[] id = new String[1];
        id[0] = Long.toString(c.id);
        db.update("chore", contentValues, "id = ?", id);

        contentValues = new ContentValues();
        contentValues.put("previous_time", c.previousTime);
        db.update("todays_chores", contentValues, "chore_fk = ?", id);
    }

    public void clearTodays() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todays_chores", null, null);
    }

    public void updateTodays(Chore chore) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("chore_fk", chore.id);
        contentValues.put("previous_time", 0);

        db.insertOrThrow("todays_chores", null, contentValues);
    }


    public Chore[] getTodaysChores() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * " +
                       "FROM chore c " +
                       "   INNER JOIN todays_chores tc ON tc.chore_fk = c.id";
        Cursor cursor = db.rawQuery(query, null);

        int rows = cursor.getCount();
        Chore[] ret = new Chore[rows];

        int idIdx = cursor.getColumnIndexOrThrow("id");
        int nameIdx = cursor.getColumnIndexOrThrow("name");
        int freqIdx = cursor.getColumnIndexOrThrow("frequency");
        int lastIdx = cursor.getColumnIndexOrThrow("last_completed");
        int prevIdx = cursor.getColumnIndexOrThrow("previous_time");

        cursor.moveToFirst();
        for (int i = 0; i < rows; i++) {
            ret[i] = new Chore();
            ret[i].id = cursor.getLong(idIdx);
            ret[i].name = cursor.getString(nameIdx);
            ret[i].frequency = cursor.getLong(freqIdx);
            ret[i].lastCompleted = cursor.getLong(lastIdx);
            ret[i].previousTime = cursor.getLong(prevIdx);
            cursor.moveToNext();
        }
        return ret;
    }

    public static enum Frequency {
        DAILY(86400000l),
        WEEKLY(637860744l),
        MONTHLY(2551442976l),
        SEASONALLY(7889537441l),
        YEARLY(31558149764l);

        public long millis;

        Frequency(long millis) {
            this.millis = millis;
        }
    }
}
