<%@page import="entidade.Categoria"%>
<%@page import="dao.CategoriaDAO"%>
<%@page import="entidade.Evento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EventoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Aplicação Web JSP</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    </head>
    <body>
        <% 
            EventoDAO eDAO = new EventoDAO();
            CategoriaDAO cDAO = new CategoriaDAO();
            ArrayList<Evento> eventos = new ArrayList<Evento>();
            String param = (String) request.getAttribute("param");
            if(param != null)
            {
                eventos = (ArrayList<Evento>) request.getAttribute("eventosFiltrados");
            }else
            {
                eventos = eDAO.consultarEventos();            
            }
            
            
        %>
        <jsp:include page="navbar.jsp"/>

        <div class="container">          
            <h2>Eventos</h2>

              <form action="<%=request.getContextPath()%>/EventoServlet?param=filtrar" method ="post"  class="form-inline d-flex" style="width:20rem">
                <input name="filtrar_nome"class="form-control mr-sm-2" type="search"  aria-label="Search" placeholder="Busca por nome">
                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit" style="margin-left: 1rem" >Filtrar</button>
            </form>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nome evento</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Data</th>
                    <th scope="col">valor</th>
                    <th scope="col">observacoes</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for(int i=0; eventos.size() >i; i++)
                    {
                        Categoria c = new Categoria();
                        c = cDAO.consultarCategoria(eventos.get(i).getCategoria_id());
                
                %>
                <tr>
                    <th scope="row"><%= eventos.get(i).getId() %></th>
                    <td><%=eventos.get(i).getNome_evento() %></td>
                    <td><%=c.getCategoria() %></td>
                    <td><%=eventos.get(i).getData_evento() %></td>
                    <td>R$ <%=eventos.get(i).getValor_custo_evento() %></td>
                    <td><%=eventos.get(i).getObservacoes() %></td>
                    <td>  
                         <a href="form-evento.jsp?param=update&id=<%=eventos.get(i).getId() %>">Editar</a>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/EventoServlet?param=excluir&id=<%=eventos.get(i).getId() %>" >Excluir</a>
                    </td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </div>
            
    <div class="container"> <% if(param != null)
            {%>
                <button type="button" class="btn btn-secondary mr-2"> <a href="eventos.jsp" style="text-decoration: none; color:white"> Remover filtro </a> </button>
            <%} %>
        
        <button type="button" class="btn btn-primary"> <a href="form-evento.jsp?param=salvar&id=0" style="text-decoration: none; color:white"> Adicionar </a> </button>
       
    </div>

</body>
</html>
