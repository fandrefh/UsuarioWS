package br.senac.pi.exemplows;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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
            soapObject.addProperty("id", usuario.getId());
            soapObject.addProperty("nome", usuario.getNome());
            soapObject.addProperty("idade", usuario.getIdade());
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
}
