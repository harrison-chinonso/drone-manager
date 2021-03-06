package chinonsoharrison.repository;

import chinonsoharrison.entity.Drone;
import chinonsoharrison.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository< Drone, Long> {
    List<Drone> findAllByStateEquals(State state);
}
