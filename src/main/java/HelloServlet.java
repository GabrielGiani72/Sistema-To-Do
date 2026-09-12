import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter; // <-- ADICIONE ESTA LINHA

// Maps the servlet to http://localhost:8080/yourApp/hello
@WebServlet("/hello") 
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");
        
        // Write the HTML payload
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello from my Java Servlet!</h1>");
        out.println("</body></html>");
    }
}