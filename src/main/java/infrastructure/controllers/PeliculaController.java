package infrastructure.controllers;

import application.services.PeliculaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Pelicula;
import domain.models.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/peliculas")
public class PeliculaController extends HttpServlet {

    private final ObjectMapper mapper;
    private final PeliculaService service;

    public PeliculaController() {
        this.mapper = new ObjectMapper();
        this.service = new PeliculaService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // configureCorsHeaders(resp);

        // Captura el titulo  que viene por parametro
        String titulo = req.getParameter("titulo");

        if(titulo != null){
            Pelicula pelicula =service.findByTitulo(titulo); // devuelve un objeto tipo Pelicula
            if(pelicula !=null){
                // Se construye la response
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                //parseo a formato json para escribirlo en la response
                resp.getWriter().write(mapper.writeValueAsString(pelicula));
            }else{
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Usuario no encontrado");
            }
        }else{
            ArrayList<Pelicula> peliculas = service.getAllMovies();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(mapper.writeValueAsString(peliculas));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //configureCorsHeaders(resp);

        Pelicula pelicula = this.mapper.readValue(req.getInputStream(), Pelicula.class);
        service.guardarPelicula(pelicula);

        resp.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");

        if(idString !=null && !idString.isEmpty()){
            int id = Integer.parseInt(idString);
            service.borrarPelicula(id);
            resp.setStatus(200);
        }else{
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("ID DE USUARIO INVALIDO");
        }
    }

}
