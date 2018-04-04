package br.com.senaijandira.fintechs;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CadastroActivity extends AppCompatActivity {

    Spinner spinner_conta;
    Spinner spinner_categoria;
    EditText txt_descricao;
    EditText txt_valor;
    EditText txt_data;
    TextView txt_valor_total;

    Boolean modoEdicao = false;
    Despesa despesa;
    Receita receita;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);





        txt_descricao = (EditText) findViewById(R.id.txt_descricao);
        txt_valor = (EditText) findViewById(R.id.txt_valor);
        txt_data = (EditText) findViewById(R.id.txt_data);
        txt_valor_total = (TextView) findViewById(R.id.txt_valor_total);

        Add_itens_conta();
        Add_itens_categoria();

        Integer idDespesa = getIntent().getIntExtra("idDespesa", 0);

        if (idDespesa != 0) {
            //edição
            modoEdicao = true;
            despesa = DespesaDAO.getInstance().selectionarUm(this, idDespesa);

            txt_descricao.setText(despesa.getDescricao());
            Locale ptBr = new Locale("pt", "BR");
            NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);


            txt_valor.setText(nf.format(despesa.getValor()));
            txt_data.setText(new SimpleDateFormat("dd/MM/yyyy")
                    .format(despesa.getData()));

        }

    }


    public void salvar(View v) {

        boolean txt;

        if (txt = true) {

            //verificar se o nome não esta vazio
            if (txt_descricao.getText().toString().isEmpty()) {
                txt_descricao.setError("Preencha a descrição");
                return;
            }

            Despesa d;

            if (modoEdicao) {
                d = despesa;
            } else {
                d = new Despesa();
            }

            d.setDescricao(txt_descricao.getText().toString());
            float number = Float.valueOf(txt_valor.getText().toString().replace(",", ".").replace("R$", ""));
            d.setValor(number);

            d.setCategoria(spinner_categoria.getSelectedItem().toString());
            d.setConta(spinner_conta.getSelectedItem().toString());

            String dt_despesa = txt_data.getText().toString();

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            try {
                //formatar a data
                Date dt = df.parse(dt_despesa);

                //definindo a dt de nascimento
                d.setData(dt);

            } catch (Exception ex) {

                txt_data.setError("Preencha uma data correta.");
                return;
            }


            if (modoEdicao) {

                DespesaDAO.getInstance().atualizar(this, d);
                //msg de atualizado sucesso
                Toast.makeText(this, "Atualizado com sucesso",
                        Toast.LENGTH_SHORT).show();
            } else {
                //inserir no banco de dados
                DespesaDAO.getInstance().inserir(this, d);

                //msg de inserido sucesso
                Toast.makeText(this, "Inserido com sucesso",
                        Toast.LENGTH_SHORT).show();
            }


        }
        else {
            //verificar se o nome não esta vazio
            if (txt_descricao.getText().toString().isEmpty()) {
                txt_descricao.setError("Preencha a descrição");
                return;
            }

            Receita r;

            if (modoEdicao) {
                r = receita;
            } else {
                r = new Receita();
            }

            r.setDescricao(txt_descricao.getText().toString());
            float number = Float.valueOf(txt_valor.getText().toString().replace(",", ".").replace("R$", ""));
            r.setValor(number);

            r.setCategoria(spinner_categoria.getSelectedItem().toString());

            String dt_receita = txt_data.getText().toString();

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            try {
                //formatar a data
                Date dt = df.parse(dt_receita);

                //definindo a dt de nascimento
                r.setData(dt);

            } catch (Exception ex) {

                txt_data.setError("Preencha uma data correta.");
                return;
            }


            if (modoEdicao) {

                ReceitaDAO.getInstance().atualizar(this, r);
                //msg de atualizado sucesso
                Toast.makeText(this, "Atualizado com sucesso",
                        Toast.LENGTH_SHORT).show();
            } else {
                //inserir no banco de dados
                ReceitaDAO.getInstance().inserirReceita(this, r);

                //msg de inserido sucesso
                Toast.makeText(this, "Inserido com sucesso",
                        Toast.LENGTH_SHORT).show();
            }
        }

        //finalizar a tela
        finish();

    }

    public void Add_itens_conta() {
        spinner_conta = (Spinner) findViewById(R.id.spinner_conta);

        List<String> list = new ArrayList<String>();
        list.add("Bradesco");
        list.add("Itau");
        list.add("Caixa");
        list.add("Banco do Brasil");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_conta.setAdapter(dataAdapter);
    }

    public void Add_itens_categoria() {
        spinner_categoria = (Spinner) findViewById(R.id.spinner_categoria);

        List<String> list = new ArrayList<String>();
        list.add("Lazer");
        list.add("Transporte");
        list.add("Saúde");
        list.add("Modaria");
        list.add("Salário");
        list.add("Outros");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_categoria.setAdapter(dataAdapter);
    }

}
