/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import Model.ItemGame;

public interface ItemGameService {
    boolean addItemGame(ItemGame newItemGame);
    List<ItemGame> getAllItemGames();
    ItemGame getItemGameByID(int itemGameID);
    boolean updateItemGame(ItemGame updatedItemGame);
    boolean deleteItemGameByID(int itemGameID);
}