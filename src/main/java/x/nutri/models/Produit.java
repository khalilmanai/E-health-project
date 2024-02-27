package x.nutri.models;
public class Produit {
    private int Id_produit,Prix,quantite;
    private String productName,description, imgSrc,Color;

    public Produit( int prix, String productName, String description,String imgSrc,String color,int quantite ) {

        Prix = prix;
        this.productName = productName;
        this.description = description;
        this.imgSrc=imgSrc;
        this.Color=color;
        this.quantite=quantite;
    }

    public Produit(String productName , int prix , int qteProduit) {
        this.productName = productName;
        this.Prix=prix;
        this.quantite = qteProduit;
    }
public Produit (){

}



    public int getId_produit() {
        return Id_produit;
    }

    public void setId_produit(int id_produit) {
        Id_produit = id_produit;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int prix) {
        Prix = prix;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "Id_produit=" + Id_produit +
                ", Prix=" + Prix +
                ", quantite=" + quantite +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", Color='" + Color + '\'' +
                '}';
    }
}

