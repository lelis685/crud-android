package br.com.uninove;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View.OnClickListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // metodo usado para navegacao entre telas
    public void irTelaCadastro(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    // metodo usado para navegacao entre telas
    public void irTelaListarTodos(View view){
        Intent intent = new Intent(this, ListarActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Clique e segure para aparecer a opção de exclusão e atualização", Toast.LENGTH_LONG).show();
    }

    // metodo usado para navegacao entre telas
    public  void irHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // metodo usado para sair do app
    public  void sair(View view){
        this.sair = (Button)this.findViewById(R.id.btSair);
        this.sair.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

}
