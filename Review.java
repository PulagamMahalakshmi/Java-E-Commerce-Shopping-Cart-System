public class Review {

    int id;
    String productName;
    int rating;
    String review;

    public Review(int id,
                  String productName,
                  int rating,
                  String review) {

        this.id = id;
        this.productName = productName;
        this.rating = rating;
        this.review = review;
    }
}