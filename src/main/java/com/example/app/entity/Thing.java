package com.example.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Some random JPA entity
 */
@Entity
@Data
@NoArgsConstructor
public class Thing {

	/**
	 * PSA: It's good practice to initialize ids immediately, that way you never have unidentifiable
	 * entities floating around which screw up Sets and other identity-based collections. Sequences,
	 * identity columns, and other db-centric id generation strategies are a bad idea.
	 */
	@Id
	private UUID id = UUID.randomUUID();

	/** */
	private String name;

	/** */
	public Thing(String name) {
		this.name = name;
	}
}
