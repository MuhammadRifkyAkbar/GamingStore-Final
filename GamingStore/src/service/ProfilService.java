/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import Model.profile;
import java.util.List;

public interface ProfilService {

    // Menambahkan profil baru ke database
    boolean addProfile(profile newProfile);

    // Mengambil profil berdasarkan username
    profile getProfileByUsername(String username);

    // Mengambil semua profil dari database
    List<profile> getAllProfiles();

    // Memperbarui informasi profil
    boolean updateProfile(profile updatedProfile);

    // Menghapus profil berdasarkan username
    boolean deleteProfileByUsername(String username);
}
