package br.senac.pi.exemplows;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome, edtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        UsuarioWS usuarioWS = new UsuarioWS();
        /*boolean resultado = usuarioWS.inserirUsuario(new Usuario(0, "Outro teste qualquer", 23));
        Log.d("cursoandroid", "Deu certo? " + resultado);*/

        /*ArrayList<Usuario> lista = usuarioWS.buscarTodosUsuario();
        Log.d("cursoandroid", "Usuários: " + lista.toString());*/

        Usuario u = usuarioWS.buscarPorID(1);
        Log.d("cursoandroid", "Usuário: " + u.toString());

        boolean r1 = usuarioWS.excluirUsuario(new Usuario(3, "", 0));
        Log.d("cursoandroid", "Deu certo? " + r1);

        boolean r2 = usuarioWS.atualizarUsuario(new Usuario(1, "Atualizado", 26));
        Log.d("cursoandroid", "Deu certo? " + r2);


        edtNome = (EditText) findViewById(R.id.edtNome);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        findViewById(R.id.btnCadastrar).setOnClickListener(cadastrar());
    }

    private View.OnClickListener cadastrar() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());
                UsuarioWS usuarioWS = new UsuarioWS();
                boolean resultado = usuarioWS.inserirUsuario(new Usuario(0, nome, idade));
                if (resultado) {
                    userMessage(getString(R.string.cadastro_sucesso));
                    edtNome.setText("");
                    edtIdade.setText("");
                    edtNome.requestFocus();
                } else {
                    userMessage(getString(R.string.cadastro_erro));
                }
            }
        };
    }

    private void userMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
