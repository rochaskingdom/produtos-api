package com.vinicius.produtos.api.produto;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/produtos")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Tag(name = "Produtos")
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    public Response listaTodosProdutos() {
        List<Produto> produtos = service.buscaTodosProdutos();
        return Response.ok(produtos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Produto produto = service.buscaPorId(id);
        if (produto != null) {
            return Response.ok(produto).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    public Response adicionarProduto(@Valid Produto produto, @Context UriInfo uriInfo) {
        produto = service.adicionar(produto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(produto.id));
        return Response.created(builder.build()).build();
    }
}
