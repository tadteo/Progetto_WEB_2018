
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.cinema.dataaccess.Beans.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<link href="https://fonts.googleapis.com/css?family=Gugi" rel="stylesheet">

<footer class="page-footer  font-small blue pt-4 mt-4 bg-secondary text-light">

  <div class="container">
    <c:choose>
      <c:when test="${hideInfos == null}">
        <div class="row">
          <div class="col-lg-6 col-md-12">
            <div class="my-2">
              <a class="my-3" href="/cinema/info">Info</a>
            </div>
            <p><b>Telefono:</b> +39 0123 123123</p>
            <p><b>Indirizzo:</b> Via La Vita E Tutto Quanto, 42 (UNIVERSO)</p>
            <p><b>Partita Iva: </b>01234561001<b> – C.F. </b>01234561001</p>

          </div>
          <div class="col-lg-6 col-md-12">
            <p>Posted by: Magic Group Srl</p>
            <p>Contact information: <a href="mailto:info@magicgroup.com">info@magicgroup.com</a>.</p>
          </div>
        </div>
      </c:when>
    </c:choose>
    <br>
    <!--Copyright-->
    <div class="footer-copyright py-3 text-center">
      <p style="font-size: 0.8rem;font-style: italic;" class="copyright">Copyright © 2018 · Tadiello Matteo - Stefani Domenico - Martini Ivan · all rights reserved.</p>
    </div>
    <!--/.Copyright-->
  </div>
</footer>