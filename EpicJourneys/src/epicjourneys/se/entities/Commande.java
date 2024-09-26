package epicjourneys.se.entities;

public class Commande {
    private int id;
    private int userId;  // Ajout du champ userId
    private int productId;  // Ajout du champ productId
    private String pathFacture;

    public Commande() {
    }

    public Commande(int userId, int productId, String pathFacture) {
        this.userId = userId;
        this.productId = productId;
        this.pathFacture = pathFacture;
    }
   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPathFacture() {
        return pathFacture;
    }

    public void setPathFacture(String pathFacture) {
        this.pathFacture = pathFacture;
    }

   
    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", pathFacture='" + pathFacture + '\'' +
                '}';
    }}