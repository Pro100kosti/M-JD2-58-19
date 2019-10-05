package by.pvt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDto {

    private long orderId;
    private double price;
    private int count;
    private String comment;
    private Date date;

//    public OrderDto(Long id, Double productPrice, Integer itemCount, String comment, Date createdDate) {
//
//    }

//    public OrderDto(long i, double v, int i1, String some_comments, Date date) {
//
//    }

//    public OrderDto() {
//    }
//
//    public OrderDto(long orderId, double price, int count, String comment, Date date) {
//        this.orderId = orderId;
//        this.price = price;
//        this.count = count;
//        this.comment = comment;
//        this.date = date;
//    }
//
//    public long getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(long orderId) {
//        this.orderId = orderId;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        OrderDto orderDto = (OrderDto) o;
//
//        if (orderId != orderDto.orderId) return false;
//        if (Double.compare(orderDto.price, price) != 0) return false;
//        if (count != orderDto.count) return false;
//        if (comment != null ? !comment.equals(orderDto.comment) : orderDto.comment != null) return false;
//        return date != null ? date.equals(orderDto.date) : orderDto.date == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        result = (int) (orderId ^ (orderId >>> 32));
//        temp = Double.doubleToLongBits(price);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        result = 31 * result + count;
//        result = 31 * result + (comment != null ? comment.hashCode() : 0);
//        result = 31 * result + (date != null ? date.hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "OrderDto{" +
//                "orderId=" + orderId +
//                ", price=" + price +
//                ", count=" + count +
//                ", comment='" + comment + '\'' +
//                ", date=" + date +
//                '}';
//    }
}
