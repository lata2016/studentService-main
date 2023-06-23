package com.project.schoolService.model;

import jakarta.persistence.*;
import lombok.Builder;

import static jakarta.persistence.GenerationType.IDENTITY;
@Builder
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer tokenId;
    private String token;
    @Column(name = "user_id")
    private Long id;
    private boolean revoked;

    public Token() {
    }

    public Token(Integer tokenId, String token, Long id, boolean revoked) {
        this.tokenId = tokenId;
        this.token = token;
        this.id = id;
        this.revoked = revoked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", id=" + id +
                ", revoked=" + revoked +
                '}';
    }
}