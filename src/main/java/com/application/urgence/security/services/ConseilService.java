package com.application.urgence.security.services;

import com.application.urgence.message.Message;
import com.application.urgence.models.Conseil;


import java.util.List;
public interface ConseilService {
    Conseil creer(Conseil conseil);
    List<Conseil> liste();
    Conseil modifier(Long id,Conseil conseil);
    String supprimer(Long id);
}
