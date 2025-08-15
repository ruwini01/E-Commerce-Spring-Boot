# E-Commerce-Spring-Boot
An E-Commerce website using Spring Boot, React js, MySQL

---
## 📩 Endpoint: Send Login/Signup OTP

This endpoint is designed to send a One-Time Password (OTP) to the user's email address for the purpose of logging in or signing up. It is a crucial step in the authentication process, ensuring that the user has access to the specified email account.

### Request

- **Method**: POST
    
- **URL**: `http://localhost:5454/auth/send/login-signup-otp`
    

#### Request Body

The request must include a JSON object in the body with the following structure:

- **email** (string): The email address to which the OTP will be sent. This is a required field.
    

**Example Request Body**:

``` json
{
  "email": "user@example.com"
}

 ```

### Response

Upon successful processing of the request, the server will respond with a JSON object. The response structure is as follows:

- **message** (string): A message indicating the status of the OTP request. The content of this field may vary based on the server's implementation.
    

**Example Response**:

``` json
{
  "message": "OTP sent successfully"
}

 ```

### Notes

- Ensure that the email provided is valid and accessible by the user.
    
- The response may contain additional information or error messages based on the outcome of the request.



---

## 📩Endpoint: Sign Up Endpoint

This endpoint allows users to create a new account by providing their details. It is a part of the authentication process for the application.

### Request

**Method:** POST  
**URL:** `http://localhost:5454/auth/signup`

#### Request Body

The request body should be in JSON format and include the following parameters:

- **fullName** (string): The full name of the user signing up.
    
- **email** (string): The email address of the user. This will be used for account verification and communication.
    
- **otp** (string): A one-time password that is typically sent to the user's email or phone for verification purposes.
    

**Example Request Body:**

``` json
{
  "fullName": "John Doe",
  "email": "john.doe@example.com",
  "otp": "123456"
}

 ```

### Response

Upon a successful sign-up, the server will respond with a JSON object containing the following fields:

- **jwt** (string): A JSON Web Token that can be used for authenticated requests.
    
- **message** (string): A message indicating the result of the sign-up operation.
    
- **role** (string): The role assigned to the user upon successful registration.
    

**Example Response:**

``` json
{
  "jwt": "your_jwt_token_here",
  "message": "Sign up successful!",
  "role": "user"
}

 ```

### Notes

- Ensure that the email provided is valid and not already in use.
    
- The OTP must be verified correctly for the sign-up to be successful.
    
- A successful response will return a JWT that should be stored securely for future authenticated requests.
