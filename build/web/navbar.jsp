<%-- 
    Document   : navbar
    Created on : 27/09/2022, 20:21:20
    Author     : comuni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-5">
    <a class="navbar-brand" href="index.jsp" style="margin-left:10rem">Projeto Integrador 7</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      
      <li class="mainMenu nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Prova
        </a>
        <div class=" dropdown-menu submenu" aria-labelledby="navbarDropdownMenuLink">
          <a class="" href="eventos.jsp" style="text-decoration: none;
                                        color: grey;
                                        margin-left: 1rem;">Presencial</a>
        </div>
      </li>
    </ul>
  </div>
</nav>
