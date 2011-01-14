<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>JSP Page</title>
    <style>
    dd {
      margin: 0 0 8px;
    }
    </style>
  </head>
  <body>
    <form action="file-upload-servlet" method="post" enctype="multipart/form-data">
      <dl>
        <dt>First name</dt>
        <dd><input name="first-name"/></dd>
        <dt>Last name</dt>
        <dd><input name="last-name"/></dd>
        <dt>Resume (Less than 200 KB)</dt>
        <dd><input type="file" name="resume"/></dd>
        <dt>&nbsp;</dt>
        <dt><input type="submit"/></dt>
      </dl>
    </form>
  </body>
</html>
