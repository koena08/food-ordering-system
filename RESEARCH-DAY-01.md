# Day 01 Research & Self-Quiz

## 2. Concepts to Research

**Q1. What does CRUD stand for?**
>CRUD stands for Create, Read, Update and Delete. These are the four fundamental operations required for persistent data storage in any application.

**Q2. Difference between HTTP methods POST, PUT, PATCH, DELETE?**
>* **POST:** Creates a brand new resource.
>* **PUT:** Replaces an entire existing resource with a new payload.
>* **PATCH:** Modifies only specific fields of an existing resource.
>* **DELETE:** Removes a resource entirely.

**Q3. Give the correct HTTP status code for each:**
>* **a. A new category was created** -> `201 Created`
>* **b. A category was deleted successfully** -> `204 No Content`
>* **c. The id requested does not exist** -> `404 Not Found`
>* **d. The request body is missing a required field** -> `400 Bad Request`
>* **e. The user is logged in but not allowed** -> `403 Forbidden`

**Q4. Difference between @RequestBody, @RequestParam, @PathVariable - with one tiny example of each.**
>* **@RequestBody:** Maps the JSON payload in an incoming HTTP request to a Java object (e.g. creating a user with a full JSON body).
>* **@RequestParam:** Extracts data from the query string of a URL (e.g. `/users?role=admin`).
>* **@PathVariable:** Extracts values directly from the URI path (e.g. `/users/5`).

**Q5. What is Jakarta Bean Validation? Explain @Valid, @NotBlank, @Size.**
>Jakarta Bean Validation is a framework that allows you to define constraints on your data models using annotations.
>* **@Valid:** Tells Spring to trigger the validation checks when a request comes in.
>* **@NotBlank:** Ensures a string is not null and contains at least one non-whitespace character.
>* **@Size:** Restricts the minimum and maximum character length of a string.

**Q6. Why return a DTO and not the entity itself? Give 2 reasons.**
>1. It prevents sensitive database fields (like passwords or internal system flags) from leaking to the client.
>2. It decouples the database schema from the API contract so you can change table structures without breaking mobile apps or frontends relying on the API.

**Q7. What is Optional<T>? Why does findById return Optional?**
>`Optional<T>` is a container object that may or may not hold a non-null value. The `findById` method returns it to explicitly warn the developer that a database record might not exist. This forces the developer to handle the potential absence of data gracefully rather than crashing the application with a `NullPointerException`.

---

## 9. Self-Quiz

**Q1. Why ResponseEntity instead of returning the object?**
>Using `ResponseEntity` allows developers to explicitly set HTTP status codes and headers. If you just return the Java object, Spring defaults to a `200 OK` status even if a `201 Created` is technically the correct REST standard.

**Q2. What status should a successful DELETE return? Why?**
>A successful DELETE should return `204 No Content`. The action was successful but the resource is gone so there is no body data left to return to the client.

**Q3. Update only one field - PUT or PATCH? Defend your answer.**
>PATCH is the correct method. PUT implies you are sending the entire object payload again to overwrite the old one whereas PATCH is specifically designed to apply partial modifications to an existing resource.

**Q4. What happens if you forget @Valid on the controller?**
>If you forget `@Valid`, Spring skips the validation checks completely. The raw and potentially dangerous or malformed data proceeds directly to your service layer which could crash the application or corrupt the database.

**Q5. Why must update/delete have {id} in the URL but create does not?**
>When creating a resource, the database generates the ID for you so the URL targets the overarching collection (e.g. `/api/categories`). When updating or deleting, the system needs to know exactly which specific existing record you want to target (e.g. `/api/categories/5`).