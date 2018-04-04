package br.com.senaijandira.fintechs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView list_view_despesas;
    TextView txt_valor_total;
    DespesaAdapter adapter;
    DespesaDAO dao ;
    String strValor_geral;
    float valor_geral = 0f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Pegando a instancia do DAO
        dao = DespesaDAO.getInstance();

        //cadastros fake
        //cadastrosFake();

        //findviews
        list_view_despesas = (ListView) findViewById(R.id.list_view_despesas);
        txt_valor_total = (TextView) findViewById(R.id.txt_valor_total);


        //strValor_geral = Float.toString(despesa.getValor_geral());
        //strValor_geral = String.valueOf(despesa.getValor_geral());
        //txt_valor_total.setText(strValor_geral);

        //criando o adaptador vazio
        adapter = new DespesaAdapter(this, new ArrayList<Despesa>());

        //plugando(conectando) o adaptador na lista
        list_view_despesas.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);

                boolean txt = true;
                Bundle bundle = new Bundle();

                bundle.putBoolean("txt", txt);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);

                Boolean txt = false;
                Bundle bundle = new Bundle();

                bundle.putBoolean("txt", txt);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        //listener de click da lista
        list_view_despesas.setOnItemClickListener(this);
    }



    @Override
    protected void onResume() {
        super.onResume();



        //Pegando os contatos do banco
        ArrayList<Despesa> despesasCadastradas;
        despesasCadastradas = dao.selecionarTodos(this);

        for (Despesa des : despesasCadastradas){

            valor_geral = valor_geral + des.getValor();

            strValor_geral = String.valueOf(valor_geral);
            txt_valor_total.setText(strValor_geral);
        }


        //limpar o conteudo
        adapter.clear();

        //preenchendo o adaptador
        adapter.addAll(despesasCadastradas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //tratar o click da ListView
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int i, long l) {

        //pegando a despesa da posição i da lista
        Despesa item = adapter.getItem(i);

        //criando o objeto de intenção
        Intent intent = new Intent(this,
                VisualizarActivity.class);

        //passando o id da despesa
        intent.putExtra("idDespesa", item.getId());

        //abrindo a tela de visualizar
        startActivity(intent);

        //Toast.makeText(this, "Cliquei na lista",
        //        Toast.LENGTH_SHORT).show();

    }
}
