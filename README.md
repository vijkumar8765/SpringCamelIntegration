
# How to Run

Start the Microservice as a SpringBootApplication.

## Testing through Postman


Success Case:

Request:

curl --location --request POST 'http://localhost:9090/saveEmployee' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1001,
    "age": 23,
    "name": "Testing"
}'

Response:

{
  "id" : 1001,
  "name" : "Testing",
  "age" : 23,
  "address" : "21, Wilson Street",
  "dob" : "01-01-1988"
}





Failure Case:

curl --location --request POST 'http://localhost:9090/saveEmployee' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": null,
    "age": 23,
    "name": "Testing"
}'

Response:

"{\n  \"id\" : null,\n  \"name\" : \"Testing\",\n  \"age\" : 23\n}"

Validtion is not handled properly but a JsonValidation Error is thrown in the logs for id parameter which is set to null and is a required as per the json schema.