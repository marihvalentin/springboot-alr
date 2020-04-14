package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService
{
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication)
    {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        //expiração = data hoje + o tempo de expiração que está como propriedade no application.properties
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do Fórum da Alura") //Quem está gerando o token?
                .setSubject(logado.getId().toString()) //Quem é o usuário autenticado dono do token?
                .setIssuedAt(hoje) //Data de geração do token
                .setExpiration(dataExpiracao) //Data de expiração do token
                //Algoritmo de cripto + senha da aplicação usada p/ fazer a assinatura e gerar o REST da cripto do token
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact(); //compactar o retorno em uma string

    }

    public boolean isTokenValido(String token)
    {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
