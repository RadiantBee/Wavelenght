package com.backend.demox.services;

import com.backend.demox.clients.ElementClient;
import com.backend.demox.models.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RemoteElementServiceImpl implements RemoteElementService {

   @Autowired
    private ElementClient elementClient ;

    @Override
    public List<Element> GetRemoteElements() {
        return elementClient.GetElements();
    }

    @Override
    public Optional<Element> GetRemoteOneElement(Long id) {
        return elementClient.GetOneElement(id);
    }
}
