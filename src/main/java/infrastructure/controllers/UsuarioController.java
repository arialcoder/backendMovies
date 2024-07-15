package infrastructure.controllers;

import application.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/users")
public class UsuarioController extends HttpServlet {

    // mapper de jackson
    private ObjectMapper mapper;
    // se llama al servicio
    private UsuarioService service;

    public UsuarioController() {
        this.mapper = new ObjectMapper();
        this.service = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //configureCorsHeaders(resp);

        // Captura el username que viene por parametro
        String username = req.getParameter("username");

        if(username != null){
            Usuario usuario =service.finByUsername(username); // devuelve un objeto tipo Usuario
            if(usuario !=null){
                // Se construye la response
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                //parseo a formato json para escribirlo en la response
                resp.getWriter().write(mapper.writeValueAsString(usuario));
            }else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Usuario no encontrado");
            }
        }else{
            ArrayList<Usuario> usuarios = service.getAllUsers();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(mapper.writeValueAsString(usuarios));
        }
    }

    // Alt + Insert se muestra el menu para sobreescribir doGet,doPost,etc
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //configureCorsHeaders(resp);
        //como los datos del usuario vienen en el body de la peticion desde el front se usa la libreria Jackson
        //getInputStream(), nombre de la clase para parsear
        Usuario usuario = this.mapper.readValue(req.getInputStream(), Usuario.class);
        service.saveUser(usuario);

        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
