package com.example.astronauts;

import java.util.List;

public interface SpaceLaunchNowClient {
    List<Astronaut> listAstronauts();

    Astronaut lookupAstronautByID(String id);
}
