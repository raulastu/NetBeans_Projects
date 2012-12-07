package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rC
 */
public class MostrarDatos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MostrarDatos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='index.jsp'>volver</a>");
            out.println("<h1> Lista de Alumnos </h1>");
            ResultSet rs = DBConnection.gerConexion().createStatement().executeQuery("SELECT * FROM t_alumno");
            out.println("<table border = '1'>");

            out.println("<th>");
            out.println("ID");
            out.println("<th/>");

            out.println("<th>");
            out.println("Nombres");
            out.println("<th/>");

            out.println("<th>");
            out.println("Apellidos");
            out.println("<th/>");

            out.println("<th>");
            out.println("Ciclo");
            out.println("<th/>");

            while (rs.next()) {
                out.println("<tr>");

                out.println("<td>");
                out.println(rs.getString(1));
                out.println("<td/>");

                out.println("<td>");
                out.println(rs.getString(2));
                out.println("<td/>");

                out.println("<td>");
                out.println(rs.getString(3));
                out.println("<td/>");

                out.println("<td>");
                out.println(rs.getString(4));
                out.println("<td/>");

                out.println("<tr/>");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
