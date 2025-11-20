package br.com.projetovenda.venda.security;

import java.time.LocalDate;

import br.com.projetovenda.venda.enums.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.projetovenda.venda.entity.Funcionario;
import br.com.projetovenda.venda.repository.FuncionarioRepository;

@Configuration
public class DataLoader {
    
    @Bean
    public CommandLineRunner initDatabase(FuncionarioRepository funcionarioRepository, 
                                          PasswordEncoder passwordEncoder) {
        return args -> {
            if (funcionarioRepository.findByCpf("00000000000").isEmpty()) {
                
                String senhaCriptografada = passwordEncoder.encode("admin123");
                
                Funcionario admin = new Funcionario();
                
                admin.setCpf("00000000000");
                admin.setNome("Admin Master"); 
                admin.setEmail("admin@loja.com");
                admin.setTelefone("99999999999");
                admin.setSenha(senhaCriptografada);
                admin.setDataAdmissao(LocalDate.now());
                admin.setCargo(Role.ADMINISTRADOR);

                funcionarioRepository.save(admin);
                System.out.println(">>> Usuário ADMINISTRADOR inserido: CPF 00000000000, Senha: admin123");
            }

            if (funcionarioRepository.findByCpf("11111111111").isEmpty()) {
                String senhaVendedorCriptografada = passwordEncoder.encode("venda123");
                
                Funcionario vendedor = new Funcionario();
                vendedor.setCpf("11111111111");
                vendedor.setNome("Vendedor Teste");
                vendedor.setEmail("vendedor@loja.com");
                vendedor.setTelefone("88888888888");
                vendedor.setSenha(senhaVendedorCriptografada);
                vendedor.setDataAdmissao(LocalDate.now());
                vendedor.setCargo(Role.VENDEDOR); 

                funcionarioRepository.save(vendedor);
                System.out.println(">>> Usuário VENDEDOR inserido: CPF 11111111111, Senha: venda123");
            }
        };
    }
}