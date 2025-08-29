Dropbox Business API - Java program to implement Authentication and fetch data to get the name of the team/organization.

The application uses the App Key, App Secret, and Refresh Token to dynamically generate a short-lived Access Token, 
calls the Dropbox team/get_info API to retrieve team details, and finally prints the team/organization name on the console.

DropboxClient.java :
1. Handles refresh token exchange.
2. Uses RestTemplate to call Dropbox OAuth endpoint: POST https://api.dropboxapi.com/oauth2/token
3. Returns new access token.

DropboxBusinessApiApplication.java :
1. Fetches access token via DropboxClient.
2. Calls Dropbox team/get_info API.
3. Prints team name.

Console Output : Team/Organization Name: Shrinidhiupadhyaya12
