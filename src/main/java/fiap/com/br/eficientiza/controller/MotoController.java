package fiap.com.br.eficientiza.controller;

import fiap.com.br.eficientiza.dto.MotoDtoResponse;
import fiap.com.br.eficientiza.entity.Moto;
import fiap.com.br.eficientiza.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @PostMapping
    public Moto criar(@RequestBody Moto moto) {
        return motoService.salvar(moto);
    }

    @GetMapping
    public List<Moto> listar() {
        return motoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Moto buscarPorId(@PathVariable Long id) {
        return motoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Moto atualizar(@PathVariable Long id, @RequestBody Moto moto) {
        return motoService.atualizar(id, moto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        motoService.deletar(id);
    }

    @PostMapping("/buscarPorPlaca")
    public ResponseEntity<?> buscarPorPlaca(@RequestBody String placa) {
        Moto moto = motoService.buscarPorPlaca(placa);
        if (moto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @PatchMapping("/{placa}/atualizarStatus")
    public Moto atualizarStatus(@PathVariable String placa, @RequestBody MotoDtoResponse data) {
        return motoService.atualizarStatus(placa, data.status);
    }


}
