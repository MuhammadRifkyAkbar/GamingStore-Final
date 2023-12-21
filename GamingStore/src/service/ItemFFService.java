package service;

import java.util.List;
import Model.itemFF;

public interface ItemFFService {
    boolean addItem(itemFF newItem);
    List<itemFF> getAllItems();
    itemFF getItemByID(Long itemID);
    boolean updateItem(itemFF updatedItem);
    boolean deleteItemByNickname(String nickname);

}
