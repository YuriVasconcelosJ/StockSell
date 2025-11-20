// package br.com.projetovenda.venda.service;

// import java.time.Instant;
// import java.time.ZoneId;
// import java.time.ZonedDateTime;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.auth0.jwt.JWT;
// import com.auth0.jwt.algorithms.Algorithm;
// import com.auth0.jwt.exceptions.JWTCreationException;
// import com.auth0.jwt.exceptions.JWTVerificationException;

// import br.com.projetovenda.venda.entity.Funcionario;

// @Service
// public class TokenService {

//     @Value("${api.security.token.secret}")
//     private String secret;

//     // Método para gerar o token JWT
//     public String gerarToken(Funcionario funcionario) {
//         try {
//             // Usa o algoritmo HMAC256 para assinar o token
//             Algorithm algorithm = Algorithm.HMAC256(secret);

//             // Constrói o token:
//             return JWT.create()
//                     .withIssuer("venda-api") // Identificador do emissor (sua API)
//                     .withSubject(funcionario.getCpf()) // Usuário que está logando
//                     .withExpiresAt(dataExpiracao()) // Define o tempo de expiração
//                     .sign(algorithm); // Assina
//         } catch (JWTCreationException exception) {
//             throw new RuntimeException("Erro ao gerar token JWT", exception);
//         }
//     }

//     // Método para validar e obter o 'subject' (CPF) do token
//     public String getSubject(String tokenJWT) {
//         try {
//             Algorithm algorithm = Algorithm.HMAC256(secret);

//             return JWT.require(algorithm)
//                     .withIssuer("venda-api")
//                     .build()
//                     .verify(tokenJWT) // Tenta verificar o token
//                     .getSubject(); // Retorna o CPF
//         } catch (JWTVerificationException exception) {
//             // Se o token for inválido (expirado, modificado, etc.)
//             return null;
//         }
//     }

//     // Define que o token expira em 2 horas
//     private Instant dataExpiracao() {
//         return ZonedDateTime.now(ZoneId.of("-03:00")).plusHours(2).toInstant();
//     }
// }
