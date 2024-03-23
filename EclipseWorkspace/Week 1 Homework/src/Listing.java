// A class that represents a real estate listing
class RealEstateListing {
  boolean singleFamily;
  int year;
  int squareFootage;
  int price;
  String city;

  RealEstateListing(boolean singleFamily, int year, int squareFootage, int price, String city) {
    this.singleFamily = singleFamily;
    this.year = year;
    this.squareFootage = squareFootage;
    this.price = price;
    this.city = city;
  }
}

class ExamplesListings {
  RealEstateListing bostonCondo = new RealEstateListing(false, 2010, 700, 350000, "Boston");
  RealEstateListing beachHouse = new RealEstateListing(true, 1995, 2000, 699999, "Yarmouth");
  RealEstateListing bigHouse = new RealEstateListing(true, 2018, 4500, 1000000, "Texas");
}