package br.com.itau.pix.controller;

import br.com.itau.pix.controller.model.Data;
import br.com.itau.pix.controller.model.KeyDTO;
import br.com.itau.pix.domain.exception.InvalidKeyException;
import br.com.itau.pix.domain.exception.KeyNotFoundException;
import br.com.itau.pix.domain.model.KeyType;
import br.com.itau.pix.domain.port.PixPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pix/key")
public class PixController {

    @Autowired
    private PixPort pixPort;

    @PostMapping()
    public ResponseEntity<?> insertKey(@RequestBody KeyDTO keyDTO) {
        try {
            return ResponseEntity.ok().body(Data.builder().data(pixPort.createKey(keyDTO)));
        } catch (Exception e) {
            return ResponseEntity.status(422).body(Data.builder().data("Invalid input!"));
        }
    }

    @GetMapping("/id")
    public ResponseEntity<?> getKeyById(@RequestParam(value = "keyId") UUID keyId) {
        try {
            return ResponseEntity.ok().body(Data.builder().data(pixPort.findKeyById(keyId)));
        } catch (KeyNotFoundException e) {
            return ResponseEntity.status(404).body("Key not found");
        }
    }

    @GetMapping("/keyType")
    public ResponseEntity<?> getKeyByKeyType(@RequestParam(value = "keyType") KeyType keyType) {
        final List<KeyDTO> keyByKeyType = pixPort.findKeyByKeyType(keyType);
        if (keyByKeyType.isEmpty()) {
            return ResponseEntity.status(404).body("Key not found");
        }
        return ResponseEntity.ok().body(Data.builder().data(keyByKeyType));
    }

    @GetMapping("/branchAccount")
    public ResponseEntity<?> getKeyByBranchAndAccount(@RequestParam(value = "branch") int branch,
                                                      @RequestParam(value = "account") int account) {
        final List<KeyDTO> keyByKeyType = pixPort.findKeyByBranchAndAccount(branch, account);
        if (keyByKeyType.isEmpty()) {
            return ResponseEntity.status(404).body("Key not found");
        }
        return ResponseEntity.ok().body(Data.builder().data(keyByKeyType));
    }

    @GetMapping("/clientName")
    public ResponseEntity<?> getKeyByClientName(@RequestParam(value = "branch") String clientName,
                                                @RequestParam(value = "account") String clientLastName) {
        final List<KeyDTO> keyByKeyType = pixPort.findKeyByClientName(clientName, clientLastName);
        if (keyByKeyType.isEmpty()) {
            return ResponseEntity.status(404).body("Key not found");
        }
        return ResponseEntity.ok().body(Data.builder().data(keyByKeyType));
    }

    @GetMapping("/createDate")
    public ResponseEntity<?> getKeyByCreateDate(@RequestParam(value = "createDate") LocalDateTime createDate) {
        final List<KeyDTO> keyByKeyType = pixPort.findKeyByCreateDate(createDate);
        if (keyByKeyType.isEmpty()) {
            return ResponseEntity.status(404).body("Key not found");
        }
        return ResponseEntity.ok().body(Data.builder().data(keyByKeyType));
    }

    @GetMapping("/deactivationDate")
    public ResponseEntity<?> getKeyByDeactivationDate(@RequestParam(value = "deacrtivateDate") LocalDateTime deacrtivateDate) {
        final List<KeyDTO> keyByKeyType = pixPort.findKeyByDeactivateDate(deacrtivateDate);
        if (keyByKeyType.isEmpty()) {
            return ResponseEntity.status(404).body("Key not found");
        }
        return ResponseEntity.ok().body(Data.builder().data(keyByKeyType));
    }

    @PutMapping()
    public ResponseEntity<?> updateKey(@RequestBody KeyDTO keyDTO) {
        try {
            return ResponseEntity.ok().body(Data.builder().data(pixPort.updateKey(keyDTO)));
        } catch (KeyNotFoundException e) {
            return ResponseEntity.status(404).body("Key not found");
        } catch (Exception e) {
            return ResponseEntity.status(422).body("Invalid input");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKey(@PathVariable(value = "id") UUID id) {
        try {
            return ResponseEntity.ok().body(Data.builder().data(pixPort.deactivateKey(id)));
        } catch (InvalidKeyException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
