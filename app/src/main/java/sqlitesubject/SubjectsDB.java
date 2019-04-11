package sqlitesubject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Subjects;

public class SubjectsDB extends SQLiteOpenHelper
{
    public static final String dbName = "dbMySubject";
    public static final String tblNameSubjects = "subjects";
    public static final String colSubCode = "subject_code";
    public static final String colSubName = "subject_name";
    public static final String colSubGrade = "subject_grade";
    public static final String colSubCredit = "subject_credit";
    public static final String colSubId = "subject_id";

    public static final String strCrtTblSubjects ="CREATE TABLE " + tblNameSubjects + " (" +
            colSubId + " INTEGER PRIMARY KEY, " + colSubCode + " TEXT, "+ colSubName +
            " TEXT, "+ colSubGrade + " TEXT, "+ colSubCredit + " INTEGER)";
    public static final String strDrpTblExpenses = "DROP TABLE IF EXISTS "+ tblNameSubjects;

    public SubjectsDB(Context context)
    {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(strCrtTblSubjects);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(strDrpTblExpenses);
        onCreate(db);
    }

    public float fnInsertSubject(Subjects meSubject)
    {
        float retResult = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colSubCode, meSubject.getStrSubCode());
        values.put(colSubName, meSubject.getStrSubName());
        values.put(colSubGrade, meSubject.getStrSubGrade());
        values.put(colSubCredit, meSubject.getStrSubCredit());

        retResult = db.insert(tblNameSubjects,null,values);
        return retResult;

    }

    public List<Subjects> fnGetAllSubjects()
    {
        List<Subjects> listSub = new ArrayList<>();
        String strSelAll = "Select * from " + tblNameSubjects;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSelAll,null);
        if(cursor.moveToFirst())
        {
            do{
                Subjects subject = new Subjects();
                subject.setStrSubGrade(cursor.getString(cursor.getColumnIndex(colSubGrade)));
                subject.setStrSubCredit(cursor.getInt(cursor.getColumnIndex(colSubCredit)));
                listSub.add(subject);
            }while(cursor.moveToNext());
        }
        return listSub;
    }

}
