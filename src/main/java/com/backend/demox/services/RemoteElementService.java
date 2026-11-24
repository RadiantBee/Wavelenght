package com.backend.demox.services;

import com.backend.demox.models.Element;

import java.util.List;
import java.util.Optional;

public interface RemoteElementService {
    List<Element> GetRemoteElements ();
    Optional<Element> GetRemoteOneElement (Long id);


}
