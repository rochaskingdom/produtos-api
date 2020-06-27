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

@Path("/products")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Tag(name = "Products")
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
        produto = service.adicionarProduto(produto);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(produto.id));
        return Response.created(builder.build()).build();
    }

    @PUT
    public Response atualizarProduto(@Valid Produto produto) {
        produto = service.atualizaProduto(produto);
        return Response.ok(produto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaProduto(@PathParam("id") Long id) {
        service.deletaProduto(id);
        return Response.noContent().build();
    }
}

