## *CA2 Group Project*
- *This project is the start code for project CA2 for Students @ B-Class*
## *How to use this Project*
### *Backend:*
Must provide an initial setup with entity classes for users and roles
- The backend must provide a JWT-based authentication/authorization mechanism.
- The backend must provide unit and integration tests, and must be “testable” by GitHub Actions
- The backend must be deployable using a single maven command
- Passwords must be protected using a hash/salt-strategy
- The backend must provide (at least) the following Dummy REST-endpoints:
- An endpoint that requires the user-role
- An endpoint that requires the admin-role
- An endpoint that fetches data from a minimum of five remote servers ((find a free endpoint as you like, or make one by yourself) similar to the free API's used by start code) (see hints and requirements at the end of this document)

### *WEB-Client:*
- Must be implemented as a Single Page Application with React and provide:
- A login/logout option
- It must render the username, and if used, role(s), for a logged-in user (in any way you like)
- A React Router Based setup with initial pages/routes that renders data fetched from the three endpoints mentioned above and a welcome page with initial instructions on how to use the Quick Start Project.
- Must be styled (use bootstrap unless you have knowledge from somewhere else) to be immediately “presentable”
- URLs used by the client-projects must be read from a file settings.js



