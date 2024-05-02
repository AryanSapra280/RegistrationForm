package registerServlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        out.println("name = " + name);

        // save to DB
        //build connection


        try {
            Thread.sleep(5000);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registeration","root","Admin@123");
            String insertQuery ="insert into user(name,user_password,email) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1,name);
            statement.setString(2,password);
            statement.setString(3,email);
            out.println(statement.getMetaData());
            statement.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
