/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import Model.itemML;
import java.util.List;
/**
 *
 * @author Akbar
 */
public interface servBarangML {
    void addItemML(itemML ItemML);

    void updateItemML(itemML ItemML);

    void deleteItemML(Long id);

    itemML getItemMLById(Long id);

    List<itemML> getAllItemMLs();

    
}
