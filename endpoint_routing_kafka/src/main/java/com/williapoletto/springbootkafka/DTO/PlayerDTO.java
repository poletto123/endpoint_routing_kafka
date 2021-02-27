package com.williapoletto.springbootkafka.DTO;

import java.util.List;

import com.williapoletto.springbootkafka.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerDTO {

	private List<Player> players;
	
}
