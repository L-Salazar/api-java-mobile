package fiap.com.br.eficientiza.service;

import fiap.com.br.eficientiza.entity.Estacao;
import fiap.com.br.eficientiza.entity.Moto;
import fiap.com.br.eficientiza.exception.ResourceNotFoundException;
import fiap.com.br.eficientiza.repository.EstacaoRepository;
import fiap.com.br.eficientiza.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private EstacaoRepository estacaoRepository;

    public Moto salvar(Moto moto) {
//        Estacao estacao = estacaoRepository.findById(estacaoId)
//                .orElseThrow(() -> new ResourceNotFoundException("Estação com ID " + estacaoId + " não encontrada."));
//        moto.setEstacao(estacao);
        //Long estacaoId = moto.getEstacao().getId();
        return motoRepository.save(moto);
    }

    public List<Moto> listarTodos() {
        return motoRepository.findAll();
    }

    public Moto buscarPorId(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto com ID " + id + " não encontrada."));
    }

    public Moto atualizar(Long id, Moto motoAtualizada) {
        Moto existente = buscarPorId(id);

        existente.setPlaca(motoAtualizada.getPlaca());
        existente.setModelo(motoAtualizada.getModelo());
        existente.setCor(motoAtualizada.getCor());
        existente.setAno(motoAtualizada.getAno());
        existente.setStatus(motoAtualizada.getStatus());
        existente.setVaga(motoAtualizada.getVaga());

//        Long estacaoId = motoAtualizada.getEstacao().getId();
//        Estacao estacao = estacaoRepository.findById(estacaoId)
//                .orElseThrow(() -> new ResourceNotFoundException("Estação com ID " + estacaoId + " não encontrada."));
//        existente.setEstacao(estacao);

        return motoRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Moto com ID " + id + " não encontrada para exclusão.");
        }
        motoRepository.deleteById(id);
    }

    public Moto buscarPorPlaca(String placa){
        return motoRepository.findMotoByPlaca(placa);
    }

    public Moto atualizarStatus(String placa, String status) {
        Moto moto = buscarPorPlaca(placa);
        if (moto == null) {
            throw new ResourceNotFoundException("Moto com placa " + placa + " não encontrada.");
        }
        moto.setStatus(status);
        return motoRepository.save(moto);
    }
}
