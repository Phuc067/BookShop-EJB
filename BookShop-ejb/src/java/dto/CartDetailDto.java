
package dto;


public class CartDetailDto {
    private int id;
    private String bookId;
    private String name;
    private String type;
    private int quantity;
    private Double price;
    private String img;

    public CartDetailDto() {
    }

    public CartDetailDto(int id, String bookId, String name, String type, int quantity, Double price, String img) {
        this.id = id;
        this.bookId = bookId;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
