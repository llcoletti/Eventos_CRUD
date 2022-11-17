<%-- 
    Document   : form-evento
    Created on : Sep 27, 2022, 10:39:48 PM
    Author     : Lucas
--%>

<%@page import="entidade.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CategoriaDAO"%>
<%@page import="entidade.Evento"%>
<%@page import="dao.EventoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    </head>
    <body>
        <%
            String param = request.getParameter("param");
            EventoDAO eDAO = new EventoDAO();
            Evento evento = null;
            int id = Integer.parseInt(request.getParameter("id"));
            if (param.equals("update")) {
                evento = eDAO.consultarEvento(id);
            }
        %>
        <jsp:include page="navbar.jsp"/>
        <div class="container " style="padding-left: 5rem; padding-right: 5rem">
            <%
                if(evento !=null)
                {
            %>
            <h2>Editor de Evento</h2> 
            <form action="<%=request.getContextPath()%>/EventoServlet?param=salvar&id=<%=evento.getId()%>" method="post">
            <%}else{%>
            <h2>Cadastro de Evento</h2>       
            <form action="<%=request.getContextPath()%>/EventoServlet?param=salvar" method="post">    
            <%}%> 
                <div class="form-group">
                    <label for="nome_evento">Nome do evento</label>
                    <%if (evento != null) {%>
                    <input name ="nome_evento" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="<%=evento.getNome_evento()%>" required>
                    <%} else {%>
                    <input name ="nome_evento" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Insire o nome do evento" required>
                    <%}%>
                </div>

                <label for="nome_evento">Categoria</label>
                <div class="d-flex">
                    <%
                        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
                        CategoriaDAO cDAO = new CategoriaDAO();
                        categorias = cDAO.consultarCategorias();

                        for (int i = 0; categorias.size() > i; i++) {
                    %>
                    <div class="form-check" style="margin-right:1rem">
                        <input required class="form-check-input" name="categoria" type="radio" name="flexRadioDefault" value="<%= categorias.get(i).getId() %>" id="<%= categorias.get(i).getCategoria()%>">
                        <label class="form-check-label" for="<%= categorias.get(i).getCategoria()%>">
                            <%= categorias.get(i).getCategoria()%>
                        </label>
                    </div>
                    <%}%>
                    <!--                    <div class="form-check" style="margin-right:1rem">
                                            <input class="form-check-input" name="categoria"type="radio" name="flexRadioDefault" value="2"id="categoria" >
                                            <label class="form-check-label" for="Formatura">
                                                Formatura
                                            </label>
                                        </div>
                                        <div class="form-check" style="margin-right:1rem">
                                            <input class="form-check-input" name="categoria" type="radio" name="flexRadioDefault" value="1" id="categoria">
                                            <label class="form-check-label" for="Aniversario">
                                                Aniversario
                                            </label>
                                        </div>-->
                </div>
                <div class="form-group">
                    <label for="data_evento">Data do evento</label>

                    <%if (evento != null) {%>
                    <input name ="data_evento" type="date" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" max="9999-12-31" placeholder="" value="<%= evento.getData_evento()%>" required>
                    <%} else {%>
                    <input name ="data_evento" type="date" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" max="9999-12-31" placeholder="" required>
                    <% }%>
                </div>
                <div class="form-group">
                    <label for="valor_custo_evento">Custo do evento</label>
                    <%if (evento != null) {%>
                    <input name ="valor_custo_evento" type="number" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="<%= evento.getValor_custo_evento()%>" required>
                    <%} else {%>
                    <input name ="valor_custo_evento" type="number" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="ex: 5000" required>
                    <% }%>
                </div>
                <div class="form-group">
                    <label for="observacoes">Observacoes</label>
                    <%if (evento != null) {%>
                    <input name ="observacoes" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="<%= evento.getObservacoes()%>" required>
                    <%} else {%>
                    <input name ="observacoes" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Observações" required>
                    <% }%>
                </div>
                <a href="eventos.jsp"  class="btn btn-secondary mt-5 mr-2"> Voltar</a>
                <button type="submit" class="btn btn-primary mt-5 ">Enviar</button>                
            </form>
        </div>
    </body>
</html>
