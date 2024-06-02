# RamenGo API

Welcome to the RamenGo API! This project is a RESTful API built with Java 21, Spring Boot, Gradle, and H2 Database. The purpose of this API is to provide endpoints for listing available broths and proteins, and to allow users to place orders for their custom ramen dishes.

## API Host and Authentication

The RamenGo API is hosted on the Render platform at [https://ramengo-api-atqt.onrender.com](https://ramengo-api-atqt.onrender.com). Please note that it is currently using a free plan, which may result in latency in API responses. To access the API endpoints, you need to include the API key in the request header:

- **Header:** `x-api-key`
- **Value:** `5a7f8b94e1c044b2b4f89996a38a2d73`

## Table of Contents

- [API Documentation](#api-documentation)
  - [List Broths](#list-broths)
  - [List Proteins](#list-proteins)
  - [Place Order](#place-order)
- [Error Handling](#error-handling)

## API Documentation

The RamenGo API provides the following endpoints:

### List Broths

- **Endpoint:** `GET /broths`
- **Description:** Retrieves a list of available broths.
- **Headers:**
  - `x-api-key` (required): `5a7f8b94e1c044b2b4f89996a38a2d73`
- **Response:**
  - **200 OK**
    ```json
    [
      {
        "id": "1",
        "imageInactive": "https://tech.redventures.com.br/icons/salt/inactive.svg",
        "imageActive": "https://tech.redventures.com.br/icons/salt/active.svg",
        "name": "Salt",
        "description": "Simple like the seawater, nothing more",
        "price": 10
      }
    ]
    ```
  - **403 Forbidden**
    ```json
    {
      "error": "x-api-key header missing"
    }
    ```

### List Proteins

- **Endpoint:** `GET /proteins`
- **Description:** Retrieves a list of available proteins.
- **Headers:**
  - `x-api-key` (required): `5a7f8b94e1c044b2b4f89996a38a2d73`
- **Response:**
  - **200 OK**
    ```json
    [
      {
        "id": "1",
        "imageInactive": "https://tech.redventures.com.br/icons/pork/inactive.svg",
        "imageActive": "https://tech.redventures.com.br/icons/pork/active.svg",
        "name": "Chasu",
        "description": "A sliced flavourful pork meat with a selection of season vegetables.",
        "price": 10
      }
    ]
    ```
  - **403 Forbidden**
    ```json
    {
      "error": "x-api-key header missing"
    }
    ```

### Place Order

- **Endpoint:** `POST /orders`
- **Description:** Places a new order based on user selection.
- **Headers:**
  - `x-api-key` (required): `5a7f8b94e1c044b2b4f89996a38a2d73`
- **Request Body:**
    ```json
    {
      "brothId": "1",
      "proteinId": "1"
    }
    ```
- **Response:**
  - **201 Created**
    ```json
    {
      "id": "12345",
      "description": "Salt and Chasu Ramen",
      "image": "https://tech.redventures.com.br/icons/ramen/ramenChasu.png"
    }
    ```
  - **400 Bad Request**
    ```json
    {
      "error": "both brothId and proteinId are required"
    }
    ```
  - **403 Forbidden**
    ```json
    {
      "error": "x-api-key header missing"
    }
    ```
  - **500 Internal Server Error**
    ```json
    {
      "error": "could not place order"
    }
    ```

## Error Handling

The API uses standard HTTP status codes to indicate the success or failure of an API request. Error responses include a descriptive error message to help identify the issue.

- **400 Bad Request:** The request could not be understood or was missing required parameters.
- **403 Forbidden:** Authentication failed or user does not have permissions for the requested operation.
- **500 Internal Server Error:** An error occurred on the server.

## License

This project is licensed under the MIT License.

## Contact

For any inquiries or issues, please contact [iurinhg2@gmail.com](mailto:iurinhg2@gmail.com).
