package com.williapoletto.springbootkafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williapoletto.springbootkafka.entity.Player;
import com.williapoletto.springbootkafka.entity.Result;
import com.williapoletto.springbootkafka.repository.PlayerRepository;

@Service
public class PlayerService {

	private ProducerService producerService;

	private PlayerRepository playerRepository;

	private Result result;

	@Autowired
	public PlayerService(ProducerService producerService, PlayerRepository playerRepository) {
		this.producerService = producerService;
		this.playerRepository = playerRepository;
	}

	public Result saveMessage(List<Player> players) {

		result = new Result();
		
		for (Player player : players) {
			System.out.println(player);
			if (player != null) {
				
				if (player.getType().equals("expert")) {
					try {
						playerRepository.save(player);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						result.addResult("player " + player.getName() + " stored in DB");
					}
				} else if (player.getType().equals("novice")) {
					try {
						producerService.sendMessageWithCallback(player);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						result.addResult("player " + player.getName() + " sent to Kafka topic");
					}
				} else {
					result.addResult("player " + player.getName() + " did not fit");
				}
				
			} else {
				result.addResult("Invalid player");
			}
		}

		return result;

	}

}
