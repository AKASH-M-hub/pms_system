# Placement Management System (PMS)

A comprehensive web-based platform for managing student placements, job postings, and applications. Built with Spring Boot and modern web technologies.

## Features

### For Students
- User registration and authentication
- View available job postings
- Apply to jobs seamlessly
- Track application status
- Manage profile and qualifications

### For Admins
- Register and login with admin credentials
- Post new job openings
- Set eligibility criteria (CGPA requirements, deadlines)
- View all student applications
- Manage placement statistics

### Security
- JWT-based authentication
- Role-based access control (RBAC)
- Password encryption with BCrypt
- Secure API endpoints with authorization filters
- CORS support

## Technology Stack

### Backend
- **Java 21** with Spring Boot 3.x
- **Spring Security** for authentication and authorization
- **Spring Data JPA** for database operations
- **JWT (JSON Web Tokens)** for stateless authentication
- **MySQL/PostgreSQL** for data persistence

### Frontend
- **HTML5** for structure
- **CSS3** for styling
- **JavaScript** for client-side logic
- **Bootstrap** for responsive design

### Build & Deployment
- **Maven** for dependency management and builds
- **Docker** ready for containerization

## Project Structure

```
PMS/
├── src/main/java/com/example/PMS/
│   ├── Controller/           # REST API endpoints
│   ├── Service/              # Business logic layer
│   ├── Repository/           # Data access layer
│   ├── Entity/               # JPA entities
│   ├── DTO/                  # Data transfer objects
│   └── Security/             # JWT and authentication config
├── src/main/resources/
│   ├── static/               # HTML, CSS, JS files
│   └── application.properties # Configuration
└── pom.xml                   # Maven configuration
```

## Architecture

The application follows a **3-layer architecture**:

1. **Controller Layer** - Handles HTTP requests and responses
2. **Service Layer** - Contains business logic and validation
3. **Repository Layer** - Manages database operations

This separation ensures clean code, testability, and maintainability.

## API Endpoints

### Authentication
- `POST /api/auth/login` - Login as student or admin
- `POST /api/auth/register/student` - Register new student
- `POST /api/admin/auth/register` - Register new admin

### Student APIs
- `GET /api/jobs` - View available jobs
- `POST /api/student/apply/{jobId}` - Apply to a job

### Admin APIs
- `POST /api/admin/job` - Create new job posting
- `GET /api/admin/applications` - View all applications

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.6+
- MySQL 8.0+
- Git

### Installation

1. Clone the repository
```bash
git clone https://github.com/AKASH-M-hub/pms_system.git
cd PMS
```

2. Configure database
```properties
# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/pms
spring.datasource.username=root
spring.datasource.password=your_password
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Usage

### Student Flow
1. Visit the application
2. Choose "Register as Student"
3. Create account with email and password
4. Login with credentials
5. View available jobs
6. Click "Apply" to apply for jobs

### Admin Flow
1. Choose "Register as Admin"
2. Create admin account
3. Login with admin credentials
4. Post new job openings
5. View student applications
6. Manage placements

## Authentication

The system uses JWT tokens for authentication:

1. User logs in with email and password
2. Server validates credentials
3. JWT token is generated and returned
4. Client stores token locally
5. Token is sent in Authorization header for subsequent requests

## Security Features

- Passwords are encrypted using BCrypt
- JWT tokens expire after 10 hours
- Role-based access control prevents unauthorized access
- CORS is configured for cross-origin requests
- SQL injection prevention through parameterized queries

## Database Schema

### Students Table
- student_id (Primary Key)
- name
- email (Unique)
- password (Encrypted)
- role
- cgpa
- phone
- created_date

### Admin Table
- admin_id (Primary Key)
- name
- email (Unique)
- password (Encrypted)
- role
- created_date

### Jobs Table
- job_id (Primary Key)
- company_name
- title
- salary
- eligibility_cgpa
- deadline_date
- registration_link
- created_by

### Applications Table
- application_id (Primary Key)
- student_id (Foreign Key)
- job_id (Foreign Key)
- status
- applied_date

## Development

### Code Style
- Follow Java naming conventions
- Use meaningful variable and method names
- One responsibility per class/method
- Proper exception handling

### Testing
Run tests with:
```bash
mvn test
```

### Building for Production
```bash
mvn clean package -DskipTests
```

## Troubleshooting

### Common Issues

**Port 8080 already in use**
```bash
# Change port in application.properties
server.port=8081
```

**Database connection error**
- Verify MySQL is running
- Check credentials in application.properties
- Ensure database exists

**JWT token invalid**
- Token may have expired (10-hour limit)
- Re-login to get new token
- Clear browser cache and localStorage

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Contact & Support

For issues, questions, or contributions:
- GitHub: [AKASH-M-hub/pms_system](https://github.com/AKASH-M-hub/pms_system)
- Create an issue in the repository

## Changelog

### Version 1.0.0
- Initial release
- Student registration and authentication
- Admin portal for job management
- Application tracking system
- JWT-based security
- Service layer architecture

## Future Enhancements

- Email notifications for application status
- Advanced search and filtering
- Interview scheduling system
- Analytics and reporting dashboard
- Multiple file upload support
- Two-factor authentication
- Payment integration
- Mobile application

---

**Last Updated:** February 2026
**Maintained by:** Development Team
