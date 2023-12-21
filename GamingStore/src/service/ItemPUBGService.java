/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import Model.itemPUBG;

public interface ItemPUBGService {
    boolean addItem(itemPUBG newItem);
    List<itemPUBG> getAllItems();
    itemPUBG getItemByID(Long itemID);
    boolean updateItem(itemPUBG updatedItem);
    boolean deleteItemByNickname(String nickname);
}
