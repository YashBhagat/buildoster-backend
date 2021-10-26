package com.buildoster.repository;

import com.buildoster.model.PasswordResetToket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToket,Long> {
}
