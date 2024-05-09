package com.example.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	 * entities floating around which screw up Sets and other identity-based collections.
	 */
	@Id
	private UUID id;

	/** */
	private String name;

	/** */
	public Thing(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}
}
