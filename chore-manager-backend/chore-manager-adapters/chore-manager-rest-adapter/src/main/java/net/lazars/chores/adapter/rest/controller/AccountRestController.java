package net.lazars.chores.adapter.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestController {

  @PostMapping("login")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void login() {
    // The session was created by accessing this endpoint
  }
}
