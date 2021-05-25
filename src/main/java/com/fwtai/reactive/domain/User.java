package com.fwtai.reactive.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Table("product")
public final class User{

    @Id
    private Long id;

    @NotBlank(message="名称不能为空")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column("created_at")
    private LocalDateTime created;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public LocalDateTime getCreated(){
        return created;
    }

    public void setCreated(LocalDateTime created){
        this.created = created;
    }

    public User(Long id,@NotBlank(message = "名称不能为空") String name,LocalDateTime created){
        this.id = id;
        this.name = name;
        this.created = created;
    }
}