package com.backend.wavelenght.services;

import com.backend.wavelenght.models.Element;

import java.util.List;
import java.util.Optional;

public interface RemoteElementService {
    List<Element> GetRemoteElements ();
    Optional<Element> GetRemoteOneElement (Long id);


}
