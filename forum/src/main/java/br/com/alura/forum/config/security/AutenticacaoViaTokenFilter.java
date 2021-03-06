package br.com.alura.forum.config.security;

import org.springframework.boot.SpringBootVersion;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter
{
    private TokenService tokenService;

    //construtor
    public AutenticacaoViaTokenFilter(TokenService tokenService)
    {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        //recuperar o token
        String token = recuperarToken(request);

        //validar o token
        boolean valido = tokenService.isTokenValido(token);
        System.out.println(valido);

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request)
    {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer "))
        {
            return null;
        }

        return token.substring(7, token.length());
    }
}
