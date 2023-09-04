package com.joaolucas.shopjj.models.records;

import com.joaolucas.shopjj.models.enums.Role;

public record RegisterRequest(String firstName, String lastName, String email, String password, Role role) {
}
