# README

**Demo Video:** https://youtu.be/jlp4nuI1_10

## Store Object Structure:

```json
{
     "id": "6645cb54795ca80e8caba655",
    "image": "http://cdn.example.com/store_image1.jpg",
    "name": "Store Name",
    "location": {
        "lat": -76.512016,
        "lon": 38.29697
    },
    "tags": [
        "Mart", 
        "Grocery", 
        "Supermarket"
    ],
    "promotions": [
        "RM15.00 off item",
        "Discounted items",
        "In-Store Prices"
    ],
    "rating": 3.5
}
```
## Field Description

| Field | Type |
| ------ | ------ |
| image | *String* |
| name | *String* |
| location | *Object* |
| location.lat | *Double* |
| location.lon | *Double* |
| tags | *Array<String>* |
| promotions | *Array<String>* |
| rating | *Double* |

For the **image** field, I suppose the image has been uploaded to a CDN, so I am just saving it's URL.

For **tags** and **promotions** objects, It's recommended to save each of them in a separate document and only reference them by their **ObjectId** for more efficiency and to avoid date duplicates.

For the **rating** field, I am using only a single value to represent a **Total Ratings** for this specific Store item,

But in real-life, rating are more complex than that, there are many different implementations,

For instance, my proposed approach for that would be by creating another collection called **store_ratings**, it shout have Three fields as the following:
```json
{
        "store_id": "6645cb54795ca80e8caba655",
        "rating_value": "",
        "rating_count": ""
}
```

Each Store element would have only five records in the store_ratings document,  noting that the value of the "rating_value" field is [1-5], as the following:
```json
{
        {"store_id": "1", "rating_value": "1", "rating_count":"5"},
        {"store_id": "1", "rating_value": "2", "rating_count":"150"},
        {"store_id": "1", "rating_value": "3", "rating_count":"198"},
        {"store_id": "1", "rating_value": "4", "rating_count":"10"},
        {"store_id": "1", "rating_value": "5", "rating_count":"65"}
}
```
The total rating could be calculated by summing the **Mean** value of ratings as the following Pseudocode:
```
// Formula: SUM (LIST(rating_value x rating_count)) / SUM(rating_count)
totlNumberOfRatings = SUM( 1 * 5 + 2 * 150 + 3 * 198 + 4 * 10 + 5 * 65) / (5 + 150 + 198 + 10 + 65) = 2.9
```

Total Ratings could be also calculated by the Mode, so the total rating would be 3, equivalent to the highest number  "198".


Regarding the **Delivery Cost**, there is no need to save it in the Database, they would be calculated based on many inputs, e.g the customer location, current time, and delivery method, etc.

## Installation

I have developed it on a local MongoDI instance running on a docker container.

```sh
docker pull mongodb/mongodb-community-server:latest
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
```

## API Testing
Swagger is available on: http://localhost:8080/swagger-ui/
