package com.williapoletto.springbootkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.williapoletto.springbootkafka.DTO.PlayerDTO;
import com.williapoletto.springbootkafka.entity.Result;
import com.williapoletto.springbootkafka.service.PlayerService;

@RestController
public class MessageController {

	private PlayerService playerService;
	
	@Autowired
	 public MessageController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@PostMapping("/sendMessage")
	 public  ResponseEntity<Result> saveMessage(@RequestBody PlayerDTO players) {
		
		Result result = playerService.saveMessage(players.getPlayers());
		
		if (result != null) {
			return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Result>(result, HttpStatus.CREATED);
		 
	 }
	
}
