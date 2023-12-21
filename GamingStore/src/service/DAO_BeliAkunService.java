package service;

import Model.BeliAkun;
import java.util.List;

public interface DAO_BeliAkunService {
    // Create operation
    void addBeliakunML(BeliAkun beliakunML);

    // Read operation
    BeliAkun getBeliakunMLByEmail(String email);

    List<BeliAkun> getAllBeliakunMLs();

    // Update operation
    void updateBeliakunML(BeliAkun beliakunML);

    // Delete operation
    void deleteBeliakunMLByEmail(String email);
}
