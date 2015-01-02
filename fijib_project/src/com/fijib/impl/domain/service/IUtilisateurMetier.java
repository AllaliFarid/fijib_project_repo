package com.fijib.impl.domain.service;

import java.util.List;

import com.fijib.impl.persistence.entity.Utilisateur;

public interface IUtilisateurMetier {
    public void addUser(Utilisateur utilisateur);
    public List<Utilisateur> getAll();
    public Utilisateur findUserByName(String userName);
    public Utilisateur findUser(String cin);
}
