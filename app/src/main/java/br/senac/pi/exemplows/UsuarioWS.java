package br.senac.pi.exemplows;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Note on 04/12/2015.
 */
public class UsuarioWS {

    private static final String URL = "http://10.19.9.2:8080/ExemploWS/services/UsuarioDAO?wsdl";
    private static final String NAMESPACE = "http://exemplows.pi.senac.br";

    private static final String INSERIR = "inserirUsuario";
    private static final String ATUALIZAR = "atualizarUsuario";
    private static final String EXCLUIR = "excluirUsuario";
    private static final String BUSCAR_POR_ID = "buscarPorID";
    private static final String BUSCAR_TODOS = "listarTodosUsuarios";

    public boolean inserirUsuario(Usuario usuario) {
        try {
            SoapObject soapObject = new SoapObject(NAMESPACE, INSERIR);
            SoapObject user = new SoapObject(NAMESPACE, "usuario");
            user.addProperty("id", usuario.getId());
            user.addProperty("nome", usuario.getNome());
            user.addProperty("idade", usuario.getIdade());
            soapObject.addSoapObject(user);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);
            envelope.implicitTypes = true;
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call("urn:" + INSERIR, envelope);
            Object resposta = envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Usuario> buscarTodosUsuario() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        SoapObject soapObject = new SoapObject(NAMESPACE, BUSCAR_TODOS);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);
        envelope.implicitTypes = true;
        HttpTransportSE transportSE = new HttpTransportSE(URL);
        try {
            transportSE.call("urn:" + BUSCAR_TODOS, envelope);
            Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
            for (SoapObject so: resposta) {
                Usuario user = new Usuario();
                user.setId(Integer.parseInt(so.getProperty("id").toString()));
                user.setNome(so.getProperty("nome").toString());
                user.setIdade(Integer.parseInt(so.getProperty("idade").toString()));
                lista.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public Usuario buscarPorID(int id) {
        Usuario usuario = null;
        SoapObject soapObject = new SoapObject(NAMESPACE, BUSCAR_POR_ID);
        soapObject.addProperty("id", id);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);
        envelope.implicitTypes = true;
        HttpTransportSE transportSE = new HttpTransportSE(URL);
        try {
            transportSE.call("urn:" + BUSCAR_POR_ID, envelope);
            SoapObject resposta = (SoapObject) envelope.getResponse();
            usuario = new Usuario();
            usuario.setId(Integer.parseInt(resposta.getProperty("id").toString()));
            usuario.setNome(resposta.getProperty("nome").toString());
            usuario.setIdade(Integer.parseInt(resposta.getProperty("idade").toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return usuario;
        }
        return usuario;
    }

    public boolean excluirUsuario(Usuario usuario) {
        try {
            SoapObject soapObject = new SoapObject(NAMESPACE, EXCLUIR);
            SoapObject user = new SoapObject(NAMESPACE, "usuario");
            user.addProperty("id", usuario.getId());
            user.addProperty("nome", usuario.getNome());
            user.addProperty("idade", usuario.getIdade());
            soapObject.addSoapObject(user);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);
            envelope.implicitTypes = true;
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call("urn:" + EXCLUIR, envelope);
            Object resposta = envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluirUsuario(int id) {
        return excluirUsuario(new Usuario(id, "", 0));
    }

    public boolean atualizarUsuario(Usuario usuario) {
        try {
            SoapObject soapObject = new SoapObject(NAMESPACE, ATUALIZAR);
            SoapObject user = new SoapObject(NAMESPACE, "usuario");
            user.addProperty("id", usuario.getId());
            user.addProperty("nome", usuario.getNome());
            user.addProperty("idade", usuario.getIdade());
            soapObject.addSoapObject(user);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);
            envelope.implicitTypes = true;
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call("urn:" + ATUALIZAR, envelope);
            Object resposta = envelope.getResponse();
            return Boolean.parseBoolean(resposta.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
