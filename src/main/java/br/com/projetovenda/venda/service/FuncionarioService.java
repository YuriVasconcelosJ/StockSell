package br.com.projetovenda.venda.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovenda.venda.dto.funcionario.FuncionarioCreateDTO;
import br.com.projetovenda.venda.dto.funcionario.FuncionarioResponseDTO;
import br.com.projetovenda.venda.dto.funcionario.FuncionarioUpdateDTO;
import br.com.projetovenda.venda.entity.Funcionario;
import br.com.projetovenda.venda.exception.ResourceConflictException;
import br.com.projetovenda.venda.exception.ResourceNotFoundException;
import br.com.projetovenda.venda.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 
     * @param funcionarioCreateDTO
     * @return
     */
    public FuncionarioResponseDTO cadastrar(FuncionarioCreateDTO funcionarioCreateDTO) {
        if (funcionarioRepository.existsByEmail(funcionarioCreateDTO.getEmail())) {
            throw new ResourceConflictException(
                    "Email já cadastrado em nosso sistema: " + funcionarioCreateDTO.getEmail());
        }

        if (funcionarioRepository.existsByCpf(funcionarioCreateDTO.getCpf())) {
            throw new ResourceConflictException("CPF já cadastrado em nosso sistema: " + funcionarioCreateDTO.getCpf());
        }

        String senhaCriptografada = passwordEncoder.encode(funcionarioCreateDTO.getSenha());

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioCreateDTO.getNome());
        funcionario.setCpf(funcionarioCreateDTO.getCpf());
        funcionario.setEmail(funcionarioCreateDTO.getEmail());
        funcionario.setTelefone(funcionarioCreateDTO.getTelefone());
        funcionario.setSenha(senhaCriptografada);
        funcionario.setDataAdmissao(LocalDate.now());
        funcionario.setCargo(funcionarioCreateDTO.getCargo());

        Funcionario salvo = funcionarioRepository.save(funcionario);

        return new FuncionarioResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getCpf(),
                salvo.getEmail(),
                salvo.getTelefone(),
                salvo.getDataAdmissao(),
                salvo.getCargo());
    }

    /**
     * 
     * @return
     */
    public List<FuncionarioResponseDTO> listarTodos() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(func -> new FuncionarioResponseDTO(func.getId(), func.getNome(), func.getCpf(),
                        func.getEmail(), func.getTelefone(), func.getDataAdmissao(), func.getCargo()))
                .toList();
    }

    @Transactional
    public FuncionarioResponseDTO atualizarDados(Long id, FuncionarioUpdateDTO funcionarioUpdate) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar o usuario com o id: " + id));
    if (funcionarioUpdate.getNome() != null && !funcionarioUpdate.getNome().isBlank()) {
        funcionario.setNome(funcionarioUpdate.getNome().trim());
    }

    if (funcionarioUpdate.getEmail() != null && !funcionarioUpdate.getEmail().isBlank()) {
        String novoEmail = funcionarioUpdate.getEmail().trim();
        if (!novoEmail.equalsIgnoreCase(funcionario.getEmail())) {
            if (funcionarioRepository.existsByEmail(novoEmail)) {
                throw new ResourceConflictException("E-mail já está em uso por outro funcionário.");
            }
            funcionario.setEmail(novoEmail);
        }
    }
    if (funcionarioUpdate.getTelefone() != null && !funcionarioUpdate.getTelefone().isBlank()) {
        funcionario.setTelefone(funcionarioUpdate.getTelefone().trim());
    }
    if (funcionarioUpdate.getCargo() != null) {
        funcionario.setCargo(funcionarioUpdate.getCargo());
    }

        Funcionario funcionarioSave = funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDTO(
            funcionarioSave.getId(),
            funcionarioSave.getNome(),
            funcionarioSave.getCpf(),
            funcionarioSave.getEmail(),
            funcionarioSave.getTelefone(),
            funcionarioSave.getDataAdmissao(),
            funcionarioSave.getCargo());
    }

    /**
     * 
     */
    public void deletarFuncionario(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado: " + id));
        funcionarioRepository.delete(funcionario);
    }

}
