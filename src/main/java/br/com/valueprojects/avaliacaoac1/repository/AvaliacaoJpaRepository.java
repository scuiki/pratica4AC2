package br.com.valueprojects.avaliacaoac1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoEntity, Long> {
}