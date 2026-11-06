package com.lawlayui.clinic.account.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.clinic.account.application.port.in.UserDto;
import com.lawlayui.clinic.account.application.services.command.RenameByEmailUseCase;
import com.lawlayui.clinic.account.application.services.query.GetUserByEmailUseCase;
import com.lawlayui.clinic.account.infrastructure.web.requestDto.UserRenameRequest;

@RestController 
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private GetUserByEmailUseCase getUserByEmailUseCase;
    private RenameByEmailUseCase renameByEmailUseCase;

    public AccountController(GetUserByEmailUseCase getUserByEmailUseCase, RenameByEmailUseCase renameByEmailUseCase) {
        this.getUserByEmailUseCase = getUserByEmailUseCase;
        this.renameByEmailUseCase = renameByEmailUseCase;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        UserDto body = getUserByEmailUseCase.getUserByEmail(email);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{email}")
    public ResponseEntity<String> renameByEmail(@PathVariable String email, @RequestBody UserRenameRequest userRenameRequest) {
        renameByEmailUseCase.renameByEmail(email, userRenameRequest.newName());
        return ResponseEntity.ok("Succes rename account.");
    }
}
