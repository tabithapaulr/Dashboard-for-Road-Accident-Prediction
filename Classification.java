package com.users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet(name = "Classification", urlPatterns = {"/Classification"})
public class Classification extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String year = request.getParameter("year");
            String ctype = request.getParameter("ctype");
            String csubtype = request.getParameter("csubtype");
            
            
            if(ctype.equals("Alcohol"))
            {
                ctype = "alcoholbasedrecords";
            }
            else if(ctype.equals("Climate"))
            {
                ctype = "climatebasedrecords";
            }
            else if(ctype.equals("Location"))
            {
                ctype = "locationbased";
            }
            else if(ctype.equals("Junction"))
            {
                ctype = "junctionbased";
            }
            else
            {
                ctype = "vehicledefect";
            }
            
            KMeansClustering km = new KMeansClustering();
            JSONObject jsonaray = km.atRiskStudents(year, ctype, csubtype);
            
            out.print(jsonaray);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
