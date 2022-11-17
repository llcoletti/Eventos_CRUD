/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dao.EventoDAO;
import entidade.Evento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class EventoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EventoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EventoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        System.out.println("Get");
        EventoDAO eDAO = new EventoDAO();

        String param = request.getParameter("param");
        String id = request.getParameter("id");

        System.out.println(param);
        if (param.equals("excluir")) {
            try {
                eDAO.excluir(Integer.parseInt(id));

                RequestDispatcher rd = request.getRequestDispatcher("eventos.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("Erro ao excluir evento");
            }
        }        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        EventoDAO eDAO = new EventoDAO();

        String param = request.getParameter("param");
        String id = request.getParameter("id");

        if (param.equals("salvar")) {
            System.out.println("POST: SALVAR");
            Evento event = new Evento();
            if(id != null)
            {
                event.setId(Integer.parseInt(id));   
            }else{                    
                event.setId(0);             
            }
            event.setNome_evento(request.getParameter("nome_evento"));
            event.setCategoria_id(Integer.parseInt( request.getParameter("categoria")));
            event.setData_evento(request.getParameter("data_evento"));
            event.setValor_custo_evento(Integer.parseInt(request.getParameter("valor_custo_evento")));
            event.setObservacoes(request.getParameter("observacoes"));
            
            
            System.out.println("ID: " + event.getId());

            try {
                eDAO.salvar(event);
                RequestDispatcher rd = request.getRequestDispatcher("eventos.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("Não foi possível salvar o Evento");
            }
        }
        
        if (param.equals("filtrar")) {
            
            String nome = request.getParameter("filtrar_nome");
            System.out.println("filtar:"+nome);

            ArrayList<Evento> eventos = new ArrayList<Evento>();
            
            eventos = eDAO.consultarEventos(nome);
            
            try {
                RequestDispatcher rd = request.getRequestDispatcher("eventos.jsp");
                request.setAttribute("param","filtro");
                request.setAttribute("eventosFiltrados",eventos);
                rd.forward(request, response);
            } catch (Exception e) {
                System.out.println("Não foi possível salvar o Evento");
            }
        }
    }
}
