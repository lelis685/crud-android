package br.com.uninove;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.uninove.banco.ProdutoDAO;
import br.com.uninove.model.Produto;
import br.com.uninove.utils.Validador;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome, categoria,valor;
    private ProdutoDAO dao;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // relacionando obj java com xml
        nome = findViewById(R.id.editNome);
        categoria = findViewById(R.id.editCategoria);
        valor = findViewById(R.id.editValor);

        // instanciando classe ProdutoDAO passando como parametro contexto atual
        dao = new ProdutoDAO(this);

        // verificando se existe produto para setar os valores na tela de atualizacao
        Intent it = getIntent();
        if (it.hasExtra("produto")) {
            produto = (Produto) it.getSerializableExtra("produto");
            nome.setText(produto.getNome());
            categoria.setText(produto.getCategoria());
            valor.setText(produto.getValor().toString());
        }
    }



    private Produto criarProduto(EditText nome,EditText categoria,EditText valor){
        // verificando se nao existi nenhum erro na validacao do form
        if(Validador.validar(nome,categoria,valor) == null){
            produto.setCategoria(categoria.getText().toString().trim());
            produto.setNome(nome.getText().toString().trim());
            produto.setValor(Double.parseDouble(valor.getText().toString().trim()));
      // existindo erro no form exibir msg de acordo com o que esta preenchido errado
       }else{
            Toast toast = Toast.makeText(this,Validador.validar(nome,categoria,valor), Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();
        }
        return produto;
    }


    // metodo responsavel para salvar ou atualizar produto de acordo com a opcao clicada na listagem
    public void salvarOuAtualizar(View view){
        // caso nao exista produto no extra sera chamado o metodo inserir
        if(produto == null){
            produto = new Produto();
            // valida preenchimento form
            if(Validador.validar(nome,categoria,valor) == null){
                produto.setCategoria(categoria.getText().toString().trim());
                produto.setNome(nome.getText().toString().trim());
                produto.setValor(Double.parseDouble(valor.getText().toString().trim()));

                // salvando produto no banco
                long id = dao.inserir(produto);

                // limpando campos
                nome.setText("");
                categoria.setText("");
                valor.setText("");

                Toast.makeText(this,"Produto inserido com id: "+ id, Toast.LENGTH_SHORT).show();
            }else{
                Toast toast = Toast.makeText(this,Validador.validar(nome,categoria,valor), Toast.LENGTH_SHORT);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextColor(Color.RED);
                toast.show();
            }

        }else{
            Produto produto = criarProduto(nome, categoria, valor);
            // atualizando produto no banco
            dao.atualizar(produto);
            Toast.makeText(this,"Produto foi atualizado", Toast.LENGTH_SHORT).show();
        }

    }

   // metodo usado para navegacao entre telas
    public  void irHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
