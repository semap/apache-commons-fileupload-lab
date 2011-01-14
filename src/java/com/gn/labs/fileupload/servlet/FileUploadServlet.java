package com.gn.labs.fileupload.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=ISO-8859-1");
    PrintWriter out = response.getWriter();
    try {
      /* Check that we have a file upload request */
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);

      if (isMultipart) {
        File tempDir = new File("com/gn/labs/uploads/temp");
        tempDir.mkdirs();

        FileItemFactory factory = new DiskFileItemFactory(75000, tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(200000);

        HashMap requestParameters = new HashMap();

        List items = upload.parseRequest(request);
        Iterator iterator = items.iterator();
        while (iterator.hasNext()) {
          FileItem fileItem = (FileItem) iterator.next();

          if (fileItem.isFormField()) {
            //out.print("[FORM FIELD] Name: " + fileItem.getFieldName() + ", value: " + fileItem.getString() + "</br>");
            requestParameters.put(fileItem.getFieldName(), fileItem.getString());
          } else {
            //out.print("[FILE FIELD] Name: " + fileItem.getFieldName() + ", file name: " + fileItem.getName() + ", size: " + fileItem.getSize() + "</br>");

            File uploadedFile = new File("com/gn/labs/uploads/abc.pdf");
            fileItem.write(uploadedFile);
          }
        }
        out.print(requestParameters.get("first-name") + " " + requestParameters.get("last-name") + ", your resume has been received.");
      }
    } catch (Exception ex) {
      Logger.getLogger(FileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
      response.sendError(500);
    } finally {
      out.close();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }
}
