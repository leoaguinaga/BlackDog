package pe.edu.utp.blackdog.servlet;

import pe.edu.utp.blackdog.dao.ProductDAO;
import pe.edu.utp.blackdog.model.Product;
import pe.edu.utp.blackdog.model.Product_Type;
import pe.edu.utp.blackdog.util.AppConfig;
import pe.edu.utp.blackdog.util.UTPBinary;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet("/registerProduct")
@MultipartConfig
public class RegisterProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Product_Type type = Product_Type.getProductType(req.getParameter("type"));
        Part image = req.getPart("image");
        double price = Double.parseDouble(req.getParameter("price"));

        try {
            ProductDAO productDAO = new ProductDAO();

            if(!type.equals("HAMBURGER")) {
                //String dir = AppConfig.imgDir();
                String imageName = getFileName(image);
                //String fileFoto1 = dir + imageName;
                //byte[] data1 = image.getInputStream().readAllBytes();
                //UTPBinary.echobin(data1, fileFoto1);

                Product product = Product.createProductWithoutId(name, imageName, price, type);
                productDAO.registerProduct(product);

                resp.sendRedirect("admin/products.jsp");
            }

            resp.sendRedirect("admin/addBurger.jsp");


        } catch (Exception e) {
            e.printStackTrace();
            // Manejar el error apropiadamente, tal vez redireccionar a una página de error
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}

