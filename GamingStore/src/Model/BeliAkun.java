
package Model;
import java.util.Objects;

public class BeliAkun {
    private String nick;
    private String harga;
    private String ppn;
    private String income;
    private String email;

    public BeliAkun (String nick, String harga, String ppn, String income, String email) {
        this.nick = nick;
        this.harga = harga;
        this.ppn = ppn;
        this.income = income;
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPpn() {
        return ppn;
    }

    public void setPpn(String ppn) {
        this.ppn = ppn;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}