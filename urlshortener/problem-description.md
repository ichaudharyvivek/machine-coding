# URL Shortener Service

## Problem Statement

Design an in-memory URL shortening service that generates, manages, and tracks shortened URLs with analytics capabilities.

### Requirements

- The service will be in-memory and does not require access to any external database.
- Each shortened URL must have a Time-to-Live (TTL) feature.
- The service must automatically clean up expired URLs.
- All operations should aim for O(1) or O(log n) time complexity.

### Core Features

The URL shortener should expose the following functionalities:

1. **URL Shortening**
   - Generate unique aliases for long URLs
   - Support custom aliases (user-provided)
   - Ensure new shortened URL for each request of same long URL
   - Support configurable TTL (default: 120 seconds)

2. **URL Redirection**
   - Redirect users to original URLs using aliases
   - Handle expired or non-existent aliases

3. **Analytics Tracking**
   - Track number of visits per shortened URL
   - Store last 10 access timestamps
   - Provide analytics data on demand

4. **URL Management**
   - Update existing URLs (custom alias, TTL)
   - Delete shortened URLs
   - Automatic cleanup of expired URLs

### API Endpoints

1. **Create Shortened URL**
   - Endpoint: `POST /shorten`
   - Request Body:
     ```json
     {
       "long_url": "https://www.example.com/some/very/long/url",
       "custom_alias": "myalias",    // Optional
       "ttl_seconds": 60             // Optional
     }
     ```
   - Response:
     ```json
     {
       "short_url": "http://localhost:<port>/myalias"
     }
     ```

2. **Access Original URL**
   - Endpoint: `GET /:alias`
   - Response:
     - 302: Redirect to original URL
     - 404: Alias not found or expired

3. **Get URL Analytics**
   - Endpoint: `GET /analytics/:alias`
   - Response:
     ```json
     {
       "alias": "myalias",
       "long_url": "https://www.example.com/some/very/long/url",
       "access_count": 150,
       "access_times": [
         "2024-07-21T12:34:56Z",
         "2024-07-21T12:35:56Z",
         // ... up to 10 timestamps
       ]
     }
     ```

4. **Update URL**
   - Endpoint: `PUT /update/:alias`
   - Request Body:
     ```json
     {
       "custom_alias": "newalias",   // Optional
       "ttl_seconds": 90             // Optional
     }
     ```
   - Response:
     - 200: Success
     - 400: Invalid request
     - 404: Alias not found or expired

5. **Delete URL**
   - Endpoint: `DELETE /delete/:alias`
   - Response:
     - 200: Success
     - 404: Alias not found or expired

### Additional Requirements

1. **Data Persistence**
   - All data should be stored in memory
   - No external databases allowed

2. **Performance**
   - URL redirection should be optimized for low latency
   - All operations should aim for O(1) or O(log n) complexity

3. **Analytics Behavior**
   - When updating a custom alias, analytics data for the old alias should be deleted
   - Access times should be stored in UTC format

4. **TTL Management**
   - URLs should become inaccessible after TTL expiration
   - Expired URL data should be automatically cleaned up
   - Default TTL: 120 seconds if not specified

### Implementation Notes

- The solution should be modular and easily extensible
- Code should be well-documented
- Include proper error handling
- Implement thread-safe operations where necessary
- Include a main/driver class for testing
- Unit tests are recommended but optional

### Example Usage

```
# Create shortened URL
POST /shorten
{
  "long_url": "https://example.com/path",
  "ttl_seconds": 60
}

# Response
{
  "short_url": "http://localhost:8080/abc123"
}

# Access analytics
GET /analytics/abc123
{
  "alias": "abc123",
  "long_url": "https://example.com/path",
  "access_count": 3,
  "access_times": [
    "2024-02-15T10:30:00Z",
    "2024-02-15T10:31:00Z",
    "2024-02-15T10:32:00Z"
  ]
}
```
