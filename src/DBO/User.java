package DBO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class User implements Serializable {
    private String name, mail, pass, ip;
    private int coin, bet;
    private Timestamp blocktime;

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws Exception {
        if(name == null || name.equals(""))
            throw new Exception("Invalid name!");
        this.name = name;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) throws Exception {
        if(mail == null || mail.equals(""))
            throw new Exception("Invalid email!");
        this.mail = mail;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) throws Exception {
        if(pass == null || pass.equals(""))
            throw new Exception("Invalid password!");
        this.pass = pass;
    }
    
    public int getCoin() {
        return this.coin;
    }
    
    public void setCoin(int coin) {
        this.coin = coin;
    }
    
    public Timestamp getBlocktime() {
        return this.blocktime;
    }
    
    public void setBlocktime(Timestamp blocktime) {
        this.blocktime = blocktime;
    }
    
    public boolean isBlocked() {
        if(this.blocktime == null) {
            return false;
        }
        return this.blocktime.getTime()/1000 + 1200 >= System.currentTimeMillis()/1000;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.mail);
        hash = 37 * hash + Objects.hashCode(this.pass);
        hash = 37 * hash + Objects.hashCode(this.ip);
        hash = 37 * hash + this.coin;
        hash = 37 * hash + this.bet;
        hash = 37 * hash + Objects.hashCode(this.blocktime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.coin != other.coin) {
            return false;
        }
        if (this.bet != other.bet) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.pass, other.pass)) {
            return false;
        }
        if (!Objects.equals(this.ip, other.ip)) {
            return false;
        }
        if (!Objects.equals(this.blocktime, other.blocktime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", mail=" + mail + ", pass=" + pass + ", ip=" + ip + ", coin=" + coin + ", bet=" + bet + ", blocktime=" + blocktime + '}';
    }
}
