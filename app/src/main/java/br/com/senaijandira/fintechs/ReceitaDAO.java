package br.com.senaijandira.fintechs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 17170118 on 28/03/2018.
 */

public class ReceitaDAO {

    private Integer idReceita = 0;
    private int id;

    //Esquema de classe Singleton
    private static ReceitaDAO instance;

    public static ReceitaDAO getInstance(){

        if(instance == null) {
            instance = new ReceitaDAO();

        }

        return instance;
    }

    public Boolean inserirReceita(Context context, Receita receita){

        //acessar o banco em modo escrita
        SQLiteDatabase db = new DbHelperReceita(context).getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descricao",receita.getDescricao());
        valores.put("valor",receita.getValor());
        valores.put("data",receita.getData().toString());
        valores.put("categoria",receita.getCategoria());


        //TODO:Arrumar data de nascimento e foto
        //valores.put("dt_nascimento",contato.getDt_nascimento());

        //inserindo os valores na tabela (nome da tabela, coluna que recebera
        //valor nulo(caso tenha valor nulo, valores)
        Long id = db.insert("tbl_fintechs_receita", null, valores);

        if (id != -1){
            return true;
        }else{
            return false;
        }


    }

    public ArrayList<Receita> selecionarTodos(Context context){

        ArrayList<Receita> retorno = new ArrayList<>();

        //Acessando o Banco no modo leitura
        SQLiteDatabase db = new DbHelperReceita(context).getReadableDatabase();

        String sql = "select * from tbl_fintechs_receita";

        //Execultando no banco (comando e os null(whare caso tenha) e retornando o resultado
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){

            Receita receita = new Receita();
            receita.setId(cursor.getInt(0));
            receita.setDescricao(cursor.getString(1));
            receita.setValor(cursor.getFloat(2));
            //despesa.setData(cursor.getString(3));
            receita.setCategoria(cursor.getString(4));

            //TODO: Arrumar dt nascimento
            receita.setData(new Date());

            retorno.add(receita);


        }

        return retorno;

    }


    public Receita selectionarUm(Context context,int id){

        SQLiteDatabase db = new DbHelperReceita(context).getReadableDatabase();
        String sql = "select * from tbl_fintechs_receita where idReceita = " +id;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Receita receita = new Receita();
            receita.setId(cursor.getInt(0));
            receita.setDescricao(cursor.getString(1));
            receita.setValor(cursor.getFloat(2));
            //despesa.setData(cursor.getString(3));
            receita.setCategoria(cursor.getString(4));

            receita.setData (new Date());//Arrumar data

            cursor.close();
            return receita;
        }

        return null;
    }

    public Boolean remover(Context context, Integer id){

        SQLiteDatabase db = new DbHelperReceita(context).getReadableDatabase();

        db.delete("tbl_fintechs_receita", "idReceita = ?", new String[]{id.toString()});

        return true;
    }

    public Boolean atualizar(Context context, Receita receita){

        SQLiteDatabase db = new DbHelperReceita(context).getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descricao",receita.getDescricao());
        valores.put("valor",receita.getValor());
        valores.put("data",receita.getData().toString());
        valores.put("categoria",receita.getCategoria());

        //TODO:Arrumar data de nascimento e foto
        //valores.put("data",receita.getData().toString());




        db.update("tbl_fintechs_receita", valores, "idReceita = ?", new String[]{receita.getId().toString()});

        return true;

    }


}
