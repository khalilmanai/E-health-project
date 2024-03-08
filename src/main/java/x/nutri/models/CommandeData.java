package x.nutri.models;

import java.time.LocalDateTime;

public class CartData {
    private int id;
    private  int cart_code;
    private Double total;
    private LocalDateTime date;

    public CartData(int id, int cart_code, Double total, LocalDateTime date) {
        this.id = id;
        this.cart_code = cart_code;
        this.total = total;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCart_code() {
        return cart_code;
    }

    public void setCart_code(int cart_code) {
        this.cart_code = cart_code;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CartData{" +
                "id=" + id +
                ", cart_code=" + cart_code +
                ", total=" + total +
                ", date=" + date +
                '}';
    }
}
