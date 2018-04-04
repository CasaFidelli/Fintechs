package br.com.senaijandira.fintechs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by 17170118 on 28/03/2018.
 */

public class DbHelperReceita extends SQLiteOpenHelper {

    //Nome do banco
    private static String DB_NAME = "fintechs.db";
    //versao do banco
    private static int DB_VERSION = 1;

    //Construtor da classe para criançao do banco
    public DbHelperReceita(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    //Crianção do banco, é igual criar tabela ou qualquer estrutura inicial
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "Create table tbl_fintechs_receita(" +
                "idDespesa INTEGER primary key autoincrement," +
                "descricao TEXT," +
                "valor REAL," +
                "data INTEGER," +
                "categoria TEXT)";

        db.execSQL(sql);

    }

    //Update do banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table tbl_fintechs");
        onCreate(db);

    }


}
