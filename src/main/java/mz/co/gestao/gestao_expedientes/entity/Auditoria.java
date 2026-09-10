package mz.co.gestao.gestao_expedientes.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "utilizador_id", nullable = false)
    private Long utilizadorId;
    @Column(nullable = false)
    private String accao;
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private LocalDateTime data;

    public Auditoria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(Long utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    public String getAccao() {
        return accao;
    }

    public void setAccao(String accao) {
        this.accao = accao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
