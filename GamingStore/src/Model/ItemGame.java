package Model;

/**
 * ItemGame class represents a game item.
 * Author: Akbar
 */
public class ItemGame {
    private String nama_game;
    private Long harga_game;
    private Long IDGame;

    public String getNama_game() {
        return nama_game;
    }

    public void setNama_game(String nama_game) {
        this.nama_game = nama_game;
    }

    public Long getHarga_game() {
        return harga_game;
    }

    public void setHarga_game(Long harga_game) {
        this.harga_game = harga_game;
    }

    public Long getIDGame() {
        return IDGame;
    }

    public void setIDGame(Long IDGame) {
        this.IDGame = IDGame;
    }
}
